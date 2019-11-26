package com.example.innovacer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckOut extends AppCompatActivity {

    private Button checkoutButton;
    public SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);
        checkoutButton = findViewById(R.id.checkout);
        sharedPreferences = getSharedPreferences("mypref",0);
        checkout();

    }

    private void checkout() {
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //building retrofit object
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(APIUrl.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                //Defining retrofit api service
                APIService service = retrofit.create(APIService.class);

                String Visitoremail = sharedPreferences.getString("Email","");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("CheckIn",false);
                editor.commit();


                Call<ResponseApi> call = service.checkOut(Visitoremail);
                call.enqueue(new Callback<ResponseApi>() {
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        Toast.makeText(getApplicationContext(), response.toString() + "Exit", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseApi> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });



            }
        });
    }
}
