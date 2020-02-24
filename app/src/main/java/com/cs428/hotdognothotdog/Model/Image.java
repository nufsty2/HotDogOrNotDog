package com.cs428.hotdognothotdog.Model;

import android.graphics.Bitmap;

import com.cs428.hotdognothotdog.Model.Interfaces.IImage;

public class Image implements IImage {
    Bitmap bitmap;

    public Image(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public Bitmap getImageBitmap() {
        return bitmap;
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
