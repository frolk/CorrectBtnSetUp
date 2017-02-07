package com.scale_driver.gridview;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class BtnsFragment extends Fragment {


    ArrayAdapter<String> mAdapter;
    CorrectDB correctDB;
    SQLiteDatabase db;

    private static final String[] mContacts = {"-100", "-200", "-300",
            "-500", "+100", "+200", "+300", "+400", "+500",
            "+1000", "+2000", "-2000"};
    GridView gvMain;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        correctDB = new CorrectDB(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.btnfragment, null);

        mAdapter = new ArrayAdapter<String>(getActivity(), R.layout.item, R.id.tvText, mContacts);
        gvMain = (GridView) v.findViewById(R.id.gridView1);
        gvMain.setAdapter(mAdapter);
        gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "Выбрано: " + mAdapter.getItem(i), Toast.LENGTH_SHORT).show();
            }
        });

        gvMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "Долгое нажатие на: " + mAdapter.getItem(i), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return v;

    }

}
