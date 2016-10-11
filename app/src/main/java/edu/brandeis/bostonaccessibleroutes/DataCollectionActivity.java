package edu.brandeis.bostonaccessibleroutes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class DataCollectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);

        final Button buttonIncline = (Button) findViewById(R.id.button_incline);

        buttonIncline.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent selectDataTypeIntent=new Intent(DataCollectionActivity.this,SelectDataTypeActivity.class);
                startActivity(selectDataTypeIntent);
            }
        });


    }
}
