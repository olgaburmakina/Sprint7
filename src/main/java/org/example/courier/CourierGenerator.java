package org.example.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {

    public Courier generic(){
        return new Courier("Kakashi", "1a2b3c", "Hatake");
    }
    public Courier random(){
        return new Courier(RandomStringUtils.randomAlphanumeric(10),"1a2b3c", "Hatake");
    }
    public Courier loginData(){
        return new Courier("Kakashi","1a2b3c");
    }
}
