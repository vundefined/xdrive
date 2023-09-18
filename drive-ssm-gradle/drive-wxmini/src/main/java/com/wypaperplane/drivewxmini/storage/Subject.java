package com.wypaperplane.drivewxmini.storage;

public class Subject {

    private static final Subject subject = new Subject();

    private Integer id;

    private Subject() {}

    public static Integer getPrincipal() {
        return subject.getId();
    }

    public static Subject setPrincipal (Integer id) {
        subject.setId(id);
        return subject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
