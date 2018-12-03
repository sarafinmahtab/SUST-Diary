package edu.sust.diary.models.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class SchoolsOfDepartments extends RealmObject {

    @PrimaryKey
    private String status;
    private RealmList<Schools> schools = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RealmList<Schools> getSchools() {
        return schools;
    }

    public void setSchools(RealmList<Schools> schools) {
        this.schools = schools;
    }
}
