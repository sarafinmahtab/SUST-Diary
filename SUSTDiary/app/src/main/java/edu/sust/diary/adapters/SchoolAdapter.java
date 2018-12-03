package edu.sust.diary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.sust.diary.R;
import edu.sust.diary.expandablelist.ExpandableRecyclerAdapter;
import edu.sust.diary.expandablelist.ParentListItem;
import edu.sust.diary.models.realm.Departments;
import edu.sust.diary.models.realm.Schools;
import edu.sust.diary.viewholders.DepartmentViewHolder;
import edu.sust.diary.viewholders.SchoolViewHolder;
import io.realm.RealmList;

public class SchoolAdapter extends
        ExpandableRecyclerAdapter<SchoolViewHolder, DepartmentViewHolder> {

    private Context context;

    public SchoolAdapter(Context context, RealmList<? extends ParentListItem> parentItemList) {
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
        Schools schools = (Schools) parentListItem;
        parentViewHolder.bind(schools);
    }

    @Override
    public void onBindChildViewHolder(DepartmentViewHolder childViewHolder, int position, Object childListItem) {
        Departments departments = (Departments) childListItem;
        childViewHolder.bind(departments);
    }
}
