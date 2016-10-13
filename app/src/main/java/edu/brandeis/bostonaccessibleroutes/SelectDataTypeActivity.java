package edu.brandeis.bostonaccessibleroutes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SelectDataTypeActivity extends AppCompatActivity {
    public static final int REQUEST_CAPTURE = 1;
    ImageView result_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_data_type);
        final Button button_add_image = (Button) findViewById(R.id.add_image);

        button_add_image.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent TakePhotoIntent=new Intent(SelectDataTypeActivity.this,TakePhotoActivity.class);
                startActivity(TakePhotoIntent);

            }
        });

    }
}
