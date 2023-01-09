package com.issergeev.themes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.sql.SQLException;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SqlDataWorker worker = new SqlDataWorker(this);

    private String[] user_data;
    private String appendixText, passwordText, loginText;

    private RelativeLayout parent;
    private Button signin;
    private EditText login, password;

    private Intent start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent);
        signin = findViewById(R.id.button);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);

        signin.setOnClickListener(this);

        SqlDataWorker worker = new SqlDataWorker(this);
        try {
            worker.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        worker.addAdmin();
        worker.close();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                loginText = login.getText().toString().trim().toLowerCase();
                passwordText = password.getText().toString();
                try {
                    worker.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                user_data = worker.getUser(loginText);

                if (user_data != null) {
//                    for (int i = 0; i < user_data.length; i++)
//                        Log.d("2", user_data[i] + " " + i);

                    if (user_data[1].equals(Encryption.EncryptWithSalt(passwordText, user_data[2]))) {
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

                        switch (Integer.valueOf(user_data[5])) {
                            case 0:
                                start = new Intent(this, StudentActivity.class);
                                break;
                            case 1:
                                start = new Intent(this, TeacherActivity.class);
                                break;
                            case 2:
                                start = new Intent(this, AdminActivity.class);
                        }
                    } else {
                        Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(parent, "Login or password incorrect", Snackbar.LENGTH_SHORT)
                            .setAction("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            })
                            .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                            .show();
                }
        }

        startActivity(start);
    }
}