package com.chnulabs.students;

public class User {
    private int id;
    private String name;
    private String birthday;
    private boolean isStudent;

    public User() {
    }

    public User(int id, String name, String birthday, boolean isStudent) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.isStudent = isStudent;
    }

    public User(String name, String birthday, boolean isStudent) {
        this.name = name;
        this.birthday = birthday;
        this.isStudent = isStudent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", isStudent=" + isStudent +
                '}';
    }
}
