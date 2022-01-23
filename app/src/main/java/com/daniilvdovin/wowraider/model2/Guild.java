package com.daniilvdovin.wowraider.model2;

import com.daniilvdovin.wowraider.model.RaidProgression;
import com.daniilvdovin.wowraider.model.Rank;

public class Guild {
    public Integer id;
    public String name,faction,profile_url;
    public Realm realm;
    public Region region;
    public GuildRaidRancing sanctum_of_domination,castle_nathria;
    public RaidProgression raid_progression;

    @Override
    public String toString() {
        return "Guild{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", faction='" + faction + '\'' +
                ", profile_url='" + profile_url + '\'' +
                ", realm=" + realm +
                ", region=" + region +
                ", sanctum_of_domination=" + sanctum_of_domination.toString()+
                ", castle_nathria=" + castle_nathria.toString() +
                ", raid_progression=" + raid_progression.toString()+
                '}';
    }
}
