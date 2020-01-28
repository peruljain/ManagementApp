package com.example.innovacer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText VisitorName;
    private EditText VisitorEmail;
    private EditText VisitorPhone;
    private EditText HostName;
    private EditText HostEmail;
    private EditText HostPhone;
    private Button CheckInButton;
    public SharedPreferences sharedPreferences;
    public static final String mypreference = "mypref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VisitorName = findViewById(R.id.visitor_name);
        VisitorEmail = findViewById(R.id.visitor_email);
        VisitorPhone = findViewById(R.id.visitor_phone);

        HostName = findViewById(R.id.host_name);
        HostEmail = findViewById(R.id.host_email);
        HostPhone = findViewById(R.id.host_phone);

        CheckInButton = findViewById(R.id.checkIn);

        sharedPreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        CheckIn();

    }

    public void CheckIn(){

        CheckInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String Vname = VisitorName.getText().toString();
                String Vemail = VisitorName.getText().toString();
                String Vphone = (VisitorPhone.getText().toString());

                String Hname = HostName.getText().toString();
                String Hemail = HostEmail.getText().toString();

                String Hphone = (HostPhone.getText().toString());

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Email",Vemail);
                editor.putBoolean("CheckIn",true);
                editor.apply();

                //building retrofit object
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(APIUrl.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                //Defining retrofit api service
                APIService service = retrofit.create(APIService.class);


                Call<ResponseApi> call = service.createDatabase(Vname,Vemail,Vphone,Hname,Hemail,Hphone);

                call.enqueue(new Callback<ResponseApi>() {



                    @Override
                    public void onResponse(@NonNull Call<ResponseApi> call,@NonNull Response<ResponseApi> response) {

                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();

                        Intent i = new Intent(getApplicationContext(), CheckOut.class);
                        startActivity(i);

                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseApi> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), CheckOut.class);
                        startActivity(i);
                    }
                });





            }
        });



    }



}
