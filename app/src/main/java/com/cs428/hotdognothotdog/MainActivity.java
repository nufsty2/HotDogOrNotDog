package com.cs428.hotdognothotdog;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.cs428.hotdognothotdog.Model.HotDogIdentifier;
import com.cs428.hotdognothotdog.Model.Image;
import com.cs428.hotdognothotdog.Model.Interfaces.IImage;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bitmap = null;
        HotDogIdentifier hotDogIdentifier = null;
        try {
            // creating bitmap from packaged into app android asset 'image.jpg',
            // app/src/main/assets/image.jpg
            bitmap = BitmapFactory.decodeStream(getAssets().open("image.jpg"));
            hotDogIdentifier = new HotDogIdentifier(this);
        } catch (IOException e) {
            Log.e("HotDog", "Error reading assets", e);
            finish();
        }

        IImage image = new Image(bitmap);
        boolean isHotDog = hotDogIdentifier.isHotDog(image);
        Log.d("Value of isHotdog", String.valueOf(isHotDog));
    }

}
