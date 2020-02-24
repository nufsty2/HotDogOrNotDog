package com.cs428.hotdognothotdog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.cs428.hotdognothotdog.Model.HotDogIdentifier;
import com.cs428.hotdognothotdog.Model.Image;
import com.cs428.hotdognothotdog.Model.Interfaces.IImage;

import java.io.IOException;

import com.cs428.hotdognothotdog.View.HotDogView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent myIntent = new Intent(MainActivity.this, HotDogView.class);
        MainActivity.this.startActivity(myIntent);
    }

}
