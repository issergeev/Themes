package com.issergeev.themes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Student2Adapter extends ArrayAdapter<User> {
    private LayoutInflater layoutInflater;

    private List<User> userList;
    private List<String> themes;
    Context context;

    public Student2Adapter(@NonNull Context context, List<User> users, List<String> themes) {
        super(context, 0, users);

        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        userList = users;
        this.themes = themes;
    }

    @Nullable
    @Override
    public User getItem(int position) {
        return userList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            View view = layoutInflater.inflate(R.layout.theme_card_teacher, parent, false);
            viewHolder = ViewHolder.create((LinearLayout) view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.studentName.setText(position+1 + ". " + userList.get(position).getFullName());
        viewHolder.theme.setText(userList.get(position).getThemeID());

        return viewHolder.parentLayout;
    }

    private static class ViewHolder {
        private final LinearLayout parentLayout;
        private final TextView studentName;
        private final TextView theme;

        private ViewHolder(LinearLayout parentLayout, TextView studentName, TextView theme) {
            this.parentLayout = parentLayout;
            this.studentName = studentName;
            this.theme = theme;
        }

        private static ViewHolder create(LinearLayout parentLayout) {
            TextView studentName = parentLayout.findViewById(R.id.student_name);
            TextView theme = parentLayout.findViewById(R.id.theme);

            return new ViewHolder(parentLayout, studentName, theme);
        }
    }
}