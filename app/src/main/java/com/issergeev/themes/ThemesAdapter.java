package com.issergeev.themes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ThemesAdapter extends ArrayAdapter<Theme> {
    private LayoutInflater layoutInflater;

    private List<Theme> themesList;
    private List<Student> studentList;
    Context context;

    public ThemesAdapter(@NonNull Context context, List<Theme> themes, List<Student> studentList) {
        super(context, 0, themes);

        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        themesList = themes;
        this.studentList = studentList;
    }

    @Nullable
    @Override
    public Theme getItem(int position) {
        return themesList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            View view = layoutInflater.inflate(R.layout.theme_card, parent, false);
            viewHolder = ViewHolder.create((LinearLayout) view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Theme item = getItem(position);

        viewHolder.themeName.setText(item.getThemeName());

        //REDO
        viewHolder.occupiedText.setText(studentList.get(position).getThemeID());

        return viewHolder.parentLayout;
    }

    private static class ViewHolder {
        private final LinearLayout parentLayout;
        private final TextView themeName;
        private final TextView occupiedText;

        private ViewHolder(LinearLayout parentLayout, TextView themeName, TextView occupiedText) {
            this.parentLayout = parentLayout;
            this.themeName = themeName;
            this.occupiedText = occupiedText;
        }

        private static ViewHolder create(LinearLayout parentLayout) {
            TextView themeName = parentLayout.findViewById(R.id.themeName);
            TextView occupied = parentLayout.findViewById(R.id.occupiedText);

            return new ViewHolder(parentLayout, themeName, occupied);
        }
    }
}
