package com.example.level1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class CounterFragment extends Fragment {

    private int fragmentCounter = 0;
    private TextView fragmentClicksTextView;

    private static final String CLICK_COUNT_PREFIX = "Fragment Clicks: ";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_counter, container, false);

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        toolbar.setNavigationOnClickListener(v -> goBackToActivity());

        fragmentClicksTextView = rootView.findViewById(R.id.fragmentClicksTextView);

        Button increaseCounterButton = rootView.findViewById(R.id.increaseCounterButton);
        increaseCounterButton.setOnClickListener(v -> increaseCounter());

        if (savedInstanceState != null) {
            fragmentCounter = savedInstanceState.getInt("counter", 0);
            updateFragmentCounter(fragmentCounter);
        }

        return rootView;
    }

    private void goBackToActivity() {
        MainActivity activity = (MainActivity) requireActivity();
        activity.updateActivityCounter(fragmentCounter);

        requireActivity().getSupportFragmentManager().popBackStack();
    }

    private void increaseCounter() {
        fragmentCounter++;
        updateFragmentCounter(fragmentCounter);
    }

    private void updateFragmentCounter(int count) {
        fragmentClicksTextView.setText(CLICK_COUNT_PREFIX + count);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter", fragmentCounter);
    }

}
