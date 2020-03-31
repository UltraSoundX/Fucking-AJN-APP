package org.litepal.tablemanager;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.litepal.LitePalBase;
import org.litepal.exceptions.DatabaseGenerateException;
import org.litepal.parser.LitePalAttr;
import org.litepal.tablemanager.model.AssociationsModel;
import org.litepal.tablemanager.model.TableModel;
import org.litepal.util.BaseUtility;

public abstract class Generator extends LitePalBase {
    public static final String TAG = "Generator";
    private Collection<AssociationsModel> mAllRelationModels;
    private Collection<TableModel> mTableModels;

    /* access modifiers changed from: protected */
    public abstract void addOrUpdateAssociation(SQLiteDatabase sQLiteDatabase, boolean z);

    /* access modifiers changed from: protected */
    public abstract void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z);

    /* access modifiers changed from: protected */
    public Collection<TableModel> getAllTableModels() {
        if (this.mTableModels == null) {
            this.mTableModels = new ArrayList();
        }
        if (!canUseCache()) {
            this.mTableModels.clear();
            for (String tableModel : LitePalAttr.getInstance().getClassNames()) {
                this.mTableModels.add(getTableModel(tableModel));
            }
        }
        return this.mTableModels;
    }

    /* access modifiers changed from: protected */
    public Collection<AssociationsModel> getAllAssociations() {
        if (this.mAllRelationModels == null || this.mAllRelationModels.isEmpty()) {
            this.mAllRelationModels = getAssociations(LitePalAttr.getInstance().getClassNames());
        }
        return this.mAllRelationModels;
    }

    /* access modifiers changed from: protected */
    public void execute(List<String> list, SQLiteDatabase sQLiteDatabase) {
        String str;
        String str2 = "";
        if (list != null) {
            try {
                if (!list.isEmpty()) {
                    for (String str3 : list) {
                        if (!TextUtils.isEmpty(str3)) {
                            str = BaseUtility.changeCase(str3);
                            try {
                                sQLiteDatabase.execSQL(str);
                            } catch (SQLException e) {
                                str2 = str;
                            }
                        } else {
                            str = str2;
                        }
                        str2 = str;
                    }
                }
            } catch (SQLException e2) {
                throw new DatabaseGenerateException(DatabaseGenerateException.SQL_ERROR + str2);
            }
        }
    }

    private static void addAssociation(SQLiteDatabase sQLiteDatabase, boolean z) {
        new Creator().addOrUpdateAssociation(sQLiteDatabase, z);
    }

    private static void updateAssociations(SQLiteDatabase sQLiteDatabase) {
        new Upgrader().addOrUpdateAssociation(sQLiteDatabase, false);
    }

    private static void upgradeTables(SQLiteDatabase sQLiteDatabase) {
        new Upgrader().createOrUpgradeTable(sQLiteDatabase, false);
    }

    private static void create(SQLiteDatabase sQLiteDatabase, boolean z) {
        new Creator().createOrUpgradeTable(sQLiteDatabase, z);
    }

    private static void drop(SQLiteDatabase sQLiteDatabase) {
        new Dropper().createOrUpgradeTable(sQLiteDatabase, false);
    }

    private boolean canUseCache() {
        if (this.mTableModels != null && this.mTableModels.size() == LitePalAttr.getInstance().getClassNames().size()) {
            return true;
        }
        return false;
    }

    static void create(SQLiteDatabase sQLiteDatabase) {
        create(sQLiteDatabase, true);
        addAssociation(sQLiteDatabase, true);
    }

    static void upgrade(SQLiteDatabase sQLiteDatabase) {
        drop(sQLiteDatabase);
        create(sQLiteDatabase, false);
        updateAssociations(sQLiteDatabase);
        upgradeTables(sQLiteDatabase);
        addAssociation(sQLiteDatabase, false);
    }
}
