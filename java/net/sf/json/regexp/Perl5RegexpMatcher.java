package net.sf.json.regexp;

import org.apache.commons.lang.exception.NestableRuntimeException;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

public class Perl5RegexpMatcher implements RegexpMatcher {
    private static final Perl5Compiler compiler = new Perl5Compiler();
    private Pattern pattern;

    public Perl5RegexpMatcher(String str) {
        this(str, false);
    }

    public Perl5RegexpMatcher(String str, boolean z) {
        if (z) {
            try {
                this.pattern = compiler.compile(str, 32776);
            } catch (MalformedPatternException e) {
                throw new NestableRuntimeException(e);
            }
        } else {
            this.pattern = compiler.compile(str, 32768);
        }
    }

    public String getGroupIfMatches(String str, int i) {
        Perl5Matcher perl5Matcher = new Perl5Matcher();
        if (perl5Matcher.matches(str, this.pattern)) {
            return perl5Matcher.getMatch().group(1);
        }
        return "";
    }

    public boolean matches(String str) {
        return new Perl5Matcher().matches(str, this.pattern);
    }
}
