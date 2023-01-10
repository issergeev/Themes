package com.issergeev.themes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener {
    private List<String> logins;

    private SqlDataWorker worker;

    private RelativeLayout parent;
    private EditText login, password, name, lastname;
    private Spinner role;
    private Button save;

    private String loginText, passwordText, nameText, lastnameText;
    private String[] data;

    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        login = findViewById(R.id.loginText);
        password = findViewById(R.id.passwordText);
        name = findViewById(R.id.userName);
        lastname = findViewById(R.id.userLastname);
        role = findViewById(R.id.role);
        save = findViewById(R.id.saveButton);
        parent = findViewById(R.id.addUserLayout);

        worker = new SqlDataWorker(this);

        snackbar = Snackbar.make(parent, "Empty fields", Snackbar.LENGTH_SHORT)
                .setAction("CLOSE", this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginText = login.getText().toString().trim();
                passwordText = password.getText().toString().trim();
                nameText = name.getText().toString().trim();
                lastnameText = lastname.getText().toString().trim();

                if (loginText.length() > 0 && passwordText.length() > 0 &&
                    nameText.length() > 0 && lastnameText.length() > 0) {
                    try {
                        worker.open();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    logins = new ArrayList<>(worker.getList());
                    if (!logins.contains(loginText)) {

                        data = Encryption.Encrypt(passwordText);
                        Toast.makeText(NewUserActivity.this,
                                worker.insertUser(loginText, data[0], data[1], nameText, lastnameText,
                                        role.getSelectedItemPosition()) > 0 ? "Successfully added" : "Error",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(NewUserActivity.this, "Login unavailable", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    snackbar.show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        worker.close();

        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

    }
}