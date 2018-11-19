package edu.sust.sustdiary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.sust.sustdiary.R;
import edu.sust.sustdiary.expandablelist.ExpandableRecyclerAdapter;
import edu.sust.sustdiary.expandablelist.ParentListItem;
import edu.sust.sustdiary.models.Department;
import edu.sust.sustdiary.models.School;
import edu.sust.sustdiary.viewholders.DepartmentViewHolder;
import edu.sust.sustdiary.viewholders.SchoolViewHolder;

public class SchoolAdapter extends
        ExpandableRecyclerAdapter<SchoolViewHolder, DepartmentViewHolder> {

    private Context context;

    public SchoolAdapter(Context context, List<? extends ParentListItem> parentItemList) {
        super(parentItemList);

        this.context = context;
    }

    @Override
    public SchoolViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View schoolView = LayoutInflater.from(context).inflate(
                R.layout.layout_single_school, parentViewGroup, false);
        return new SchoolViewHolder(context, schoolView);
    }

    @Override
    public DepartmentViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View departmentView = LayoutInflater.from(context).inflate(
                R.layout.layout_single_department, childViewGroup, false);
        return new DepartmentViewHolder(context, departmentView);
    }

    @Override
    public void onBindParentViewHolder(SchoolViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        School school = (School) parentListItem;
        parentViewHolder.bind(school);
    }

    @Override
    public void onBindChildViewHolder(DepartmentViewHolder childViewHolder, int position, Object childListItem) {
        Department department = (Department) childListItem;
        childViewHolder.bind(department);
    }
}
