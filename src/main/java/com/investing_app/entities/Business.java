package com.investing_app.entities;

import java.util.Objects;

public class Business {

    private int businessId;
    private String firstName;
    private String lastName;
    private String businessName;
    private String username;
    private String password;
    private String role;

    public Business(){}

    public Business(int businessId, String firstName, String lastName, String businessName, String username,
                    String password, String role) {
        this.businessId = businessId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.businessName = businessName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Business{" +
                "businessId=" + businessId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", businessName='" + businessName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Business business = (Business) o;
        return businessId == business.businessId && Objects.equals(firstName, business.firstName) && Objects.equals(lastName, business.lastName) && Objects.equals(businessName, business.businessName) && Objects.equals(username, business.username) && Objects.equals(password, business.password) && Objects.equals(role, business.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessId, firstName, lastName, businessName, username, password, role);
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
