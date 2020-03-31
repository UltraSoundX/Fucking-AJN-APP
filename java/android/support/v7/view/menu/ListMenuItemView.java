package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.m.a;
import android.support.v7.widget.at;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView.SelectionBoundsAdjuster;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class ListMenuItemView extends LinearLayout implements a, SelectionBoundsAdjuster {
    private MenuItemImpl a;
    private ImageView b;
    private RadioButton c;
    private TextView d;
    private CheckBox e;
    private TextView f;
    private ImageView g;
    private ImageView h;
    private LinearLayout i;
    private Drawable j;
    private int k;
    private Context l;
    private boolean m;
    private Drawable n;
    private boolean o;
    private int p;

    /* renamed from: q reason: collision with root package name */
    private LayoutInflater f343q;
    private boolean r;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listMenuViewStyle);
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet);
        at a2 = at.a(getContext(), attributeSet, R.styleable.MenuView, i2, 0);
        this.j = a2.a(R.styleable.MenuView_android_itemBackground);
        this.k = a2.g(R.styleable.MenuView_android_itemTextAppearance, -1);
        this.m = a2.a(R.styleable.MenuView_preserveIconSpacing, false);
        this.l = context;
        this.n = a2.a(R.styleable.MenuView_subMenuArrow);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{16843049}, R.attr.dropDownListViewStyle, 0);
        this.o = obtainStyledAttributes.hasValue(0);
        a2.a();
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        ViewCompat.setBackground(this, this.j);
        this.d = (TextView) findViewById(R.id.title);
        if (this.k != -1) {
            this.d.setTextAppearance(this.l, this.k);
        }
        this.f = (TextView) findViewById(R.id.shortcut);
        this.g = (ImageView) findViewById(R.id.submenuarrow);
        if (this.g != null) {
            this.g.setImageDrawable(this.n);
        }
        this.h = (ImageView) findViewById(R.id.group_divider);
        this.i = (LinearLayout) findViewById(R.id.content);
    }

    public void a(MenuItemImpl menuItemImpl, int i2) {
        this.a = menuItemImpl;
        this.p = i2;
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        setTitle(menuItemImpl.a((a) this));
        setCheckable(menuItemImpl.isCheckable());
        a(menuItemImpl.e(), menuItemImpl.c());
        setIcon(menuItemImpl.getIcon());
        setEnabled(menuItemImpl.isEnabled());
        setSubMenuArrowVisible(menuItemImpl.hasSubMenu());
        setContentDescription(menuItemImpl.getContentDescription());
    }

    private void a(View view) {
        a(view, -1);
    }

    private void a(View view, int i2) {
        if (this.i != null) {
            this.i.addView(view, i2);
        } else {
            addView(view, i2);
        }
    }

    public void setForceShowIcon(boolean z) {
        this.r = z;
        this.m = z;
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence != null) {
            this.d.setText(charSequence);
            if (this.d.getVisibility() != 0) {
                this.d.setVisibility(0);
            }
        } else if (this.d.getVisibility() != 8) {
            this.d.setVisibility(8);
        }
    }

    public MenuItemImpl getItemData() {
        return this.a;
    }

    public void setCheckable(boolean z) {
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        if (z || this.c != null || this.e != null) {
            if (this.a.f()) {
                if (this.c == null) {
                    c();
                }
                compoundButton = this.c;
                compoundButton2 = this.e;
            } else {
                if (this.e == null) {
                    d();
                }
                compoundButton = this.e;
                compoundButton2 = this.c;
            }
            if (z) {
                compoundButton.setChecked(this.a.isChecked());
                if (compoundButton.getVisibility() != 0) {
                    compoundButton.setVisibility(0);
                }
                if (compoundButton2 != null && compoundButton2.getVisibility() != 8) {
                    compoundButton2.setVisibility(8);
                    return;
                }
                return;
            }
            if (this.e != null) {
                this.e.setVisibility(8);
            }
            if (this.c != null) {
                this.c.setVisibility(8);
            }
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.a.f()) {
            if (this.c == null) {
                c();
            }
            compoundButton = this.c;
        } else {
            if (this.e == null) {
                d();
            }
            compoundButton = this.e;
        }
        compoundButton.setChecked(z);
    }

    private void setSubMenuArrowVisible(boolean z) {
        if (this.g != null) {
            this.g.setVisibility(z ? 0 : 8);
        }
    }

    public void a(boolean z, char c2) {
        int i2 = (!z || !this.a.e()) ? 8 : 0;
        if (i2 == 0) {
            this.f.setText(this.a.d());
        }
        if (this.f.getVisibility() != i2) {
            this.f.setVisibility(i2);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z = this.a.h() || this.r;
        if (!z && !this.m) {
            return;
        }
        if (this.b != null || drawable != null || this.m) {
            if (this.b == null) {
                b();
            }
            if (drawable != null || this.m) {
                ImageView imageView = this.b;
                if (!z) {
                    drawable = null;
                }
                imageView.setImageDrawable(drawable);
                if (this.b.getVisibility() != 0) {
                    this.b.setVisibility(0);
                    return;
                }
                return;
            }
            this.b.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        if (this.b != null && this.m) {
            LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.b.getLayoutParams();
            if (layoutParams.height > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = layoutParams.height;
            }
        }
        super.onMeasure(i2, i3);
    }

    private void b() {
        this.b = (ImageView) getInflater().inflate(R.layout.abc_list_menu_item_icon, this, false);
        a((View) this.b, 0);
    }

    private void c() {
        this.c = (RadioButton) getInflater().inflate(R.layout.abc_list_menu_item_radio, this, false);
        a(this.c);
    }

    private void d() {
        this.e = (CheckBox) getInflater().inflate(R.layout.abc_list_menu_item_checkbox, this, false);
        a(this.e);
    }

    public boolean a() {
        return false;
    }

    private LayoutInflater getInflater() {
        if (this.f343q == null) {
            this.f343q = LayoutInflater.from(getContext());
        }
        return this.f343q;
    }

    public void setGroupDividerEnabled(boolean z) {
        if (this.h != null) {
            this.h.setVisibility((this.o || !z) ? 8 : 0);
        }
    }

    public void adjustListItemSelectionBounds(Rect rect) {
        if (this.h != null && this.h.getVisibility() == 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.h.getLayoutParams();
            rect.top = layoutParams.bottomMargin + this.h.getHeight() + layoutParams.topMargin + rect.top;
        }
    }
}
