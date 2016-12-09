package edu.brandeis.bostonaccessibleroutes;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.models.nosql.AprSidewalkConditionDO;
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
import com.yalantis.guillotine.animation.GuillotineAnimation;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import edu.brandeis.bostonaccessibleroutes.dbutil.AndroidDatabaseManager;
import edu.brandeis.bostonaccessibleroutes.dbutil.DBHelper;
import edu.brandeis.bostonaccessibleroutes.widget.CanaroTextView;

import android.support.v7.widget.Toolbar;


public class DataCollectionActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    public static final int ADD_EVIDENCE_REQUEST=1;
    public static final String PHOTO_KEY="photo";
    public static final String VOICE_KEY="voice";
    public static final String COMMENT_KEY="comment";
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;
    private LocationRequest mLocationRequest;
    private DBHelper mydb;
    private Map<Integer,SidewalkCondition> sidewalkCondMap;
    private View.OnClickListener onClickListener;
    private SidewalkCondition lastSelectedSidewalkCondition;
    private AprSidewalkConditionDO aprSidewalkConditionDO;



    private class SidewalkCondition{
        String description;
        Double id;
        public SidewalkCondition(String description,Double id)
        {
            this.description=description;
            this.id=id;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);
        mydb = new DBHelper(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        RelativeLayout root=(RelativeLayout)findViewById(R.id.activity_data_collection);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        //if (toolbar != null) {
          //  setSupportActionBar(toolbar);
           // getSupportActionBar().setTitle(null);
        //}
        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        root.addView(guillotineMenu);
        ImageView contentHamburger=(ImageView)findViewById(R.id.content_hamburger);
        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();

        aprSidewalkConditionDO=new AprSidewalkConditionDO();
        sidewalkCondMap=new HashMap<Integer,SidewalkCondition>();
        sidewalkCondMap.put(R.id.fabUnevenPavement,new SidewalkCondition("Uneven Pavement",1.0));
        sidewalkCondMap.put(R.id.fabHole,new SidewalkCondition("Holes",2.0));
        sidewalkCondMap.put(R.id.fabSteps,new SidewalkCondition("Steps",3.0));
        sidewalkCondMap.put(R.id.fabDebris,new SidewalkCondition("Debris",4.0));
        sidewalkCondMap.put(R.id.fabNarrowSidewalk,new SidewalkCondition("Narrow Sidewalk",5.0));
        sidewalkCondMap.put(R.id.fabCurbCut,new SidewalkCondition("Curb Cut",6.0));
        sidewalkCondMap.put(R.id.fabCrossSlope,new SidewalkCondition("Cross Slope",7.0));
        sidewalkCondMap.put(R.id.fabInclination,new SidewalkCondition("Inclination",8.0));
        sidewalkCondMap.put(R.id.fabOthers,new SidewalkCondition("Others",9.0));
        onClickListener=new View.OnClickListener() {
            AlertDialog.Builder builder = new AlertDialog.Builder(DataCollectionActivity.this);
            @Override
            public void onClick(View v) {
                System.out.println(sidewalkCondMap.get(v.getId()).description);
                lastSelectedSidewalkCondition=sidewalkCondMap.get(v.getId());

                builder.setMessage("Add Evidences?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent TakePhotoIntent=new Intent(DataCollectionActivity.this,TakePhotoActivity.class);
                        startActivityForResult(TakePhotoIntent,ADD_EVIDENCE_REQUEST);

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        saveSidewalkCondition();
                        dialog.dismiss();

                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        };
        Iterator<Integer> itr=sidewalkCondMap.keySet().iterator();
        while (itr.hasNext())
        {
           findViewById(itr.next()).setOnClickListener(onClickListener);
        }


        final CanaroTextView buttonInfo = (CanaroTextView) findViewById(R.id.how_to_use);

        buttonInfo.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent Start=new Intent(DataCollectionActivity.this,InfoActivity.class);
                startActivity(Start);

            }
        });

        final CanaroTextView signOut = (CanaroTextView) findViewById(R.id.sign_out);

        signOut.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AWSMobileClient.defaultMobileClient().getIdentityManager().signOut();
                Intent i=new Intent(DataCollectionActivity.this,SignInActivity.class);
                startActivity(i);
                finish();

            }
        });


    }



private void saveSidewalkCondition()
{
    aprSidewalkConditionDO.setScId(aprSidewalkConditionDO.generateUUID());
    aprSidewalkConditionDO.setUsername(AWSMobileClient.defaultMobileClient().getIdentityManager().getUserName());
    aprSidewalkConditionDO.setUserId(AWSMobileClient.defaultMobileClient().getIdentityManager().getCachedUserID());
    aprSidewalkConditionDO.setGpsLat(mLastLocation.getLatitude());
    aprSidewalkConditionDO.setGpsLong(mLastLocation.getLongitude());
    aprSidewalkConditionDO.setScId(aprSidewalkConditionDO.generateUUID());
    aprSidewalkConditionDO.setSidewalkConditionId(lastSelectedSidewalkCondition.id);
    aprSidewalkConditionDO.setSidewalkConditionDescription(lastSelectedSidewalkCondition.description);
    aprSidewalkConditionDO.setScTimestamp((double)new Date().getTime());
    aprSidewalkConditionDO.save();
    Toast.makeText(getApplicationContext(), lastSelectedSidewalkCondition.description+" has been captured!", Toast.LENGTH_SHORT).show();

    //saveToLocalDB
//    if(mydb.insertRouteCondition(12,75,1,"comment","c:\\voice.mp3","c:\\image.jpg"))
//    {
//        Toast.makeText(getApplicationContext(), "Route Condition Saved", Toast.LENGTH_SHORT).show();
//    }else {
//        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
//    }
}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case ADD_EVIDENCE_REQUEST:
                switch (resultCode)
                {
                    case RESULT_OK:
                       byte[] photo= data.getByteArrayExtra(PHOTO_KEY);
                        byte[] voice=data.getByteArrayExtra(VOICE_KEY);
                        String comment=data.getStringExtra(COMMENT_KEY);
                        aprSidewalkConditionDO.setAttachedComment(comment);
                        aprSidewalkConditionDO.setAttachedImage(photo);
                        aprSidewalkConditionDO.setAttachedVoice(voice);
                        saveSidewalkCondition();
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

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

            }else
            {
                checkLocationPermission();
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
        }

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
                        mMap.getUiSettings().setZoomControlsEnabled(true);
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
