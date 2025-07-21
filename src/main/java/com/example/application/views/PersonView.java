package com.example.application.views;

import java.util.function.Supplier;

public interface PersonView {
    void setFullName(String fullName);
    String getFirstName();
    String getLastName();
    void addShowFullNameListener(Runnable listener);
    void addShowFullNameSupplier(Supplier<String> supplier);
}
