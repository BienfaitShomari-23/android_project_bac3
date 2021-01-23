package com.jungo.ngenyproject.appdata;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "T_Session")
public class SessionApp {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(dataType = DataType.STRING)
    private String email;
    @DatabaseField(dataType = DataType.STRING)
    private String idUser;

    public SessionApp() {
    }

    public SessionApp(String idUSer, String email) {
        this.email = email;
        this.idUser = idUSer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
