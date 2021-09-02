package com.quickghy.qgdaksha.ui.dash.profile
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.quickghy.qgdaksha.R
import kotlinx.android.synthetic.main.mapmainactivity.*
import java.lang.Exception
import java.util.*


class MapMainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProvider: FusedLocationProviderClient
    private val permissionCode = 101


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mapmainactivity)

        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)
        fetchLocation()
    }


    private fun fetchLocation() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), permissionCode)
            return
        }

        val task = fusedLocationProvider.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null){
                currentLocation = location
                Log.d("MAP","fetch location")
                Toast.makeText(this, "lat- "+currentLocation.latitude.toString() + " long- " + currentLocation.longitude.toString(), Toast.LENGTH_SHORT).show()
                getcompleteaddress(currentLocation.latitude,currentLocation.longitude)
                val supportMapFragment = (supportFragmentManager.findFragmentById(R.id.myMap) as
                        SupportMapFragment?)!!
                supportMapFragment.getMapAsync(this)
            }
        }
    }



    override fun onMapReady(googleMap: GoogleMap) {

        var latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        val makerOptions = MarkerOptions().position(latLng).title("Hello I am here").draggable(true)
        Log.d("MAP","fetch location yyy")
        googleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f))
        googleMap?.addMarker(makerOptions)

        googleMap.setOnMapClickListener {

            googleMap.clear()
            var latLng = LatLng(it.latitude, it.longitude)
            val makerOptions = MarkerOptions().position(latLng).title("Hello I am here").draggable(true)
            getcompleteaddress(it.latitude,it.longitude)
            googleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
            googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f))
            googleMap?.addMarker(makerOptions)

        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            permissionCode -> if (grantResults.isEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                fetchLocation()
            }
        }

    }



    fun getcompleteaddress(latitude: Double, longitude: Double): Boolean {
        val geocoder = Geocoder(this, Locale.getDefault())
    try {
        val addresses: List<Address> = geocoder.getFromLocation(latitude, longitude, 1)
        val address: String = addresses[0].getAddressLine(0)
        val city: String = addresses[0].getLocality()
        val state: String = addresses[0].getAdminArea()
        val zip: String = addresses[0].getPostalCode()
        val country: String = addresses[0].getCountryName()

        Toast.makeText(this, address+city+state+zip+country , Toast.LENGTH_SHORT).show()
    }
    catch (e:Exception){
    }

        return true
    }


}