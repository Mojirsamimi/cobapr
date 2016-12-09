package edu.brandeis.bostonaccessibleroutes;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by macy on 10/12/16.
 */

public class TakePhotoActivity extends AppCompatActivity {
    public static final int REQUEST_CAPTURE = 1;
    public static final int VOICE_REQUEST = 2;
    public static final String VOICE_PATH_KEY="voice_path";
    public static final String PHOTO_BITMAP_KEY="data";
    ImageView result_photo;
    private byte[] photo;
    private byte[] voice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        ImageButton click = (ImageButton) findViewById(R.id.button_attach_photo);
        ImageButton audio = (ImageButton) findViewById(R.id.button_attach_voice);
        Button button_done = (Button) findViewById(R.id.button_addevidence_done);
        final EditText editText=(EditText)findViewById(R.id.editText);
        result_photo = (ImageView) findViewById((R.id.imageView));
        button_done.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i=new Intent();
                i.putExtra(DataCollectionActivity.VOICE_KEY,voice);
                i.putExtra(DataCollectionActivity.PHOTO_KEY,photo);
                i.putExtra(DataCollectionActivity.COMMENT_KEY,editText.getText().toString());
                setResult(RESULT_OK,i);
                finish();
            }
        });
        if (!hasCamera()) {
            click.setEnabled(false);
        }
        audio.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent AudioRecordingIntent = new Intent(TakePhotoActivity.this, AudioRecordingActivity.class);
                startActivityForResult(AudioRecordingIntent, VOICE_REQUEST);

            }
        });
    }


    public boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void launchCamera(View v) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, REQUEST_CAPTURE);
    }

    public byte[] converFileToByteArray(String path)  {

        FileInputStream fis = null;
        ByteArrayOutputStream bos=null;
        try {
            fis = new FileInputStream(path);

         bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];

        for (int readNum; (readNum = fis.read(b)) != -1;) {
            bos.write(b, 0, readNum);
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = bos.toByteArray();

        return bytes;
    }

    public byte[] convertBitmapToByteArray(Bitmap b)
    {
        int bytes = b.getByteCount();
        ByteBuffer buffer = ByteBuffer.allocate(bytes);
        b.copyPixelsToBuffer(buffer);

        byte[] array = buffer.array();
        return array;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CAPTURE:
                switch (resultCode) {
                    case RESULT_OK:
                        Bundle extras = data.getExtras();
                        Bitmap bmPhoto = (Bitmap) extras.get(PHOTO_BITMAP_KEY);
                        result_photo.setImageBitmap(bmPhoto);
                        if(bmPhoto!=null)
                        {
                            photo=convertBitmapToByteArray(bmPhoto);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case VOICE_REQUEST:
                switch (resultCode)
                {
                    case RESULT_OK:
                        String voicePath=data.getStringExtra(VOICE_PATH_KEY);
                        if(voicePath!=null)
                        {
                            voice=converFileToByteArray(voicePath);
                        }
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;

        }
    }
}
