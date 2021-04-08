package com.example.bin.commonmodule;

import lombok.Data;

@Data
public class UserInfo {
    private int id;
    private String name;
    private int age;
    private String city;

    public UserInfo() {

    }

    public UserInfo(int usrId, String usrName, int usrAge, String usrCity) {
        id = usrId;
        name = usrName;
        age = usrAge;
        city = usrCity;
    }
}