package com.test.prop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.prop.entity.Person;

@RestController
@RequestMapping("/prop")
public class PropController {
    @Autowired
    private Person person;

    @GetMapping("/addresses")
    public List<String> listAddress() {
        return person.getAddress();
    }
}
