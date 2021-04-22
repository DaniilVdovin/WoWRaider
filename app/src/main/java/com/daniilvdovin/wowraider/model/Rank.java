package com.daniilvdovin.wowraider.model;

public class Rank {
    String name;
    Integer
            world,
            region,
            realm;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWorld() {
        return world;
    }

    public void setWorld(Integer world) {
        this.world = world;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public Integer getRealm() {
        return realm;
    }

    public void setRealm(Integer realm) {
        this.realm = realm;
    }
}
