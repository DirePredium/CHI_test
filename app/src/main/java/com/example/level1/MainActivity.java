package com.example.level1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    private CounterViewModel counterViewModel;
    private TextView activityClicksTextView;

    private static final String CLICK_COUNT_PREFIX = "Was clicked in fragment: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterViewModel = new ViewModelProvider(this).get(CounterViewModel.class);

        activityClicksTextView = findViewById(R.id.activityClicksTextView);
        updateActivityCounter(counterViewModel.getActivityCounter());

        Button openFragmentButton = findViewById(R.id.openFragmentButton);
        openFragmentButton.setOnClickListener(v -> openFragment());
    }

    private void openFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        CounterFragment counterFragment = new CounterFragment();
        fragmentTransaction.replace(R.id.fragmentContainer, counterFragment);

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    public void updateActivityCounter(int count) {
        counterViewModel.setActivityCounter(count);
        activityClicksTextView.setText(CLICK_COUNT_PREFIX + count);
    }
}

