package android.support.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.VectorDrawable;
import android.os.Build.VERSION;
import android.support.v4.content.res.ComplexColorCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.support.v4.graphics.PathParser.PathDataNode;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VectorDrawableCompat extends g {
    static final Mode a = Mode.SRC_IN;
    private g b;
    private PorterDuffColorFilter d;
    private ColorFilter e;
    private boolean f;
    private boolean g;
    private ConstantState h;
    private final float[] i;
    private final Matrix j;
    private final Rect k;

    private static class a extends e {
        public a() {
        }

        public a(a aVar) {
            super(aVar);
        }

        public void a(Resources resources, AttributeSet attributeSet, Theme theme, XmlPullParser xmlPullParser) {
            if (TypedArrayUtils.hasAttribute(xmlPullParser, "pathData")) {
                TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, a.d);
                a(obtainAttributes);
                obtainAttributes.recycle();
            }
        }

        private void a(TypedArray typedArray) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.n = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.m = PathParser.createNodesFromPathData(string2);
            }
        }

        public boolean a() {
            return true;
        }
    }

    private static class b extends e {
        ComplexColorCompat a;
        float b = 0.0f;
        ComplexColorCompat c;
        float d = 1.0f;
        int e = 0;
        float f = 1.0f;
        float g = 0.0f;
        float h = 1.0f;
        float i = 0.0f;
        Cap j = Cap.BUTT;
        Join k = Join.MITER;
        float l = 4.0f;
        private int[] p;

        public b() {
        }

        public b(b bVar) {
            super(bVar);
            this.p = bVar.p;
            this.a = bVar.a;
            this.b = bVar.b;
            this.d = bVar.d;
            this.c = bVar.c;
            this.e = bVar.e;
            this.f = bVar.f;
            this.g = bVar.g;
            this.h = bVar.h;
            this.i = bVar.i;
            this.j = bVar.j;
            this.k = bVar.k;
            this.l = bVar.l;
        }

        private Cap a(int i2, Cap cap) {
            switch (i2) {
                case 0:
                    return Cap.BUTT;
                case 1:
                    return Cap.ROUND;
                case 2:
                    return Cap.SQUARE;
                default:
                    return cap;
            }
        }

        private Join a(int i2, Join join) {
            switch (i2) {
                case 0:
                    return Join.MITER;
                case 1:
                    return Join.ROUND;
                case 2:
                    return Join.BEVEL;
                default:
                    return join;
            }
        }

        public void a(Resources resources, AttributeSet attributeSet, Theme theme, XmlPullParser xmlPullParser) {
            TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, a.c);
            a(obtainAttributes, xmlPullParser, theme);
            obtainAttributes.recycle();
        }

        private void a(TypedArray typedArray, XmlPullParser xmlPullParser, Theme theme) {
            this.p = null;
            if (TypedArrayUtils.hasAttribute(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.n = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.m = PathParser.createNodesFromPathData(string2);
                }
                this.c = TypedArrayUtils.getNamedComplexColor(typedArray, xmlPullParser, theme, "fillColor", 1, 0);
                this.f = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "fillAlpha", 12, this.f);
                this.j = a(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.j);
                this.k = a(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.k);
                this.l = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.l);
                this.a = TypedArrayUtils.getNamedComplexColor(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                this.d = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeAlpha", 11, this.d);
                this.b = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeWidth", 4, this.b);
                this.h = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathEnd", 6, this.h);
                this.i = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathOffset", 7, this.i);
                this.g = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathStart", 5, this.g);
                this.e = TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "fillType", 13, this.e);
            }
        }

        public boolean b() {
            return this.c.isStateful() || this.a.isStateful();
        }

        public boolean a(int[] iArr) {
            return this.c.onStateChanged(iArr) | this.a.onStateChanged(iArr);
        }

        /* access modifiers changed from: 0000 */
        public int getStrokeColor() {
            return this.a.getColor();
        }

        /* access modifiers changed from: 0000 */
        public void setStrokeColor(int i2) {
            this.a.setColor(i2);
        }

        /* access modifiers changed from: 0000 */
        public float getStrokeWidth() {
            return this.b;
        }

        /* access modifiers changed from: 0000 */
        public void setStrokeWidth(float f2) {
            this.b = f2;
        }

        /* access modifiers changed from: 0000 */
        public float getStrokeAlpha() {
            return this.d;
        }

        /* access modifiers changed from: 0000 */
        public void setStrokeAlpha(float f2) {
            this.d = f2;
        }

        /* access modifiers changed from: 0000 */
        public int getFillColor() {
            return this.c.getColor();
        }

        /* access modifiers changed from: 0000 */
        public void setFillColor(int i2) {
            this.c.setColor(i2);
        }

        /* access modifiers changed from: 0000 */
        public float getFillAlpha() {
            return this.f;
        }

        /* access modifiers changed from: 0000 */
        public void setFillAlpha(float f2) {
            this.f = f2;
        }

        /* access modifiers changed from: 0000 */
        public float getTrimPathStart() {
            return this.g;
        }

        /* access modifiers changed from: 0000 */
        public void setTrimPathStart(float f2) {
            this.g = f2;
        }

        /* access modifiers changed from: 0000 */
        public float getTrimPathEnd() {
            return this.h;
        }

        /* access modifiers changed from: 0000 */
        public void setTrimPathEnd(float f2) {
            this.h = f2;
        }

        /* access modifiers changed from: 0000 */
        public float getTrimPathOffset() {
            return this.i;
        }

        /* access modifiers changed from: 0000 */
        public void setTrimPathOffset(float f2) {
            this.i = f2;
        }
    }

    private static class c extends d {
        final Matrix a = new Matrix();
        final ArrayList<d> b = new ArrayList<>();
        float c = 0.0f;
        final Matrix d = new Matrix();
        int e;
        private float f = 0.0f;
        private float g = 0.0f;
        private float h = 1.0f;
        private float i = 1.0f;
        private float j = 0.0f;
        private float k = 0.0f;
        private int[] l;
        private String m = null;

        public c(c cVar, ArrayMap<String, Object> arrayMap) {
            e aVar;
            super();
            this.c = cVar.c;
            this.f = cVar.f;
            this.g = cVar.g;
            this.h = cVar.h;
            this.i = cVar.i;
            this.j = cVar.j;
            this.k = cVar.k;
            this.l = cVar.l;
            this.m = cVar.m;
            this.e = cVar.e;
            if (this.m != null) {
                arrayMap.put(this.m, this);
            }
            this.d.set(cVar.d);
            ArrayList<d> arrayList = cVar.b;
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 < arrayList.size()) {
                    Object obj = arrayList.get(i3);
                    if (obj instanceof c) {
                        this.b.add(new c((c) obj, arrayMap));
                    } else {
                        if (obj instanceof b) {
                            aVar = new b((b) obj);
                        } else if (obj instanceof a) {
                            aVar = new a((a) obj);
                        } else {
                            throw new IllegalStateException("Unknown object in the tree!");
                        }
                        this.b.add(aVar);
                        if (aVar.n != null) {
                            arrayMap.put(aVar.n, aVar);
                        }
                    }
                    i2 = i3 + 1;
                } else {
                    return;
                }
            }
        }

        public c() {
            super();
        }

        public String getGroupName() {
            return this.m;
        }

        public Matrix getLocalMatrix() {
            return this.d;
        }

        public void a(Resources resources, AttributeSet attributeSet, Theme theme, XmlPullParser xmlPullParser) {
            TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, a.b);
            a(obtainAttributes, xmlPullParser);
            obtainAttributes.recycle();
        }

        private void a(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.l = null;
            this.c = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "rotation", 5, this.c);
            this.f = typedArray.getFloat(1, this.f);
            this.g = typedArray.getFloat(2, this.g);
            this.h = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "scaleX", 3, this.h);
            this.i = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "scaleY", 4, this.i);
            this.j = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "translateX", 6, this.j);
            this.k = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "translateY", 7, this.k);
            String string = typedArray.getString(0);
            if (string != null) {
                this.m = string;
            }
            a();
        }

        private void a() {
            this.d.reset();
            this.d.postTranslate(-this.f, -this.g);
            this.d.postScale(this.h, this.i);
            this.d.postRotate(this.c, 0.0f, 0.0f);
            this.d.postTranslate(this.j + this.f, this.k + this.g);
        }

        public float getRotation() {
            return this.c;
        }

        public void setRotation(float f2) {
            if (f2 != this.c) {
                this.c = f2;
                a();
            }
        }

        public float getPivotX() {
            return this.f;
        }

        public void setPivotX(float f2) {
            if (f2 != this.f) {
                this.f = f2;
                a();
            }
        }

        public float getPivotY() {
            return this.g;
        }

        public void setPivotY(float f2) {
            if (f2 != this.g) {
                this.g = f2;
                a();
            }
        }

        public float getScaleX() {
            return this.h;
        }

        public void setScaleX(float f2) {
            if (f2 != this.h) {
                this.h = f2;
                a();
            }
        }

        public float getScaleY() {
            return this.i;
        }

        public void setScaleY(float f2) {
            if (f2 != this.i) {
                this.i = f2;
                a();
            }
        }

        public float getTranslateX() {
            return this.j;
        }

        public void setTranslateX(float f2) {
            if (f2 != this.j) {
                this.j = f2;
                a();
            }
        }

        public float getTranslateY() {
            return this.k;
        }

        public void setTranslateY(float f2) {
            if (f2 != this.k) {
                this.k = f2;
                a();
            }
        }

        public boolean b() {
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                if (((d) this.b.get(i2)).b()) {
                    return true;
                }
            }
            return false;
        }

        public boolean a(int[] iArr) {
            boolean z = false;
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                z |= ((d) this.b.get(i2)).a(iArr);
            }
            return z;
        }
    }

    private static abstract class d {
        private d() {
        }

        public boolean b() {
            return false;
        }

        public boolean a(int[] iArr) {
            return false;
        }
    }

    private static abstract class e extends d {
        protected PathDataNode[] m = null;
        String n;
        int o;

        public e() {
            super();
        }

        public e(e eVar) {
            super();
            this.n = eVar.n;
            this.o = eVar.o;
            this.m = PathParser.deepCopyNodes(eVar.m);
        }

        public void a(Path path) {
            path.reset();
            if (this.m != null) {
                PathDataNode.nodesToPath(this.m, path);
            }
        }

        public String getPathName() {
            return this.n;
        }

        public boolean a() {
            return false;
        }

        public PathDataNode[] getPathData() {
            return this.m;
        }

        public void setPathData(PathDataNode[] pathDataNodeArr) {
            if (!PathParser.canMorph(this.m, pathDataNodeArr)) {
                this.m = PathParser.deepCopyNodes(pathDataNodeArr);
            } else {
                PathParser.updateNodes(this.m, pathDataNodeArr);
            }
        }
    }

    private static class f {
        private static final Matrix n = new Matrix();
        Paint a;
        Paint b;
        final c c;
        float d;
        float e;
        float f;
        float g;
        int h;
        String i;
        Boolean j;
        final ArrayMap<String, Object> k;
        private final Path l;
        private final Path m;
        private final Matrix o;
        private PathMeasure p;

        /* renamed from: q reason: collision with root package name */
        private int f331q;

        public f() {
            this.o = new Matrix();
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 0.0f;
            this.g = 0.0f;
            this.h = 255;
            this.i = null;
            this.j = null;
            this.k = new ArrayMap<>();
            this.c = new c();
            this.l = new Path();
            this.m = new Path();
        }

        public void setRootAlpha(int i2) {
            this.h = i2;
        }

        public int getRootAlpha() {
            return this.h;
        }

        public void setAlpha(float f2) {
            setRootAlpha((int) (255.0f * f2));
        }

        public float getAlpha() {
            return ((float) getRootAlpha()) / 255.0f;
        }

        public f(f fVar) {
            this.o = new Matrix();
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 0.0f;
            this.g = 0.0f;
            this.h = 255;
            this.i = null;
            this.j = null;
            this.k = new ArrayMap<>();
            this.c = new c(fVar.c, this.k);
            this.l = new Path(fVar.l);
            this.m = new Path(fVar.m);
            this.d = fVar.d;
            this.e = fVar.e;
            this.f = fVar.f;
            this.g = fVar.g;
            this.f331q = fVar.f331q;
            this.h = fVar.h;
            this.i = fVar.i;
            if (fVar.i != null) {
                this.k.put(fVar.i, this);
            }
            this.j = fVar.j;
        }

        private void a(c cVar, Matrix matrix, Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            cVar.a.set(matrix);
            cVar.a.preConcat(cVar.d);
            canvas.save();
            int i4 = 0;
            while (true) {
                int i5 = i4;
                if (i5 < cVar.b.size()) {
                    d dVar = (d) cVar.b.get(i5);
                    if (dVar instanceof c) {
                        a((c) dVar, cVar.a, canvas, i2, i3, colorFilter);
                    } else if (dVar instanceof e) {
                        a(cVar, (e) dVar, canvas, i2, i3, colorFilter);
                    }
                    i4 = i5 + 1;
                } else {
                    canvas.restore();
                    return;
                }
            }
        }

        public void a(Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            a(this.c, n, canvas, i2, i3, colorFilter);
        }

        private void a(c cVar, e eVar, Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            float f2 = ((float) i2) / this.f;
            float f3 = ((float) i3) / this.g;
            float min = Math.min(f2, f3);
            Matrix matrix = cVar.a;
            this.o.set(matrix);
            this.o.postScale(f2, f3);
            float a2 = a(matrix);
            if (a2 != 0.0f) {
                eVar.a(this.l);
                Path path = this.l;
                this.m.reset();
                if (eVar.a()) {
                    this.m.addPath(path, this.o);
                    canvas.clipPath(this.m);
                    return;
                }
                b bVar = (b) eVar;
                if (!(bVar.g == 0.0f && bVar.h == 1.0f)) {
                    float f4 = (bVar.g + bVar.i) % 1.0f;
                    float f5 = (bVar.h + bVar.i) % 1.0f;
                    if (this.p == null) {
                        this.p = new PathMeasure();
                    }
                    this.p.setPath(this.l, false);
                    float length = this.p.getLength();
                    float f6 = f4 * length;
                    float f7 = f5 * length;
                    path.reset();
                    if (f6 > f7) {
                        this.p.getSegment(f6, length, path, true);
                        this.p.getSegment(0.0f, f7, path, true);
                    } else {
                        this.p.getSegment(f6, f7, path, true);
                    }
                    path.rLineTo(0.0f, 0.0f);
                }
                this.m.addPath(path, this.o);
                if (bVar.c.willDraw()) {
                    ComplexColorCompat complexColorCompat = bVar.c;
                    if (this.b == null) {
                        this.b = new Paint(1);
                        this.b.setStyle(Style.FILL);
                    }
                    Paint paint = this.b;
                    if (complexColorCompat.isGradient()) {
                        Shader shader = complexColorCompat.getShader();
                        shader.setLocalMatrix(this.o);
                        paint.setShader(shader);
                        paint.setAlpha(Math.round(bVar.f * 255.0f));
                    } else {
                        paint.setColor(VectorDrawableCompat.a(complexColorCompat.getColor(), bVar.f));
                    }
                    paint.setColorFilter(colorFilter);
                    this.m.setFillType(bVar.e == 0 ? FillType.WINDING : FillType.EVEN_ODD);
                    canvas.drawPath(this.m, paint);
                }
                if (bVar.a.willDraw()) {
                    ComplexColorCompat complexColorCompat2 = bVar.a;
                    if (this.a == null) {
                        this.a = new Paint(1);
                        this.a.setStyle(Style.STROKE);
                    }
                    Paint paint2 = this.a;
                    if (bVar.k != null) {
                        paint2.setStrokeJoin(bVar.k);
                    }
                    if (bVar.j != null) {
                        paint2.setStrokeCap(bVar.j);
                    }
                    paint2.setStrokeMiter(bVar.l);
                    if (complexColorCompat2.isGradient()) {
                        Shader shader2 = complexColorCompat2.getShader();
                        shader2.setLocalMatrix(this.o);
                        paint2.setShader(shader2);
                        paint2.setAlpha(Math.round(bVar.d * 255.0f));
                    } else {
                        paint2.setColor(VectorDrawableCompat.a(complexColorCompat2.getColor(), bVar.d));
                    }
                    paint2.setColorFilter(colorFilter);
                    paint2.setStrokeWidth(min * a2 * bVar.b);
                    canvas.drawPath(this.m, paint2);
                }
            }
        }

        private static float a(float f2, float f3, float f4, float f5) {
            return (f2 * f5) - (f3 * f4);
        }

        private float a(Matrix matrix) {
            float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
            matrix.mapVectors(fArr);
            float hypot = (float) Math.hypot((double) fArr[0], (double) fArr[1]);
            float hypot2 = (float) Math.hypot((double) fArr[2], (double) fArr[3]);
            float a2 = a(fArr[0], fArr[1], fArr[2], fArr[3]);
            float max = Math.max(hypot, hypot2);
            if (max > 0.0f) {
                return Math.abs(a2) / max;
            }
            return 0.0f;
        }

        public boolean a() {
            if (this.j == null) {
                this.j = Boolean.valueOf(this.c.b());
            }
            return this.j.booleanValue();
        }

        public boolean a(int[] iArr) {
            return this.c.a(iArr);
        }
    }

    private static class g extends ConstantState {
        int a;
        f b;
        ColorStateList c;
        Mode d;
        boolean e;
        Bitmap f;
        ColorStateList g;
        Mode h;
        int i;
        boolean j;
        boolean k;
        Paint l;

        public g(g gVar) {
            this.c = null;
            this.d = VectorDrawableCompat.a;
            if (gVar != null) {
                this.a = gVar.a;
                this.b = new f(gVar.b);
                if (gVar.b.b != null) {
                    this.b.b = new Paint(gVar.b.b);
                }
                if (gVar.b.a != null) {
                    this.b.a = new Paint(gVar.b.a);
                }
                this.c = gVar.c;
                this.d = gVar.d;
                this.e = gVar.e;
            }
        }

        public void a(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f, null, rect, a(colorFilter));
        }

        public boolean a() {
            return this.b.getRootAlpha() < 255;
        }

        public Paint a(ColorFilter colorFilter) {
            if (!a() && colorFilter == null) {
                return null;
            }
            if (this.l == null) {
                this.l = new Paint();
                this.l.setFilterBitmap(true);
            }
            this.l.setAlpha(this.b.getRootAlpha());
            this.l.setColorFilter(colorFilter);
            return this.l;
        }

        public void a(int i2, int i3) {
            this.f.eraseColor(0);
            this.b.a(new Canvas(this.f), i2, i3, (ColorFilter) null);
        }

        public void b(int i2, int i3) {
            if (this.f == null || !c(i2, i3)) {
                this.f = Bitmap.createBitmap(i2, i3, Config.ARGB_8888);
                this.k = true;
            }
        }

        public boolean c(int i2, int i3) {
            if (i2 == this.f.getWidth() && i3 == this.f.getHeight()) {
                return true;
            }
            return false;
        }

        public boolean b() {
            if (!this.k && this.g == this.c && this.h == this.d && this.j == this.e && this.i == this.b.getRootAlpha()) {
                return true;
            }
            return false;
        }

        public void c() {
            this.g = this.c;
            this.h = this.d;
            this.i = this.b.getRootAlpha();
            this.j = this.e;
            this.k = false;
        }

        public g() {
            this.c = null;
            this.d = VectorDrawableCompat.a;
            this.b = new f();
        }

        public Drawable newDrawable() {
            return new VectorDrawableCompat(this);
        }

        public Drawable newDrawable(Resources resources) {
            return new VectorDrawableCompat(this);
        }

        public int getChangingConfigurations() {
            return this.a;
        }

        public boolean d() {
            return this.b.a();
        }

        public boolean a(int[] iArr) {
            boolean a2 = this.b.a(iArr);
            this.k |= a2;
            return a2;
        }
    }

    private static class h extends ConstantState {
        private final ConstantState a;

        public h(ConstantState constantState) {
            this.a = constantState;
        }

        public Drawable newDrawable() {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.c = (VectorDrawable) this.a.newDrawable();
            return vectorDrawableCompat;
        }

        public Drawable newDrawable(Resources resources) {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.c = (VectorDrawable) this.a.newDrawable(resources);
            return vectorDrawableCompat;
        }

        public Drawable newDrawable(Resources resources, Theme theme) {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.c = (VectorDrawable) this.a.newDrawable(resources, theme);
            return vectorDrawableCompat;
        }

        public boolean canApplyTheme() {
            return this.a.canApplyTheme();
        }

        public int getChangingConfigurations() {
            return this.a.getChangingConfigurations();
        }
    }

    public /* bridge */ /* synthetic */ void applyTheme(Theme theme) {
        super.applyTheme(theme);
    }

    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i2) {
        super.setChangingConfigurations(i2);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(int i2, Mode mode) {
        super.setColorFilter(i2, mode);
    }

    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    public /* bridge */ /* synthetic */ void setHotspotBounds(int i2, int i3, int i4, int i5) {
        super.setHotspotBounds(i2, i3, i4, i5);
    }

    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    VectorDrawableCompat() {
        this.g = true;
        this.i = new float[9];
        this.j = new Matrix();
        this.k = new Rect();
        this.b = new g();
    }

    VectorDrawableCompat(g gVar) {
        this.g = true;
        this.i = new float[9];
        this.j = new Matrix();
        this.k = new Rect();
        this.b = gVar;
        this.d = a(this.d, gVar.c, gVar.d);
    }

    public Drawable mutate() {
        if (this.c != null) {
            this.c.mutate();
        } else if (!this.f && super.mutate() == this) {
            this.b = new g(this.b);
            this.f = true;
        }
        return this;
    }

    /* access modifiers changed from: 0000 */
    public Object a(String str) {
        return this.b.b.k.get(str);
    }

    public ConstantState getConstantState() {
        if (this.c != null && VERSION.SDK_INT >= 24) {
            return new h(this.c.getConstantState());
        }
        this.b.a = getChangingConfigurations();
        return this.b;
    }

    public void draw(Canvas canvas) {
        if (this.c != null) {
            this.c.draw(canvas);
            return;
        }
        copyBounds(this.k);
        if (this.k.width() > 0 && this.k.height() > 0) {
            ColorFilter colorFilter = this.e == null ? this.d : this.e;
            canvas.getMatrix(this.j);
            this.j.getValues(this.i);
            float abs = Math.abs(this.i[0]);
            float abs2 = Math.abs(this.i[4]);
            float abs3 = Math.abs(this.i[1]);
            float abs4 = Math.abs(this.i[3]);
            if (!(abs3 == 0.0f && abs4 == 0.0f)) {
                abs2 = 1.0f;
                abs = 1.0f;
            }
            int height = (int) (abs2 * ((float) this.k.height()));
            int min = Math.min(2048, (int) (abs * ((float) this.k.width())));
            int min2 = Math.min(2048, height);
            if (min > 0 && min2 > 0) {
                int save = canvas.save();
                canvas.translate((float) this.k.left, (float) this.k.top);
                if (a()) {
                    canvas.translate((float) this.k.width(), 0.0f);
                    canvas.scale(-1.0f, 1.0f);
                }
                this.k.offsetTo(0, 0);
                this.b.b(min, min2);
                if (!this.g) {
                    this.b.a(min, min2);
                } else if (!this.b.b()) {
                    this.b.a(min, min2);
                    this.b.c();
                }
                this.b.a(canvas, colorFilter, this.k);
                canvas.restoreToCount(save);
            }
        }
    }

    public int getAlpha() {
        if (this.c != null) {
            return DrawableCompat.getAlpha(this.c);
        }
        return this.b.b.getRootAlpha();
    }

    public void setAlpha(int i2) {
        if (this.c != null) {
            this.c.setAlpha(i2);
        } else if (this.b.b.getRootAlpha() != i2) {
            this.b.b.setRootAlpha(i2);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.c != null) {
            this.c.setColorFilter(colorFilter);
            return;
        }
        this.e = colorFilter;
        invalidateSelf();
    }

    /* access modifiers changed from: 0000 */
    public PorterDuffColorFilter a(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    public void setTint(int i2) {
        if (this.c != null) {
            DrawableCompat.setTint(this.c, i2);
        } else {
            setTintList(ColorStateList.valueOf(i2));
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        if (this.c != null) {
            DrawableCompat.setTintList(this.c, colorStateList);
            return;
        }
        g gVar = this.b;
        if (gVar.c != colorStateList) {
            gVar.c = colorStateList;
            this.d = a(this.d, colorStateList, gVar.d);
            invalidateSelf();
        }
    }

    public void setTintMode(Mode mode) {
        if (this.c != null) {
            DrawableCompat.setTintMode(this.c, mode);
            return;
        }
        g gVar = this.b;
        if (gVar.d != mode) {
            gVar.d = mode;
            this.d = a(this.d, gVar.c, mode);
            invalidateSelf();
        }
    }

    public boolean isStateful() {
        if (this.c != null) {
            return this.c.isStateful();
        }
        return super.isStateful() || (this.b != null && (this.b.d() || (this.b.c != null && this.b.c.isStateful())));
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        if (this.c != null) {
            return this.c.setState(iArr);
        }
        boolean z = false;
        g gVar = this.b;
        if (!(gVar.c == null || gVar.d == null)) {
            this.d = a(this.d, gVar.c, gVar.d);
            invalidateSelf();
            z = true;
        }
        if (!gVar.d() || !gVar.a(iArr)) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    public int getOpacity() {
        if (this.c != null) {
            return this.c.getOpacity();
        }
        return -3;
    }

    public int getIntrinsicWidth() {
        if (this.c != null) {
            return this.c.getIntrinsicWidth();
        }
        return (int) this.b.b.d;
    }

    public int getIntrinsicHeight() {
        if (this.c != null) {
            return this.c.getIntrinsicHeight();
        }
        return (int) this.b.b.e;
    }

    public boolean canApplyTheme() {
        if (this.c != null) {
            DrawableCompat.canApplyTheme(this.c);
        }
        return false;
    }

    public boolean isAutoMirrored() {
        if (this.c != null) {
            return DrawableCompat.isAutoMirrored(this.c);
        }
        return this.b.e;
    }

    public void setAutoMirrored(boolean z) {
        if (this.c != null) {
            DrawableCompat.setAutoMirrored(this.c, z);
        } else {
            this.b.e = z;
        }
    }

    public static VectorDrawableCompat a(Resources resources, int i2, Theme theme) {
        int next;
        if (VERSION.SDK_INT >= 24) {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.c = ResourcesCompat.getDrawable(resources, i2, theme);
            vectorDrawableCompat.h = new h(vectorDrawableCompat.c.getConstantState());
            return vectorDrawableCompat;
        }
        try {
            XmlResourceParser xml = resources.getXml(i2);
            AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
            do {
                next = xml.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next == 2) {
                return a(resources, xml, asAttributeSet, theme);
            }
            throw new XmlPullParserException("No start tag found");
        } catch (XmlPullParserException e2) {
            Log.e("VectorDrawableCompat", "parser error", e2);
        } catch (IOException e3) {
            Log.e("VectorDrawableCompat", "parser error", e3);
        }
        return null;
    }

    public static VectorDrawableCompat a(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) throws XmlPullParserException, IOException {
        VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
        vectorDrawableCompat.inflate(resources, xmlPullParser, attributeSet, theme);
        return vectorDrawableCompat;
    }

    static int a(int i2, float f2) {
        return (((int) (((float) Color.alpha(i2)) * f2)) << 24) | (16777215 & i2);
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        if (this.c != null) {
            this.c.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) throws XmlPullParserException, IOException {
        if (this.c != null) {
            DrawableCompat.inflate(this.c, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        g gVar = this.b;
        gVar.b = new f();
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, a.a);
        a(obtainAttributes, xmlPullParser);
        obtainAttributes.recycle();
        gVar.a = getChangingConfigurations();
        gVar.k = true;
        b(resources, xmlPullParser, attributeSet, theme);
        this.d = a(this.d, gVar.c, gVar.d);
    }

    private static Mode a(int i2, Mode mode) {
        switch (i2) {
            case 3:
                return Mode.SRC_OVER;
            case 5:
                return Mode.SRC_IN;
            case 9:
                return Mode.SRC_ATOP;
            case 14:
                return Mode.MULTIPLY;
            case 15:
                return Mode.SCREEN;
            case 16:
                return Mode.ADD;
            default:
                return mode;
        }
    }

    private void a(TypedArray typedArray, XmlPullParser xmlPullParser) throws XmlPullParserException {
        g gVar = this.b;
        f fVar = gVar.b;
        gVar.d = a(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "tintMode", 6, -1), Mode.SRC_IN);
        ColorStateList colorStateList = typedArray.getColorStateList(1);
        if (colorStateList != null) {
            gVar.c = colorStateList;
        }
        gVar.e = TypedArrayUtils.getNamedBoolean(typedArray, xmlPullParser, "autoMirrored", 5, gVar.e);
        fVar.f = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "viewportWidth", 7, fVar.f);
        fVar.g = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "viewportHeight", 8, fVar.g);
        if (fVar.f <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        } else if (fVar.g <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        } else {
            fVar.d = typedArray.getDimension(3, fVar.d);
            fVar.e = typedArray.getDimension(2, fVar.e);
            if (fVar.d <= 0.0f) {
                throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
            } else if (fVar.e <= 0.0f) {
                throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
            } else {
                fVar.setAlpha(TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "alpha", 4, fVar.getAlpha()));
                String string = typedArray.getString(0);
                if (string != null) {
                    fVar.i = string;
                    fVar.k.put(string, fVar);
                }
            }
        }
    }

    private void b(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) throws XmlPullParserException, IOException {
        boolean z;
        g gVar = this.b;
        f fVar = gVar.b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(fVar.c);
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        boolean z2 = true;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                c cVar = (c) arrayDeque.peek();
                if (com.baidu.mobstat.Config.FEED_LIST_ITEM_PATH.equals(name)) {
                    b bVar = new b();
                    bVar.a(resources, attributeSet, theme, xmlPullParser);
                    cVar.b.add(bVar);
                    if (bVar.getPathName() != null) {
                        fVar.k.put(bVar.getPathName(), bVar);
                    }
                    z = false;
                    gVar.a = bVar.o | gVar.a;
                } else if ("clip-path".equals(name)) {
                    a aVar = new a();
                    aVar.a(resources, attributeSet, theme, xmlPullParser);
                    cVar.b.add(aVar);
                    if (aVar.getPathName() != null) {
                        fVar.k.put(aVar.getPathName(), aVar);
                    }
                    gVar.a |= aVar.o;
                    z = z2;
                } else {
                    if ("group".equals(name)) {
                        c cVar2 = new c();
                        cVar2.a(resources, attributeSet, theme, xmlPullParser);
                        cVar.b.add(cVar2);
                        arrayDeque.push(cVar2);
                        if (cVar2.getGroupName() != null) {
                            fVar.k.put(cVar2.getGroupName(), cVar2);
                        }
                        gVar.a |= cVar2.e;
                    }
                    z = z2;
                }
                z2 = z;
            } else if (eventType == 3) {
                if ("group".equals(xmlPullParser.getName())) {
                    arrayDeque.pop();
                }
            }
            eventType = xmlPullParser.next();
        }
        if (z2) {
            throw new XmlPullParserException("no path defined");
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z) {
        this.g = z;
    }

    private boolean a() {
        if (VERSION.SDK_INT < 17) {
            return false;
        }
        if (!isAutoMirrored() || DrawableCompat.getLayoutDirection(this) != 1) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        if (this.c != null) {
            this.c.setBounds(rect);
        }
    }

    public int getChangingConfigurations() {
        if (this.c != null) {
            return this.c.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.b.getChangingConfigurations();
    }

    public void invalidateSelf() {
        if (this.c != null) {
            this.c.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    public void scheduleSelf(Runnable runnable, long j2) {
        if (this.c != null) {
            this.c.scheduleSelf(runnable, j2);
        } else {
            super.scheduleSelf(runnable, j2);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        if (this.c != null) {
            return this.c.setVisible(z, z2);
        }
        return super.setVisible(z, z2);
    }

    public void unscheduleSelf(Runnable runnable) {
        if (this.c != null) {
            this.c.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }
}
