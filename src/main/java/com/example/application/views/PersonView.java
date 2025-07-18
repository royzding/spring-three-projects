package com.example.application.views;

public interface PersonView {
    void setFullName(String fullName);
    String getFirstName();
    String getLastName();
    void addShowFullNameListener(Runnable listener);
}
