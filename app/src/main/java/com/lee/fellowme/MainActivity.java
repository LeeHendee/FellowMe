package com.lee.fellowme;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "FollowMe has started", Toast.LENGTH_SHORT).show();

        LocationManager lm = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
        String provider = LocationManager.NETWORK_PROVIDER;
        Location location = lm.getLastKnownLocation(provider);
        Log.e(TAG, "onCreate: location is = " +location);

        double longitude = location.getLongitude();
        double latitude = location.getLatitude();

        Log.e(TAG, "onCreate: longitude is "+longitude );
        Log.e(TAG, "onCreate: latitude is "+latitude );

        Geocoder geo = new Geocoder(this, Locale.getDefault());
        List<Address> addressList = null;
        try {
            addressList = geo.getFromLocation(latitude,longitude,1);
            Address address = null;
            String countryStr = null;
            String provinceStr = null;
            String cityStr = null;
            String street = null;
            String featureName = null;
            String subAdminArea = null;
            String sublocality = null;
            if (addressList!=null){
                address = addressList.get(0);
                countryStr = address.getCountryName();
                provinceStr = address.getAdminArea();
                cityStr = address.getLocality();
                featureName = address.getFeatureName();
                subAdminArea = address.getSubAdminArea();
                sublocality = address.getSubLocality();
                Log.e(TAG, "onCreate: "+address.getCountryName()+address.getAdminArea() +address.getLocality()+address.getFeatureName()+address.getSubAdminArea()+address.getSubLocality());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
