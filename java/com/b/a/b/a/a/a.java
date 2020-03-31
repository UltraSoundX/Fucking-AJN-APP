package com.b.a.b.a.a;

import java.util.TimeZone;

/* compiled from: ISO8601Utils */
public class a {
    private static final TimeZone a = TimeZone.getTimeZone("UTC");

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0221  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Date a(java.lang.String r13, java.text.ParsePosition r14) throws java.text.ParseException {
        /*
            r12 = 43
            r11 = 5
            r10 = 45
            r0 = 0
            int r2 = r14.getIndex()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            int r1 = r2 + 4
            int r6 = a(r13, r2, r1)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r2 = 45
            boolean r2 = a(r13, r1, r2)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            if (r2 == 0) goto L_0x0255
            int r1 = r1 + 1
            r2 = r1
        L_0x001b:
            int r1 = r2 + 2
            int r7 = a(r13, r2, r1)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r2 = 45
            boolean r2 = a(r13, r1, r2)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            if (r2 == 0) goto L_0x0252
            int r1 = r1 + 1
            r2 = r1
        L_0x002c:
            int r1 = r2 + 2
            int r8 = a(r13, r2, r1)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r2 = 84
            boolean r2 = a(r13, r1, r2)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            if (r2 != 0) goto L_0x004f
            int r3 = r13.length()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            if (r3 > r1) goto L_0x004f
            java.util.GregorianCalendar r0 = new java.util.GregorianCalendar     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            int r2 = r7 + -1
            r0.<init>(r6, r2, r8)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r14.setIndex(r1)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.util.Date r0 = r0.getTime()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
        L_0x004e:
            return r0
        L_0x004f:
            if (r2 == 0) goto L_0x024c
            int r2 = r1 + 1
            int r1 = r2 + 2
            int r3 = a(r13, r2, r1)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r2 = 58
            boolean r2 = a(r13, r1, r2)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            if (r2 == 0) goto L_0x0249
            int r1 = r1 + 1
            r2 = r1
        L_0x0064:
            int r1 = r2 + 2
            int r2 = a(r13, r2, r1)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r4 = 58
            boolean r4 = a(r13, r1, r4)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            if (r4 == 0) goto L_0x0074
            int r1 = r1 + 1
        L_0x0074:
            int r4 = r13.length()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            if (r4 <= r1) goto L_0x0243
            char r4 = r13.charAt(r1)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r5 = 90
            if (r4 == r5) goto L_0x0243
            if (r4 == r12) goto L_0x0243
            if (r4 == r10) goto L_0x0243
            int r4 = r1 + 2
            int r1 = a(r13, r1, r4)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r5 = 59
            if (r1 <= r5) goto L_0x0096
            r5 = 63
            if (r1 >= r5) goto L_0x0096
            r1 = 59
        L_0x0096:
            r5 = 46
            boolean r5 = a(r13, r4, r5)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            if (r5 == 0) goto L_0x023c
            int r5 = r4 + 1
            int r0 = r5 + 1
            int r4 = a(r13, r0)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            int r0 = r5 + 3
            int r9 = java.lang.Math.min(r4, r0)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            int r0 = a(r13, r5, r9)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            int r5 = r9 - r5
            switch(r5) {
                case 1: goto L_0x0127;
                case 2: goto L_0x0124;
                default: goto L_0x00b5;
            }     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
        L_0x00b5:
            r5 = r3
            r3 = r1
            r1 = r4
            r4 = r2
            r2 = r0
        L_0x00ba:
            int r0 = r13.length()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            if (r0 > r1) goto L_0x012a
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.String r1 = "No time zone indicator"
            r0.<init>(r1)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
        L_0x00c8:
            r0 = move-exception
            r2 = r0
        L_0x00ca:
            if (r13 != 0) goto L_0x0221
            r0 = 0
        L_0x00cd:
            java.lang.String r1 = r2.getMessage()
            if (r1 == 0) goto L_0x00d9
            boolean r3 = r1.isEmpty()
            if (r3 == 0) goto L_0x00fa
        L_0x00d9:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "("
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.Class r3 = r2.getClass()
            java.lang.String r3 = r3.getName()
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r3 = ")"
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r1 = r1.toString()
        L_0x00fa:
            java.text.ParseException r3 = new java.text.ParseException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Failed to parse date ["
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r0 = r4.append(r0)
            java.lang.String r4 = "]: "
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            int r1 = r14.getIndex()
            r3.<init>(r0, r1)
            r3.initCause(r2)
            throw r3
        L_0x0124:
            int r0 = r0 * 10
            goto L_0x00b5
        L_0x0127:
            int r0 = r0 * 100
            goto L_0x00b5
        L_0x012a:
            char r0 = r13.charAt(r1)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r9 = 90
            if (r0 != r9) goto L_0x016a
            java.util.TimeZone r0 = a     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            int r1 = r1 + 1
        L_0x0136:
            java.util.GregorianCalendar r9 = new java.util.GregorianCalendar     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r9.<init>(r0)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r0 = 0
            r9.setLenient(r0)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r0 = 1
            r9.set(r0, r6)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r0 = 2
            int r6 = r7 + -1
            r9.set(r0, r6)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r0 = 5
            r9.set(r0, r8)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r0 = 11
            r9.set(r0, r5)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r0 = 12
            r9.set(r0, r4)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r0 = 13
            r9.set(r0, r3)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r0 = 14
            r9.set(r0, r2)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r14.setIndex(r1)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.util.Date r0 = r9.getTime()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            goto L_0x004e
        L_0x016a:
            if (r0 == r12) goto L_0x016e
            if (r0 != r10) goto L_0x01fe
        L_0x016e:
            java.lang.String r0 = r13.substring(r1)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            int r9 = r0.length()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            if (r9 < r11) goto L_0x0190
        L_0x0178:
            int r9 = r0.length()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            int r1 = r1 + r9
            java.lang.String r9 = "+0000"
            boolean r9 = r9.equals(r0)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            if (r9 != 0) goto L_0x018d
            java.lang.String r9 = "+00:00"
            boolean r9 = r9.equals(r0)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            if (r9 == 0) goto L_0x01a4
        L_0x018d:
            java.util.TimeZone r0 = a     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            goto L_0x0136
        L_0x0190:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r9.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.StringBuilder r0 = r9.append(r0)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.String r9 = "00"
            java.lang.StringBuilder r0 = r0.append(r9)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.String r0 = r0.toString()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            goto L_0x0178
        L_0x01a4:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r9.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.String r10 = "GMT"
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.StringBuilder r0 = r9.append(r0)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.String r9 = r0.toString()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.util.TimeZone r0 = java.util.TimeZone.getTimeZone(r9)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.String r10 = r0.getID()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            boolean r11 = r10.equals(r9)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            if (r11 != 0) goto L_0x0136
            java.lang.String r11 = ":"
            java.lang.String r12 = ""
            java.lang.String r10 = r10.replace(r11, r12)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            boolean r10 = r10.equals(r9)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            if (r10 != 0) goto L_0x0136
            java.lang.IndexOutOfBoundsException r1 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r2.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.String r3 = "Mismatching time zone indicator: "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.StringBuilder r2 = r2.append(r9)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.String r3 = " given, resolves to "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.String r0 = r0.getID()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.String r0 = r0.toString()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r1.<init>(r0)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            throw r1     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
        L_0x01fa:
            r0 = move-exception
            r2 = r0
            goto L_0x00ca
        L_0x01fe:
            java.lang.IndexOutOfBoundsException r1 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r2.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.String r3 = "Invalid time zone indicator '"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.String r2 = "'"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            java.lang.String r0 = r0.toString()     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            r1.<init>(r0)     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
            throw r1     // Catch:{ IndexOutOfBoundsException -> 0x00c8, NumberFormatException -> 0x01fa, IllegalArgumentException -> 0x021d }
        L_0x021d:
            r0 = move-exception
            r2 = r0
            goto L_0x00ca
        L_0x0221:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 34
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r13)
            java.lang.String r1 = "'"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            goto L_0x00cd
        L_0x023c:
            r5 = r3
            r3 = r1
            r1 = r4
            r4 = r2
            r2 = r0
            goto L_0x00ba
        L_0x0243:
            r4 = r2
            r5 = r3
            r2 = r0
            r3 = r0
            goto L_0x00ba
        L_0x0249:
            r2 = r1
            goto L_0x0064
        L_0x024c:
            r2 = r0
            r3 = r0
            r4 = r0
            r5 = r0
            goto L_0x00ba
        L_0x0252:
            r2 = r1
            goto L_0x002c
        L_0x0255:
            r2 = r1
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.b.a.a.a.a(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static boolean a(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int a(String str, int i, int i2) throws NumberFormatException {
        int i3;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        int i4 = 0;
        if (i < i2) {
            i3 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i4 = -digit;
        } else {
            i3 = i;
        }
        while (i3 < i2) {
            int i5 = i3 + 1;
            int digit2 = Character.digit(str.charAt(i3), 10);
            if (digit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i4 = (i4 * 10) - digit2;
            i3 = i5;
        }
        return -i4;
    }

    private static int a(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
