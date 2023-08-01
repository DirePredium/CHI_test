package com.chnulabs.students;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chnulabs.students.utils.DateUtil;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;
    private UsersDatabaseHelper usersDatabaseHelper;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public UserAdapter(List<User> userList, UsersDatabaseHelper userDatabaseHelper) {
        this.userList = userList;
        this.usersDatabaseHelper = userDatabaseHelper;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.textViewName.setText(user.getName());
        int age = DateUtil.calculateAge(user.getBirthday());
        holder.textViewAge.setText(String.valueOf(age));
        holder.checkBoxIsUser.setChecked(user.isStudent());
        holder.itemView.setOnClickListener(view -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(user);
            }
        });

        holder.checkBoxIsUser.setOnCheckedChangeListener((buttonView, isChecked) -> {
            User updatedStudent = userList.get(holder.getAdapterPosition());
            updatedStudent.setIsStudent(isChecked);
            usersDatabaseHelper.updateUser(updatedStudent);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewAge;
        CheckBox checkBoxIsUser;

        public UserViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            checkBoxIsUser = itemView.findViewById(R.id.checkBoxIsStudent);
        }
    }
}
