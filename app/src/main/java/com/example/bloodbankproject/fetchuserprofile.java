package com.example.bloodbankproject;

public class fetchuserprofile {
    String name;
    String email;
    String contact;
    String bloodgroup;
    String id;
    String don;
    String Address;
    String City;

    public fetchuserprofile(String name, String email, String contact, String bloodgroup, String id, String don, String Address, String City) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.bloodgroup = bloodgroup;
        this.id = id;
        this.don = don;
        this.Address = Address;
        this.City = City;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    public String getContact() {
        return contact;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public String getId() {
        return id;
    }

    public String getDon() {
        return don;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }
}
