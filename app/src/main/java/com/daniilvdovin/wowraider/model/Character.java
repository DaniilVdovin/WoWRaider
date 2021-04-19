package com.daniilvdovin.wowraider.model;

import org.json.JSONObject;

public class Character extends JSONObject {

    public String
            name,
            race,
            classc,
            active_spec_name,
            active_spec_role,
            achievement_points,
            honorable_kills,
            thumbnail_url,
            gender,
            faction,
            region,
            realm,
            profile_url,
            profile_banner;
    public Gear gear;
    public RaidProgression raid_progression;
    public MythicPlusRanks
            mythic_plus_ranks,
            previous_mythic_plus_ranks;
    public MythicPlusScores
            mythic_plus_scores,
            previous_mythic_plus_scores;
    public  DungeonRun[]
            mythic_plus_recent_runs,
            mythic_plus_best_runs;
}
