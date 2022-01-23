package com.daniilvdovin.wowraider.model;

public class Raid{
    public String
            name,
            summary;
    public Integer
            total_bosses,
            normal_bosses_killed,
            heroic_bosses_killed,
            mythic_bosses_killed;

    @Override
    public String toString() {
        return "Raid{" +
                "name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", total_bosses=" + total_bosses +
                ", normal_bosses_killed=" + normal_bosses_killed +
                ", heroic_bosses_killed=" + heroic_bosses_killed +
                ", mythic_bosses_killed=" + mythic_bosses_killed +
                '}';
    }
}
