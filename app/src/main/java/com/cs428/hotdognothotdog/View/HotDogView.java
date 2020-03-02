package com.cs428.hotdognothotdog.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.cs428.hotdognothotdog.Model.HotDogIdentifier;
import com.cs428.hotdognothotdog.Model.Image;
import com.cs428.hotdognothotdog.Model.Interfaces.IHotDogIdentifier;
import com.cs428.hotdognothotdog.Model.Interfaces.IImage;
import com.cs428.hotdognothotdog.R;
import com.cs428.hotdognothotdog.View.Interfaces.IHotDogView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HotDogView extends AppCompatActivity implements IHotDogView {

    private ImageButton imageButton;
    private ImageView imageView;
    private IHotDogIdentifier hotDogIdentifier;
    private Bitmap imageBitmap;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_dog_view);

        imageButton = findViewById(R.id.imageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        imageView = findViewById(R.id.imageView);

        try {
            hotDogIdentifier = new HotDogIdentifier(this);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Ok boomer.");
        }
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        System.out.println(currentPhotoPath.toString());
        return image;
    }


    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                postToast("Unable to take photo");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.cs428.hotdognothotdog.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            setPic();
            analyzePhoto();
        }
    }

    private void analyzePhoto() {
        IImage image = new Image(imageBitmap);
        Boolean isHotDog;

        try {
            isHotDog = hotDogIdentifier.isHotDog(image);
            if (isHotDog) {
                postToast("HotDog!");
            } else {
                postToast("NotHotDog! :(");
            }
            Log.d("Value of isHotdog", String.valueOf(isHotDog));
        } catch (NullPointerException ex) {
            System.out.println("Caught " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        imageBitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageView.setImageBitmap(imageBitmap);
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

    @Override
    public void postToast(String message) {
        Toast toast = Toast.makeText(HotDogView.this, message, Toast.LENGTH_LONG);
        toast.show();
    }
}
