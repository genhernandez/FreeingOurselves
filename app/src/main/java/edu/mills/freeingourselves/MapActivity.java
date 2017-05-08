package edu.mills.freeingourselves;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.data.kml.KmlLayer;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Displays a map with resource locations marked.
 */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_map);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we add KML layers of resources to the map.
     * If Google Play services is not installed on the device, the user receives a prompt to install
     * Play services inside the SupportMapFragment. The API invokes this method after the user has
     * installed Google Play services and returned to the app.
     *
     * @param googleMap the created GoogleMap.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Bay Area, California
        // and move the map's camera to the same location.
        LatLng bayArea = new LatLng(37.8271784, -122.29130780000003);
        googleMap.addMarker(new MarkerOptions().position(bayArea)
                .title("Marker in Bay Area")
                .snippet("Here is text about it"));

        // Add layers from kml files.
        try {
            KmlLayer erLayer = new KmlLayer(googleMap, R.raw.ers, getApplicationContext());
            erLayer.addLayerToMap();
            KmlLayer clinicalCareLayer = new KmlLayer(googleMap, R.raw.clinicalcareprograms, getApplicationContext());
            clinicalCareLayer.addLayerToMap();
            KmlLayer transacrossamericaLayer = new KmlLayer(googleMap, R.raw.transacrossamerica, getApplicationContext());
            transacrossamericaLayer.addLayerToMap();
            KmlLayer littleblackbookLayer = new KmlLayer(googleMap, R.raw.littleblackbook, getApplicationContext());
            littleblackbookLayer.addLayerToMap();
            KmlLayer youthLayer = new KmlLayer(googleMap, R.raw.youthresourcemap, getApplicationContext());
            youthLayer.addLayerToMap();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        // Setting my location.
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                googleMap.setMyLocationEnabled(true);
            }
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(bayArea));
    }
}
