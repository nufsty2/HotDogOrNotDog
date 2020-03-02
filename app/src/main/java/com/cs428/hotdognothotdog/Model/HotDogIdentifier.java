package com.cs428.hotdognothotdog.Model;

import com.cs428.hotdognothotdog.Model.Interfaces.IHotDogIdentifier;
import com.cs428.hotdognothotdog.Model.Interfaces.IImage;

import android.graphics.Bitmap;

import org.pytorch.IValue;
import org.pytorch.Module;
import org.pytorch.Tensor;
import org.pytorch.torchvision.TensorImageUtils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public class HotDogIdentifier implements IHotDogIdentifier {
    Module module;
    Random rand;

    public HotDogIdentifier(Context context) throws IOException {
        // loading serialized torchscript module from packaged into app android asset
        // model.pt,
        // app/src/model/assets/model.pt
        // module = Module.load(assetFilePath(context, "model.pt"));
        rand = new Random(5); // T, F, F, T, F
        rand.nextBoolean(); // Start on the first false
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
        return rand.nextBoolean();
    }
}
