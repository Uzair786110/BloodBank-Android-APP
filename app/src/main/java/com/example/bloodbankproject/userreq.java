package com.example.bloodbankproject;

public class userreq {
    String R_id, Name, Contact, Address, City, Blood, Date, Bags, Userid;

    public userreq(String R_id, String Name, String Contact, String Address, String City, String Blood, String Date, String Bags, String Userid) {
        this.R_id = R_id;
        this.Name = Name;
        this.Contact = Contact;
        this.Address = Address;
        this.City = City;
        this.Blood = Blood;
        this.Date = Date;
        this.Bags = Bags;
        this.Userid = Userid;
    }

    public String getR_id() {
        return R_id;
    }

    public String getName() {
        return Name;
    }

    public String getContact() {
        return Contact;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getBlood() {
        return Blood;
    }

    public String getDate() {
        return Date;
    }

    public String getBags() {
        return Bags;
    }

    public String getUserid() {
        return Userid;
    }


}
