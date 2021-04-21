package com.daniilvdovin.wowraider;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.daniilvdovin.wowraider.model.Character;
import com.daniilvdovin.wowraider.model.CharacterArmory;
import com.daniilvdovin.wowraider.model.GearItem;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class API {

    public interface ApiEvent {
        void UpdateListner();
    }
    public interface ErrorEvent{
        void Error(String message);
    }
    static ApiEvent UpdateListner;
    static ErrorEvent ErrorEvent;
    public static void setUpdateListner(ApiEvent apiEvent) {
        UpdateListner = apiEvent;
    }
    public static void setErrorListner(ErrorEvent errorListner) {
        ErrorEvent = errorListner;
    }
    static String
            ROOT = "https://raider.io",
            CHARACTER = "/api/v1/characters/profile?region=%s&realm=%s&name=%s",
            CHARACTER_FIELDS="&fields=gear,covenant,mythic_plus_scores,previous_mythic_plus_scores";

    static Character character;

    API(){

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
            e.printStackTrace();
        } catch (JSONException e) {
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
        }
        return false;
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
}
