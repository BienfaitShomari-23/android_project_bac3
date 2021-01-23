package com.jungo.ngenyproject.appdata;

import androidx.annotation.NonNull;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="T_Schools")
public class Schools {
    @DatabaseField(generatedId = true)
    private int idShools;
    @DatabaseField(dataType = DataType.STRING)
    private String code;
    @DatabaseField(dataType = DataType.STRING)
    private String name;
    @DatabaseField
    private boolean active;
    public Schools(){

    }
    public Schools(String code, String name, boolean active){
        this.code = code;
        this.name= name;
        this.active = active;
    }
    public boolean isActive() {
        return active;
    }

    public int getIdShools() {
        return idShools;
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
