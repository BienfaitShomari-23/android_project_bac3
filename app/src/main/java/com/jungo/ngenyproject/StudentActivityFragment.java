package com.jungo.ngenyproject;

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

import com.jungo.ngenyproject.appdata.DatabaseHelper;
import com.jungo.ngenyproject.appdata.Schools;
import com.jungo.ngenyproject.appdata.Students;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentActivityFragment extends Fragment {
    DatabaseHelper databaseHelper;
    ArrayList<Students> studentsArrayList;
    StudentActivityFragment.MyAdapter custumAdapter;
    ListView listOfSchool;
    LayoutInflater inflater ;

    private static final String ARG_id = "id";
    private static final String ARG_PARAM2 = "position";
    private long id;
    private int mParam2;
    Schools schools;

    public StudentActivityFragment() {
        // Required empty public constructor
    }

    public static StudentActivityFragment newInstance(String param1, String param2) {
        StudentActivityFragment fragment = new StudentActivityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_id, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        AppCompatActivity app = (AppCompatActivity) getActivity();
        assert app != null;
        Objects.requireNonNull(app.getSupportActionBar()).setTitle("Student");
        Objects.requireNonNull(app.getSupportActionBar()).setElevation(0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getLong(ARG_id);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }


        AppCompatActivity app = (AppCompatActivity) getActivity();
        assert app != null;
        databaseHelper = new DatabaseHelper(getActivity());
        try {
            schools = databaseHelper.getWhereSchools().where().eq("idShools",id).queryForFirst();
            Objects.requireNonNull(app.getSupportActionBar()).setTitle(schools.getName().toUpperCase());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_student_activity, container, false);
        this.inflater = inflater;
        listOfSchool = (ListView) v.findViewById(R.id.list_view_student);

        studentsArrayList = new ArrayList<>();
        custumAdapter = new StudentActivityFragment.MyAdapter(this.getContext(),studentsArrayList);
        listOfSchool.setAdapter(custumAdapter);
//        dialogue
        Button btn = (Button) v.findViewById(R.id.adding_school);
        TextView  result = (TextView) v.findViewById(R.id.result_dialgue);
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
                                            databaseHelper.addStudents(
                                                    new Students(code,nameSchool, true)
                                            );

                                            // get data
                                            try {
                                                Students sc = databaseHelper.getWhereStudents().where().eq("code", code)
                                                        .and().eq("name", nameSchool).queryForFirst();
                                                Bundle b = new Bundle();
                                                b.putLong("id",sc.getIdStudents());
                                                b.putInt("position",sc.getIdStudents());
                                                Navigation.findNavController(v).navigate(R.id.action_studentActivityFragment_to_studentTabActivityFragment,b);
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

        listOfSchool.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle dataItem = new Bundle();
                dataItem.putLong("id",id);
                dataItem.putInt("position", position);
                Navigation.findNavController(v).navigate(R.id.action_studentActivityFragment_to_studentTabActivityFragment, dataItem);
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFromDB();
    }
    public void getDataFromDB() {
        if (studentsArrayList != null) studentsArrayList.clear();
        try {
            ArrayList<Students> _schoolsList = (ArrayList<Students>) databaseHelper.getWhereStudents().where()
                    .eq("active", true).query();
            custumAdapter.addAll(_schoolsList);
        }catch (Exception e){
            Log.e("DATABASE", "error", e);
        }

//        custumAdapter.addAll(l);
        if (studentsArrayList.size() == 0) {
            listOfSchool.setVisibility(View.GONE);
        } else {
            custumAdapter.notifyDataSetChanged();
        }
    }
    private class MyAdapter extends ArrayAdapter<Students> {
        Context context ;
        ArrayList<Students> students ;
        MyAdapter(Context c, ArrayList<Students> students){
            super(c, R.layout.fragment_items_view, R.id.title_text, students);
            this.context = c;
            this.students = students;
        }

        @Override
        public int getPosition(@Nullable Students item) {
            return item.getIdStudents();
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getIdStudents();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            LayoutInflater layoutInflater = (LayoutInflater)
//                    getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.fragment_home_list_view, parent, false);
            TextView _title = v.findViewById(R.id.my_title);
            TextView _subTitle = v.findViewById(R.id.my_sub_title);
            _title.setText(students.get(position).getName());
            _subTitle.setText(students.get(position).getCode());
            return v;

        }
    }
}