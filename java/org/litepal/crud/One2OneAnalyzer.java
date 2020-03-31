package org.litepal.crud;

import java.lang.reflect.InvocationTargetException;
import org.litepal.crud.model.AssociationsInfo;
import org.litepal.util.DBUtility;

public class One2OneAnalyzer extends AssociationsAnalyzer {
    /* access modifiers changed from: 0000 */
    public void analyze(DataSupport dataSupport, AssociationsInfo associationsInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        DataSupport associatedModel = getAssociatedModel(dataSupport, associationsInfo);
        if (associatedModel != null) {
            buildBidirectionalAssociations(dataSupport, associatedModel, associationsInfo);
            dealAssociatedModel(dataSupport, associatedModel, associationsInfo);
            return;
        }
        dataSupport.addAssociatedTableNameToClearFK(DBUtility.getTableNameByClassName(associationsInfo.getAssociatedClassName()));
    }

    private void dealAssociatedModel(DataSupport dataSupport, DataSupport dataSupport2, AssociationsInfo associationsInfo) {
        if (associationsInfo.getAssociateSelfFromOtherModel() != null) {
            bidirectionalCondition(dataSupport, dataSupport2);
        } else {
            unidirectionalCondition(dataSupport, dataSupport2);
        }
    }

    private void bidirectionalCondition(DataSupport dataSupport, DataSupport dataSupport2) {
        if (dataSupport2.isSaved()) {
            dataSupport.addAssociatedModelWithFK(dataSupport2.getTableName(), dataSupport2.getBaseObjId());
            dataSupport.addAssociatedModelWithoutFK(dataSupport2.getTableName(), dataSupport2.getBaseObjId());
        }
    }

    private void unidirectionalCondition(DataSupport dataSupport, DataSupport dataSupport2) {
        dealsAssociationsOnTheSideWithoutFK(dataSupport, dataSupport2);
    }
}
