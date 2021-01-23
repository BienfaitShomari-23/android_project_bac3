package com.jungo.ngenyproject.appdata;

import androidx.annotation.NonNull;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName="T_Users")
public class Users {


    @DatabaseField(columnName = "idUser", generatedId = true)
    private int idUser;
    @DatabaseField
    private String name;
    @DatabaseField
    private String password;
    @DatabaseField(unique = true)
    private String email;
    @DatabaseField
    private boolean active;
    @DatabaseField (dataType = DataType.DATE_STRING)
    private Date createAt;
    public Users(){

    }
    public Users(String name, String email, String pwd,boolean active ,Date createAt){
        this.name = name;
        this.email = email;
        this.password = pwd;
        this.createAt = createAt;
        this.active = active;

    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    @NonNull
    @Override
    public String toString() {
        return name +" "+ email;
    }

    public Date getCreateAt() {
        return createAt;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
