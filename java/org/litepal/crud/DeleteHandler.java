package org.litepal.crud;

import android.database.sqlite.SQLiteDatabase;
import com.baidu.mobstat.Config;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.sf.json.util.JSONUtils;
import org.litepal.crud.model.AssociationsInfo;
import org.litepal.exceptions.DataSupportException;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;

class DeleteHandler extends DataHandler {
    private List<String> foreignKeyTableToDelete;

    DeleteHandler(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    /* access modifiers changed from: 0000 */
    public int onDelete(DataSupport dataSupport) {
        if (!dataSupport.isSaved()) {
            return 0;
        }
        List supportedGenericFields = getSupportedGenericFields(dataSupport.getClassName());
        deleteGenericData(dataSupport.getClass(), supportedGenericFields, dataSupport.getBaseObjId());
        int deleteCascade = deleteCascade(dataSupport) + this.mDatabase.delete(dataSupport.getTableName(), "id = " + dataSupport.getBaseObjId(), null);
        clearAssociatedModelSaveState(dataSupport, analyzeAssociations(dataSupport));
        return deleteCascade;
    }

    /* access modifiers changed from: 0000 */
    public int onDelete(Class<?> cls, long j) {
        deleteGenericData(cls, getSupportedGenericFields(cls.getName()), j);
        analyzeAssociations(cls);
        int deleteCascade = deleteCascade(cls, j) + this.mDatabase.delete(getTableName(cls), "id = " + j, null);
        getForeignKeyTableToDelete().clear();
        return deleteCascade;
    }

    /* access modifiers changed from: 0000 */
    public int onDeleteAll(String str, String... strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        return this.mDatabase.delete(str, getWhereClause(strArr), getWhereArgs(strArr));
    }

    /* access modifiers changed from: 0000 */
    public int onDeleteAll(Class<?> cls, String... strArr) {
        int i = 0;
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        List supportedGenericFields = getSupportedGenericFields(cls.getName());
        if (!supportedGenericFields.isEmpty()) {
            List find = DataSupport.select(Config.FEED_LIST_ITEM_CUSTOM_ID).where(strArr).find(cls);
            if (find.size() > 0) {
                long[] jArr = new long[find.size()];
                while (true) {
                    int i2 = i;
                    if (i2 >= jArr.length) {
                        break;
                    }
                    jArr[i2] = ((DataSupport) find.get(i2)).getBaseObjId();
                    i = i2 + 1;
                }
                deleteGenericData(cls, supportedGenericFields, jArr);
            }
        }
        analyzeAssociations(cls);
        int deleteAllCascade = deleteAllCascade(cls, strArr) + this.mDatabase.delete(getTableName(cls), getWhereClause(strArr), getWhereArgs(strArr));
        getForeignKeyTableToDelete().clear();
        return deleteAllCascade;
    }

    private void analyzeAssociations(Class<?> cls) {
        for (AssociationsInfo associationsInfo : getAssociationInfo(cls.getName())) {
            String tableNameByClassName = DBUtility.getTableNameByClassName(associationsInfo.getAssociatedClassName());
            if (associationsInfo.getAssociationType() == 2 || associationsInfo.getAssociationType() == 1) {
                if (!cls.getName().equals(associationsInfo.getClassHoldsForeignKey())) {
                    getForeignKeyTableToDelete().add(tableNameByClassName);
                }
            } else if (associationsInfo.getAssociationType() == 3) {
                getForeignKeyTableToDelete().add(BaseUtility.changeCase(DBUtility.getIntermediateTableName(getTableName(cls), tableNameByClassName)));
            }
        }
    }

    private int deleteCascade(Class<?> cls, long j) {
        int i = 0;
        Iterator it = getForeignKeyTableToDelete().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = this.mDatabase.delete((String) it.next(), getForeignKeyColumnName(getTableName(cls)) + " = " + j, null) + i2;
        }
    }

    private int deleteAllCascade(Class<?> cls, String... strArr) {
        int i = 0;
        Iterator it = getForeignKeyTableToDelete().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            String str = (String) it.next();
            String tableName = getTableName(cls);
            String foreignKeyColumnName = getForeignKeyColumnName(tableName);
            StringBuilder sb = new StringBuilder();
            sb.append(foreignKeyColumnName).append(" in (select id from ");
            sb.append(tableName);
            if (strArr != null && strArr.length > 0) {
                sb.append(" where ").append(buildConditionString(strArr));
            }
            sb.append(")");
            i = this.mDatabase.delete(str, BaseUtility.changeCase(sb.toString()), null) + i2;
        }
    }

    private String buildConditionString(String... strArr) {
        int length = strArr.length - 1;
        String str = strArr[0];
        for (int i = 0; i < length; i++) {
            str = str.replaceFirst("\\?", JSONUtils.SINGLE_QUOTE + strArr[i + 1] + JSONUtils.SINGLE_QUOTE);
        }
        return str;
    }

    private Collection<AssociationsInfo> analyzeAssociations(DataSupport dataSupport) {
        try {
            Collection<AssociationsInfo> associationInfo = getAssociationInfo(dataSupport.getClassName());
            analyzeAssociatedModels(dataSupport, associationInfo);
            return associationInfo;
        } catch (Exception e) {
            throw new DataSupportException(e.getMessage(), e);
        }
    }

    private void clearAssociatedModelSaveState(DataSupport dataSupport, Collection<AssociationsInfo> collection) {
        try {
            for (AssociationsInfo associationsInfo : collection) {
                if (associationsInfo.getAssociationType() == 2 && !dataSupport.getClassName().equals(associationsInfo.getClassHoldsForeignKey())) {
                    Collection<DataSupport> associatedModels = getAssociatedModels(dataSupport, associationsInfo);
                    if (associatedModels != null && !associatedModels.isEmpty()) {
                        for (DataSupport dataSupport2 : associatedModels) {
                            if (dataSupport2 != null) {
                                dataSupport2.clearSavedState();
                            }
                        }
                    }
                } else if (associationsInfo.getAssociationType() == 1) {
                    DataSupport associatedModel = getAssociatedModel(dataSupport, associationsInfo);
                    if (associatedModel != null) {
                        associatedModel.clearSavedState();
                    }
                }
            }
        } catch (Exception e) {
            throw new DataSupportException(e.getMessage(), e);
        }
    }

    private int deleteCascade(DataSupport dataSupport) {
        return deleteAssociatedForeignKeyRows(dataSupport) + deleteAssociatedJoinTableRows(dataSupport);
    }

    private int deleteAssociatedForeignKeyRows(DataSupport dataSupport) {
        int i = 0;
        Iterator it = dataSupport.getAssociatedModelsMapWithFK().keySet().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = this.mDatabase.delete((String) it.next(), getForeignKeyColumnName(dataSupport.getTableName()) + " = " + dataSupport.getBaseObjId(), null) + i2;
        }
    }

    private int deleteAssociatedJoinTableRows(DataSupport dataSupport) {
        int i = 0;
        Iterator it = dataSupport.getAssociatedModelsMapForJoinTable().keySet().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = this.mDatabase.delete(DBUtility.getIntermediateTableName(dataSupport.getTableName(), (String) it.next()), getForeignKeyColumnName(dataSupport.getTableName()) + " = " + dataSupport.getBaseObjId(), null) + i2;
        }
    }

    private List<String> getForeignKeyTableToDelete() {
        if (this.foreignKeyTableToDelete == null) {
            this.foreignKeyTableToDelete = new ArrayList();
        }
        return this.foreignKeyTableToDelete;
    }

    private void deleteGenericData(Class<?> cls, List<Field> list, long... jArr) {
        for (Field name : list) {
            String genericTableName = DBUtility.getGenericTableName(cls.getName(), name.getName());
            String genericValueIdColumnName = DBUtility.getGenericValueIdColumnName(cls.getName());
            StringBuilder sb = new StringBuilder();
            boolean z = false;
            for (long j : jArr) {
                if (z) {
                    sb.append(" or ");
                }
                sb.append(genericValueIdColumnName).append(" = ").append(j);
                z = true;
            }
            this.mDatabase.delete(genericTableName, sb.toString(), null);
        }
    }
}
