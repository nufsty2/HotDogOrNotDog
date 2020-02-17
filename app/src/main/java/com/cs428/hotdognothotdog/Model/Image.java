package com.cs428.hotdognothotdog.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.cs428.hotdognothotdog.Model.Interfaces.IImage;
import java.io.IOException;

public class Image implements IImage {
    Bitmap image;

    public Image(Bitmap image) {
        this.image = image;
    }

    @Override
    public Bitmap getImageBitmap() {
        return image;
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        image = bitmap;
    }

    @Override
    public void setImageBitmap(Context context, String imgPath) {
        try {
            image = BitmapFactory.decodeStream(context.getAssets().open(imgPath));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
