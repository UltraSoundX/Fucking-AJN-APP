package org.litepal.tablemanager;

import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.litepal.tablemanager.model.TableModel;
import org.litepal.util.BaseUtility;

public class Dropper extends AssociationUpdater {
    private Collection<TableModel> mTableModels;

    /* access modifiers changed from: protected */
    public void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        this.mTableModels = getAllTableModels();
        this.mDb = sQLiteDatabase;
        dropTables();
    }

    private void dropTables() {
        List findTablesToDrop = findTablesToDrop();
        dropTables(findTablesToDrop, this.mDb);
        clearCopyInTableSchema(findTablesToDrop);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x006a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<java.lang.String> findTablesToDrop() {
        /*
            r10 = this;
            r8 = 0
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            android.database.sqlite.SQLiteDatabase r0 = r10.mDb     // Catch:{ Exception -> 0x005b, all -> 0x0066 }
            java.lang.String r1 = "table_schema"
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x005b, all -> 0x0066 }
            boolean r0 = r1.moveToFirst()     // Catch:{ Exception -> 0x0070 }
            if (r0 == 0) goto L_0x0055
        L_0x001a:
            java.lang.String r0 = "name"
            int r0 = r1.getColumnIndexOrThrow(r0)     // Catch:{ Exception -> 0x0070 }
            java.lang.String r0 = r1.getString(r0)     // Catch:{ Exception -> 0x0070 }
            java.lang.String r2 = "type"
            int r2 = r1.getColumnIndexOrThrow(r2)     // Catch:{ Exception -> 0x0070 }
            int r2 = r1.getInt(r2)     // Catch:{ Exception -> 0x0070 }
            boolean r2 = r10.shouldDropThisTable(r0, r2)     // Catch:{ Exception -> 0x0070 }
            if (r2 == 0) goto L_0x004f
            java.lang.String r2 = "AssociationUpdater"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0070 }
            r3.<init>()     // Catch:{ Exception -> 0x0070 }
            java.lang.String r4 = "need to drop "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x0070 }
            java.lang.StringBuilder r3 = r3.append(r0)     // Catch:{ Exception -> 0x0070 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0070 }
            org.litepal.util.LogUtil.d(r2, r3)     // Catch:{ Exception -> 0x0070 }
            r9.add(r0)     // Catch:{ Exception -> 0x0070 }
        L_0x004f:
            boolean r0 = r1.moveToNext()     // Catch:{ Exception -> 0x0070 }
            if (r0 != 0) goto L_0x001a
        L_0x0055:
            if (r1 == 0) goto L_0x005a
            r1.close()
        L_0x005a:
            return r9
        L_0x005b:
            r0 = move-exception
            r1 = r8
        L_0x005d:
            r0.printStackTrace()     // Catch:{ all -> 0x006e }
            if (r1 == 0) goto L_0x005a
            r1.close()
            goto L_0x005a
        L_0x0066:
            r0 = move-exception
            r1 = r8
        L_0x0068:
            if (r1 == 0) goto L_0x006d
            r1.close()
        L_0x006d:
            throw r0
        L_0x006e:
            r0 = move-exception
            goto L_0x0068
        L_0x0070:
            r0 = move-exception
            goto L_0x005d
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.tablemanager.Dropper.findTablesToDrop():java.util.List");
    }

    private List<String> pickTableNamesFromTableModels() {
        ArrayList arrayList = new ArrayList();
        for (TableModel tableName : this.mTableModels) {
            arrayList.add(tableName.getTableName());
        }
        return arrayList;
    }

    private boolean shouldDropThisTable(String str, int i) {
        return !BaseUtility.containsIgnoreCases(pickTableNamesFromTableModels(), str) && i == 0;
    }
}
