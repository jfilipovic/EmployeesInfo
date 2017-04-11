package com.example.jerko.employeesinfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.jerko.employeesinfo.fragments.DetailsFragment;
import com.example.jerko.employeesinfo.fragments.ListFragment;
import com.example.jerko.employeesinfo.models.Employee;
import com.example.jerko.employeesinfo.tasks.FetchEmployeesTask;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Fragment frag;
    private List<Employee> employeeList;
    private Employee selectedEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
        setFragment("list");
        if (isNetworkAvailable(this)) {
            new FetchEmployeesTask(this).execute();
        } else {
            Toast.makeText(this, "NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
        }


    }


    public void fillInList(List<Employee> employees){
        this.employeeList = employees;
        setFragment("list");

    }

    public List<Employee> getEmployeeList(){
        return employeeList;
    }

    public void setSelectedEmployee(int position) {
        selectedEmployee = employeeList.get(position);
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }


    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
    }

    public void setToolbarIcon(){
        if (frag instanceof DetailsFragment){
            toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha );
            toolbar.setNavigationOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setFragment("list");
                        }
                    }

            );

            toolbar.setTitle("Details");

        } else {
            toolbar.setNavigationIcon(null);
            toolbar.setTitle(getResources().getString(R.string.app_name));
        }
    }

    public void setFragment(String fragName){
        if (fragName.equals("list"))
            frag = new ListFragment();
        else
            frag = new DetailsFragment();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit);
        fragmentTransaction.replace(R.id.fragment_holder, frag);
        fragmentTransaction.commit();

        setToolbarIcon();
    }

    @Override
    public void onBackPressed() {
        if (frag instanceof DetailsFragment) setFragment("list");
        else super.onBackPressed();
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
