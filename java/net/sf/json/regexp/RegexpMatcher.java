package net.sf.json.regexp;

public interface RegexpMatcher {
    String getGroupIfMatches(String str, int i);

    boolean matches(String str);
}
