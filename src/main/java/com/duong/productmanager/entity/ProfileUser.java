package com.duong.productmanager.entity;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "profile_users")
public class ProfileUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private AppUser appUser;
    private String address;
    private String phone;
    private String dateOfBirth;

    public ProfileUser() {
    }

    public ProfileUser(Long id, AppUser appUser, String address, String phone, String dateOfBirth) {
        this.id = id;
        this.appUser = appUser;
        this.address = address;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public ProfileUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
