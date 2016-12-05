package edu.brandeis.bostonaccessibleroutes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by macy on 11/28/16.
 */

public class InfoActivity_step2 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_step2);
        Button click = (Button) findViewById(R.id.next2);
        click.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent Start=new Intent(InfoActivity_step2.this,InfoActivity_step3.class);
                startActivity(Start);

            }
        });
    }
}
