package com.issergeev.themes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLException;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    private SqlDataWorker worker;

    private EditText name, lastName;
    private Button button;
    private FloatingActionButton add;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        intent = getIntent();

        worker = new SqlDataWorker(this);
        try {
            worker.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        name = findViewById(R.id.userName);
        lastName = findViewById(R.id.userLastname);
        button = findViewById(R.id.saveButton);

        name.setText(intent.getStringExtra("name"));
        lastName.setText(intent.getStringExtra("lastname"));

        button.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        worker.close();

        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveButton:
                Toast.makeText(UserActivity.this, worker.updateUser(intent.getStringExtra("login"),
                        name.getText().toString(), lastName.getText().toString()) != 0 ? "Updated" : "Error",
                        Toast.LENGTH_SHORT).show();
                finish();
        }
    }
}