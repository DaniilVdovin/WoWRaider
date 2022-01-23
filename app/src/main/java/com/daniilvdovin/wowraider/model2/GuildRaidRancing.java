package com.daniilvdovin.wowraider.model2;

import com.daniilvdovin.wowraider.model.Rank;

public class GuildRaidRancing {
    public Rank normal, heroic, mythic;

    @Override
    public String toString() {
        return "GuildRaidRancing{" +
                "normal=" + normal.toString() +
                ", heroic=" + heroic.toString() +
                ", mythic=" + mythic.toString() +
                '}';
    }
}
