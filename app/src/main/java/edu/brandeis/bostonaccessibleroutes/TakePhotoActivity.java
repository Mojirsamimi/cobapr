package edu.brandeis.bostonaccessibleroutes;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by macy on 10/12/16.
 */

public class TakePhotoActivity extends AppCompatActivity {
    public static final int REQUEST_CAPTURE = 1;
    ImageView result_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        Button click = (Button) findViewById(R.id.Bcapture);
        result_photo = (ImageView) findViewById((R.id.imageView));

        if(!hasCamera()){
            click.setEnabled(false);
        }
    }


    public boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void launchCamera(View v){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, REQUEST_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAPTURE){
            if(resultCode == RESULT_OK){
                Bundle extras = data.getExtras();
                Bitmap photo = (Bitmap) extras.get("data");
                result_photo.setImageBitmap(photo);
            }
        }
    }
}
