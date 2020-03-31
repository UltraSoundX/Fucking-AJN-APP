package org.litepal.crud;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import org.litepal.crud.model.AssociationsInfo;
import org.litepal.exceptions.DataSupportException;
import org.litepal.util.DBUtility;

abstract class AssociationsAnalyzer extends DataHandler {
    AssociationsAnalyzer() {
    }

    /* access modifiers changed from: protected */
    public Collection<DataSupport> getReverseAssociatedModels(DataSupport dataSupport, AssociationsInfo associationsInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (Collection) getFieldValue(dataSupport, associationsInfo.getAssociateSelfFromOtherModel());
    }

    /* access modifiers changed from: protected */
    public void setReverseAssociatedModels(DataSupport dataSupport, AssociationsInfo associationsInfo, Collection<DataSupport> collection) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        setFieldValue(dataSupport, associationsInfo.getAssociateSelfFromOtherModel(), collection);
    }

    /* access modifiers changed from: protected */
    public Collection<DataSupport> checkAssociatedModelCollection(Collection<DataSupport> collection, Field field) {
        Collection<DataSupport> hashSet;
        if (isList(field.getType())) {
            hashSet = new ArrayList<>();
        } else if (isSet(field.getType())) {
            hashSet = new HashSet<>();
        } else {
            throw new DataSupportException(DataSupportException.WRONG_FIELD_TYPE_FOR_ASSOCIATIONS);
        }
        if (collection != null) {
            hashSet.addAll(collection);
        }
        return hashSet;
    }

    /* access modifiers changed from: protected */
    public void buildBidirectionalAssociations(DataSupport dataSupport, DataSupport dataSupport2, AssociationsInfo associationsInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        setFieldValue(dataSupport2, associationsInfo.getAssociateSelfFromOtherModel(), dataSupport);
    }

    /* access modifiers changed from: protected */
    public void dealsAssociationsOnTheSideWithoutFK(DataSupport dataSupport, DataSupport dataSupport2) {
        if (dataSupport2 == null) {
            return;
        }
        if (dataSupport2.isSaved()) {
            dataSupport.addAssociatedModelWithFK(dataSupport2.getTableName(), dataSupport2.getBaseObjId());
        } else if (dataSupport.isSaved()) {
            dataSupport2.addAssociatedModelWithoutFK(dataSupport.getTableName(), dataSupport.getBaseObjId());
        }
    }

    /* access modifiers changed from: protected */
    public void mightClearFKValue(DataSupport dataSupport, AssociationsInfo associationsInfo) {
        dataSupport.addFKNameToClearSelf(getForeignKeyName(associationsInfo));
    }

    private String getForeignKeyName(AssociationsInfo associationsInfo) {
        return getForeignKeyColumnName(DBUtility.getTableNameByClassName(associationsInfo.getAssociatedClassName()));
    }
}
