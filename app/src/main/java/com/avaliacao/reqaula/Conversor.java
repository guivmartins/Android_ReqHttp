package com.avaliacao.reqaula;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Conversor {


    public User getInformacao(String end){
        String json = ConexaoAPI.getJsonFromApi(end);
        User retorno = parseJson(json);
        return retorno;
    }

    private User parseJson(String json){
        try {
            User user = new User();

            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("items");

            JSONObject git = array.getJSONObject(0);

            user.setLogin(git.getString("login"));
            user.setId(git.getString("id"));

            return user;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }}
