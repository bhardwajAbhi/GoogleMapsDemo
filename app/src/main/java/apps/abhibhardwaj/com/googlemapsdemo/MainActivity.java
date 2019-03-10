package apps.abhibhardwaj.com.googlemapsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
    mapFragment.getMapAsync(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {

    LatLng chandigarh = new LatLng(30.741482, 76.768066);

    MarkerOptions markerOptions = new MarkerOptions();
    markerOptions.title("Marker in Chandigarh");
    markerOptions.position(chandigarh);
    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

    // googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MainActivity.this, R.raw.maps_style_night));     //custom map style
    googleMap.addMarker(markerOptions);
    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(chandigarh, 16));
  }
}





