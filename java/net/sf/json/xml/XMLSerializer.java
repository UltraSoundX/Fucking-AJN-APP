package net.sf.json.xml;

import com.baidu.mobstat.Config;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONFunction;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Serializer;
import nu.xom.Text;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class XMLSerializer {
    private static final String[] EMPTY_ARRAY = new String[0];
    private static final String JSON_PREFIX = "json_";
    static Class class$net$sf$json$xml$XMLSerializer;
    private static final Log log;
    private String arrayName;
    private String elementName;
    private String[] expandableProperties;
    private boolean forceTopLevelObject;
    private boolean namespaceLenient;
    private Map namespacesPerElement = new TreeMap();
    private String objectName;
    private boolean removeNamespacePrefixFromElements;
    private String rootName;
    private Map rootNamespace = new TreeMap();
    private boolean skipNamespaces;
    private boolean skipWhitespace;
    private boolean trimSpaces;
    private boolean typeHintsCompatibility;
    private boolean typeHintsEnabled;

    private static class CustomElement extends Element {
        private String prefix;

        private static String getName(String str) {
            int indexOf = str.indexOf(58);
            if (indexOf != -1) {
                return str.substring(indexOf + 1);
            }
            return str;
        }

        private static String getPrefix(String str) {
            int indexOf = str.indexOf(58);
            if (indexOf != -1) {
                return str.substring(0, indexOf);
            }
            return "";
        }

        public CustomElement(String str) {
            super(getName(str));
            this.prefix = getPrefix(str);
        }

        public final String getQName() {
            if (this.prefix.length() == 0) {
                return getLocalName();
            }
            return new StringBuffer().append(this.prefix).append(Config.TRACE_TODAY_VISIT_SPLIT).append(getLocalName()).toString();
        }
    }

    private class XomSerializer extends Serializer {
        private final XMLSerializer this$0;

        public XomSerializer(XMLSerializer xMLSerializer, OutputStream outputStream) {
            super(outputStream);
            this.this$0 = xMLSerializer;
        }

        public XomSerializer(XMLSerializer xMLSerializer, OutputStream outputStream, String str) throws UnsupportedEncodingException {
            super(outputStream, str);
            this.this$0 = xMLSerializer;
        }

        /* access modifiers changed from: protected */
        public void write(Text text) throws IOException {
            String value = text.getValue();
            if (!value.startsWith("<![CDATA[") || !value.endsWith("]]>")) {
                XMLSerializer.super.write(text);
                return;
            }
            String substring = value.substring(9);
            String substring2 = substring.substring(0, substring.length() - 3);
            writeRaw("<![CDATA[");
            writeRaw(substring2);
            writeRaw("]]>");
        }

        /* access modifiers changed from: protected */
        public void writeEmptyElementTag(Element element) throws IOException {
            if (!(element instanceof CustomElement) || !this.this$0.isNamespaceLenient()) {
                XMLSerializer.super.writeEmptyElementTag(element);
                return;
            }
            writeTagBeginning((CustomElement) element);
            writeRaw("/>");
        }

        /* access modifiers changed from: protected */
        public void writeEndTag(Element element) throws IOException {
            if (!(element instanceof CustomElement) || !this.this$0.isNamespaceLenient()) {
                XMLSerializer.super.writeEndTag(element);
                return;
            }
            writeRaw("</");
            writeRaw(((CustomElement) element).getQName());
            writeRaw(">");
        }

        /* access modifiers changed from: protected */
        public void writeNamespaceDeclaration(String str, String str2) throws IOException {
            if (!StringUtils.isBlank(str2)) {
                XMLSerializer.super.writeNamespaceDeclaration(str, str2);
            }
        }

        /* access modifiers changed from: protected */
        public void writeStartTag(Element element) throws IOException {
            if (!(element instanceof CustomElement) || !this.this$0.isNamespaceLenient()) {
                XMLSerializer.super.writeStartTag(element);
                return;
            }
            writeTagBeginning((CustomElement) element);
            writeRaw(">");
        }

        private void writeTagBeginning(CustomElement customElement) throws IOException {
            writeRaw("<");
            writeRaw(customElement.getQName());
            writeAttributes(customElement);
            writeNamespaceDeclarations(customElement);
        }
    }

    static {
        Class cls;
        if (class$net$sf$json$xml$XMLSerializer == null) {
            cls = class$("net.sf.json.xml.XMLSerializer");
            class$net$sf$json$xml$XMLSerializer = cls;
        } else {
            cls = class$net$sf$json$xml$XMLSerializer;
        }
        log = LogFactory.getLog(cls);
    }

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public XMLSerializer() {
        setObjectName(Config.OS);
        setArrayName(Config.APP_VERSION_CODE);
        setElementName("e");
        setTypeHintsEnabled(true);
        setTypeHintsCompatibility(true);
        setNamespaceLenient(false);
        setSkipNamespaces(false);
        setRemoveNamespacePrefixFromElements(false);
        setTrimSpaces(false);
        setExpandableProperties(EMPTY_ARRAY);
        setSkipNamespaces(false);
    }

    public void addNamespace(String str, String str2) {
        addNamespace(str, str2, null);
    }

    public void addNamespace(String str, String str2, String str3) {
        if (!StringUtils.isBlank(str2)) {
            if (str == null) {
                str = "";
            }
            if (StringUtils.isBlank(str3)) {
                this.rootNamespace.put(str.trim(), str2.trim());
                return;
            }
            Map map = (Map) this.namespacesPerElement.get(str3);
            if (map == null) {
                map = new TreeMap();
                this.namespacesPerElement.put(str3, map);
            }
            map.put(str, str2);
        }
    }

    public void clearNamespaces() {
        this.rootNamespace.clear();
        this.namespacesPerElement.clear();
    }

    public void clearNamespaces(String str) {
        if (StringUtils.isBlank(str)) {
            this.rootNamespace.clear();
        } else {
            this.namespacesPerElement.remove(str);
        }
    }

    public String getArrayName() {
        return this.arrayName;
    }

    public String getElementName() {
        return this.elementName;
    }

    public String[] getExpandableProperties() {
        return this.expandableProperties;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public String getRootName() {
        return this.rootName;
    }

    public boolean isForceTopLevelObject() {
        return this.forceTopLevelObject;
    }

    public boolean isNamespaceLenient() {
        return this.namespaceLenient;
    }

    public boolean isRemoveNamespacePrefixFromElements() {
        return this.removeNamespacePrefixFromElements;
    }

    public boolean isSkipNamespaces() {
        return this.skipNamespaces;
    }

    public boolean isSkipWhitespace() {
        return this.skipWhitespace;
    }

    public boolean isTrimSpaces() {
        return this.trimSpaces;
    }

    public boolean isTypeHintsCompatibility() {
        return this.typeHintsCompatibility;
    }

    public boolean isTypeHintsEnabled() {
        return this.typeHintsEnabled;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 36
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public net.sf.json.JSON read(java.lang.String r4) {
        /*
            r3 = this;
            nu.xom.Builder r0 = new nu.xom.Builder     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            r0.<init>()     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            java.io.StringReader r1 = new java.io.StringReader     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            r1.<init>(r4)     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            nu.xom.Document r0 = r0.build(r1)     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            nu.xom.Element r1 = r0.getRootElement()     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            boolean r0 = r3.isNullObject(r1)     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            if (r0 == 0) goto L_0x001d
            net.sf.json.JSONNull r0 = net.sf.json.JSONNull.getInstance()     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
        L_0x001c:
            return r0
        L_0x001d:
            java.lang.String r0 = "string"
            java.lang.String r0 = r3.getType(r1, r0)     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            r2 = 1
            boolean r2 = r3.isArray(r1, r2)     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            if (r2 == 0) goto L_0x0044
            net.sf.json.JSON r0 = r3.processArrayElement(r1, r0)     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            boolean r2 = r3.forceTopLevelObject     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            if (r2 == 0) goto L_0x001c
            java.lang.String r1 = r1.getQualifiedName()     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            java.lang.String r1 = r3.removeNamespacePrefix(r1)     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            net.sf.json.JSONObject r2 = new net.sf.json.JSONObject     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            r2.<init>()     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            net.sf.json.JSONObject r0 = r2.element(r1, r0)     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            goto L_0x001c
        L_0x0044:
            net.sf.json.JSON r0 = r3.processObjectElement(r1, r0)     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            boolean r2 = r3.forceTopLevelObject     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            if (r2 == 0) goto L_0x001c
            java.lang.String r1 = r1.getQualifiedName()     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            java.lang.String r1 = r3.removeNamespacePrefix(r1)     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            net.sf.json.JSONObject r2 = new net.sf.json.JSONObject     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            r2.<init>()     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            net.sf.json.JSONObject r0 = r2.element(r1, r0)     // Catch:{ JSONException -> 0x005e, Exception -> 0x0060 }
            goto L_0x001c
        L_0x005e:
            r0 = move-exception
            throw r0
        L_0x0060:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.xml.XMLSerializer.read(java.lang.String):net.sf.json.JSON");
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v7, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 20
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public net.sf.json.JSON readFromFile(java.io.File r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x000a
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "File is null"
            r0.<init>(r1)
            throw r0
        L_0x000a:
            boolean r0 = r3.canRead()
            if (r0 != 0) goto L_0x0018
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Can't read input file"
            r0.<init>(r1)
            throw r0
        L_0x0018:
            boolean r0 = r3.isDirectory()
            if (r0 == 0) goto L_0x0026
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "File is a directory"
            r0.<init>(r1)
            throw r0
        L_0x0026:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0030 }
            r0.<init>(r3)     // Catch:{ IOException -> 0x0030 }
            net.sf.json.JSON r0 = r2.readFromStream(r0)     // Catch:{ IOException -> 0x0030 }
            return r0
        L_0x0030:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.xml.XMLSerializer.readFromFile(java.io.File):net.sf.json.JSON");
    }

    public JSON readFromFile(String str) {
        return readFromStream(Thread.currentThread().getContextClassLoader().getResourceAsStream(str));
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 12
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public net.sf.json.JSON readFromStream(java.io.InputStream r4) {
        /*
            r3 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch:{ IOException -> 0x0019 }
            r0.<init>()     // Catch:{ IOException -> 0x0019 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0019 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0019 }
            r2.<init>(r4)     // Catch:{ IOException -> 0x0019 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x0019 }
        L_0x000f:
            java.lang.String r2 = r1.readLine()     // Catch:{ IOException -> 0x0019 }
            if (r2 == 0) goto L_0x0020
            r0.append(r2)     // Catch:{ IOException -> 0x0019 }
            goto L_0x000f
        L_0x0019:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        L_0x0020:
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x0019 }
            net.sf.json.JSON r0 = r3.read(r0)     // Catch:{ IOException -> 0x0019 }
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.xml.XMLSerializer.readFromStream(java.io.InputStream):net.sf.json.JSON");
    }

    public void removeNamespace(String str) {
        removeNamespace(str, null);
    }

    public void removeNamespace(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (StringUtils.isBlank(str2)) {
            this.rootNamespace.remove(str.trim());
        } else {
            ((Map) this.namespacesPerElement.get(str2)).remove(str);
        }
    }

    public void setArrayName(String str) {
        if (StringUtils.isBlank(str)) {
            str = Config.APP_VERSION_CODE;
        }
        this.arrayName = str;
    }

    public void setElementName(String str) {
        if (StringUtils.isBlank(str)) {
            str = "e";
        }
        this.elementName = str;
    }

    public void setExpandableProperties(String[] strArr) {
        if (strArr == null) {
            strArr = EMPTY_ARRAY;
        }
        this.expandableProperties = strArr;
    }

    public void setForceTopLevelObject(boolean z) {
        this.forceTopLevelObject = z;
    }

    public void setNamespace(String str, String str2) {
        setNamespace(str, str2, null);
    }

    public void setNamespace(String str, String str2, String str3) {
        if (!StringUtils.isBlank(str2)) {
            if (str == null) {
                str = "";
            }
            if (StringUtils.isBlank(str3)) {
                this.rootNamespace.clear();
                this.rootNamespace.put(str.trim(), str2.trim());
                return;
            }
            Map map = (Map) this.namespacesPerElement.get(str3);
            if (map == null) {
                map = new TreeMap();
                this.namespacesPerElement.put(str3, map);
            }
            map.clear();
            map.put(str, str2);
        }
    }

    public void setNamespaceLenient(boolean z) {
        this.namespaceLenient = z;
    }

    public void setObjectName(String str) {
        if (StringUtils.isBlank(str)) {
            str = Config.OS;
        }
        this.objectName = str;
    }

    public void setRemoveNamespacePrefixFromElements(boolean z) {
        this.removeNamespacePrefixFromElements = z;
    }

    public void setRootName(String str) {
        if (StringUtils.isBlank(str)) {
            str = null;
        }
        this.rootName = str;
    }

    public void setSkipNamespaces(boolean z) {
        this.skipNamespaces = z;
    }

    public void setSkipWhitespace(boolean z) {
        this.skipWhitespace = z;
    }

    public void setTrimSpaces(boolean z) {
        this.trimSpaces = z;
    }

    public void setTypeHintsCompatibility(boolean z) {
        this.typeHintsCompatibility = z;
    }

    public void setTypeHintsEnabled(boolean z) {
        this.typeHintsEnabled = z;
    }

    public String write(JSON json) {
        return write(json, null);
    }

    public String write(JSON json, String str) {
        Element processJSONObject;
        if (JSONNull.getInstance().equals(json)) {
            Element newElement = newElement(getRootName() == null ? getObjectName() : getRootName());
            newElement.addAttribute(new Attribute(addJsonPrefix("null"), "true"));
            return writeDocument(new Document(newElement), str);
        } else if (json instanceof JSONArray) {
            return writeDocument(new Document(processJSONArray((JSONArray) json, newElement(getRootName() == null ? getArrayName() : getRootName()), this.expandableProperties)), str);
        } else {
            JSONObject jSONObject = (JSONObject) json;
            if (jSONObject.isNullObject()) {
                processJSONObject = newElement(getObjectName());
                processJSONObject.addAttribute(new Attribute(addJsonPrefix("null"), "true"));
            } else {
                processJSONObject = processJSONObject(jSONObject, newElement(getRootName() == null ? getObjectName() : getRootName()), this.expandableProperties, true);
            }
            return writeDocument(new Document(processJSONObject), str);
        }
    }

    private String addJsonPrefix(String str) {
        if (!isTypeHintsCompatibility()) {
            return new StringBuffer().append(JSON_PREFIX).append(str).toString();
        }
        return str;
    }

    private void addNameSpaceToElement(Element element) {
        String qualifiedName;
        if (element instanceof CustomElement) {
            qualifiedName = ((CustomElement) element).getQName();
        } else {
            qualifiedName = element.getQualifiedName();
        }
        Map map = (Map) this.namespacesPerElement.get(qualifiedName);
        if (map != null && !map.isEmpty()) {
            setNamespaceLenient(true);
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                if (StringUtils.isBlank(str)) {
                    element.setNamespaceURI(str2);
                } else {
                    element.addNamespaceDeclaration(str, str2);
                }
            }
        }
    }

    private boolean checkChildElements(Element element, boolean z) {
        int childCount = element.getChildCount();
        Elements childElements = element.getChildElements();
        int size = childElements.size();
        if (childCount == 1 && (element.getChild(0) instanceof Text)) {
            return z;
        }
        if (childCount == size) {
            if (size == 0) {
                return true;
            }
            if (size == 1) {
                if (this.skipWhitespace || (element.getChild(0) instanceof Text)) {
                    return true;
                }
                return false;
            }
        }
        if (childCount > size) {
            for (int i = 0; i < childCount; i++) {
                Text child = element.getChild(i);
                if ((child instanceof Text) && StringUtils.isNotBlank(StringUtils.strip(child.getValue())) && !this.skipWhitespace) {
                    return false;
                }
            }
        }
        String qualifiedName = childElements.get(0).getQualifiedName();
        for (int i2 = 1; i2 < size; i2++) {
            if (qualifiedName.compareTo(childElements.get(i2).getQualifiedName()) != 0) {
                return false;
            }
        }
        return true;
    }

    private String getClass(Element element) {
        Attribute attribute = element.getAttribute(addJsonPrefix("class"));
        if (attribute == null) {
            return null;
        }
        String trim = attribute.getValue().trim();
        if (JSONTypes.OBJECT.compareToIgnoreCase(trim) == 0) {
            return JSONTypes.OBJECT;
        }
        if (JSONTypes.ARRAY.compareToIgnoreCase(trim) == 0) {
            return JSONTypes.ARRAY;
        }
        return null;
    }

    private String getType(Element element) {
        return getType(element, null);
    }

    private String getType(Element element, String str) {
        Attribute attribute = element.getAttribute(addJsonPrefix("type"));
        if (attribute != null) {
            String trim = attribute.getValue().trim();
            if ("boolean".compareToIgnoreCase(trim) == 0) {
                return "boolean";
            }
            if (JSONTypes.NUMBER.compareToIgnoreCase(trim) == 0) {
                return JSONTypes.NUMBER;
            }
            if ("integer".compareToIgnoreCase(trim) == 0) {
                return "integer";
            }
            if ("float".compareToIgnoreCase(trim) == 0) {
                return "float";
            }
            if (JSONTypes.OBJECT.compareToIgnoreCase(trim) == 0) {
                return JSONTypes.OBJECT;
            }
            if (JSONTypes.ARRAY.compareToIgnoreCase(trim) == 0) {
                return JSONTypes.ARRAY;
            }
            if ("string".compareToIgnoreCase(trim) == 0) {
                return "string";
            }
            if (JSONTypes.FUNCTION.compareToIgnoreCase(trim) == 0) {
                return JSONTypes.FUNCTION;
            }
            return null;
        } else if (str == null) {
            return null;
        } else {
            log.info(new StringBuffer().append("Using default type ").append(str).toString());
            return str;
        }
    }

    private boolean hasNamespaces(Element element) {
        int i = 0;
        for (int i2 = 0; i2 < element.getNamespaceDeclarationCount(); i2++) {
            if (!StringUtils.isBlank(element.getNamespaceURI(element.getNamespacePrefix(i2)))) {
                i++;
            }
        }
        if (i > 0) {
            return true;
        }
        return false;
    }

    private boolean isArray(Element element, boolean z) {
        boolean z2 = true;
        String str = getClass(element);
        if (str == null || !str.equals(JSONTypes.ARRAY)) {
            if (element.getAttributeCount() == 0) {
                z2 = checkChildElements(element, z);
            } else if (element.getAttributeCount() == 1 && (element.getAttribute(addJsonPrefix("class")) != null || element.getAttribute(addJsonPrefix("type")) != null)) {
                z2 = checkChildElements(element, z);
            } else if (element.getAttributeCount() != 2 || element.getAttribute(addJsonPrefix("class")) == null || element.getAttribute(addJsonPrefix("type")) == null) {
                z2 = false;
            } else {
                z2 = checkChildElements(element, z);
            }
        }
        if (z2) {
            for (int i = 0; i < element.getNamespaceDeclarationCount(); i++) {
                if (!StringUtils.isBlank(element.getNamespaceURI(element.getNamespacePrefix(i)))) {
                    return false;
                }
            }
        }
        return z2;
    }

    private boolean isFunction(Element element) {
        int attributeCount = element.getAttributeCount();
        if (attributeCount > 0) {
            Attribute attribute = element.getAttribute(addJsonPrefix("type"));
            Attribute attribute2 = element.getAttribute(addJsonPrefix("params"));
            if (attributeCount == 1 && attribute2 != null) {
                return true;
            }
            if (attributeCount == 2 && attribute2 != null && attribute != null && (attribute.getValue().compareToIgnoreCase("string") == 0 || attribute.getValue().compareToIgnoreCase(JSONTypes.FUNCTION) == 0)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNullObject(Element element) {
        if (element.getChildCount() == 0) {
            if (element.getAttributeCount() == 0 || element.getAttribute(addJsonPrefix("null")) != null) {
                return true;
            }
            if (element.getAttributeCount() == 1 && (element.getAttribute(addJsonPrefix("class")) != null || element.getAttribute(addJsonPrefix("type")) != null)) {
                return true;
            }
            if (!(element.getAttributeCount() != 2 || element.getAttribute(addJsonPrefix("class")) == null || element.getAttribute(addJsonPrefix("type")) == null)) {
                return true;
            }
        }
        if (!this.skipWhitespace || element.getChildCount() != 1 || !(element.getChild(0) instanceof Text)) {
            return false;
        }
        return true;
    }

    private boolean isObject(Element element, boolean z) {
        int i;
        int i2;
        boolean z2 = false;
        if (!isArray(element, z) && !isFunction(element)) {
            if (hasNamespaces(element)) {
                return true;
            }
            int attributeCount = element.getAttributeCount();
            if (attributeCount > 0) {
                int i3 = element.getAttribute(addJsonPrefix("null")) == null ? 0 : 1;
                if (element.getAttribute(addJsonPrefix("class")) == null) {
                    i = 0;
                } else {
                    i = 1;
                }
                int i4 = i + i3;
                if (element.getAttribute(addJsonPrefix("type")) == null) {
                    i2 = 0;
                } else {
                    i2 = 1;
                }
                int i5 = i2 + i4;
                switch (attributeCount) {
                    case 1:
                        if (i5 == 0) {
                            return true;
                        }
                        break;
                    case 2:
                        if (i5 < 2) {
                            return true;
                        }
                        break;
                    case 3:
                        if (i5 < 3) {
                            return true;
                        }
                        break;
                    default:
                        return true;
                }
            }
            if (element.getChildCount() == 1 && (element.getChild(0) instanceof Text)) {
                return z;
            }
            z2 = true;
        }
        return z2;
    }

    private Element newElement(String str) {
        if (str.indexOf(58) != -1) {
            this.namespaceLenient = true;
        }
        return this.namespaceLenient ? new CustomElement(str) : new Element(str);
    }

    private JSON processArrayElement(Element element, String str) {
        JSONArray jSONArray = new JSONArray();
        int childCount = element.getChildCount();
        for (int i = 0; i < childCount; i++) {
            Text child = element.getChild(i);
            if (child instanceof Text) {
                Text text = child;
                if (StringUtils.isNotBlank(StringUtils.strip(text.getValue()))) {
                    jSONArray.element(text.getValue());
                }
            } else if (child instanceof Element) {
                setValue(jSONArray, (Element) child, str);
            }
        }
        return jSONArray;
    }

    private Object processElement(Element element, String str) {
        if (isNullObject(element)) {
            return JSONNull.getInstance();
        }
        if (isArray(element, false)) {
            return processArrayElement(element, str);
        }
        if (isObject(element, false)) {
            return processObjectElement(element, str);
        }
        return trimSpaceFromValue(element.getValue());
    }

    private Element processJSONArray(JSONArray jSONArray, Element element, String[] strArr) {
        int size = jSONArray.size();
        for (int i = 0; i < size; i++) {
            element.appendChild(processJSONValue(jSONArray.get(i), element, null, strArr));
        }
        return element;
    }

    private Element processJSONObject(JSONObject jSONObject, Element element, String[] strArr, boolean z) {
        Element processJSONValue;
        if (jSONObject.isNullObject()) {
            element.addAttribute(new Attribute(addJsonPrefix("null"), "true"));
        } else if (!jSONObject.isEmpty()) {
            if (z && !this.rootNamespace.isEmpty()) {
                setNamespaceLenient(true);
                for (Entry entry : this.rootNamespace.entrySet()) {
                    String str = (String) entry.getKey();
                    String str2 = (String) entry.getValue();
                    if (StringUtils.isBlank(str)) {
                        element.setNamespaceURI(str2);
                    } else {
                        element.addNamespaceDeclaration(str, str2);
                    }
                }
            }
            addNameSpaceToElement(element);
            Object[] array = jSONObject.names().toArray();
            Arrays.sort(array);
            for (int i = 0; i < array.length; i++) {
                String str3 = (String) array[i];
                Object obj = jSONObject.get(str3);
                if (str3.startsWith("@xmlns")) {
                    setNamespaceLenient(true);
                    int indexOf = str3.indexOf(58);
                    if (indexOf != -1) {
                        String substring = str3.substring(indexOf + 1);
                        if (StringUtils.isBlank(element.getNamespaceURI(substring))) {
                            element.addNamespaceDeclaration(substring, String.valueOf(obj));
                        }
                    } else if (StringUtils.isBlank(element.getNamespaceURI())) {
                        element.setNamespaceURI(String.valueOf(obj));
                    }
                } else if (str3.startsWith("@")) {
                    element.addAttribute(new Attribute(str3.substring(1), String.valueOf(obj)));
                } else if (str3.equals("#text")) {
                    if (obj instanceof JSONArray) {
                        element.appendChild(((JSONArray) obj).join("", true));
                    } else {
                        element.appendChild(String.valueOf(obj));
                    }
                } else if (!(obj instanceof JSONArray) || (!((JSONArray) obj).isExpandElements() && !ArrayUtils.contains(strArr, str3))) {
                    Element processJSONValue2 = processJSONValue(obj, element, newElement(str3), strArr);
                    addNameSpaceToElement(processJSONValue2);
                    element.appendChild(processJSONValue2);
                } else {
                    JSONArray jSONArray = (JSONArray) obj;
                    int size = jSONArray.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        Object obj2 = jSONArray.get(i2);
                        Element newElement = newElement(str3);
                        if (obj2 instanceof JSONObject) {
                            processJSONValue = processJSONValue((JSONObject) obj2, element, newElement, strArr);
                        } else if (obj2 instanceof JSONArray) {
                            processJSONValue = processJSONValue((JSONArray) obj2, element, newElement, strArr);
                        } else {
                            processJSONValue = processJSONValue(obj2, element, newElement, strArr);
                        }
                        addNameSpaceToElement(processJSONValue);
                        element.appendChild(processJSONValue);
                    }
                }
            }
        }
        return element;
    }

    private Element processJSONValue(Object obj, Element element, Element element2, String[] strArr) {
        Object obj2;
        if (element2 == null) {
            element2 = newElement(getElementName());
        }
        if (JSONUtils.isBoolean(obj)) {
            if (isTypeHintsEnabled()) {
                element2.addAttribute(new Attribute(addJsonPrefix("type"), "boolean"));
            }
            element2.appendChild(obj.toString());
            return element2;
        } else if (JSONUtils.isNumber(obj)) {
            if (isTypeHintsEnabled()) {
                element2.addAttribute(new Attribute(addJsonPrefix("type"), JSONTypes.NUMBER));
            }
            element2.appendChild(obj.toString());
            return element2;
        } else if (JSONUtils.isFunction(obj)) {
            if (obj instanceof String) {
                obj2 = JSONFunction.parse((String) obj);
            } else {
                obj2 = obj;
            }
            JSONFunction jSONFunction = (JSONFunction) obj2;
            if (isTypeHintsEnabled()) {
                element2.addAttribute(new Attribute(addJsonPrefix("type"), JSONTypes.FUNCTION));
            }
            String substring = ArrayUtils.toString(jSONFunction.getParams()).substring(1);
            element2.addAttribute(new Attribute(addJsonPrefix("params"), substring.substring(0, substring.length() - 1)));
            element2.appendChild(new Text(new StringBuffer().append("<![CDATA[").append(jSONFunction.getText()).append("]]>").toString()));
            return element2;
        } else if (JSONUtils.isString(obj)) {
            if (isTypeHintsEnabled()) {
                element2.addAttribute(new Attribute(addJsonPrefix("type"), "string"));
            }
            element2.appendChild(obj.toString());
            return element2;
        } else if (obj instanceof JSONArray) {
            if (isTypeHintsEnabled()) {
                element2.addAttribute(new Attribute(addJsonPrefix("class"), JSONTypes.ARRAY));
            }
            return processJSONArray((JSONArray) obj, element2, strArr);
        } else if (obj instanceof JSONObject) {
            if (isTypeHintsEnabled()) {
                element2.addAttribute(new Attribute(addJsonPrefix("class"), JSONTypes.OBJECT));
            }
            return processJSONObject((JSONObject) obj, element2, strArr, false);
        } else if (!JSONUtils.isNull(obj)) {
            return element2;
        } else {
            if (isTypeHintsEnabled()) {
                element2.addAttribute(new Attribute(addJsonPrefix("class"), JSONTypes.OBJECT));
            }
            element2.addAttribute(new Attribute(addJsonPrefix("null"), "true"));
            return element2;
        }
    }

    private JSON processObjectElement(Element element, String str) {
        if (isNullObject(element)) {
            return JSONNull.getInstance();
        }
        JSONObject jSONObject = new JSONObject();
        if (!this.skipNamespaces) {
            for (int i = 0; i < element.getNamespaceDeclarationCount(); i++) {
                String namespacePrefix = element.getNamespacePrefix(i);
                String namespaceURI = element.getNamespaceURI(namespacePrefix);
                if (!StringUtils.isBlank(namespaceURI)) {
                    if (!StringUtils.isBlank(namespacePrefix)) {
                        namespacePrefix = new StringBuffer().append(Config.TRACE_TODAY_VISIT_SPLIT).append(namespacePrefix).toString();
                    }
                    setOrAccumulate(jSONObject, new StringBuffer().append("@xmlns").append(namespacePrefix).toString(), trimSpaceFromValue(namespaceURI));
                }
            }
        }
        int attributeCount = element.getAttributeCount();
        for (int i2 = 0; i2 < attributeCount; i2++) {
            Attribute attribute = element.getAttribute(i2);
            String qualifiedName = attribute.getQualifiedName();
            if (!isTypeHintsEnabled() || !(addJsonPrefix("class").compareToIgnoreCase(qualifiedName) == 0 || addJsonPrefix("type").compareToIgnoreCase(qualifiedName) == 0)) {
                setOrAccumulate(jSONObject, new StringBuffer().append("@").append(removeNamespacePrefix(qualifiedName)).toString(), trimSpaceFromValue(attribute.getValue()));
            }
        }
        int childCount = element.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            Text child = element.getChild(i3);
            if (child instanceof Text) {
                Text text = child;
                if (StringUtils.isNotBlank(StringUtils.strip(text.getValue()))) {
                    setOrAccumulate(jSONObject, "#text", trimSpaceFromValue(text.getValue()));
                }
            } else if (child instanceof Element) {
                setValue(jSONObject, (Element) child, str);
            }
        }
        return jSONObject;
    }

    private String removeNamespacePrefix(String str) {
        if (!isRemoveNamespacePrefixFromElements()) {
            return str;
        }
        int indexOf = str.indexOf(58);
        if (indexOf != -1) {
            return str.substring(indexOf + 1);
        }
        return str;
    }

    private void setOrAccumulate(JSONObject jSONObject, String str, Object obj) {
        if (jSONObject.has(str)) {
            jSONObject.accumulate(str, obj);
            Object obj2 = jSONObject.get(str);
            if (obj2 instanceof JSONArray) {
                ((JSONArray) obj2).setExpandElements(true);
                return;
            }
            return;
        }
        jSONObject.element(str, obj);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setValue(net.sf.json.JSONArray r7, nu.xom.Element r8, java.lang.String r9) {
        /*
            r6 = this;
            r2 = 1
            r3 = 0
            r1 = 0
            java.lang.String r4 = r6.getClass(r8)
            java.lang.String r0 = r6.getType(r8)
            if (r0 != 0) goto L_0x000e
            r0 = r9
        L_0x000e:
            boolean r5 = r6.hasNamespaces(r8)
            if (r5 == 0) goto L_0x0024
            boolean r5 = r6.skipNamespaces
            if (r5 != 0) goto L_0x0024
            java.lang.Object r0 = r6.processElement(r8, r0)
            java.lang.Object r0 = r6.simplifyValue(r1, r0)
            r7.element(r0)
        L_0x0023:
            return
        L_0x0024:
            int r5 = r8.getAttributeCount()
            if (r5 <= 0) goto L_0x005d
            boolean r2 = r6.isFunction(r8)
            if (r2 == 0) goto L_0x0051
            java.lang.String r0 = "params"
            java.lang.String r0 = r6.addJsonPrefix(r0)
            nu.xom.Attribute r0 = r8.getAttribute(r0)
            java.lang.String r1 = r8.getValue()
            java.lang.String r0 = r0.getValue()
            java.lang.String r2 = ","
            java.lang.String[] r0 = org.apache.commons.lang.StringUtils.split(r0, r2)
            net.sf.json.JSONFunction r2 = new net.sf.json.JSONFunction
            r2.<init>(r0, r1)
            r7.element(r2)
            goto L_0x0023
        L_0x0051:
            java.lang.Object r0 = r6.processElement(r8, r0)
            java.lang.Object r0 = r6.simplifyValue(r1, r0)
            r7.element(r0)
            goto L_0x0023
        L_0x005d:
            if (r4 == 0) goto L_0x016e
            java.lang.String r5 = "array"
            int r5 = r4.compareToIgnoreCase(r5)
            if (r5 != 0) goto L_0x0084
            net.sf.json.JSON r4 = r6.processArrayElement(r8, r0)
            r7.element(r4)
        L_0x006e:
            if (r2 != 0) goto L_0x0023
            java.lang.String r2 = "boolean"
            int r2 = r0.compareToIgnoreCase(r2)
            if (r2 != 0) goto L_0x0098
            java.lang.String r0 = r8.getValue()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r7.element(r0)
            goto L_0x0023
        L_0x0084:
            java.lang.String r5 = "object"
            int r4 = r4.compareToIgnoreCase(r5)
            if (r4 != 0) goto L_0x016e
            net.sf.json.JSON r4 = r6.processObjectElement(r8, r0)
            java.lang.Object r4 = r6.simplifyValue(r1, r4)
            r7.element(r4)
            goto L_0x006e
        L_0x0098:
            java.lang.String r2 = "number"
            int r2 = r0.compareToIgnoreCase(r2)
            if (r2 != 0) goto L_0x00bb
            java.lang.String r0 = r8.getValue()     // Catch:{ NumberFormatException -> 0x00ad }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ NumberFormatException -> 0x00ad }
            r7.element(r0)     // Catch:{ NumberFormatException -> 0x00ad }
            goto L_0x0023
        L_0x00ad:
            r0 = move-exception
            java.lang.String r0 = r8.getValue()
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            r7.element(r0)
            goto L_0x0023
        L_0x00bb:
            java.lang.String r2 = "integer"
            int r2 = r0.compareToIgnoreCase(r2)
            if (r2 != 0) goto L_0x00d0
            java.lang.String r0 = r8.getValue()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r7.element(r0)
            goto L_0x0023
        L_0x00d0:
            java.lang.String r2 = "float"
            int r2 = r0.compareToIgnoreCase(r2)
            if (r2 != 0) goto L_0x00e5
            java.lang.String r0 = r8.getValue()
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            r7.element(r0)
            goto L_0x0023
        L_0x00e5:
            java.lang.String r2 = "function"
            int r2 = r0.compareToIgnoreCase(r2)
            if (r2 != 0) goto L_0x0111
            java.lang.String r2 = r8.getValue()
            java.lang.String r0 = "params"
            java.lang.String r0 = r6.addJsonPrefix(r0)
            nu.xom.Attribute r0 = r8.getAttribute(r0)
            if (r0 == 0) goto L_0x016c
            java.lang.String r0 = r0.getValue()
            java.lang.String r1 = ","
            java.lang.String[] r0 = org.apache.commons.lang.StringUtils.split(r0, r1)
        L_0x0107:
            net.sf.json.JSONFunction r1 = new net.sf.json.JSONFunction
            r1.<init>(r0, r2)
            r7.element(r1)
            goto L_0x0023
        L_0x0111:
            java.lang.String r2 = "string"
            int r0 = r0.compareToIgnoreCase(r2)
            if (r0 != 0) goto L_0x0023
            java.lang.String r0 = "params"
            java.lang.String r0 = r6.addJsonPrefix(r0)
            nu.xom.Attribute r0 = r8.getAttribute(r0)
            if (r0 == 0) goto L_0x013d
            java.lang.String r1 = r8.getValue()
            java.lang.String r0 = r0.getValue()
            java.lang.String r2 = ","
            java.lang.String[] r0 = org.apache.commons.lang.StringUtils.split(r0, r2)
            net.sf.json.JSONFunction r2 = new net.sf.json.JSONFunction
            r2.<init>(r0, r1)
            r7.element(r2)
            goto L_0x0023
        L_0x013d:
            boolean r0 = r6.isArray(r8, r3)
            if (r0 == 0) goto L_0x014c
            net.sf.json.JSON r0 = r6.processArrayElement(r8, r9)
            r7.element(r0)
            goto L_0x0023
        L_0x014c:
            boolean r0 = r6.isObject(r8, r3)
            if (r0 == 0) goto L_0x015f
            net.sf.json.JSON r0 = r6.processObjectElement(r8, r9)
            java.lang.Object r0 = r6.simplifyValue(r1, r0)
            r7.element(r0)
            goto L_0x0023
        L_0x015f:
            java.lang.String r0 = r8.getValue()
            java.lang.String r0 = r6.trimSpaceFromValue(r0)
            r7.element(r0)
            goto L_0x0023
        L_0x016c:
            r0 = r1
            goto L_0x0107
        L_0x016e:
            r2 = r3
            goto L_0x006e
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.xml.XMLSerializer.setValue(net.sf.json.JSONArray, nu.xom.Element, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setValue(net.sf.json.JSONObject r7, nu.xom.Element r8, java.lang.String r9) {
        /*
            r6 = this;
            r1 = 1
            r2 = 0
            java.lang.String r3 = r6.getClass(r8)
            java.lang.String r0 = r6.getType(r8)
            if (r0 != 0) goto L_0x000d
            r0 = r9
        L_0x000d:
            java.lang.String r4 = r8.getQualifiedName()
            java.lang.String r4 = r6.removeNamespacePrefix(r4)
            boolean r5 = r6.hasNamespaces(r8)
            if (r5 == 0) goto L_0x002b
            boolean r5 = r6.skipNamespaces
            if (r5 != 0) goto L_0x002b
            java.lang.Object r0 = r6.processElement(r8, r0)
            java.lang.Object r0 = r6.simplifyValue(r7, r0)
            r6.setOrAccumulate(r7, r4, r0)
        L_0x002a:
            return
        L_0x002b:
            int r5 = r8.getAttributeCount()
            if (r5 <= 0) goto L_0x0058
            boolean r5 = r6.isFunction(r8)
            if (r5 == 0) goto L_0x0058
            java.lang.String r0 = "params"
            java.lang.String r0 = r6.addJsonPrefix(r0)
            nu.xom.Attribute r0 = r8.getAttribute(r0)
            java.lang.String r1 = r8.getValue()
            java.lang.String r0 = r0.getValue()
            java.lang.String r2 = ","
            java.lang.String[] r0 = org.apache.commons.lang.StringUtils.split(r0, r2)
            net.sf.json.JSONFunction r2 = new net.sf.json.JSONFunction
            r2.<init>(r0, r1)
            r6.setOrAccumulate(r7, r4, r2)
            goto L_0x002a
        L_0x0058:
            if (r3 == 0) goto L_0x0167
            java.lang.String r5 = "array"
            int r5 = r3.compareToIgnoreCase(r5)
            if (r5 != 0) goto L_0x007f
            net.sf.json.JSON r3 = r6.processArrayElement(r8, r0)
            r6.setOrAccumulate(r7, r4, r3)
        L_0x0069:
            if (r1 != 0) goto L_0x002a
            java.lang.String r1 = "boolean"
            int r1 = r0.compareToIgnoreCase(r1)
            if (r1 != 0) goto L_0x0093
            java.lang.String r0 = r8.getValue()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r6.setOrAccumulate(r7, r4, r0)
            goto L_0x002a
        L_0x007f:
            java.lang.String r5 = "object"
            int r3 = r3.compareToIgnoreCase(r5)
            if (r3 != 0) goto L_0x0167
            net.sf.json.JSON r3 = r6.processObjectElement(r8, r0)
            java.lang.Object r3 = r6.simplifyValue(r7, r3)
            r6.setOrAccumulate(r7, r4, r3)
            goto L_0x0069
        L_0x0093:
            java.lang.String r1 = "number"
            int r1 = r0.compareToIgnoreCase(r1)
            if (r1 != 0) goto L_0x00b5
            java.lang.String r0 = r8.getValue()     // Catch:{ NumberFormatException -> 0x00a7 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ NumberFormatException -> 0x00a7 }
            r6.setOrAccumulate(r7, r4, r0)     // Catch:{ NumberFormatException -> 0x00a7 }
            goto L_0x002a
        L_0x00a7:
            r0 = move-exception
            java.lang.String r0 = r8.getValue()
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            r6.setOrAccumulate(r7, r4, r0)
            goto L_0x002a
        L_0x00b5:
            java.lang.String r1 = "integer"
            int r1 = r0.compareToIgnoreCase(r1)
            if (r1 != 0) goto L_0x00ca
            java.lang.String r0 = r8.getValue()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6.setOrAccumulate(r7, r4, r0)
            goto L_0x002a
        L_0x00ca:
            java.lang.String r1 = "float"
            int r1 = r0.compareToIgnoreCase(r1)
            if (r1 != 0) goto L_0x00df
            java.lang.String r0 = r8.getValue()
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            r6.setOrAccumulate(r7, r4, r0)
            goto L_0x002a
        L_0x00df:
            java.lang.String r1 = "function"
            int r1 = r0.compareToIgnoreCase(r1)
            if (r1 != 0) goto L_0x010c
            r0 = 0
            java.lang.String r1 = r8.getValue()
            java.lang.String r2 = "params"
            java.lang.String r2 = r6.addJsonPrefix(r2)
            nu.xom.Attribute r2 = r8.getAttribute(r2)
            if (r2 == 0) goto L_0x0102
            java.lang.String r0 = r2.getValue()
            java.lang.String r2 = ","
            java.lang.String[] r0 = org.apache.commons.lang.StringUtils.split(r0, r2)
        L_0x0102:
            net.sf.json.JSONFunction r2 = new net.sf.json.JSONFunction
            r2.<init>(r0, r1)
            r6.setOrAccumulate(r7, r4, r2)
            goto L_0x002a
        L_0x010c:
            java.lang.String r1 = "string"
            int r0 = r0.compareToIgnoreCase(r1)
            if (r0 != 0) goto L_0x002a
            java.lang.String r0 = "params"
            java.lang.String r0 = r6.addJsonPrefix(r0)
            nu.xom.Attribute r0 = r8.getAttribute(r0)
            if (r0 == 0) goto L_0x0138
            java.lang.String r1 = r8.getValue()
            java.lang.String r0 = r0.getValue()
            java.lang.String r2 = ","
            java.lang.String[] r0 = org.apache.commons.lang.StringUtils.split(r0, r2)
            net.sf.json.JSONFunction r2 = new net.sf.json.JSONFunction
            r2.<init>(r0, r1)
            r6.setOrAccumulate(r7, r4, r2)
            goto L_0x002a
        L_0x0138:
            boolean r0 = r6.isArray(r8, r2)
            if (r0 == 0) goto L_0x0147
            net.sf.json.JSON r0 = r6.processArrayElement(r8, r9)
            r6.setOrAccumulate(r7, r4, r0)
            goto L_0x002a
        L_0x0147:
            boolean r0 = r6.isObject(r8, r2)
            if (r0 == 0) goto L_0x015a
            net.sf.json.JSON r0 = r6.processObjectElement(r8, r9)
            java.lang.Object r0 = r6.simplifyValue(r7, r0)
            r6.setOrAccumulate(r7, r4, r0)
            goto L_0x002a
        L_0x015a:
            java.lang.String r0 = r8.getValue()
            java.lang.String r0 = r6.trimSpaceFromValue(r0)
            r6.setOrAccumulate(r7, r4, r0)
            goto L_0x002a
        L_0x0167:
            r1 = r2
            goto L_0x0069
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.xml.XMLSerializer.setValue(net.sf.json.JSONObject, nu.xom.Element, java.lang.String):void");
    }

    private Object simplifyValue(JSONObject jSONObject, Object obj) {
        if (!(obj instanceof JSONObject)) {
            return obj;
        }
        JSONObject jSONObject2 = (JSONObject) obj;
        if (jSONObject != null) {
            for (Entry entry : jSONObject.entrySet()) {
                String str = (String) entry.getKey();
                Object value = entry.getValue();
                if (str.startsWith("@xmlns") && value.equals(jSONObject2.opt(str))) {
                    jSONObject2.remove(str);
                }
            }
        }
        if (jSONObject2.size() != 1 || !jSONObject2.has("#text")) {
            return obj;
        }
        return jSONObject2.get("#text");
    }

    private String trimSpaceFromValue(String str) {
        if (isTrimSpaces()) {
            return str.trim();
        }
        return str;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 14
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String writeDocument(nu.xom.Document r3, java.lang.String r4) {
        /*
            r2 = this;
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            if (r4 != 0) goto L_0x0018
            net.sf.json.xml.XMLSerializer$XomSerializer r0 = new net.sf.json.xml.XMLSerializer$XomSerializer     // Catch:{ IOException -> 0x001e }
            r0.<init>(r2, r1)     // Catch:{ IOException -> 0x001e }
        L_0x000c:
            r0.write(r3)     // Catch:{ IOException -> 0x001e }
            java.lang.String r0 = r0.getEncoding()     // Catch:{ IOException -> 0x001e }
            java.lang.String r0 = r1.toString(r0)     // Catch:{ UnsupportedEncodingException -> 0x0025 }
            return r0
        L_0x0018:
            net.sf.json.xml.XMLSerializer$XomSerializer r0 = new net.sf.json.xml.XMLSerializer$XomSerializer     // Catch:{ IOException -> 0x001e }
            r0.<init>(r2, r1, r4)     // Catch:{ IOException -> 0x001e }
            goto L_0x000c
        L_0x001e:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        L_0x0025:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.xml.XMLSerializer.writeDocument(nu.xom.Document, java.lang.String):java.lang.String");
    }
}
