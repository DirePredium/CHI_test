package com.chnulabs.students;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddUserActivity extends AppCompatActivity {

    private UsersDatabaseHelper usersDatabaseHelper =
            UsersDatabaseHelper.getInstance(this);
    private EditText editTextName;
    private CheckBox checkBoxIsStudent;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextName = findViewById(R.id.editTextName);
        checkBoxIsStudent = findViewById(R.id.checkBoxIsStudent);
        datePicker = findViewById(R.id.datePicker);

        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    private void addUser() {
        String name = editTextName.getText().toString();
        boolean isStudent = checkBoxIsStudent.isChecked();

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();

        String dayString = String.format("%02d", day);
        String monthString = String.format("%02d", month);

        String birthday = year + "-" + monthString + "-" + dayString;

        if (!isNameValid(name)) {
            Toast.makeText(getApplicationContext(), "Invalid name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isDateValid(day, month, year)) {
            Toast.makeText(getApplicationContext(), "Invalid date", Toast.LENGTH_SHORT).show();
            return;
        }
        User user = new User(name, birthday, isStudent);
        usersDatabaseHelper.addUser(user);
        setResult(Activity.RESULT_OK);
        finish();
        Toast.makeText(getApplicationContext(), "User added successfully", Toast.LENGTH_SHORT).show();
    }

    private boolean isDateValid(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        long selectedTimeInMillis = calendar.getTimeInMillis();
        long currentTimeInMillis = System.currentTimeMillis();
        return selectedTimeInMillis <= currentTimeInMillis;
    }

    private boolean isNameValid(String name) {
        return !TextUtils.isEmpty(name);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
