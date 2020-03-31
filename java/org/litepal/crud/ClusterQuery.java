package org.litepal.crud;

import com.tencent.mid.sotrage.StorageInterface;
import java.util.List;
import org.litepal.LitePal;
import org.litepal.crud.async.AverageExecutor;
import org.litepal.crud.async.CountExecutor;
import org.litepal.crud.async.FindExecutor;
import org.litepal.crud.async.FindMultiExecutor;
import org.litepal.tablemanager.Connector;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;

public class ClusterQuery {
    String[] mColumns;
    String[] mConditions;
    String mLimit;
    String mOffset;
    String mOrderBy;

    ClusterQuery() {
    }

    public ClusterQuery select(String... strArr) {
        this.mColumns = strArr;
        return this;
    }

    public ClusterQuery where(String... strArr) {
        this.mConditions = strArr;
        return this;
    }

    public ClusterQuery order(String str) {
        this.mOrderBy = str;
        return this;
    }

    public ClusterQuery limit(int i) {
        this.mLimit = String.valueOf(i);
        return this;
    }

    public ClusterQuery offset(int i) {
        this.mOffset = String.valueOf(i);
        return this;
    }

    public <T> List<T> find(Class<T> cls) {
        return find(cls, false);
    }

    public <T> FindMultiExecutor findAsync(Class<T> cls) {
        return findAsync(cls, false);
    }

    public synchronized <T> List<T> find(Class<T> cls, boolean z) {
        QueryHandler queryHandler;
        String str;
        queryHandler = new QueryHandler(Connector.getDatabase());
        if (this.mOffset == null) {
            str = this.mLimit;
        } else {
            if (this.mLimit == null) {
                this.mLimit = "0";
            }
            str = this.mOffset + StorageInterface.KEY_SPLITER + this.mLimit;
        }
        return queryHandler.onFind(cls, this.mColumns, this.mConditions, this.mOrderBy, str, z);
    }

    public <T> FindMultiExecutor findAsync(final Class<T> cls, final boolean z) {
        final FindMultiExecutor findMultiExecutor = new FindMultiExecutor();
        findMultiExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final List find = ClusterQuery.this.find(cls, z);
                    if (findMultiExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                findMultiExecutor.getListener().onFinish(find);
                            }
                        });
                    }
                }
            }
        });
        return findMultiExecutor;
    }

    public <T> T findFirst(Class<T> cls) {
        return findFirst(cls, false);
    }

    public <T> FindExecutor findFirstAsync(Class<T> cls) {
        return findFirstAsync(cls, false);
    }

    public <T> T findFirst(Class<T> cls, boolean z) {
        List find = find(cls, z);
        if (find.size() > 0) {
            return find.get(0);
        }
        return null;
    }

    public <T> FindExecutor findFirstAsync(final Class<T> cls, final boolean z) {
        final FindExecutor findExecutor = new FindExecutor();
        findExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final Object findFirst = ClusterQuery.this.findFirst(cls, z);
                    if (findExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                findExecutor.getListener().onFinish(findFirst);
                            }
                        });
                    }
                }
            }
        });
        return findExecutor;
    }

    public <T> T findLast(Class<T> cls) {
        return findLast(cls, false);
    }

    public <T> FindExecutor findLastAsync(Class<T> cls) {
        return findLastAsync(cls, false);
    }

    public <T> T findLast(Class<T> cls, boolean z) {
        List find = find(cls, z);
        int size = find.size();
        if (size > 0) {
            return find.get(size - 1);
        }
        return null;
    }

    public <T> FindExecutor findLastAsync(final Class<T> cls, final boolean z) {
        final FindExecutor findExecutor = new FindExecutor();
        findExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final Object findLast = ClusterQuery.this.findLast(cls, z);
                    if (findExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                findExecutor.getListener().onFinish(findLast);
                            }
                        });
                    }
                }
            }
        });
        return findExecutor;
    }

    public synchronized int count(Class<?> cls) {
        return count(BaseUtility.changeCase(cls.getSimpleName()));
    }

    public CountExecutor countAsync(Class<?> cls) {
        return countAsync(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())));
    }

    public synchronized int count(String str) {
        return new QueryHandler(Connector.getDatabase()).onCount(str, this.mConditions);
    }

    public CountExecutor countAsync(final String str) {
        final CountExecutor countExecutor = new CountExecutor();
        countExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final int count = ClusterQuery.this.count(str);
                    if (countExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                countExecutor.getListener().onFinish(count);
                            }
                        });
                    }
                }
            }
        });
        return countExecutor;
    }

    public synchronized double average(Class<?> cls, String str) {
        return average(BaseUtility.changeCase(cls.getSimpleName()), str);
    }

    public AverageExecutor averageAsync(Class<?> cls, String str) {
        return averageAsync(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str);
    }

    public synchronized double average(String str, String str2) {
        return new QueryHandler(Connector.getDatabase()).onAverage(str, str2, this.mConditions);
    }

    public AverageExecutor averageAsync(final String str, final String str2) {
        final AverageExecutor averageExecutor = new AverageExecutor();
        averageExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final double average = ClusterQuery.this.average(str, str2);
                    if (averageExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                averageExecutor.getListener().onFinish(average);
                            }
                        });
                    }
                }
            }
        });
        return averageExecutor;
    }

    public synchronized <T> T max(Class<?> cls, String str, Class<T> cls2) {
        return max(BaseUtility.changeCase(cls.getSimpleName()), str, cls2);
    }

    public <T> FindExecutor maxAsync(Class<?> cls, String str, Class<T> cls2) {
        return maxAsync(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str, cls2);
    }

    public synchronized <T> T max(String str, String str2, Class<T> cls) {
        return new QueryHandler(Connector.getDatabase()).onMax(str, str2, this.mConditions, cls);
    }

    public <T> FindExecutor maxAsync(String str, String str2, Class<T> cls) {
        final FindExecutor findExecutor = new FindExecutor();
        final String str3 = str;
        final String str4 = str2;
        final Class<T> cls2 = cls;
        findExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final Object max = ClusterQuery.this.max(str3, str4, cls2);
                    if (findExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                findExecutor.getListener().onFinish(max);
                            }
                        });
                    }
                }
            }
        });
        return findExecutor;
    }

    public synchronized <T> T min(Class<?> cls, String str, Class<T> cls2) {
        return min(BaseUtility.changeCase(cls.getSimpleName()), str, cls2);
    }

    public <T> FindExecutor minAsync(Class<?> cls, String str, Class<T> cls2) {
        return minAsync(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str, cls2);
    }

    public synchronized <T> T min(String str, String str2, Class<T> cls) {
        return new QueryHandler(Connector.getDatabase()).onMin(str, str2, this.mConditions, cls);
    }

    public <T> FindExecutor minAsync(String str, String str2, Class<T> cls) {
        final FindExecutor findExecutor = new FindExecutor();
        final String str3 = str;
        final String str4 = str2;
        final Class<T> cls2 = cls;
        findExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final Object min = ClusterQuery.this.min(str3, str4, cls2);
                    if (findExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                findExecutor.getListener().onFinish(min);
                            }
                        });
                    }
                }
            }
        });
        return findExecutor;
    }

    public synchronized <T> T sum(Class<?> cls, String str, Class<T> cls2) {
        return sum(BaseUtility.changeCase(cls.getSimpleName()), str, cls2);
    }

    public <T> FindExecutor sumAsync(Class<?> cls, String str, Class<T> cls2) {
        return sumAsync(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str, cls2);
    }

    public synchronized <T> T sum(String str, String str2, Class<T> cls) {
        return new QueryHandler(Connector.getDatabase()).onSum(str, str2, this.mConditions, cls);
    }

    public <T> FindExecutor sumAsync(String str, String str2, Class<T> cls) {
        final FindExecutor findExecutor = new FindExecutor();
        final String str3 = str;
        final String str4 = str2;
        final Class<T> cls2 = cls;
        findExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final Object sum = ClusterQuery.this.sum(str3, str4, cls2);
                    if (findExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                findExecutor.getListener().onFinish(sum);
                            }
                        });
                    }
                }
            }
        });
        return findExecutor;
    }
}
