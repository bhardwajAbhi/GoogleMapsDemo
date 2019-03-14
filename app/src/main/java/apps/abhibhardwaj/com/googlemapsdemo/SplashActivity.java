package apps.abhibhardwaj.com.googlemapsdemo;

import android.Manifest;
import android.Manifest.permission;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

  private static final int PERMISSION_REQUEST_CODE_FOR_LOCATION = 121;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


    if (ContextCompat.checkSelfPermission(SplashActivity.this, permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        && ContextCompat.checkSelfPermission(SplashActivity.this, permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
    {
      // permissions are granted already
      startActivity(new Intent(SplashActivity.this, MainActivity.class));
      finish();
    }
    else
    {
      // permissions are not granted yet; request for permissions
      String [] permissions = {permission.ACCESS_COARSE_LOCATION, permission.ACCESS_FINE_LOCATION};
      ActivityCompat.requestPermissions(SplashActivity.this, permissions, PERMISSION_REQUEST_CODE_FOR_LOCATION);
    }
    
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    
    switch (requestCode)
    {
      case PERMISSION_REQUEST_CODE_FOR_LOCATION:
      {
          if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
          {
            // permissions are granted
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
          }
          else
          {
            Toast.makeText(SplashActivity.this, "Please Grant Permissions in order to use app", Toast.LENGTH_SHORT).show();
          }
          return;
      }
    }
    
    
    
    
    
  }
}
