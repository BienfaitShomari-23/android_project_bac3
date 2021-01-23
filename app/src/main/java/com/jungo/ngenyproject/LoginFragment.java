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

public class LoginFragment extends Fragment {

    EditText email;
    EditText password;
    Activity activity;
    DatabaseHelper databaseHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString("args");
        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        AppCompatActivity app = (AppCompatActivity) getActivity();
        assert app != null;
        Objects.requireNonNull(app.getSupportActionBar()).hide();

        databaseHelper = new DatabaseHelper(getContext());

        Button btnCreateCount = (Button) view.findViewById(R.id.btn_login);
        TextView LinkToSignin = (TextView) view.findViewById(R.id.create_count_text);

        email = (EditText) view.findViewById(R.id.email_login);
        password = (EditText) view.findViewById(R.id.password_login);

        btnCreateCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(view);
            }
        });

        LinkToSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signinFragment);
            }
        });


        return view;
    }

    private void login(View v){
        Bundle bundle = new Bundle();//
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();

        if (
                (passwordText != null && emailText !=null) &&
                        (!passwordText.equals("") && !emailText.equals(""))
        ){

            try {
//                get the fist user
                Users users = databaseHelper.getWhereUser().where()
                        .eq("email",emailText).and()
                        .eq("active",true).queryForFirst();

                if (users !=null){
                    if (users.getPassword().equals(passwordText)){
                        bundle.putString("email", users.getEmail());
                        bundle.putString("password", users.getPassword());
                        bundle.putString("name", users.getName());
                        SessionApp sessionApp = databaseHelper.getWhereSessionApp().where()
                                .queryForFirst();
                        if (sessionApp != null){
                            sessionApp.setEmail(emailText);
                            sessionApp.setIdUser(""+ users.getIdUser());
                            databaseHelper.updateSessionApp(sessionApp);

                        }else{
                            databaseHelper.addSessionApp(
                                    new SessionApp(""+ users.getIdUser(),users.getEmail())
                            );
                        }
                        this.message("Bienvenu "+ users.getName().toUpperCase());
                        Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_homeFragment, bundle);
                    }else{
                        this.message("Mot de passe incorect...");
                    }
                }else{
                    this.message("Ce compte n'existe pas");
                }

            }catch (Exception e){
                this.message("Ce compte n'existe pas");
            }
        }else{
            this.message("Veillez remplire les informations");
        }

    }


    public void message(String message){
        Activity activity = getActivity();
        if(activity != null){
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
        }
    }
}