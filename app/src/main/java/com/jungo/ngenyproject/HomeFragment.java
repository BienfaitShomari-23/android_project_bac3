package com.jungo.ngenyproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class HomeFragment extends Fragment {
    private final Activity activity = getActivity();
    private Boolean sessionUser  = false;

    LayoutInflater inflater;

    String title[] = {"aijj", "blo", "cju", "aijj", "blo", "cju", "aijj", "blo", "cju"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.inflater = inflater;
        AppCompatActivity app = (AppCompatActivity) getActivity();
        assert app != null;
        Objects.requireNonNull(app.getSupportActionBar()).setTitle("Home");

        ListView listOfSchool = (ListView) view.findViewById(R.id.list_view_school);

        MyAdapter custumAdapter = new MyAdapter(this.getContext(),title, title);
        listOfSchool.setAdapter(custumAdapter);
        listOfSchool.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ITEM CLICK", position +" position");
                Bundle dataItem = new Bundle();
                dataItem.putLong("id",id);
                dataItem.putInt("position", position);
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_studentActivityFragment, dataItem);
            }
        });

//

        return view;
    }


    public void redirect(String to){
//        assert getFragmentManager() != null;
//        FragmentTransaction fr = getFragmentManager().beginTransaction();
//        int main_container = R.id.main_container;
//        switch (to){
//            case "login":
//                fr.replace(main_container, new LoginFragment());
//                fr.commit();
//                break;
//            case "welcome":
//                fr.replace(main_container,
//                        new WelcomeFragment());
//                fr.commit();
//                break;
//            default:
//                break;
//        }

    }

    private void checkDataOfUser(){
        String email = getArguments().getString("email");
        String password = getArguments().getString("password");
        String name = getArguments().getString("name");

        if (email != null && password !=null && name !=null ){
            if(activity != null){
                sessionUser = true;
                Toast.makeText(activity, "bonjour :"+name+" email: "+ email, Toast.LENGTH_LONG).show();
            }
        }
    }

    private class MyAdapter extends ArrayAdapter<String>{
        Context context ;
        String title[];
        String subTitle[];
        MyAdapter(Context c, String title[], String subTitle[]){
            super(c, R.layout.fragment_items_view, R.id.title_text, title);
            this.context = c;
            this.title = title;
            this.subTitle = subTitle;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            LayoutInflater layoutInflater = (LayoutInflater)
//                    getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.fragment_home_list_view, parent, false);
            TextView _title = v.findViewById(R.id.my_title);
            TextView _subTitle = v.findViewById(R.id.my_sub_title);
            _title.setText(title[position]);
            _subTitle.setText(subTitle[position]);
            return v;

        }
    }
}