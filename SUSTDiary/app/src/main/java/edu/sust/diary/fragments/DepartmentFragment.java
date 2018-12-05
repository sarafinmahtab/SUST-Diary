package edu.sust.diary.fragments;


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

import java.util.List;

import edu.sust.diary.R;
import edu.sust.diary.adapters.SchoolAdapter;
import edu.sust.diary.models.realm.Schools;
import edu.sust.diary.models.realm.SchoolsOfDepartments;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepartmentFragment extends Fragment {

    private RealmList<Schools> schoolList;

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

        schoolList = getListOfSchool();

        SchoolAdapter schoolAdapter = new SchoolAdapter(context, schoolList);

        departmentRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        departmentRecyclerView.addItemDecoration(
                new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        departmentRecyclerView.setNestedScrollingEnabled(false);
        departmentRecyclerView.setHasFixedSize(false);

        departmentRecyclerView.setAdapter(schoolAdapter);
    }

    private RealmList<Schools> getListOfSchool() {

        try (Realm realm = Realm.getDefaultInstance()) {
            RealmResults<SchoolsOfDepartments> results = realm
                    .where(SchoolsOfDepartments.class)
                    .findAll();

            List<SchoolsOfDepartments> schoolsOfDepartmentsList = realm.copyFromRealm(results);

            return schoolsOfDepartmentsList.get(0).getSchools();
        }
    }

    private void findViews(View view) {
        departmentRecyclerView = view.findViewById(R.id.department_recycler_view);
    }

    private Context context;

    private RecyclerView departmentRecyclerView;
}
