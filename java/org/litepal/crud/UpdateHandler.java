package org.litepal.crud;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build.VERSION;
import com.baidu.mobstat.Config;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.litepal.exceptions.DataSupportException;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;

class UpdateHandler extends DataHandler {
    UpdateHandler(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    /* access modifiers changed from: 0000 */
    public int onUpdate(DataSupport dataSupport, long j) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List supportedFields = getSupportedFields(dataSupport.getClassName());
        updateGenericTables(dataSupport, getSupportedGenericFields(dataSupport.getClassName()), j);
        ContentValues contentValues = new ContentValues();
        putFieldsValue(dataSupport, supportedFields, contentValues);
        putFieldsToDefaultValue(dataSupport, contentValues, j);
        if (contentValues.size() > 0) {
            return this.mDatabase.update(dataSupport.getTableName(), contentValues, "id = " + j, null);
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public int onUpdate(Class<?> cls, long j, ContentValues contentValues) {
        if (contentValues.size() <= 0) {
            return 0;
        }
        convertContentValues(contentValues);
        return this.mDatabase.update(getTableName(cls), contentValues, "id = " + j, null);
    }

    /* access modifiers changed from: 0000 */
    public int onUpdateAll(DataSupport dataSupport, String... strArr) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        long[] jArr;
        int i = 0;
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        List supportedFields = getSupportedFields(dataSupport.getClassName());
        List supportedGenericFields = getSupportedGenericFields(dataSupport.getClassName());
        if (!supportedGenericFields.isEmpty()) {
            List find = DataSupport.select(Config.FEED_LIST_ITEM_CUSTOM_ID).where(strArr).find(dataSupport.getClass());
            if (find.size() > 0) {
                long[] jArr2 = new long[find.size()];
                while (true) {
                    int i2 = i;
                    if (i2 >= jArr2.length) {
                        break;
                    }
                    jArr2[i2] = ((DataSupport) find.get(i2)).getBaseObjId();
                    i = i2 + 1;
                }
                updateGenericTables(dataSupport, supportedGenericFields, jArr2);
                jArr = jArr2;
                ContentValues contentValues = new ContentValues();
                putFieldsValue(dataSupport, supportedFields, contentValues);
                putFieldsToDefaultValue(dataSupport, contentValues, jArr);
                return doUpdateAllAction(dataSupport.getTableName(), contentValues, strArr);
            }
        }
        jArr = null;
        ContentValues contentValues2 = new ContentValues();
        putFieldsValue(dataSupport, supportedFields, contentValues2);
        putFieldsToDefaultValue(dataSupport, contentValues2, jArr);
        return doUpdateAllAction(dataSupport.getTableName(), contentValues2, strArr);
    }

    /* access modifiers changed from: 0000 */
    public int onUpdateAll(String str, ContentValues contentValues, String... strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        convertContentValues(contentValues);
        return doUpdateAllAction(str, contentValues, strArr);
    }

    private int doUpdateAllAction(String str, ContentValues contentValues, String... strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (contentValues.size() > 0) {
            return this.mDatabase.update(str, contentValues, getWhereClause(strArr), getWhereArgs(strArr));
        }
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00aa, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b4, code lost:
        throw new org.litepal.exceptions.DataSupportException(r2.getMessage(), r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b6, code lost:
        r2 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00aa A[ExcHandler: Exception (r2v1 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void putFieldsToDefaultValue(org.litepal.crud.DataSupport r17, android.content.ContentValues r18, long... r19) {
        /*
            r16 = this;
            r3 = 0
            org.litepal.crud.DataSupport r5 = r16.getEmptyModel(r17)     // Catch:{ NoSuchFieldException -> 0x00b6, Exception -> 0x00aa }
            java.lang.Class r6 = r5.getClass()     // Catch:{ NoSuchFieldException -> 0x00b6, Exception -> 0x00aa }
            java.util.List r2 = r17.getFieldsToSetToDefault()     // Catch:{ NoSuchFieldException -> 0x00b6, Exception -> 0x00aa }
            java.util.Iterator r7 = r2.iterator()     // Catch:{ NoSuchFieldException -> 0x00b6, Exception -> 0x00aa }
        L_0x0011:
            boolean r2 = r7.hasNext()     // Catch:{ NoSuchFieldException -> 0x00b6, Exception -> 0x00aa }
            if (r2 == 0) goto L_0x00b5
            java.lang.Object r2 = r7.next()     // Catch:{ NoSuchFieldException -> 0x00b6, Exception -> 0x00aa }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ NoSuchFieldException -> 0x00b6, Exception -> 0x00aa }
            r0 = r16
            boolean r4 = r0.isIdColumn(r2)     // Catch:{ NoSuchFieldException -> 0x00b6, Exception -> 0x00aa }
            if (r4 != 0) goto L_0x00b8
            java.lang.reflect.Field r3 = r6.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            java.lang.Class r4 = r3.getType()     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            r0 = r16
            boolean r4 = r0.isCollection(r4)     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            if (r4 == 0) goto L_0x0090
            if (r19 == 0) goto L_0x008e
            r0 = r19
            int r4 = r0.length     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            if (r4 <= 0) goto L_0x008e
            r0 = r16
            java.lang.String r4 = r0.getGenericTypeName(r3)     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            boolean r4 = org.litepal.util.BaseUtility.isGenericTypeSupported(r4)     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            if (r4 == 0) goto L_0x008e
            java.lang.String r4 = r17.getClassName()     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            java.lang.String r3 = r3.getName()     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            java.lang.String r8 = org.litepal.util.DBUtility.getGenericTableName(r4, r3)     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            java.lang.String r3 = r17.getClassName()     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            java.lang.String r9 = org.litepal.util.DBUtility.getGenericValueIdColumnName(r3)     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            r10.<init>()     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            r4 = 0
            r0 = r19
            int r11 = r0.length     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            r3 = 0
        L_0x0066:
            if (r3 >= r11) goto L_0x0082
            r12 = r19[r3]     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            if (r4 == 0) goto L_0x0071
            java.lang.String r4 = " or "
            r10.append(r4)     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
        L_0x0071:
            java.lang.StringBuilder r4 = r10.append(r9)     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            java.lang.String r14 = " = "
            java.lang.StringBuilder r4 = r4.append(r14)     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            r4.append(r12)     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            r4 = 1
            int r3 = r3 + 1
            goto L_0x0066
        L_0x0082:
            r0 = r16
            android.database.sqlite.SQLiteDatabase r3 = r0.mDatabase     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            java.lang.String r4 = r10.toString()     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            r9 = 0
            r3.delete(r8, r4, r9)     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
        L_0x008e:
            r3 = r2
            goto L_0x0011
        L_0x0090:
            r0 = r16
            r1 = r18
            r0.putContentValuesForUpdate(r5, r3, r1)     // Catch:{ NoSuchFieldException -> 0x0098, Exception -> 0x00aa }
            goto L_0x008e
        L_0x0098:
            r3 = move-exception
            r15 = r3
            r3 = r2
            r2 = r15
        L_0x009c:
            org.litepal.exceptions.DataSupportException r4 = new org.litepal.exceptions.DataSupportException
            java.lang.String r5 = r17.getClassName()
            java.lang.String r3 = org.litepal.exceptions.DataSupportException.noSuchFieldExceptioin(r5, r3)
            r4.<init>(r3, r2)
            throw r4
        L_0x00aa:
            r2 = move-exception
            org.litepal.exceptions.DataSupportException r3 = new org.litepal.exceptions.DataSupportException
            java.lang.String r4 = r2.getMessage()
            r3.<init>(r4, r2)
            throw r3
        L_0x00b5:
            return
        L_0x00b6:
            r2 = move-exception
            goto L_0x009c
        L_0x00b8:
            r2 = r3
            goto L_0x008e
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.crud.UpdateHandler.putFieldsToDefaultValue(org.litepal.crud.DataSupport, android.content.ContentValues, long[]):void");
    }

    private int doUpdateAssociations(DataSupport dataSupport, long j, ContentValues contentValues) {
        analyzeAssociations(dataSupport);
        updateSelfTableForeignKey(dataSupport, contentValues);
        return 0 + updateAssociatedTableForeignKey(dataSupport, j);
    }

    private void analyzeAssociations(DataSupport dataSupport) {
        try {
            analyzeAssociatedModels(dataSupport, getAssociationInfo(dataSupport.getClassName()));
        } catch (Exception e) {
            throw new DataSupportException(e.getMessage(), e);
        }
    }

    private void updateSelfTableForeignKey(DataSupport dataSupport, ContentValues contentValues) {
        Map associatedModelsMapWithoutFK = dataSupport.getAssociatedModelsMapWithoutFK();
        for (String str : associatedModelsMapWithoutFK.keySet()) {
            contentValues.put(getForeignKeyColumnName(str), (Long) associatedModelsMapWithoutFK.get(str));
        }
    }

    private int updateAssociatedTableForeignKey(DataSupport dataSupport, long j) {
        Map associatedModelsMapWithFK = dataSupport.getAssociatedModelsMapWithFK();
        ContentValues contentValues = new ContentValues();
        for (String str : associatedModelsMapWithFK.keySet()) {
            contentValues.clear();
            contentValues.put(getForeignKeyColumnName(dataSupport.getTableName()), Long.valueOf(j));
            Set set = (Set) associatedModelsMapWithFK.get(str);
            if (set != null && !set.isEmpty()) {
                return this.mDatabase.update(str, contentValues, getWhereOfIdsWithOr((Collection<Long>) set), null);
            }
        }
        return 0;
    }

    private void updateGenericTables(DataSupport dataSupport, List<Field> list, long... jArr) throws IllegalAccessException, InvocationTargetException {
        if (jArr != null && jArr.length > 0) {
            for (Field field : list) {
                field.setAccessible(true);
                Collection collection = (Collection) field.get(dataSupport);
                if (collection != null && !collection.isEmpty()) {
                    String genericTableName = DBUtility.getGenericTableName(dataSupport.getClassName(), field.getName());
                    String genericValueIdColumnName = DBUtility.getGenericValueIdColumnName(dataSupport.getClassName());
                    for (long j : jArr) {
                        this.mDatabase.delete(genericTableName, genericValueIdColumnName + " = ?", new String[]{String.valueOf(j)});
                        for (Object next : collection) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(genericValueIdColumnName, Long.valueOf(j));
                            DynamicExecutor.send(contentValues, "put", new Object[]{DBUtility.convertToValidColumnName(BaseUtility.changeCase(field.getName())), next}, contentValues.getClass(), new Class[]{String.class, getGenericTypeClass(field)});
                            this.mDatabase.insert(genericTableName, null, contentValues);
                        }
                    }
                }
            }
        }
    }

    private void convertContentValues(ContentValues contentValues) {
        if (VERSION.SDK_INT >= 11) {
            HashMap hashMap = new HashMap();
            for (String str : contentValues.keySet()) {
                if (DBUtility.isFieldNameConflictWithSQLiteKeywords(str)) {
                    hashMap.put(str, contentValues.get(str));
                }
            }
            for (String str2 : hashMap.keySet()) {
                String convertToValidColumnName = DBUtility.convertToValidColumnName(str2);
                Object obj = contentValues.get(str2);
                contentValues.remove(str2);
                if (obj == null) {
                    contentValues.putNull(convertToValidColumnName);
                } else {
                    String name = obj.getClass().getName();
                    if ("java.lang.Byte".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Byte) obj);
                    } else if ("[B".equals(name)) {
                        contentValues.put(convertToValidColumnName, (byte[]) obj);
                    } else if ("java.lang.Boolean".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Boolean) obj);
                    } else if ("java.lang.String".equals(name)) {
                        contentValues.put(convertToValidColumnName, (String) obj);
                    } else if ("java.lang.Float".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Float) obj);
                    } else if ("java.lang.Long".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Long) obj);
                    } else if ("java.lang.Integer".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Integer) obj);
                    } else if ("java.lang.Short".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Short) obj);
                    } else if ("java.lang.Double".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Double) obj);
                    }
                }
            }
        }
    }
}
