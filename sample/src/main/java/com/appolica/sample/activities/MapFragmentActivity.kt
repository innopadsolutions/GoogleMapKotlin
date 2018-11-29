package com.appolica.sample.activities

import android.os.Bundle
import android.support.v4.app.FragmentActivity

import com.appolica.interactiveinfowindow.InfoWindow
import com.appolica.interactiveinfowindow.InfoWindowManager
import com.appolica.interactiveinfowindow.fragment.MapInfoWindowFragment
import com.appolica.sample.R
import com.appolica.sample.fragments.FormFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapFragmentActivity : FragmentActivity(), InfoWindowManager.WindowShowListener, GoogleMap.OnMarkerClickListener {

    private var recyclerWindow: InfoWindow? = null
    private var formWindow: InfoWindow? = null
    private var formWindow1: InfoWindow? = null
    private var formWindow2: InfoWindow? = null
    private var infoWindowManager: InfoWindowManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_with_map_fragment)

        val mapInfoWindowFragment = supportFragmentManager
                .findFragmentById(R.id.infoWindowMap) as MapInfoWindowFragment?

        infoWindowManager = mapInfoWindowFragment!!.infoWindowManager()
        infoWindowManager!!.setHideOnFling(true)

        mapInfoWindowFragment.getMapAsync { googleMap ->
            val marker1 = googleMap.addMarker(MarkerOptions()
                    .position(LatLng(29.696620, -95.723020)).snippet(RECYCLER_VIEW))

            val marker2 = googleMap.addMarker(MarkerOptions()
                    .position(LatLng(29.743290, -95.775960)).snippet(FORM_VIEW))

            val marker3 = googleMap.addMarker(MarkerOptions()
                    .position(LatLng(29.761710, -95.752140)).snippet(FORM_VIEW1))


            val marker4 = googleMap.addMarker(MarkerOptions()
                    .position(LatLng(29.750900, -95.752430)).snippet(FORM_VIEW2))

            val offsetX = resources.getDimension(R.dimen.marker_offset_x).toInt()
            val offsetY = resources.getDimension(R.dimen.marker_offset_y).toInt()

            val markerSpec = InfoWindow.MarkerSpecification(offsetX, offsetY)

            recyclerWindow = InfoWindow(marker1, markerSpec, FormFragment())
            formWindow = InfoWindow(marker2, markerSpec, FormFragment())
            formWindow1 = InfoWindow(marker3, markerSpec, FormFragment())
            formWindow2 = InfoWindow(marker4, markerSpec, FormFragment())


            val latLng = LatLng(29.750900, -95.752430)
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))

            googleMap.setOnMarkerClickListener(this@MapFragmentActivity)
        }

        infoWindowManager!!.setWindowShowListener(this@MapFragmentActivity)

    }

    override fun onMarkerClick(marker: Marker): Boolean {
        var infoWindow: InfoWindow? = null
        when (marker.snippet) {
            RECYCLER_VIEW -> infoWindow = recyclerWindow
            FORM_VIEW -> infoWindow = formWindow
            FORM_VIEW1 -> infoWindow = formWindow1
            FORM_VIEW2 -> infoWindow = formWindow2
        }

        if (infoWindow != null) {
            infoWindowManager!!.toggle(infoWindow, true)
        }

        return true
    }

    override fun onWindowShowStarted(infoWindow: InfoWindow) {
        //        Log.d("debug", "onWindowShowStarted: " + infoWindow);
    }

    override fun onWindowShown(infoWindow: InfoWindow) {
        //        Log.d("debug", "onWindowShown: " + infoWindow);
    }

    override fun onWindowHideStarted(infoWindow: InfoWindow) {
        //        Log.d("debug", "onWindowHideStarted: " + infoWindow);
    }

    override fun onWindowHidden(infoWindow: InfoWindow) {
        //        Log.d("debug", "onWindowHidden: " + infoWindow);
    }

    companion object {

        private val RECYCLER_VIEW = "RECYCLER_VIEW_MARKER"
        private val FORM_VIEW = "FORM_VIEW_MARKER"
        private val FORM_VIEW1 = "FORM_VIEW_MARKER1"
        private val FORM_VIEW2 = "FORM_VIEW_MARKER2"
    }
}
