package com.issergeev.themes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class AddThemeActivity extends AppCompatActivity {
    private SqlDataWorker worker;

    private EditText themeName;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_theme);

        themeName = findViewById(R.id.theme_name);
        save = findViewById(R.id.save);

        worker = new SqlDataWorker(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    worker.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), worker.insertTheme(themeName.getText().toString()) != 0
                        ? "Тема добавлена" : "Неизвестная ошибка", Toast.LENGTH_SHORT).show();
                worker.close();
                finish();
            }
        });
    }
}