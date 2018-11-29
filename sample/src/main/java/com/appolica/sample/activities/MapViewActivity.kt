package com.appolica.sample.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.FragmentActivity

import com.appolica.interactiveinfowindow.InfoWindow
import com.appolica.interactiveinfowindow.InfoWindowManager
import com.appolica.sample.R
import com.appolica.sample.fragments.FormFragment
import com.appolica.sample.fragments.RecyclerViewFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_sample_with_map_view.*

class MapViewActivity : FragmentActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {



    private var infoWindowManager: InfoWindowManager? = null
    private var formWindow: InfoWindow? = null

    private var recyclerWindow: InfoWindow? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_with_map_view)

        mapView!!.onCreate(savedInstanceState)

        mapView!!.getMapAsync(this)

        infoWindowManager = InfoWindowManager(supportFragmentManager)
        infoWindowManager!!.onParentViewCreated(mapViewContainer, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        mapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView!!.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        mapView!!.onSaveInstanceState(outState)
        infoWindowManager!!.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        infoWindowManager!!.onDestroy()
        mapView!!.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        infoWindowManager!!.onMapReady(googleMap)

        val marker1 = googleMap.addMarker(MarkerOptions()
                .position(LatLng(5.0, 5.0)).snippet(RECYCLER_VIEW))

        val marker2 = googleMap.addMarker(MarkerOptions()
                .position(LatLng(1.0, 1.0)).snippet(FORM_VIEW))

        val offsetX = resources.getDimension(R.dimen.marker_offset_x).toInt()
        val offsetY = resources.getDimension(R.dimen.marker_offset_y).toInt()

        val markerSpec = InfoWindow.MarkerSpecification(offsetX, offsetY)

        recyclerWindow = InfoWindow(marker1, markerSpec, RecyclerViewFragment())
        formWindow = InfoWindow(marker2, markerSpec, FormFragment())

        googleMap.setOnMarkerClickListener(this)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        var infoWindow: InfoWindow? = null
        when (marker.snippet) {
            RECYCLER_VIEW -> infoWindow = recyclerWindow
            FORM_VIEW -> infoWindow = formWindow
        }

        if (infoWindow != null) {
            infoWindowManager!!.toggle(infoWindow, true)
        }

        return true
    }

    companion object {

        private val RECYCLER_VIEW = "RECYCLER_VIEW_MARKER"
        private val FORM_VIEW = "FORM_VIEW_MARKER"
    }
}
