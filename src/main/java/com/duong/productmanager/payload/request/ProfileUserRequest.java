package com.duong.productmanager.payload.request;

import com.duong.productmanager.entity.AppUser;
import com.duong.productmanager.validator.phone.ContactNumberConstraint;

public class ProfileUserRequest {
    private AppUser appUser;
    private String address;
    @ContactNumberConstraint
    private String phone;
    private String dateOfBirth;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
