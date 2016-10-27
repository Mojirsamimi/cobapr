package edu.brandeis.bostonaccessibleroutes;

import android.*;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class DataCollectionActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap googleMap;
    private static final int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 1;
    private static final int MY_PERMISSIONS_ACCESS_COARSE_LOCATION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);

        SupportMapFragment fm = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);

        // Getting GoogleMap object from the fragment
        fm.getMapAsync(this);

//        final Button buttonIncline = (Button) findViewById(R.id.button_incline);
//
//        buttonIncline.setOnClickListener(new View.OnClickListener() {
//            AlertDialog.Builder builder = new AlertDialog.Builder(DataCollectionActivity.this);
//            public void onClick(View v) {
//                //  Intent selectDataTypeIntent=new Intent(DataCollectionActivity.this,SelectDataTypeActivity.class);
//                // startActivity(selectDataTypeIntent);
//
//
//
//                builder.setMessage("Add Evidences?");
//
//                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent TakePhotoIntent=new Intent(DataCollectionActivity.this,TakePhotoActivity.class);
//                        startActivity(TakePhotoIntent);
//
//                        dialog.dismiss();
//                    }
//                });
//
//                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        // Do nothing
//                        dialog.dismiss();
//                    }
//                });
//
//                AlertDialog alert = builder.create();
//                alert.show();
//            }
//        });


    }

    @Override
    public void onMapReady(GoogleMap map) {

        googleMap = map;

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_ACCESS_FINE_LOCATION);

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_ACCESS_COARSE_LOCATION);
            googleMap.setMyLocationEnabled(true);
            return;

        }
        googleMap.setMyLocationEnabled(true);
       // googleMap.setTrafficEnabled(true);
       // googleMap.setIndoorEnabled(true);
       // googleMap.setBuildingsEnabled(true);
         googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    googleMap.setMyLocationEnabled(true);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
