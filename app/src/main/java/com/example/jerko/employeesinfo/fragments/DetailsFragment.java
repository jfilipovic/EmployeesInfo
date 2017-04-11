package com.example.jerko.employeesinfo.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Jerko on 10.4.2017..
 */

public class DetailsFragment extends Fragment {
    private Employee employee;
    private TextView nameTextView, departmentTextView, roleTextView;
    private ImageView photoImageView, p1;
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

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmailClient();

            }
        });

        photoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ((BitmapDrawable)photoImageView.getDrawable()).getBitmap();
                openPhotoInViewer(bitmap);
            }
        });

        return view;
    }

    private void openEmailClient(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { employee.getEmail() });
        startActivity(Intent.createChooser(intent, ""));

    }

    private void openPhotoInViewer(Bitmap bitmap){

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

        File f = new File(Environment.getExternalStorageDirectory()
                + File.separator + employee.getPhoto());
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Uri path = Uri.fromFile(f);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(path, "image/*");
        startActivity(intent);
    }


}
