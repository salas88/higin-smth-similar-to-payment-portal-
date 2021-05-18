package com.example.please.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recipient {

    private String first_name;
    private String last_name;

    public Recipient(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
