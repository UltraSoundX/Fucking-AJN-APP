package org.jsoup.helper;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.parser.TokenQueue;

public class HttpConnection implements Connection {
    private static final int HTTP_TEMP_REDIR = 307;
    private org.jsoup.Connection.Request req = new Request();
    private org.jsoup.Connection.Response res = new Response();

    private static abstract class Base<T extends org.jsoup.Connection.Base> implements org.jsoup.Connection.Base<T> {
        Map<String, String> cookies;
        Map<String, String> headers;
        Method method;
        URL url;

        private Base() {
            this.headers = new LinkedHashMap();
            this.cookies = new LinkedHashMap();
        }

        public URL url() {
            return this.url;
        }

        public T url(URL url2) {
            Validate.notNull(url2, "URL must not be null");
            this.url = url2;
            return this;
        }

        public Method method() {
            return this.method;
        }

        public T method(Method method2) {
            Validate.notNull(method2, "Method must not be null");
            this.method = method2;
            return this;
        }

        public String header(String str) {
            Validate.notNull(str, "Header name must not be null");
            return getHeaderCaseInsensitive(str);
        }

        public T header(String str, String str2) {
            Validate.notEmpty(str, "Header name must not be empty");
            Validate.notNull(str2, "Header value must not be null");
            removeHeader(str);
            this.headers.put(str, str2);
            return this;
        }

        public boolean hasHeader(String str) {
            Validate.notEmpty(str, "Header name must not be empty");
            return getHeaderCaseInsensitive(str) != null;
        }

        public T removeHeader(String str) {
            Validate.notEmpty(str, "Header name must not be empty");
            Entry scanHeaders = scanHeaders(str);
            if (scanHeaders != null) {
                this.headers.remove(scanHeaders.getKey());
            }
            return this;
        }

        public Map<String, String> headers() {
            return this.headers;
        }

        private String getHeaderCaseInsensitive(String str) {
            Validate.notNull(str, "Header name must not be null");
            String str2 = (String) this.headers.get(str);
            if (str2 == null) {
                str2 = (String) this.headers.get(str.toLowerCase());
            }
            if (str2 != null) {
                return str2;
            }
            Entry scanHeaders = scanHeaders(str);
            if (scanHeaders != null) {
                return (String) scanHeaders.getValue();
            }
            return str2;
        }

        private Entry<String, String> scanHeaders(String str) {
            String lowerCase = str.toLowerCase();
            for (Entry<String, String> entry : this.headers.entrySet()) {
                if (((String) entry.getKey()).toLowerCase().equals(lowerCase)) {
                    return entry;
                }
            }
            return null;
        }

        public String cookie(String str) {
            Validate.notNull(str, "Cookie name must not be null");
            return (String) this.cookies.get(str);
        }

        public T cookie(String str, String str2) {
            Validate.notEmpty(str, "Cookie name must not be empty");
            Validate.notNull(str2, "Cookie value must not be null");
            this.cookies.put(str, str2);
            return this;
        }

        public boolean hasCookie(String str) {
            Validate.notEmpty("Cookie name must not be empty");
            return this.cookies.containsKey(str);
        }

        public T removeCookie(String str) {
            Validate.notEmpty("Cookie name must not be empty");
            this.cookies.remove(str);
            return this;
        }

        public Map<String, String> cookies() {
            return this.cookies;
        }
    }

    public static class KeyVal implements org.jsoup.Connection.KeyVal {
        private String key;
        private String value;

        public static KeyVal create(String str, String str2) {
            Validate.notEmpty(str, "Data key must not be empty");
            Validate.notNull(str2, "Data value must not be null");
            return new KeyVal(str, str2);
        }

        private KeyVal(String str, String str2) {
            this.key = str;
            this.value = str2;
        }

        public KeyVal key(String str) {
            Validate.notEmpty(str, "Data key must not be empty");
            this.key = str;
            return this;
        }

        public String key() {
            return this.key;
        }

        public KeyVal value(String str) {
            Validate.notNull(str, "Data value must not be null");
            this.value = str;
            return this;
        }

        public String value() {
            return this.value;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    public static class Request extends Base<org.jsoup.Connection.Request> implements org.jsoup.Connection.Request {
        private Collection<org.jsoup.Connection.KeyVal> data;
        private boolean followRedirects;
        private boolean ignoreContentType;
        private boolean ignoreHttpErrors;
        private int maxBodySizeBytes;
        private Parser parser;
        private int timeoutMilliseconds;

        public /* bridge */ /* synthetic */ String cookie(String str) {
            return super.cookie(str);
        }

        public /* bridge */ /* synthetic */ Map cookies() {
            return super.cookies();
        }

        public /* bridge */ /* synthetic */ boolean hasCookie(String str) {
            return super.hasCookie(str);
        }

        public /* bridge */ /* synthetic */ boolean hasHeader(String str) {
            return super.hasHeader(str);
        }

        public /* bridge */ /* synthetic */ String header(String str) {
            return super.header(str);
        }

        public /* bridge */ /* synthetic */ Map headers() {
            return super.headers();
        }

        public /* bridge */ /* synthetic */ Method method() {
            return super.method();
        }

        public /* bridge */ /* synthetic */ URL url() {
            return super.url();
        }

        private Request() {
            super();
            this.ignoreHttpErrors = false;
            this.ignoreContentType = false;
            this.timeoutMilliseconds = 3000;
            this.maxBodySizeBytes = 1048576;
            this.followRedirects = true;
            this.data = new ArrayList();
            this.method = Method.GET;
            this.headers.put("Accept-Encoding", "gzip");
            this.parser = Parser.htmlParser();
        }

        public int timeout() {
            return this.timeoutMilliseconds;
        }

        public Request timeout(int i) {
            Validate.isTrue(i >= 0, "Timeout milliseconds must be 0 (infinite) or greater");
            this.timeoutMilliseconds = i;
            return this;
        }

        public int maxBodySize() {
            return this.maxBodySizeBytes;
        }

        public org.jsoup.Connection.Request maxBodySize(int i) {
            Validate.isTrue(i >= 0, "maxSize must be 0 (unlimited) or larger");
            this.maxBodySizeBytes = i;
            return this;
        }

        public boolean followRedirects() {
            return this.followRedirects;
        }

        public org.jsoup.Connection.Request followRedirects(boolean z) {
            this.followRedirects = z;
            return this;
        }

        public boolean ignoreHttpErrors() {
            return this.ignoreHttpErrors;
        }

        public org.jsoup.Connection.Request ignoreHttpErrors(boolean z) {
            this.ignoreHttpErrors = z;
            return this;
        }

        public boolean ignoreContentType() {
            return this.ignoreContentType;
        }

        public org.jsoup.Connection.Request ignoreContentType(boolean z) {
            this.ignoreContentType = z;
            return this;
        }

        public Request data(org.jsoup.Connection.KeyVal keyVal) {
            Validate.notNull(keyVal, "Key val must not be null");
            this.data.add(keyVal);
            return this;
        }

        public Collection<org.jsoup.Connection.KeyVal> data() {
            return this.data;
        }

        public Request parser(Parser parser2) {
            this.parser = parser2;
            return this;
        }

        public Parser parser() {
            return this.parser;
        }
    }

    public static class Response extends Base<org.jsoup.Connection.Response> implements org.jsoup.Connection.Response {
        private static final int MAX_REDIRECTS = 20;
        private static final Pattern xmlContentTypeRxp = Pattern.compile("application/\\w+\\+xml.*");
        private ByteBuffer byteData;
        private String charset;
        private String contentType;
        private boolean executed = false;
        private int numRedirects = 0;
        private org.jsoup.Connection.Request req;
        private int statusCode;
        private String statusMessage;

        public /* bridge */ /* synthetic */ String cookie(String str) {
            return super.cookie(str);
        }

        public /* bridge */ /* synthetic */ Map cookies() {
            return super.cookies();
        }

        public /* bridge */ /* synthetic */ boolean hasCookie(String str) {
            return super.hasCookie(str);
        }

        public /* bridge */ /* synthetic */ boolean hasHeader(String str) {
            return super.hasHeader(str);
        }

        public /* bridge */ /* synthetic */ String header(String str) {
            return super.header(str);
        }

        public /* bridge */ /* synthetic */ Map headers() {
            return super.headers();
        }

        public /* bridge */ /* synthetic */ Method method() {
            return super.method();
        }

        public /* bridge */ /* synthetic */ URL url() {
            return super.url();
        }

        Response() {
            super();
        }

        private Response(Response response) throws IOException {
            super();
            if (response != null) {
                this.numRedirects = response.numRedirects + 1;
                if (this.numRedirects >= 20) {
                    throw new IOException(String.format("Too many redirects occurred trying to load URL %s", new Object[]{response.url()}));
                }
            }
        }

        static Response execute(org.jsoup.Connection.Request request) throws IOException {
            return execute(request, null);
        }

        /* JADX WARNING: Removed duplicated region for block: B:93:0x019c A[SYNTHETIC, Splitter:B:93:0x019c] */
        /* JADX WARNING: Removed duplicated region for block: B:96:0x01a1 A[Catch:{ all -> 0x00e0 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        static org.jsoup.helper.HttpConnection.Response execute(org.jsoup.Connection.Request r7, org.jsoup.helper.HttpConnection.Response r8) throws java.io.IOException {
            /*
                r1 = 0
                r4 = 1
                java.lang.String r0 = "Request must not be null"
                org.jsoup.helper.Validate.notNull(r7, r0)
                java.net.URL r0 = r7.url()
                java.lang.String r0 = r0.getProtocol()
                java.lang.String r2 = "http"
                boolean r2 = r0.equals(r2)
                if (r2 != 0) goto L_0x0027
                java.lang.String r2 = "https"
                boolean r0 = r0.equals(r2)
                if (r0 != 0) goto L_0x0027
                java.net.MalformedURLException r0 = new java.net.MalformedURLException
                java.lang.String r1 = "Only http & https protocols supported"
                r0.<init>(r1)
                throw r0
            L_0x0027:
                org.jsoup.Connection$Method r0 = r7.method()
                org.jsoup.Connection$Method r2 = org.jsoup.Connection.Method.GET
                if (r0 != r2) goto L_0x003c
                java.util.Collection r0 = r7.data()
                int r0 = r0.size()
                if (r0 <= 0) goto L_0x003c
                serialiseRequestUrl(r7)
            L_0x003c:
                java.net.HttpURLConnection r5 = createConnection(r7)
                r5.connect()     // Catch:{ all -> 0x00e0 }
                org.jsoup.Connection$Method r0 = r7.method()     // Catch:{ all -> 0x00e0 }
                org.jsoup.Connection$Method r2 = org.jsoup.Connection.Method.POST     // Catch:{ all -> 0x00e0 }
                if (r0 != r2) goto L_0x0056
                java.util.Collection r0 = r7.data()     // Catch:{ all -> 0x00e0 }
                java.io.OutputStream r2 = r5.getOutputStream()     // Catch:{ all -> 0x00e0 }
                writePost(r0, r2)     // Catch:{ all -> 0x00e0 }
            L_0x0056:
                int r2 = r5.getResponseCode()     // Catch:{ all -> 0x00e0 }
                r0 = 0
                r3 = 200(0xc8, float:2.8E-43)
                if (r2 == r3) goto L_0x0070
                r3 = 302(0x12e, float:4.23E-43)
                if (r2 == r3) goto L_0x006f
                r3 = 301(0x12d, float:4.22E-43)
                if (r2 == r3) goto L_0x006f
                r3 = 303(0x12f, float:4.25E-43)
                if (r2 == r3) goto L_0x006f
                r3 = 307(0x133, float:4.3E-43)
                if (r2 != r3) goto L_0x00e5
            L_0x006f:
                r0 = r4
            L_0x0070:
                org.jsoup.helper.HttpConnection$Response r2 = new org.jsoup.helper.HttpConnection$Response     // Catch:{ all -> 0x00e0 }
                r2.<init>(r8)     // Catch:{ all -> 0x00e0 }
                r2.setupFromConnection(r5, r8)     // Catch:{ all -> 0x00e0 }
                if (r0 == 0) goto L_0x0103
                boolean r0 = r7.followRedirects()     // Catch:{ all -> 0x00e0 }
                if (r0 == 0) goto L_0x0103
                org.jsoup.Connection$Method r0 = org.jsoup.Connection.Method.GET     // Catch:{ all -> 0x00e0 }
                r7.method(r0)     // Catch:{ all -> 0x00e0 }
                java.util.Collection r0 = r7.data()     // Catch:{ all -> 0x00e0 }
                r0.clear()     // Catch:{ all -> 0x00e0 }
                java.lang.String r0 = "Location"
                java.lang.String r0 = r2.header(r0)     // Catch:{ all -> 0x00e0 }
                if (r0 == 0) goto L_0x00aa
                java.lang.String r1 = "http:/"
                boolean r1 = r0.startsWith(r1)     // Catch:{ all -> 0x00e0 }
                if (r1 == 0) goto L_0x00aa
                r1 = 6
                char r1 = r0.charAt(r1)     // Catch:{ all -> 0x00e0 }
                r3 = 47
                if (r1 == r3) goto L_0x00aa
                r1 = 6
                java.lang.String r0 = r0.substring(r1)     // Catch:{ all -> 0x00e0 }
            L_0x00aa:
                java.net.URL r1 = new java.net.URL     // Catch:{ all -> 0x00e0 }
                java.net.URL r3 = r7.url()     // Catch:{ all -> 0x00e0 }
                java.lang.String r0 = org.jsoup.helper.HttpConnection.encodeUrl(r0)     // Catch:{ all -> 0x00e0 }
                r1.<init>(r3, r0)     // Catch:{ all -> 0x00e0 }
                r7.url(r1)     // Catch:{ all -> 0x00e0 }
                java.util.Map r0 = r2.cookies     // Catch:{ all -> 0x00e0 }
                java.util.Set r0 = r0.entrySet()     // Catch:{ all -> 0x00e0 }
                java.util.Iterator r3 = r0.iterator()     // Catch:{ all -> 0x00e0 }
            L_0x00c4:
                boolean r0 = r3.hasNext()     // Catch:{ all -> 0x00e0 }
                if (r0 == 0) goto L_0x00fb
                java.lang.Object r0 = r3.next()     // Catch:{ all -> 0x00e0 }
                java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ all -> 0x00e0 }
                java.lang.Object r1 = r0.getKey()     // Catch:{ all -> 0x00e0 }
                java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x00e0 }
                java.lang.Object r0 = r0.getValue()     // Catch:{ all -> 0x00e0 }
                java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x00e0 }
                r7.cookie(r1, r0)     // Catch:{ all -> 0x00e0 }
                goto L_0x00c4
            L_0x00e0:
                r0 = move-exception
                r5.disconnect()
                throw r0
            L_0x00e5:
                boolean r3 = r7.ignoreHttpErrors()     // Catch:{ all -> 0x00e0 }
                if (r3 != 0) goto L_0x0070
                org.jsoup.HttpStatusException r0 = new org.jsoup.HttpStatusException     // Catch:{ all -> 0x00e0 }
                java.lang.String r1 = "HTTP error fetching URL"
                java.net.URL r3 = r7.url()     // Catch:{ all -> 0x00e0 }
                java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00e0 }
                r0.<init>(r1, r2, r3)     // Catch:{ all -> 0x00e0 }
                throw r0     // Catch:{ all -> 0x00e0 }
            L_0x00fb:
                org.jsoup.helper.HttpConnection$Response r0 = execute(r7, r2)     // Catch:{ all -> 0x00e0 }
                r5.disconnect()
            L_0x0102:
                return r0
            L_0x0103:
                r2.req = r7     // Catch:{ all -> 0x00e0 }
                java.lang.String r0 = r2.contentType()     // Catch:{ all -> 0x00e0 }
                if (r0 == 0) goto L_0x013d
                boolean r3 = r7.ignoreContentType()     // Catch:{ all -> 0x00e0 }
                if (r3 != 0) goto L_0x013d
                java.lang.String r3 = "text/"
                boolean r3 = r0.startsWith(r3)     // Catch:{ all -> 0x00e0 }
                if (r3 != 0) goto L_0x013d
                java.lang.String r3 = "application/xml"
                boolean r3 = r0.startsWith(r3)     // Catch:{ all -> 0x00e0 }
                if (r3 != 0) goto L_0x013d
                java.util.regex.Pattern r3 = xmlContentTypeRxp     // Catch:{ all -> 0x00e0 }
                java.util.regex.Matcher r3 = r3.matcher(r0)     // Catch:{ all -> 0x00e0 }
                boolean r3 = r3.matches()     // Catch:{ all -> 0x00e0 }
                if (r3 != 0) goto L_0x013d
                org.jsoup.UnsupportedMimeTypeException r1 = new org.jsoup.UnsupportedMimeTypeException     // Catch:{ all -> 0x00e0 }
                java.lang.String r2 = "Unhandled content type. Must be text/*, application/xml, or application/xhtml+xml"
                java.net.URL r3 = r7.url()     // Catch:{ all -> 0x00e0 }
                java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00e0 }
                r1.<init>(r2, r0, r3)     // Catch:{ all -> 0x00e0 }
                throw r1     // Catch:{ all -> 0x00e0 }
            L_0x013d:
                java.io.InputStream r0 = r5.getErrorStream()     // Catch:{ all -> 0x0198 }
                if (r0 == 0) goto L_0x018c
                java.io.InputStream r3 = r5.getErrorStream()     // Catch:{ all -> 0x0198 }
            L_0x0147:
                java.lang.String r0 = "Content-Encoding"
                boolean r0 = r2.hasHeader(r0)     // Catch:{ all -> 0x01a5 }
                if (r0 == 0) goto L_0x0191
                java.lang.String r0 = "Content-Encoding"
                java.lang.String r0 = r2.header(r0)     // Catch:{ all -> 0x01a5 }
                java.lang.String r6 = "gzip"
                boolean r0 = r0.equalsIgnoreCase(r6)     // Catch:{ all -> 0x01a5 }
                if (r0 == 0) goto L_0x0191
                java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ all -> 0x01a5 }
                java.util.zip.GZIPInputStream r6 = new java.util.zip.GZIPInputStream     // Catch:{ all -> 0x01a5 }
                r6.<init>(r3)     // Catch:{ all -> 0x01a5 }
                r0.<init>(r6)     // Catch:{ all -> 0x01a5 }
                r1 = r0
            L_0x0168:
                int r0 = r7.maxBodySize()     // Catch:{ all -> 0x01a9 }
                java.nio.ByteBuffer r0 = org.jsoup.helper.DataUtil.readToByteBuffer(r1, r0)     // Catch:{ all -> 0x01a9 }
                r2.byteData = r0     // Catch:{ all -> 0x01a9 }
                java.lang.String r0 = r2.contentType     // Catch:{ all -> 0x01a9 }
                java.lang.String r0 = org.jsoup.helper.DataUtil.getCharsetFromContentType(r0)     // Catch:{ all -> 0x01a9 }
                r2.charset = r0     // Catch:{ all -> 0x01a9 }
                if (r1 == 0) goto L_0x017f
                r1.close()     // Catch:{ all -> 0x00e0 }
            L_0x017f:
                if (r3 == 0) goto L_0x0184
                r3.close()     // Catch:{ all -> 0x00e0 }
            L_0x0184:
                r5.disconnect()
                r2.executed = r4
                r0 = r2
                goto L_0x0102
            L_0x018c:
                java.io.InputStream r3 = r5.getInputStream()     // Catch:{ all -> 0x0198 }
                goto L_0x0147
            L_0x0191:
                java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ all -> 0x01a5 }
                r0.<init>(r3)     // Catch:{ all -> 0x01a5 }
                r1 = r0
                goto L_0x0168
            L_0x0198:
                r0 = move-exception
                r2 = r1
            L_0x019a:
                if (r2 == 0) goto L_0x019f
                r2.close()     // Catch:{ all -> 0x00e0 }
            L_0x019f:
                if (r1 == 0) goto L_0x01a4
                r1.close()     // Catch:{ all -> 0x00e0 }
            L_0x01a4:
                throw r0     // Catch:{ all -> 0x00e0 }
            L_0x01a5:
                r0 = move-exception
                r2 = r1
                r1 = r3
                goto L_0x019a
            L_0x01a9:
                r0 = move-exception
                r2 = r1
                r1 = r3
                goto L_0x019a
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.helper.HttpConnection.Response.execute(org.jsoup.Connection$Request, org.jsoup.helper.HttpConnection$Response):org.jsoup.helper.HttpConnection$Response");
        }

        public int statusCode() {
            return this.statusCode;
        }

        public String statusMessage() {
            return this.statusMessage;
        }

        public String charset() {
            return this.charset;
        }

        public String contentType() {
            return this.contentType;
        }

        public Document parse() throws IOException {
            Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before parsing response");
            Document parseByteData = DataUtil.parseByteData(this.byteData, this.charset, this.url.toExternalForm(), this.req.parser());
            this.byteData.rewind();
            this.charset = parseByteData.outputSettings().charset().name();
            return parseByteData;
        }

        public String body() {
            String charBuffer;
            Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before getting response body");
            if (this.charset == null) {
                charBuffer = Charset.forName("UTF-8").decode(this.byteData).toString();
            } else {
                charBuffer = Charset.forName(this.charset).decode(this.byteData).toString();
            }
            this.byteData.rewind();
            return charBuffer;
        }

        public byte[] bodyAsBytes() {
            Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before getting response body");
            return this.byteData.array();
        }

        private static HttpURLConnection createConnection(org.jsoup.Connection.Request request) throws IOException {
            HttpURLConnection httpURLConnection = (HttpURLConnection) request.url().openConnection();
            httpURLConnection.setRequestMethod(request.method().name());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setConnectTimeout(request.timeout());
            httpURLConnection.setReadTimeout(request.timeout());
            if (request.method() == Method.POST) {
                httpURLConnection.setDoOutput(true);
            }
            if (request.cookies().size() > 0) {
                httpURLConnection.addRequestProperty("Cookie", getRequestCookieString(request));
            }
            for (Entry entry : request.headers().entrySet()) {
                httpURLConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
            return httpURLConnection;
        }

        private void setupFromConnection(HttpURLConnection httpURLConnection, org.jsoup.Connection.Response response) throws IOException {
            this.method = Method.valueOf(httpURLConnection.getRequestMethod());
            this.url = httpURLConnection.getURL();
            this.statusCode = httpURLConnection.getResponseCode();
            this.statusMessage = httpURLConnection.getResponseMessage();
            this.contentType = httpURLConnection.getContentType();
            processResponseHeaders(httpURLConnection.getHeaderFields());
            if (response != null) {
                for (Entry entry : response.cookies().entrySet()) {
                    if (!hasCookie((String) entry.getKey())) {
                        cookie((String) entry.getKey(), (String) entry.getValue());
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void processResponseHeaders(Map<String, List<String>> map) {
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str != null) {
                    List<String> list = (List) entry.getValue();
                    if (str.equalsIgnoreCase("Set-Cookie")) {
                        for (String str2 : list) {
                            if (str2 != null) {
                                TokenQueue tokenQueue = new TokenQueue(str2);
                                String trim = tokenQueue.chompTo("=").trim();
                                String trim2 = tokenQueue.consumeTo(";").trim();
                                if (trim2 == null) {
                                    trim2 = "";
                                }
                                if (trim != null && trim.length() > 0) {
                                    cookie(trim, trim2);
                                }
                            }
                        }
                    } else if (!list.isEmpty()) {
                        header(str, (String) list.get(0));
                    }
                }
            }
        }

        private static void writePost(Collection<org.jsoup.Connection.KeyVal> collection, OutputStream outputStream) throws IOException {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            boolean z = true;
            for (org.jsoup.Connection.KeyVal keyVal : collection) {
                if (!z) {
                    outputStreamWriter.append('&');
                } else {
                    z = false;
                }
                outputStreamWriter.write(URLEncoder.encode(keyVal.key(), "UTF-8"));
                outputStreamWriter.write(61);
                outputStreamWriter.write(URLEncoder.encode(keyVal.value(), "UTF-8"));
            }
            outputStreamWriter.close();
        }

        private static String getRequestCookieString(org.jsoup.Connection.Request request) {
            boolean z;
            StringBuilder sb = new StringBuilder();
            boolean z2 = true;
            for (Entry entry : request.cookies().entrySet()) {
                if (!z2) {
                    sb.append("; ");
                    z = z2;
                } else {
                    z = false;
                }
                sb.append((String) entry.getKey()).append('=').append((String) entry.getValue());
                z2 = z;
            }
            return sb.toString();
        }

        private static void serialiseRequestUrl(org.jsoup.Connection.Request request) throws IOException {
            URL url = request.url();
            StringBuilder sb = new StringBuilder();
            boolean z = true;
            sb.append(url.getProtocol()).append("://").append(url.getAuthority()).append(url.getPath()).append("?");
            if (url.getQuery() != null) {
                sb.append(url.getQuery());
                z = false;
            }
            boolean z2 = z;
            for (org.jsoup.Connection.KeyVal keyVal : request.data()) {
                if (!z2) {
                    sb.append('&');
                } else {
                    z2 = false;
                }
                sb.append(URLEncoder.encode(keyVal.key(), "UTF-8")).append('=').append(URLEncoder.encode(keyVal.value(), "UTF-8"));
            }
            request.url(new URL(sb.toString()));
            request.data().clear();
        }
    }

    public static Connection connect(String str) {
        HttpConnection httpConnection = new HttpConnection();
        httpConnection.url(str);
        return httpConnection;
    }

    public static Connection connect(URL url) {
        HttpConnection httpConnection = new HttpConnection();
        httpConnection.url(url);
        return httpConnection;
    }

    /* access modifiers changed from: private */
    public static String encodeUrl(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll(" ", "%20");
    }

    private HttpConnection() {
    }

    public Connection url(URL url) {
        this.req.url(url);
        return this;
    }

    public Connection url(String str) {
        Validate.notEmpty(str, "Must supply a valid URL");
        try {
            this.req.url(new URL(encodeUrl(str)));
            return this;
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Malformed URL: " + str, e);
        }
    }

    public Connection userAgent(String str) {
        Validate.notNull(str, "User agent must not be null");
        this.req.header("User-Agent", str);
        return this;
    }

    public Connection timeout(int i) {
        this.req.timeout(i);
        return this;
    }

    public Connection maxBodySize(int i) {
        this.req.maxBodySize(i);
        return this;
    }

    public Connection followRedirects(boolean z) {
        this.req.followRedirects(z);
        return this;
    }

    public Connection referrer(String str) {
        Validate.notNull(str, "Referrer must not be null");
        this.req.header("Referer", str);
        return this;
    }

    public Connection method(Method method) {
        this.req.method(method);
        return this;
    }

    public Connection ignoreHttpErrors(boolean z) {
        this.req.ignoreHttpErrors(z);
        return this;
    }

    public Connection ignoreContentType(boolean z) {
        this.req.ignoreContentType(z);
        return this;
    }

    public Connection data(String str, String str2) {
        this.req.data(KeyVal.create(str, str2));
        return this;
    }

    public Connection data(Map<String, String> map) {
        Validate.notNull(map, "Data map must not be null");
        for (Entry entry : map.entrySet()) {
            this.req.data(KeyVal.create((String) entry.getKey(), (String) entry.getValue()));
        }
        return this;
    }

    public Connection data(String... strArr) {
        boolean z;
        Validate.notNull(strArr, "Data key value pairs must not be null");
        if (strArr.length % 2 == 0) {
            z = true;
        } else {
            z = false;
        }
        Validate.isTrue(z, "Must supply an even number of key value pairs");
        for (int i = 0; i < strArr.length; i += 2) {
            String str = strArr[i];
            String str2 = strArr[i + 1];
            Validate.notEmpty(str, "Data key must not be empty");
            Validate.notNull(str2, "Data value must not be null");
            this.req.data(KeyVal.create(str, str2));
        }
        return this;
    }

    public Connection data(Collection<org.jsoup.Connection.KeyVal> collection) {
        Validate.notNull(collection, "Data collection must not be null");
        for (org.jsoup.Connection.KeyVal data : collection) {
            this.req.data(data);
        }
        return this;
    }

    public Connection header(String str, String str2) {
        this.req.header(str, str2);
        return this;
    }

    public Connection cookie(String str, String str2) {
        this.req.cookie(str, str2);
        return this;
    }

    public Connection cookies(Map<String, String> map) {
        Validate.notNull(map, "Cookie map must not be null");
        for (Entry entry : map.entrySet()) {
            this.req.cookie((String) entry.getKey(), (String) entry.getValue());
        }
        return this;
    }

    public Connection parser(Parser parser) {
        this.req.parser(parser);
        return this;
    }

    public Document get() throws IOException {
        this.req.method(Method.GET);
        execute();
        return this.res.parse();
    }

    public Document post() throws IOException {
        this.req.method(Method.POST);
        execute();
        return this.res.parse();
    }

    public org.jsoup.Connection.Response execute() throws IOException {
        this.res = Response.execute(this.req);
        return this.res;
    }

    public org.jsoup.Connection.Request request() {
        return this.req;
    }

    public Connection request(org.jsoup.Connection.Request request) {
        this.req = request;
        return this;
    }

    public org.jsoup.Connection.Response response() {
        return this.res;
    }

    public Connection response(org.jsoup.Connection.Response response) {
        this.res = response;
        return this;
    }
}
