package com.example.jerko.employeesinfo.tasks;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.example.jerko.employeesinfo.MainActivity;
import com.example.jerko.employeesinfo.models.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Jerko on 10.4.2017..
 */

public class FetchEmployeesTask extends AsyncTask<String, Void, JSONObject> {

    private Activity activity;
    private Dialog dialog;
    private Handler handler = new Handler();
    private Runnable runnable;


    public FetchEmployeesTask(final Activity activity) {
        this.activity = activity;

        runnable = new Runnable() {
            @Override
            public void run() {
                dialog = ProgressDialog.show(activity, null, "Loading...", true);
            }
        };
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        showDialog();
    }

    @Override
    protected JSONObject doInBackground(String... params) {

        String str="https://nielsmouthaan.nl/backbase/members.php";
        URLConnection urlConn = null;
        BufferedReader bufferedReader = null;
        try
        {
            URL url = new URL(str);
            urlConn = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line);
            }

            return new JSONObject(stringBuffer.toString());
        }
        catch(Exception ex)
        {
            Log.e("App", "FetchEmployeesTask", ex);
            return null;
        }
        finally
        {
            if(bufferedReader != null)
            {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {

        dismissDialog();

        List<Employee> employees= new ArrayList<>();
        if (jsonObject != null) {
            try {
                Log.d("json", jsonObject.toString());

                ObjectMapper mapper = new ObjectMapper();
                Iterator<?> keys = jsonObject.keys();


                while( keys.hasNext() ) {
                    String key = (String)keys.next();
                    Log.d("header", key);

                    Employee headerEmployee = new Employee();
                    headerEmployee.setName(key);
                    headerEmployee.setRole("_header_");
                    employees.add(headerEmployee);

                    Employee[] sectionEmployees = mapper.readValue(jsonObject.get(key).toString(), Employee[].class);
                    employees.addAll((new ArrayList<>(Arrays.asList(sectionEmployees))));

                }
                ((MainActivity)activity).logEmployees(employees);
                ((MainActivity)activity).fillInList(employees);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        else Log.d("json", "nema");

    }


    private void showDialog(){
        handler.postDelayed(runnable, 500);
    }

    private void dismissDialog() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }else {
                handler.removeCallbacks(runnable);
            }
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }

    }
}
