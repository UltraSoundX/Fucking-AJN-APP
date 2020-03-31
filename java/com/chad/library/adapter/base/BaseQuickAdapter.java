package com.chad.library.adapter.base;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.b;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.a;
import android.support.v7.widget.RecyclerView.i;
import android.support.v7.widget.RecyclerView.v;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.AlphaInAnimation;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.chad.library.adapter.base.animation.SlideInBottomAnimation;
import com.chad.library.adapter.base.animation.SlideInLeftAnimation;
import com.chad.library.adapter.base.animation.SlideInRightAnimation;
import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseQuickAdapter<T, K extends BaseViewHolder> extends a<K> {
    public static final int ALPHAIN = 1;
    public static final int EMPTY_VIEW = 1365;
    public static final int FOOTER_VIEW = 819;
    public static final int HEADER_VIEW = 273;
    public static final int LOADING_VIEW = 546;
    public static final int SCALEIN = 2;
    public static final int SLIDEIN_BOTTOM = 3;
    public static final int SLIDEIN_LEFT = 4;
    public static final int SLIDEIN_RIGHT = 5;
    protected static final String TAG = BaseQuickAdapter.class.getSimpleName();
    private boolean footerViewAsFlow;
    private boolean headerViewAsFlow;
    protected Context mContext;
    private BaseAnimation mCustomAnimation;
    protected List<T> mData;
    private int mDuration;
    private FrameLayout mEmptyLayout;
    /* access modifiers changed from: private */
    public boolean mEnableLoadMoreEndClick;
    private boolean mFirstOnlyEnable;
    private boolean mFootAndEmptyEnable;
    private LinearLayout mFooterLayout;
    private boolean mHeadAndEmptyEnable;
    private LinearLayout mHeaderLayout;
    private Interpolator mInterpolator;
    private boolean mIsUseEmpty;
    private int mLastPosition;
    protected LayoutInflater mLayoutInflater;
    protected int mLayoutResId;
    private boolean mLoadMoreEnable;
    /* access modifiers changed from: private */
    public LoadMoreView mLoadMoreView;
    private boolean mLoading;
    private MultiTypeDelegate<T> mMultiTypeDelegate;
    private boolean mNextLoadEnable;
    private OnItemChildClickListener mOnItemChildClickListener;
    private OnItemChildLongClickListener mOnItemChildLongClickListener;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private boolean mOpenAnimationEnable;
    private int mPreLoadNumber;
    private RecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public RequestLoadMoreListener mRequestLoadMoreListener;
    private BaseAnimation mSelectAnimation;
    /* access modifiers changed from: private */
    public SpanSizeLookup mSpanSizeLookup;
    private int mStartUpFetchPosition;
    private boolean mUpFetchEnable;
    private UpFetchListener mUpFetchListener;
    private boolean mUpFetching;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AnimationType {
    }

    public interface OnItemChildClickListener {
        void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i);
    }

    public interface OnItemChildLongClickListener {
        boolean onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i);
    }

    public interface OnItemClickListener {
        void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i);
    }

    public interface RequestLoadMoreListener {
        void onLoadMoreRequested();
    }

    public interface SpanSizeLookup {
        int getSpanSize(GridLayoutManager gridLayoutManager, int i);
    }

    public interface UpFetchListener {
        void onUpFetch();
    }

    /* access modifiers changed from: protected */
    public abstract void convert(K k, T t);

    /* access modifiers changed from: protected */
    public RecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    private void checkNotNull() {
        if (getRecyclerView() == null) {
            throw new RuntimeException("please bind recyclerView first!");
        }
    }

    public void bindToRecyclerView(RecyclerView recyclerView) {
        if (getRecyclerView() != null) {
            throw new RuntimeException("Don't bind twice");
        }
        setRecyclerView(recyclerView);
        getRecyclerView().setAdapter(this);
    }

    @Deprecated
    public void setOnLoadMoreListener(RequestLoadMoreListener requestLoadMoreListener) {
        openLoadMore(requestLoadMoreListener);
    }

    private void openLoadMore(RequestLoadMoreListener requestLoadMoreListener) {
        this.mRequestLoadMoreListener = requestLoadMoreListener;
        this.mNextLoadEnable = true;
        this.mLoadMoreEnable = true;
        this.mLoading = false;
    }

    public void setOnLoadMoreListener(RequestLoadMoreListener requestLoadMoreListener, RecyclerView recyclerView) {
        openLoadMore(requestLoadMoreListener);
        if (getRecyclerView() == null) {
            setRecyclerView(recyclerView);
        }
    }

    public void disableLoadMoreIfNotFullPage() {
        checkNotNull();
        disableLoadMoreIfNotFullPage(getRecyclerView());
    }

    public void disableLoadMoreIfNotFullPage(RecyclerView recyclerView) {
        setEnableLoadMore(false);
        if (recyclerView != null) {
            i layoutManager = recyclerView.getLayoutManager();
            if (layoutManager == null) {
                return;
            }
            if (layoutManager instanceof LinearLayoutManager) {
                final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                recyclerView.postDelayed(new Runnable() {
                    public void run() {
                        if (BaseQuickAdapter.this.isFullScreen(linearLayoutManager)) {
                            BaseQuickAdapter.this.setEnableLoadMore(true);
                        }
                    }
                }, 50);
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                final StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                recyclerView.postDelayed(new Runnable() {
                    public void run() {
                        int[] iArr = new int[staggeredGridLayoutManager.i()];
                        staggeredGridLayoutManager.a(iArr);
                        if (BaseQuickAdapter.this.getTheBiggestNumber(iArr) + 1 != BaseQuickAdapter.this.getItemCount()) {
                            BaseQuickAdapter.this.setEnableLoadMore(true);
                        }
                    }
                }, 50);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean isFullScreen(LinearLayoutManager linearLayoutManager) {
        return (linearLayoutManager.q() + 1 == getItemCount() && linearLayoutManager.o() == 0) ? false : true;
    }

    /* access modifiers changed from: private */
    public int getTheBiggestNumber(int[] iArr) {
        int i = -1;
        if (!(iArr == null || iArr.length == 0)) {
            int length = iArr.length;
            int i2 = 0;
            while (i2 < length) {
                int i3 = iArr[i2];
                if (i3 <= i) {
                    i3 = i;
                }
                i2++;
                i = i3;
            }
        }
        return i;
    }

    public void setUpFetchEnable(boolean z) {
        this.mUpFetchEnable = z;
    }

    public boolean isUpFetchEnable() {
        return this.mUpFetchEnable;
    }

    public void setStartUpFetchPosition(int i) {
        this.mStartUpFetchPosition = i;
    }

    private void autoUpFetch(int i) {
        if (isUpFetchEnable() && !isUpFetching() && i <= this.mStartUpFetchPosition && this.mUpFetchListener != null) {
            this.mUpFetchListener.onUpFetch();
        }
    }

    public boolean isUpFetching() {
        return this.mUpFetching;
    }

    public void setUpFetching(boolean z) {
        this.mUpFetching = z;
    }

    public void setUpFetchListener(UpFetchListener upFetchListener) {
        this.mUpFetchListener = upFetchListener;
    }

    public void setNotDoAnimationCount(int i) {
        this.mLastPosition = i;
    }

    public void setLoadMoreView(LoadMoreView loadMoreView) {
        this.mLoadMoreView = loadMoreView;
    }

    public int getLoadMoreViewCount() {
        if (this.mRequestLoadMoreListener == null || !this.mLoadMoreEnable) {
            return 0;
        }
        if ((this.mNextLoadEnable || !this.mLoadMoreView.isLoadEndMoreGone()) && this.mData.size() != 0) {
            return 1;
        }
        return 0;
    }

    public int getLoadMoreViewPosition() {
        return getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount();
    }

    public boolean isLoading() {
        return this.mLoading;
    }

    public void loadMoreEnd() {
        loadMoreEnd(false);
    }

    public void loadMoreEnd(boolean z) {
        if (getLoadMoreViewCount() != 0) {
            this.mLoading = false;
            this.mNextLoadEnable = false;
            this.mLoadMoreView.setLoadMoreEndGone(z);
            if (z) {
                notifyItemRemoved(getLoadMoreViewPosition());
                return;
            }
            this.mLoadMoreView.setLoadMoreStatus(4);
            notifyItemChanged(getLoadMoreViewPosition());
        }
    }

    public void loadMoreComplete() {
        if (getLoadMoreViewCount() != 0) {
            this.mLoading = false;
            this.mNextLoadEnable = true;
            this.mLoadMoreView.setLoadMoreStatus(1);
            notifyItemChanged(getLoadMoreViewPosition());
        }
    }

    public void loadMoreFail() {
        if (getLoadMoreViewCount() != 0) {
            this.mLoading = false;
            this.mLoadMoreView.setLoadMoreStatus(3);
            notifyItemChanged(getLoadMoreViewPosition());
        }
    }

    public void setEnableLoadMore(boolean z) {
        int loadMoreViewCount = getLoadMoreViewCount();
        this.mLoadMoreEnable = z;
        int loadMoreViewCount2 = getLoadMoreViewCount();
        if (loadMoreViewCount == 1) {
            if (loadMoreViewCount2 == 0) {
                notifyItemRemoved(getLoadMoreViewPosition());
            }
        } else if (loadMoreViewCount2 == 1) {
            this.mLoadMoreView.setLoadMoreStatus(1);
            notifyItemInserted(getLoadMoreViewPosition());
        }
    }

    public boolean isLoadMoreEnable() {
        return this.mLoadMoreEnable;
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public final void refreshNotifyItemChanged(int i) {
        notifyItemChanged(getHeaderLayoutCount() + i);
    }

    public BaseQuickAdapter(int i, List<T> list) {
        this.mNextLoadEnable = false;
        this.mLoadMoreEnable = false;
        this.mLoading = false;
        this.mLoadMoreView = new SimpleLoadMoreView();
        this.mEnableLoadMoreEndClick = false;
        this.mFirstOnlyEnable = true;
        this.mOpenAnimationEnable = false;
        this.mInterpolator = new LinearInterpolator();
        this.mDuration = ErrorCode.ERROR_CODE_LOAD_BASE;
        this.mLastPosition = -1;
        this.mSelectAnimation = new AlphaInAnimation();
        this.mIsUseEmpty = true;
        this.mStartUpFetchPosition = 1;
        this.mPreLoadNumber = 1;
        if (list == null) {
            list = new ArrayList<>();
        }
        this.mData = list;
        if (i != 0) {
            this.mLayoutResId = i;
        }
    }

    public BaseQuickAdapter(List<T> list) {
        this(0, list);
    }

    public BaseQuickAdapter(int i) {
        this(i, null);
    }

    public void setNewData(List<T> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.mData = list;
        if (this.mRequestLoadMoreListener != null) {
            this.mNextLoadEnable = true;
            this.mLoadMoreEnable = true;
            this.mLoading = false;
            this.mLoadMoreView.setLoadMoreStatus(1);
        }
        this.mLastPosition = -1;
        notifyDataSetChanged();
    }

    @Deprecated
    public void add(int i, T t) {
        addData(i, t);
    }

    public void addData(int i, T t) {
        this.mData.add(i, t);
        notifyItemInserted(getHeaderLayoutCount() + i);
        compatibilityDataSizeChanged(1);
    }

    public void addData(T t) {
        this.mData.add(t);
        notifyItemInserted(this.mData.size() + getHeaderLayoutCount());
        compatibilityDataSizeChanged(1);
    }

    public void remove(int i) {
        this.mData.remove(i);
        int headerLayoutCount = getHeaderLayoutCount() + i;
        notifyItemRemoved(headerLayoutCount);
        compatibilityDataSizeChanged(0);
        notifyItemRangeChanged(headerLayoutCount, this.mData.size() - headerLayoutCount);
    }

    public void setData(int i, T t) {
        this.mData.set(i, t);
        notifyItemChanged(getHeaderLayoutCount() + i);
    }

    public void addData(int i, Collection<? extends T> collection) {
        this.mData.addAll(i, collection);
        notifyItemRangeInserted(getHeaderLayoutCount() + i, collection.size());
        compatibilityDataSizeChanged(collection.size());
    }

    public void addData(Collection<? extends T> collection) {
        this.mData.addAll(collection);
        notifyItemRangeInserted((this.mData.size() - collection.size()) + getHeaderLayoutCount(), collection.size());
        compatibilityDataSizeChanged(collection.size());
    }

    public void replaceData(Collection<? extends T> collection) {
        if (collection != this.mData) {
            this.mData.clear();
            this.mData.addAll(collection);
        }
        notifyDataSetChanged();
    }

    private void compatibilityDataSizeChanged(int i) {
        if ((this.mData == null ? 0 : this.mData.size()) == i) {
            notifyDataSetChanged();
        }
    }

    public List<T> getData() {
        return this.mData;
    }

    public T getItem(int i) {
        if (i < 0 || i >= this.mData.size()) {
            return null;
        }
        return this.mData.get(i);
    }

    @Deprecated
    public int getHeaderViewsCount() {
        return getHeaderLayoutCount();
    }

    @Deprecated
    public int getFooterViewsCount() {
        return getFooterLayoutCount();
    }

    public int getHeaderLayoutCount() {
        if (this.mHeaderLayout == null || this.mHeaderLayout.getChildCount() == 0) {
            return 0;
        }
        return 1;
    }

    public int getFooterLayoutCount() {
        if (this.mFooterLayout == null || this.mFooterLayout.getChildCount() == 0) {
            return 0;
        }
        return 1;
    }

    public int getEmptyViewCount() {
        if (this.mEmptyLayout == null || this.mEmptyLayout.getChildCount() == 0 || !this.mIsUseEmpty || this.mData.size() != 0) {
            return 0;
        }
        return 1;
    }

    public int getItemCount() {
        int i = 1;
        if (getEmptyViewCount() != 1) {
            return getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount() + getLoadMoreViewCount();
        }
        if (this.mHeadAndEmptyEnable && getHeaderLayoutCount() != 0) {
            i = 2;
        }
        if (!this.mFootAndEmptyEnable || getFooterLayoutCount() == 0) {
            return i;
        }
        return i + 1;
    }

    public int getItemViewType(int i) {
        boolean z = true;
        if (getEmptyViewCount() == 1) {
            if (!this.mHeadAndEmptyEnable || getHeaderLayoutCount() == 0) {
                z = false;
            }
            switch (i) {
                case 0:
                    if (!z) {
                        return EMPTY_VIEW;
                    }
                    return HEADER_VIEW;
                case 1:
                    if (z) {
                        return EMPTY_VIEW;
                    }
                    return FOOTER_VIEW;
                case 2:
                    return FOOTER_VIEW;
                default:
                    return EMPTY_VIEW;
            }
        } else {
            int headerLayoutCount = getHeaderLayoutCount();
            if (i < headerLayoutCount) {
                return HEADER_VIEW;
            }
            int i2 = i - headerLayoutCount;
            int size = this.mData.size();
            if (i2 < size) {
                return getDefItemViewType(i2);
            }
            if (i2 - size < getFooterLayoutCount()) {
                return FOOTER_VIEW;
            }
            return LOADING_VIEW;
        }
    }

    /* access modifiers changed from: protected */
    public int getDefItemViewType(int i) {
        if (this.mMultiTypeDelegate != null) {
            return this.mMultiTypeDelegate.getDefItemViewType(this.mData, i);
        }
        return super.getItemViewType(i);
    }

    public K onCreateViewHolder(ViewGroup viewGroup, int i) {
        K createBaseViewHolder;
        this.mContext = viewGroup.getContext();
        this.mLayoutInflater = LayoutInflater.from(this.mContext);
        switch (i) {
            case HEADER_VIEW /*273*/:
                createBaseViewHolder = createBaseViewHolder(this.mHeaderLayout);
                break;
            case LOADING_VIEW /*546*/:
                createBaseViewHolder = getLoadingView(viewGroup);
                break;
            case FOOTER_VIEW /*819*/:
                createBaseViewHolder = createBaseViewHolder(this.mFooterLayout);
                break;
            case EMPTY_VIEW /*1365*/:
                createBaseViewHolder = createBaseViewHolder(this.mEmptyLayout);
                break;
            default:
                createBaseViewHolder = onCreateDefViewHolder(viewGroup, i);
                bindViewClickListener(createBaseViewHolder);
                break;
        }
        createBaseViewHolder.setAdapter(this);
        return createBaseViewHolder;
    }

    private K getLoadingView(ViewGroup viewGroup) {
        K createBaseViewHolder = createBaseViewHolder(getItemView(this.mLoadMoreView.getLayoutId(), viewGroup));
        createBaseViewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (BaseQuickAdapter.this.mLoadMoreView.getLoadMoreStatus() == 3) {
                    BaseQuickAdapter.this.notifyLoadMoreToLoading();
                }
                if (BaseQuickAdapter.this.mEnableLoadMoreEndClick && BaseQuickAdapter.this.mLoadMoreView.getLoadMoreStatus() == 4) {
                    BaseQuickAdapter.this.notifyLoadMoreToLoading();
                }
            }
        });
        return createBaseViewHolder;
    }

    public void notifyLoadMoreToLoading() {
        if (this.mLoadMoreView.getLoadMoreStatus() != 2) {
            this.mLoadMoreView.setLoadMoreStatus(1);
            notifyItemChanged(getLoadMoreViewPosition());
        }
    }

    public void enableLoadMoreEndClick(boolean z) {
        this.mEnableLoadMoreEndClick = z;
    }

    public void onViewAttachedToWindow(K k) {
        super.onViewAttachedToWindow(k);
        int itemViewType = k.getItemViewType();
        if (itemViewType == 1365 || itemViewType == 273 || itemViewType == 819 || itemViewType == 546) {
            setFullSpan(k);
        } else {
            addAnimation(k);
        }
    }

    /* access modifiers changed from: protected */
    public void setFullSpan(v vVar) {
        if (vVar.itemView.getLayoutParams() instanceof LayoutParams) {
            ((LayoutParams) vVar.itemView.getLayoutParams()).a(true);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        i layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.a((b) new b() {
                public int getSpanSize(int i) {
                    int itemViewType = BaseQuickAdapter.this.getItemViewType(i);
                    if (itemViewType == 273 && BaseQuickAdapter.this.isHeaderViewAsFlow()) {
                        return 1;
                    }
                    if (itemViewType == 819 && BaseQuickAdapter.this.isFooterViewAsFlow()) {
                        return 1;
                    }
                    if (BaseQuickAdapter.this.mSpanSizeLookup != null) {
                        return BaseQuickAdapter.this.isFixedViewType(itemViewType) ? gridLayoutManager.b() : BaseQuickAdapter.this.mSpanSizeLookup.getSpanSize(gridLayoutManager, i - BaseQuickAdapter.this.getHeaderLayoutCount());
                    }
                    if (BaseQuickAdapter.this.isFixedViewType(itemViewType)) {
                        return gridLayoutManager.b();
                    }
                    return 1;
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public boolean isFixedViewType(int i) {
        return i == 1365 || i == 273 || i == 819 || i == 546;
    }

    public void setHeaderViewAsFlow(boolean z) {
        this.headerViewAsFlow = z;
    }

    public boolean isHeaderViewAsFlow() {
        return this.headerViewAsFlow;
    }

    public void setFooterViewAsFlow(boolean z) {
        this.footerViewAsFlow = z;
    }

    public boolean isFooterViewAsFlow() {
        return this.footerViewAsFlow;
    }

    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        this.mSpanSizeLookup = spanSizeLookup;
    }

    public void onBindViewHolder(K k, int i) {
        autoUpFetch(i);
        autoLoadMore(i);
        switch (k.getItemViewType()) {
            case 0:
                convert(k, getItem(i - getHeaderLayoutCount()));
                return;
            case HEADER_VIEW /*273*/:
            case FOOTER_VIEW /*819*/:
            case EMPTY_VIEW /*1365*/:
                return;
            case LOADING_VIEW /*546*/:
                this.mLoadMoreView.convert(k);
                return;
            default:
                convert(k, getItem(i - getHeaderLayoutCount()));
                return;
        }
    }

    private void bindViewClickListener(final BaseViewHolder baseViewHolder) {
        if (baseViewHolder != null) {
            View view = baseViewHolder.itemView;
            if (view != null) {
                if (getOnItemClickListener() != null) {
                    view.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            BaseQuickAdapter.this.setOnItemClick(view, baseViewHolder.getLayoutPosition() - BaseQuickAdapter.this.getHeaderLayoutCount());
                        }
                    });
                }
                if (getOnItemLongClickListener() != null) {
                    view.setOnLongClickListener(new OnLongClickListener() {
                        public boolean onLongClick(View view) {
                            return BaseQuickAdapter.this.setOnItemLongClick(view, baseViewHolder.getLayoutPosition() - BaseQuickAdapter.this.getHeaderLayoutCount());
                        }
                    });
                }
            }
        }
    }

    public void setOnItemClick(View view, int i) {
        getOnItemClickListener().onItemClick(this, view, i);
    }

    public boolean setOnItemLongClick(View view, int i) {
        return getOnItemLongClickListener().onItemLongClick(this, view, i);
    }

    public void setMultiTypeDelegate(MultiTypeDelegate<T> multiTypeDelegate) {
        this.mMultiTypeDelegate = multiTypeDelegate;
    }

    public MultiTypeDelegate<T> getMultiTypeDelegate() {
        return this.mMultiTypeDelegate;
    }

    /* access modifiers changed from: protected */
    public K onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        int i2 = this.mLayoutResId;
        if (this.mMultiTypeDelegate != null) {
            i2 = this.mMultiTypeDelegate.getLayoutId(i);
        }
        return createBaseViewHolder(viewGroup, i2);
    }

    /* access modifiers changed from: protected */
    public K createBaseViewHolder(ViewGroup viewGroup, int i) {
        return createBaseViewHolder(getItemView(i, viewGroup));
    }

    /* access modifiers changed from: protected */
    public K createBaseViewHolder(View view) {
        K createGenericKInstance;
        Class cls = getClass();
        Class cls2 = null;
        while (cls2 == null && cls != null) {
            cls2 = getInstancedGenericKClass(cls);
            cls = cls.getSuperclass();
        }
        if (cls2 == null) {
            createGenericKInstance = new BaseViewHolder(view);
        } else {
            createGenericKInstance = createGenericKInstance(cls2, view);
        }
        return createGenericKInstance != null ? createGenericKInstance : new BaseViewHolder(view);
    }

    private K createGenericKInstance(Class cls, View view) {
        try {
            if (!cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) {
                Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[]{View.class});
                declaredConstructor.setAccessible(true);
                return (BaseViewHolder) declaredConstructor.newInstance(new Object[]{view});
            }
            Constructor declaredConstructor2 = cls.getDeclaredConstructor(new Class[]{getClass(), View.class});
            declaredConstructor2.setAccessible(true);
            return (BaseViewHolder) declaredConstructor2.newInstance(new Object[]{this, view});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            return null;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    private Class getInstancedGenericKClass(Class cls) {
        Type[] actualTypeArguments;
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            for (Type type : ((ParameterizedType) genericSuperclass).getActualTypeArguments()) {
                if (type instanceof Class) {
                    Class cls2 = (Class) type;
                    if (BaseViewHolder.class.isAssignableFrom(cls2)) {
                        return cls2;
                    }
                } else if (type instanceof ParameterizedType) {
                    Type rawType = ((ParameterizedType) type).getRawType();
                    if ((rawType instanceof Class) && BaseViewHolder.class.isAssignableFrom((Class) rawType)) {
                        return (Class) rawType;
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    public LinearLayout getHeaderLayout() {
        return this.mHeaderLayout;
    }

    public LinearLayout getFooterLayout() {
        return this.mFooterLayout;
    }

    public int addHeaderView(View view) {
        return addHeaderView(view, -1);
    }

    public int addHeaderView(View view, int i) {
        return addHeaderView(view, i, 1);
    }

    public int addHeaderView(View view, int i, int i2) {
        if (this.mHeaderLayout == null) {
            this.mHeaderLayout = new LinearLayout(view.getContext());
            if (i2 == 1) {
                this.mHeaderLayout.setOrientation(1);
                this.mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                this.mHeaderLayout.setOrientation(0);
                this.mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        int childCount = this.mHeaderLayout.getChildCount();
        if (i < 0 || i > childCount) {
            i = childCount;
        }
        this.mHeaderLayout.addView(view, i);
        if (this.mHeaderLayout.getChildCount() == 1) {
            int headerViewPosition = getHeaderViewPosition();
            if (headerViewPosition != -1) {
                notifyItemInserted(headerViewPosition);
            }
        }
        return i;
    }

    public int setHeaderView(View view) {
        return setHeaderView(view, 0, 1);
    }

    public int setHeaderView(View view, int i) {
        return setHeaderView(view, i, 1);
    }

    public int setHeaderView(View view, int i, int i2) {
        if (this.mHeaderLayout == null || this.mHeaderLayout.getChildCount() <= i) {
            return addHeaderView(view, i, i2);
        }
        this.mHeaderLayout.removeViewAt(i);
        this.mHeaderLayout.addView(view, i);
        return i;
    }

    public int addFooterView(View view) {
        return addFooterView(view, -1, 1);
    }

    public int addFooterView(View view, int i) {
        return addFooterView(view, i, 1);
    }

    public int addFooterView(View view, int i, int i2) {
        if (this.mFooterLayout == null) {
            this.mFooterLayout = new LinearLayout(view.getContext());
            if (i2 == 1) {
                this.mFooterLayout.setOrientation(1);
                this.mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                this.mFooterLayout.setOrientation(0);
                this.mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        int childCount = this.mFooterLayout.getChildCount();
        if (i < 0 || i > childCount) {
            i = childCount;
        }
        this.mFooterLayout.addView(view, i);
        if (this.mFooterLayout.getChildCount() == 1) {
            int footerViewPosition = getFooterViewPosition();
            if (footerViewPosition != -1) {
                notifyItemInserted(footerViewPosition);
            }
        }
        return i;
    }

    public int setFooterView(View view) {
        return setFooterView(view, 0, 1);
    }

    public int setFooterView(View view, int i) {
        return setFooterView(view, i, 1);
    }

    public int setFooterView(View view, int i, int i2) {
        if (this.mFooterLayout == null || this.mFooterLayout.getChildCount() <= i) {
            return addFooterView(view, i, i2);
        }
        this.mFooterLayout.removeViewAt(i);
        this.mFooterLayout.addView(view, i);
        return i;
    }

    public void removeHeaderView(View view) {
        if (getHeaderLayoutCount() != 0) {
            this.mHeaderLayout.removeView(view);
            if (this.mHeaderLayout.getChildCount() == 0) {
                int headerViewPosition = getHeaderViewPosition();
                if (headerViewPosition != -1) {
                    notifyItemRemoved(headerViewPosition);
                }
            }
        }
    }

    public void removeFooterView(View view) {
        if (getFooterLayoutCount() != 0) {
            this.mFooterLayout.removeView(view);
            if (this.mFooterLayout.getChildCount() == 0) {
                int footerViewPosition = getFooterViewPosition();
                if (footerViewPosition != -1) {
                    notifyItemRemoved(footerViewPosition);
                }
            }
        }
    }

    public void removeAllHeaderView() {
        if (getHeaderLayoutCount() != 0) {
            this.mHeaderLayout.removeAllViews();
            int headerViewPosition = getHeaderViewPosition();
            if (headerViewPosition != -1) {
                notifyItemRemoved(headerViewPosition);
            }
        }
    }

    public void removeAllFooterView() {
        if (getFooterLayoutCount() != 0) {
            this.mFooterLayout.removeAllViews();
            int footerViewPosition = getFooterViewPosition();
            if (footerViewPosition != -1) {
                notifyItemRemoved(footerViewPosition);
            }
        }
    }

    private int getHeaderViewPosition() {
        if (getEmptyViewCount() != 1 || this.mHeadAndEmptyEnable) {
            return 0;
        }
        return -1;
    }

    private int getFooterViewPosition() {
        int i = 1;
        if (getEmptyViewCount() != 1) {
            return getHeaderLayoutCount() + this.mData.size();
        }
        if (this.mHeadAndEmptyEnable && getHeaderLayoutCount() != 0) {
            i = 2;
        }
        if (this.mFootAndEmptyEnable) {
            return i;
        }
        return -1;
    }

    public void setEmptyView(int i, ViewGroup viewGroup) {
        setEmptyView(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
    }

    @Deprecated
    public void setEmptyView(int i) {
        checkNotNull();
        setEmptyView(i, getRecyclerView());
    }

    public void setEmptyView(View view) {
        boolean z;
        int i = 0;
        if (this.mEmptyLayout == null) {
            this.mEmptyLayout = new FrameLayout(view.getContext());
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(-1, -1);
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            if (layoutParams2 != null) {
                layoutParams.width = layoutParams2.width;
                layoutParams.height = layoutParams2.height;
            }
            this.mEmptyLayout.setLayoutParams(layoutParams);
            z = true;
        } else {
            z = false;
        }
        this.mEmptyLayout.removeAllViews();
        this.mEmptyLayout.addView(view);
        this.mIsUseEmpty = true;
        if (z && getEmptyViewCount() == 1) {
            if (this.mHeadAndEmptyEnable && getHeaderLayoutCount() != 0) {
                i = 1;
            }
            notifyItemInserted(i);
        }
    }

    public void setHeaderAndEmpty(boolean z) {
        setHeaderFooterEmpty(z, false);
    }

    public void setHeaderFooterEmpty(boolean z, boolean z2) {
        this.mHeadAndEmptyEnable = z;
        this.mFootAndEmptyEnable = z2;
    }

    public void isUseEmpty(boolean z) {
        this.mIsUseEmpty = z;
    }

    public View getEmptyView() {
        return this.mEmptyLayout;
    }

    @Deprecated
    public void setAutoLoadMoreSize(int i) {
        setPreLoadNumber(i);
    }

    public void setPreLoadNumber(int i) {
        if (i > 1) {
            this.mPreLoadNumber = i;
        }
    }

    private void autoLoadMore(int i) {
        if (getLoadMoreViewCount() != 0 && i >= getItemCount() - this.mPreLoadNumber && this.mLoadMoreView.getLoadMoreStatus() == 1) {
            this.mLoadMoreView.setLoadMoreStatus(2);
            if (!this.mLoading) {
                this.mLoading = true;
                if (getRecyclerView() != null) {
                    getRecyclerView().post(new Runnable() {
                        public void run() {
                            BaseQuickAdapter.this.mRequestLoadMoreListener.onLoadMoreRequested();
                        }
                    });
                } else {
                    this.mRequestLoadMoreListener.onLoadMoreRequested();
                }
            }
        }
    }

    private void addAnimation(v vVar) {
        BaseAnimation baseAnimation;
        if (!this.mOpenAnimationEnable) {
            return;
        }
        if (!this.mFirstOnlyEnable || vVar.getLayoutPosition() > this.mLastPosition) {
            if (this.mCustomAnimation != null) {
                baseAnimation = this.mCustomAnimation;
            } else {
                baseAnimation = this.mSelectAnimation;
            }
            for (Animator startAnim : baseAnimation.getAnimators(vVar.itemView)) {
                startAnim(startAnim, vVar.getLayoutPosition());
            }
            this.mLastPosition = vVar.getLayoutPosition();
        }
    }

    /* access modifiers changed from: protected */
    public void startAnim(Animator animator, int i) {
        animator.setDuration((long) this.mDuration).start();
        animator.setInterpolator(this.mInterpolator);
    }

    /* access modifiers changed from: protected */
    public View getItemView(int i, ViewGroup viewGroup) {
        return this.mLayoutInflater.inflate(i, viewGroup, false);
    }

    public void openLoadAnimation(int i) {
        this.mOpenAnimationEnable = true;
        this.mCustomAnimation = null;
        switch (i) {
            case 1:
                this.mSelectAnimation = new AlphaInAnimation();
                return;
            case 2:
                this.mSelectAnimation = new ScaleInAnimation();
                return;
            case 3:
                this.mSelectAnimation = new SlideInBottomAnimation();
                return;
            case 4:
                this.mSelectAnimation = new SlideInLeftAnimation();
                return;
            case 5:
                this.mSelectAnimation = new SlideInRightAnimation();
                return;
            default:
                return;
        }
    }

    public void openLoadAnimation(BaseAnimation baseAnimation) {
        this.mOpenAnimationEnable = true;
        this.mCustomAnimation = baseAnimation;
    }

    public void openLoadAnimation() {
        this.mOpenAnimationEnable = true;
    }

    public void closeLoadAnimation() {
        this.mOpenAnimationEnable = false;
    }

    public void isFirstOnly(boolean z) {
        this.mFirstOnlyEnable = z;
    }

    public View getViewByPosition(int i, int i2) {
        checkNotNull();
        return getViewByPosition(getRecyclerView(), i, i2);
    }

    public View getViewByPosition(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView == null) {
            return null;
        }
        BaseViewHolder baseViewHolder = (BaseViewHolder) recyclerView.c(i);
        if (baseViewHolder == null) {
            return null;
        }
        return baseViewHolder.getView(i2);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    private int recursiveExpand(int i, List list) {
        int i2;
        int size = (list.size() + i) - 1;
        int size2 = list.size();
        int size3 = list.size() - 1;
        while (size3 >= 0) {
            if (list.get(size3) instanceof IExpandable) {
                IExpandable iExpandable = (IExpandable) list.get(size3);
                if (iExpandable.isExpanded() && hasSubItems(iExpandable)) {
                    List subItems = iExpandable.getSubItems();
                    this.mData.addAll(size + 1, subItems);
                    i2 = recursiveExpand(size + 1, subItems) + size2;
                    size--;
                    size3--;
                    size2 = i2;
                }
            }
            i2 = size2;
            size--;
            size3--;
            size2 = i2;
        }
        return size2;
    }

    public int expand(int i, boolean z, boolean z2) {
        int i2 = 0;
        int headerLayoutCount = i - getHeaderLayoutCount();
        IExpandable expandableItem = getExpandableItem(headerLayoutCount);
        if (expandableItem != null) {
            if (!hasSubItems(expandableItem)) {
                expandableItem.setExpanded(true);
                notifyItemChanged(headerLayoutCount);
            } else {
                if (!expandableItem.isExpanded()) {
                    List subItems = expandableItem.getSubItems();
                    this.mData.addAll(headerLayoutCount + 1, subItems);
                    i2 = 0 + recursiveExpand(headerLayoutCount + 1, subItems);
                    expandableItem.setExpanded(true);
                }
                int headerLayoutCount2 = headerLayoutCount + getHeaderLayoutCount();
                if (z2) {
                    if (z) {
                        notifyItemChanged(headerLayoutCount2);
                        notifyItemRangeInserted(headerLayoutCount2 + 1, i2);
                    } else {
                        notifyDataSetChanged();
                    }
                }
            }
        }
        return i2;
    }

    public int expand(int i, boolean z) {
        return expand(i, z, true);
    }

    public int expand(int i) {
        return expand(i, true, true);
    }

    public int expandAll(int i, boolean z, boolean z2) {
        int headerLayoutCount = i - getHeaderLayoutCount();
        Object obj = null;
        if (headerLayoutCount + 1 < this.mData.size()) {
            obj = getItem(headerLayoutCount + 1);
        }
        IExpandable expandableItem = getExpandableItem(headerLayoutCount);
        if (expandableItem == null) {
            return 0;
        }
        if (!hasSubItems(expandableItem)) {
            expandableItem.setExpanded(true);
            notifyItemChanged(headerLayoutCount);
            return 0;
        }
        int expand = expand(getHeaderLayoutCount() + headerLayoutCount, false, false);
        for (int i2 = headerLayoutCount + 1; i2 < this.mData.size(); i2++) {
            Object item = getItem(i2);
            if (item == obj) {
                break;
            }
            if (isExpandable(item)) {
                expand += expand(getHeaderLayoutCount() + i2, false, false);
            }
        }
        if (!z2) {
            return expand;
        }
        if (z) {
            notifyItemRangeInserted(getHeaderLayoutCount() + headerLayoutCount + 1, expand);
            return expand;
        }
        notifyDataSetChanged();
        return expand;
    }

    public int expandAll(int i, boolean z) {
        return expandAll(i, true, !z);
    }

    public void expandAll() {
        for (int size = (this.mData.size() - 1) + getHeaderLayoutCount(); size >= getHeaderLayoutCount(); size--) {
            expandAll(size, false, false);
        }
    }

    private int recursiveCollapse(int i) {
        int i2;
        Object item = getItem(i);
        if (!isExpandable(item)) {
            return 0;
        }
        IExpandable iExpandable = (IExpandable) item;
        if (iExpandable.isExpanded()) {
            List subItems = iExpandable.getSubItems();
            if (subItems == null) {
                return 0;
            }
            i2 = 0;
            for (int size = subItems.size() - 1; size >= 0; size--) {
                Object obj = subItems.get(size);
                int itemPosition = getItemPosition(obj);
                if (itemPosition >= 0) {
                    if (obj instanceof IExpandable) {
                        i2 += recursiveCollapse(itemPosition);
                    }
                    this.mData.remove(itemPosition);
                    i2++;
                }
            }
        } else {
            i2 = 0;
        }
        return i2;
    }

    public int collapse(int i, boolean z, boolean z2) {
        int headerLayoutCount = i - getHeaderLayoutCount();
        IExpandable expandableItem = getExpandableItem(headerLayoutCount);
        if (expandableItem == null) {
            return 0;
        }
        int recursiveCollapse = recursiveCollapse(headerLayoutCount);
        expandableItem.setExpanded(false);
        int headerLayoutCount2 = getHeaderLayoutCount() + headerLayoutCount;
        if (z2) {
            if (z) {
                notifyItemChanged(headerLayoutCount2);
                notifyItemRangeRemoved(headerLayoutCount2 + 1, recursiveCollapse);
            } else {
                notifyDataSetChanged();
            }
        }
        return recursiveCollapse;
    }

    public int collapse(int i) {
        return collapse(i, true, true);
    }

    public int collapse(int i, boolean z) {
        return collapse(i, z, true);
    }

    private int getItemPosition(T t) {
        if (t == null || this.mData == null || this.mData.isEmpty()) {
            return -1;
        }
        return this.mData.indexOf(t);
    }

    private boolean hasSubItems(IExpandable iExpandable) {
        if (iExpandable == null) {
            return false;
        }
        List subItems = iExpandable.getSubItems();
        if (subItems == null || subItems.size() <= 0) {
            return false;
        }
        return true;
    }

    public boolean isExpandable(T t) {
        return t != null && (t instanceof IExpandable);
    }

    private IExpandable getExpandableItem(int i) {
        Object item = getItem(i);
        if (isExpandable(item)) {
            return (IExpandable) item;
        }
        return null;
    }

    public int getParentPosition(T t) {
        int i;
        int itemPosition = getItemPosition(t);
        if (itemPosition == -1) {
            return -1;
        }
        if (t instanceof IExpandable) {
            i = ((IExpandable) t).getLevel();
        } else {
            i = Integer.MAX_VALUE;
        }
        if (i == 0) {
            return itemPosition;
        }
        if (i == -1) {
            return -1;
        }
        for (int i2 = itemPosition; i2 >= 0; i2--) {
            Object obj = this.mData.get(i2);
            if (obj instanceof IExpandable) {
                IExpandable iExpandable = (IExpandable) obj;
                if (iExpandable.getLevel() >= 0 && iExpandable.getLevel() < i) {
                    return i2;
                }
            }
        }
        return -1;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        this.mOnItemChildClickListener = onItemChildClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemChildLongClickListener(OnItemChildLongClickListener onItemChildLongClickListener) {
        this.mOnItemChildLongClickListener = onItemChildLongClickListener;
    }

    public final OnItemLongClickListener getOnItemLongClickListener() {
        return this.mOnItemLongClickListener;
    }

    public final OnItemClickListener getOnItemClickListener() {
        return this.mOnItemClickListener;
    }

    public final OnItemChildClickListener getOnItemChildClickListener() {
        return this.mOnItemChildClickListener;
    }

    public final OnItemChildLongClickListener getOnItemChildLongClickListener() {
        return this.mOnItemChildLongClickListener;
    }
}
