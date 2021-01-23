package com.jungo.ngenyproject.appdata;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "T_Evolution")
public class Evolution {
    @DatabaseField(generatedId = true)
    private int idEvolution;
    @DatabaseField(dataType = DataType.STRING)
    private String school;
    @DatabaseField(dataType = DataType.STRING)
    private String title;
    @DatabaseField(dataType = DataType.STRING)
    private String body;

    public Evolution() {
    }

    public Evolution(String school, String title, String body) {
        this.school = school;
        this.title = title;
        this.body = body;
    }

    public int getIdEvolution() {
        return idEvolution;
    }

    public void setIdEvolution(int idEvolution) {
        this.idEvolution = idEvolution;
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
