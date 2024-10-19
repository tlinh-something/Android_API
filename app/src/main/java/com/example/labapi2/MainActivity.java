package com.example.labapi2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.labapi2.model.Trainee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TraineeServices traineeServices;
    EditText etname, etemail, etphone, etgender;
    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etname = findViewById(R.id.editTextName);
        etemail = findViewById(R.id.editTextEmail);
        etphone = findViewById(R.id.editTextPhone);
        etgender = findViewById(R.id.editTextGender);
        btnSave = findViewById(R.id.button2);

        traineeServices = TraineeRepository.getTraineeServices();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    private void save() {
        String name = etname.getText().toString();
        String email = etemail.getText().toString();
        String phone = etphone.getText().toString();
        String gender = etgender.getText().toString();

        Trainee trainee = new Trainee(name, email, phone, gender);
        try {
            Call<Trainee> call = traineeServices.createTrainees(trainee);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if (response.body() != null) {
                        Toast.makeText(MainActivity.this, "Save Successfully", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Save Fail", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }





















}