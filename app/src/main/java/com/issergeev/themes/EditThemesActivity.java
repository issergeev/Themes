package com.issergeev.themes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        adapter = new ThemesAdapter(this, worker.getThemesList());

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                final long id = adapter.getItem(i).getId();
                new AlertDialog.Builder(EditThemesActivity.this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (worker.deleteTheme(id) != 0) {
                                    Toast.makeText(EditThemesActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                    adapter.remove(adapter.getItem(i));
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(EditThemesActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
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

        adapter = null;
        adapter = new ThemesAdapter(EditThemesActivity.this, worker.getThemesList());
        list.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        worker.close();

        super.onDestroy();
    }
}