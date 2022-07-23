package com.wisein.wiselab.config;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonInstance {

    static JSONObject jObjectInstance;
    static JSONParser jsonParserInstatnce;
    static JSONArray jsonArrayInstance;

    private JsonInstance() {}

    public static JSONObject getjObjectInstance() {
        if (jObjectInstance==null) {
            jObjectInstance = new JSONObject();
        }
        return jObjectInstance;
    }

    public static JSONParser getJsonParserInstatnce() {
        if (jsonParserInstatnce==null) {
            jsonParserInstatnce = new JSONParser();
        }
        return jsonParserInstatnce;
    }

    public static JSONArray getJsonArrayInstance() {
        if (jsonArrayInstance==null) {
            jsonArrayInstance = new JSONArray();
        }
        return jsonArrayInstance;
    }
}
