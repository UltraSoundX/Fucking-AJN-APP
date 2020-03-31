package org.litepal.tablemanager;

import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.litepal.parser.LitePalAttr;
import org.litepal.tablemanager.model.AssociationsModel;
import org.litepal.tablemanager.model.ColumnModel;
import org.litepal.tablemanager.model.GenericModel;
import org.litepal.tablemanager.model.TableModel;
import org.litepal.util.BaseUtility;
import org.litepal.util.Const.TableSchema;
import org.litepal.util.DBUtility;
import org.litepal.util.LogUtil;

public abstract class AssociationUpdater extends Creator {
    public static final String TAG = "AssociationUpdater";
    private Collection<AssociationsModel> mAssociationModels;
    protected SQLiteDatabase mDb;

    /* access modifiers changed from: protected */
    public abstract void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z);

    /* access modifiers changed from: protected */
    public void addOrUpdateAssociation(SQLiteDatabase sQLiteDatabase, boolean z) {
        this.mAssociationModels = getAllAssociations();
        this.mDb = sQLiteDatabase;
        removeAssociations();
    }

    /* access modifiers changed from: protected */
    public List<String> getForeignKeyColumns(TableModel tableModel) {
        ArrayList arrayList = new ArrayList();
        for (ColumnModel columnModel : getTableModelFromDB(tableModel.getTableName()).getColumnModels()) {
            String columnName = columnModel.getColumnName();
            if (isForeignKeyColumnFormat(columnModel.getColumnName()) && !tableModel.containsColumn(columnName)) {
                LogUtil.d(TAG, "getForeignKeyColumnNames >> foreign key column is " + columnName);
                arrayList.add(columnName);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public boolean isForeignKeyColumn(TableModel tableModel, String str) {
        return BaseUtility.containsIgnoreCases(getForeignKeyColumns(tableModel), str);
    }

    /* access modifiers changed from: protected */
    public TableModel getTableModelFromDB(String str) {
        return DBUtility.findPragmaTableInfo(str, this.mDb);
    }

    /* access modifiers changed from: protected */
    public void dropTables(List<String> list, SQLiteDatabase sQLiteDatabase) {
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < list.size()) {
                    arrayList.add(generateDropTableSQL((String) list.get(i2)));
                    i = i2 + 1;
                } else {
                    execute(arrayList, sQLiteDatabase);
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void removeColumns(Collection<String> collection, String str) {
        if (collection != null && !collection.isEmpty()) {
            execute(getRemoveColumnSQLs(collection, str), this.mDb);
        }
    }

    /* access modifiers changed from: protected */
    public void clearCopyInTableSchema(List<String> list) {
        if (list != null && !list.isEmpty()) {
            StringBuilder sb = new StringBuilder("delete from ");
            sb.append(TableSchema.TABLE_NAME).append(" where");
            boolean z = false;
            for (String str : list) {
                if (z) {
                    sb.append(" or ");
                }
                z = true;
                sb.append(" lower(").append("name").append(") ");
                sb.append("=").append(" lower('").append(str).append("')");
            }
            LogUtil.d(TAG, "clear table schema value sql is " + sb);
            ArrayList arrayList = new ArrayList();
            arrayList.add(sb.toString());
            execute(arrayList, this.mDb);
        }
    }

    private void removeAssociations() {
        removeForeignKeyColumns();
        removeIntermediateTables();
        removeGenericTables();
    }

    private void removeForeignKeyColumns() {
        for (String tableModel : LitePalAttr.getInstance().getClassNames()) {
            TableModel tableModel2 = getTableModel(tableModel);
            removeColumns(findForeignKeyToRemove(tableModel2), tableModel2.getTableName());
        }
    }

    private void removeIntermediateTables() {
        List findIntermediateTablesToDrop = findIntermediateTablesToDrop();
        dropTables(findIntermediateTablesToDrop, this.mDb);
        clearCopyInTableSchema(findIntermediateTablesToDrop);
    }

    private void removeGenericTables() {
        List findGenericTablesToDrop = findGenericTablesToDrop();
        dropTables(findGenericTablesToDrop, this.mDb);
        clearCopyInTableSchema(findGenericTablesToDrop);
    }

    private List<String> findForeignKeyToRemove(TableModel tableModel) {
        ArrayList arrayList = new ArrayList();
        List<String> foreignKeyColumns = getForeignKeyColumns(tableModel);
        String tableName = tableModel.getTableName();
        for (String str : foreignKeyColumns) {
            if (shouldDropForeignKey(tableName, DBUtility.getTableNameByForeignColumn(str))) {
                arrayList.add(str);
            }
        }
        LogUtil.d(TAG, "findForeignKeyToRemove >> " + tableModel.getTableName() + " " + arrayList);
        return arrayList;
    }

    private List<String> findIntermediateTablesToDrop() {
        boolean z;
        ArrayList arrayList = new ArrayList();
        for (String str : DBUtility.findAllTableNames(this.mDb)) {
            if (DBUtility.isIntermediateTable(str, this.mDb)) {
                boolean z2 = true;
                Iterator it = this.mAssociationModels.iterator();
                while (true) {
                    z = z2;
                    if (!it.hasNext()) {
                        break;
                    }
                    AssociationsModel associationsModel = (AssociationsModel) it.next();
                    if (associationsModel.getAssociationType() == 3 && str.equalsIgnoreCase(DBUtility.getIntermediateTableName(associationsModel.getTableName(), associationsModel.getAssociatedTableName()))) {
                        z = false;
                    }
                    z2 = z;
                }
                if (z) {
                    arrayList.add(str);
                }
            }
        }
        LogUtil.d(TAG, "findIntermediateTablesToDrop >> " + arrayList);
        return arrayList;
    }

    private List<String> findGenericTablesToDrop() {
        boolean z;
        ArrayList arrayList = new ArrayList();
        for (String str : DBUtility.findAllTableNames(this.mDb)) {
            if (DBUtility.isGenericTable(str, this.mDb)) {
                boolean z2 = true;
                Iterator it = getGenericModels().iterator();
                while (true) {
                    z = z2;
                    if (!it.hasNext()) {
                        break;
                    } else if (str.equalsIgnoreCase(((GenericModel) it.next()).getTableName())) {
                        z2 = false;
                    } else {
                        z2 = z;
                    }
                }
                if (z) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public String generateAlterToTempTableSQL(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("alter table ").append(str).append(" rename to ").append(getTempTableName(str));
        return sb.toString();
    }

    private String generateCreateNewTableSQL(Collection<String> collection, TableModel tableModel) {
        for (String removeColumnModelByName : collection) {
            tableModel.removeColumnModelByName(removeColumnModelByName);
        }
        return generateCreateTableSQL(tableModel);
    }

    /* access modifiers changed from: protected */
    public String generateDataMigrationSQL(TableModel tableModel) {
        boolean z = false;
        String tableName = tableModel.getTableName();
        List<ColumnModel> columnModels = tableModel.getColumnModels();
        if (columnModels.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ").append(tableName).append("(");
        boolean z2 = false;
        for (ColumnModel columnModel : columnModels) {
            if (z2) {
                sb.append(", ");
            }
            sb.append(columnModel.getColumnName());
            z2 = true;
        }
        sb.append(") ");
        sb.append("select ");
        for (ColumnModel columnModel2 : columnModels) {
            if (z) {
                sb.append(", ");
            }
            sb.append(columnModel2.getColumnName());
            z = true;
        }
        sb.append(" from ").append(getTempTableName(tableName));
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public String generateDropTempTableSQL(String str) {
        return generateDropTableSQL(getTempTableName(str));
    }

    /* access modifiers changed from: protected */
    public String getTempTableName(String str) {
        return str + "_temp";
    }

    private List<String> getRemoveColumnSQLs(Collection<String> collection, String str) {
        TableModel tableModelFromDB = getTableModelFromDB(str);
        String generateAlterToTempTableSQL = generateAlterToTempTableSQL(str);
        LogUtil.d(TAG, "generateRemoveColumnSQL >> " + generateAlterToTempTableSQL);
        String generateCreateNewTableSQL = generateCreateNewTableSQL(collection, tableModelFromDB);
        LogUtil.d(TAG, "generateRemoveColumnSQL >> " + generateCreateNewTableSQL);
        String generateDataMigrationSQL = generateDataMigrationSQL(tableModelFromDB);
        LogUtil.d(TAG, "generateRemoveColumnSQL >> " + generateDataMigrationSQL);
        String generateDropTempTableSQL = generateDropTempTableSQL(str);
        LogUtil.d(TAG, "generateRemoveColumnSQL >> " + generateDropTempTableSQL);
        ArrayList arrayList = new ArrayList();
        arrayList.add(generateAlterToTempTableSQL);
        arrayList.add(generateCreateNewTableSQL);
        arrayList.add(generateDataMigrationSQL);
        arrayList.add(generateDropTempTableSQL);
        return arrayList;
    }

    private boolean shouldDropForeignKey(String str, String str2) {
        for (AssociationsModel associationsModel : this.mAssociationModels) {
            if (associationsModel.getAssociationType() == 1) {
                if (!str.equalsIgnoreCase(associationsModel.getTableHoldsForeignKey())) {
                    continue;
                } else if (associationsModel.getTableName().equalsIgnoreCase(str)) {
                    if (isRelationCorrect(associationsModel, str, str2)) {
                        return false;
                    }
                } else if (associationsModel.getAssociatedTableName().equalsIgnoreCase(str) && isRelationCorrect(associationsModel, str2, str)) {
                    return false;
                }
            } else if (associationsModel.getAssociationType() == 2 && isRelationCorrect(associationsModel, str2, str)) {
                return false;
            }
        }
        return true;
    }

    private boolean isRelationCorrect(AssociationsModel associationsModel, String str, String str2) {
        return associationsModel.getTableName().equalsIgnoreCase(str) && associationsModel.getAssociatedTableName().equalsIgnoreCase(str2);
    }
}
