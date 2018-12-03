package edu.sust.diary.models.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Departments extends RealmObject {

    private String name;
    private String code;
    private RealmList<Teachers> teachers = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RealmList<Teachers> getTeachers() {
        return teachers;
    }

    public void setTeachers(RealmList<Teachers> teachers) {
        this.teachers = teachers;
    }

}
