package com.example.jerko.employeesinfo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jerko.employeesinfo.R;
import com.example.jerko.employeesinfo.models.Employee;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerko on 10.4.2017..
 */

public class EmployeesAdapter extends BaseAdapter {

    private static final int TYPE_EMPLOYEE = 0;
    private static final int TYPE_HEADER = 1;

    private List<Employee> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public EmployeesAdapter(Context context, List<Employee> mData) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.mData = mData;
    }


    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getRole().equals("_header_") ? TYPE_HEADER : TYPE_EMPLOYEE;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        if (mData != null)return mData.size();
        else return 0;
    }

    @Override
    public Employee getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();
            switch (rowType) {
                case TYPE_EMPLOYEE:
                    convertView = mInflater.inflate(R.layout.item_employee, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.textName);
                    holder.imageView = (ImageView) convertView.findViewById(R.id.viewPhoto);
                    break;
                case TYPE_HEADER:
                    convertView = mInflater.inflate(R.layout.item_header, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.textHeader);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        switch (rowType){
            case TYPE_EMPLOYEE:
                holder.textView.setText(mData.get(position).getName() + " " + mData.get(position).getSurname());
                Picasso.with(context).load("https://nielsmouthaan.nl/backbase/photos/" + mData.get(position).getPhoto() ).into(holder.imageView);
                break;
            case TYPE_HEADER:
                holder.textView.setText(mData.get(position).getName());
                break;
        }


        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }

}
