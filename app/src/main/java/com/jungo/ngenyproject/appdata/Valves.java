package com.jungo.ngenyproject.appdata;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "T_Valve")
public class Valves {
    @DatabaseField(generatedId = true)
    private int idValve;
    @DatabaseField(dataType = DataType.STRING)
    private String school;
    @DatabaseField(dataType = DataType.STRING)
    private String title;
    @DatabaseField(dataType = DataType.STRING)
    private String body;

    public Valves() {
    }

    public Valves(String school, String title, String body) {
        this.school = school;
        this.title = title;
        this.body = body;
    }

    public int getIdValve() {
        return idValve;
    }

    public void setIdValve(int idValve) {
        this.idValve = idValve;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
