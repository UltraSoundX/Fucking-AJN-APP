package cn.sharesdk.framework.utils;

import android.text.TextUtils;
import android.util.Xml;
import java.util.HashMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* compiled from: XmlParser */
public class k {

    /* compiled from: XmlParser */
    private static class a extends DefaultHandler {
        private HashMap<String, Object> a = new HashMap<>();
        private HashMap<String, Object> b;

        public HashMap<String, Object> a() {
            return this.a;
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (this.b != null) {
                HashMap<String, Object> hashMap = new HashMap<>();
                this.b.put(str2, hashMap);
                this.b = hashMap;
            } else {
                this.b = new HashMap<>();
                this.a.put(str2, this.b);
            }
            int length = attributes.getLength();
            for (int i = 0; i < length; i++) {
                this.b.put(attributes.getLocalName(i), attributes.getValue(i));
            }
        }

        public void endElement(String str, String str2, String str3) throws SAXException {
            this.b = null;
        }

        public void characters(char[] cArr, int i, int i2) {
            String trim = String.valueOf(cArr, i, i2).trim();
            if (!TextUtils.isEmpty(trim) && this.b != null) {
                this.b.put("value", trim);
            }
        }
    }

    public HashMap<String, Object> a(String str) throws Throwable {
        a aVar = new a();
        Xml.parse(str, aVar);
        return aVar.a();
    }
}
