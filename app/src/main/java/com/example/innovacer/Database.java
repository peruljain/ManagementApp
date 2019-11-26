package com.example.innovacer;

public class Database {

    private String visitor_name;
    private String visitor_email;
    private String visitor_phone;
    private String host_name;
    private String host_email;
    private String host_phone;


    public Database(String visitor_name, String visitor_email, String visitor_phone, String host_name, String host_email, String host_phone) {
        this.visitor_name = visitor_name;
        this.visitor_email = visitor_email;
        this.visitor_phone = visitor_phone;
        this.host_name = host_name;
        this.host_email = host_email;
        this.host_phone = host_phone;
    }
}
