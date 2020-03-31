package android.support.v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ResourceCursorAdapter;
import android.support.v7.appcompat.R;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

/* compiled from: SuggestionsAdapter */
class an extends ResourceCursorAdapter implements OnClickListener {
    private final SearchManager a = ((SearchManager) this.mContext.getSystemService("search"));
    private final SearchView b;
    private final SearchableInfo c;
    private final Context d;
    private final WeakHashMap<String, ConstantState> e;
    private final int f;
    private boolean g = false;
    private int h = 1;
    private ColorStateList i;
    private int j = -1;
    private int k = -1;
    private int l = -1;
    private int m = -1;
    private int n = -1;
    private int o = -1;

    /* compiled from: SuggestionsAdapter */
    private static final class a {
        public final TextView a;
        public final TextView b;
        public final ImageView c;
        public final ImageView d;
        public final ImageView e;

        public a(View view) {
            this.a = (TextView) view.findViewById(16908308);
            this.b = (TextView) view.findViewById(16908309);
            this.c = (ImageView) view.findViewById(16908295);
            this.d = (ImageView) view.findViewById(16908296);
            this.e = (ImageView) view.findViewById(R.id.edit_query);
        }
    }

    public an(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), (Cursor) null, true);
        this.b = searchView;
        this.c = searchableInfo;
        this.f = searchView.getSuggestionCommitIconResId();
        this.d = context;
        this.e = weakHashMap;
    }

    public void a(int i2) {
        this.h = i2;
    }

    public boolean hasStableIds() {
        return false;
    }

    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        String charSequence2 = charSequence == null ? "" : charSequence.toString();
        if (this.b.getVisibility() != 0 || this.b.getWindowVisibility() != 0) {
            return null;
        }
        try {
            Cursor a2 = a(this.c, charSequence2, 50);
            if (a2 != null) {
                a2.getCount();
                return a2;
            }
        } catch (RuntimeException e2) {
            Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e2);
        }
        return null;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        a(getCursor());
    }

    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        a(getCursor());
    }

    private void a(Cursor cursor) {
        Bundle bundle = cursor != null ? cursor.getExtras() : null;
        if (bundle == null || bundle.getBoolean("in_progress")) {
        }
    }

    public void changeCursor(Cursor cursor) {
        if (this.g) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super.changeCursor(cursor);
            if (cursor != null) {
                this.j = cursor.getColumnIndex("suggest_text_1");
                this.k = cursor.getColumnIndex("suggest_text_2");
                this.l = cursor.getColumnIndex("suggest_text_2_url");
                this.m = cursor.getColumnIndex("suggest_icon_1");
                this.n = cursor.getColumnIndex("suggest_icon_2");
                this.o = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e2) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e2);
        }
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View newView = super.newView(context, cursor, viewGroup);
        newView.setTag(new a(newView));
        ((ImageView) newView.findViewById(R.id.edit_query)).setImageResource(this.f);
        return newView;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        int i2;
        CharSequence a2;
        a aVar = (a) view.getTag();
        if (this.o != -1) {
            i2 = cursor.getInt(this.o);
        } else {
            i2 = 0;
        }
        if (aVar.a != null) {
            a(aVar.a, (CharSequence) a(cursor, this.j));
        }
        if (aVar.b != null) {
            String a3 = a(cursor, this.l);
            if (a3 != null) {
                a2 = a((CharSequence) a3);
            } else {
                a2 = a(cursor, this.k);
            }
            if (TextUtils.isEmpty(a2)) {
                if (aVar.a != null) {
                    aVar.a.setSingleLine(false);
                    aVar.a.setMaxLines(2);
                }
            } else if (aVar.a != null) {
                aVar.a.setSingleLine(true);
                aVar.a.setMaxLines(1);
            }
            a(aVar.b, a2);
        }
        if (aVar.c != null) {
            a(aVar.c, b(cursor), 4);
        }
        if (aVar.d != null) {
            a(aVar.d, c(cursor), 8);
        }
        if (this.h == 2 || (this.h == 1 && (i2 & 1) != 0)) {
            aVar.e.setVisibility(0);
            aVar.e.setTag(aVar.a.getText());
            aVar.e.setOnClickListener(this);
            return;
        }
        aVar.e.setVisibility(8);
    }

    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.b.a((CharSequence) tag);
        }
    }

    private CharSequence a(CharSequence charSequence) {
        if (this.i == null) {
            TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.textColorSearchUrl, typedValue, true);
            this.i = this.mContext.getResources().getColorStateList(typedValue.resourceId);
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.i, null), 0, charSequence.length(), 33);
        return spannableString;
    }

    private void a(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    private Drawable b(Cursor cursor) {
        if (this.m == -1) {
            return null;
        }
        Drawable a2 = a(cursor.getString(this.m));
        return a2 == null ? d(cursor) : a2;
    }

    private Drawable c(Cursor cursor) {
        if (this.n == -1) {
            return null;
        }
        return a(cursor.getString(this.n));
    }

    private void a(ImageView imageView, Drawable drawable, int i2) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i2);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    public CharSequence convertToString(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        String a2 = a(cursor, "suggest_intent_query");
        if (a2 != null) {
            return a2;
        }
        if (this.c.shouldRewriteQueryFromData()) {
            String a3 = a(cursor, "suggest_intent_data");
            if (a3 != null) {
                return a3;
            }
        }
        if (!this.c.shouldRewriteQueryFromText()) {
            return null;
        }
        String a4 = a(cursor, "suggest_text_1");
        if (a4 != null) {
            return a4;
        }
        return null;
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i2, view, viewGroup);
        } catch (RuntimeException e2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e2);
            View newView = newView(this.mContext, this.mCursor, viewGroup);
            if (newView != null) {
                ((a) newView.getTag()).a.setText(e2.toString());
            }
            return newView;
        }
    }

    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i2, view, viewGroup);
        } catch (RuntimeException e2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e2);
            View newDropDownView = newDropDownView(this.mContext, this.mCursor, viewGroup);
            if (newDropDownView != null) {
                ((a) newDropDownView.getTag()).a.setText(e2.toString());
            }
            return newDropDownView;
        }
    }

    private Drawable a(String str) {
        if (str == null || str.isEmpty() || "0".equals(str)) {
            return null;
        }
        try {
            int parseInt = Integer.parseInt(str);
            String str2 = "android.resource://" + this.d.getPackageName() + "/" + parseInt;
            Drawable b2 = b(str2);
            if (b2 != null) {
                return b2;
            }
            Drawable drawable = ContextCompat.getDrawable(this.d, parseInt);
            a(str2, drawable);
            return drawable;
        } catch (NumberFormatException e2) {
            Drawable b3 = b(str);
            if (b3 != null) {
                return b3;
            }
            Drawable b4 = b(Uri.parse(str));
            a(str, b4);
            return b4;
        } catch (NotFoundException e3) {
            Log.w("SuggestionsAdapter", "Icon resource not found: " + str);
            return null;
        }
    }

    private Drawable b(Uri uri) {
        InputStream openInputStream;
        try {
            if ("android.resource".equals(uri.getScheme())) {
                return a(uri);
            }
            openInputStream = this.d.getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                throw new FileNotFoundException("Failed to open " + uri);
            }
            Drawable createFromStream = Drawable.createFromStream(openInputStream, null);
            try {
                openInputStream.close();
                return createFromStream;
            } catch (IOException e2) {
                Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e2);
                return createFromStream;
            }
        } catch (NotFoundException e3) {
            throw new FileNotFoundException("Resource does not exist: " + uri);
        } catch (FileNotFoundException e4) {
            Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e4.getMessage());
            return null;
        } catch (Throwable th) {
            try {
                openInputStream.close();
            } catch (IOException e5) {
                Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e5);
            }
            throw th;
        }
    }

    private Drawable b(String str) {
        ConstantState constantState = (ConstantState) this.e.get(str);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable();
    }

    private void a(String str, Drawable drawable) {
        if (drawable != null) {
            this.e.put(str, drawable.getConstantState());
        }
    }

    private Drawable d(Cursor cursor) {
        Drawable a2 = a(this.c.getSearchActivity());
        return a2 != null ? a2 : this.mContext.getPackageManager().getDefaultActivityIcon();
    }

    private Drawable a(ComponentName componentName) {
        ConstantState constantState = null;
        String flattenToShortString = componentName.flattenToShortString();
        if (this.e.containsKey(flattenToShortString)) {
            ConstantState constantState2 = (ConstantState) this.e.get(flattenToShortString);
            if (constantState2 == null) {
                return null;
            }
            return constantState2.newDrawable(this.d.getResources());
        }
        Drawable b2 = b(componentName);
        if (b2 != null) {
            constantState = b2.getConstantState();
        }
        this.e.put(flattenToShortString, constantState);
        return b2;
    }

    private Drawable b(ComponentName componentName) {
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 128);
            int iconResource = activityInfo.getIconResource();
            if (iconResource == 0) {
                return null;
            }
            Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
            if (drawable != null) {
                return drawable;
            }
            Log.w("SuggestionsAdapter", "Invalid icon resource " + iconResource + " for " + componentName.flattenToShortString());
            return null;
        } catch (NameNotFoundException e2) {
            Log.w("SuggestionsAdapter", e2.toString());
            return null;
        }
    }

    public static String a(Cursor cursor, String str) {
        return a(cursor, cursor.getColumnIndex(str));
    }

    private static String a(Cursor cursor, int i2) {
        String str = null;
        if (i2 == -1) {
            return str;
        }
        try {
            return cursor.getString(i2);
        } catch (Exception e2) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e2);
            return str;
        }
    }

    /* access modifiers changed from: 0000 */
    public Drawable a(Uri uri) throws FileNotFoundException {
        int identifier;
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources resourcesForApplication = this.mContext.getPackageManager().getResourcesForApplication(authority);
            List pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                throw new FileNotFoundException("No path: " + uri);
            }
            int size = pathSegments.size();
            if (size == 1) {
                try {
                    identifier = Integer.parseInt((String) pathSegments.get(0));
                } catch (NumberFormatException e2) {
                    throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                }
            } else if (size == 2) {
                identifier = resourcesForApplication.getIdentifier((String) pathSegments.get(1), (String) pathSegments.get(0), authority);
            } else {
                throw new FileNotFoundException("More than two path segments: " + uri);
            }
            if (identifier != 0) {
                return resourcesForApplication.getDrawable(identifier);
            }
            throw new FileNotFoundException("No resource found for: " + uri);
        } catch (NameNotFoundException e3) {
            throw new FileNotFoundException("No package found for authority: " + uri);
        }
    }

    /* access modifiers changed from: 0000 */
    public Cursor a(SearchableInfo searchableInfo, String str, int i2) {
        String[] strArr;
        if (searchableInfo == null) {
            return null;
        }
        String suggestAuthority = searchableInfo.getSuggestAuthority();
        if (suggestAuthority == null) {
            return null;
        }
        Builder fragment = new Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            fragment.appendEncodedPath(suggestPath);
        }
        fragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            fragment.appendPath(str);
            strArr = null;
        }
        if (i2 > 0) {
            fragment.appendQueryParameter("limit", String.valueOf(i2));
        }
        return this.mContext.getContentResolver().query(fragment.build(), null, suggestSelection, strArr, null);
    }
}
