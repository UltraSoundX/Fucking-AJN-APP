package org.jsoup.nodes;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.MissingResourceException;
import java.util.Properties;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.parser.Parser;

public class Entities {
    private static final Map<String, Character> base = loadEntities("entities-base.properties");
    /* access modifiers changed from: private */
    public static final Map<Character, String> baseByVal = toCharacterKey(base);
    private static final Map<String, Character> full = loadEntities("entities-full.properties");
    /* access modifiers changed from: private */
    public static final Map<Character, String> fullByVal = toCharacterKey(full);
    private static final Object[][] xhtmlArray = {new Object[]{"quot", Integer.valueOf(34)}, new Object[]{"amp", Integer.valueOf(38)}, new Object[]{"lt", Integer.valueOf(60)}, new Object[]{"gt", Integer.valueOf(62)}};
    /* access modifiers changed from: private */
    public static final Map<Character, String> xhtmlByVal = new HashMap();

    public enum EscapeMode {
        xhtml(Entities.xhtmlByVal),
        base(Entities.baseByVal),
        extended(Entities.fullByVal);
        
        private Map<Character, String> map;

        private EscapeMode(Map<Character, String> map2) {
            this.map = map2;
        }

        public Map<Character, String> getMap() {
            return this.map;
        }
    }

    private Entities() {
    }

    public static boolean isNamedEntity(String str) {
        return full.containsKey(str);
    }

    public static boolean isBaseNamedEntity(String str) {
        return base.containsKey(str);
    }

    public static Character getCharacterByName(String str) {
        return (Character) full.get(str);
    }

    static String escape(String str, OutputSettings outputSettings) {
        StringBuilder sb = new StringBuilder(str.length() * 2);
        escape(sb, str, outputSettings, false, false, false);
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void escape(java.lang.StringBuilder r10, java.lang.String r11, org.jsoup.nodes.Document.OutputSettings r12, boolean r13, boolean r14, boolean r15) {
        /*
            r2 = 0
            r1 = 0
            org.jsoup.nodes.Entities$EscapeMode r4 = r12.escapeMode()
            java.nio.charset.CharsetEncoder r5 = r12.encoder()
            java.util.Map r6 = r4.getMap()
            int r7 = r11.length()
            r0 = 0
            r3 = r0
            r0 = r1
            r1 = r2
        L_0x0016:
            if (r3 >= r7) goto L_0x00eb
            int r8 = r11.codePointAt(r3)
            if (r14 == 0) goto L_0x00ec
            boolean r2 = org.jsoup.helper.StringUtil.isWhitespace(r8)
            if (r2 == 0) goto L_0x0038
            if (r15 == 0) goto L_0x0028
            if (r0 == 0) goto L_0x002a
        L_0x0028:
            if (r1 == 0) goto L_0x0031
        L_0x002a:
            int r2 = java.lang.Character.charCount(r8)
            int r2 = r2 + r3
            r3 = r2
            goto L_0x0016
        L_0x0031:
            r1 = 32
            r10.append(r1)
            r1 = 1
            goto L_0x002a
        L_0x0038:
            r2 = 0
            r1 = 1
        L_0x003a:
            r0 = 65536(0x10000, float:9.18355E-41)
            if (r8 >= r0) goto L_0x00be
            char r0 = (char) r8
            switch(r0) {
                case 34: goto L_0x007a;
                case 38: goto L_0x004e;
                case 60: goto L_0x0062;
                case 62: goto L_0x006e;
                case 160: goto L_0x0054;
                default: goto L_0x0042;
            }
        L_0x0042:
            boolean r9 = r5.canEncode(r0)
            if (r9 == 0) goto L_0x0086
            r10.append(r0)
        L_0x004b:
            r0 = r1
            r1 = r2
            goto L_0x002a
        L_0x004e:
            java.lang.String r0 = "&amp;"
            r10.append(r0)
            goto L_0x004b
        L_0x0054:
            org.jsoup.nodes.Entities$EscapeMode r9 = org.jsoup.nodes.Entities.EscapeMode.xhtml
            if (r4 == r9) goto L_0x005e
            java.lang.String r0 = "&nbsp;"
            r10.append(r0)
            goto L_0x004b
        L_0x005e:
            r10.append(r0)
            goto L_0x004b
        L_0x0062:
            if (r13 != 0) goto L_0x006a
            java.lang.String r0 = "&lt;"
            r10.append(r0)
            goto L_0x004b
        L_0x006a:
            r10.append(r0)
            goto L_0x004b
        L_0x006e:
            if (r13 != 0) goto L_0x0076
            java.lang.String r0 = "&gt;"
            r10.append(r0)
            goto L_0x004b
        L_0x0076:
            r10.append(r0)
            goto L_0x004b
        L_0x007a:
            if (r13 == 0) goto L_0x0082
            java.lang.String r0 = "&quot;"
            r10.append(r0)
            goto L_0x004b
        L_0x0082:
            r10.append(r0)
            goto L_0x004b
        L_0x0086:
            java.lang.Character r9 = java.lang.Character.valueOf(r0)
            boolean r9 = r6.containsKey(r9)
            if (r9 == 0) goto L_0x00aa
            r9 = 38
            java.lang.StringBuilder r9 = r10.append(r9)
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            java.lang.Object r0 = r6.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.StringBuilder r0 = r9.append(r0)
            r9 = 59
            r0.append(r9)
            goto L_0x004b
        L_0x00aa:
            java.lang.String r0 = "&#x"
            java.lang.StringBuilder r0 = r10.append(r0)
            java.lang.String r9 = java.lang.Integer.toHexString(r8)
            java.lang.StringBuilder r0 = r0.append(r9)
            r9 = 59
            r0.append(r9)
            goto L_0x004b
        L_0x00be:
            java.lang.String r0 = new java.lang.String
            char[] r9 = java.lang.Character.toChars(r8)
            r0.<init>(r9)
            boolean r9 = r5.canEncode(r0)
            if (r9 == 0) goto L_0x00d4
            r10.append(r0)
            r0 = r1
            r1 = r2
            goto L_0x002a
        L_0x00d4:
            java.lang.String r0 = "&#x"
            java.lang.StringBuilder r0 = r10.append(r0)
            java.lang.String r9 = java.lang.Integer.toHexString(r8)
            java.lang.StringBuilder r0 = r0.append(r9)
            r9 = 59
            r0.append(r9)
            r0 = r1
            r1 = r2
            goto L_0x002a
        L_0x00eb:
            return
        L_0x00ec:
            r2 = r1
            r1 = r0
            goto L_0x003a
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.nodes.Entities.escape(java.lang.StringBuilder, java.lang.String, org.jsoup.nodes.Document$OutputSettings, boolean, boolean, boolean):void");
    }

    static String unescape(String str) {
        return unescape(str, false);
    }

    static String unescape(String str, boolean z) {
        return Parser.unescapeEntities(str, z);
    }

    static {
        Object[][] objArr;
        for (Object[] objArr2 : xhtmlArray) {
            xhtmlByVal.put(Character.valueOf((char) ((Integer) objArr2[1]).intValue()), (String) objArr2[0]);
        }
    }

    private static Map<String, Character> loadEntities(String str) {
        Properties properties = new Properties();
        HashMap hashMap = new HashMap();
        try {
            InputStream resourceAsStream = Entities.class.getResourceAsStream(str);
            properties.load(resourceAsStream);
            resourceAsStream.close();
            for (Entry entry : properties.entrySet()) {
                hashMap.put((String) entry.getKey(), Character.valueOf((char) Integer.parseInt((String) entry.getValue(), 16)));
            }
            return hashMap;
        } catch (IOException e) {
            throw new MissingResourceException("Error loading entities resource: " + e.getMessage(), "Entities", str);
        }
    }

    private static Map<Character, String> toCharacterKey(Map<String, Character> map) {
        HashMap hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            Character ch = (Character) entry.getValue();
            String str = (String) entry.getKey();
            if (!hashMap.containsKey(ch)) {
                hashMap.put(ch, str);
            } else if (str.toLowerCase().equals(str)) {
                hashMap.put(ch, str);
            }
        }
        return hashMap;
    }
}
