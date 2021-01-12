package com.jungo.ngenyproject.appdata;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.List;

public class DataBaseManager extends OrmLiteSqliteOpenHelper {
    private static final String DATA_BASE_NAME = "NgenyDataBase.db";
    private static final int DATA_BASE_VERSION = 1;
    public DataBaseManager(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.clearTable(connectionSource, Users.class);
            TableUtils.clearTable(connectionSource, Schools.class);
            TableUtils.clearTable(connectionSource, Students.class);
            Log.i("DataBase", "create data base invoked");
        }catch (Exception e){
            Log.e("DataBase", "Can't create data base", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Users.class, true);
            TableUtils.dropTable(connectionSource, Schools.class,true);
            TableUtils.dropTable(connectionSource, Students.class,true);
            onCreate(database,connectionSource);
            Log.i("DataBase", "upgrate database invoked");
        }catch (Exception e){
            Log.e("DataBase", "Can't upgrate data base", e);
        }
    }

    public void inserUser(Users users){
        try{
            Dao<Users, Integer> usersQuery = getDao(Users.class);
            usersQuery.create(users);
        }catch (Exception e){
            Log.e("DataBase", "Can't inser user", e);
        }
    }
    public void inserSchool(Schools schools){
        try{
            Dao<Schools, Integer> usersQuery = getDao(Schools.class);
            usersQuery.create(schools);
        }catch (Exception e){
            Log.e("DataBase", "Can't inser Schools", e);
        }
    }
    public void inserStudent(Students students){
        try{
            Dao<Students, Integer> usersQuery = getDao(Students.class);
            usersQuery.create(students);
        }catch (Exception e){
            Log.e("DataBase", "Can't inser Students", e);
        }
    }

    public List<Users> getAllUser(){
        try{
            Dao<Users, Integer> usersQuery = getDao(Users.class);
            return usersQuery.queryForAll();
        }catch (Exception e){
            Log.e("DataBase", "Can't inser user", e);
            return null;
        }
    }
    public List<Schools> getAllSchool(Schools schools){
        try{
            Dao<Schools, Integer> usersQuery = getDao(Schools.class);
            return usersQuery.queryForAll();
        }catch (Exception e){
            Log.e("DataBase", "Can't inser Schools", e);
            return null;
        }
    }
    public List<Students> getAllStudent(Students students){
        try{
            Dao<Students, Integer> usersQuery = getDao(Students.class);
            return usersQuery.queryForAll();

        }catch (Exception e){
            Log.e("DataBase", "Can't inser Students", e);
            return null;
        }
    }
}
