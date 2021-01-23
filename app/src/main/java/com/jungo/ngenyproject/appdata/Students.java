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
    @DatabaseField
    private boolean active;

    public Students(){

    }
    public Students(String code, String name, boolean active){
        this.code = code;
        this.name= name;
        this.active = active;
    }
    public boolean isActive() {
        return active;
    }
    public int getIdStudents() {
        return idStudents;
    }

    public void setIdStudents(int idStudents) {
        this.idStudents = idStudents;
    }
    public void setActive(boolean active) {
        this.active = active;
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
