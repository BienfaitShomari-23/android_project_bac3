package com.jungo.ngenyproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        Button btnGo  = (Button) view.findViewById(R.id.btn_go_to_second_fragment);
//        btnGo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                assert getFragmentManager() != null;
//                FragmentTransaction fr = getFragmentManager().beginTransaction();
//                fr.replace(R.id.frame_container, new SecondFragment());
//                fr.commit();
//            }
//        });
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment);
//            }
//        }, 3000);
        redirect("login");
        return view;
    }

    public void redirect(String to){
        FragmentTransaction fr = getFragmentManager().beginTransaction();
        int main_container = R.id.main_container;
        switch (to){
            case "login":
                fr.replace(main_container, new LoginFragment());
                fr.commit();
                break;
            case "welcome":
                fr.replace(main_container,
                        new WelcomeFragment());
                fr.commit();
                break;
            default:
                break;
        }

    }
}