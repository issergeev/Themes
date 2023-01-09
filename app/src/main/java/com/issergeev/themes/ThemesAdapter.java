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
    Context context;

    public ThemesAdapter(@NonNull Context context, List<Theme> themes) {
        super(context, 0, themes);

        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        themesList = themes;
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

        viewHolder.themeName.setText(position+1 + ". " + themesList.get(position).getThemeName());

        return viewHolder.parentLayout;
    }

    private static class ViewHolder {
        private final LinearLayout parentLayout;
        private final TextView themeName;

        private ViewHolder(LinearLayout parentLayout, TextView themeName) {
            this.parentLayout = parentLayout;
            this.themeName = themeName;
        }

        private static ViewHolder create(LinearLayout parentLayout) {
            TextView themeName = parentLayout.findViewById(R.id.themeName);

            return new ViewHolder(parentLayout, themeName);
        }
    }
}
