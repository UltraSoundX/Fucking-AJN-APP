package org.litepal.crud;

import android.database.sqlite.SQLiteDatabase;
import com.baidu.mobstat.Config;
import java.util.List;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;

class QueryHandler extends DataHandler {
    QueryHandler(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    /* access modifiers changed from: 0000 */
    public <T> T onFind(Class<T> cls, long j, boolean z) {
        Class<T> cls2 = cls;
        List query = query(cls2, null, "id = ?", new String[]{String.valueOf(j)}, null, null, null, null, getForeignKeyAssociations(cls.getName(), z));
        if (query.size() > 0) {
            return query.get(0);
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public <T> T onFindFirst(Class<T> cls, boolean z) {
        Class<T> cls2 = cls;
        List query = query(cls2, null, null, null, null, null, Config.FEED_LIST_ITEM_CUSTOM_ID, "1", getForeignKeyAssociations(cls.getName(), z));
        if (query.size() > 0) {
            return query.get(0);
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public <T> T onFindLast(Class<T> cls, boolean z) {
        Class<T> cls2 = cls;
        List query = query(cls2, null, null, null, null, null, "id desc", "1", getForeignKeyAssociations(cls.getName(), z));
        if (query.size() > 0) {
            return query.get(0);
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public <T> List<T> onFindAll(Class<T> cls, boolean z, long... jArr) {
        if (isAffectAllLines(jArr)) {
            return query(cls, null, null, null, null, null, Config.FEED_LIST_ITEM_CUSTOM_ID, null, getForeignKeyAssociations(cls.getName(), z));
        }
        return query(cls, null, getWhereOfIdsWithOr(jArr), null, null, null, Config.FEED_LIST_ITEM_CUSTOM_ID, null, getForeignKeyAssociations(cls.getName(), z));
    }

    /* access modifiers changed from: 0000 */
    public <T> List<T> onFind(Class<T> cls, String[] strArr, String[] strArr2, String str, String str2, boolean z) {
        BaseUtility.checkConditionsCorrect(strArr2);
        if (strArr2 != null && strArr2.length > 0) {
            strArr2[0] = DBUtility.convertWhereClauseToColumnName(strArr2[0]);
        }
        String convertOrderByClauseToValidName = DBUtility.convertOrderByClauseToValidName(str);
        return query(cls, strArr, getWhereClause(strArr2), getWhereArgs(strArr2), null, null, convertOrderByClauseToValidName, str2, getForeignKeyAssociations(cls.getName(), z));
    }

    /* access modifiers changed from: 0000 */
    public int onCount(String str, String[] strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        return ((Integer) mathQuery(str, new String[]{"count(1)"}, strArr, Integer.TYPE)).intValue();
    }

    /* access modifiers changed from: 0000 */
    public double onAverage(String str, String str2, String[] strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        return ((Double) mathQuery(str, new String[]{"avg(" + str2 + ")"}, strArr, Double.TYPE)).doubleValue();
    }

    /* access modifiers changed from: 0000 */
    public <T> T onMax(String str, String str2, String[] strArr, Class<T> cls) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        return mathQuery(str, new String[]{"max(" + str2 + ")"}, strArr, cls);
    }

    /* access modifiers changed from: 0000 */
    public <T> T onMin(String str, String str2, String[] strArr, Class<T> cls) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        return mathQuery(str, new String[]{"min(" + str2 + ")"}, strArr, cls);
    }

    /* access modifiers changed from: 0000 */
    public <T> T onSum(String str, String str2, String[] strArr, Class<T> cls) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        return mathQuery(str, new String[]{"sum(" + str2 + ")"}, strArr, cls);
    }
}
