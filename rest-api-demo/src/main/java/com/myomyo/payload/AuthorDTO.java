package com.myomyo.payload;


public class AuthorDTO {

    private long id;
    private String name;
    private String mail;
    private String phone;
    private String biography;

    public AuthorDTO(long id, String name, String mail, String phone, String biography) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.biography = biography;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
