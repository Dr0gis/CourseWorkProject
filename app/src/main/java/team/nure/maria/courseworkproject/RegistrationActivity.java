package team.nure.maria.courseworkproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dr0gi on 25.12.2017.
 */

public class RegistrationActivity extends AppCompatActivity {

    private Button bRegister;
    private EditText etFullName;
    private EditText etLogin;
    private EditText etPassword;
    private EditText etEmail;

    private ServerController server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration);

        server = ServerController.Create();

        bRegister = findViewById(R.id.bRegister);
        etFullName = findViewById(R.id.etFullName);
        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullNameValue = etFullName.getText().toString();
                String loginValue = etLogin.getText().toString();
                String passwordValue = etPassword.getText().toString();
                String emailValue = etEmail.getText().toString();

                server.getServerQuery().registration(0, loginValue, passwordValue, fullNameValue, emailValue, "user").enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "You are register", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(RegistrationActivity.this, "Response with error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(RegistrationActivity.this, "Server not found", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
