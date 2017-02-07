package com.scale_driver.gridview;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BtnsFragment btnFrag;
    SetUpBtnsFragment setUpBtnsFragment;
    FragmentTransaction fragtr;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFrag = new BtnsFragment();
        setUpBtnsFragment = new SetUpBtnsFragment();
        fragtr = getFragmentManager().beginTransaction();
        fragtr.add(R.id.fragCont1, setUpBtnsFragment);
        fragtr.add(R.id.fragCont2, btnFrag);
        fragtr.commit();

    }
}


