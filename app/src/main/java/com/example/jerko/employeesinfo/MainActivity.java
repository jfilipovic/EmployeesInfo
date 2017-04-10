package com.example.jerko.employeesinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.jerko.employeesinfo.adapters.EmployeesAdapter;
import com.example.jerko.employeesinfo.models.Employee;
import com.example.jerko.employeesinfo.tasks.FetchEmployeesTask;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EmployeesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.employeeList);
        new FetchEmployeesTask(this).execute();
    }

    public void fillInList(List<Employee> employees){
        adapter = new EmployeesAdapter(this, employees);
        listView.setAdapter(adapter);

    }

    public void logEmployees(List<Employee> employees){
        for (int i = 0; i < employees.size(); i++){
            if (employees.get(i) != null) Log.d("employees", employees.get(i).getName() + " " +  employees.get(i).getSurname());
        }
    }
}
