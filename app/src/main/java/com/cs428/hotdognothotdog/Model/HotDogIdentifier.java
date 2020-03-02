package com.cs428.hotdognothotdog.Model;

import com.cs428.hotdognothotdog.Model.Interfaces.IHotDogIdentifier;
import com.cs428.hotdognothotdog.Model.Interfaces.IImage;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import org.pytorch.IValue;
import org.pytorch.Module;
import org.pytorch.Tensor;
import org.pytorch.torchvision.TensorImageUtils;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public class HotDogIdentifier implements IHotDogIdentifier {
    Module module;
    Context context;

    public HotDogIdentifier(Context context) throws IOException {
        // loading serialized torchscript module from packaged into app android asset
        // model.pt,
        // app/src/model/assets/model.pt
        this.context = context;
        module = Module.load(assetFilePath(context, "model.pt"));
    }

    /**
     * Copies specified asset to the file in /files app directory and returns this file absolute path.
     *
     * @return absolute file path
     */
    private static String assetFilePath(Context context, String assetName) throws IOException {
        File file = new File(context.getFilesDir(), assetName);
        if (file.exists() && file.length() > 0) {
            return file.getAbsolutePath();
        }

        try (InputStream is = context.getAssets().open(assetName)) {
            try (OutputStream os = new FileOutputStream(file)) {
                byte[] buffer = new byte[4 * 1024];
                int read;
                while ((read = is.read(buffer)) != -1) {
                    os.write(buffer, 0, read);
                }
                os.flush();
            }
            return file.getAbsolutePath();
        }
    }
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("HOTDOG","Permission is granted");
                return true;
            } else {

    @Override
    public boolean isHotDog(IImage potentialHotDog) {
//        Bitmap bitmap = potentialHotDog.getImageBitmap();
//
//
//        // preparing input tensor
//        final Tensor inputTensor = TensorImageUtils.bitmapToFloat32Tensor(bitmap,
//                TensorImageUtils.TORCHVISION_NORM_MEAN_RGB, TensorImageUtils.TORCHVISION_NORM_STD_RGB);
//
//        // running the model
//        final Tensor outputTensor = module.forward(IValue.from(inputTensor)).toTensor();
//
//        // getting tensor content as java array of floats
//        final float[] scores = outputTensor.getDataAsFloatArray();
//
//        return scores[0] > scores[1];
        Random rand = new Random();
        return rand.nextBoolean();
    }
}
