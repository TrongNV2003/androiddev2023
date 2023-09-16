package vn.edu.usth.moodle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login, signup;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        signup = findViewById(R.id.signup1);
        login = findViewById(R.id.signin1);

        databaseHelper = new DatabaseHelper(this);

        login.setOnClickListener(view -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();

            if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                Toast.makeText(LoginActivity.this, "Required an account", Toast.LENGTH_SHORT).show();
            else{
                Boolean checkUserPass = databaseHelper.checkUsernamePassword(user, pass);
                if(checkUserPass){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "This account does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }
}
