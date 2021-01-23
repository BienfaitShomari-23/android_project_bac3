package com.jungo.ngenyproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jungo.ngenyproject.appdata.DatabaseHelper;
import com.jungo.ngenyproject.appdata.Schools;
import com.jungo.ngenyproject.appdata.SessionApp;

import java.util.ArrayList;
import java.util.Objects;


public class HomeFragment extends Fragment {
    private final Activity activity = getActivity();

    LayoutInflater inflater;
    ArrayList<Schools> schoolsList;
    ListView listOfSchool;
    MyAdapter custumAdapter;
    DatabaseHelper databaseHelper;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        AppCompatActivity app = (AppCompatActivity) getActivity();
        assert app != null;
        Objects.requireNonNull(app.getSupportActionBar()).show();
        Objects.requireNonNull(app.getSupportActionBar()).setElevation(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.inflater = inflater;
        AppCompatActivity app = (AppCompatActivity) getActivity();
        assert app != null;
        Objects.requireNonNull(app.getSupportActionBar()).setTitle("Home");
        session(view);

        listOfSchool = (ListView) view.findViewById(R.id.list_view_school);

//        init database manager
        databaseHelper = new DatabaseHelper(getContext());

        schoolsList = new ArrayList<>();
        custumAdapter = new MyAdapter(this.getContext(), schoolsList);
        listOfSchool.setAdapter(custumAdapter);


        Button btn = (Button) view.findViewById(R.id.adding_school);
        TextView  result = (TextView) view.findViewById(R.id.result_dialgue);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                View promptsView = inflater.inflate(R.layout.dialogue, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text_dialogue);
                final EditText codeSchool = (EditText) promptsView.findViewById(R.id.input_text_dialogue_code);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        String nameSchool = userInput.getText().toString();
                                        String code  = codeSchool.getText().toString();
                                        if (!nameSchool.equals("") && !code.equals("")){
                                            databaseHelper.addSchool(
                                                    new Schools(code,nameSchool, true)
                                            );

                                            // get data
                                            try {
                                                Schools sc = databaseHelper.getWhereSchools().where().eq("code", code)
                                                        .and().eq("name", nameSchool).queryForFirst();
                                                Bundle b = new Bundle();
                                                b.putLong("id",sc.getIdShools());
                                                b.putInt("position",sc.getIdShools());
                                                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_studentActivityFragment,b);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }

                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });

//        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment);

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
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFromDB();
    }
    public void getDataFromDB() {
        if (schoolsList != null) schoolsList.clear();
        ArrayList<Schools> _schoolsList = databaseHelper.getAllSchools();
        try {
            custumAdapter.addAll(_schoolsList);
        }catch (Exception e){
           Log.e("DATABASE", "error", e);
        }

//        custumAdapter.addAll(l);
        if (schoolsList.size() == 0) {
            //no data in database
            listOfSchool.setVisibility(View.GONE);
//            notif.setText("Database is Empty");
//            notice.setVisibility(View.VISIBLE);
        } else {
            custumAdapter.notifyDataSetChanged();
        }
    }

    private void checkDataOfUser(){
        String email = getArguments().getString("email");
        String password = getArguments().getString("password");
        String name = getArguments().getString("name");

        if (email != null && password !=null && name !=null ){
            if(activity != null){
                Toast.makeText(activity, "bonjour :"+name+" email: "+ email, Toast.LENGTH_LONG).show();
            }
        }
    }

    private class MyAdapter extends ArrayAdapter<Schools>{
        Context context ;
        ArrayList<Schools> schools;
        MyAdapter(Context c, ArrayList<Schools> listSchools){
            super(c, R.layout.fragment_items_view, R.id.title_text,listSchools);
            this.context = c;
            this.schools = listSchools;
        }

        @Override
        public int getPosition(@Nullable Schools item) {
            return item.getIdShools();
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getIdShools();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            LayoutInflater layoutInflater = (LayoutInflater)
//                    getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.fragment_home_list_view, parent, false);
            TextView _title = v.findViewById(R.id.my_title);
            TextView _subTitle = v.findViewById(R.id.my_sub_title);
            _title.setText(schools.get(position).getName());
            _subTitle.setText(schools.get(position).getCode());
            return v;
        }
    }
    private void message(String message){
        Activity activity = getActivity();
        if(activity != null){
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
        }
    }

    public void session(View view) {
        try {
            SessionApp sessionApp = databaseHelper.getWhereSessionApp().where()
                    .queryForFirst();
            if (sessionApp == null){
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragments);
            }
        }catch (Exception e){
//            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragments);
            e.printStackTrace();
        }

    }
}