package edu.sust.diary.models.realm;

import java.util.List;

import edu.sust.diary.expandablelist.ParentListItem;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Schools extends RealmObject implements ParentListItem {

    private String name;
    private RealmList<Departments> departments = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Departments> getDepartments() {
        return departments;
    }

    public void setDepartments(RealmList<Departments> departments) {
        this.departments = departments;
    }

    @Override
    public List<?> getChildItemList() {
        return departments;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return true;
    }
}
