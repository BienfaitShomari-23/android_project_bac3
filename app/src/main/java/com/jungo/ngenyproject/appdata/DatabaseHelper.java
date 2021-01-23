package com.jungo.ngenyproject.appdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.ArrayList;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATA_BASE_NAME = "NgenyDataBases";
    private static final int DATA_BASE_VERSION = 3;
    private Dao<Schools, Integer> schoolsDAO = null;
    private Dao<Users, Integer> usersDAO = null;
    private Dao<SessionApp, Integer> sessionAppsDAO = null;
    private Dao<Chats, Integer> chatsDAO = null;
    private Dao<Evolution, Integer> evolutionsDAO = null;
    private Dao<Valves, Integer> valvesDAO = null;
    private Dao<Students, Integer> studentDAO = null;

    public DatabaseHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Users.class);
            TableUtils.createTable(connectionSource, Schools.class);
            TableUtils.createTable(connectionSource, SessionApp.class);
            TableUtils.createTable(connectionSource, Chats.class);
            TableUtils.createTable(connectionSource, Evolution.class);
            TableUtils.createTable(connectionSource, Valves.class);
            TableUtils.createTable(connectionSource, Students.class);
            Log.i("DataBase", "create data base invoked");
        }catch (Exception e){
            Log.e("DataBase", "Can't create data base", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
//            TableUtils.createTable(connectionSource, Students.class);
            TableUtils.dropTable(connectionSource, Users.class,true);
            TableUtils.dropTable(connectionSource, Schools.class,true);
            TableUtils.dropTable(connectionSource, SessionApp.class,true);
            TableUtils.dropTable(connectionSource, Chats.class,true);
            TableUtils.dropTable(connectionSource, Evolution.class,true);
            TableUtils.dropTable(connectionSource, Valves.class,true);
            TableUtils.dropTable(connectionSource, Students.class,true);
            onCreate(database,connectionSource);
            Log.i("DataBase", "upgrate database invoked");
        }catch (Exception e){
            Log.e("DataBase", "Can't upgrate data base", e);
        }
    }
//    Schools
    public Dao<Schools, Integer> getSchoolsDAO() {
        if (schoolsDAO == null) {
            try {
                schoolsDAO = getDao(Schools.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return schoolsDAO;
    }
//Students
    public Dao<Students, Integer> getStudentsDAO() {
        if (studentDAO == null) {
            try {
                studentDAO = getDao(Students.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return studentDAO;
    }
//Users
    public Dao<Users, Integer> getUsersDAO() {
        if (usersDAO == null) {
            try {
                usersDAO = getDao(Users.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return usersDAO;
    }
// SessionApp Dao
    public Dao<SessionApp, Integer> getSessionAppDAO() {
        if (sessionAppsDAO == null) {
            try {
                sessionAppsDAO = getDao(SessionApp.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return sessionAppsDAO;
    }
// Chats
    public Dao<Chats, Integer> getChatsDAO() {
        if (chatsDAO == null) {
            try {
                chatsDAO = getDao(Chats.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return chatsDAO;
    }
// Evolution
    public Dao<Evolution, Integer> getEvolutionDAO() {
        if (evolutionsDAO == null) {
            try {
                evolutionsDAO = getDao(Evolution.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return evolutionsDAO;
    }
// Valves
    public Dao<Valves, Integer> getValvesDAO() {
        if (valvesDAO == null) {
            try {
                valvesDAO = getDao(Valves.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return valvesDAO;
    }


    //    queries....
//    Schools
    public ArrayList<Schools> getAllSchools() {
        ArrayList<Schools> schools = null;
        try {
            schools = (ArrayList<Schools>) getSchoolsDAO().queryForAll();
            Log.d("DATABASE", "getShool");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schools;
    }
    public QueryBuilder<Schools, Integer> getWhereSchools() {
        QueryBuilder<Schools, Integer> queryBuilder = null;
        try {
            queryBuilder = getSchoolsDAO().queryBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryBuilder;
    }
    public void addSchool(Schools schools) {
        try {
            getSchoolsDAO().create(schools);
            Log.d("DATABASE","adding Schools");
        } catch (Exception e) {
            Log.e("DATABASE", "error to add school", e);
            e.printStackTrace();
        }
    }
//    Users
    public ArrayList<Users> getAllUsers() {
        ArrayList<Users> schools = null;
        try {
            schools = (ArrayList<Users>) getUsersDAO().queryForAll();
            Log.d("DATABASE", "getShool");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schools;
    }
    public QueryBuilder<Users, Integer> getWhereUser() {
        QueryBuilder<Users, Integer> queryBuilder = null;
        try {
            queryBuilder = getUsersDAO().queryBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryBuilder;
    }
    public void addUser(Users users) {
        try {
            getUsersDAO().createIfNotExists(users);
            Log.d("DATABASE","adding user");
        } catch (Exception e) {
            Log.e("DATABASE", "error to add school", e);
            e.printStackTrace();
        }
    }

    public void updateUser(Users users) {
        try {
            getUsersDAO().update(users);
            Log.d("DATABASE","adding user");
        } catch (Exception e) {
            Log.e("DATABASE", "error to add school", e);
            e.printStackTrace();
        }
    }

//    Students
    public ArrayList<Students> getAllStudents() {
    ArrayList<Students> schools = null;
        try {
            schools = (ArrayList<Students>) getStudentsDAO().queryForAll();
            Log.d("DATABASE", "getShool");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schools;
    }
    public QueryBuilder<Students, Integer> getWhereStudents() {
        QueryBuilder<Students, Integer> queryBuilder = null;
        try {
            queryBuilder = getStudentsDAO().queryBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryBuilder;
    }
    public void addStudents(Students users) {
        try {
            getStudentsDAO().createIfNotExists(users);
            Log.d("DATABASE","adding user");
        } catch (Exception e) {
            Log.e("DATABASE", "error to add school", e);
            e.printStackTrace();
        }
    }
//
//    SessionApp
    public ArrayList<SessionApp> getAllSessionApp() {
        ArrayList<SessionApp> schools = null;
        try {
            schools = (ArrayList<SessionApp>) getSessionAppDAO().queryForAll();
            Log.d("DATABASE", "getShool");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schools;
    }
    public QueryBuilder<SessionApp, Integer> getWhereSessionApp() {
        QueryBuilder<SessionApp, Integer> queryBuilder = null;
        try {
            queryBuilder = getSessionAppDAO().queryBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryBuilder;
    }
    public void addSessionApp(SessionApp users) {
        try {
            getSessionAppDAO().createIfNotExists(users);
            Log.d("DATABASE","adding user");
        } catch (Exception e) {
            Log.e("DATABASE", "error to add school", e);
            e.printStackTrace();
        }
    }
    public void updateSessionApp(SessionApp users) {
        try {
            getSessionAppDAO().update(users);
            Log.d("DATABASE","adding user");
        } catch (Exception e) {
            Log.e("DATABASE", "error to add school", e);
            e.printStackTrace();
        }
    }
    public void deleteSessionApp(SessionApp users) {
        try {
            getSessionAppDAO().delete(users);
            Log.d("DATABASE","adding user");
        } catch (Exception e) {
            Log.e("DATABASE", "error to add school", e);
            e.printStackTrace();
        }
    }
//
//    Chats
    public ArrayList<Chats> getAllChats() {
        ArrayList<Chats> schools = null;
        try {
            schools = (ArrayList<Chats>) getChatsDAO().queryForAll();
            Log.d("DATABASE", "getShool");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schools;
    }
    public QueryBuilder<Chats, Integer> getWhereChats() {
        QueryBuilder<Chats, Integer> queryBuilder = null;
        try {
            queryBuilder = getChatsDAO().queryBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryBuilder;
    }
    public void addChats(Chats users) {
        try {
            getChatsDAO().createIfNotExists(users);
            Log.d("DATABASE","adding user");
        } catch (Exception e) {
            Log.e("DATABASE", "error to add school", e);
            e.printStackTrace();
        }
    }
//
//    Evolution
    public ArrayList<Evolution> getAllEvolution() {
        ArrayList<Evolution> schools = null;
        try {
            schools = (ArrayList<Evolution>) getEvolutionDAO().queryForAll();
            Log.d("DATABASE", "getShool");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schools;
    }
    public QueryBuilder<Evolution, Integer> getWhereEvolution() {
        QueryBuilder<Evolution, Integer> queryBuilder = null;
        try {
            queryBuilder = getEvolutionDAO().queryBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryBuilder;
    }
    public void addEvolution(Evolution users) {
        try {
            getEvolutionDAO().createIfNotExists(users);
            Log.d("DATABASE","adding user");
        } catch (Exception e) {
            Log.e("DATABASE", "error to add school", e);
            e.printStackTrace();
        }
    }
//
//    Valves
    public ArrayList<Valves> getAllValves() {
        ArrayList<Valves> schools = null;
        try {
            schools = (ArrayList<Valves>) getValvesDAO().queryForAll();
            Log.d("DATABASE", "getShool");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schools;
    }
    public QueryBuilder<Valves, Integer> getWhereValves() {
        QueryBuilder<Valves, Integer> queryBuilder = null;
        try {
            queryBuilder = getValvesDAO().queryBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryBuilder;
    }
    public void addValves(Valves users) {
        try {
            getValvesDAO().createIfNotExists(users);
            Log.d("DATABASE","adding user");
        } catch (Exception e) {
            Log.e("DATABASE", "error to add school", e);
            e.printStackTrace();
        }
    }
//
}
