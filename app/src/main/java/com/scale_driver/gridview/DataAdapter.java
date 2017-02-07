package com.scale_driver.gridview;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class DataAdapter extends ArrayAdapter<String> {

    private static final String[] mCountries = { "Рыжик", "Барсик", "Мурзик",
            "Мурка", "Васька", "Полосатик", "Матроскин", "Лизка", "Томосина",
            "Бегемот", "Чеширский", "Дивуар", "Тигра", "Лаура"};

    Context mContext;

    @Override
    public int getCount() {
        return 5;
    }

    public DataAdapter(Context context, int resource) {
        super(context, resource, mCountries);
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView label = (TextView) convertView;

        if (convertView == null) {
            convertView = new TextView(mContext);
            label = (TextView) convertView;
        }

        label.setText(mCountries[position]);
        label.setTextColor(Color.BLACK);
        label.setTextSize(20f);
        return convertView;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return mCountries[position];
    }
}




