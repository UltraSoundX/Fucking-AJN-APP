package org.litepal.crud;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.litepal.LitePal;
import org.litepal.crud.async.AverageExecutor;
import org.litepal.crud.async.CountExecutor;
import org.litepal.crud.async.FindExecutor;
import org.litepal.crud.async.FindMultiExecutor;
import org.litepal.crud.async.SaveExecutor;
import org.litepal.crud.async.UpdateOrDeleteExecutor;
import org.litepal.exceptions.DataSupportException;
import org.litepal.tablemanager.Connector;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;

public class DataSupport {
    private Map<String, Set<Long>> associatedModelsMapForJoinTable;
    private Map<String, Set<Long>> associatedModelsMapWithFK;
    private Map<String, Long> associatedModelsMapWithoutFK;
    private long baseObjId;
    private List<String> fieldsToSetToDefault;
    private List<String> listToClearAssociatedFK;
    private List<String> listToClearSelfFK;

    public static synchronized ClusterQuery select(String... strArr) {
        ClusterQuery clusterQuery;
        synchronized (DataSupport.class) {
            clusterQuery = new ClusterQuery();
            clusterQuery.mColumns = strArr;
        }
        return clusterQuery;
    }

    public static synchronized ClusterQuery where(String... strArr) {
        ClusterQuery clusterQuery;
        synchronized (DataSupport.class) {
            clusterQuery = new ClusterQuery();
            clusterQuery.mConditions = strArr;
        }
        return clusterQuery;
    }

    public static synchronized ClusterQuery order(String str) {
        ClusterQuery clusterQuery;
        synchronized (DataSupport.class) {
            clusterQuery = new ClusterQuery();
            clusterQuery.mOrderBy = str;
        }
        return clusterQuery;
    }

    public static synchronized ClusterQuery limit(int i) {
        ClusterQuery clusterQuery;
        synchronized (DataSupport.class) {
            clusterQuery = new ClusterQuery();
            clusterQuery.mLimit = String.valueOf(i);
        }
        return clusterQuery;
    }

    public static synchronized ClusterQuery offset(int i) {
        ClusterQuery clusterQuery;
        synchronized (DataSupport.class) {
            clusterQuery = new ClusterQuery();
            clusterQuery.mOffset = String.valueOf(i);
        }
        return clusterQuery;
    }

    public static synchronized int count(Class<?> cls) {
        int count;
        synchronized (DataSupport.class) {
            count = count(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())));
        }
        return count;
    }

    public static CountExecutor countAsync(Class<?> cls) {
        return countAsync(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())));
    }

    public static synchronized int count(String str) {
        int count;
        synchronized (DataSupport.class) {
            count = new ClusterQuery().count(str);
        }
        return count;
    }

    public static CountExecutor countAsync(final String str) {
        final CountExecutor countExecutor = new CountExecutor();
        countExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final int count = DataSupport.count(str);
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

    public static synchronized double average(Class<?> cls, String str) {
        double average;
        synchronized (DataSupport.class) {
            average = average(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str);
        }
        return average;
    }

    public static AverageExecutor averageAsync(Class<?> cls, String str) {
        return averageAsync(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str);
    }

    public static synchronized double average(String str, String str2) {
        double average;
        synchronized (DataSupport.class) {
            average = new ClusterQuery().average(str, str2);
        }
        return average;
    }

    public static AverageExecutor averageAsync(final String str, final String str2) {
        final AverageExecutor averageExecutor = new AverageExecutor();
        averageExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final double average = DataSupport.average(str, str2);
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

    public static synchronized <T> T max(Class<?> cls, String str, Class<T> cls2) {
        T max;
        synchronized (DataSupport.class) {
            max = max(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str, cls2);
        }
        return max;
    }

    public static <T> FindExecutor maxAsync(Class<?> cls, String str, Class<T> cls2) {
        return maxAsync(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str, cls2);
    }

    public static synchronized <T> T max(String str, String str2, Class<T> cls) {
        T max;
        synchronized (DataSupport.class) {
            max = new ClusterQuery().max(str, str2, cls);
        }
        return max;
    }

    public static <T> FindExecutor maxAsync(final String str, final String str2, final Class<T> cls) {
        final FindExecutor findExecutor = new FindExecutor();
        findExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final Object max = DataSupport.max(str, str2, cls);
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

    public static synchronized <T> T min(Class<?> cls, String str, Class<T> cls2) {
        T min;
        synchronized (DataSupport.class) {
            min = min(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str, cls2);
        }
        return min;
    }

    public static <T> FindExecutor minAsync(Class<?> cls, String str, Class<T> cls2) {
        return minAsync(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str, cls2);
    }

    public static synchronized <T> T min(String str, String str2, Class<T> cls) {
        T min;
        synchronized (DataSupport.class) {
            min = new ClusterQuery().min(str, str2, cls);
        }
        return min;
    }

    public static <T> FindExecutor minAsync(final String str, final String str2, final Class<T> cls) {
        final FindExecutor findExecutor = new FindExecutor();
        findExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final Object min = DataSupport.min(str, str2, cls);
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

    public static synchronized <T> T sum(Class<?> cls, String str, Class<T> cls2) {
        T sum;
        synchronized (DataSupport.class) {
            sum = sum(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str, cls2);
        }
        return sum;
    }

    public static <T> FindExecutor sumAsync(Class<?> cls, String str, Class<T> cls2) {
        return sumAsync(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str, cls2);
    }

    public static synchronized <T> T sum(String str, String str2, Class<T> cls) {
        T sum;
        synchronized (DataSupport.class) {
            sum = new ClusterQuery().sum(str, str2, cls);
        }
        return sum;
    }

    public static <T> FindExecutor sumAsync(final String str, final String str2, final Class<T> cls) {
        final FindExecutor findExecutor = new FindExecutor();
        findExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final Object sum = DataSupport.sum(str, str2, cls);
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

    public static synchronized <T> T find(Class<T> cls, long j) {
        T find;
        synchronized (DataSupport.class) {
            find = find(cls, j, false);
        }
        return find;
    }

    public static <T> FindExecutor findAsync(Class<T> cls, long j) {
        return findAsync(cls, j, false);
    }

    public static synchronized <T> T find(Class<T> cls, long j, boolean z) {
        T onFind;
        synchronized (DataSupport.class) {
            onFind = new QueryHandler(Connector.getDatabase()).onFind(cls, j, z);
        }
        return onFind;
    }

    public static <T> FindExecutor findAsync(Class<T> cls, long j, boolean z) {
        final FindExecutor findExecutor = new FindExecutor();
        final Class<T> cls2 = cls;
        final long j2 = j;
        final boolean z2 = z;
        findExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final Object find = DataSupport.find(cls2, j2, z2);
                    if (findExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                findExecutor.getListener().onFinish(find);
                            }
                        });
                    }
                }
            }
        });
        return findExecutor;
    }

    public static synchronized <T> T findFirst(Class<T> cls) {
        T findFirst;
        synchronized (DataSupport.class) {
            findFirst = findFirst(cls, false);
        }
        return findFirst;
    }

    public static <T> FindExecutor findFirstAsync(Class<T> cls) {
        return findFirstAsync(cls, false);
    }

    public static synchronized <T> T findFirst(Class<T> cls, boolean z) {
        T onFindFirst;
        synchronized (DataSupport.class) {
            onFindFirst = new QueryHandler(Connector.getDatabase()).onFindFirst(cls, z);
        }
        return onFindFirst;
    }

    public static <T> FindExecutor findFirstAsync(final Class<T> cls, final boolean z) {
        final FindExecutor findExecutor = new FindExecutor();
        findExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final Object findFirst = DataSupport.findFirst(cls, z);
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

    public static synchronized <T> T findLast(Class<T> cls) {
        T findLast;
        synchronized (DataSupport.class) {
            findLast = findLast(cls, false);
        }
        return findLast;
    }

    public static <T> FindExecutor findLastAsync(Class<T> cls) {
        return findLastAsync(cls, false);
    }

    public static synchronized <T> T findLast(Class<T> cls, boolean z) {
        T onFindLast;
        synchronized (DataSupport.class) {
            onFindLast = new QueryHandler(Connector.getDatabase()).onFindLast(cls, z);
        }
        return onFindLast;
    }

    public static <T> FindExecutor findLastAsync(final Class<T> cls, final boolean z) {
        final FindExecutor findExecutor = new FindExecutor();
        findExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final Object findLast = DataSupport.findLast(cls, z);
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

    public static synchronized <T> List<T> findAll(Class<T> cls, long... jArr) {
        List<T> findAll;
        synchronized (DataSupport.class) {
            findAll = findAll(cls, false, jArr);
        }
        return findAll;
    }

    public static <T> FindMultiExecutor findAllAsync(Class<T> cls, long... jArr) {
        return findAllAsync(cls, false, jArr);
    }

    public static synchronized <T> List<T> findAll(Class<T> cls, boolean z, long... jArr) {
        List<T> onFindAll;
        synchronized (DataSupport.class) {
            onFindAll = new QueryHandler(Connector.getDatabase()).onFindAll(cls, z, jArr);
        }
        return onFindAll;
    }

    public static <T> FindMultiExecutor findAllAsync(final Class<T> cls, final boolean z, final long... jArr) {
        final FindMultiExecutor findMultiExecutor = new FindMultiExecutor();
        findMultiExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final List findAll = DataSupport.findAll(cls, z, jArr);
                    if (findMultiExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                findMultiExecutor.getListener().onFinish(findAll);
                            }
                        });
                    }
                }
            }
        });
        return findMultiExecutor;
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Object, java.lang.String[]] */
    /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.String[]] */
    /* JADX WARNING: type inference failed for: r0v6, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v7, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], android.database.Cursor, java.lang.String[]]
  uses: [android.database.Cursor, java.lang.String[], java.lang.Object]
  mth insns count: 25
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized android.database.Cursor findBySQL(java.lang.String... r5) {
        /*
            r3 = 1
            r0 = 0
            java.lang.Class<org.litepal.crud.DataSupport> r1 = org.litepal.crud.DataSupport.class
            monitor-enter(r1)
            org.litepal.util.BaseUtility.checkConditionsCorrect(r5)     // Catch:{ all -> 0x002c }
            if (r5 != 0) goto L_0x000c
        L_0x000a:
            monitor-exit(r1)
            return r0
        L_0x000c:
            int r2 = r5.length     // Catch:{ all -> 0x002c }
            if (r2 <= 0) goto L_0x000a
            int r2 = r5.length     // Catch:{ all -> 0x002c }
            if (r2 != r3) goto L_0x001e
        L_0x0012:
            android.database.sqlite.SQLiteDatabase r2 = org.litepal.tablemanager.Connector.getDatabase()     // Catch:{ all -> 0x002c }
            r3 = 0
            r3 = r5[r3]     // Catch:{ all -> 0x002c }
            android.database.Cursor r0 = r2.rawQuery(r3, r0)     // Catch:{ all -> 0x002c }
            goto L_0x000a
        L_0x001e:
            int r0 = r5.length     // Catch:{ all -> 0x002c }
            int r0 = r0 + -1
            java.lang.String[] r0 = new java.lang.String[r0]     // Catch:{ all -> 0x002c }
            r2 = 1
            r3 = 0
            int r4 = r5.length     // Catch:{ all -> 0x002c }
            int r4 = r4 + -1
            java.lang.System.arraycopy(r5, r2, r0, r3, r4)     // Catch:{ all -> 0x002c }
            goto L_0x0012
        L_0x002c:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.crud.DataSupport.findBySQL(java.lang.String[]):android.database.Cursor");
    }

    public static synchronized int delete(Class<?> cls, long j) {
        int onDelete;
        synchronized (DataSupport.class) {
            SQLiteDatabase database = Connector.getDatabase();
            database.beginTransaction();
            try {
                onDelete = new DeleteHandler(database).onDelete(cls, j);
                database.setTransactionSuccessful();
            } finally {
                database.endTransaction();
            }
        }
        return onDelete;
    }

    public static UpdateOrDeleteExecutor deleteAsync(final Class<?> cls, final long j) {
        final UpdateOrDeleteExecutor updateOrDeleteExecutor = new UpdateOrDeleteExecutor();
        updateOrDeleteExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final int delete = DataSupport.delete(cls, j);
                    if (updateOrDeleteExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                updateOrDeleteExecutor.getListener().onFinish(delete);
                            }
                        });
                    }
                }
            }
        });
        return updateOrDeleteExecutor;
    }

    public static synchronized int deleteAll(Class<?> cls, String... strArr) {
        int onDeleteAll;
        synchronized (DataSupport.class) {
            onDeleteAll = new DeleteHandler(Connector.getDatabase()).onDeleteAll(cls, strArr);
        }
        return onDeleteAll;
    }

    public static UpdateOrDeleteExecutor deleteAllAsync(final Class<?> cls, final String... strArr) {
        final UpdateOrDeleteExecutor updateOrDeleteExecutor = new UpdateOrDeleteExecutor();
        updateOrDeleteExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final int deleteAll = DataSupport.deleteAll(cls, strArr);
                    if (updateOrDeleteExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                updateOrDeleteExecutor.getListener().onFinish(deleteAll);
                            }
                        });
                    }
                }
            }
        });
        return updateOrDeleteExecutor;
    }

    public static synchronized int deleteAll(String str, String... strArr) {
        int onDeleteAll;
        synchronized (DataSupport.class) {
            onDeleteAll = new DeleteHandler(Connector.getDatabase()).onDeleteAll(str, strArr);
        }
        return onDeleteAll;
    }

    public static UpdateOrDeleteExecutor deleteAllAsync(final String str, final String... strArr) {
        final UpdateOrDeleteExecutor updateOrDeleteExecutor = new UpdateOrDeleteExecutor();
        updateOrDeleteExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final int deleteAll = DataSupport.deleteAll(str, strArr);
                    if (updateOrDeleteExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                updateOrDeleteExecutor.getListener().onFinish(deleteAll);
                            }
                        });
                    }
                }
            }
        });
        return updateOrDeleteExecutor;
    }

    public static synchronized int update(Class<?> cls, ContentValues contentValues, long j) {
        int onUpdate;
        synchronized (DataSupport.class) {
            onUpdate = new UpdateHandler(Connector.getDatabase()).onUpdate(cls, j, contentValues);
        }
        return onUpdate;
    }

    public static UpdateOrDeleteExecutor updateAsync(Class<?> cls, ContentValues contentValues, long j) {
        final UpdateOrDeleteExecutor updateOrDeleteExecutor = new UpdateOrDeleteExecutor();
        final Class<?> cls2 = cls;
        final ContentValues contentValues2 = contentValues;
        final long j2 = j;
        updateOrDeleteExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final int update = DataSupport.update(cls2, contentValues2, j2);
                    if (updateOrDeleteExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                updateOrDeleteExecutor.getListener().onFinish(update);
                            }
                        });
                    }
                }
            }
        });
        return updateOrDeleteExecutor;
    }

    public static synchronized int updateAll(Class<?> cls, ContentValues contentValues, String... strArr) {
        int updateAll;
        synchronized (DataSupport.class) {
            updateAll = updateAll(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), contentValues, strArr);
        }
        return updateAll;
    }

    public static UpdateOrDeleteExecutor updateAllAsync(Class<?> cls, ContentValues contentValues, String... strArr) {
        return updateAllAsync(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), contentValues, strArr);
    }

    public static synchronized int updateAll(String str, ContentValues contentValues, String... strArr) {
        int onUpdateAll;
        synchronized (DataSupport.class) {
            onUpdateAll = new UpdateHandler(Connector.getDatabase()).onUpdateAll(str, contentValues, strArr);
        }
        return onUpdateAll;
    }

    public static UpdateOrDeleteExecutor updateAllAsync(final String str, final ContentValues contentValues, final String... strArr) {
        final UpdateOrDeleteExecutor updateOrDeleteExecutor = new UpdateOrDeleteExecutor();
        updateOrDeleteExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final int updateAll = DataSupport.updateAll(str, contentValues, strArr);
                    if (updateOrDeleteExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                updateOrDeleteExecutor.getListener().onFinish(updateAll);
                            }
                        });
                    }
                }
            }
        });
        return updateOrDeleteExecutor;
    }

    public static synchronized <T extends DataSupport> void saveAll(Collection<T> collection) {
        synchronized (DataSupport.class) {
            SQLiteDatabase database = Connector.getDatabase();
            database.beginTransaction();
            try {
                new SaveHandler(database).onSaveAll(collection);
                database.setTransactionSuccessful();
                database.endTransaction();
            } catch (Exception e) {
                throw new DataSupportException(e.getMessage(), e);
            } catch (Throwable th) {
                database.endTransaction();
                throw th;
            }
        }
    }

    public static <T extends DataSupport> SaveExecutor saveAllAsync(final Collection<T> collection) {
        final SaveExecutor saveExecutor = new SaveExecutor();
        saveExecutor.submit(new Runnable() {
            public void run() {
                final boolean z;
                synchronized (DataSupport.class) {
                    try {
                        DataSupport.saveAll(collection);
                        z = true;
                    } catch (Exception e) {
                        z = false;
                    }
                    if (saveExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                saveExecutor.getListener().onFinish(z);
                            }
                        });
                    }
                }
            }
        });
        return saveExecutor;
    }

    public static <T extends DataSupport> void markAsDeleted(Collection<T> collection) {
        for (T clearSavedState : collection) {
            clearSavedState.clearSavedState();
        }
    }

    public static <T> boolean isExist(Class<T> cls, String... strArr) {
        if (strArr != null && where(strArr).count(cls) > 0) {
            return true;
        }
        return false;
    }

    public synchronized int delete() {
        int onDelete;
        SQLiteDatabase database = Connector.getDatabase();
        database.beginTransaction();
        try {
            onDelete = new DeleteHandler(database).onDelete(this);
            this.baseObjId = 0;
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
        return onDelete;
    }

    public UpdateOrDeleteExecutor deleteAsync() {
        final UpdateOrDeleteExecutor updateOrDeleteExecutor = new UpdateOrDeleteExecutor();
        updateOrDeleteExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final int delete = DataSupport.this.delete();
                    if (updateOrDeleteExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                updateOrDeleteExecutor.getListener().onFinish(delete);
                            }
                        });
                    }
                }
            }
        });
        return updateOrDeleteExecutor;
    }

    public synchronized int update(long j) {
        int onUpdate;
        try {
            onUpdate = new UpdateHandler(Connector.getDatabase()).onUpdate(this, j);
            getFieldsToSetToDefault().clear();
        } catch (Exception e) {
            throw new DataSupportException(e.getMessage(), e);
        }
        return onUpdate;
    }

    public UpdateOrDeleteExecutor updateAsync(final long j) {
        final UpdateOrDeleteExecutor updateOrDeleteExecutor = new UpdateOrDeleteExecutor();
        updateOrDeleteExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final int update = DataSupport.this.update(j);
                    if (updateOrDeleteExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                updateOrDeleteExecutor.getListener().onFinish(update);
                            }
                        });
                    }
                }
            }
        });
        return updateOrDeleteExecutor;
    }

    public synchronized int updateAll(String... strArr) {
        int onUpdateAll;
        try {
            onUpdateAll = new UpdateHandler(Connector.getDatabase()).onUpdateAll(this, strArr);
            getFieldsToSetToDefault().clear();
        } catch (Exception e) {
            throw new DataSupportException(e.getMessage(), e);
        }
        return onUpdateAll;
    }

    public UpdateOrDeleteExecutor updateAllAsync(final String... strArr) {
        final UpdateOrDeleteExecutor updateOrDeleteExecutor = new UpdateOrDeleteExecutor();
        updateOrDeleteExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final int updateAll = DataSupport.this.updateAll(strArr);
                    if (updateOrDeleteExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                updateOrDeleteExecutor.getListener().onFinish(updateAll);
                            }
                        });
                    }
                }
            }
        });
        return updateOrDeleteExecutor;
    }

    public synchronized boolean save() {
        boolean z;
        try {
            saveThrows();
            z = true;
        } catch (Exception e) {
            e.printStackTrace();
            z = false;
        }
        return z;
    }

    public SaveExecutor saveAsync() {
        final SaveExecutor saveExecutor = new SaveExecutor();
        saveExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final boolean save = DataSupport.this.save();
                    if (saveExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                saveExecutor.getListener().onFinish(save);
                            }
                        });
                    }
                }
            }
        });
        return saveExecutor;
    }

    public synchronized void saveThrows() {
        SQLiteDatabase database = Connector.getDatabase();
        database.beginTransaction();
        try {
            new SaveHandler(database).onSave(this);
            clearAssociatedData();
            database.setTransactionSuccessful();
            database.endTransaction();
        } catch (Exception e) {
            throw new DataSupportException(e.getMessage(), e);
        } catch (Throwable th) {
            database.endTransaction();
            throw th;
        }
    }

    @Deprecated
    public synchronized boolean saveIfNotExist(String... strArr) {
        boolean z;
        if (!isExist(getClass(), strArr)) {
            z = save();
        } else {
            z = false;
        }
        return z;
    }

    public synchronized boolean saveOrUpdate(String... strArr) {
        boolean z;
        if (strArr == null) {
            z = save();
        } else {
            List<DataSupport> find = where(strArr).find(getClass());
            if (find.isEmpty()) {
                z = save();
            } else {
                SQLiteDatabase database = Connector.getDatabase();
                database.beginTransaction();
                try {
                    for (DataSupport baseObjId2 : find) {
                        this.baseObjId = baseObjId2.getBaseObjId();
                        new SaveHandler(database).onSave(this);
                        clearAssociatedData();
                    }
                    database.setTransactionSuccessful();
                    z = true;
                    database.endTransaction();
                } catch (Exception e) {
                    e.printStackTrace();
                    z = false;
                    database.endTransaction();
                } catch (Throwable th) {
                    database.endTransaction();
                    throw th;
                }
            }
        }
        return z;
    }

    public SaveExecutor saveOrUpdateAsync(final String... strArr) {
        final SaveExecutor saveExecutor = new SaveExecutor();
        saveExecutor.submit(new Runnable() {
            public void run() {
                synchronized (DataSupport.class) {
                    final boolean saveOrUpdate = DataSupport.this.saveOrUpdate(strArr);
                    if (saveExecutor.getListener() != null) {
                        LitePal.getHandler().post(new Runnable() {
                            public void run() {
                                saveExecutor.getListener().onFinish(saveOrUpdate);
                            }
                        });
                    }
                }
            }
        });
        return saveExecutor;
    }

    @Deprecated
    public synchronized boolean saveFast() {
        boolean z;
        SQLiteDatabase database = Connector.getDatabase();
        database.beginTransaction();
        try {
            new SaveHandler(database).onSaveFast(this);
            database.setTransactionSuccessful();
            z = true;
        } catch (Exception e) {
            e.printStackTrace();
            z = false;
        } finally {
            database.endTransaction();
        }
        return z;
    }

    public boolean isSaved() {
        return this.baseObjId > 0;
    }

    public void clearSavedState() {
        this.baseObjId = 0;
    }

    public void setToDefault(String str) {
        getFieldsToSetToDefault().add(str);
    }

    public void assignBaseObjId(int i) {
        this.baseObjId = (long) i;
    }

    protected DataSupport() {
    }

    /* access modifiers changed from: protected */
    public long getBaseObjId() {
        return this.baseObjId;
    }

    /* access modifiers changed from: protected */
    public String getClassName() {
        return getClass().getName();
    }

    /* access modifiers changed from: protected */
    public String getTableName() {
        return BaseUtility.changeCase(DBUtility.getTableNameByClassName(getClassName()));
    }

    /* access modifiers changed from: 0000 */
    public List<String> getFieldsToSetToDefault() {
        if (this.fieldsToSetToDefault == null) {
            this.fieldsToSetToDefault = new ArrayList();
        }
        return this.fieldsToSetToDefault;
    }

    /* access modifiers changed from: 0000 */
    public void addAssociatedModelWithFK(String str, long j) {
        Set set = (Set) getAssociatedModelsMapWithFK().get(str);
        if (set == null) {
            HashSet hashSet = new HashSet();
            hashSet.add(Long.valueOf(j));
            this.associatedModelsMapWithFK.put(str, hashSet);
            return;
        }
        set.add(Long.valueOf(j));
    }

    /* access modifiers changed from: 0000 */
    public Map<String, Set<Long>> getAssociatedModelsMapWithFK() {
        if (this.associatedModelsMapWithFK == null) {
            this.associatedModelsMapWithFK = new HashMap();
        }
        return this.associatedModelsMapWithFK;
    }

    /* access modifiers changed from: 0000 */
    public void addAssociatedModelForJoinTable(String str, long j) {
        Set set = (Set) getAssociatedModelsMapForJoinTable().get(str);
        if (set == null) {
            HashSet hashSet = new HashSet();
            hashSet.add(Long.valueOf(j));
            this.associatedModelsMapForJoinTable.put(str, hashSet);
            return;
        }
        set.add(Long.valueOf(j));
    }

    /* access modifiers changed from: 0000 */
    public void addEmptyModelForJoinTable(String str) {
        if (((Set) getAssociatedModelsMapForJoinTable().get(str)) == null) {
            this.associatedModelsMapForJoinTable.put(str, new HashSet());
        }
    }

    /* access modifiers changed from: 0000 */
    public Map<String, Set<Long>> getAssociatedModelsMapForJoinTable() {
        if (this.associatedModelsMapForJoinTable == null) {
            this.associatedModelsMapForJoinTable = new HashMap();
        }
        return this.associatedModelsMapForJoinTable;
    }

    /* access modifiers changed from: 0000 */
    public void addAssociatedModelWithoutFK(String str, long j) {
        getAssociatedModelsMapWithoutFK().put(str, Long.valueOf(j));
    }

    /* access modifiers changed from: 0000 */
    public Map<String, Long> getAssociatedModelsMapWithoutFK() {
        if (this.associatedModelsMapWithoutFK == null) {
            this.associatedModelsMapWithoutFK = new HashMap();
        }
        return this.associatedModelsMapWithoutFK;
    }

    /* access modifiers changed from: 0000 */
    public void addFKNameToClearSelf(String str) {
        List listToClearSelfFK2 = getListToClearSelfFK();
        if (!listToClearSelfFK2.contains(str)) {
            listToClearSelfFK2.add(str);
        }
    }

    /* access modifiers changed from: 0000 */
    public List<String> getListToClearSelfFK() {
        if (this.listToClearSelfFK == null) {
            this.listToClearSelfFK = new ArrayList();
        }
        return this.listToClearSelfFK;
    }

    /* access modifiers changed from: 0000 */
    public void addAssociatedTableNameToClearFK(String str) {
        List listToClearAssociatedFK2 = getListToClearAssociatedFK();
        if (!listToClearAssociatedFK2.contains(str)) {
            listToClearAssociatedFK2.add(str);
        }
    }

    /* access modifiers changed from: 0000 */
    public List<String> getListToClearAssociatedFK() {
        if (this.listToClearAssociatedFK == null) {
            this.listToClearAssociatedFK = new ArrayList();
        }
        return this.listToClearAssociatedFK;
    }

    /* access modifiers changed from: 0000 */
    public void clearAssociatedData() {
        clearIdOfModelWithFK();
        clearIdOfModelWithoutFK();
        clearIdOfModelForJoinTable();
        clearFKNameList();
    }

    private void clearIdOfModelWithFK() {
        for (String str : getAssociatedModelsMapWithFK().keySet()) {
            ((Set) this.associatedModelsMapWithFK.get(str)).clear();
        }
        this.associatedModelsMapWithFK.clear();
    }

    private void clearIdOfModelWithoutFK() {
        getAssociatedModelsMapWithoutFK().clear();
    }

    private void clearIdOfModelForJoinTable() {
        for (String str : getAssociatedModelsMapForJoinTable().keySet()) {
            ((Set) this.associatedModelsMapForJoinTable.get(str)).clear();
        }
        this.associatedModelsMapForJoinTable.clear();
    }

    private void clearFKNameList() {
        getListToClearSelfFK().clear();
        getListToClearAssociatedFK().clear();
    }
}
