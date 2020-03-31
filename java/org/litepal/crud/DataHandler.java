package org.litepal.crud;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;
import com.baidu.mobstat.Config;
import com.tencent.android.tpush.SettingsContentProvider;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.litepal.LitePalBase;
import org.litepal.crud.model.AssociationsInfo;
import org.litepal.exceptions.DataSupportException;
import org.litepal.tablemanager.model.GenericModel;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;

abstract class DataHandler extends LitePalBase {
    public static final String TAG = "DataHandler";
    private List<AssociationsInfo> fkInCurrentModel;
    private List<AssociationsInfo> fkInOtherModel;
    SQLiteDatabase mDatabase;
    private DataSupport tempEmptyModel;

    class QueryInfoCache {
        Field field;
        String getMethodName;

        QueryInfoCache() {
        }
    }

    DataHandler() {
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0098  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> java.util.List<T> query(java.lang.Class<T> r16, java.lang.String[] r17, java.lang.String r18, java.lang.String[] r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.util.List<org.litepal.crud.model.AssociationsInfo> r24) {
        /*
            r15 = this;
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            r11 = 0
            java.lang.String r2 = r16.getName()     // Catch:{ Exception -> 0x0089, all -> 0x009c }
            java.util.List r13 = r15.getSupportedFields(r2)     // Catch:{ Exception -> 0x0089, all -> 0x009c }
            java.lang.String r2 = r16.getName()     // Catch:{ Exception -> 0x0089, all -> 0x009c }
            java.util.List r14 = r15.getSupportedGenericFields(r2)     // Catch:{ Exception -> 0x0089, all -> 0x009c }
            r0 = r17
            r1 = r24
            java.lang.String[] r2 = r15.getCustomizedColumns(r0, r14, r1)     // Catch:{ Exception -> 0x0089, all -> 0x009c }
            java.lang.String[] r4 = org.litepal.util.DBUtility.convertSelectClauseToValidNames(r2)     // Catch:{ Exception -> 0x0089, all -> 0x009c }
            java.lang.String r3 = r15.getTableName(r16)     // Catch:{ Exception -> 0x0089, all -> 0x009c }
            android.database.sqlite.SQLiteDatabase r2 = r15.mDatabase     // Catch:{ Exception -> 0x0089, all -> 0x009c }
            r5 = r18
            r6 = r19
            r7 = r20
            r8 = r21
            r9 = r22
            r10 = r23
            android.database.Cursor r6 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x0089, all -> 0x009c }
            boolean r2 = r6.moveToFirst()     // Catch:{ Exception -> 0x009f }
            if (r2 == 0) goto L_0x0083
            android.util.SparseArray r7 = new android.util.SparseArray     // Catch:{ Exception -> 0x009f }
            r7.<init>()     // Catch:{ Exception -> 0x009f }
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ Exception -> 0x009f }
            r8.<init>()     // Catch:{ Exception -> 0x009f }
        L_0x0048:
            java.lang.Object r3 = r15.createInstanceFromClass(r16)     // Catch:{ Exception -> 0x009f }
            r0 = r3
            org.litepal.crud.DataSupport r0 = (org.litepal.crud.DataSupport) r0     // Catch:{ Exception -> 0x009f }
            r2 = r0
            java.lang.String r4 = "id"
            int r4 = r6.getColumnIndexOrThrow(r4)     // Catch:{ Exception -> 0x009f }
            long r4 = r6.getLong(r4)     // Catch:{ Exception -> 0x009f }
            r15.giveBaseObjIdValue(r2, r4)     // Catch:{ Exception -> 0x009f }
            r2 = r15
            r4 = r13
            r5 = r24
            r2.setValueToModel(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x009f }
            r0 = r3
            org.litepal.crud.DataSupport r0 = (org.litepal.crud.DataSupport) r0     // Catch:{ Exception -> 0x009f }
            r2 = r0
            r15.setGenericValueToModel(r2, r14, r8)     // Catch:{ Exception -> 0x009f }
            if (r24 == 0) goto L_0x0074
            r0 = r3
            org.litepal.crud.DataSupport r0 = (org.litepal.crud.DataSupport) r0     // Catch:{ Exception -> 0x009f }
            r2 = r0
            r15.setAssociatedModel(r2)     // Catch:{ Exception -> 0x009f }
        L_0x0074:
            r12.add(r3)     // Catch:{ Exception -> 0x009f }
            boolean r2 = r6.moveToNext()     // Catch:{ Exception -> 0x009f }
            if (r2 != 0) goto L_0x0048
            r7.clear()     // Catch:{ Exception -> 0x009f }
            r8.clear()     // Catch:{ Exception -> 0x009f }
        L_0x0083:
            if (r6 == 0) goto L_0x0088
            r6.close()
        L_0x0088:
            return r12
        L_0x0089:
            r2 = move-exception
            r6 = r11
        L_0x008b:
            org.litepal.exceptions.DataSupportException r3 = new org.litepal.exceptions.DataSupportException     // Catch:{ all -> 0x0095 }
            java.lang.String r4 = r2.getMessage()     // Catch:{ all -> 0x0095 }
            r3.<init>(r4, r2)     // Catch:{ all -> 0x0095 }
            throw r3     // Catch:{ all -> 0x0095 }
        L_0x0095:
            r2 = move-exception
        L_0x0096:
            if (r6 == 0) goto L_0x009b
            r6.close()
        L_0x009b:
            throw r2
        L_0x009c:
            r2 = move-exception
            r6 = r11
            goto L_0x0096
        L_0x009f:
            r2 = move-exception
            goto L_0x008b
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.crud.DataHandler.query(java.lang.Class, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List):java.util.List");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0055  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T mathQuery(java.lang.String r10, java.lang.String[] r11, java.lang.String[] r12, java.lang.Class<T> r13) {
        /*
            r9 = this;
            r8 = 0
            org.litepal.util.BaseUtility.checkConditionsCorrect(r12)
            android.database.sqlite.SQLiteDatabase r0 = r9.mDatabase     // Catch:{ Exception -> 0x0046, all -> 0x0059 }
            java.lang.String r3 = r9.getWhereClause(r12)     // Catch:{ Exception -> 0x0046, all -> 0x0059 }
            java.lang.String[] r4 = r9.getWhereArgs(r12)     // Catch:{ Exception -> 0x0046, all -> 0x0059 }
            r5 = 0
            r6 = 0
            r7 = 0
            r1 = r10
            r2 = r11
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0046, all -> 0x0059 }
            boolean r0 = r1.moveToFirst()     // Catch:{ Exception -> 0x005c }
            if (r0 == 0) goto L_0x0040
            java.lang.Class r0 = r1.getClass()     // Catch:{ Exception -> 0x005c }
            java.lang.String r2 = r9.genGetColumnMethod(r13)     // Catch:{ Exception -> 0x005c }
            r3 = 1
            java.lang.Class[] r3 = new java.lang.Class[r3]     // Catch:{ Exception -> 0x005c }
            r4 = 0
            java.lang.Class r5 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x005c }
            r3[r4] = r5     // Catch:{ Exception -> 0x005c }
            java.lang.reflect.Method r0 = r0.getMethod(r2, r3)     // Catch:{ Exception -> 0x005c }
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x005c }
            r3 = 0
            r4 = 0
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x005c }
            r2[r3] = r4     // Catch:{ Exception -> 0x005c }
            java.lang.Object r8 = r0.invoke(r1, r2)     // Catch:{ Exception -> 0x005c }
        L_0x0040:
            if (r1 == 0) goto L_0x0045
            r1.close()
        L_0x0045:
            return r8
        L_0x0046:
            r0 = move-exception
            r1 = r8
        L_0x0048:
            org.litepal.exceptions.DataSupportException r2 = new org.litepal.exceptions.DataSupportException     // Catch:{ all -> 0x0052 }
            java.lang.String r3 = r0.getMessage()     // Catch:{ all -> 0x0052 }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x0052 }
            throw r2     // Catch:{ all -> 0x0052 }
        L_0x0052:
            r0 = move-exception
        L_0x0053:
            if (r1 == 0) goto L_0x0058
            r1.close()
        L_0x0058:
            throw r0
        L_0x0059:
            r0 = move-exception
            r1 = r8
            goto L_0x0053
        L_0x005c:
            r0 = move-exception
            goto L_0x0048
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.crud.DataHandler.mathQuery(java.lang.String, java.lang.String[], java.lang.String[], java.lang.Class):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public void giveBaseObjIdValue(DataSupport dataSupport, long j) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (j > 0) {
            DynamicExecutor.set(dataSupport, "baseObjId", Long.valueOf(j), DataSupport.class);
        }
    }

    /* access modifiers changed from: protected */
    public void putFieldsValue(DataSupport dataSupport, List<Field> list, ContentValues contentValues) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (Field field : list) {
            if (!isIdColumn(field.getName())) {
                putFieldsValueDependsOnSaveOrUpdate(dataSupport, field, contentValues);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void putContentValuesForSave(DataSupport dataSupport, Field field, ContentValues contentValues) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object field2 = DynamicExecutor.getField(dataSupport, field.getName(), dataSupport.getClass());
        if (field2 != null) {
            if ("java.util.Date".equals(field.getType().getName())) {
                field2 = Long.valueOf(((Date) field2).getTime());
            }
            Object[] objArr = {BaseUtility.changeCase(DBUtility.convertToValidColumnName(field.getName())), field2};
            DynamicExecutor.send(contentValues, "put", objArr, contentValues.getClass(), getParameterTypes(field, field2, objArr));
        }
    }

    /* access modifiers changed from: protected */
    public void putContentValuesForUpdate(DataSupport dataSupport, Field field, ContentValues contentValues) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object fieldValue = getFieldValue(dataSupport, field);
        if ("java.util.Date".equals(field.getType().getName()) && fieldValue != null) {
            fieldValue = Long.valueOf(((Date) fieldValue).getTime());
        }
        Object[] objArr = {BaseUtility.changeCase(DBUtility.convertToValidColumnName(field.getName())), fieldValue};
        DynamicExecutor.send(contentValues, "put", objArr, contentValues.getClass(), getParameterTypes(field, fieldValue, objArr));
    }

    /* access modifiers changed from: protected */
    public Object getFieldValue(DataSupport dataSupport, Field field) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (shouldGetOrSet(dataSupport, field)) {
            return DynamicExecutor.getField(dataSupport, field.getName(), dataSupport.getClass());
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void setFieldValue(DataSupport dataSupport, Field field, Object obj) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (shouldGetOrSet(dataSupport, field)) {
            DynamicExecutor.setField(dataSupport, field.getName(), obj, dataSupport.getClass());
        }
    }

    /* access modifiers changed from: protected */
    public void analyzeAssociatedModels(DataSupport dataSupport, Collection<AssociationsInfo> collection) {
        try {
            for (AssociationsInfo associationsInfo : collection) {
                if (associationsInfo.getAssociationType() == 2) {
                    new Many2OneAnalyzer().analyze(dataSupport, associationsInfo);
                } else if (associationsInfo.getAssociationType() == 1) {
                    new One2OneAnalyzer().analyze(dataSupport, associationsInfo);
                } else if (associationsInfo.getAssociationType() == 3) {
                    new Many2ManyAnalyzer().analyze(dataSupport, associationsInfo);
                }
            }
        } catch (Exception e) {
            throw new DataSupportException(e.getMessage(), e);
        }
    }

    /* access modifiers changed from: protected */
    public DataSupport getAssociatedModel(DataSupport dataSupport, AssociationsInfo associationsInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (DataSupport) getFieldValue(dataSupport, associationsInfo.getAssociateOtherModelFromSelf());
    }

    /* access modifiers changed from: protected */
    public Collection<DataSupport> getAssociatedModels(DataSupport dataSupport, AssociationsInfo associationsInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (Collection) getFieldValue(dataSupport, associationsInfo.getAssociateOtherModelFromSelf());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0035, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0036, code lost:
        r4 = r1;
        r1 = null;
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0052, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005c, code lost:
        throw new org.litepal.exceptions.DataSupportException(r0.getMessage(), r0);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0052 A[ExcHandler: Exception (r0v2 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:4:0x0008] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.litepal.crud.DataSupport getEmptyModel(org.litepal.crud.DataSupport r6) {
        /*
            r5 = this;
            org.litepal.crud.DataSupport r0 = r5.tempEmptyModel
            if (r0 == 0) goto L_0x0007
            org.litepal.crud.DataSupport r0 = r5.tempEmptyModel
        L_0x0006:
            return r0
        L_0x0007:
            r0 = 0
            java.lang.String r1 = r6.getClassName()     // Catch:{ ClassNotFoundException -> 0x001b, InstantiationException -> 0x0035, Exception -> 0x0052 }
            java.lang.Class r0 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x005f, InstantiationException -> 0x005d, Exception -> 0x0052 }
            java.lang.Object r0 = r0.newInstance()     // Catch:{ ClassNotFoundException -> 0x005f, InstantiationException -> 0x005d, Exception -> 0x0052 }
            org.litepal.crud.DataSupport r0 = (org.litepal.crud.DataSupport) r0     // Catch:{ ClassNotFoundException -> 0x005f, InstantiationException -> 0x005d, Exception -> 0x0052 }
            r5.tempEmptyModel = r0     // Catch:{ ClassNotFoundException -> 0x005f, InstantiationException -> 0x005d, Exception -> 0x0052 }
            org.litepal.crud.DataSupport r0 = r5.tempEmptyModel     // Catch:{ ClassNotFoundException -> 0x005f, InstantiationException -> 0x005d, Exception -> 0x0052 }
            goto L_0x0006
        L_0x001b:
            r1 = move-exception
        L_0x001c:
            org.litepal.exceptions.DatabaseGenerateException r1 = new org.litepal.exceptions.DatabaseGenerateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "can not find a class named "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x0035:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x0039:
            org.litepal.exceptions.DataSupportException r2 = new org.litepal.exceptions.DataSupportException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r3 = " needs a default constructor."
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r1 = r1.toString()
            r2.<init>(r1, r0)
            throw r2
        L_0x0052:
            r0 = move-exception
            org.litepal.exceptions.DataSupportException r1 = new org.litepal.exceptions.DataSupportException
            java.lang.String r2 = r0.getMessage()
            r1.<init>(r2, r0)
            throw r1
        L_0x005d:
            r0 = move-exception
            goto L_0x0039
        L_0x005f:
            r0 = move-exception
            r0 = r1
            goto L_0x001c
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.crud.DataHandler.getEmptyModel(org.litepal.crud.DataSupport):org.litepal.crud.DataSupport");
    }

    /* access modifiers changed from: protected */
    public String getWhereClause(String... strArr) {
        if (!isAffectAllLines(strArr) && strArr != null && strArr.length > 0) {
            return strArr[0];
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public String[] getWhereArgs(String... strArr) {
        if (isAffectAllLines(strArr) || strArr == null || strArr.length <= 1) {
            return null;
        }
        String[] strArr2 = new String[(strArr.length - 1)];
        System.arraycopy(strArr, 1, strArr2, 0, strArr.length - 1);
        return strArr2;
    }

    /* access modifiers changed from: protected */
    public boolean isAffectAllLines(Object... objArr) {
        if (objArr == null || objArr.length != 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public String getWhereOfIdsWithOr(Collection<Long> collection) {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        Iterator it = collection.iterator();
        while (true) {
            boolean z2 = z;
            if (!it.hasNext()) {
                return BaseUtility.changeCase(sb.toString());
            }
            long longValue = ((Long) it.next()).longValue();
            if (z2) {
                sb.append(" or ");
            }
            z = true;
            sb.append("id = ");
            sb.append(longValue);
        }
    }

    /* access modifiers changed from: protected */
    public String getWhereOfIdsWithOr(long... jArr) {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (long j : jArr) {
            if (z) {
                sb.append(" or ");
            }
            z = true;
            sb.append("id = ");
            sb.append(j);
        }
        return BaseUtility.changeCase(sb.toString());
    }

    /* access modifiers changed from: protected */
    public boolean shouldGetOrSet(DataSupport dataSupport, Field field) {
        return (dataSupport == null || field == null) ? false : true;
    }

    /* access modifiers changed from: protected */
    public String getIntermediateTableName(DataSupport dataSupport, String str) {
        return BaseUtility.changeCase(DBUtility.getIntermediateTableName(dataSupport.getTableName(), str));
    }

    /* access modifiers changed from: protected */
    public String getTableName(Class<?> cls) {
        return BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName()));
    }

    /* access modifiers changed from: protected */
    public Object createInstanceFromClass(Class<?> cls) {
        try {
            Constructor findBestSuitConstructor = findBestSuitConstructor(cls);
            return findBestSuitConstructor.newInstance(getConstructorParams(cls, findBestSuitConstructor));
        } catch (Exception e) {
            throw new DataSupportException(e.getMessage(), e);
        }
    }

    /* access modifiers changed from: protected */
    public Constructor<?> findBestSuitConstructor(Class<?> cls) {
        Class<?>[] parameterTypes;
        Constructor[] declaredConstructors = cls.getDeclaredConstructors();
        SparseArray sparseArray = new SparseArray();
        int i = Integer.MAX_VALUE;
        int length = declaredConstructors.length;
        int i2 = 0;
        while (i2 < length) {
            Constructor constructor = declaredConstructors[i2];
            int length2 = constructor.getParameterTypes().length;
            for (Class<?> cls2 : constructor.getParameterTypes()) {
                if (cls2 == cls) {
                    length2 += 10000;
                } else if (cls2.getName().equals("com.android.tools.fd.runtime.InstantReloadException")) {
                    length2 += 10000;
                }
            }
            if (sparseArray.get(length2) == null) {
                sparseArray.put(length2, constructor);
            }
            if (length2 >= i) {
                length2 = i;
            }
            i2++;
            i = length2;
        }
        Constructor<?> constructor2 = (Constructor) sparseArray.get(i);
        if (constructor2 != null) {
            constructor2.setAccessible(true);
        }
        return constructor2;
    }

    /* access modifiers changed from: protected */
    public Object[] getConstructorParams(Class<?> cls, Constructor<?> constructor) {
        Class[] parameterTypes = constructor.getParameterTypes();
        Object[] objArr = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            objArr[i] = getInitParamValue(cls, parameterTypes[i]);
        }
        return objArr;
    }

    /* access modifiers changed from: protected */
    public void setValueToModel(Object obj, List<Field> list, List<AssociationsInfo> list2, Cursor cursor, SparseArray<QueryInfoCache> sparseArray) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String convertToValidColumnName;
        int size = sparseArray.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                int keyAt = sparseArray.keyAt(i);
                QueryInfoCache queryInfoCache = (QueryInfoCache) sparseArray.get(keyAt);
                setToModelByReflection(obj, queryInfoCache.field, keyAt, queryInfoCache.getMethodName, cursor);
            }
        } else {
            for (Field field : list) {
                String genGetColumnMethod = genGetColumnMethod(field);
                if (isIdColumn(field.getName())) {
                    convertToValidColumnName = Config.FEED_LIST_ITEM_CUSTOM_ID;
                } else {
                    convertToValidColumnName = DBUtility.convertToValidColumnName(field.getName());
                }
                int columnIndex = cursor.getColumnIndex(BaseUtility.changeCase(convertToValidColumnName));
                if (columnIndex != -1) {
                    setToModelByReflection(obj, field, columnIndex, genGetColumnMethod, cursor);
                    QueryInfoCache queryInfoCache2 = new QueryInfoCache();
                    queryInfoCache2.getMethodName = genGetColumnMethod;
                    queryInfoCache2.field = field;
                    sparseArray.put(columnIndex, queryInfoCache2);
                }
            }
        }
        if (list2 != null) {
            for (AssociationsInfo associationsInfo : list2) {
                int columnIndex2 = cursor.getColumnIndex(getForeignKeyColumnName(DBUtility.getTableNameByClassName(associationsInfo.getAssociatedClassName())));
                if (columnIndex2 != -1) {
                    try {
                        DataSupport dataSupport = (DataSupport) DataSupport.find(Class.forName(associationsInfo.getAssociatedClassName()), cursor.getLong(columnIndex2));
                        if (dataSupport != null) {
                            setFieldValue((DataSupport) obj, associationsInfo.getAssociateOtherModelFromSelf(), dataSupport);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setGenericValueToModel(DataSupport dataSupport, List<Field> list, Map<Field, GenericModel> map) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String getMethodName;
        String str;
        String str2;
        String str3;
        Cursor cursor;
        for (Field field : list) {
            GenericModel genericModel = (GenericModel) map.get(field);
            if (genericModel == null) {
                String genericTableName = DBUtility.getGenericTableName(dataSupport.getClassName(), field.getName());
                String convertToValidColumnName = DBUtility.convertToValidColumnName(field.getName());
                String genericValueIdColumnName = DBUtility.getGenericValueIdColumnName(dataSupport.getClassName());
                String genGetColumnMethod = genGetColumnMethod(field);
                GenericModel genericModel2 = new GenericModel();
                genericModel2.setTableName(genericTableName);
                genericModel2.setValueColumnName(convertToValidColumnName);
                genericModel2.setValueIdColumnName(genericValueIdColumnName);
                genericModel2.setGetMethodName(genGetColumnMethod);
                map.put(field, genericModel2);
                getMethodName = genGetColumnMethod;
                str = convertToValidColumnName;
                String str4 = genericValueIdColumnName;
                str2 = genericTableName;
                str3 = str4;
            } else {
                String tableName = genericModel.getTableName();
                String valueColumnName = genericModel.getValueColumnName();
                String valueIdColumnName = genericModel.getValueIdColumnName();
                getMethodName = genericModel.getGetMethodName();
                str = valueColumnName;
                String str5 = valueIdColumnName;
                str2 = tableName;
                str3 = str5;
            }
            try {
                cursor = this.mDatabase.query(str2, null, str3 + " = ?", new String[]{String.valueOf(dataSupport.getBaseObjId())}, null, null, null);
                try {
                    if (cursor.moveToFirst()) {
                        do {
                            int columnIndex = cursor.getColumnIndex(BaseUtility.changeCase(str));
                            if (columnIndex != -1) {
                                setToModelByReflection(dataSupport, field, columnIndex, getMethodName, cursor);
                            }
                        } while (cursor.moveToNext());
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public List<AssociationsInfo> getForeignKeyAssociations(String str, boolean z) {
        if (!z) {
            return null;
        }
        analyzeAssociations(str);
        return this.fkInCurrentModel;
    }

    /* access modifiers changed from: protected */
    public Class<?>[] getParameterTypes(Field field, Object obj, Object[] objArr) {
        if (isCharType(field)) {
            objArr[1] = String.valueOf(obj);
            return new Class[]{String.class, String.class};
        } else if (field.getType().isPrimitive()) {
            return new Class[]{String.class, getObjectType(field.getType())};
        } else if ("java.util.Date".equals(field.getType().getName())) {
            return new Class[]{String.class, Long.class};
        } else {
            return new Class[]{String.class, field.getType()};
        }
    }

    private Class<?> getObjectType(Class<?> cls) {
        if (cls != null && cls.isPrimitive()) {
            String name = cls.getName();
            if ("int".equals(name)) {
                return Integer.class;
            }
            if ("short".equals(name)) {
                return Short.class;
            }
            if (SettingsContentProvider.LONG_TYPE.equals(name)) {
                return Long.class;
            }
            if ("float".equals(name)) {
                return Float.class;
            }
            if ("double".equals(name)) {
                return Double.class;
            }
            if ("boolean".equals(name)) {
                return Boolean.class;
            }
            if ("char".equals(name)) {
                return Character.class;
            }
        }
        return null;
    }

    private Object getInitParamValue(Class<?> cls, Class<?> cls2) {
        String name = cls2.getName();
        if ("boolean".equals(name) || "java.lang.Boolean".equals(name)) {
            return Boolean.valueOf(false);
        }
        if ("float".equals(name) || "java.lang.Float".equals(name)) {
            return Float.valueOf(0.0f);
        }
        if ("double".equals(name) || "java.lang.Double".equals(name)) {
            return Double.valueOf(0.0d);
        }
        if ("int".equals(name) || "java.lang.Integer".equals(name)) {
            return Integer.valueOf(0);
        }
        if (SettingsContentProvider.LONG_TYPE.equals(name) || "java.lang.Long".equals(name)) {
            return Long.valueOf(0);
        }
        if ("short".equals(name) || "java.lang.Short".equals(name)) {
            return Integer.valueOf(0);
        }
        if ("char".equals(name) || "java.lang.Character".equals(name)) {
            return Character.valueOf(' ');
        }
        if ("[B".equals(name) || "[Ljava.lang.Byte;".equals(name)) {
            return new byte[0];
        }
        if ("java.lang.String".equals(name)) {
            return "";
        }
        if (cls == cls2) {
            return null;
        }
        return createInstanceFromClass(cls2);
    }

    private boolean isCharType(Field field) {
        String name = field.getType().getName();
        return name.equals("char") || name.endsWith("Character");
    }

    private boolean isPrimitiveBooleanType(Field field) {
        if ("boolean".equals(field.getType().getName())) {
            return true;
        }
        return false;
    }

    private void putFieldsValueDependsOnSaveOrUpdate(DataSupport dataSupport, Field field, ContentValues contentValues) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (isUpdating()) {
            if (!isFieldWithDefaultValue(dataSupport, field)) {
                putContentValuesForUpdate(dataSupport, field, contentValues);
            }
        } else if (isSaving()) {
            putContentValuesForSave(dataSupport, field, contentValues);
        }
    }

    private boolean isUpdating() {
        return UpdateHandler.class.getName().equals(getClass().getName());
    }

    private boolean isSaving() {
        return SaveHandler.class.getName().equals(getClass().getName());
    }

    private boolean isFieldWithDefaultValue(DataSupport dataSupport, Field field) throws IllegalAccessException, SecurityException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException {
        DataSupport emptyModel = getEmptyModel(dataSupport);
        Object fieldValue = getFieldValue(dataSupport, field);
        Object fieldValue2 = getFieldValue(emptyModel, field);
        if (fieldValue == null || fieldValue2 == null) {
            return fieldValue == fieldValue2;
        }
        return fieldValue.toString().equals(fieldValue2.toString());
    }

    private String makeGetterMethodName(Field field) {
        String str;
        String name = field.getName();
        if (isPrimitiveBooleanType(field)) {
            if (name.matches("^is[A-Z]{1}.*$")) {
                name = name.substring(2);
            }
            str = "is";
        } else {
            str = "get";
        }
        if (name.matches("^[a-z]{1}[A-Z]{1}.*")) {
            return str + name;
        }
        return str + BaseUtility.capitalize(name);
    }

    private String makeSetterMethodName(Field field) {
        String str = "set";
        if (isPrimitiveBooleanType(field) && field.getName().matches("^is[A-Z]{1}.*$")) {
            return str + field.getName().substring(2);
        }
        if (field.getName().matches("^[a-z]{1}[A-Z]{1}.*")) {
            return str + field.getName();
        }
        return str + BaseUtility.capitalize(field.getName());
    }

    private String genGetColumnMethod(Field field) {
        Class type;
        if (isCollection(field.getType())) {
            type = getGenericTypeClass(field);
        } else {
            type = field.getType();
        }
        return genGetColumnMethod(type);
    }

    private String genGetColumnMethod(Class<?> cls) {
        String simpleName;
        if (cls.isPrimitive()) {
            simpleName = BaseUtility.capitalize(cls.getName());
        } else {
            simpleName = cls.getSimpleName();
        }
        String str = "get" + simpleName;
        if ("getBoolean".equals(str)) {
            return "getInt";
        }
        if ("getChar".equals(str) || "getCharacter".equals(str)) {
            return "getString";
        }
        if ("getDate".equals(str)) {
            return "getLong";
        }
        if ("getInteger".equals(str)) {
            return "getInt";
        }
        if ("getbyte[]".equalsIgnoreCase(str)) {
            return "getBlob";
        }
        return str;
    }

    private String[] getCustomizedColumns(String[] strArr, List<Field> list, List<AssociationsInfo> list2) {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        for (Field name : list) {
            arrayList2.add(name.getName());
        }
        boolean z = false;
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            if (BaseUtility.containsIgnoreCases(arrayList2, str)) {
                arrayList3.add(Integer.valueOf(i));
            } else if (isIdColumn(str)) {
                z = true;
                if ("_id".equalsIgnoreCase(str)) {
                    arrayList.set(i, BaseUtility.changeCase(Config.FEED_LIST_ITEM_CUSTOM_ID));
                }
            }
        }
        for (int size = arrayList3.size() - 1; size >= 0; size--) {
            arrayList4.add((String) arrayList.remove(((Integer) arrayList3.get(size)).intValue()));
        }
        for (Field field : list) {
            if (BaseUtility.containsIgnoreCases(arrayList4, field.getName())) {
                arrayList5.add(field);
            }
        }
        list.clear();
        list.addAll(arrayList5);
        if (list2 != null && list2.size() > 0) {
            for (int i2 = 0; i2 < list2.size(); i2++) {
                arrayList.add(getForeignKeyColumnName(DBUtility.getTableNameByClassName(((AssociationsInfo) list2.get(i2)).getAssociatedClassName())));
            }
        }
        if (!z) {
            arrayList.add(BaseUtility.changeCase(Config.FEED_LIST_ITEM_CUSTOM_ID));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private void analyzeAssociations(String str) {
        Collection<AssociationsInfo> associationInfo = getAssociationInfo(str);
        if (this.fkInCurrentModel == null) {
            this.fkInCurrentModel = new ArrayList();
        } else {
            this.fkInCurrentModel.clear();
        }
        if (this.fkInOtherModel == null) {
            this.fkInOtherModel = new ArrayList();
        } else {
            this.fkInOtherModel.clear();
        }
        for (AssociationsInfo associationsInfo : associationInfo) {
            if (associationsInfo.getAssociationType() == 2 || associationsInfo.getAssociationType() == 1) {
                if (associationsInfo.getClassHoldsForeignKey().equals(str)) {
                    this.fkInCurrentModel.add(associationsInfo);
                } else {
                    this.fkInOtherModel.add(associationsInfo);
                }
            } else if (associationsInfo.getAssociationType() == 3) {
                this.fkInOtherModel.add(associationsInfo);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x018e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setAssociatedModel(org.litepal.crud.DataSupport r19) {
        /*
            r18 = this;
            r0 = r18
            java.util.List<org.litepal.crud.model.AssociationsInfo> r2 = r0.fkInOtherModel
            if (r2 != 0) goto L_0x0007
        L_0x0006:
            return
        L_0x0007:
            r0 = r18
            java.util.List<org.litepal.crud.model.AssociationsInfo> r2 = r0.fkInOtherModel
            java.util.Iterator r14 = r2.iterator()
        L_0x000f:
            boolean r2 = r14.hasNext()
            if (r2 == 0) goto L_0x0006
            java.lang.Object r2 = r14.next()
            r11 = r2
            org.litepal.crud.model.AssociationsInfo r11 = (org.litepal.crud.model.AssociationsInfo) r11
            r13 = 0
            java.lang.String r15 = r11.getAssociatedClassName()
            int r2 = r11.getAssociationType()
            r3 = 3
            if (r2 != r3) goto L_0x012f
            r2 = 1
            r12 = r2
        L_0x002a:
            r0 = r18
            java.util.List r16 = r0.getSupportedFields(r15)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            r0 = r18
            java.util.List r17 = r0.getSupportedGenericFields(r15)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            if (r12 == 0) goto L_0x0133
            java.lang.String r2 = r19.getTableName()     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.String r3 = org.litepal.util.DBUtility.getTableNameByClassName(r15)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.String r4 = org.litepal.util.DBUtility.getIntermediateTableName(r2, r3)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            r5.<init>()     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.String r6 = "select * from "
            java.lang.StringBuilder r6 = r5.append(r6)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.StringBuilder r6 = r6.append(r3)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.String r7 = " a inner join "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.StringBuilder r4 = r6.append(r4)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.String r6 = " b on a.id = b."
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            r6.<init>()     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.StringBuilder r3 = r6.append(r3)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.String r6 = "_id"
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.String r4 = " where b."
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.String r3 = "_id = ?"
            r2.append(r3)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            r3 = 0
            java.lang.String r4 = r5.toString()     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.String r4 = org.litepal.util.BaseUtility.changeCase(r4)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            r2[r3] = r4     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            r3 = 1
            long r4 = r19.getBaseObjId()     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            r2[r3] = r4     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            android.database.Cursor r6 = org.litepal.crud.DataSupport.findBySQL(r2)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
        L_0x00a6:
            if (r6 == 0) goto L_0x0128
            boolean r2 = r6.moveToFirst()     // Catch:{ Exception -> 0x017f }
            if (r2 == 0) goto L_0x0128
            android.util.SparseArray r7 = new android.util.SparseArray     // Catch:{ Exception -> 0x017f }
            r7.<init>()     // Catch:{ Exception -> 0x017f }
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ Exception -> 0x017f }
            r8.<init>()     // Catch:{ Exception -> 0x017f }
        L_0x00b8:
            java.lang.Class r2 = java.lang.Class.forName(r15)     // Catch:{ Exception -> 0x017f }
            r0 = r18
            java.lang.Object r3 = r0.createInstanceFromClass(r2)     // Catch:{ Exception -> 0x017f }
            org.litepal.crud.DataSupport r3 = (org.litepal.crud.DataSupport) r3     // Catch:{ Exception -> 0x017f }
            java.lang.String r2 = "id"
            int r2 = r6.getColumnIndexOrThrow(r2)     // Catch:{ Exception -> 0x017f }
            long r4 = r6.getLong(r2)     // Catch:{ Exception -> 0x017f }
            r0 = r18
            r0.giveBaseObjIdValue(r3, r4)     // Catch:{ Exception -> 0x017f }
            r5 = 0
            r2 = r18
            r4 = r16
            r2.setValueToModel(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x017f }
            r0 = r18
            r1 = r17
            r0.setGenericValueToModel(r3, r1, r8)     // Catch:{ Exception -> 0x017f }
            int r2 = r11.getAssociationType()     // Catch:{ Exception -> 0x017f }
            r4 = 2
            if (r2 == r4) goto L_0x00eb
            if (r12 == 0) goto L_0x0192
        L_0x00eb:
            java.lang.reflect.Field r4 = r11.getAssociateOtherModelFromSelf()     // Catch:{ Exception -> 0x017f }
            r0 = r18
            r1 = r19
            java.lang.Object r2 = r0.getFieldValue(r1, r4)     // Catch:{ Exception -> 0x017f }
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ Exception -> 0x017f }
            if (r2 != 0) goto L_0x0119
            java.lang.Class r2 = r4.getType()     // Catch:{ Exception -> 0x017f }
            r0 = r18
            boolean r2 = r0.isList(r2)     // Catch:{ Exception -> 0x017f }
            if (r2 == 0) goto L_0x0179
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Exception -> 0x017f }
            r2.<init>()     // Catch:{ Exception -> 0x017f }
        L_0x010c:
            java.lang.String r4 = r4.getName()     // Catch:{ Exception -> 0x017f }
            java.lang.Class r5 = r19.getClass()     // Catch:{ Exception -> 0x017f }
            r0 = r19
            org.litepal.crud.DynamicExecutor.setField(r0, r4, r2, r5)     // Catch:{ Exception -> 0x017f }
        L_0x0119:
            r2.add(r3)     // Catch:{ Exception -> 0x017f }
        L_0x011c:
            boolean r2 = r6.moveToNext()     // Catch:{ Exception -> 0x017f }
            if (r2 != 0) goto L_0x00b8
            r7.clear()     // Catch:{ Exception -> 0x017f }
            r8.clear()     // Catch:{ Exception -> 0x017f }
        L_0x0128:
            if (r6 == 0) goto L_0x000f
            r6.close()
            goto L_0x000f
        L_0x012f:
            r2 = 0
            r12 = r2
            goto L_0x002a
        L_0x0133:
            java.lang.String r2 = r11.getSelfClassName()     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.String r2 = org.litepal.util.DBUtility.getTableNameByClassName(r2)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            r0 = r18
            java.lang.String r5 = r0.getForeignKeyColumnName(r2)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.String r3 = org.litepal.util.DBUtility.getTableNameByClassName(r15)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            r0 = r18
            android.database.sqlite.SQLiteDatabase r2 = r0.mDatabase     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.String r3 = org.litepal.util.BaseUtility.changeCase(r3)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            r4 = 0
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            r6.<init>()     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.StringBuilder r5 = r6.append(r5)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.String r6 = "=?"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            r6 = 1
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            r7 = 0
            long r8 = r19.getBaseObjId()     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            r6[r7] = r8     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r6 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x01a8, all -> 0x01a6 }
            goto L_0x00a6
        L_0x0179:
            java.util.HashSet r2 = new java.util.HashSet     // Catch:{ Exception -> 0x017f }
            r2.<init>()     // Catch:{ Exception -> 0x017f }
            goto L_0x010c
        L_0x017f:
            r2 = move-exception
        L_0x0180:
            org.litepal.exceptions.DataSupportException r3 = new org.litepal.exceptions.DataSupportException     // Catch:{ all -> 0x018a }
            java.lang.String r4 = r2.getMessage()     // Catch:{ all -> 0x018a }
            r3.<init>(r4, r2)     // Catch:{ all -> 0x018a }
            throw r3     // Catch:{ all -> 0x018a }
        L_0x018a:
            r2 = move-exception
            r13 = r6
        L_0x018c:
            if (r13 == 0) goto L_0x0191
            r13.close()
        L_0x0191:
            throw r2
        L_0x0192:
            int r2 = r11.getAssociationType()     // Catch:{ Exception -> 0x017f }
            r4 = 1
            if (r2 != r4) goto L_0x011c
            java.lang.reflect.Field r2 = r11.getAssociateOtherModelFromSelf()     // Catch:{ Exception -> 0x017f }
            r0 = r18
            r1 = r19
            r0.setFieldValue(r1, r2, r3)     // Catch:{ Exception -> 0x017f }
            goto L_0x011c
        L_0x01a6:
            r2 = move-exception
            goto L_0x018c
        L_0x01a8:
            r2 = move-exception
            r6 = r13
            goto L_0x0180
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.crud.DataHandler.setAssociatedModel(org.litepal.crud.DataSupport):void");
    }

    private void setToModelByReflection(Object obj, Field field, int i, String str, Cursor cursor) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object obj2;
        Object invoke = cursor.getClass().getMethod(str, new Class[]{Integer.TYPE}).invoke(cursor, new Object[]{Integer.valueOf(i)});
        if (field.getType() == Boolean.TYPE || field.getType() == Boolean.class) {
            if ("0".equals(String.valueOf(invoke))) {
                obj2 = Boolean.valueOf(false);
            } else {
                if ("1".equals(String.valueOf(invoke))) {
                    obj2 = Boolean.valueOf(true);
                }
                obj2 = invoke;
            }
        } else if (field.getType() == Character.TYPE || field.getType() == Character.class) {
            obj2 = Character.valueOf(((String) invoke).charAt(0));
        } else {
            if (field.getType() == Date.class) {
                long longValue = ((Long) invoke).longValue();
                if (longValue <= 0) {
                    obj2 = null;
                } else {
                    obj2 = new Date(longValue);
                }
            }
            obj2 = invoke;
        }
        if (isCollection(field.getType())) {
            Collection collection = (Collection) DynamicExecutor.getField(obj, field.getName(), obj.getClass());
            if (collection == null) {
                if (isList(field.getType())) {
                    collection = new ArrayList();
                } else {
                    collection = new HashSet();
                }
                DynamicExecutor.setField(obj, field.getName(), collection, obj.getClass());
            }
            collection.add(obj2);
            return;
        }
        DynamicExecutor.setField(obj, field.getName(), obj2, obj.getClass());
    }
}
