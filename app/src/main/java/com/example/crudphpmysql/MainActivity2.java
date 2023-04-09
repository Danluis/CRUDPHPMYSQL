package com.example.crudphpmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    EditText etName,etPassword, etEdad,etFechaRegistro, etId;
    Button btnEdit,btnDelete;
    String id;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        requestQueue = Volley.newRequestQueue(this);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            id = extras.getString("id");
        }
        //UI
        initUI();

        readUser();

        btnDelete.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
    }


    private void initUI(){
        //EditText
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        etEdad = findViewById(R.id.etEdad);
        etFechaRegistro = findViewById(R.id.etFechaRegistro);
        etId = findViewById(R.id.etId);

        //Buttons
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

    }

    private void readUser(){
        String URL = "http://192.168.2.14/android/fetch.php?id=" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String name, edad, password, fechaRegistro;
                        try{
                            name = response.getString("name");
                            edad = response.getString("edad");
                            password = response.getString("password");
                            fechaRegistro = response.getString("fechaRegistro");

                            etName.setText(name);
                            etEdad.setText(edad);
                            etPassword.setText(password);
                            etFechaRegistro.setText(fechaRegistro);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }

    /**Overrride**/
    @Override
    public  void onClick(View v){
        int id = v.getId();

        if (id == R.id.btnEdit){
            String name = etName.getText().toString().trim();
            String edad = etEdad.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String fechaRegistro = etFechaRegistro.getText().toString().trim();

            updateUser(name,edad,password,fechaRegistro);

        } else if (id == R.id.btnDelete) {
            String idUser = etId.getText().toString().trim();

            removeUser(idUser);
        }
    }

    private void updateUser(final String name,final String edad,final String password,final String fechaRegistro) {
        String URL = "http://192.168.2.14/android/edit.php";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity2.this, "Updated successfully!!!", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",id);
                params.put("name", name);
                params.put("edad", edad);
                params.put("password", password);
                params.put("fechaRegistro", fechaRegistro);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void removeUser(String idUser) {
        String URL = "http://192.168.2.14/android/delete.php";
        StringRequest stringRequest = new StringRequest(

                Request.Method.POST,
                URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        finish();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", idUser);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}