package android.support.v4.app;

import android.arch.lifecycle.o;
import java.util.List;

public class FragmentManagerNonConfig {
    private final List<FragmentManagerNonConfig> mChildNonConfigs;
    private final List<Fragment> mFragments;
    private final List<o> mViewModelStores;

    FragmentManagerNonConfig(List<Fragment> list, List<FragmentManagerNonConfig> list2, List<o> list3) {
        this.mFragments = list;
        this.mChildNonConfigs = list2;
        this.mViewModelStores = list3;
    }

    /* access modifiers changed from: 0000 */
    public List<Fragment> getFragments() {
        return this.mFragments;
    }

    /* access modifiers changed from: 0000 */
    public List<FragmentManagerNonConfig> getChildNonConfigs() {
        return this.mChildNonConfigs;
    }

    /* access modifiers changed from: 0000 */
    public List<o> getViewModelStores() {
        return this.mViewModelStores;
    }
}
