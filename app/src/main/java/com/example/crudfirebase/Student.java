package com.example.crudfirebase;

public class Student {
    private String name;
    private String chuyennganh;
    private String hocky;
    private String id;
    private String key;

    public Student(String name, String chuyennganh, String hocky, String id) {
        this.name = name;
        this.chuyennganh = chuyennganh;
        this.hocky = hocky;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChuyennganh() {
        return chuyennganh;
    }

    public void setChuyennganh(String chuyennganh) {
        this.chuyennganh = chuyennganh;
    }

    public String getHocky() {
        return hocky;
    }

    public void setHocky(String hocky) {
        this.hocky = hocky;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
