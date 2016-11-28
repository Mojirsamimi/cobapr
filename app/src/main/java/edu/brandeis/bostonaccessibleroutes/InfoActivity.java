package edu.brandeis.bostonaccessibleroutes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by macy on 11/28/16.
 */

public class InfoActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Button click = (Button) findViewById(R.id.know_the_info);
        click.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent Start=new Intent(InfoActivity.this,DataCollectionActivity.class);
                startActivity(Start);

            }
        });
    }
}
