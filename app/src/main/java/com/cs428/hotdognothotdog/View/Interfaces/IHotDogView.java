package com.cs428.hotdognothotdog.View.Interfaces;

import android.graphics.Bitmap;

public interface IHotDogView {
    void displayIsHotDog();
    void displayNotHotDog();
    Bitmap getDisplay();
    void clearDisplay();
    void postToast(String message);
}
