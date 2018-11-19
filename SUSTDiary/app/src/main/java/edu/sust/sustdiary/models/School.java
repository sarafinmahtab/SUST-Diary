package edu.sust.sustdiary.models;

import java.util.List;

import edu.sust.sustdiary.expandablelist.ParentListItem;

public class School implements ParentListItem {

    private String schoolName;
    private List<Department> departmentList;

    public School(String schoolName, List<Department> departmentList) {
        this.schoolName = schoolName;
        this.departmentList = departmentList;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public List<?> getChildItemList() {
        return departmentList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
