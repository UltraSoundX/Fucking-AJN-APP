package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build.VERSION;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.support.v4.graphics.PathParser.PathDataNode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: AnimatorInflaterCompat */
public class d {

    /* compiled from: AnimatorInflaterCompat */
    private static class a implements TypeEvaluator<PathDataNode[]> {
        private PathDataNode[] a;

        a() {
        }

        /* renamed from: a */
        public PathDataNode[] evaluate(float f, PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
            if (!PathParser.canMorph(pathDataNodeArr, pathDataNodeArr2)) {
                throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
            }
            if (this.a == null || !PathParser.canMorph(this.a, pathDataNodeArr)) {
                this.a = PathParser.deepCopyNodes(pathDataNodeArr);
            }
            for (int i = 0; i < pathDataNodeArr.length; i++) {
                this.a[i].interpolatePathDataNode(pathDataNodeArr[i], pathDataNodeArr2[i], f);
            }
            return this.a;
        }
    }

    public static Animator a(Context context, int i) throws NotFoundException {
        if (VERSION.SDK_INT >= 24) {
            return AnimatorInflater.loadAnimator(context, i);
        }
        return a(context, context.getResources(), context.getTheme(), i);
    }

    public static Animator a(Context context, Resources resources, Theme theme, int i) throws NotFoundException {
        return a(context, resources, theme, i, 1.0f);
    }

    public static Animator a(Context context, Resources resources, Theme theme, int i, float f) throws NotFoundException {
        XmlResourceParser xmlResourceParser = null;
        try {
            xmlResourceParser = resources.getAnimation(i);
            Animator a2 = a(context, resources, theme, (XmlPullParser) xmlResourceParser, f);
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
            return a2;
        } catch (XmlPullParserException e) {
            NotFoundException notFoundException = new NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
            notFoundException.initCause(e);
            throw notFoundException;
        } catch (IOException e2) {
            NotFoundException notFoundException2 = new NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
            notFoundException2.initCause(e2);
            throw notFoundException2;
        } catch (Throwable th) {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
            throw th;
        }
    }

    private static PropertyValuesHolder a(TypedArray typedArray, int i, int i2, int i3, String str) {
        boolean z;
        PropertyValuesHolder propertyValuesHolder;
        int i4;
        int i5;
        int i6;
        float f;
        float f2;
        float f3;
        TypedValue peekValue = typedArray.peekValue(i2);
        boolean z2 = peekValue != null;
        int i7 = z2 ? peekValue.type : 0;
        TypedValue peekValue2 = typedArray.peekValue(i3);
        boolean z3 = peekValue2 != null;
        int i8 = z3 ? peekValue2.type : 0;
        if (i == 4) {
            if ((!z2 || !a(i7)) && (!z3 || !a(i8))) {
                i = 0;
            } else {
                i = 3;
            }
        }
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        if (i == 2) {
            String string = typedArray.getString(i2);
            String string2 = typedArray.getString(i3);
            PathDataNode[] createNodesFromPathData = PathParser.createNodesFromPathData(string);
            PathDataNode[] createNodesFromPathData2 = PathParser.createNodesFromPathData(string2);
            if (!(createNodesFromPathData == null && createNodesFromPathData2 == null)) {
                if (createNodesFromPathData != null) {
                    a aVar = new a();
                    if (createNodesFromPathData2 == null) {
                        return PropertyValuesHolder.ofObject(str, aVar, new Object[]{createNodesFromPathData});
                    } else if (!PathParser.canMorph(createNodesFromPathData, createNodesFromPathData2)) {
                        throw new InflateException(" Can't morph from " + string + " to " + string2);
                    } else {
                        return PropertyValuesHolder.ofObject(str, aVar, new Object[]{createNodesFromPathData, createNodesFromPathData2});
                    }
                } else if (createNodesFromPathData2 != null) {
                    return PropertyValuesHolder.ofObject(str, new a(), new Object[]{createNodesFromPathData2});
                }
            }
            return null;
        }
        e eVar = null;
        if (i == 3) {
            eVar = e.a();
        }
        if (z) {
            if (z2) {
                if (i7 == 5) {
                    f2 = typedArray.getDimension(i2, 0.0f);
                } else {
                    f2 = typedArray.getFloat(i2, 0.0f);
                }
                if (z3) {
                    if (i8 == 5) {
                        f3 = typedArray.getDimension(i3, 0.0f);
                    } else {
                        f3 = typedArray.getFloat(i3, 0.0f);
                    }
                    propertyValuesHolder = PropertyValuesHolder.ofFloat(str, new float[]{f2, f3});
                } else {
                    propertyValuesHolder = PropertyValuesHolder.ofFloat(str, new float[]{f2});
                }
            } else {
                if (i8 == 5) {
                    f = typedArray.getDimension(i3, 0.0f);
                } else {
                    f = typedArray.getFloat(i3, 0.0f);
                }
                propertyValuesHolder = PropertyValuesHolder.ofFloat(str, new float[]{f});
            }
        } else if (z2) {
            if (i7 == 5) {
                i5 = (int) typedArray.getDimension(i2, 0.0f);
            } else if (a(i7)) {
                i5 = typedArray.getColor(i2, 0);
            } else {
                i5 = typedArray.getInt(i2, 0);
            }
            if (z3) {
                if (i8 == 5) {
                    i6 = (int) typedArray.getDimension(i3, 0.0f);
                } else if (a(i8)) {
                    i6 = typedArray.getColor(i3, 0);
                } else {
                    i6 = typedArray.getInt(i3, 0);
                }
                propertyValuesHolder = PropertyValuesHolder.ofInt(str, new int[]{i5, i6});
            } else {
                propertyValuesHolder = PropertyValuesHolder.ofInt(str, new int[]{i5});
            }
        } else if (z3) {
            if (i8 == 5) {
                i4 = (int) typedArray.getDimension(i3, 0.0f);
            } else if (a(i8)) {
                i4 = typedArray.getColor(i3, 0);
            } else {
                i4 = typedArray.getInt(i3, 0);
            }
            propertyValuesHolder = PropertyValuesHolder.ofInt(str, new int[]{i4});
        } else {
            propertyValuesHolder = null;
        }
        if (propertyValuesHolder == null || eVar == null) {
            return propertyValuesHolder;
        }
        propertyValuesHolder.setEvaluator(eVar);
        return propertyValuesHolder;
    }

    private static void a(ValueAnimator valueAnimator, TypedArray typedArray, TypedArray typedArray2, float f, XmlPullParser xmlPullParser) {
        long namedInt = (long) TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "duration", 1, ErrorCode.ERROR_CODE_LOAD_BASE);
        long namedInt2 = (long) TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "startOffset", 2, 0);
        int namedInt3 = TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "valueType", 7, 4);
        if (TypedArrayUtils.hasAttribute(xmlPullParser, "valueFrom") && TypedArrayUtils.hasAttribute(xmlPullParser, "valueTo")) {
            if (namedInt3 == 4) {
                namedInt3 = a(typedArray, 5, 6);
            }
            PropertyValuesHolder a2 = a(typedArray, namedInt3, 5, 6, "");
            if (a2 != null) {
                valueAnimator.setValues(new PropertyValuesHolder[]{a2});
            }
        }
        valueAnimator.setDuration(namedInt);
        valueAnimator.setStartDelay(namedInt2);
        valueAnimator.setRepeatCount(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "repeatCount", 3, 0));
        valueAnimator.setRepeatMode(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "repeatMode", 4, 1));
        if (typedArray2 != null) {
            a(valueAnimator, typedArray2, namedInt3, f, xmlPullParser);
        }
    }

    private static void a(ValueAnimator valueAnimator, TypedArray typedArray, int i, float f, XmlPullParser xmlPullParser) {
        ObjectAnimator objectAnimator = (ObjectAnimator) valueAnimator;
        String namedString = TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "pathData", 1);
        if (namedString != null) {
            String namedString2 = TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "propertyXName", 2);
            String namedString3 = TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "propertyYName", 3);
            if (i == 2 || i == 4) {
            }
            if (namedString2 == null && namedString3 == null) {
                throw new InflateException(typedArray.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
            }
            a(PathParser.createPathFromPathData(namedString), objectAnimator, 0.5f * f, namedString2, namedString3);
            return;
        }
        objectAnimator.setPropertyName(TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "propertyName", 0));
    }

    private static void a(Path path, ObjectAnimator objectAnimator, float f, String str, String str2) {
        int i;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float f2 = 0.0f;
        ArrayList arrayList = new ArrayList();
        arrayList.add(Float.valueOf(0.0f));
        do {
            f2 += pathMeasure.getLength();
            arrayList.add(Float.valueOf(f2));
        } while (pathMeasure.nextContour());
        PathMeasure pathMeasure2 = new PathMeasure(path, false);
        int min = Math.min(100, ((int) (f2 / f)) + 1);
        float[] fArr = new float[min];
        float[] fArr2 = new float[min];
        float[] fArr3 = new float[2];
        float f3 = f2 / ((float) (min - 1));
        float f4 = 0.0f;
        int i2 = 0;
        int i3 = 0;
        while (i3 < min) {
            pathMeasure2.getPosTan(f4 - ((Float) arrayList.get(i2)).floatValue(), fArr3, null);
            fArr[i3] = fArr3[0];
            fArr2[i3] = fArr3[1];
            f4 += f3;
            if (i2 + 1 >= arrayList.size() || f4 <= ((Float) arrayList.get(i2 + 1)).floatValue()) {
                i = i2;
            } else {
                i = i2 + 1;
                pathMeasure2.nextContour();
            }
            i3++;
            i2 = i;
        }
        PropertyValuesHolder propertyValuesHolder = null;
        PropertyValuesHolder propertyValuesHolder2 = null;
        if (str != null) {
            propertyValuesHolder = PropertyValuesHolder.ofFloat(str, fArr);
        }
        if (str2 != null) {
            propertyValuesHolder2 = PropertyValuesHolder.ofFloat(str2, fArr2);
        }
        if (propertyValuesHolder == null) {
            objectAnimator.setValues(new PropertyValuesHolder[]{propertyValuesHolder2});
        } else if (propertyValuesHolder2 == null) {
            objectAnimator.setValues(new PropertyValuesHolder[]{propertyValuesHolder});
        } else {
            objectAnimator.setValues(new PropertyValuesHolder[]{propertyValuesHolder, propertyValuesHolder2});
        }
    }

    private static Animator a(Context context, Resources resources, Theme theme, XmlPullParser xmlPullParser, float f) throws XmlPullParserException, IOException {
        return a(context, resources, theme, xmlPullParser, Xml.asAttributeSet(xmlPullParser), null, 0, f);
    }

    private static Animator a(Context context, Resources resources, Theme theme, XmlPullParser xmlPullParser, AttributeSet attributeSet, AnimatorSet animatorSet, int i, float f) throws XmlPullParserException, IOException {
        boolean z;
        ArrayList arrayList;
        Animator animator = null;
        ArrayList arrayList2 = null;
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if ((next != 3 || xmlPullParser.getDepth() > depth) && next != 1) {
                if (next == 2) {
                    String name = xmlPullParser.getName();
                    if (name.equals("objectAnimator")) {
                        animator = a(context, resources, theme, attributeSet, f, xmlPullParser);
                        z = false;
                    } else if (name.equals("animator")) {
                        animator = a(context, resources, theme, attributeSet, null, f, xmlPullParser);
                        z = false;
                    } else if (name.equals("set")) {
                        Animator animatorSet2 = new AnimatorSet();
                        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, a.h);
                        Context context2 = context;
                        Resources resources2 = resources;
                        Theme theme2 = theme;
                        XmlPullParser xmlPullParser2 = xmlPullParser;
                        AttributeSet attributeSet2 = attributeSet;
                        a(context2, resources2, theme2, xmlPullParser2, attributeSet2, (AnimatorSet) animatorSet2, TypedArrayUtils.getNamedInt(obtainAttributes, xmlPullParser, "ordering", 0, 0), f);
                        obtainAttributes.recycle();
                        z = false;
                        animator = animatorSet2;
                    } else if (name.equals("propertyValuesHolder")) {
                        PropertyValuesHolder[] a2 = a(context, resources, theme, xmlPullParser, Xml.asAttributeSet(xmlPullParser));
                        if (!(a2 == null || animator == null || !(animator instanceof ValueAnimator))) {
                            ((ValueAnimator) animator).setValues(a2);
                        }
                        z = true;
                    } else {
                        throw new RuntimeException("Unknown animator name: " + xmlPullParser.getName());
                    }
                    if (animatorSet != null && !z) {
                        if (arrayList2 == null) {
                            arrayList = new ArrayList();
                        } else {
                            arrayList = arrayList2;
                        }
                        arrayList.add(animator);
                        arrayList2 = arrayList;
                    }
                }
            }
        }
        if (!(animatorSet == null || arrayList2 == null)) {
            Animator[] animatorArr = new Animator[arrayList2.size()];
            Iterator it = arrayList2.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                int i3 = i2 + 1;
                animatorArr[i2] = (Animator) it.next();
                i2 = i3;
            }
            if (i == 0) {
                animatorSet.playTogether(animatorArr);
            } else {
                animatorSet.playSequentially(animatorArr);
            }
        }
        return animator;
    }

    private static PropertyValuesHolder[] a(Context context, Resources resources, Theme theme, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        ArrayList arrayList;
        PropertyValuesHolder propertyValuesHolder;
        ArrayList arrayList2 = null;
        while (true) {
            int eventType = xmlPullParser.getEventType();
            if (eventType != 3 && eventType != 1) {
                if (eventType != 2) {
                    xmlPullParser.next();
                } else {
                    if (xmlPullParser.getName().equals("propertyValuesHolder")) {
                        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, a.i);
                        String namedString = TypedArrayUtils.getNamedString(obtainAttributes, xmlPullParser, "propertyName", 3);
                        int namedInt = TypedArrayUtils.getNamedInt(obtainAttributes, xmlPullParser, "valueType", 2, 4);
                        PropertyValuesHolder a2 = a(context, resources, theme, xmlPullParser, namedString, namedInt);
                        if (a2 == null) {
                            propertyValuesHolder = a(obtainAttributes, namedInt, 0, 1, namedString);
                        } else {
                            propertyValuesHolder = a2;
                        }
                        if (propertyValuesHolder != null) {
                            if (arrayList2 == null) {
                                arrayList = new ArrayList();
                            } else {
                                arrayList = arrayList2;
                            }
                            arrayList.add(propertyValuesHolder);
                        } else {
                            arrayList = arrayList2;
                        }
                        obtainAttributes.recycle();
                    } else {
                        arrayList = arrayList2;
                    }
                    xmlPullParser.next();
                    arrayList2 = arrayList;
                }
            }
        }
        if (arrayList2 == null) {
            return null;
        }
        int size = arrayList2.size();
        PropertyValuesHolder[] propertyValuesHolderArr = new PropertyValuesHolder[size];
        for (int i = 0; i < size; i++) {
            propertyValuesHolderArr[i] = (PropertyValuesHolder) arrayList2.get(i);
        }
        return propertyValuesHolderArr;
    }

    private static int a(Resources resources, Theme theme, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        int i = 0;
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, a.j);
        TypedValue peekNamedValue = TypedArrayUtils.peekNamedValue(obtainAttributes, xmlPullParser, "value", 0);
        if ((peekNamedValue != null) && a(peekNamedValue.type)) {
            i = 3;
        }
        obtainAttributes.recycle();
        return i;
    }

    private static int a(TypedArray typedArray, int i, int i2) {
        int i3;
        boolean z;
        int i4;
        TypedValue peekValue = typedArray.peekValue(i);
        boolean z2 = peekValue != null;
        if (z2) {
            i3 = peekValue.type;
        } else {
            i3 = 0;
        }
        TypedValue peekValue2 = typedArray.peekValue(i2);
        if (peekValue2 != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            i4 = peekValue2.type;
        } else {
            i4 = 0;
        }
        if ((!z2 || !a(i3)) && (!z || !a(i4))) {
            return 0;
        }
        return 3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.animation.PropertyValuesHolder a(android.content.Context r9, android.content.res.Resources r10, android.content.res.Resources.Theme r11, org.xmlpull.v1.XmlPullParser r12, java.lang.String r13, int r14) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r7 = 0
            r6 = 0
            r4 = r14
        L_0x0003:
            int r0 = r12.next()
            r1 = 3
            if (r0 == r1) goto L_0x0041
            r1 = 1
            if (r0 == r1) goto L_0x0041
            java.lang.String r0 = r12.getName()
            java.lang.String r1 = "keyframe"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 4
            if (r4 != r0) goto L_0x0024
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r12)
            int r4 = a(r10, r11, r0, r12)
        L_0x0024:
            android.util.AttributeSet r3 = android.util.Xml.asAttributeSet(r12)
            r0 = r9
            r1 = r10
            r2 = r11
            r5 = r12
            android.animation.Keyframe r1 = a(r0, r1, r2, r3, r4, r5)
            if (r1 == 0) goto L_0x0105
            if (r6 != 0) goto L_0x0102
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x0039:
            r0.add(r1)
        L_0x003c:
            r12.next()
        L_0x003f:
            r6 = r0
            goto L_0x0003
        L_0x0041:
            if (r6 == 0) goto L_0x0100
            int r2 = r6.size()
            if (r2 <= 0) goto L_0x0100
            r0 = 0
            java.lang.Object r0 = r6.get(r0)
            android.animation.Keyframe r0 = (android.animation.Keyframe) r0
            int r1 = r2 + -1
            java.lang.Object r1 = r6.get(r1)
            android.animation.Keyframe r1 = (android.animation.Keyframe) r1
            float r3 = r1.getFraction()
            r5 = 1065353216(0x3f800000, float:1.0)
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 >= 0) goto L_0x00fd
            r5 = 0
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 >= 0) goto L_0x009b
            r3 = 1065353216(0x3f800000, float:1.0)
            r1.setFraction(r3)
            r1 = r2
        L_0x006d:
            float r2 = r0.getFraction()
            r3 = 0
            int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x007f
            r3 = 0
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 >= 0) goto L_0x00ab
            r2 = 0
            r0.setFraction(r2)
        L_0x007f:
            android.animation.Keyframe[] r5 = new android.animation.Keyframe[r1]
            r6.toArray(r5)
            r3 = 0
        L_0x0085:
            if (r3 >= r1) goto L_0x00ee
            r0 = r5[r3]
            float r2 = r0.getFraction()
            r6 = 0
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 >= 0) goto L_0x0098
            if (r3 != 0) goto L_0x00b7
            r2 = 0
            r0.setFraction(r2)
        L_0x0098:
            int r3 = r3 + 1
            goto L_0x0085
        L_0x009b:
            int r3 = r6.size()
            r5 = 1065353216(0x3f800000, float:1.0)
            android.animation.Keyframe r1 = a(r1, r5)
            r6.add(r3, r1)
            int r1 = r2 + 1
            goto L_0x006d
        L_0x00ab:
            r2 = 0
            r3 = 0
            android.animation.Keyframe r0 = a(r0, r3)
            r6.add(r2, r0)
            int r1 = r1 + 1
            goto L_0x007f
        L_0x00b7:
            int r2 = r1 + -1
            if (r3 != r2) goto L_0x00c1
            r2 = 1065353216(0x3f800000, float:1.0)
            r0.setFraction(r2)
            goto L_0x0098
        L_0x00c1:
            int r0 = r3 + 1
            r2 = r3
        L_0x00c4:
            int r6 = r1 + -1
            if (r0 >= r6) goto L_0x00d3
            r6 = r5[r0]
            float r6 = r6.getFraction()
            r7 = 0
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 < 0) goto L_0x00e8
        L_0x00d3:
            int r0 = r2 + 1
            r0 = r5[r0]
            float r0 = r0.getFraction()
            int r6 = r3 + -1
            r6 = r5[r6]
            float r6 = r6.getFraction()
            float r0 = r0 - r6
            a(r5, r0, r3, r2)
            goto L_0x0098
        L_0x00e8:
            int r2 = r0 + 1
            r8 = r2
            r2 = r0
            r0 = r8
            goto L_0x00c4
        L_0x00ee:
            android.animation.PropertyValuesHolder r0 = android.animation.PropertyValuesHolder.ofKeyframe(r13, r5)
            r1 = 3
            if (r4 != r1) goto L_0x00fc
            android.support.graphics.drawable.e r1 = android.support.graphics.drawable.e.a()
            r0.setEvaluator(r1)
        L_0x00fc:
            return r0
        L_0x00fd:
            r1 = r2
            goto L_0x006d
        L_0x0100:
            r0 = r7
            goto L_0x00fc
        L_0x0102:
            r0 = r6
            goto L_0x0039
        L_0x0105:
            r0 = r6
            goto L_0x003c
        L_0x0108:
            r0 = r6
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.d.a(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser, java.lang.String, int):android.animation.PropertyValuesHolder");
    }

    private static Keyframe a(Keyframe keyframe, float f) {
        if (keyframe.getType() == Float.TYPE) {
            return Keyframe.ofFloat(f);
        }
        if (keyframe.getType() == Integer.TYPE) {
            return Keyframe.ofInt(f);
        }
        return Keyframe.ofObject(f);
    }

    private static void a(Keyframe[] keyframeArr, float f, int i, int i2) {
        float f2 = f / ((float) ((i2 - i) + 2));
        while (i <= i2) {
            keyframeArr[i].setFraction(keyframeArr[i - 1].getFraction() + f2);
            i++;
        }
    }

    private static Keyframe a(Context context, Resources resources, Theme theme, AttributeSet attributeSet, int i, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, a.j);
        Keyframe keyframe = null;
        float namedFloat = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "fraction", 3, -1.0f);
        TypedValue peekNamedValue = TypedArrayUtils.peekNamedValue(obtainAttributes, xmlPullParser, "value", 0);
        boolean z = peekNamedValue != null;
        if (i == 4) {
            if (!z || !a(peekNamedValue.type)) {
                i = 0;
            } else {
                i = 3;
            }
        }
        if (z) {
            switch (i) {
                case 0:
                    keyframe = Keyframe.ofFloat(namedFloat, TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "value", 0, 0.0f));
                    break;
                case 1:
                case 3:
                    keyframe = Keyframe.ofInt(namedFloat, TypedArrayUtils.getNamedInt(obtainAttributes, xmlPullParser, "value", 0, 0));
                    break;
            }
        } else if (i == 0) {
            keyframe = Keyframe.ofFloat(namedFloat);
        } else {
            keyframe = Keyframe.ofInt(namedFloat);
        }
        int namedResourceId = TypedArrayUtils.getNamedResourceId(obtainAttributes, xmlPullParser, "interpolator", 1, 0);
        if (namedResourceId > 0) {
            keyframe.setInterpolator(c.a(context, namedResourceId));
        }
        obtainAttributes.recycle();
        return keyframe;
    }

    private static ObjectAnimator a(Context context, Resources resources, Theme theme, AttributeSet attributeSet, float f, XmlPullParser xmlPullParser) throws NotFoundException {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        a(context, resources, theme, attributeSet, objectAnimator, f, xmlPullParser);
        return objectAnimator;
    }

    private static ValueAnimator a(Context context, Resources resources, Theme theme, AttributeSet attributeSet, ValueAnimator valueAnimator, float f, XmlPullParser xmlPullParser) throws NotFoundException {
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, a.g);
        TypedArray obtainAttributes2 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, a.k);
        if (valueAnimator == null) {
            valueAnimator = new ValueAnimator();
        }
        a(valueAnimator, obtainAttributes, obtainAttributes2, f, xmlPullParser);
        int namedResourceId = TypedArrayUtils.getNamedResourceId(obtainAttributes, xmlPullParser, "interpolator", 0, 0);
        if (namedResourceId > 0) {
            valueAnimator.setInterpolator(c.a(context, namedResourceId));
        }
        obtainAttributes.recycle();
        if (obtainAttributes2 != null) {
            obtainAttributes2.recycle();
        }
        return valueAnimator;
    }

    private static boolean a(int i) {
        return i >= 28 && i <= 31;
    }
}
