package org.litepal.crud;

import android.database.Cursor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import org.litepal.crud.model.AssociationsInfo;
import org.litepal.tablemanager.Connector;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;

public class Many2ManyAnalyzer extends AssociationsAnalyzer {
    /* access modifiers changed from: 0000 */
    public void analyze(DataSupport dataSupport, AssociationsInfo associationsInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Collection<DataSupport> associatedModels = getAssociatedModels(dataSupport, associationsInfo);
        declareAssociations(dataSupport, associationsInfo);
        if (associatedModels != null) {
            for (DataSupport dataSupport2 : associatedModels) {
                Collection checkAssociatedModelCollection = checkAssociatedModelCollection(getReverseAssociatedModels(dataSupport2, associationsInfo), associationsInfo.getAssociateSelfFromOtherModel());
                addNewModelForAssociatedModel(checkAssociatedModelCollection, dataSupport);
                setReverseAssociatedModels(dataSupport2, associationsInfo, checkAssociatedModelCollection);
                dealAssociatedModel(dataSupport, dataSupport2);
            }
        }
    }

    private void declareAssociations(DataSupport dataSupport, AssociationsInfo associationsInfo) {
        dataSupport.addEmptyModelForJoinTable(getAssociatedTableName(associationsInfo));
    }

    private void addNewModelForAssociatedModel(Collection<DataSupport> collection, DataSupport dataSupport) {
        if (!collection.contains(dataSupport)) {
            collection.add(dataSupport);
        }
    }

    private void dealAssociatedModel(DataSupport dataSupport, DataSupport dataSupport2) {
        if (dataSupport2.isSaved()) {
            dataSupport.addAssociatedModelForJoinTable(dataSupport2.getTableName(), dataSupport2.getBaseObjId());
        }
    }

    private String getAssociatedTableName(AssociationsInfo associationsInfo) {
        return BaseUtility.changeCase(DBUtility.getTableNameByClassName(associationsInfo.getAssociatedClassName()));
    }

    @Deprecated
    private boolean isDataExists(DataSupport dataSupport, DataSupport dataSupport2) {
        Cursor cursor;
        Cursor cursor2 = null;
        try {
            cursor = Connector.getDatabase().query(getJoinTableName(dataSupport, dataSupport2), null, getSelection(dataSupport, dataSupport2), getSelectionArgs(dataSupport, dataSupport2), null, null, null);
            try {
                boolean z = cursor.getCount() > 0;
                cursor.close();
                return z;
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th) {
            th = th;
            cursor2.close();
            throw th;
        }
        try {
            e.printStackTrace();
            cursor.close();
            return true;
        } catch (Throwable th2) {
            th = th2;
            cursor2 = cursor;
            cursor2.close();
            throw th;
        }
    }

    private String getSelection(DataSupport dataSupport, DataSupport dataSupport2) {
        StringBuilder sb = new StringBuilder();
        sb.append(getForeignKeyColumnName(dataSupport.getTableName()));
        sb.append(" = ? and ");
        sb.append(getForeignKeyColumnName(dataSupport2.getTableName()));
        sb.append(" = ?");
        return sb.toString();
    }

    private String[] getSelectionArgs(DataSupport dataSupport, DataSupport dataSupport2) {
        return new String[]{String.valueOf(dataSupport.getBaseObjId()), String.valueOf(dataSupport2.getBaseObjId())};
    }

    private String getJoinTableName(DataSupport dataSupport, DataSupport dataSupport2) {
        return getIntermediateTableName(dataSupport, dataSupport2.getTableName());
    }
}
