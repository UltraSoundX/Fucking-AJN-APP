package com.e23.ajn.activity;

import android.os.Bundle;
import com.baidu.mobstat.StatService;
import com.e23.ajn.base.BaseSwipeBackActivity;
import com.stub.StubApp;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class SwipeBackCommonActivity extends BaseSwipeBackActivity {
    public static final int BAOLIAO = 38;
    public static final int CHANNEL_MANAGER = 2;
    public static final int CHILD_CAT = 9;
    public static final int CHILD_INTERACTION = 91;
    public static final int COLLECTION_NEWS_DETAIL = 12;
    public static final String LIST = "LIST";
    public static final int LIVE_DETAIL = 53;
    public static final int MINE_ABOUTUS = 30;
    public static final int MINE_ARTICLES = 31;
    public static final int MINE_CELL = 26;
    public static final int MINE_CLUE = 37;
    public static final int MINE_COMMENT = 28;
    public static final int MINE_EDITME = 22;
    public static final int MINE_LOGIN = 21;
    public static final int MINE_MESSAGE = 29;
    public static final int MINE_POSTS = 33;
    public static final int MINE_REPLY = 34;
    public static final int MINE_SET = 25;
    public static final int MINE_VIEWS = 27;
    public static final int MINE_YQM = 24;
    public static final int NEWS_DETAIL = 3;
    public static final int NEWS_DETAIL_FROM_MINE = 333;
    public static final int OUT_LINK_DETAIL = 13;
    public static final int PAPER_BM_ARTICLE_LIST = 52;
    public static final int PAPER_MAIN = 51;
    public static final int PICS = 7;
    public static final int REPORTER_STATION = 35;
    public static final int REPORTER_STATION_DETAIL = 36;
    public static final int SEARCH = 5;
    public static final String SERIALIZABLE = "SERIALIZABLE";
    public static final int SUBSCRIPTION_CONTENT_LIST = 10;
    public static final String TAG = "TAG";
    public static final String TITLE = "TITLE";
    public static final int TOPIC = 6;
    public static final int TOPICS = 8;
    public static final String URL = "URL";
    public static final int WEB = 1;
    private int c;
    private String d;

    static {
        StubApp.interface11(3501);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public boolean swipeBackPriority() {
        return false;
    }

    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        StatService.onResume(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        StatService.onPause(this);
    }
}
