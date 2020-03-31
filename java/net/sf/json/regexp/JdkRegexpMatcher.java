package net.sf.json.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JdkRegexpMatcher implements RegexpMatcher {
    private final Pattern pattern;

    public JdkRegexpMatcher(String str) {
        this(str, false);
    }

    public JdkRegexpMatcher(String str, boolean z) {
        if (z) {
            this.pattern = Pattern.compile(str, 8);
        } else {
            this.pattern = Pattern.compile(str);
        }
    }

    public String getGroupIfMatches(String str, int i) {
        Matcher matcher = this.pattern.matcher(str);
        if (matcher.matches()) {
            return matcher.group(i);
        }
        return "";
    }

    public boolean matches(String str) {
        return this.pattern.matcher(str).matches();
    }
}
