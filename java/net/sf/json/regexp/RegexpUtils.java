package net.sf.json.regexp;

public class RegexpUtils {
    private static String javaVersion;

    static {
        javaVersion = "1.3.1";
        javaVersion = System.getProperty("java.version");
    }

    public static RegexpMatcher getMatcher(String str) {
        if (isJDK13()) {
            return new Perl5RegexpMatcher(str);
        }
        return new JdkRegexpMatcher(str);
    }

    public static RegexpMatcher getMatcher(String str, boolean z) {
        if (isJDK13()) {
            return new Perl5RegexpMatcher(str, true);
        }
        return new JdkRegexpMatcher(str, true);
    }

    public static boolean isJDK13() {
        return javaVersion.indexOf("1.3") != -1;
    }

    private RegexpUtils() {
    }
}
