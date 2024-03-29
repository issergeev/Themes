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

public class StudentAdapter extends ArrayAdapter<User> {
    private LayoutInflater layoutInflater;

    private List<User> userList;
    Context context;

    public StudentAdapter(@NonNull Context context, List<User> users) {
        super(context, 0, users);

        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        userList = users;
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
            View view = layoutInflater.inflate(R.layout.student_card, parent, false);
            viewHolder = ViewHolder.create((LinearLayout) view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.studentName.setText(position+1 + ". " + userList.get(position).getFullName());

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