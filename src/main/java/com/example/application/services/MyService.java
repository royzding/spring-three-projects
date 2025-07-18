package com.example.application.services;

import com.example.application.model.Card;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyService {
    public List<Card> fetchData() {
        // Fetch data from database or other source
        return new ArrayList<>(Arrays.asList(new Card(1,"n1","an1"), new Card(2, "n2", "an2")));
    }

    public void processInput(String input) {
        // Perform business logic
    }
}