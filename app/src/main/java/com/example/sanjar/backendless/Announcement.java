package com.example.sanjar.backendless;

/**
 * Created by Sanjar on 01.10.2016.
 */
public class Announcement {

    private String message;
    private String authorEmail;
    private  String time;


    public Announcement()
    {}


    public Announcement( String message, String authorEmail )
    {
        this.message = message;
        this.authorEmail = authorEmail;
        //this.time = time;
    }

    public String getMessage(){
        return message;
    }
    public void setMessage(){
        this.message = message;
    }

    public String getAuthorEmail()
    {
        return authorEmail;
    }

    public void setAuthorEmail( String authorEmail )
    {
        this.authorEmail = authorEmail;
    }

    public String getTime(){
        this.time = String.valueOf(System.currentTimeMillis());
        return time;
    }


}
