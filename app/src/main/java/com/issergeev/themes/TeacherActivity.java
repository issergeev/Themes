package com.issergeev.themes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.sql.SQLException;

public class TeacherActivity extends AppCompatActivity {
    private SqlDataWorker worker;

    private ListView students;

    private Student2Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        worker = new SqlDataWorker(this);

        students = findViewById(R.id.studentsList);

        try {
            worker.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter = new Student2Adapter(this, worker.getStudentList(), worker.getThemesListString());
        students.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        worker.close();
        super.onDestroy();
    }
}