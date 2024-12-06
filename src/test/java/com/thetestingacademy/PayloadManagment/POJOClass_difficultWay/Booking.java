package com.thetestingacademy.PayloadManagment.POJOClass_difficultWay;

public class Booking {

    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private String additionalneeds;
    // another class
    private BookingDate bookingdate;

    public Booking()
    {

    }

    public String getFirstname() {
        return firstname;
    }

    public BookingDate getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(BookingDate bookingdate) {
        this.bookingdate = bookingdate;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public Boolean getDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }
}
