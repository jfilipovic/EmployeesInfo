package com.example.jerko.employeesinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.jerko.employeesinfo.models.Employee;
import com.example.jerko.employeesinfo.tasks.FetchEmployeesTask;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FetchEmployeesTask(this).execute();
    }

    public void logEmployees(List<Employee> employees){
        for (int i = 0; i < employees.size(); i++){
            if (employees.get(i) != null) Log.d("employees", employees.get(i).getName() + " " +  employees.get(i).getSurname());
        }
    }
}
