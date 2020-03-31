package android.support.v7.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.appcompat.R;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewConfiguration;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class SearchView extends LinearLayoutCompat implements android.support.v7.view.c {
    static final a i = new a();
    private c A;
    private b B;
    private d C;
    private OnClickListener D;
    private boolean E;
    private boolean F;
    private boolean G;
    private CharSequence H;
    private boolean I;
    private boolean J;
    private int K;
    private boolean L;
    private CharSequence M;
    private CharSequence N;
    private boolean O;
    private int P;
    private Bundle Q;
    private final Runnable R;
    private Runnable S;
    private final WeakHashMap<String, ConstantState> T;
    private final OnClickListener U;
    private final OnEditorActionListener V;
    private final OnItemClickListener W;
    final SearchAutoComplete a;
    private final OnItemSelectedListener aa;
    private TextWatcher ab;
    final ImageView b;
    final ImageView c;
    final ImageView d;
    final ImageView e;
    OnFocusChangeListener f;
    CursorAdapter g;
    SearchableInfo h;
    OnKeyListener j;
    private final View k;
    private final View l;
    private final View m;
    private final View n;
    private e o;
    private Rect p;

    /* renamed from: q reason: collision with root package name */
    private Rect f356q;
    private int[] r;
    private int[] s;
    private final ImageView t;
    private final Drawable u;
    private final int v;
    private final int w;
    private final Intent x;
    private final Intent y;
    private final CharSequence z;

    static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        boolean a;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = ((Boolean) parcel.readValue(null)).booleanValue();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.a));
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.a + "}";
        }
    }

    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
        final Runnable a;
        private int b;
        private SearchView c;
        private boolean d;

        public SearchAutoComplete(Context context) {
            this(context, null);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, R.attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.a = new Runnable() {
                public void run() {
                    SearchAutoComplete.this.b();
                }
            };
            this.b = getThreshold();
        }

        /* access modifiers changed from: protected */
        public void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, (float) getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        /* access modifiers changed from: 0000 */
        public void setSearchView(SearchView searchView) {
            this.c = searchView;
        }

        public void setThreshold(int i) {
            super.setThreshold(i);
            this.b = i;
        }

        /* access modifiers changed from: 0000 */
        public boolean a() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        /* access modifiers changed from: protected */
        public void replaceText(CharSequence charSequence) {
        }

        public void performCompletion() {
        }

        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.c.hasFocus() && getVisibility() == 0) {
                this.d = true;
                if (SearchView.a(getContext())) {
                    SearchView.i.a(this, true);
                }
            }
        }

        /* access modifiers changed from: protected */
        public void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            this.c.i();
        }

        public boolean enoughToFilter() {
            return this.b <= 0 || super.enoughToFilter();
        }

        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState == null) {
                        return true;
                    }
                    keyDispatcherState.startTracking(keyEvent, this);
                    return true;
                } else if (keyEvent.getAction() == 1) {
                    DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.c.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            if (i >= 960 && i2 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i >= 600 || (i >= 640 && i2 >= 480)) {
                return 192;
            }
            return ErrorCode.STARTDOWNLOAD_1;
        }

        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.d) {
                removeCallbacks(this.a);
                post(this.a);
            }
            return onCreateInputConnection;
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            if (this.d) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.d = false;
            }
        }

        /* access modifiers changed from: 0000 */
        public void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.d = false;
                removeCallbacks(this.a);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(this)) {
                this.d = false;
                removeCallbacks(this.a);
                inputMethodManager.showSoftInput(this, 0);
            } else {
                this.d = true;
            }
        }
    }

    private static class a {
        private Method a;
        private Method b;
        private Method c;

        a() {
            try {
                this.a = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.a.setAccessible(true);
            } catch (NoSuchMethodException e) {
            }
            try {
                this.b = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.b.setAccessible(true);
            } catch (NoSuchMethodException e2) {
            }
            try {
                this.c = AutoCompleteTextView.class.getMethod("ensureImeVisible", new Class[]{Boolean.TYPE});
                this.c.setAccessible(true);
            } catch (NoSuchMethodException e3) {
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(AutoCompleteTextView autoCompleteTextView) {
            if (this.a != null) {
                try {
                    this.a.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception e) {
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void b(AutoCompleteTextView autoCompleteTextView) {
            if (this.b != null) {
                try {
                    this.b.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception e) {
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(AutoCompleteTextView autoCompleteTextView, boolean z) {
            if (this.c != null) {
                try {
                    this.c.invoke(autoCompleteTextView, new Object[]{Boolean.valueOf(z)});
                } catch (Exception e) {
                }
            }
        }
    }

    public interface b {
        boolean a();
    }

    public interface c {
        boolean a(String str);

        boolean b(String str);
    }

    public interface d {
        boolean a(int i);

        boolean b(int i);
    }

    private static class e extends TouchDelegate {
        private final View a;
        private final Rect b = new Rect();
        private final Rect c = new Rect();
        private final Rect d = new Rect();
        private final int e;
        private boolean f;

        public e(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            a(rect, rect2);
            this.a = view;
        }

        public void a(Rect rect, Rect rect2) {
            this.b.set(rect);
            this.d.set(rect);
            this.d.inset(-this.e, -this.e);
            this.c.set(rect2);
        }

        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTouchEvent(android.view.MotionEvent r7) {
            /*
                r6 = this;
                r1 = 1
                r0 = 0
                float r2 = r7.getX()
                int r3 = (int) r2
                float r2 = r7.getY()
                int r4 = (int) r2
                int r2 = r7.getAction()
                switch(r2) {
                    case 0: goto L_0x003c;
                    case 1: goto L_0x0048;
                    case 2: goto L_0x0048;
                    case 3: goto L_0x0056;
                    default: goto L_0x0013;
                }
            L_0x0013:
                r2 = r0
            L_0x0014:
                if (r2 == 0) goto L_0x003b
                if (r1 == 0) goto L_0x005b
                android.graphics.Rect r0 = r6.c
                boolean r0 = r0.contains(r3, r4)
                if (r0 != 0) goto L_0x005b
                android.view.View r0 = r6.a
                int r0 = r0.getWidth()
                int r0 = r0 / 2
                float r0 = (float) r0
                android.view.View r1 = r6.a
                int r1 = r1.getHeight()
                int r1 = r1 / 2
                float r1 = (float) r1
                r7.setLocation(r0, r1)
            L_0x0035:
                android.view.View r0 = r6.a
                boolean r0 = r0.dispatchTouchEvent(r7)
            L_0x003b:
                return r0
            L_0x003c:
                android.graphics.Rect r2 = r6.b
                boolean r2 = r2.contains(r3, r4)
                if (r2 == 0) goto L_0x0013
                r6.f = r1
                r2 = r1
                goto L_0x0014
            L_0x0048:
                boolean r2 = r6.f
                if (r2 == 0) goto L_0x0014
                android.graphics.Rect r5 = r6.d
                boolean r5 = r5.contains(r3, r4)
                if (r5 != 0) goto L_0x0014
                r1 = r0
                goto L_0x0014
            L_0x0056:
                boolean r2 = r6.f
                r6.f = r0
                goto L_0x0014
            L_0x005b:
                android.graphics.Rect r0 = r6.c
                int r0 = r0.left
                int r0 = r3 - r0
                float r0 = (float) r0
                android.graphics.Rect r1 = r6.c
                int r1 = r1.top
                int r1 = r4 - r1
                float r1 = (float) r1
                r7.setLocation(r0, r1)
                goto L_0x0035
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.SearchView.e.onTouchEvent(android.view.MotionEvent):boolean");
        }
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.p = new Rect();
        this.f356q = new Rect();
        this.r = new int[2];
        this.s = new int[2];
        this.R = new Runnable() {
            public void run() {
                SearchView.this.d();
            }
        };
        this.S = new Runnable() {
            public void run() {
                if (SearchView.this.g != null && (SearchView.this.g instanceof an)) {
                    SearchView.this.g.changeCursor(null);
                }
            }
        };
        this.T = new WeakHashMap<>();
        this.U = new OnClickListener() {
            public void onClick(View view) {
                if (view == SearchView.this.b) {
                    SearchView.this.g();
                } else if (view == SearchView.this.d) {
                    SearchView.this.f();
                } else if (view == SearchView.this.c) {
                    SearchView.this.e();
                } else if (view == SearchView.this.e) {
                    SearchView.this.h();
                } else if (view == SearchView.this.a) {
                    SearchView.this.l();
                }
            }
        };
        this.j = new OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (SearchView.this.h == null) {
                    return false;
                }
                if (SearchView.this.a.isPopupShowing() && SearchView.this.a.getListSelection() != -1) {
                    return SearchView.this.a(view, i, keyEvent);
                }
                if (SearchView.this.a.a() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i != 66) {
                    return false;
                }
                view.cancelLongPress();
                SearchView.this.a(0, (String) null, SearchView.this.a.getText().toString());
                return true;
            }
        };
        this.V = new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                SearchView.this.e();
                return true;
            }
        };
        this.W = new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                SearchView.this.a(i, 0, (String) null);
            }
        };
        this.aa = new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                SearchView.this.a(i);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        this.ab = new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SearchView.this.b(charSequence);
            }

            public void afterTextChanged(Editable editable) {
            }
        };
        at a2 = at.a(context, attributeSet, R.styleable.SearchView, i2, 0);
        LayoutInflater.from(context).inflate(a2.g(R.styleable.SearchView_layout, R.layout.abc_search_view), this, true);
        this.a = (SearchAutoComplete) findViewById(R.id.search_src_text);
        this.a.setSearchView(this);
        this.k = findViewById(R.id.search_edit_frame);
        this.l = findViewById(R.id.search_plate);
        this.m = findViewById(R.id.submit_area);
        this.b = (ImageView) findViewById(R.id.search_button);
        this.c = (ImageView) findViewById(R.id.search_go_btn);
        this.d = (ImageView) findViewById(R.id.search_close_btn);
        this.e = (ImageView) findViewById(R.id.search_voice_btn);
        this.t = (ImageView) findViewById(R.id.search_mag_icon);
        ViewCompat.setBackground(this.l, a2.a(R.styleable.SearchView_queryBackground));
        ViewCompat.setBackground(this.m, a2.a(R.styleable.SearchView_submitBackground));
        this.b.setImageDrawable(a2.a(R.styleable.SearchView_searchIcon));
        this.c.setImageDrawable(a2.a(R.styleable.SearchView_goIcon));
        this.d.setImageDrawable(a2.a(R.styleable.SearchView_closeIcon));
        this.e.setImageDrawable(a2.a(R.styleable.SearchView_voiceIcon));
        this.t.setImageDrawable(a2.a(R.styleable.SearchView_searchIcon));
        this.u = a2.a(R.styleable.SearchView_searchHintIcon);
        av.a(this.b, getResources().getString(R.string.abc_searchview_description_search));
        this.v = a2.g(R.styleable.SearchView_suggestionRowLayout, R.layout.abc_search_dropdown_item_icons_2line);
        this.w = a2.g(R.styleable.SearchView_commitIcon, 0);
        this.b.setOnClickListener(this.U);
        this.d.setOnClickListener(this.U);
        this.c.setOnClickListener(this.U);
        this.e.setOnClickListener(this.U);
        this.a.setOnClickListener(this.U);
        this.a.addTextChangedListener(this.ab);
        this.a.setOnEditorActionListener(this.V);
        this.a.setOnItemClickListener(this.W);
        this.a.setOnItemSelectedListener(this.aa);
        this.a.setOnKeyListener(this.j);
        this.a.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (SearchView.this.f != null) {
                    SearchView.this.f.onFocusChange(SearchView.this, z);
                }
            }
        });
        setIconifiedByDefault(a2.a(R.styleable.SearchView_iconifiedByDefault, true));
        int e2 = a2.e(R.styleable.SearchView_android_maxWidth, -1);
        if (e2 != -1) {
            setMaxWidth(e2);
        }
        this.z = a2.c(R.styleable.SearchView_defaultQueryHint);
        this.H = a2.c(R.styleable.SearchView_queryHint);
        int a3 = a2.a(R.styleable.SearchView_android_imeOptions, -1);
        if (a3 != -1) {
            setImeOptions(a3);
        }
        int a4 = a2.a(R.styleable.SearchView_android_inputType, -1);
        if (a4 != -1) {
            setInputType(a4);
        }
        setFocusable(a2.a(R.styleable.SearchView_android_focusable, true));
        a2.a();
        this.x = new Intent("android.speech.action.WEB_SEARCH");
        this.x.addFlags(268435456);
        this.x.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        this.y = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.y.addFlags(268435456);
        this.n = findViewById(this.a.getDropDownAnchor());
        if (this.n != null) {
            this.n.addOnLayoutChangeListener(new OnLayoutChangeListener() {
                public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    SearchView.this.k();
                }
            });
        }
        a(this.E);
        r();
    }

    /* access modifiers changed from: 0000 */
    public int getSuggestionRowLayout() {
        return this.v;
    }

    /* access modifiers changed from: 0000 */
    public int getSuggestionCommitIconResId() {
        return this.w;
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.h = searchableInfo;
        if (this.h != null) {
            s();
            r();
        }
        this.L = m();
        if (this.L) {
            this.a.setPrivateImeOptions("nm");
        }
        a(c());
    }

    public void setAppSearchData(Bundle bundle) {
        this.Q = bundle;
    }

    public void setImeOptions(int i2) {
        this.a.setImeOptions(i2);
    }

    public int getImeOptions() {
        return this.a.getImeOptions();
    }

    public void setInputType(int i2) {
        this.a.setInputType(i2);
    }

    public int getInputType() {
        return this.a.getInputType();
    }

    public boolean requestFocus(int i2, Rect rect) {
        if (this.J || !isFocusable()) {
            return false;
        }
        if (c()) {
            return super.requestFocus(i2, rect);
        }
        boolean requestFocus = this.a.requestFocus(i2, rect);
        if (requestFocus) {
            a(false);
        }
        return requestFocus;
    }

    public void clearFocus() {
        this.J = true;
        super.clearFocus();
        this.a.clearFocus();
        this.a.setImeVisibility(false);
        this.J = false;
    }

    public void setOnQueryTextListener(c cVar) {
        this.A = cVar;
    }

    public void setOnCloseListener(b bVar) {
        this.B = bVar;
    }

    public void setOnQueryTextFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        this.f = onFocusChangeListener;
    }

    public void setOnSuggestionListener(d dVar) {
        this.C = dVar;
    }

    public void setOnSearchClickListener(OnClickListener onClickListener) {
        this.D = onClickListener;
    }

    public CharSequence getQuery() {
        return this.a.getText();
    }

    public void a(CharSequence charSequence, boolean z2) {
        this.a.setText(charSequence);
        if (charSequence != null) {
            this.a.setSelection(this.a.length());
            this.N = charSequence;
        }
        if (z2 && !TextUtils.isEmpty(charSequence)) {
            e();
        }
    }

    public void setQueryHint(CharSequence charSequence) {
        this.H = charSequence;
        r();
    }

    public CharSequence getQueryHint() {
        if (this.H != null) {
            return this.H;
        }
        if (this.h == null || this.h.getHintId() == 0) {
            return this.z;
        }
        return getContext().getText(this.h.getHintId());
    }

    public void setIconifiedByDefault(boolean z2) {
        if (this.E != z2) {
            this.E = z2;
            a(z2);
            r();
        }
    }

    public void setIconified(boolean z2) {
        if (z2) {
            f();
        } else {
            g();
        }
    }

    public boolean c() {
        return this.F;
    }

    public void setSubmitButtonEnabled(boolean z2) {
        this.G = z2;
        a(c());
    }

    public void setQueryRefinementEnabled(boolean z2) {
        this.I = z2;
        if (this.g instanceof an) {
            ((an) this.g).a(z2 ? 2 : 1);
        }
    }

    public void setSuggestionsAdapter(CursorAdapter cursorAdapter) {
        this.g = cursorAdapter;
        this.a.setAdapter(this.g);
    }

    public CursorAdapter getSuggestionsAdapter() {
        return this.g;
    }

    public void setMaxWidth(int i2) {
        this.K = i2;
        requestLayout();
    }

    public int getMaxWidth() {
        return this.K;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        if (c()) {
            super.onMeasure(i2, i3);
            return;
        }
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        switch (mode) {
            case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                if (this.K <= 0) {
                    size = Math.min(getPreferredWidth(), size);
                    break;
                } else {
                    size = Math.min(this.K, size);
                    break;
                }
            case 0:
                if (this.K <= 0) {
                    size = getPreferredWidth();
                    break;
                } else {
                    size = this.K;
                    break;
                }
            case 1073741824:
                if (this.K > 0) {
                    size = Math.min(this.K, size);
                    break;
                }
                break;
        }
        int mode2 = MeasureSpec.getMode(i3);
        int size2 = MeasureSpec.getSize(i3);
        switch (mode2) {
            case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                size2 = Math.min(getPreferredHeight(), size2);
                break;
            case 0:
                size2 = getPreferredHeight();
                break;
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(size, 1073741824), MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        if (z2) {
            a((View) this.a, this.p);
            this.f356q.set(this.p.left, 0, this.p.right, i5 - i3);
            if (this.o == null) {
                this.o = new e(this.f356q, this.p, this.a);
                setTouchDelegate(this.o);
                return;
            }
            this.o.a(this.f356q, this.p);
        }
    }

    private void a(View view, Rect rect) {
        view.getLocationInWindow(this.r);
        getLocationInWindow(this.s);
        int i2 = this.r[1] - this.s[1];
        int i3 = this.r[0] - this.s[0];
        rect.set(i3, i2, view.getWidth() + i3, view.getHeight() + i2);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_width);
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_height);
    }

    private void a(boolean z2) {
        boolean z3;
        int i2;
        boolean z4 = true;
        int i3 = 8;
        this.F = z2;
        int i4 = z2 ? 0 : 8;
        if (!TextUtils.isEmpty(this.a.getText())) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.b.setVisibility(i4);
        b(z3);
        View view = this.k;
        if (z2) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        view.setVisibility(i2);
        if (this.t.getDrawable() != null && !this.E) {
            i3 = 0;
        }
        this.t.setVisibility(i3);
        p();
        if (z3) {
            z4 = false;
        }
        c(z4);
        o();
    }

    private boolean m() {
        if (this.h == null || !this.h.getVoiceSearchEnabled()) {
            return false;
        }
        Intent intent = null;
        if (this.h.getVoiceSearchLaunchWebSearch()) {
            intent = this.x;
        } else if (this.h.getVoiceSearchLaunchRecognizer()) {
            intent = this.y;
        }
        if (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) {
            return false;
        }
        return true;
    }

    private boolean n() {
        return (this.G || this.L) && !c();
    }

    private void b(boolean z2) {
        int i2 = 8;
        if (this.G && n() && hasFocus() && (z2 || !this.L)) {
            i2 = 0;
        }
        this.c.setVisibility(i2);
    }

    private void o() {
        int i2 = 8;
        if (n() && (this.c.getVisibility() == 0 || this.e.getVisibility() == 0)) {
            i2 = 0;
        }
        this.m.setVisibility(i2);
    }

    private void p() {
        boolean z2 = true;
        int i2 = 0;
        boolean z3 = !TextUtils.isEmpty(this.a.getText());
        if (!z3 && (!this.E || this.O)) {
            z2 = false;
        }
        ImageView imageView = this.d;
        if (!z2) {
            i2 = 8;
        }
        imageView.setVisibility(i2);
        Drawable drawable = this.d.getDrawable();
        if (drawable != null) {
            drawable.setState(z3 ? ENABLED_STATE_SET : EMPTY_STATE_SET);
        }
    }

    private void q() {
        post(this.R);
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        int[] iArr = this.a.hasFocus() ? FOCUSED_STATE_SET : EMPTY_STATE_SET;
        Drawable background = this.l.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.m.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        removeCallbacks(this.R);
        post(this.S);
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: 0000 */
    public void a(CharSequence charSequence) {
        setQuery(charSequence);
    }

    /* access modifiers changed from: 0000 */
    public boolean a(View view, int i2, KeyEvent keyEvent) {
        int length;
        if (this.h == null || this.g == null || keyEvent.getAction() != 0 || !keyEvent.hasNoModifiers()) {
            return false;
        }
        if (i2 == 66 || i2 == 84 || i2 == 61) {
            return a(this.a.getListSelection(), 0, (String) null);
        }
        if (i2 == 21 || i2 == 22) {
            if (i2 == 21) {
                length = 0;
            } else {
                length = this.a.length();
            }
            this.a.setSelection(length);
            this.a.setListSelection(0);
            this.a.clearListSelection();
            i.a(this.a, true);
            return true;
        }
        if (!(i2 == 19 && this.a.getListSelection() == 0)) {
        }
        return false;
    }

    private CharSequence c(CharSequence charSequence) {
        if (!this.E || this.u == null) {
            return charSequence;
        }
        int textSize = (int) (((double) this.a.getTextSize()) * 1.25d);
        this.u.setBounds(0, 0, textSize, textSize);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.u), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    private void r() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.a;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(c(queryHint));
    }

    private void s() {
        int i2 = 1;
        this.a.setThreshold(this.h.getSuggestThreshold());
        this.a.setImeOptions(this.h.getImeOptions());
        int inputType = this.h.getInputType();
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.h.getSuggestAuthority() != null) {
                inputType = inputType | 65536 | 524288;
            }
        }
        this.a.setInputType(inputType);
        if (this.g != null) {
            this.g.changeCursor(null);
        }
        if (this.h.getSuggestAuthority() != null) {
            this.g = new an(getContext(), this, this.h, this.T);
            this.a.setAdapter(this.g);
            an anVar = (an) this.g;
            if (this.I) {
                i2 = 2;
            }
            anVar.a(i2);
        }
    }

    private void c(boolean z2) {
        int i2;
        if (!this.L || c() || !z2) {
            i2 = 8;
        } else {
            i2 = 0;
            this.c.setVisibility(8);
        }
        this.e.setVisibility(i2);
    }

    /* access modifiers changed from: 0000 */
    public void b(CharSequence charSequence) {
        boolean z2 = true;
        Editable text = this.a.getText();
        this.N = text;
        boolean z3 = !TextUtils.isEmpty(text);
        b(z3);
        if (z3) {
            z2 = false;
        }
        c(z2);
        p();
        o();
        if (this.A != null && !TextUtils.equals(charSequence, this.M)) {
            this.A.b(charSequence.toString());
        }
        this.M = charSequence.toString();
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        Editable text = this.a.getText();
        if (text != null && TextUtils.getTrimmedLength(text) > 0) {
            if (this.A == null || !this.A.a(text.toString())) {
                if (this.h != null) {
                    a(0, (String) null, text.toString());
                }
                this.a.setImeVisibility(false);
                t();
            }
        }
    }

    private void t() {
        this.a.dismissDropDown();
    }

    /* access modifiers changed from: 0000 */
    public void f() {
        if (!TextUtils.isEmpty(this.a.getText())) {
            this.a.setText("");
            this.a.requestFocus();
            this.a.setImeVisibility(true);
        } else if (!this.E) {
        } else {
            if (this.B == null || !this.B.a()) {
                clearFocus();
                a(true);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void g() {
        a(false);
        this.a.requestFocus();
        this.a.setImeVisibility(true);
        if (this.D != null) {
            this.D.onClick(this);
        }
    }

    /* access modifiers changed from: 0000 */
    public void h() {
        if (this.h != null) {
            SearchableInfo searchableInfo = this.h;
            try {
                if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                    getContext().startActivity(a(this.x, searchableInfo));
                } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                    getContext().startActivity(b(this.y, searchableInfo));
                }
            } catch (ActivityNotFoundException e2) {
                Log.w("SearchView", "Could not find voice search activity");
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void i() {
        a(c());
        q();
        if (this.a.hasFocus()) {
            l();
        }
    }

    public void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        q();
    }

    public void b() {
        a((CharSequence) "", false);
        clearFocus();
        a(true);
        this.a.setImeOptions(this.P);
        this.O = false;
    }

    public void a() {
        if (!this.O) {
            this.O = true;
            this.P = this.a.getImeOptions();
            this.a.setImeOptions(this.P | 33554432);
            this.a.setText("");
            setIconified(false);
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = c();
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        a(savedState.a);
        requestLayout();
    }

    /* access modifiers changed from: 0000 */
    public void k() {
        int i2;
        int i3;
        if (this.n.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.l.getPaddingLeft();
            Rect rect = new Rect();
            boolean a2 = bb.a(this);
            if (this.E) {
                i2 = resources.getDimensionPixelSize(R.dimen.abc_dropdownitem_text_padding_left) + resources.getDimensionPixelSize(R.dimen.abc_dropdownitem_icon_width);
            } else {
                i2 = 0;
            }
            this.a.getDropDownBackground().getPadding(rect);
            if (a2) {
                i3 = -rect.left;
            } else {
                i3 = paddingLeft - (rect.left + i2);
            }
            this.a.setDropDownHorizontalOffset(i3);
            this.a.setDropDownWidth((i2 + ((this.n.getWidth() + rect.left) + rect.right)) - paddingLeft);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(int i2, int i3, String str) {
        if (this.C != null && this.C.b(i2)) {
            return false;
        }
        b(i2, 0, null);
        this.a.setImeVisibility(false);
        t();
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(int i2) {
        if (this.C != null && this.C.a(i2)) {
            return false;
        }
        e(i2);
        return true;
    }

    private void e(int i2) {
        Editable text = this.a.getText();
        Cursor cursor = this.g.getCursor();
        if (cursor != null) {
            if (cursor.moveToPosition(i2)) {
                CharSequence convertToString = this.g.convertToString(cursor);
                if (convertToString != null) {
                    setQuery(convertToString);
                } else {
                    setQuery(text);
                }
            } else {
                setQuery(text);
            }
        }
    }

    private boolean b(int i2, int i3, String str) {
        Cursor cursor = this.g.getCursor();
        if (cursor == null || !cursor.moveToPosition(i2)) {
            return false;
        }
        a(a(cursor, i3, str));
        return true;
    }

    private void a(Intent intent) {
        if (intent != null) {
            try {
                getContext().startActivity(intent);
            } catch (RuntimeException e2) {
                Log.e("SearchView", "Failed launch activity: " + intent, e2);
            }
        }
    }

    private void setQuery(CharSequence charSequence) {
        this.a.setText(charSequence);
        this.a.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, String str, String str2) {
        getContext().startActivity(a("android.intent.action.SEARCH", null, null, str2, i2, str));
    }

    private Intent a(String str, Uri uri, String str2, String str3, int i2, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.N);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        if (this.Q != null) {
            intent.putExtra("app_data", this.Q);
        }
        if (i2 != 0) {
            intent.putExtra("action_key", i2);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.h.getSearchActivity());
        return intent;
    }

    private Intent a(Intent intent, SearchableInfo searchableInfo) {
        String flattenToShortString;
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        String str = "calling_package";
        if (searchActivity == null) {
            flattenToShortString = null;
        } else {
            flattenToShortString = searchActivity.flattenToShortString();
        }
        intent2.putExtra(str, flattenToShortString);
        return intent2;
    }

    private Intent b(Intent intent, SearchableInfo searchableInfo) {
        String str;
        String str2;
        String str3 = null;
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1073741824);
        Bundle bundle = new Bundle();
        if (this.Q != null) {
            bundle.putParcelable("app_data", this.Q);
        }
        Intent intent3 = new Intent(intent);
        String str4 = "free_form";
        int i2 = 1;
        Resources resources = getResources();
        if (searchableInfo.getVoiceLanguageModeId() != 0) {
            str4 = resources.getString(searchableInfo.getVoiceLanguageModeId());
        }
        if (searchableInfo.getVoicePromptTextId() != 0) {
            str = resources.getString(searchableInfo.getVoicePromptTextId());
        } else {
            str = null;
        }
        if (searchableInfo.getVoiceLanguageId() != 0) {
            str2 = resources.getString(searchableInfo.getVoiceLanguageId());
        } else {
            str2 = null;
        }
        if (searchableInfo.getVoiceMaxResults() != 0) {
            i2 = searchableInfo.getVoiceMaxResults();
        }
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", str4);
        intent3.putExtra("android.speech.extra.PROMPT", str);
        intent3.putExtra("android.speech.extra.LANGUAGE", str2);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", i2);
        String str5 = "calling_package";
        if (searchActivity != null) {
            str3 = searchActivity.flattenToShortString();
        }
        intent3.putExtra(str5, str3);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    private Intent a(Cursor cursor, int i2, String str) {
        int i3;
        try {
            String a2 = an.a(cursor, "suggest_intent_action");
            if (a2 == null) {
                a2 = this.h.getSuggestIntentAction();
            }
            if (a2 == null) {
                a2 = "android.intent.action.SEARCH";
            }
            String a3 = an.a(cursor, "suggest_intent_data");
            if (a3 == null) {
                a3 = this.h.getSuggestIntentData();
            }
            if (a3 != null) {
                String a4 = an.a(cursor, "suggest_intent_data_id");
                if (a4 != null) {
                    a3 = a3 + "/" + Uri.encode(a4);
                }
            }
            return a(a2, a3 == null ? null : Uri.parse(a3), an.a(cursor, "suggest_intent_extra_data"), an.a(cursor, "suggest_intent_query"), i2, str);
        } catch (RuntimeException e2) {
            RuntimeException runtimeException = e2;
            try {
                i3 = cursor.getPosition();
            } catch (RuntimeException e3) {
                i3 = -1;
            }
            Log.w("SearchView", "Search suggestions cursor at row " + i3 + " returned exception.", runtimeException);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public void l() {
        i.a(this.a);
        i.b(this.a);
    }

    static boolean a(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }
}
