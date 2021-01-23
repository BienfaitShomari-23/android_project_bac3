package com.jungo.ngenyproject;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jungo.ngenyproject.appdata.DataBaseManager;
import com.jungo.ngenyproject.appdata.DatabaseHelper;
import com.jungo.ngenyproject.appdata.SessionApp;
import com.jungo.ngenyproject.appdata.Users;

import java.util.Date;
import java.util.Objects;


public class SigninFragment extends Fragment {

    EditText email;
    EditText name;
    EditText password;
    Activity activity;
    DatabaseHelper databaseHelper;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_signin, container, false);
        activity = getActivity();

//        hiding the appbar
        AppCompatActivity app = (AppCompatActivity) getActivity();
        assert app != null;
        Objects.requireNonNull(app.getSupportActionBar()).hide();

        databaseHelper = new DatabaseHelper(getContext());
        initFragment(view);

        TextView link_to_login_text = (TextView) view.findViewById(R.id.go_to_login_text);
//
        link_to_login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_signinFragment_to_loginFragment);
            }
        });
//

        return view;
    }

    private void signin(){
        Bundle bundle = new Bundle();//
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        String nameText = name.getText().toString();

        if (
                (emailText !=null && passwordText != null && emailText !=null) &&
                (!nameText.equals("") && !passwordText.equals("") && !emailText.equals(""))
        ){

            try {
//                inser into database or create a new users
                databaseHelper.addUser(
                        new Users(nameText,emailText,passwordText,true, new Date())
                );

                bundle.putString("email", emailText);
                bundle.putString("password", passwordText);
                bundle.putString("name", nameText);
                SessionApp sessionApp = databaseHelper.getWhereSessionApp().where()
                        .queryForFirst();

                Users users = databaseHelper.getWhereUser().where()
                        .eq("email",emailText).queryForFirst();
                if (sessionApp != null){
                    sessionApp.setEmail(emailText);
                    sessionApp.setIdUser(""+ users.getIdUser());
                    databaseHelper.updateSessionApp(sessionApp);

                }else{
                    databaseHelper.addSessionApp(
                            new SessionApp(""+ users.getIdUser(),users.getEmail())
                    );
                }
                this.message("Bienvenu"+ nameText);
                Navigation.findNavController(this.view).navigate(R.id.action_signinFragment_to_homeFragment, bundle);
            }catch (Exception e){
                this.message("On n'a pu creer un creer un compte, Veiller ressayer!");
            }
        }else{
            this.message("Veillez remplire les informations");
        }

    }

    private void message(String message){
        Activity activity = getActivity();
        if(activity != null){
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
        }
    }

    private void initFragment(View view){
//
        Button btnCreateCount = (Button) view.findViewById(R.id.btn_signin);
        email = (EditText) view.findViewById(R.id.email_signin);
        name = (EditText) view.findViewById(R.id.name_signin);
        password = (EditText) view.findViewById(R.id.password_signin);



        btnCreateCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });
    }
}