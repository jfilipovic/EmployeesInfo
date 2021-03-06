package com.example.jerko.employeesinfo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jerko.employeesinfo.MainActivity;
import com.example.jerko.employeesinfo.R;
import com.example.jerko.employeesinfo.adapters.EmployeesAdapter;
import com.example.jerko.employeesinfo.models.Employee;
import com.example.jerko.employeesinfo.tasks.FetchEmployeesTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerko on 10.4.2017..
 */

public class ListFragment extends Fragment {

    private ListView listView;
    private List<Employee> employeeList;
    private SwipeRefreshLayout swipeContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        employeeList = ((MainActivity)getActivity()).getEmployeeList();
        listView = (ListView) view.findViewById(R.id.employeesListview);
        EmployeesAdapter adapter = new EmployeesAdapter(getActivity(), employeeList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                if (!employeeList.get(position).getRole().equals("_header_")){
                    ((MainActivity)getActivity()).setSelectedEmployee(position);
                    ((MainActivity)getActivity()).setFragment("details");
                }

            }
        });

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                if (((MainActivity)getActivity()).isNetworkAvailable(getActivity())) {
                    new FetchEmployeesTask(getActivity()).execute();
                } else {
                    Toast.makeText(getActivity(), "NO INTERNET CONNECTION! Swipe to refresh!" , Toast.LENGTH_LONG).show();
                    swipeContainer.setRefreshing(false);
                }
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright);



        return view;
    }


}
