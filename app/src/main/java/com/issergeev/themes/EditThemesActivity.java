package com.issergeev.themes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLException;

public class EditThemesActivity extends AppCompatActivity implements View.OnClickListener {
    private SqlDataWorker worker;

    private TextView noThemes;
    private FloatingActionButton add;
    private ListView list;

    private ThemesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_themes);

        noThemes = findViewById(R.id.no_themes);
        add = findViewById(R.id.fab);
        list = findViewById(R.id.themes_list);

        add.setOnClickListener(this);

        worker = new SqlDataWorker(this);
        try {
            worker.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter = new ThemesAdapter(this, worker.getThemesList(), worker.getStudentList());
        worker.close();

        list.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (adapter.isEmpty()) {
            list.setVisibility(View.GONE);
            noThemes.setVisibility(View.VISIBLE);
        } else {
            list.setVisibility(View.VISIBLE);
            noThemes.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                startActivity(new Intent(this, AddThemeActivity.class));
                break;
            default:

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        adapter.notifyDataSetChanged();
    }
}