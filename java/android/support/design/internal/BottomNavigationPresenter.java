package android.support.design.internal;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.view.menu.l;
import android.support.v7.view.menu.l.a;

public class BottomNavigationPresenter implements l {
    private MenuBuilder a;
    private BottomNavigationMenuView b;
    private boolean c = false;
    private int d;

    static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int a;

        SavedState() {
        }

        SavedState(Parcel parcel) {
            this.a = parcel.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
        }
    }

    public void a(BottomNavigationMenuView bottomNavigationMenuView) {
        this.b = bottomNavigationMenuView;
    }

    public void a(Context context, MenuBuilder menuBuilder) {
        this.a = menuBuilder;
        this.b.a(this.a);
    }

    public void a(boolean z) {
        if (!this.c) {
            if (z) {
                this.b.b();
            } else {
                this.b.c();
            }
        }
    }

    public void a(a aVar) {
    }

    public boolean a(SubMenuBuilder subMenuBuilder) {
        return false;
    }

    public void a(MenuBuilder menuBuilder, boolean z) {
    }

    public boolean a() {
        return false;
    }

    public boolean a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public void a(int i) {
        this.d = i;
    }

    public int b() {
        return this.d;
    }

    public Parcelable c() {
        SavedState savedState = new SavedState();
        savedState.a = this.b.getSelectedItemId();
        return savedState;
    }

    public void a(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.b.b(((SavedState) parcelable).a);
        }
    }

    public void b(boolean z) {
        this.c = z;
    }
}
