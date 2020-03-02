package com.cs428.hotdognothotdog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

        Bitmap bitmap = null;
        HotDogIdentifier hotDogIdentifier = null;
        Log.i("HotDog", "Creating hot dog");
        try {
            // creating bitmap from packaged into app android asset 'image.jpg',
            // app/src/main/assets/image.jpg
            bitmap = BitmapFactory.decodeStream(getAssets().open("image.jpg"));
            hotDogIdentifier = new HotDogIdentifier(this);
        } catch (IOException e) {
            Log.e("HotDog", "Error reading assets", e);
            finish();
        }

        Log.i("HotDog", "HotDog created");
        IImage image = new Image(bitmap);
        boolean isHotDog = hotDogIdentifier.isHotDog(image);
        Log.d("HotDog", String.valueOf(isHotDog));

//        Intent myIntent = new Intent(MainActivity.this, HotDogView.class);
//        MainActivity.this.startActivity(myIntent);
    }

}
