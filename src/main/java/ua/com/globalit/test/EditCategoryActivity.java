package ua.com.globalit.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EditCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayAdapter<String> spinnerArrayAdapter;
    Spinner categorySpinner;
    EditText categoryEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        Intent intent = getIntent();
        ArrayList<String> extraList = intent.getStringArrayListExtra("categoryArray");

        categorySpinner = (Spinner)findViewById(R.id.category_spinner);
        categoryEditText = (EditText)findViewById(R.id.categoryEditText);

        //selected item will look like a spinner set from XML
        spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, extraList);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerArrayAdapter);

        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);
        Button remove = (Button) findViewById(R.id.remove);
        remove.setOnClickListener(this);

        Button applyButton = (Button) findViewById(R.id.apply);
        applyButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String textForAdding = categoryEditText.getText().toString();
        String selectedCategory = categorySpinner.getSelectedItem().toString();
        int position = spinnerArrayAdapter.getPosition(selectedCategory);

        switch (v.getId()) {

            case R.id.add:
                spinnerArrayAdapter.add(textForAdding);
                spinnerArrayAdapter.notifyDataSetChanged();
                categorySpinner.setSelection(spinnerArrayAdapter.getPosition(textForAdding));
                Toast.makeText(getBaseContext(), "Category " + textForAdding + " created successfully!", Toast.LENGTH_LONG).show();
                break;

            case R.id.remove:

                if(position >= 0){
                    spinnerArrayAdapter.remove(selectedCategory);
                    spinnerArrayAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getBaseContext(), selectedCategory + " not in Spinner", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.apply:

                Intent intent = new Intent();

                ArrayList<String> extraList = new ArrayList<String>();
                for(int i=0 ; i<categorySpinner.getCount() ; i++){
                    extraList.add(categorySpinner.getItemAtPosition(i).toString());
                }
                intent.putExtra("resultCategoryArray", extraList);
                setResult(RESULT_OK, intent);
                finish();
        }

    }
}
