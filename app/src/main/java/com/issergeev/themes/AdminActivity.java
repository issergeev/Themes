package com.issergeev.themes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
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
    }
}