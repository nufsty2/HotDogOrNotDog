package com.cs428.hotdognothotdog.View;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.cs428.hotdognothotdog.R;
import com.cs428.hotdognothotdog.View.Interfaces.IHotDogView;

public class HotDogView extends AppCompatActivity implements IHotDogView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_dog_view);
    }

    @Override
    public void displayIsHotDog() {

    }

    @Override
    public void displayNotHotDog() {

    }

    @Override
    public Bitmap getDisplay() {
        return null;
    }

    @Override
    public void clearDisplay() {

    }
}
