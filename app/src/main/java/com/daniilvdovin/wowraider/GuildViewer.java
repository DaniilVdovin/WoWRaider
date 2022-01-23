package com.daniilvdovin.wowraider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.daniilvdovin.wowraider.model2.Guild;

import org.w3c.dom.Text;

public class GuildViewer extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guild_viewer);

        ImageButton share = findViewById(R.id.imageButton);
        TextView
                name = findViewById(R.id.tv_name),
                realm = findViewById(R.id.tv_realm),
                faction = findViewById(R.id.tv_faction),
                muthic_summary = findViewById(R.id.tv_muthic_sammeru);

        View sanctum_of_domination = findViewById(R.id.inc_sanctum_of_domination);
        TextView
                sanctum_of_domination_summary = sanctum_of_domination.findViewById(R.id.tv_guild_mythic_summary),
                sanctum_of_domination_name = sanctum_of_domination.findViewById(R.id.tv_Info),

                sanctum_of_domination_normal_world = sanctum_of_domination.findViewById(R.id.tv_guild_normal_world),
                sanctum_of_domination_normal_region = sanctum_of_domination.findViewById(R.id.tv_guild_normal_region),
                sanctum_of_domination_normal_real = sanctum_of_domination.findViewById(R.id.tv_guild_normal_realm),

                sanctum_of_domination_heroic_region = sanctum_of_domination.findViewById(R.id.tv_guild_heroic_region),
                sanctum_of_domination_heroic_world = sanctum_of_domination.findViewById(R.id.tv_guild_heroic_world),
                sanctum_of_domination_heroic_real = sanctum_of_domination.findViewById(R.id.tv_guild_heroic_realm),

                sanctum_of_domination_mythic_world = sanctum_of_domination.findViewById(R.id.tv_guild_mythic_world),
                sanctum_of_domination_mythic_region = sanctum_of_domination.findViewById(R.id.tv_guild_mythic_region),
                sanctum_of_domination_mythic_real = sanctum_of_domination.findViewById(R.id.tv_guild_mythic_realm);

        View  castle_nathria = findViewById(R.id.inc_castle_nathria);
        TextView
                castle_nathria_summary = castle_nathria.findViewById(R.id.tv_guild_mythic_summary),
                castle_nathria_name = castle_nathria.findViewById(R.id.tv_Info),

                castle_nathria_normal_world = castle_nathria.findViewById(R.id.tv_guild_normal_world),
                castle_nathria_normal_region = castle_nathria.findViewById(R.id.tv_guild_normal_region),
                castle_nathria_normal_real = castle_nathria.findViewById(R.id.tv_guild_normal_realm),

                castle_nathria_heroic_region = castle_nathria.findViewById(R.id.tv_guild_heroic_region),
                castle_nathria_heroic_world = castle_nathria.findViewById(R.id.tv_guild_heroic_world),
                castle_nathria_heroic_real = castle_nathria.findViewById(R.id.tv_guild_heroic_realm),

                castle_nathria_mythic_world = castle_nathria.findViewById(R.id.tv_guild_mythic_world),
                castle_nathria_mythic_region = castle_nathria.findViewById(R.id.tv_guild_mythic_region),
                castle_nathria_mythic_real = castle_nathria.findViewById(R.id.tv_guild_mythic_realm);

        API.geGuildAsync("eu","gordunni","Эффект Доплера");
        API.setUpdateListner(()->{
            Guild guild = API.guild;
           //Set info
            share.setOnClickListener(v->startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(guild.profile_url))));
            name.setText(guild.name);
            realm.setText(guild.realm.getName());
            faction.setText(guild.faction);
            muthic_summary.setText(guild.raid_progression.raids[0].summary);

            sanctum_of_domination_summary.setText(guild.raid_progression.raids[0].summary);
            sanctum_of_domination_name.setText("sanctum-of-domination");

            sanctum_of_domination_normal_world.setText(guild.sanctum_of_domination.normal.getWorld().toString());
            sanctum_of_domination_normal_region.setText(guild.sanctum_of_domination.normal.getRegion().toString());
            sanctum_of_domination_normal_real.setText(guild.sanctum_of_domination.normal.getRealm().toString());

            sanctum_of_domination_heroic_world.setText(guild.sanctum_of_domination.heroic.getWorld().toString());
            sanctum_of_domination_heroic_region.setText(guild.sanctum_of_domination.heroic.getRegion().toString());
            sanctum_of_domination_heroic_real.setText(guild.sanctum_of_domination.heroic.getRealm().toString());

            sanctum_of_domination_mythic_world.setText(guild.sanctum_of_domination.mythic.getWorld().toString());
            sanctum_of_domination_mythic_region.setText(guild.sanctum_of_domination.mythic.getRegion().toString());
            sanctum_of_domination_mythic_real.setText(guild.sanctum_of_domination.mythic.getRealm().toString());

            castle_nathria_summary.setText(guild.raid_progression.raids[1].summary);
            castle_nathria_name.setText("castle-nathria");

            castle_nathria_normal_world.setText(guild.castle_nathria.normal.getWorld().toString());
            castle_nathria_normal_region.setText(guild.castle_nathria.normal.getRegion().toString());
            castle_nathria_normal_real.setText(guild.castle_nathria.normal.getRealm().toString());

            castle_nathria_heroic_world.setText(guild.castle_nathria.heroic.getWorld().toString());
            castle_nathria_heroic_region.setText(guild.castle_nathria.heroic.getRegion().toString());
            castle_nathria_heroic_real.setText(guild.castle_nathria.heroic.getRealm().toString());

            castle_nathria_mythic_world.setText(guild.castle_nathria.mythic.getWorld().toString());
            castle_nathria_mythic_region.setText(guild.castle_nathria.mythic.getRegion().toString());
            castle_nathria_mythic_real.setText(guild.castle_nathria.mythic.getRealm().toString());
        });
    }
}