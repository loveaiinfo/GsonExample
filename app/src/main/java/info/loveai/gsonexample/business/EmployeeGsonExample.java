package info.loveai.gsonexample.business;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.loveai.gsonexample.model.Address;
import info.loveai.gsonexample.model.Employee;

public class EmployeeGsonExample {
    public static final String TAG = "GsonExample";

    public static void test(Context context){
        Employee emp = createEmployee();

        // Get Gson object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // read JSON file data as String
        try {
            InputStream is = context.getAssets().open("employee.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonString = new String(buffer, "UTF-8");
            Employee emp1 = gson.fromJson(jsonString, Employee.class);
            Log.d(TAG,"\n\nEmployee Object\n\n" + emp1);

            String jsonEmp = gson.toJson(emp);
            Log.d(TAG,jsonEmp);

        }catch (IOException e){

        }
    }
    public static Employee createEmployee() {

        Employee emp = new Employee();
        emp.setId(100);
        emp.setName("David");
        emp.setPermanent(false);
        emp.setPhoneNumbers(new long[] { 123456, 987654 });
        emp.setRole("Manager");

        Address add = new Address();
        add.setCity("Bangalore");
        add.setStreet("BTM 1st Stage");
        add.setZipcode(560100);
        emp.setAddress(add);

        List<String> cities = new ArrayList<String>();
        cities.add("Los Angeles");
        cities.add("New York");
        emp.setCities(cities);

        Map<String, String> props = new HashMap<String, String>();
        props.put("salary", "1000 Rs");
        props.put("age", "28 years");
        emp.setProperties(props);

        return emp;
    }
}
