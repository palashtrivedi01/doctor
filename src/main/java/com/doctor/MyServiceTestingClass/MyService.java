package com.doctor.MyServiceTestingClass;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public String greetUser(String name) {
        return "Hello, " + name;
    }
}
