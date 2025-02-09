package com.droveda.course.genericsstudy;

public class HighPerson extends Person {

    private Integer height;

    public HighPerson(String name) {
        super(name);
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "HighPerson{" +
                "height=" + height +
                '}';
    }
}
