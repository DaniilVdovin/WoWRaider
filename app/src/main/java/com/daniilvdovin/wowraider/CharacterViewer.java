package com.daniilvdovin.wowraider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.daniilvdovin.wowraider.model.Character;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CharacterViewer extends AppCompatActivity {
    Character character;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_viewer);

        TextView
                name = findViewById(R.id.tv_name),
                race = findViewById(R.id.tv_race),
                class_cpec = findViewById(R.id.tv_class_spec_name),
                itemlvl = findViewById(R.id.tv_itemlvl),
                covenant = findViewById(R.id.tv_covenant);
        RecyclerView
                dnm_gear = findViewById(R.id.dnm_gear),
                dnm_mpluseprogress = findViewById(R.id.dnm_mplusescore);

        dnm_gear.setLayoutManager(new GridLayoutManager(this,8));
        LinearLayoutManager l = new LinearLayoutManager(this);
        l.setOrientation(LinearLayoutManager.HORIZONTAL);
        dnm_mpluseprogress.setLayoutManager(l);

        ImageView icon = findViewById(R.id.imageView2);
        ImageButton shared = findViewById(R.id.imageButton);
        //After Get Character
        API.setUpdateListner(() -> {
            character = API.character;
            new DonwloadImageTask(icon).execute(character.thumbnail_url);
            name.setText(character.name);
            race.setText(character.race);
            class_cpec.setText(String.format("%s/%s (%s)",character.classc,character.active_spec_name,character.active_spec_role));

            //covenant
            if(character.covenant != null){
                covenant.setText(String.format("%s Renown %s",character.covenant.name,character.covenant.renown_level));
            }else {
                covenant.setVisibility(View.GONE);
            }

            //Gear
            if(character.gear!=null){
                if(character.gear.item_level_total!=null)
                    itemlvl.setText(String.format("%s Item Level",character.gear.item_level_equipped));
                else
                    itemlvl.setVisibility(View.GONE);

                Adapter adapter = new Adapter(this,API.ArmoryTyArray(character.gear.items));
                dnm_gear.setAdapter(adapter);
            }

            //Mythic Score
            if(character.mythic_plus_scores != null)
            {
                AdapterMscore ar = new AdapterMscore(this, API.getAllMpluseScore());
                dnm_mpluseprogress.setAdapter(ar);
            }

            //Open profile
            shared.setOnClickListener(v->startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(character.profile_url))));



        });
        API.setErrorListner((s)->{
            Snackbar.make(dnm_gear,s,Snackbar.LENGTH_SHORT).show();
            onBackPressed();
        });
        String[] request = getIntent().getStringArrayExtra("request");
        API.getCharacterAsync(request[0],request[1],request[2]);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}