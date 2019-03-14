package apps.abhibhardwaj.com.googlemapsdemo;

import android.Manifest.permission;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener, OnMapReadyCallback {

  GoogleMap gMap;
  Geocoder geocoder;

  EditText edtInput;
  Button btnSearch;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    edtInput = findViewById(R.id.edt_location);
    btnSearch = findViewById(R.id.btn_search);
    btnSearch.setOnClickListener(this);

    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.fragment_map);
    mapFragment.getMapAsync(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {

    gMap = googleMap;

    LatLng chandigarh = new LatLng(30.741482, 76.768066);

    MarkerOptions markerOptions = new MarkerOptions();
    markerOptions.title("Marker in Chandigarh");
    markerOptions.position(chandigarh);
    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
    // markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mario));    //custom marker icon

    googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    // gMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MainActivity.this, R.raw.maps_style_night));     //custom map style
    gMap.addMarker(markerOptions);
    gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(chandigarh, 16));

    // UI elements on google map
    gMap.getUiSettings().setZoomControlsEnabled(true);
    gMap.getUiSettings().setMyLocationButtonEnabled(true);
    gMap.getUiSettings().setCompassEnabled(true);
    gMap.getUiSettings().setAllGesturesEnabled(true);



    // enable current location
    if (ActivityCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this, permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      return;
    }
    gMap.setMyLocationEnabled(true);

  }

  @Override
  public void onClick(View v) {

    if (v == btnSearch)
    {
      searchLocation();
    }

  }

  private void searchLocation() {

    List<Address> addressList = null;

    String location = edtInput.getText().toString().trim();

    if (location.isEmpty())
    {
      return;
    }

    geocoder = new Geocoder(MainActivity.this);

    try
    {
      addressList = geocoder.getFromLocationName(location, 1);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    Address address = addressList.get(0);

    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

    MarkerOptions options = new MarkerOptions();
    options.position(latLng);
    options.title(location);
    gMap.addMarker(options);
    gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));

  }
}





