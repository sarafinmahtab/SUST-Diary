package edu.sust.sustdiary.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.sust.sustdiary.models.Department;
import edu.sust.sustdiary.R;
import edu.sust.sustdiary.models.School;
import edu.sust.sustdiary.adapters.SchoolAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepartmentFragment extends Fragment {

    private SchoolAdapter schoolAdapter;

    public DepartmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_department, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        initViewAndObjects(view);
    }

    private void initViewAndObjects(View view) {
        context = view.getContext();

        final List<School> schoolList = getSchoolList();

        schoolAdapter = new SchoolAdapter(context, schoolList);

        departmentRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        departmentRecyclerView.addItemDecoration(
                new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        departmentRecyclerView.setHasFixedSize(true);

        departmentRecyclerView.setAdapter(schoolAdapter);
    }

    private List<School> getSchoolList() {

        Department dept1 = new Department();
        dept1.setDepartmentID(1);
        dept1.setDepartmentName("Computer Science & Engineering");

        Department dept2 = new Department();
        dept2.setDepartmentID(2);
        dept2.setDepartmentName("Chemical Engineering & Polymer Science");

        Department dept3 = new Department();
        dept3.setDepartmentID(3);
        dept3.setDepartmentName("Architecture");

        School school1 = new School("School of Applied Sciences & Technology",
                Arrays.asList(dept1, dept2, dept3));


        Department dept4 = new Department();
        dept4.setDepartmentID(4);
        dept4.setDepartmentName("Civil & Environmental Engineering");

        Department dept5 = new Department();
        dept5.setDepartmentID(5);
        dept5.setDepartmentName("Genetic Engineering & Biotechnology");

        Department dept6 = new Department();
        dept6.setDepartmentID(6);
        dept6.setDepartmentName("Biochemistry and Molecular Biology");

        Department dept7 = new Department();
        dept7.setDepartmentID(7);
        dept7.setDepartmentName("Chemistry");

        School school2 = new School("School of Life Sciences", Arrays.asList(dept4, dept5, dept6, dept7));

        Department dept8 = new Department();
        dept8.setDepartmentID(8);
        dept8.setDepartmentName("Business Administration");

        School school3 = new School("School of Management & Business Administration", Collections.singletonList(dept8));

        return Arrays.asList(school1, school2, school3);
    }

    private void findViews(View view) {
        departmentRecyclerView = view.findViewById(R.id.department_recycler_view);
    }

    private Context context;

    private RecyclerView departmentRecyclerView;
}
