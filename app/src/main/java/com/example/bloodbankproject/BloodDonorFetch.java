package com.example.bloodbankproject;

public class BloodDonorFetch {
    String D_id, Name, Contact, Address, City, Blood;

    public BloodDonorFetch(String D_id, String Name, String Contact, String Address, String City, String Blood) {
        this.D_id = D_id;
        this.Name = Name;
        this.Contact = Contact;
        this.Address = Address;
        this.City = City;
        this.Blood = Blood;

    }

    public String getD_id() {
        return D_id;
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


}


