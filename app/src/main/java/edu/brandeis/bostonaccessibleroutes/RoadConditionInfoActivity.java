package edu.brandeis.bostonaccessibleroutes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by macy on 11/28/16.
 */

public class RoadConditionInfoActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_condition_info);
        Button goBack = (Button) findViewById(R.id.go_back);
        goBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent Start=new Intent(RoadConditionInfoActivity.this,DataCollectionActivity.class);
                startActivity(Start);

            }
        });
    }
}
