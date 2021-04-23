package com.daniilvdovin.wowraider.model2;

public class RaidRankingGuild {
    /*
    "rank": 1,
      "regionRank": 1,
      "guild": {
        "id": 1075539,
        "name": "Complexity Limit",
        "faction": "horde",
        "realm": {
          "id": 369,
          "connectedRealmId": 207,
          "name": "Illidan",
          "altName": null,
          "slug": "illidan",
          "altSlug": "illidan",
          "locale": "en_US",
          "isConnected": false
        },
        "region": {
          "name": "United States & Oceania",
          "slug": "us",
          "short_name": "US"
        },
        "path": "/guilds/us/illidan/Complexity%20Limit"
     */
    Integer rank,regionRank;
    Guild guild;
    String path;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getRegionRank() {
        return regionRank;
    }

    public void setRegionRank(Integer regionRank) {
        this.regionRank = regionRank;
    }

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
