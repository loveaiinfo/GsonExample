package info.loveai.gsonexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

import info.loveai.gsonexample.business.EmployeeGsonExample;
import info.loveai.gsonexample.business.EmployeeGsonReader;
import info.loveai.gsonexample.business.EmployeeGsonWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EmployeeGsonExample.test(this);
        try {
            EmployeeGsonReader.tests(this);
            EmployeeGsonWriter.test(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
