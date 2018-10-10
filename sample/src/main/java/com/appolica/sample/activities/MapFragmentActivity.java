package com.appolica.sample.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.appolica.interactiveinfowindow.InfoWindow;
import com.appolica.interactiveinfowindow.InfoWindowManager;
import com.appolica.interactiveinfowindow.fragment.MapInfoWindowFragment;
import com.appolica.sample.R;
import com.appolica.sample.fragments.FormFragment;
import com.appolica.sample.fragments.RecyclerViewFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragmentActivity
        extends FragmentActivity
        implements InfoWindowManager.WindowShowListener,
        GoogleMap.OnMarkerClickListener {

    private static final String RECYCLER_VIEW = "RECYCLER_VIEW_MARKER";
    private static final String FORM_VIEW = "FORM_VIEW_MARKER";
    private static final String FORM_VIEW1 = "FORM_VIEW_MARKER1";
    private static final String FORM_VIEW2 = "FORM_VIEW_MARKER2";

    private InfoWindow recyclerWindow;
    private InfoWindow formWindow;
    private InfoWindow formWindow1;
    private InfoWindow formWindow2;
    private InfoWindowManager infoWindowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_with_map_fragment);

        final MapInfoWindowFragment mapInfoWindowFragment =
                (MapInfoWindowFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.infoWindowMap);

        infoWindowManager = mapInfoWindowFragment.infoWindowManager();
        infoWindowManager.setHideOnFling(true);

        mapInfoWindowFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                final Marker marker1 = googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(29.696620, -95.723020)).snippet(RECYCLER_VIEW));

                final Marker marker2 = googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(29.743290, -95.775960)).snippet(FORM_VIEW));

                final Marker marker3 = googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(29.761710, -95.752140)).snippet(FORM_VIEW1));


                final Marker marker4 = googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(29.750900, -95.752430)).snippet(FORM_VIEW2));

                final int offsetX = (int) getResources().getDimension(R.dimen.marker_offset_x);
                final int offsetY = (int) getResources().getDimension(R.dimen.marker_offset_y);

                final InfoWindow.MarkerSpecification markerSpec =
                        new InfoWindow.MarkerSpecification(offsetX, offsetY);

                recyclerWindow = new InfoWindow(marker1, markerSpec, new FormFragment());
                formWindow = new InfoWindow(marker2, markerSpec, new FormFragment());
                formWindow1 = new InfoWindow(marker3, markerSpec, new FormFragment());
                formWindow2 = new InfoWindow(marker4, markerSpec, new FormFragment());


                LatLng latLng = new LatLng(29.750900, -95.752430);
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10F));

                googleMap.setOnMarkerClickListener(MapFragmentActivity.this);
            }
        });

        infoWindowManager.setWindowShowListener(MapFragmentActivity.this);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        InfoWindow infoWindow = null;
        switch (marker.getSnippet()) {
            case RECYCLER_VIEW:
                infoWindow = recyclerWindow;
                break;
            case FORM_VIEW:
                infoWindow = formWindow;
                break;
            case FORM_VIEW1:
                infoWindow = formWindow1;
                break;
            case FORM_VIEW2:
                infoWindow = formWindow2;
                break;
        }

        if (infoWindow != null) {
            infoWindowManager.toggle(infoWindow, true);
        }

        return true;
    }

    @Override
    public void onWindowShowStarted(@NonNull InfoWindow infoWindow) {
//        Log.d("debug", "onWindowShowStarted: " + infoWindow);
    }

    @Override
    public void onWindowShown(@NonNull InfoWindow infoWindow) {
//        Log.d("debug", "onWindowShown: " + infoWindow);
    }

    @Override
    public void onWindowHideStarted(@NonNull InfoWindow infoWindow) {
//        Log.d("debug", "onWindowHideStarted: " + infoWindow);
    }

    @Override
    public void onWindowHidden(@NonNull InfoWindow infoWindow) {
//        Log.d("debug", "onWindowHidden: " + infoWindow);
    }
}
