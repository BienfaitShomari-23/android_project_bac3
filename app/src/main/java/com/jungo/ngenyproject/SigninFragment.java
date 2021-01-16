package com.jungo.ngenyproject;

import android.app.Activity;
import android.os.Bundle;

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


public class SigninFragment extends Fragment {

    EditText email;
    EditText name;
    EditText password;
    Activity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);
        activity = getActivity();

//        activity.getActionBar().hide();
        activity.setTitle(R.string.signin);
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
        Activity activity = getActivity();
        if(activity != null){
            Toast.makeText(activity, "Say&ing Hi in Progress...", Toast.LENGTH_LONG).show();
        }
        Bundle bundle = new Bundle();
//
        bundle.putString("email", email.getText().toString());
        bundle.putString("password", password.getText().toString());
        bundle.putString("password", password.getText().toString());
        redirect(bundle);
    }
//
    private void redirect(Bundle data){
//        assert getFragmentManager() != null;
//        FragmentTransaction fr = getFragmentManager().beginTransaction();
//        HomeFragment hfr = new HomeFragment();
//        hfr.setArguments(data);
//        fr.replace(R.id.main_container, hfr);
//        fr.commit();
    }

    private  void linkToSignin (View view){
//        FragmentTransaction fr = getFragmentManager().beginTransaction();
//        LoginFragment hfr = new LoginFragment();
//        fr.replace(R.id.main_container, hfr);
//        fr.commit();

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