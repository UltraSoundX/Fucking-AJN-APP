package cn.sharesdk.framework.authorize;

import android.app.Activity;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import cn.sharesdk.framework.TitleLayout;

public class AuthorizeAdapter {
    private Activity activity;
    private boolean noTitle;
    private String platform;
    private boolean popUpAnimationDisable;
    private RelativeLayout rlBody;
    private TitleLayout title;
    private WebView webview;

    /* access modifiers changed from: 0000 */
    public void setActivity(Activity activity2) {
        this.activity = activity2;
    }

    public Activity getActivity() {
        return this.activity;
    }

    /* access modifiers changed from: 0000 */
    public void setTitleView(TitleLayout titleLayout) {
        this.title = titleLayout;
    }

    public TitleLayout getTitleLayout() {
        return this.title;
    }

    /* access modifiers changed from: 0000 */
    public void setWebView(WebView webView) {
        this.webview = webView;
    }

    public WebView getWebBody() {
        return this.webview;
    }

    /* access modifiers changed from: 0000 */
    public void setNotitle(boolean z) {
        this.noTitle = z;
    }

    /* access modifiers changed from: 0000 */
    public boolean isNotitle() {
        return this.noTitle;
    }

    /* access modifiers changed from: 0000 */
    public void setPlatformName(String str) {
        this.platform = str;
    }

    public String getPlatformName() {
        return this.platform;
    }

    /* access modifiers changed from: 0000 */
    public void setBodyView(RelativeLayout relativeLayout) {
        this.rlBody = relativeLayout;
    }

    public RelativeLayout getBodyView() {
        return this.rlBody;
    }

    public void onCreate() {
    }

    public void onResize(int i, int i2, int i3, int i4) {
    }

    public void onDestroy() {
    }

    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void disablePopUpAnimation() {
        this.popUpAnimationDisable = true;
    }

    /* access modifiers changed from: 0000 */
    public boolean isPopUpAnimationDisable() {
        return this.popUpAnimationDisable;
    }

    public void onStart() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStop() {
    }

    public void onRestart() {
    }

    public boolean onFinish() {
        return false;
    }

    public void hideShareSDKLogo() {
        getTitleLayout().getChildAt(getTitleLayout().getChildCount() - 1).setVisibility(8);
    }
}
