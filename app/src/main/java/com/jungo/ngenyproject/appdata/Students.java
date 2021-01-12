package com.jungo.ngenyproject.appdata;

import androidx.annotation.NonNull;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "T_Students")
public class Students {
    @DatabaseField(generatedId = true)
    private int idStudents;
    @DatabaseField
    private String code;
    @DatabaseField
    private String name;

    Students(String code, String name){
        this.code = code;
        this.name= name;
    }
    @NonNull
    @Override
    public String toString() {
        return name +" "+ code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
