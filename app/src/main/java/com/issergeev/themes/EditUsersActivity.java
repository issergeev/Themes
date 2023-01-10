package com.issergeev.themes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLException;

public class EditUsersActivity extends AppCompatActivity implements View.OnClickListener {
    private SqlDataWorker worker;

    private ListView list;
    private TextView textView;
    private FloatingActionButton add;

    private StudentAdapter adapter;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_users);

        worker = new SqlDataWorker(this);

        list = findViewById(R.id.users_list);
        textView = findViewById(R.id.no_users_text);
        add = findViewById(R.id.add_user);

        add.setOnClickListener(this);

        try {
            worker.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        adapter = new StudentAdapter(this, worker.getUserList());
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("login", adapter.getItem(i).getLogin());
                intent.putExtra("name", adapter.getItem(i).getName());
                intent.putExtra("lastname", adapter.getItem(i).getLastName());

                startActivity(intent);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                new AlertDialog.Builder(EditUsersActivity.this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (worker.deleteUser(adapter.getItem(i).getLogin()) != 0) {
                                    Toast.makeText(EditUsersActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                    adapter.remove(adapter.getItem(i));
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(EditUsersActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (adapter.isEmpty()) {
            list.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        adapter = null;
        adapter = new StudentAdapter(EditUsersActivity.this, worker.getUserList());
        list.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_user:
                startActivity(new Intent(this, NewUserActivity.class));
        }
    }
}