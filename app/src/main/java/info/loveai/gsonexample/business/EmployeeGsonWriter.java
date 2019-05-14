package info.loveai.gsonexample.business;

import android.content.Context;

import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Set;

import info.loveai.gsonexample.model.Employee;

public class EmployeeGsonWriter {
    public static void test(Context context) throws IOException {
        Employee emp = EmployeeGsonExample.createEmployee();

        //writing on console, we can initialize with FileOutputStream to write to file
        File filesDir = context.getExternalFilesDir(null);
        if (!filesDir.exists()){
            filesDir.mkdirs();
        }
        File fileEmployees = new File(filesDir.getAbsoluteFile(),"employees.json");
        if (fileEmployees.exists()){
            fileEmployees.delete();
        }
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileEmployees));
        JsonWriter writer = new JsonWriter(out);
        //set indentation for pretty print
        writer.setIndent("    ");

        writer.beginObject(); // {
        writer.name("data");
        writer.beginArray();

        for(int i = 0; i < 10; i++) {
            //start writing
            writer.beginObject(); //{
            writer.name("id").value(emp.getId()); // "id": 123
            writer.name("name").value(emp.getName()); // "name": "David"
            writer.name("permanent").value(emp.isPermanent()); // "permanent": false
            writer.name("address").beginObject(); // "address": {
            writer.name("street").value(emp.getAddress().getStreet()); // "street": "BTM 1st Stage"
            writer.name("city").value(emp.getAddress().getCity()); // "city": "Bangalore"
            writer.name("zipcode").value(emp.getAddress().getZipcode()); // "zipcode": 560100
            writer.endObject(); // }
            writer.name("phoneNumbers").beginArray(); // "phoneNumbers": [
            for (long num : emp.getPhoneNumbers()) writer.value(num); //123456,987654
            writer.endArray(); // ]
            writer.name("role").value(emp.getRole()); // "role": "Manager"
            writer.name("cities").beginArray(); // "cities": [
            for (String c : emp.getCities()) writer.value(c); //"Los Angeles","New York"
            writer.endArray(); // ]
            writer.name("properties").beginObject(); //"properties": {
            Set<String> keySet = emp.getProperties().keySet();
            for (String key : keySet)
                writer.name("key").value(emp.getProperties().get(key));//"age": "28 years","salary": "1000 Rs"
            writer.endObject(); // }
            writer.endObject(); // }
        }

        writer.endArray();

        writer.endObject(); // }

        writer.flush();

        //close writer
        writer.close();

    }
}
