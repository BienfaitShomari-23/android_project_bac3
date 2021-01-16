package com.jungo.ngenyproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StudentTabActivityFragment extends Fragment {
        // When requested, this adapter returns a DemoObjectFragment,
        // representing an object in the collection.
        TabPageAdapter tabPageAdapter;
        ViewPager viewPager;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_student_tab_activity, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            tabPageAdapter = new TabPageAdapter(getChildFragmentManager());
            viewPager = view.findViewById(R.id.pager);
            viewPager.setAdapter(tabPageAdapter);
        }
    }