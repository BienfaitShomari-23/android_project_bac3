package com.jungo.ngenyproject;

import android.content.Context;
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
import android.widget.ListView;
import android.widget.TextView;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentActivityFragment extends Fragment {

    String title[] = {"aijj", "blo", "cju", "aijj", "blo", "cju", "aijj", "blo", "cju"};
    LayoutInflater inflater ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "id";
    private static final String ARG_PARAM2 = "position";

    // TODO: Rename and change types of parameters
    private long mParam1;
    private int mParam2;

    public StudentActivityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentActivityFragment newInstance(String param1, String param2) {
        StudentActivityFragment fragment = new StudentActivityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getLong(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_student_activity, container, false);
        this.inflater = inflater;
        TextView text = v.findViewById(R.id.detail);
        AppCompatActivity app = (AppCompatActivity) getActivity();
        assert app != null;
        Objects.requireNonNull(app.getSupportActionBar()).setTitle("Student");
        ListView listOfSchool = (ListView) v.findViewById(R.id.list_view_student);

        StudentActivityFragment.MyAdapter custumAdapter = new StudentActivityFragment.MyAdapter(this.getContext(),title, title);
        listOfSchool.setAdapter(custumAdapter);
        listOfSchool.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ITEM CLICK", position +" position");
                Bundle dataItem = new Bundle();
                dataItem.putLong("id",id);
                dataItem.putInt("position", position);
                Navigation.findNavController(v).navigate(R.id.action_studentActivityFragment_to_studentTabActivityFragment, dataItem);
            }
        });
        text.setText("position :"+ mParam1+" id : "+ mParam2);
        return v;
    }
    private class MyAdapter extends ArrayAdapter<String> {
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