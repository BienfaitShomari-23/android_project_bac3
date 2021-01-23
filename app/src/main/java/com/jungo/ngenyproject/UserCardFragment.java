package com.jungo.ngenyproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jungo.ngenyproject.appdata.DatabaseHelper;
import com.jungo.ngenyproject.appdata.SessionApp;
import com.jungo.ngenyproject.appdata.Users;

public class UserCardFragment extends Fragment {
    DatabaseHelper databaseHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_card, container, false);
        TextView usernameLabel = (TextView) view.findViewById(R.id.user_name_card);
        databaseHelper = new DatabaseHelper(getContext());
       try {
           SessionApp session = databaseHelper.getWhereSessionApp().queryForFirst();
           Users users = databaseHelper.getWhereUser().where()
                   .eq("email",session.getEmail()).queryForFirst();
           usernameLabel.setText(users.getName().toLowerCase());
       }catch (Exception e){
           e.printStackTrace();
       }
        return view;
    }
}