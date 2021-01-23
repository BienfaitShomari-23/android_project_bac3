package com.jungo.ngenyproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jungo.ngenyproject.appdata.Chats;
import com.jungo.ngenyproject.appdata.Schools;

import java.util.ArrayList;

public class ChatFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    private class MyAdapter extends ArrayAdapter<Chats> {
        Context context ;
        ArrayList<Chats> schools;
        MyAdapter(Context c, ArrayList<Chats> listSchools){
            super(c, R.layout.fragment_items_view, R.id.title_text,listSchools);
            this.context = c;
            this.schools = listSchools;
        }

        @Override
        public int getPosition(@Nullable Chats item) {
            return item.getIdChat();
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getIdChat();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(R.layout.fragment_home_list_view, parent, false);
            TextView _title = v.findViewById(R.id.my_title);
            TextView _subTitle = v.findViewById(R.id.my_sub_title);
            _title.setText(schools.get(position).getMe());
            _subTitle.setText(schools.get(position).getText());
            return v;
        }
    }
    private void message(String message){
        Activity activity = getActivity();
        if(activity != null){
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
        }
    }
}