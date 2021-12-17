package com.example.mymap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymap.databinding.ActivityNavigationMapBinding;

import java.util.ArrayList;

public class NavigationMap extends AppCompatActivity implements OnMapReadyCallback {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationMapBinding binding;
    ArrayList<LatLng> arrayList=new ArrayList<LatLng>();
    private GoogleMap map;
    LatLng pak= new LatLng(30.3753, 69.3451);
    LatLng ind= new LatLng(20.5937, 78.9629);
    LatLng ban= new LatLng(23.6850, 90.3563);
    LatLng afg= new LatLng(33.9391, 67.7100);
    LatLng iran= new LatLng(32.4279, 53.6880);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList.add(pak);
        arrayList.add(ind);
        arrayList.add(ban);
        arrayList.add(afg);
        arrayList.add(iran);

        binding = ActivityNavigationMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        setSupportActionBar(binding.appBarNavigationMap.toolbar);
        binding.appBarNavigationMap.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_map);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_map, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_map);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        map=googleMap;
        for(int i = 0; i<arrayList.size();i++){
            map.addMarker(new MarkerOptions().position(arrayList.get(i)).title("Marker"));
            map.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
            map.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
        }


//       googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//           @Override
//           public void onMapClick(@NonNull LatLng latLng) {
//               MarkerOptions markerOptions = new MarkerOptions();
//               markerOptions.position(latLng);
//               markerOptions.title(latLng.latitude + ":" + latLng.longitude);
//               googleMap.clear();
//
//               googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//               googleMap.addMarker(markerOptions);
//
//           }
//       });



    }

}