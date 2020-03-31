package org.jsoup.select;

import com.baidu.mobstat.Config;
import com.tencent.mid.sotrage.StorageInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.parser.TokenQueue;
import org.jsoup.select.Evaluator.AllElements;
import org.jsoup.select.Evaluator.Attribute;
import org.jsoup.select.Evaluator.AttributeStarting;
import org.jsoup.select.Evaluator.AttributeWithValue;
import org.jsoup.select.Evaluator.AttributeWithValueContaining;
import org.jsoup.select.Evaluator.AttributeWithValueEnding;
import org.jsoup.select.Evaluator.AttributeWithValueMatching;
import org.jsoup.select.Evaluator.AttributeWithValueNot;
import org.jsoup.select.Evaluator.AttributeWithValueStarting;
import org.jsoup.select.Evaluator.Class;
import org.jsoup.select.Evaluator.ContainsOwnText;
import org.jsoup.select.Evaluator.ContainsText;
import org.jsoup.select.Evaluator.Id;
import org.jsoup.select.Evaluator.IndexEquals;
import org.jsoup.select.Evaluator.IndexGreaterThan;
import org.jsoup.select.Evaluator.IndexLessThan;
import org.jsoup.select.Evaluator.IsEmpty;
import org.jsoup.select.Evaluator.IsFirstChild;
import org.jsoup.select.Evaluator.IsFirstOfType;
import org.jsoup.select.Evaluator.IsLastChild;
import org.jsoup.select.Evaluator.IsLastOfType;
import org.jsoup.select.Evaluator.IsNthChild;
import org.jsoup.select.Evaluator.IsNthLastChild;
import org.jsoup.select.Evaluator.IsNthLastOfType;
import org.jsoup.select.Evaluator.IsNthOfType;
import org.jsoup.select.Evaluator.IsOnlyChild;
import org.jsoup.select.Evaluator.IsOnlyOfType;
import org.jsoup.select.Evaluator.IsRoot;
import org.jsoup.select.Evaluator.Matches;
import org.jsoup.select.Evaluator.MatchesOwn;
import org.jsoup.select.Evaluator.Tag;
import org.jsoup.select.Selector.SelectorParseException;

class QueryParser {
    private static final String[] AttributeEvals = {"=", "!=", "^=", "$=", "*=", "~="};
    private static final Pattern NTH_AB = Pattern.compile("((\\+|-)?(\\d+)?)n(\\s*(\\+|-)?\\s*\\d+)?", 2);
    private static final Pattern NTH_B = Pattern.compile("(\\+|-)?(\\d+)");
    private static final String[] combinators = {StorageInterface.KEY_SPLITER, ">", "+", "~", " "};
    private List<Evaluator> evals = new ArrayList();
    private String query;
    private TokenQueue tq;

    private QueryParser(String str) {
        this.query = str;
        this.tq = new TokenQueue(str);
    }

    public static Evaluator parse(String str) {
        return new QueryParser(str).parse();
    }

    /* access modifiers changed from: 0000 */
    public Evaluator parse() {
        this.tq.consumeWhitespace();
        if (this.tq.matchesAny(combinators)) {
            this.evals.add(new Root());
            combinator(this.tq.consume());
        } else {
            findElements();
        }
        while (!this.tq.isEmpty()) {
            boolean consumeWhitespace = this.tq.consumeWhitespace();
            if (this.tq.matchesAny(combinators)) {
                combinator(this.tq.consume());
            } else if (consumeWhitespace) {
                combinator(' ');
            } else {
                findElements();
            }
        }
        if (this.evals.size() == 1) {
            return (Evaluator) this.evals.get(0);
        }
        return new And((Collection<Evaluator>) this.evals);
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [org.jsoup.select.CombiningEvaluator$And] */
    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r0v5, types: [org.jsoup.select.Evaluator] */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r5v5, types: [org.jsoup.select.CombiningEvaluator$And] */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r5v6, types: [org.jsoup.select.CombiningEvaluator$And] */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r5v7, types: [org.jsoup.select.CombiningEvaluator$And] */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r3v7, types: [org.jsoup.select.Evaluator] */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: type inference failed for: r5v8, types: [org.jsoup.select.CombiningEvaluator$And] */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: type inference failed for: r0v15, types: [org.jsoup.select.Evaluator] */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v13, types: [org.jsoup.select.Evaluator] */
    /* JADX WARNING: type inference failed for: r9v0 */
    /* JADX WARNING: type inference failed for: r1v14 */
    /* JADX WARNING: type inference failed for: r0v16 */
    /* JADX WARNING: type inference failed for: r0v17 */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: type inference failed for: r0v18 */
    /* JADX WARNING: type inference failed for: r0v19 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 14 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void combinator(char r11) {
        /*
            r10 = this;
            r8 = 44
            r7 = 2
            r3 = 1
            r4 = 0
            org.jsoup.parser.TokenQueue r0 = r10.tq
            r0.consumeWhitespace()
            java.lang.String r0 = r10.consumeSubQuery()
            org.jsoup.select.Evaluator r6 = parse(r0)
            java.util.List<org.jsoup.select.Evaluator> r0 = r10.evals
            int r0 = r0.size()
            if (r0 != r3) goto L_0x005b
            java.util.List<org.jsoup.select.Evaluator> r0 = r10.evals
            java.lang.Object r0 = r0.get(r4)
            org.jsoup.select.Evaluator r0 = (org.jsoup.select.Evaluator) r0
            boolean r1 = r0 instanceof org.jsoup.select.CombiningEvaluator.Or
            if (r1 == 0) goto L_0x00df
            if (r11 == r8) goto L_0x00df
            r1 = r0
            org.jsoup.select.CombiningEvaluator$Or r1 = (org.jsoup.select.CombiningEvaluator.Or) r1
            org.jsoup.select.Evaluator r1 = r1.rightMostEvaluator()
            r2 = r3
            r9 = r1
            r1 = r0
            r0 = r9
        L_0x0033:
            java.util.List<org.jsoup.select.Evaluator> r5 = r10.evals
            r5.clear()
            r5 = 62
            if (r11 != r5) goto L_0x0065
            org.jsoup.select.CombiningEvaluator$And r5 = new org.jsoup.select.CombiningEvaluator$And
            org.jsoup.select.Evaluator[] r7 = new org.jsoup.select.Evaluator[r7]
            r7[r4] = r6
            org.jsoup.select.StructuralEvaluator$ImmediateParent r4 = new org.jsoup.select.StructuralEvaluator$ImmediateParent
            r4.<init>(r0)
            r7[r3] = r4
            r5.<init>(r7)
            r3 = r5
        L_0x004d:
            if (r2 == 0) goto L_0x00dc
            r0 = r1
            org.jsoup.select.CombiningEvaluator$Or r0 = (org.jsoup.select.CombiningEvaluator.Or) r0
            r0.replaceRightMostEvaluator(r3)
        L_0x0055:
            java.util.List<org.jsoup.select.Evaluator> r0 = r10.evals
            r0.add(r1)
            return
        L_0x005b:
            org.jsoup.select.CombiningEvaluator$And r0 = new org.jsoup.select.CombiningEvaluator$And
            java.util.List<org.jsoup.select.Evaluator> r1 = r10.evals
            r0.<init>(r1)
            r2 = r4
            r1 = r0
            goto L_0x0033
        L_0x0065:
            r5 = 32
            if (r11 != r5) goto L_0x007b
            org.jsoup.select.CombiningEvaluator$And r5 = new org.jsoup.select.CombiningEvaluator$And
            org.jsoup.select.Evaluator[] r7 = new org.jsoup.select.Evaluator[r7]
            r7[r4] = r6
            org.jsoup.select.StructuralEvaluator$Parent r4 = new org.jsoup.select.StructuralEvaluator$Parent
            r4.<init>(r0)
            r7[r3] = r4
            r5.<init>(r7)
            r3 = r5
            goto L_0x004d
        L_0x007b:
            r5 = 43
            if (r11 != r5) goto L_0x0091
            org.jsoup.select.CombiningEvaluator$And r5 = new org.jsoup.select.CombiningEvaluator$And
            org.jsoup.select.Evaluator[] r7 = new org.jsoup.select.Evaluator[r7]
            r7[r4] = r6
            org.jsoup.select.StructuralEvaluator$ImmediatePreviousSibling r4 = new org.jsoup.select.StructuralEvaluator$ImmediatePreviousSibling
            r4.<init>(r0)
            r7[r3] = r4
            r5.<init>(r7)
            r3 = r5
            goto L_0x004d
        L_0x0091:
            r5 = 126(0x7e, float:1.77E-43)
            if (r11 != r5) goto L_0x00a7
            org.jsoup.select.CombiningEvaluator$And r5 = new org.jsoup.select.CombiningEvaluator$And
            org.jsoup.select.Evaluator[] r7 = new org.jsoup.select.Evaluator[r7]
            r7[r4] = r6
            org.jsoup.select.StructuralEvaluator$PreviousSibling r4 = new org.jsoup.select.StructuralEvaluator$PreviousSibling
            r4.<init>(r0)
            r7[r3] = r4
            r5.<init>(r7)
            r3 = r5
            goto L_0x004d
        L_0x00a7:
            if (r11 != r8) goto L_0x00c1
            boolean r3 = r0 instanceof org.jsoup.select.CombiningEvaluator.Or
            if (r3 == 0) goto L_0x00b4
            org.jsoup.select.CombiningEvaluator$Or r0 = (org.jsoup.select.CombiningEvaluator.Or) r0
            r0.add(r6)
        L_0x00b2:
            r3 = r0
            goto L_0x004d
        L_0x00b4:
            org.jsoup.select.CombiningEvaluator$Or r3 = new org.jsoup.select.CombiningEvaluator$Or
            r3.<init>()
            r3.add(r0)
            r3.add(r6)
            r0 = r3
            goto L_0x00b2
        L_0x00c1:
            org.jsoup.select.Selector$SelectorParseException r0 = new org.jsoup.select.Selector$SelectorParseException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unknown combinator: "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r11)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r2 = new java.lang.Object[r4]
            r0.<init>(r1, r2)
            throw r0
        L_0x00dc:
            r1 = r3
            goto L_0x0055
        L_0x00df:
            r2 = r4
            r1 = r0
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.select.QueryParser.combinator(char):void");
    }

    private String consumeSubQuery() {
        StringBuilder sb = new StringBuilder();
        while (!this.tq.isEmpty()) {
            if (this.tq.matches("(")) {
                sb.append("(").append(this.tq.chompBalanced('(', ')')).append(")");
            } else if (this.tq.matches("[")) {
                sb.append("[").append(this.tq.chompBalanced('[', ']')).append("]");
            } else if (this.tq.matchesAny(combinators)) {
                break;
            } else {
                sb.append(this.tq.consume());
            }
        }
        return sb.toString();
    }

    private void findElements() {
        if (this.tq.matchChomp("#")) {
            byId();
        } else if (this.tq.matchChomp(".")) {
            byClass();
        } else if (this.tq.matchesWord()) {
            byTag();
        } else if (this.tq.matches("[")) {
            byAttribute();
        } else if (this.tq.matchChomp("*")) {
            allElements();
        } else if (this.tq.matchChomp(":lt(")) {
            indexLessThan();
        } else if (this.tq.matchChomp(":gt(")) {
            indexGreaterThan();
        } else if (this.tq.matchChomp(":eq(")) {
            indexEquals();
        } else if (this.tq.matches(":has(")) {
            has();
        } else if (this.tq.matches(":contains(")) {
            contains(false);
        } else if (this.tq.matches(":containsOwn(")) {
            contains(true);
        } else if (this.tq.matches(":matches(")) {
            matches(false);
        } else if (this.tq.matches(":matchesOwn(")) {
            matches(true);
        } else if (this.tq.matches(":not(")) {
            not();
        } else if (this.tq.matchChomp(":nth-child(")) {
            cssNthChild(false, false);
        } else if (this.tq.matchChomp(":nth-last-child(")) {
            cssNthChild(true, false);
        } else if (this.tq.matchChomp(":nth-of-type(")) {
            cssNthChild(false, true);
        } else if (this.tq.matchChomp(":nth-last-of-type(")) {
            cssNthChild(true, true);
        } else if (this.tq.matchChomp(":first-child")) {
            this.evals.add(new IsFirstChild());
        } else if (this.tq.matchChomp(":last-child")) {
            this.evals.add(new IsLastChild());
        } else if (this.tq.matchChomp(":first-of-type")) {
            this.evals.add(new IsFirstOfType());
        } else if (this.tq.matchChomp(":last-of-type")) {
            this.evals.add(new IsLastOfType());
        } else if (this.tq.matchChomp(":only-child")) {
            this.evals.add(new IsOnlyChild());
        } else if (this.tq.matchChomp(":only-of-type")) {
            this.evals.add(new IsOnlyOfType());
        } else if (this.tq.matchChomp(":empty")) {
            this.evals.add(new IsEmpty());
        } else if (this.tq.matchChomp(":root")) {
            this.evals.add(new IsRoot());
        } else {
            throw new SelectorParseException("Could not parse query '%s': unexpected token at '%s'", this.query, this.tq.remainder());
        }
    }

    private void byId() {
        String consumeCssIdentifier = this.tq.consumeCssIdentifier();
        Validate.notEmpty(consumeCssIdentifier);
        this.evals.add(new Id(consumeCssIdentifier));
    }

    private void byClass() {
        String consumeCssIdentifier = this.tq.consumeCssIdentifier();
        Validate.notEmpty(consumeCssIdentifier);
        this.evals.add(new Class(consumeCssIdentifier.trim().toLowerCase()));
    }

    private void byTag() {
        String consumeElementSelector = this.tq.consumeElementSelector();
        Validate.notEmpty(consumeElementSelector);
        if (consumeElementSelector.contains("|")) {
            consumeElementSelector = consumeElementSelector.replace("|", Config.TRACE_TODAY_VISIT_SPLIT);
        }
        this.evals.add(new Tag(consumeElementSelector.trim().toLowerCase()));
    }

    private void byAttribute() {
        TokenQueue tokenQueue = new TokenQueue(this.tq.chompBalanced('[', ']'));
        String consumeToAny = tokenQueue.consumeToAny(AttributeEvals);
        Validate.notEmpty(consumeToAny);
        tokenQueue.consumeWhitespace();
        if (tokenQueue.isEmpty()) {
            if (consumeToAny.startsWith("^")) {
                this.evals.add(new AttributeStarting(consumeToAny.substring(1)));
            } else {
                this.evals.add(new Attribute(consumeToAny));
            }
        } else if (tokenQueue.matchChomp("=")) {
            this.evals.add(new AttributeWithValue(consumeToAny, tokenQueue.remainder()));
        } else if (tokenQueue.matchChomp("!=")) {
            this.evals.add(new AttributeWithValueNot(consumeToAny, tokenQueue.remainder()));
        } else if (tokenQueue.matchChomp("^=")) {
            this.evals.add(new AttributeWithValueStarting(consumeToAny, tokenQueue.remainder()));
        } else if (tokenQueue.matchChomp("$=")) {
            this.evals.add(new AttributeWithValueEnding(consumeToAny, tokenQueue.remainder()));
        } else if (tokenQueue.matchChomp("*=")) {
            this.evals.add(new AttributeWithValueContaining(consumeToAny, tokenQueue.remainder()));
        } else if (tokenQueue.matchChomp("~=")) {
            this.evals.add(new AttributeWithValueMatching(consumeToAny, Pattern.compile(tokenQueue.remainder())));
        } else {
            throw new SelectorParseException("Could not parse attribute query '%s': unexpected token at '%s'", this.query, tokenQueue.remainder());
        }
    }

    private void allElements() {
        this.evals.add(new AllElements());
    }

    private void indexLessThan() {
        this.evals.add(new IndexLessThan(consumeIndex()));
    }

    private void indexGreaterThan() {
        this.evals.add(new IndexGreaterThan(consumeIndex()));
    }

    private void indexEquals() {
        this.evals.add(new IndexEquals(consumeIndex()));
    }

    private void cssNthChild(boolean z, boolean z2) {
        int i = 1;
        int i2 = 0;
        String lowerCase = this.tq.chompTo(")").trim().toLowerCase();
        Matcher matcher = NTH_AB.matcher(lowerCase);
        Matcher matcher2 = NTH_B.matcher(lowerCase);
        if ("odd".equals(lowerCase)) {
            i2 = 1;
            i = 2;
        } else if ("even".equals(lowerCase)) {
            i = 2;
        } else if (matcher.matches()) {
            if (matcher.group(3) != null) {
                i = Integer.parseInt(matcher.group(1).replaceFirst("^\\+", ""));
            }
            if (matcher.group(4) != null) {
                i2 = Integer.parseInt(matcher.group(4).replaceFirst("^\\+", ""));
            }
        } else if (matcher2.matches()) {
            i = 0;
            i2 = Integer.parseInt(matcher2.group().replaceFirst("^\\+", ""));
        } else {
            throw new SelectorParseException("Could not parse nth-index '%s': unexpected format", lowerCase);
        }
        if (z2) {
            if (z) {
                this.evals.add(new IsNthLastOfType(i, i2));
            } else {
                this.evals.add(new IsNthOfType(i, i2));
            }
        } else if (z) {
            this.evals.add(new IsNthLastChild(i, i2));
        } else {
            this.evals.add(new IsNthChild(i, i2));
        }
    }

    private int consumeIndex() {
        String trim = this.tq.chompTo(")").trim();
        Validate.isTrue(StringUtil.isNumeric(trim), "Index must be numeric");
        return Integer.parseInt(trim);
    }

    private void has() {
        this.tq.consume(":has");
        String chompBalanced = this.tq.chompBalanced('(', ')');
        Validate.notEmpty(chompBalanced, ":has(el) subselect must not be empty");
        this.evals.add(new Has(parse(chompBalanced)));
    }

    private void contains(boolean z) {
        this.tq.consume(z ? ":containsOwn" : ":contains");
        String unescape = TokenQueue.unescape(this.tq.chompBalanced('(', ')'));
        Validate.notEmpty(unescape, ":contains(text) query must not be empty");
        if (z) {
            this.evals.add(new ContainsOwnText(unescape));
        } else {
            this.evals.add(new ContainsText(unescape));
        }
    }

    private void matches(boolean z) {
        this.tq.consume(z ? ":matchesOwn" : ":matches");
        String chompBalanced = this.tq.chompBalanced('(', ')');
        Validate.notEmpty(chompBalanced, ":matches(regex) query must not be empty");
        if (z) {
            this.evals.add(new MatchesOwn(Pattern.compile(chompBalanced)));
        } else {
            this.evals.add(new Matches(Pattern.compile(chompBalanced)));
        }
    }

    private void not() {
        this.tq.consume(":not");
        String chompBalanced = this.tq.chompBalanced('(', ')');
        Validate.notEmpty(chompBalanced, ":not(selector) subselect must not be empty");
        this.evals.add(new Not(parse(chompBalanced)));
    }
}
