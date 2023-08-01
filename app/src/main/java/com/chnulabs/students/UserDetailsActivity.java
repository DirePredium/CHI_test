package com.chnulabs.students;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chnulabs.students.utils.DateUtil;

public class UserDetailsActivity extends AppCompatActivity {

    public static final String USER_ID = "user_id";

    private TextView textViewName;
    private TextView textViewBirthday;
    private CheckBox checkBoxIsStudent;
    private UsersDatabaseHelper usersDatabaseHelper =
            UsersDatabaseHelper.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewName = findViewById(R.id.textViewName);
        textViewBirthday = findViewById(R.id.textViewBirthday);
        checkBoxIsStudent = findViewById(R.id.checkBoxIsStudent);

        User user = usersDatabaseHelper.getUser(getIntent().getIntExtra(USER_ID, 0));
        if (user != null) {
            textViewName.setText("Name: " + user.getName());
            textViewBirthday.setText("Birthday: " + DateUtil.calculateAge(user.getBirthday()));
            checkBoxIsStudent.setChecked(user.isStudent());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}