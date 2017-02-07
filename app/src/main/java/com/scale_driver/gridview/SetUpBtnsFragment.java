package com.scale_driver.gridview;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static com.scale_driver.gridview.R.id.etBtnName;


public class SetUpBtnsFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "mLog";
    SeekBar seekBar;
    int currentBtn = 0;
    Button btnInc, btnDec, btnSave, btnClose;
    int correctvalue = 0;
    TextView seekText;
    EditText etName;

    SQLiteDatabase db;
    CorrectDB correctDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        correctDB = new CorrectDB(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.set_up_btns_fragment, null);

        etName = (EditText) v.findViewById(R.id.etBtnName);

        btnSave = (Button) v.findViewById(R.id.btnSaveData);
        btnSave.setOnClickListener(this);

        btnClose = (Button) v.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);


        btnInc = (Button) v.findViewById(R.id.btnInc);
        btnInc.setOnClickListener(this);

        btnDec = (Button) v.findViewById(R.id.btnDec);
        btnDec.setOnClickListener(this);

        seekBar = (SeekBar) v.findViewById(R.id.seekBar);
        correctvalue = seekBar.getProgress();
        seekText = (TextView) v.findViewById(R.id.seekText);
        seekText.setText(String.valueOf(correctvalue));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                correctvalue = i;
                seekText.setText(String.valueOf(correctvalue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return v;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInc:
                changeSeekValue(correctvalue++);
                break;

            case R.id.btnDec:
                changeSeekValue(correctvalue--);
                break;

            case R.id.btnSaveData:
                saveData();
                break;

            case R.id.btnClose:
                readData();
                break;

        }
    }

    private void changeSeekValue(int value) {
        seekText.setText(String.valueOf(value));
        seekBar.setProgress(value);
    }

    private void saveData() {
        db = correctDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CorrectDB.KEY_NAME, currentBtn);
        contentValues.put(CorrectDB.KEY_NAME, etName.getText().toString());
        contentValues.put(CorrectDB.KEY_VALUE1, seekBar.getProgress());
        db.insert(CorrectDB.TABLE_BTNS, null, contentValues);
        correctDB.close();
        Toast.makeText(getActivity(), "Saved data", Toast.LENGTH_SHORT).show();
    }

    private void readData(){
        db = correctDB.getWritableDatabase();
        Cursor cursor = db.query(CorrectDB.TABLE_BTNS, null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(CorrectDB.KEY_ID);
            int nameIndex = cursor.getColumnIndex(CorrectDB.KEY_NAME);
            int value1Index = cursor.getColumnIndex(CorrectDB.KEY_VALUE1);

            do {
                Log.d(TAG, "id = " + cursor.getInt(idIndex)
                        + ", name = " + cursor.getString(nameIndex) + ", value = " + cursor.getInt(value1Index));
            } while (cursor.moveToNext());

        } else Log.d (TAG, "0 rows");
        cursor.close();
    }

}
