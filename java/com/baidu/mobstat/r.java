package com.baidu.mobstat;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;

class r extends j {
    public r() {
        super("app_trace3", "Create table if not exists app_trace3(_id Integer primary key AUTOINCREMENT,time VARCHAR(50),content TEXT);");
    }

    public ArrayList<i> a(int i, int i2) {
        Cursor a = a("time", i, i2);
        ArrayList<i> a2 = a(a);
        if (a != null) {
            a.close();
        }
        return a2;
    }

    public long a(String str, String str2) {
        Cursor a = a("content", str2, "time", 1);
        ArrayList a2 = a(a);
        if (a != null) {
            a.close();
        }
        if (a2.size() != 0) {
            return ((i) a2.get(0)).a();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("time", str);
        contentValues.put("content", str2);
        return a(contentValues);
    }

    public boolean b(long j) {
        return a(j);
    }

    private ArrayList<i> a(Cursor cursor) {
        ArrayList<i> arrayList = new ArrayList<>();
        if (!(cursor == null || cursor.getCount() == 0)) {
            int columnIndex = cursor.getColumnIndex("_id");
            int columnIndex2 = cursor.getColumnIndex("time");
            int columnIndex3 = cursor.getColumnIndex("content");
            while (cursor.moveToNext()) {
                arrayList.add(new i(cursor.getLong(columnIndex), cursor.getString(columnIndex2), cursor.getString(columnIndex3)));
            }
        }
        return arrayList;
    }
}
