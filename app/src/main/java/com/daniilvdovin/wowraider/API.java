package com.daniilvdovin.wowraider;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.daniilvdovin.wowraider.Token.Token;
import com.daniilvdovin.wowraider.Token.TokenByRegion;
import com.daniilvdovin.wowraider.model.Character;
import com.daniilvdovin.wowraider.model.CharacterArmory;
import com.daniilvdovin.wowraider.model.DungeonRun;
import com.daniilvdovin.wowraider.model.GearItem;
import com.daniilvdovin.wowraider.model.MythicPluseProgressItem;
import com.daniilvdovin.wowraider.model.Raid;
import com.daniilvdovin.wowraider.model.RaidProgression;
import com.daniilvdovin.wowraider.model.Rank;
import com.daniilvdovin.wowraider.model2.Guild;
import com.daniilvdovin.wowraider.model2.GuildRaidRancing;
import com.daniilvdovin.wowraider.model2.RaidRanking;
import com.daniilvdovin.wowraider.model2.RaidRankingGuild;
import com.daniilvdovin.wowraider.model2.Realm;
import com.daniilvdovin.wowraider.model2.Region;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleToIntFunction;

public class API {

    public interface ApiEvent {
        void UpdateListner();
    }
    public interface ApiRankingEvent{
        void UpdateRaidRanking();
    }
    public interface ApiTokenEvent{
        void Update();
    }
    public interface ErrorEvent{
        void Error(String message);
    }
    static ApiEvent UpdateListner;
    static ErrorEvent ErrorEvent;
    static ApiRankingEvent apiRankingEvent;
    static ApiTokenEvent apiTokenEvent;
    public static void setUpdateListner(ApiEvent apiEvent) {
        UpdateListner = apiEvent;
    }
    public static void setUpdateToken(ApiTokenEvent apiEvent) {
        apiTokenEvent = apiEvent;
    }
    public static void setUpdateRaidRanking(ApiRankingEvent apiEvent) {
        apiRankingEvent = apiEvent;
    }
    public static void setErrorListner(ErrorEvent errorListner) {
        ErrorEvent = errorListner;
    }

    static String
            ROOT = "https://raider.io",

            ROOT_TOKEN ="https://wowtokenprices.com",
            TOKEN_CURRENT_PRICE="/current_prices.json",

            CHARACTER = "/api/v1/characters/profile?region=%s&realm=%s&name=%s",
            GUILD = "/api/v1/guilds/profile?region=%s&realm=%s&name=%s&fields=raid_progression,raid_rankings",
            CHARACTER_FIELDS="&fields=guild,gear,covenant,mythic_plus_scores,previous_mythic_plus_scores,mythic_plus_best_runs,mythic_plus_ranks,mythic_plus_recent_runs",

            RAID_WORLD_PROGRESS = "/api/v1/raiding/raid-rankings?raid=%s&difficulty=%s&region=%s";

    static Character character;
    static Guild guild;
    static RaidRanking raidRanking;
    static Token token;
    static Context context;
    API(){

    }

    static void setDefaultContext(Context c){
        context = c;
    }
    //Tool
    static GearItem[] ArmoryTyArray(CharacterArmory armory){
        GearItem[] gearItems = new GearItem[]{
                armory.head,
                armory.neck,
                armory.shoulder,
                armory.back,
                armory.waist,
                armory.shirt,
                armory.wrist,
                armory.hands,
                armory.legs,
                armory.feet,
                armory.finger1,
                armory.finger2,
                armory.trinket1,
                armory.trinket2,
                armory.mainhand,
                armory.offhand
        };
        List<GearItem> temp = new ArrayList<>();
        for (GearItem i:
             gearItems) {
            if(i!=null)
                temp.add(i);
        }
        return temp.toArray(new GearItem[0]);
    }
    static JSONObject getJsonFromUrl(String cmd) {
        try {
            return jsonReader.readJsonFromUrl(cmd);
        } catch (IOException e) {
            JSONObject error = new JSONObject();
            try {
                error.put("error", e.getMessage().toString());
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            isError(error);
            e.printStackTrace();
        } catch (JSONException e) {
            JSONObject error = new JSONObject();
            try {
                error.put("error", e.getMessage().toString());
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            isError(error);
            e.printStackTrace();
        }
        return null;
    }
    static boolean isError(JSONObject object){
        if(object==null) {
                ErrorEvent.Error("Just Null");
            return true;
        }
        else if(object.has("statusCode"))
        {
            try {
                ErrorEvent.Error(object.getString("message"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }else  if(object.has("error")){
            ErrorEvent.Error("API Error");
            return true;
        }
        return false;
    }
    //Token
    static Token getToken(){
        try {
            Token token = new Gson().fromJson(getJsonFromUrl(ROOT_TOKEN+TOKEN_CURRENT_PRICE).toString(), Token.class);
            return token;
        }catch (Exception e){
            isError(null);
        }
        return null;
    }
    static void getTokenAsynk(){
        new getTokenAsyncRequest().execute();
    }
    static TokenByRegion[] getArrayTokens(){
        return new TokenByRegion[]{
                token.us,
                token.eu,
                token.china,
                token.korea,
                token.taiwan
        };
    }
    //Raid progress Guild
    static RaidRanking getRaidRanking(String raid ,String difficulty, String region){
        try {
            RaidRanking raidRanking = new Gson().fromJson(getJsonFromUrl(ROOT+String.format(RAID_WORLD_PROGRESS,raid,difficulty,region)).toString(), RaidRanking.class);
            return raidRanking;
        }catch (Exception e){
            isError(null);
        }
        return null;
    }
    static void getRaidRankingAsynk(String raid ,String difficulty, String region){
        if(region!=null||difficulty!=null||raid!=null)
            if(!region.equals("") && !raid.equals("") && !difficulty.equals(""))
                new getRaidRankingAsyncRequest().execute(raid,difficulty,region);
            else ErrorEvent.Error("Arguments null");
    }
    static RaidRankingGuild[] getArrayOfRaidRankingGuild(){
        if(raidRanking!=null)
            return raidRanking.raidRankings;
        else
            ErrorEvent.Error("Raid Rankings null");
        return null;
    }
    //Character
    static JSONObject getJsonCharacter(String region,String realm,String name){
        return getJsonFromUrl(ROOT+String.format(CHARACTER,region,realm,name)+CHARACTER_FIELDS);
    }
    static Character getCharacter(String region,String realm,String name) throws JSONException {
        JSONObject jsonC = getJsonCharacter(region,realm,name);
        if(isError(jsonC))return null;
        String jsonCharacter = jsonC.toString().replace("\"class\"","\"classc\"");
        Character obj = new Gson().fromJson(jsonCharacter, Character.class);
        return obj;
    }
    static void getCharacterAsync(String region, String realm, String name){
        Log.e("REQUEST", region+realm+name);
        if(region!=null||realm!=null||name!=null)
            if(!region.equals("") && !realm.equals("") && !name.equals(""))
                new getCharacterAsyncRequest().execute(region,realm,name);
        else ErrorEvent.Error("Arguments null");
    }
    //Guild
    static JSONObject getJsonGuild(String region,String realm,String name){
        return getJsonFromUrl(ROOT+String.format(GUILD,region,realm,name));
    }
    static Guild getGuild(String region, String realm, String name) throws JSONException {
        JSONObject jsonC = getJsonGuild(region,realm,name);
        if(isError(jsonC))return null;
        Guild obj = new Guild();
        obj.realm = new Realm();
        obj.realm.setName(jsonC.getString("realm"));
        obj.name = jsonC.getString("name");
        obj.id = 0;
        obj.region = new Region();
        obj.faction = jsonC.getString("faction");
        obj.region.setName(jsonC.getString("region"));
        obj.profile_url = jsonC.getString("profile_url");
        obj.sanctum_of_domination =new GuildRaidRancing();
        obj.castle_nathria =new GuildRaidRancing();
        obj.sanctum_of_domination.normal = new Gson().fromJson(jsonC.getJSONObject("raid_rankings").getJSONObject("sanctum-of-domination").getJSONObject("normal").toString(),Rank.class);
        obj.sanctum_of_domination.heroic = new Gson().fromJson(jsonC.getJSONObject("raid_rankings").getJSONObject("sanctum-of-domination").getJSONObject("heroic").toString(),Rank.class);
        obj.sanctum_of_domination.mythic = new Gson().fromJson(jsonC.getJSONObject("raid_rankings").getJSONObject("sanctum-of-domination").getJSONObject("mythic").toString(),Rank.class);
        obj.castle_nathria.normal = new Gson().fromJson(jsonC.getJSONObject("raid_rankings").getJSONObject("castle-nathria").getJSONObject("normal").toString(),Rank.class);
        obj.castle_nathria.heroic = new Gson().fromJson(jsonC.getJSONObject("raid_rankings").getJSONObject("castle-nathria").getJSONObject("heroic").toString(),Rank.class);
        obj.castle_nathria.mythic = new Gson().fromJson(jsonC.getJSONObject("raid_rankings").getJSONObject("castle-nathria").getJSONObject("mythic").toString(),Rank.class);
        obj.raid_progression = new RaidProgression();
        obj.raid_progression.raids = new Raid[]{
                new Gson().fromJson(jsonC.getJSONObject("raid_progression").getJSONObject("sanctum-of-domination").toString(),Raid.class),
                new Gson().fromJson(jsonC.getJSONObject("raid_progression").getJSONObject("castle-nathria").toString(),Raid.class)
        };
        return obj;
    }
    public static void geGuildAsync(String region, String realm, String name){
        Log.e("REQUEST", region+realm+name);
        if(region!=null||realm!=null||name!=null)
            if(!region.equals("") && !realm.equals("") && !name.equals(""))
                new getGuildsyncRequest().execute(region,realm,name);
            else ErrorEvent.Error("Arguments null");
    }


    static DungeonRun MythicPluseGetBestRun(){
        if(character.mythic_plus_best_runs.length>0)
        return character.mythic_plus_best_runs[0];
        else return null;
    }

    static Rank[] getListRanks(){

        if(character.mythic_plus_ranks.overall!=null)
                character.mythic_plus_ranks.overall.setName(context.getResources().getString(R.string.overall));
        if(character.mythic_plus_ranks.classc!=null)
                character.mythic_plus_ranks.classc.setName(context.getResources().getString(R.string.class_));

        if(character.mythic_plus_ranks.class_dps!=null)
                character.mythic_plus_ranks.class_dps.setName(context.getResources().getString(R.string.class_dps));
        if(character.mythic_plus_ranks.class_healer!=null)
                character.mythic_plus_ranks.class_healer.setName(context.getResources().getString(R.string.class_hps));
        if(character.mythic_plus_ranks.class_tank!=null)
                character.mythic_plus_ranks.class_tank.setName(context.getResources().getString(R.string.class_tank));

        if(character.mythic_plus_ranks.dps!=null)
                character.mythic_plus_ranks.dps.setName(context.getResources().getString(R.string.dps));
        if(character.mythic_plus_ranks.healer!=null)
                character.mythic_plus_ranks.healer.setName(context.getResources().getString(R.string.hps));
        if(character.mythic_plus_ranks.tank!=null)
                character.mythic_plus_ranks.tank.setName(context.getResources().getString(R.string.tank));

        Rank[] temp = new Rank[]{
                character.mythic_plus_ranks.overall,
                character.mythic_plus_ranks.classc,

                character.mythic_plus_ranks.class_dps,
                character.mythic_plus_ranks.class_healer,
                character.mythic_plus_ranks.class_tank,

                character.mythic_plus_ranks.dps,
                character.mythic_plus_ranks.healer,
                character.mythic_plus_ranks.tank
        };
        List<Rank> templ = new ArrayList<>();
        for (Rank l :
             temp) {
            if(l!=null)
                if(l.getName()!=null && l.getName()!="")
                    templ.add(l);
        }
         return templ.toArray(new Rank[0]);
    }
    static MythicPluseProgressItem[] getAllMpluseScore() {
        MythicPluseProgressItem[] temp =
                new MythicPluseProgressItem[]{
                        new MythicPluseProgressItem("DPS",""+character.mythic_plus_scores.getAll(),context.getResources().getString(R.string.mpallscore)),
                        MythicPluseGetBestRun()!=null?
                                new MythicPluseProgressItem(MythicPluseGetBestRun().short_name,"+"+MythicPluseGetBestRun().mythic_level,""+context.getResources().getString(R.string.timed_run))
                                :
                                null
                };
        return temp;
    }

    //Async
    static class getCharacterAsyncRequest extends AsyncTask<String, Integer, String> {
        Character character;
        @Override
        protected String doInBackground(String... arg) {
            try {
                character =  getCharacter(arg[0], arg[1],arg[2]);
                if(character == null){
                    Thread.currentThread().interrupt();
                    return null;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "s";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(character!=null){
                API.character = this.character;
                UpdateListner.UpdateListner();
            }
            if(s==null){
                ErrorEvent.Error("Character not found");
            }

        }
    }
    static class getGuildsyncRequest extends AsyncTask<String, Integer, String> {
        Guild guild;
        @Override
        protected String doInBackground(String... arg) {
            try {
                guild =  getGuild(arg[0], arg[1],arg[2]);
                if(guild == null){
                    Thread.currentThread().interrupt();
                    return null;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "s";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(guild!=null){
                API.guild = this.guild;
                UpdateListner.UpdateListner();
            }
            if(s==null){
                ErrorEvent.Error("Guild not found");
            }

        }
    }
    static class getRaidRankingAsyncRequest extends AsyncTask<String, Integer, String> {
        RaidRanking raidRanking;
        @Override
        protected String doInBackground(String... arg) {
                raidRanking =  getRaidRanking(arg[0], arg[1],arg[2]);
                if(raidRanking == null){
                    Thread.currentThread().interrupt();
                    return null;
                }
            return "s";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(raidRanking!=null){
                API.raidRanking = this.raidRanking;
                apiRankingEvent.UpdateRaidRanking();
            }
            if(s==null){
                ErrorEvent.Error("Not found");
            }

        }
    }
    static class getTokenAsyncRequest extends AsyncTask<String, Integer, String> {
        Token token;
        @Override
        protected String doInBackground(String... arg) {
                token =  getToken();
                if(token == null){
                    Thread.currentThread().interrupt();
                    return null;
                }
            return "s";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(token!=null){
                API.token = this.token;
                apiTokenEvent.Update();
            }
            if(s==null){
                ErrorEvent.Error("Not found");
            }

        }
    }

}
