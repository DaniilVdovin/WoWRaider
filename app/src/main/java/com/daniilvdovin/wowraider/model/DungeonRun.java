package com.daniilvdovin.wowraider.model;

public class DungeonRun {
    public  String
            dungeon,
            short_name,
            completed_at,
            url,
            zone_id;
    public  Integer
            mythic_level,
            clear_time_ms,
            num_keystone_upgrades;
    public double score;
    public Affix[] affixes;

    public String getDungeon() {
        return dungeon;
    }

    public void setDungeon(String dungeon) {
        this.dungeon = dungeon;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getCompleted_at() {
        return completed_at;
    }

    public void setCompleted_at(String completed_at) {
        this.completed_at = completed_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getMythic_level() {
        return mythic_level;
    }
    public void setMythic_level(Integer mythic_level) {
        this.mythic_level = mythic_level;
    }
    public Integer getNum_keystone_upgrades() {
        return num_keystone_upgrades;
    }
    public void setNum_keystone_upgrades(Integer num_keystone_upgrades) {this.num_keystone_upgrades = num_keystone_upgrades;}
    public Double getScore() {
        return score;
    }
    public void setScore(Integer score) {
        this.score = score;
    }
}
