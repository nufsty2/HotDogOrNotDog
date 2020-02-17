package com.cs428.hotdognothotdog.Model.Interfaces;

import android.content.Context;
import android.graphics.Bitmap;

public interface IImage {
    Bitmap getImageBitmap();
    void setImageBitmap(Bitmap bitmap);
    void setImageBitmap(Context context, String imgPath);
}