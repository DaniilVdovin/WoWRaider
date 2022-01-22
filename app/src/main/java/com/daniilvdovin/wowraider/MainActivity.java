package com.daniilvdovin.wowraider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.Toast;

import com.daniilvdovin.wowraider.Token.AdapterToken;
import com.daniilvdovin.wowraider.mainscreen.AdapterRaidRanking;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView realm,region,name;
    ArrayAdapter<String> aregion, arealm,aname;
    String[] brealm,bregion, bname;

    RecyclerView dnm_guildranking,dnm_token_price;

    ImageButton bt_seatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        API.setDefaultContext(this);
        API.setErrorListner((s)->{
            Snackbar.make(dnm_guildranking,s,Snackbar.LENGTH_SHORT).show();
        });
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

        dnm_guildranking = findViewById(R.id.dnm_raidrankguild);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        dnm_guildranking.setLayoutManager(linearLayoutManager);



        API.setUpdateRaidRanking(()->{
            AdapterRaidRanking adapterRaidRanking = new AdapterRaidRanking(this,API.getArrayOfRaidRankingGuild(),5);
            dnm_guildranking.setAdapter(adapterRaidRanking);
        });


        findViewById(R.id.wowmap).setOnClickListener((v -> {
            String packageName = "com.daniilvdovin.wowmap";
            Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
            if(intent == null) {
                Toast.makeText(this,"App Not Installed",Toast.LENGTH_LONG).show();
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+packageName));
            }
            startActivity(intent);
        }));

        API.getRaidRankingAsynk("sanctum-of-domination","mythic","world");


        dnm_token_price = findViewById(R.id.dnm_mplusescore);
        dnm_token_price.setLayoutManager(new GridLayoutManager(this,5));

        API.setUpdateToken(()->{
            Log.e("TAG",API.token.eu.current_price + " : : "+ API.token.eu.last_change);
            AdapterToken adapterToken = new AdapterToken(this,API.getArrayTokens());
            dnm_token_price.setAdapter(adapterToken);
        });

        API.getTokenAsynk();
    }
}