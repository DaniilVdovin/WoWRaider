package com.daniilvdovin.wowraider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.inputmethod.CompletionInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView realm,region,name;
    ArrayAdapter<String> aregion, arealm,aname;
    String[] brealm,bregion, bname;

    ImageButton bt_seatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent characterViewer = new Intent(this,CharacterViewer.class);

        bt_seatch = findViewById(R.id.imageButton2);

        brealm = getResources().getStringArray(R.array.server_names);
        bregion = getResources().getStringArray(R.array.static_regions);

        realm = findViewById(R.id.editTextTextRealm);
        region = findViewById(R.id.editTextTextRegion);
        name = findViewById(R.id.editTextTextCharacterName);

        aregion = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, bregion);
        arealm = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, brealm);

        realm.setThreshold(1);
        region.setThreshold(1);
        name.setThreshold(1);

        realm.setAdapter(arealm);
        region.setAdapter(aregion);


        bt_seatch.setOnClickListener(l->{
            if(!region.getText().toString().equals("") && !realm.getText().toString().equals("") && !name.getText().toString().equals(""))
            {
                characterViewer.putExtra("request",
                        new String[]{
                                (String) region.getText().toString(),
                                (String) realm.getText().toString(),
                                (String) name.getText().toString()});

                startActivity(characterViewer);
            }
            else {
                Snackbar.make(name,"Please enter all arguments",Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}