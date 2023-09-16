package vn.edu.usth.moodle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText username, password, re_password;
    Button signup, login;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        re_password = findViewById(R.id.repassword);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.signin);

        databaseHelper = new DatabaseHelper(this);

        signup.setOnClickListener(view -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            String repass = re_password.getText().toString();

            if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                Toast.makeText(MainActivity.this, "All fields Required", Toast.LENGTH_SHORT).show();
            else{
                if(pass.equals(repass)){
                    Boolean checkUser = databaseHelper.checkUsername(user);
                    if(!checkUser){
                        Boolean insert = databaseHelper.insertData(user,pass);
                        if(insert){
                            Toast.makeText(MainActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this, "Register failed", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "This account already exists", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Password are not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}