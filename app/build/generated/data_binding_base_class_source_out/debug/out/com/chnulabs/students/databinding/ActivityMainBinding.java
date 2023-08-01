// Generated by view binder compiler. Do not edit!
package com.chnulabs.students.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chnulabs.students.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final FrameLayout fragmentContainer;

  @NonNull
  public final RecyclerView recyclerViewUsers;

  private ActivityMainBinding(@NonNull LinearLayout rootView,
      @NonNull FrameLayout fragmentContainer, @NonNull RecyclerView recyclerViewUsers) {
    this.rootView = rootView;
    this.fragmentContainer = fragmentContainer;
    this.recyclerViewUsers = recyclerViewUsers;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.fragmentContainer;
      FrameLayout fragmentContainer = ViewBindings.findChildViewById(rootView, id);
      if (fragmentContainer == null) {
        break missingId;
      }

      id = R.id.recyclerViewUsers;
      RecyclerView recyclerViewUsers = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewUsers == null) {
        break missingId;
      }

      return new ActivityMainBinding((LinearLayout) rootView, fragmentContainer, recyclerViewUsers);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
