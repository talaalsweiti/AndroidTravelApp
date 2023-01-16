package com.example.travelapp;

public class User {

    private String Email;
    private String FirstName;
    private String LastName;
    private String Password;
    private String Destination;

    public User() {
    }

    public User(String Email, String FirstName, String LastName, String Password, String Destination) {
        this.Email = Email;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Password = Password;
        this.Destination = Destination;
    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }
}
