package cn.sharesdk.framework.loopshare;

public interface LoopShareResultListener<T> {
    void onError(Throwable th);

    void onResult(T t);
}
