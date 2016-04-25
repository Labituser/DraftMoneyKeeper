package ua.com.globalit.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayAdapter<String> spinnerArrayAdapter;
    ArrayAdapter<CategoryModel> categoryArrayAdapter;
    ArrayList<CategoryModel> categoryList;
    ListView categoryListView;
    Spinner mainCategorySpinner;
    EditText valueEditText;

    ArrayList<String> categoryArray = new ArrayList<String>() {{
        add("Common");
        add("Food");
        add("Home");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainCategorySpinner = (Spinner) findViewById(R.id.main_category_spinner);

        categoryList = new ArrayList<CategoryModel>();
        for (String item : categoryArray) {
            categoryList.add(new CategoryModel(item, 0));
        }

        categoryArrayAdapter = new InteractiveArrayAdapter(this, categoryList);
        categoryListView = (ListView) findViewById(R.id.categoryListView);
        categoryListView.setAdapter(categoryArrayAdapter);

        spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryArray);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mainCategorySpinner.setAdapter(spinnerArrayAdapter);

        valueEditText = (EditText) findViewById(R.id.editText);
        Button settingsButton = (Button) findViewById(R.id.category_settings);
        settingsButton.setOnClickListener(this);
        Button addButton = (Button) findViewById(R.id.add_value_button);
        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.category_settings:
                Intent intent = new Intent(this, EditCategoryActivity.class);

                ArrayList<String> extraList = new ArrayList<String>();
                for (int i = 0; i < mainCategorySpinner.getCount(); i++) {
                    extraList.add(mainCategorySpinner.getItemAtPosition(i).toString());
                }
                intent.putExtra("categoryArray", extraList);
                startActivityForResult(intent, 1);
                break;

            case R.id.add_value_button:
                String selectedCategory = mainCategorySpinner.getSelectedItem().toString();


                for (CategoryModel categoryModel : categoryList) {

                        if (categoryModel.Name == selectedCategory) {
                            categoryModel.Value += Integer.valueOf(valueEditText.getText().toString());
                        }
                    }
                    categoryArrayAdapter = new InteractiveArrayAdapter(this, categoryList);
                    categoryListView = (ListView) findViewById(R.id.categoryListView);
                    categoryListView.setAdapter(categoryArrayAdapter);

                    break;
                }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        ArrayList<String> resultCategories = data.getStringArrayListExtra("resultCategoryArray");
        spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, resultCategories);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mainCategorySpinner.setAdapter(spinnerArrayAdapter);

        categoryList = new ArrayList<CategoryModel>();
        for (String item : resultCategories) {
            categoryList.add(new CategoryModel(item, 0));
        }
        categoryArrayAdapter = new InteractiveArrayAdapter(this, categoryList);
        categoryListView = (ListView) findViewById(R.id.categoryListView);
        categoryListView.setAdapter(categoryArrayAdapter);
    }


}
