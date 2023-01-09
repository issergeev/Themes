package com.issergeev.themes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private long time = 0;

    private Intent intent;

    private Button editThemes, editUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        editThemes = findViewById(R.id.edit_themes);
        editUsers = findViewById(R.id.edit_users);

        editThemes.setOnClickListener(this);
        editUsers.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.edit_themes):
                intent = new Intent(this, EditThemesActivity.class);
                break;
            case (R.id.edit_users):
                intent = new Intent(this, EditUsersActivity.class);
        }

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //time = System.currentTimeMillis() + 2000;
        if (time >= System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            time = System.currentTimeMillis() + 2000;
            Toast.makeText(this, "Нажмите еще раз для выхода", Toast.LENGTH_SHORT).show();
        }
    }
}