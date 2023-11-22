package com.example.cashslash;

public class Users {

    public Users() {
        // Default constructor required for calls to DataSnapshot.getValue(Users.class)
    }
    String profilepic,mail,password,userId,lastMessage,status,firstName,lastName;

    public Users(String id, String mail, String pass, String status, String firstName, String lastName, String imageuri){
        this.userId = id;
        this.mail = mail;
        this.password = pass;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilepic = imageuri;
//        this.gpname = groupname;
    }


    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

//    public String getGpname() {
//        return gpname;
//    }
//
//    public void setGpname(String gpname) {
//        this.gpname = gpname;
//    }
}
