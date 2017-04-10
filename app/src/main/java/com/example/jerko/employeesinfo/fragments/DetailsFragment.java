package com.example.jerko.employeesinfo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jerko.employeesinfo.MainActivity;
import com.example.jerko.employeesinfo.R;
import com.example.jerko.employeesinfo.models.Employee;
import com.squareup.picasso.Picasso;

/**
 * Created by Jerko on 10.4.2017..
 */

public class DetailsFragment extends Fragment {
    private Employee employee;
    private TextView nameTextView, departmentTextView, roleTextView;
    private ImageView photoImageView;
    private Button emailBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);
        employee = ((MainActivity)getActivity()).getSelectedEmployee();

        nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        departmentTextView = (TextView) view.findViewById(R.id.departmentTextView);
        roleTextView = (TextView) view.findViewById(R.id.roleTextView);
        photoImageView = (ImageView) view.findViewById(R.id.photoImageView);
        emailBtn = (Button) view.findViewById(R.id.emailButton);

        nameTextView.setText(employee.getName() + " " + employee.getSurname());
        departmentTextView.setText("Department: " + employee.getDepartment());
        roleTextView.setText(employee.getRole());
        Picasso.with(getActivity()).load("https://nielsmouthaan.nl/backbase/photos/" + employee.getPhoto()).into(photoImageView);

        return view;
    }
}
