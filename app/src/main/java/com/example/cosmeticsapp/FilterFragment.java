package com.example.cosmeticsapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;

import androidx.annotation.Nullable;

public class FilterFragment extends Fragment {
EditText description, name;
Button filter;
public FilterFragment(){

}

public static FilterFragment newInstance(){
    FilterFragment fragment=new FilterFragment();
    return fragment;
}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.filter_fragment,container,false);
        description=view.findViewById(R.id.desc);
        name=view.findViewById(R.id.name);
        filter=view.findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String desc=description.getText().toString();
                String nameF=name.getText().toString();
                getFragmentManager().beginTransaction().replace(R.id.frame,MainFragment.newInstance(desc,nameF)).commitAllowingStateLoss();

            }
        });
        return view;
    }

}
