package com.jungo.ngenyproject;
import android.app.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SchoolListView extends ArrayAdapter<String> {

    private final Context context;
    private final String[] maintitle;
    private final String[] subtitle;
    private final Integer[] imgid;
    LayoutInflater inflater;
    public SchoolListView(Context context, LayoutInflater inflater, String[] maintitle,String[] subtitle, Integer[] imgid) {
        super(context, R.layout.fragment_list_item, maintitle);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.inflater = inflater;
        this.maintitle=maintitle;
        this.subtitle=subtitle;
        this.imgid=imgid;

    }
    public View getView(int position,View view,ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.fragment_home_list_view ,  null , true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);

        titleText.setText(maintitle[position]);
        imageView.setImageResource(imgid[position]);
        subtitleText.setText(subtitle[position]);

        return rowView;

    };
}