package com.sea.GrandBlue.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private String name, type, description, place, food, imgsrc;
    public Long getID() {
        return ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public String getFood() {
        return food;
    }
    public void setFood(String food) {
        this.food = food;
    }
    public String getImgsrc() {
        return imgsrc;
    }
    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }
    public Article() {
    }
    public Article(String name, String type, String description, String place, String food, String imgsrc) { /*Animals*/
        this.name = name;
        this.type = type;
        this.description = description;
        this.place = place;
        this.food = food;
        this.imgsrc = imgsrc;
    }
}
