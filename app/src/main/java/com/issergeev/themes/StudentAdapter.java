package com.issergeev.themes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StudentAdapter extends ArrayAdapter<Student> {
    private LayoutInflater layoutInflater;

    private List<Student> studentList;
    Context context;

    public StudentAdapter(@NonNull Context context, List<Student> students) {
        super(context, 0, students);

        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        studentList = students;
    }

    @Nullable
    @Override
    public Student getItem(int position) {
        return studentList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            View view = layoutInflater.inflate(R.layout.student_card, parent, false);
            viewHolder = ViewHolder.create((LinearLayout) view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.studentName.setText(position+1 + ". " + studentList.get(position).getFullName());

        return viewHolder.parentLayout;
    }

    private static class ViewHolder {
        private final LinearLayout parentLayout;
        private final TextView studentName;

        private ViewHolder(LinearLayout parentLayout, TextView studentName) {
            this.parentLayout = parentLayout;
            this.studentName = studentName;
        }

        private static ViewHolder create(LinearLayout parentLayout) {
            TextView studentName = parentLayout.findViewById(R.id.student_name);

            return new ViewHolder(parentLayout, studentName);
        }
    }
}