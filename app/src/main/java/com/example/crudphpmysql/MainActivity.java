package com.example.crudphpmysql;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName,etPassword, etEdad,etFechaRegistro, etId;
    Button btnCreate,btnFetch;

    RequestQueue requestQueue;

    private static  final String URL1 = "http://192.168.2.14/android/save.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);
        //UI
        initUI();

        btnCreate.setOnClickListener(this);
        btnFetch.setOnClickListener(this);
    }

    private void initUI(){
        //EditText
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        etEdad = findViewById(R.id.etEdad);
        etFechaRegistro = findViewById(R.id.etFechaRegistro);
        etId = findViewById(R.id.etId);

        //Buttons
        btnCreate = findViewById(R.id.btnCreate);
        btnFetch = findViewById(R.id.btnFetch);

    }
    /**Overrride**/
    @Override
     public  void onClick(View v){
        int id = v.getId();

        if (id == R.id.btnCreate){
            String name = etName.getText().toString().trim();
            String edad = etEdad.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String fechaRegistro = etFechaRegistro.getText().toString().trim();

            createUser(name,edad,password,fechaRegistro);

        } else if (id == R.id.btnFetch) {

            Intent intent = new Intent(this,MainActivity2.class);
            intent.putExtra("id", etId.getText().toString().trim());
            startActivity(intent);
        }


    }

     private void createUser(final String name,final String edad,final String password,final String fechaRegistro){

         StringRequest stringRequest = new StringRequest(
                 Request.Method.POST,
                 URL1,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {

                         Toast.makeText(MainActivity.this,"Correct",Toast.LENGTH_SHORT).show();

                     }
                 },
                 new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {

                     }
                 }
         ){
             @Nullable
             @Override
             protected Map<String, String> getParams() throws AuthFailureError {
                 Map<String, String> params = new HashMap<>();
                 params.put("name",name);
                 params.put("edad",edad);
                 params.put("password",password);
                 params.put("fechaRegistro",fechaRegistro);
                 return params;
             }
         };

         requestQueue.add(stringRequest);
     }
}