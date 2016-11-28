package edu.brandeis.bostonaccessibleroutes;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import edu.brandeis.bostonaccessibleroutes.dbutil.AndroidDatabaseManager;
import edu.brandeis.bostonaccessibleroutes.dbutil.DBHelper;

public class DataCollectionActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    private DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);
        mydb = new DBHelper(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final FloatingActionButton buttonIncline = (FloatingActionButton) findViewById(R.id.fab1);
        final FloatingActionButton buttonDecline = (FloatingActionButton) findViewById(R.id.fab2);
        final FloatingActionButton buttonCurve = (FloatingActionButton) findViewById(R.id.fab3);
        final FloatingActionButton buttonCrossSlope = (FloatingActionButton) findViewById(R.id.fab4);
        final FloatingActionButton buttonHole = (FloatingActionButton) findViewById(R.id.fab5);
        final FloatingActionButton buttonDebris = (FloatingActionButton) findViewById(R.id.fab6);
        final FloatingActionButton buttonNarrow = (FloatingActionButton) findViewById(R.id.fab7);
        final FloatingActionButton buttonUneven = (FloatingActionButton) findViewById(R.id.fab8);
        final FloatingActionButton buttonSteps = (FloatingActionButton) findViewById(R.id.fab9);

        buttonIncline.setOnClickListener(new View.OnClickListener() {
            AlertDialog.Builder builder = new AlertDialog.Builder(DataCollectionActivity.this);
            public void onClick(View v) {
                //  Intent selectDataTypeIntent=new Intent(DataCollectionActivity.this,SelectDataTypeActivity.class);
                // startActivity(selectDataTypeIntent);



                builder.setMessage("Add Evidences?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent TakePhotoIntent=new Intent(DataCollectionActivity.this,TakePhotoActivity.class);
                        startActivity(TakePhotoIntent);

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.dismiss();
                        if(mydb.insertRouteCondition(12,75,1,"comment","c:\\voice.mp3","c:\\image.jpg"))
                        {
                            Toast.makeText(getApplicationContext(), "Route Condition Saved", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        buttonDecline.setOnClickListener(new View.OnClickListener() {
            AlertDialog.Builder builder = new AlertDialog.Builder(DataCollectionActivity.this);
            public void onClick(View v) {
                //  Intent selectDataTypeIntent=new Intent(DataCollectionActivity.this,SelectDataTypeActivity.class);
                // startActivity(selectDataTypeIntent);



                builder.setMessage("Add Evidences?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent TakePhotoIntent=new Intent(DataCollectionActivity.this,TakePhotoActivity.class);
                        startActivity(TakePhotoIntent);

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.dismiss();
                        if(mydb.insertRouteCondition(12,75,1,"comment","c:\\voice.mp3","c:\\image.jpg"))
                        {
                            Toast.makeText(getApplicationContext(), "Route Condition Saved", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        buttonCurve.setOnClickListener(new View.OnClickListener() {
            AlertDialog.Builder builder = new AlertDialog.Builder(DataCollectionActivity.this);
            public void onClick(View v) {
                //  Intent selectDataTypeIntent=new Intent(DataCollectionActivity.this,SelectDataTypeActivity.class);
                // startActivity(selectDataTypeIntent);



                builder.setMessage("Add Evidences?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent TakePhotoIntent=new Intent(DataCollectionActivity.this,TakePhotoActivity.class);
                        startActivity(TakePhotoIntent);

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.dismiss();
                        if(mydb.insertRouteCondition(12,75,1,"comment","c:\\voice.mp3","c:\\image.jpg"))
                        {
                            Toast.makeText(getApplicationContext(), "Route Condition Saved", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        buttonCrossSlope.setOnClickListener(new View.OnClickListener() {
            AlertDialog.Builder builder = new AlertDialog.Builder(DataCollectionActivity.this);
            public void onClick(View v) {
                //  Intent selectDataTypeIntent=new Intent(DataCollectionActivity.this,SelectDataTypeActivity.class);
                // startActivity(selectDataTypeIntent);



                builder.setMessage("Add Evidences?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent TakePhotoIntent=new Intent(DataCollectionActivity.this,TakePhotoActivity.class);
                        startActivity(TakePhotoIntent);

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.dismiss();
                        if(mydb.insertRouteCondition(12,75,1,"comment","c:\\voice.mp3","c:\\image.jpg"))
                        {
                            Toast.makeText(getApplicationContext(), "Route Condition Saved", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        buttonHole.setOnClickListener(new View.OnClickListener() {
            AlertDialog.Builder builder = new AlertDialog.Builder(DataCollectionActivity.this);
            public void onClick(View v) {
                //  Intent selectDataTypeIntent=new Intent(DataCollectionActivity.this,SelectDataTypeActivity.class);
                // startActivity(selectDataTypeIntent);



                builder.setMessage("Add Evidences?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent TakePhotoIntent=new Intent(DataCollectionActivity.this,TakePhotoActivity.class);
                        startActivity(TakePhotoIntent);

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.dismiss();
                        if(mydb.insertRouteCondition(12,75,1,"comment","c:\\voice.mp3","c:\\image.jpg"))
                        {
                            Toast.makeText(getApplicationContext(), "Route Condition Saved", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        buttonDebris.setOnClickListener(new View.OnClickListener() {
            AlertDialog.Builder builder = new AlertDialog.Builder(DataCollectionActivity.this);
            public void onClick(View v) {
                //  Intent selectDataTypeIntent=new Intent(DataCollectionActivity.this,SelectDataTypeActivity.class);
                // startActivity(selectDataTypeIntent);



                builder.setMessage("Add Evidences?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent TakePhotoIntent=new Intent(DataCollectionActivity.this,TakePhotoActivity.class);
                        startActivity(TakePhotoIntent);

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.dismiss();
                        if(mydb.insertRouteCondition(12,75,1,"comment","c:\\voice.mp3","c:\\image.jpg"))
                        {
                            Toast.makeText(getApplicationContext(), "Route Condition Saved", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        buttonNarrow.setOnClickListener(new View.OnClickListener() {
            AlertDialog.Builder builder = new AlertDialog.Builder(DataCollectionActivity.this);
            public void onClick(View v) {
                //  Intent selectDataTypeIntent=new Intent(DataCollectionActivity.this,SelectDataTypeActivity.class);
                // startActivity(selectDataTypeIntent);



                builder.setMessage("Add Evidences?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent TakePhotoIntent=new Intent(DataCollectionActivity.this,TakePhotoActivity.class);
                        startActivity(TakePhotoIntent);

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.dismiss();
                        if(mydb.insertRouteCondition(12,75,1,"comment","c:\\voice.mp3","c:\\image.jpg"))
                        {
                            Toast.makeText(getApplicationContext(), "Route Condition Saved", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        buttonUneven.setOnClickListener(new View.OnClickListener() {
            AlertDialog.Builder builder = new AlertDialog.Builder(DataCollectionActivity.this);
            public void onClick(View v) {
                //  Intent selectDataTypeIntent=new Intent(DataCollectionActivity.this,SelectDataTypeActivity.class);
                // startActivity(selectDataTypeIntent);



                builder.setMessage("Add Evidences?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent TakePhotoIntent=new Intent(DataCollectionActivity.this,TakePhotoActivity.class);
                        startActivity(TakePhotoIntent);

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.dismiss();
                        if(mydb.insertRouteCondition(12,75,1,"comment","c:\\voice.mp3","c:\\image.jpg"))
                        {
                            Toast.makeText(getApplicationContext(), "Route Condition Saved", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        buttonSteps.setOnClickListener(new View.OnClickListener() {
            AlertDialog.Builder builder = new AlertDialog.Builder(DataCollectionActivity.this);
            public void onClick(View v) {
                //  Intent selectDataTypeIntent=new Intent(DataCollectionActivity.this,SelectDataTypeActivity.class);
                // startActivity(selectDataTypeIntent);



                builder.setMessage("Add Evidences?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent TakePhotoIntent=new Intent(DataCollectionActivity.this,TakePhotoActivity.class);
                        startActivity(TakePhotoIntent);

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.dismiss();
                        if(mydb.insertRouteCondition(12,75,1,"comment","c:\\voice.mp3","c:\\image.jpg"))
                        {
                            Toast.makeText(getApplicationContext(), "Route Condition Saved", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setZoomControlsEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        //MarkerOptions markerOptions = new MarkerOptions();
       // markerOptions.position(latLng);
       // markerOptions.title("Current Position");
       // markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
       // mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        CameraPosition cameraPosition = CameraPosition.builder()
                .target(latLng)
                .zoom(18)
                .bearing(90)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),1000,null);


        //stop location updates
     //   if (mGoogleApiClient != null) {
          //  LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
      //  }
    }
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // Permission was granted.
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            //You can add here other case statements according to your requirement.
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.show_db:
                Intent dbmanager = new Intent(this,AndroidDatabaseManager.class);
                startActivity(dbmanager);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
