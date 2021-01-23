package com.jungo.ngenyproject.appdata;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.DeleteBuilder;

import java.util.ArrayList;

public class MainDataBaseManager {
    private static MainDataBaseManager instance;
    private DatabaseHelper helper;

    public static void init(Context ctx) {
        if (null == instance) {
            instance = new MainDataBaseManager(ctx);
        }
    }

    static public MainDataBaseManager getInstance() {
        return instance;
    }

    private MainDataBaseManager(Context ctx) {
        helper = new DatabaseHelper(ctx);
    }

    public DatabaseHelper getHelper() {
        return helper;
    }

    /**
     * Get all customer in db
     *
     * @return
     */
    public ArrayList<Schools> getAllSchools() {
        ArrayList<Schools> schools = null;
        try {
            schools = (ArrayList<Schools>) getHelper().getSchoolsDAO().queryForAll();
            Log.d("DATABASE", "getShool");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schools;
    }

    public void addSchool(Schools schools) {
        try {
            getHelper().getSchoolsDAO().create(schools);
            Log.d("DATABASE","adding Schools");
        } catch (Exception e) {
            Log.e("DATABASE", "error to add school", e);
            e.printStackTrace();
        }
    }

    public void refreshCat(Schools schools) {
        try {
            getHelper().getSchoolsDAO().refresh(schools);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSchool(Schools wishList) {
        try {
            getHelper().getSchoolsDAO().update(wishList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSchool (int catId) {
        try {
            DeleteBuilder<Schools, Integer> deleteBuilder = getHelper().getSchoolsDAO().deleteBuilder();
            deleteBuilder.where().eq("id", catId);
            deleteBuilder.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public Kitten newKitten() {
//        Kitten kitten = new Kitten();
//        try {
//            getHelper().getKittenDAO().create(kitten);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return kitten;
//    }
//
//    public Kitten newKittenAppend(Kitten kitten) {
//        try {
//            getHelper().getKittenDAO().create(kitten);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return kitten;
//    }
//
//    public void updateKitten(Kitten item) {
//        try {
//            getHelper().getKittenDAO().update(item);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public ArrayList<Kitten> getAllKittens() {
//        ArrayList<Kitten> kittenArrayList = null;
//        try {
//            kittenArrayList = (ArrayList<Kitten>) getHelper().getKittenDAO().queryForAll();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return kittenArrayList;
//    }
//
//    public void deleteKitten (int kittenId) {
//        try {
//            DeleteBuilder<Kitten, Integer> deleteBuilder = getHelper().getKittenDAO().deleteBuilder();
//            deleteBuilder.where().eq("id", kittenId);
//            deleteBuilder.delete();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
