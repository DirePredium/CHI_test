package com.chnulabs.students;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UsersDatabaseHelper usersDatabaseHelper =
            UsersDatabaseHelper.getInstance(this);
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentList = usersDatabaseHelper.getAllUsers();

        userAdapter = new UserAdapter(studentList, usersDatabaseHelper);
        userAdapter.setOnItemClickListener((user) -> {
            Intent intent = new Intent(MainActivity.this, UserDetailsActivity.class);
            intent.putExtra(UserDetailsActivity.USER_ID, user.getId());
            startActivity(intent);
        });
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_user) {
            Intent intent = new Intent(this, AddUserActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRecyclerView();
    }

    private void updateRecyclerView() {
        studentList.clear();
        studentList.addAll(usersDatabaseHelper.getAllUsers());
        userAdapter.notifyDataSetChanged();
    }

}