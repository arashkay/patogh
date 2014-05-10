package com.tectual.patogh.patogh.web;

/**
 * Created by arash on 27/04/2014.
 */
public class JsonCard {

    public int id;
    public String name;
    public String address;
    public String card_description;
    public String card_small400;
    public String card_on_small200;
    public String card_off_small200;
    public int points;

    public String getLogo(){
        return "http://api.patoghapp.com"+card_small400;
    }
    public String getPunch(){
        return "http://api.patoghapp.com"+card_on_small200;
    }
    public String getOffPunch(){
        return "http://api.patoghapp.com"+card_off_small200;
    }

}
