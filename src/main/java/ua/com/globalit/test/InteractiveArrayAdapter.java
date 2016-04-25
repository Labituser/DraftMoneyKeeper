package ua.com.globalit.test;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

public class InteractiveArrayAdapter extends ArrayAdapter<CategoryModel> {

    private final List<CategoryModel> list;
    private final Activity context;

    public InteractiveArrayAdapter(Activity context, List<CategoryModel> list) {
        super(context, R.layout.rowlayout, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected TextView name;
        protected TextView value;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.rowlayout, null);

            TextView categoryName = (TextView) view.findViewById(R.id.list_item_category_name);
            TextView categoryValue = (TextView) view.findViewById(R.id.list_item_category_value);

        categoryName.setText(list.get(position).Name);
        categoryValue.setText((String.valueOf(list.get(position).Value)));

        return view;
    }
}

