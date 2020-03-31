package cn.sharesdk.framework.loopshare;

public interface MoblinkActionListener<T> {
    void onError(Throwable th);

    void onResult(T t);
}
