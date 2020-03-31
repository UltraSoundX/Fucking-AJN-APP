package org.jsoup.parser;

import java.util.Locale;
import org.jsoup.helper.Validate;

class CharacterReader {
    static final char EOF = '￿';
    private final char[] input;
    private final int length;
    private int mark = 0;
    private int pos = 0;

    CharacterReader(String str) {
        Validate.notNull(str);
        this.input = str.toCharArray();
        this.length = this.input.length;
    }

    /* access modifiers changed from: 0000 */
    public int pos() {
        return this.pos;
    }

    /* access modifiers changed from: 0000 */
    public boolean isEmpty() {
        return this.pos >= this.length;
    }

    /* access modifiers changed from: 0000 */
    public char current() {
        return this.pos >= this.length ? EOF : this.input[this.pos];
    }

    /* access modifiers changed from: 0000 */
    public char consume() {
        char c = this.pos >= this.length ? EOF : this.input[this.pos];
        this.pos++;
        return c;
    }

    /* access modifiers changed from: 0000 */
    public void unconsume() {
        this.pos--;
    }

    /* access modifiers changed from: 0000 */
    public void advance() {
        this.pos++;
    }

    /* access modifiers changed from: 0000 */
    public void mark() {
        this.mark = this.pos;
    }

    /* access modifiers changed from: 0000 */
    public void rewindToMark() {
        this.pos = this.mark;
    }

    /* access modifiers changed from: 0000 */
    public String consumeAsString() {
        char[] cArr = this.input;
        int i = this.pos;
        this.pos = i + 1;
        return new String(cArr, i, 1);
    }

    /* access modifiers changed from: 0000 */
    public int nextIndexOf(char c) {
        for (int i = this.pos; i < this.length; i++) {
            if (c == this.input[i]) {
                return i - this.pos;
            }
        }
        return -1;
    }

    /* access modifiers changed from: 0000 */
    public int nextIndexOf(CharSequence charSequence) {
        char charAt = charSequence.charAt(0);
        int i = this.pos;
        while (i < this.length) {
            if (charAt != this.input[i]) {
                do {
                    i++;
                    if (i >= this.length) {
                        break;
                    }
                } while (charAt != this.input[i]);
            }
            int i2 = i + 1;
            int length2 = (charSequence.length() + i2) - 1;
            if (i < this.length && length2 <= this.length) {
                int i3 = 1;
                while (i2 < length2 && charSequence.charAt(i3) == this.input[i2]) {
                    i2++;
                    i3++;
                }
                if (i2 == length2) {
                    return i - this.pos;
                }
            }
            i++;
        }
        return -1;
    }

    /* access modifiers changed from: 0000 */
    public String consumeTo(char c) {
        int nextIndexOf = nextIndexOf(c);
        if (nextIndexOf == -1) {
            return consumeToEnd();
        }
        String str = new String(this.input, this.pos, nextIndexOf);
        this.pos = nextIndexOf + this.pos;
        return str;
    }

    /* access modifiers changed from: 0000 */
    public String consumeTo(String str) {
        int nextIndexOf = nextIndexOf((CharSequence) str);
        if (nextIndexOf == -1) {
            return consumeToEnd();
        }
        String str2 = new String(this.input, this.pos, nextIndexOf);
        this.pos = nextIndexOf + this.pos;
        return str2;
    }

    /* access modifiers changed from: 0000 */
    public String consumeToAny(char... cArr) {
        int i = this.pos;
        loop0:
        while (this.pos < this.length) {
            for (char c : cArr) {
                if (this.input[this.pos] == c) {
                    break loop0;
                }
            }
            this.pos++;
        }
        if (this.pos > i) {
            return new String(this.input, i, this.pos - i);
        }
        return "";
    }

    /* access modifiers changed from: 0000 */
    public String consumeToEnd() {
        String str = new String(this.input, this.pos, this.length - this.pos);
        this.pos = this.length;
        return str;
    }

    /* access modifiers changed from: 0000 */
    public String consumeLetterSequence() {
        int i = this.pos;
        while (this.pos < this.length) {
            char c = this.input[this.pos];
            if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) {
                break;
            }
            this.pos++;
        }
        return new String(this.input, i, this.pos - i);
    }

    /* access modifiers changed from: 0000 */
    public String consumeLetterThenDigitSequence() {
        int i = this.pos;
        while (this.pos < this.length) {
            char c = this.input[this.pos];
            if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) {
                break;
            }
            this.pos++;
        }
        while (!isEmpty()) {
            char c2 = this.input[this.pos];
            if (c2 < '0' || c2 > '9') {
                break;
            }
            this.pos++;
        }
        return new String(this.input, i, this.pos - i);
    }

    /* access modifiers changed from: 0000 */
    public String consumeHexSequence() {
        int i = this.pos;
        while (this.pos < this.length) {
            char c = this.input[this.pos];
            if ((c < '0' || c > '9') && ((c < 'A' || c > 'F') && (c < 'a' || c > 'f'))) {
                break;
            }
            this.pos++;
        }
        return new String(this.input, i, this.pos - i);
    }

    /* access modifiers changed from: 0000 */
    public String consumeDigitSequence() {
        int i = this.pos;
        while (this.pos < this.length) {
            char c = this.input[this.pos];
            if (c < '0' || c > '9') {
                break;
            }
            this.pos++;
        }
        return new String(this.input, i, this.pos - i);
    }

    /* access modifiers changed from: 0000 */
    public boolean matches(char c) {
        return !isEmpty() && this.input[this.pos] == c;
    }

    /* access modifiers changed from: 0000 */
    public boolean matches(String str) {
        int length2 = str.length();
        if (length2 > this.length - this.pos) {
            return false;
        }
        for (int i = 0; i < length2; i++) {
            if (str.charAt(i) != this.input[this.pos + i]) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean matchesIgnoreCase(String str) {
        int length2 = str.length();
        if (length2 > this.length - this.pos) {
            return false;
        }
        for (int i = 0; i < length2; i++) {
            if (Character.toUpperCase(str.charAt(i)) != Character.toUpperCase(this.input[this.pos + i])) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean matchesAny(char... cArr) {
        if (isEmpty()) {
            return false;
        }
        char c = this.input[this.pos];
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean matchesLetter() {
        if (isEmpty()) {
            return false;
        }
        char c = this.input[this.pos];
        if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean matchesDigit() {
        if (isEmpty()) {
            return false;
        }
        char c = this.input[this.pos];
        if (c < '0' || c > '9') {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean matchConsume(String str) {
        if (!matches(str)) {
            return false;
        }
        this.pos += str.length();
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean matchConsumeIgnoreCase(String str) {
        if (!matchesIgnoreCase(str)) {
            return false;
        }
        this.pos += str.length();
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean containsIgnoreCase(String str) {
        return nextIndexOf((CharSequence) str.toLowerCase(Locale.ENGLISH)) > -1 || nextIndexOf((CharSequence) str.toUpperCase(Locale.ENGLISH)) > -1;
    }

    public String toString() {
        return new String(this.input, this.pos, this.length - this.pos);
    }
}
