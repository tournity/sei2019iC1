package com.tournity.Repository.Repositories;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tournity.Entities.UserEntity;
import com.tournity.Repository.Enums.RepositoryError;
import com.tournity.Repository.Listeners.RepositoryListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    public UserRepository() {
    }
    public static void Insert(final UserEntity newUser, final Context context, final RepositoryListener<UserEntity>listener){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="http://10.203.183.88:3000/api/guard/login";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    UserEntity user = UserEntity.fromJSON(new JSONObject(response).getJSONObject("userData"));
                    Toast.makeText(context, user.getId(), Toast.LENGTH_SHORT).show();
                    listener.onQueryCompleted(user);
                } catch (JSONException e) {
                    listener.onQueryFailed(RepositoryError.JSON_ERROR);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onQueryFailed(RepositoryError.DATA_ERROR);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("id", ""+newUser.getId());
                MyData.put("password", userData.getEncryptedPassword());
                return MyData;
            }
            @Override
            public Map<String, String> getHeaders() {
                Map<String,String> headers = new HashMap<String, String>();
                headers.put("Content-Type","application/x-www-form-urlencoded");
                return headers;
            }
        };
        queue.add(MyStringRequest);
    }



}
