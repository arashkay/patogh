package com.tectual.patogh.patogh.web;

/**
 * Created by arash on 6/05/2014.
 */
public class JsonCoupon {

    public int id;
    public String title;
    public String description;
    public String small400;

    public String getImage(){
        return "http://api.patoghapp.com"+small400;
    }

}
