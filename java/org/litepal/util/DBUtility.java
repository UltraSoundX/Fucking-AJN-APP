package org.litepal.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.e23.ajn.activity.OutUrlActivity;
import com.tencent.mid.sotrage.StorageInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sf.json.util.JSONUtils;
import org.litepal.exceptions.DatabaseGenerateException;
import org.litepal.tablemanager.model.ColumnModel;
import org.litepal.tablemanager.model.TableModel;

public class DBUtility {
    private static final String KEYWORDS_COLUMN_SUFFIX = "_lpcolumn";
    private static final String REG_COLLECTION = "\\s+(not\\s+)?(in|exists)\\s*\\(";
    private static final String REG_FUZZY = "\\s+(not\\s+)?(like|between)\\s+";
    private static final String REG_OPERATOR = "\\s*(=|!=|<>|<|>)";
    private static final String SQLITE_KEYWORDS = ",abort,add,after,all,alter,and,as,asc,autoincrement,before,begin,between,by,cascade,check,collate,column,commit,conflict,constraint,create,cross,database,deferrable,deferred,delete,desc,distinct,drop,each,end,escape,except,exclusive,exists,foreign,from,glob,group,having,in,index,inner,insert,intersect,into,is,isnull,join,like,limit,match,natural,not,notnull,null,of,offset,on,or,order,outer,plan,pragma,primary,query,raise,references,regexp,reindex,release,rename,replace,restrict,right,rollback,row,savepoint,select,set,table,temp,temporary,then,to,transaction,trigger,union,unique,update,using,vacuum,values,view,virtual,when,where,";
    private static final String TAG = "DBUtility";

    private DBUtility() {
    }

    public static String getTableNameByClassName(String str) {
        if (TextUtils.isEmpty(str) || '.' == str.charAt(str.length() - 1)) {
            return null;
        }
        return str.substring(str.lastIndexOf(".") + 1);
    }

    public static List<String> getTableNameListByClassNameList(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            for (String tableNameByClassName : list) {
                arrayList.add(getTableNameByClassName(tableNameByClassName));
            }
        }
        return arrayList;
    }

    public static String getTableNameByForeignColumn(String str) {
        if (TextUtils.isEmpty(str) || !str.toLowerCase().endsWith("_id")) {
            return null;
        }
        return str.substring(0, str.length() - "_id".length());
    }

    public static String getIntermediateTableName(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        if (str.toLowerCase().compareTo(str2.toLowerCase()) <= 0) {
            return str + "_" + str2;
        }
        return str2 + "_" + str;
    }

    public static String getGenericTableName(String str, String str2) {
        return BaseUtility.changeCase(getTableNameByClassName(str) + "_" + str2);
    }

    public static String getGenericValueIdColumnName(String str) {
        return BaseUtility.changeCase(getTableNameByClassName(str) + "_id");
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0062  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isIntermediateTable(java.lang.String r10, android.database.sqlite.SQLiteDatabase r11) {
        /*
            r8 = 1
            r9 = 0
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            if (r0 != 0) goto L_0x0051
            java.lang.String r0 = "[0-9a-zA-Z]+_[0-9a-zA-Z]+"
            boolean r0 = r10.matches(r0)
            if (r0 == 0) goto L_0x0051
            java.lang.String r1 = "table_schema"
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r0 = r11
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0053, all -> 0x005e }
            boolean r0 = r1.moveToFirst()     // Catch:{ Exception -> 0x0068 }
            if (r0 == 0) goto L_0x004c
        L_0x0023:
            java.lang.String r0 = "name"
            int r0 = r1.getColumnIndexOrThrow(r0)     // Catch:{ Exception -> 0x0068 }
            java.lang.String r0 = r1.getString(r0)     // Catch:{ Exception -> 0x0068 }
            boolean r0 = r10.equalsIgnoreCase(r0)     // Catch:{ Exception -> 0x0068 }
            if (r0 == 0) goto L_0x0046
            java.lang.String r0 = "type"
            int r0 = r1.getColumnIndexOrThrow(r0)     // Catch:{ Exception -> 0x0068 }
            int r0 = r1.getInt(r0)     // Catch:{ Exception -> 0x0068 }
            if (r0 != r8) goto L_0x004c
            if (r1 == 0) goto L_0x0044
            r1.close()
        L_0x0044:
            r0 = r8
        L_0x0045:
            return r0
        L_0x0046:
            boolean r0 = r1.moveToNext()     // Catch:{ Exception -> 0x0068 }
            if (r0 != 0) goto L_0x0023
        L_0x004c:
            if (r1 == 0) goto L_0x0051
            r1.close()
        L_0x0051:
            r0 = 0
            goto L_0x0045
        L_0x0053:
            r0 = move-exception
            r1 = r9
        L_0x0055:
            r0.printStackTrace()     // Catch:{ all -> 0x0066 }
            if (r1 == 0) goto L_0x0051
            r1.close()
            goto L_0x0051
        L_0x005e:
            r0 = move-exception
            r1 = r9
        L_0x0060:
            if (r1 == 0) goto L_0x0065
            r1.close()
        L_0x0065:
            throw r0
        L_0x0066:
            r0 = move-exception
            goto L_0x0060
        L_0x0068:
            r0 = move-exception
            goto L_0x0055
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.util.DBUtility.isIntermediateTable(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0062  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isGenericTable(java.lang.String r9, android.database.sqlite.SQLiteDatabase r10) {
        /*
            r8 = 0
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            if (r0 != 0) goto L_0x0051
            java.lang.String r0 = "[0-9a-zA-Z]+_[0-9a-zA-Z]+"
            boolean r0 = r9.matches(r0)
            if (r0 == 0) goto L_0x0051
            java.lang.String r1 = "table_schema"
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r0 = r10
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0053, all -> 0x005e }
            boolean r0 = r1.moveToFirst()     // Catch:{ Exception -> 0x0068 }
            if (r0 == 0) goto L_0x004c
        L_0x0022:
            java.lang.String r0 = "name"
            int r0 = r1.getColumnIndexOrThrow(r0)     // Catch:{ Exception -> 0x0068 }
            java.lang.String r0 = r1.getString(r0)     // Catch:{ Exception -> 0x0068 }
            boolean r0 = r9.equalsIgnoreCase(r0)     // Catch:{ Exception -> 0x0068 }
            if (r0 == 0) goto L_0x0046
            java.lang.String r0 = "type"
            int r0 = r1.getColumnIndexOrThrow(r0)     // Catch:{ Exception -> 0x0068 }
            int r0 = r1.getInt(r0)     // Catch:{ Exception -> 0x0068 }
            r2 = 2
            if (r0 != r2) goto L_0x004c
            r0 = 1
            if (r1 == 0) goto L_0x0045
            r1.close()
        L_0x0045:
            return r0
        L_0x0046:
            boolean r0 = r1.moveToNext()     // Catch:{ Exception -> 0x0068 }
            if (r0 != 0) goto L_0x0022
        L_0x004c:
            if (r1 == 0) goto L_0x0051
            r1.close()
        L_0x0051:
            r0 = 0
            goto L_0x0045
        L_0x0053:
            r0 = move-exception
            r1 = r8
        L_0x0055:
            r0.printStackTrace()     // Catch:{ all -> 0x0066 }
            if (r1 == 0) goto L_0x0051
            r1.close()
            goto L_0x0051
        L_0x005e:
            r0 = move-exception
            r1 = r8
        L_0x0060:
            if (r1 == 0) goto L_0x0065
            r1.close()
        L_0x0065:
            throw r0
        L_0x0066:
            r0 = move-exception
            goto L_0x0060
        L_0x0068:
            r0 = move-exception
            goto L_0x0055
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.util.DBUtility.isGenericTable(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
    }

    public static boolean isTableExists(String str, SQLiteDatabase sQLiteDatabase) {
        try {
            return BaseUtility.containsIgnoreCases(findAllTableNames(sQLiteDatabase), str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isColumnExists(String str, String str2, SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        boolean z = false;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                cursor = sQLiteDatabase.rawQuery("pragma table_info(" + str2 + ")", null);
                if (cursor.moveToFirst()) {
                    while (true) {
                        if (!str.equalsIgnoreCase(cursor.getString(cursor.getColumnIndexOrThrow("name")))) {
                            if (!cursor.moveToNext()) {
                                break;
                            }
                        } else {
                            z = true;
                            break;
                        }
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        return z;
    }

    public static List<String> findAllTableNames(SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            Cursor rawQuery = sQLiteDatabase.rawQuery("select * from sqlite_master where type = ?", new String[]{"table"});
            if (rawQuery.moveToFirst()) {
                do {
                    String string = rawQuery.getString(rawQuery.getColumnIndexOrThrow("tbl_name"));
                    if (!arrayList.contains(string)) {
                        arrayList.add(string);
                    }
                } while (rawQuery.moveToNext());
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseGenerateException(e.getMessage());
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static TableModel findPragmaTableInfo(String str, SQLiteDatabase sQLiteDatabase) {
        String str2;
        Cursor cursor = null;
        if (isTableExists(str, sQLiteDatabase)) {
            List findUniqueColumns = findUniqueColumns(str, sQLiteDatabase);
            TableModel tableModel = new TableModel();
            tableModel.setTableName(str);
            try {
                Cursor rawQuery = sQLiteDatabase.rawQuery("pragma table_info(" + str + ")", null);
                if (rawQuery.moveToFirst()) {
                    do {
                        ColumnModel columnModel = new ColumnModel();
                        String string = rawQuery.getString(rawQuery.getColumnIndexOrThrow("name"));
                        String string2 = rawQuery.getString(rawQuery.getColumnIndexOrThrow("type"));
                        boolean z = rawQuery.getInt(rawQuery.getColumnIndexOrThrow("notnull")) != 1;
                        boolean contains = findUniqueColumns.contains(string);
                        String string3 = rawQuery.getString(rawQuery.getColumnIndexOrThrow("dflt_value"));
                        columnModel.setColumnName(string);
                        columnModel.setColumnType(string2);
                        columnModel.setNullable(z);
                        columnModel.setUnique(contains);
                        if (string3 != null) {
                            str2 = string3.replace(JSONUtils.SINGLE_QUOTE, "");
                        } else {
                            str2 = "";
                        }
                        columnModel.setDefaultValue(str2);
                        tableModel.addColumnModel(columnModel);
                    } while (rawQuery.moveToNext());
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return tableModel;
            } catch (Exception e) {
                e.printStackTrace();
                throw new DatabaseGenerateException(e.getMessage());
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } else {
            throw new DatabaseGenerateException(DatabaseGenerateException.TABLE_DOES_NOT_EXIST_WHEN_EXECUTING + str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<java.lang.String> findUniqueColumns(java.lang.String r6, android.database.sqlite.SQLiteDatabase r7) {
        /*
            r1 = 0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0083, all -> 0x009e }
            r2.<init>()     // Catch:{ Exception -> 0x0083, all -> 0x009e }
            java.lang.String r3 = "pragma index_list("
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x0083, all -> 0x009e }
            java.lang.StringBuilder r2 = r2.append(r6)     // Catch:{ Exception -> 0x0083, all -> 0x009e }
            java.lang.String r3 = ")"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x0083, all -> 0x009e }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0083, all -> 0x009e }
            r3 = 0
            android.database.Cursor r2 = r7.rawQuery(r2, r3)     // Catch:{ Exception -> 0x0083, all -> 0x009e }
            boolean r3 = r2.moveToFirst()     // Catch:{ Exception -> 0x00a1 }
            if (r3 == 0) goto L_0x0078
        L_0x002a:
            java.lang.String r3 = "unique"
            int r3 = r2.getColumnIndexOrThrow(r3)     // Catch:{ Exception -> 0x00a1 }
            int r3 = r2.getInt(r3)     // Catch:{ Exception -> 0x00a1 }
            r4 = 1
            if (r3 != r4) goto L_0x0072
            java.lang.String r3 = "name"
            int r3 = r2.getColumnIndexOrThrow(r3)     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r3 = r2.getString(r3)     // Catch:{ Exception -> 0x00a1 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a1 }
            r4.<init>()     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r5 = "pragma index_info("
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Exception -> 0x00a1 }
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r4 = ")"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00a1 }
            r4 = 0
            android.database.Cursor r1 = r7.rawQuery(r3, r4)     // Catch:{ Exception -> 0x00a1 }
            boolean r3 = r1.moveToFirst()     // Catch:{ Exception -> 0x00a1 }
            if (r3 == 0) goto L_0x0072
            java.lang.String r3 = "name"
            int r3 = r1.getColumnIndexOrThrow(r3)     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r3 = r1.getString(r3)     // Catch:{ Exception -> 0x00a1 }
            r0.add(r3)     // Catch:{ Exception -> 0x00a1 }
        L_0x0072:
            boolean r3 = r2.moveToNext()     // Catch:{ Exception -> 0x00a1 }
            if (r3 != 0) goto L_0x002a
        L_0x0078:
            if (r2 == 0) goto L_0x007d
            r2.close()
        L_0x007d:
            if (r1 == 0) goto L_0x0082
            r1.close()
        L_0x0082:
            return r0
        L_0x0083:
            r0 = move-exception
            r2 = r1
        L_0x0085:
            r0.printStackTrace()     // Catch:{ all -> 0x0092 }
            org.litepal.exceptions.DatabaseGenerateException r3 = new org.litepal.exceptions.DatabaseGenerateException     // Catch:{ all -> 0x0092 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0092 }
            r3.<init>(r0)     // Catch:{ all -> 0x0092 }
            throw r3     // Catch:{ all -> 0x0092 }
        L_0x0092:
            r0 = move-exception
        L_0x0093:
            if (r2 == 0) goto L_0x0098
            r2.close()
        L_0x0098:
            if (r1 == 0) goto L_0x009d
            r1.close()
        L_0x009d:
            throw r0
        L_0x009e:
            r0 = move-exception
            r2 = r1
            goto L_0x0093
        L_0x00a1:
            r0 = move-exception
            goto L_0x0085
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.util.DBUtility.findUniqueColumns(java.lang.String, android.database.sqlite.SQLiteDatabase):java.util.List");
    }

    public static boolean isFieldNameConflictWithSQLiteKeywords(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (SQLITE_KEYWORDS.contains(StorageInterface.KEY_SPLITER + str.toLowerCase() + StorageInterface.KEY_SPLITER)) {
                return true;
            }
        }
        return false;
    }

    public static String convertToValidColumnName(String str) {
        if (isFieldNameConflictWithSQLiteKeywords(str)) {
            return str + KEYWORDS_COLUMN_SUFFIX;
        }
        return str;
    }

    public static String convertWhereClauseToColumnName(String str) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            Matcher matcher = Pattern.compile("(\\w+\\s*(=|!=|<>|<|>)|\\w+\\s+(not\\s+)?(like|between)\\s+|\\w+\\s+(not\\s+)?(in|exists)\\s*\\()").matcher(str);
            while (matcher.find()) {
                String group = matcher.group();
                String replaceAll = group.replaceAll("(\\s*(=|!=|<>|<|>)|\\s+(not\\s+)?(like|between)\\s+|\\s+(not\\s+)?(in|exists)\\s*\\()", "");
                matcher.appendReplacement(stringBuffer, convertToValidColumnName(replaceAll) + group.replace(replaceAll, ""));
            }
            matcher.appendTail(stringBuffer);
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String[] convertSelectClauseToValidNames(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr2[i] = convertToValidColumnName(strArr[i]);
        }
        return strArr2;
    }

    public static String convertOrderByClauseToValidName(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String lowerCase = str.trim().toLowerCase();
        if (!lowerCase.contains(StorageInterface.KEY_SPLITER)) {
            return convertOrderByItem(lowerCase);
        }
        String[] split = lowerCase.split(StorageInterface.KEY_SPLITER);
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (String str2 : split) {
            if (z) {
                sb.append(StorageInterface.KEY_SPLITER);
            }
            sb.append(convertOrderByItem(str2));
            z = true;
        }
        return sb.toString();
    }

    private static String convertOrderByItem(String str) {
        String str2;
        if (str.endsWith("asc")) {
            str = str.replace("asc", "").trim();
            str2 = " asc";
        } else if (str.endsWith(OutUrlActivity.ARG_DESC)) {
            str = str.replace(OutUrlActivity.ARG_DESC, "").trim();
            str2 = " desc";
        } else {
            str2 = "";
        }
        return convertToValidColumnName(str) + str2;
    }
}
