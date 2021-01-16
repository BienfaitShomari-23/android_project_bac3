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
import com.jungo.ngenyproject.appdata.Users;

import java.util.Objects;

public class LoginFragment extends Fragment {

    EditText email;
    EditText password;
    Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString("args");
            MessageToast(mParam1);
        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        AppCompatActivity app = (AppCompatActivity) getActivity();
        assert app != null;
        Objects.requireNonNull(app.getSupportActionBar()).setElevation(0);
        activity = getActivity();

        Button btnCreateCount = (Button) view.findViewById(R.id.btn_login);
        TextView LinkToSignin = (TextView) view.findViewById(R.id.create_count_text);

        email = (EditText) view.findViewById(R.id.email_login);
        password = (EditText) view.findViewById(R.id.password_login);

        btnCreateCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
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

    private void login(){
        Activity activity = getActivity();
        if(activity != null){
            Toast.makeText(activity, "Say&ing Hi in Progress...", Toast.LENGTH_LONG).show();
        }
        Bundle bundle = new Bundle();
        String email_text = email.getText().toString();
        String pwd = password.getText().toString();

//        if (email_text!="" && pwd !=""){
//            Users userLoged = users.getUser(email_text);
//            if(userLoged.getPassword().equals(pwd) ){
//                bundle.putString("email", email.getText().toString());
//                redirect(bundle);
//            }else {
//                this.MessageToast("cet utilisateur n'existe pas ou mot de passe incorrect");
//            }
//
//        }else{
//            this.MessageToast("Veiller insere les le mot de passe ou votre email!");
//        }

//

    }

    private void redirect(Bundle data){
//        assert getFragmentManager() != null;
//        FragmentTransaction fr = getFragmentManager().beginTransaction();
//        HomeFragment hfr = new HomeFragment();
//        hfr.setArguments(data);
//        fr.replace(R.id.main_container, hfr);
//        fr.commit();
    }


    public void goToSignin() {
//        assert getFragmentManager() != null;
//        FragmentTransaction fr = getFragmentManager().beginTransaction();
//        SigninFragment signin = new SigninFragment();
//        fr.replace(R.id.main_container, signin);
//        fr.commit();
    }

    public void MessageToast(String message){
        Activity activity = getActivity();
        if(activity != null){
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
        }
    }
}