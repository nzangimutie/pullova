package kinzangilar.systems.munzangilar.org.pullova_test;


import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import java.text.MessageFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener,
        LocationListener, View.OnClickListener {


    Location yourLocation = null;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Marker mCurrLocationMarker = null;
    private MarkerOptions markerOptions = null;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.mapSrnTitle);
        getActivity().setActionBar(toolbar);
        getActivity().getActionBar().setHomeButtonEnabled(true);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        initializeMap();
        FloatingActionButton floatingActionButton = v.findViewById(R.id.fbMyLocation);
        floatingActionButton.setOnClickListener(this);
        return v;
    }

    private void initializeMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapLocationFragment_);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mMap != null) {
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.setOnMarkerClickListener(this);
        }
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
        initializeUiSettings();
    }

    public void initializeUiSettings() {
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.getUiSettings().setTiltGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this.getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();

    }
private  LocationRequest mLocationRequest;
    @Override
    public void onConnected(@Nullable Bundle bundle) {
         mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getContext(), "Connection failed: " + connectionResult.getErrorMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        TextView text = new TextView(getContext());
        text.setText(MessageFormat.format("{0}Promo code: {1}({2},{3}", R.string.salutation, StaticVariables.ACTIVE_PROMOCODE, yourLocation.getLatitude(), yourLocation.getLongitude()));
        IconGenerator generator = new IconGenerator(getContext());
        generator.setBackground(getContext().getDrawable(R.drawable.amu_bubble_mask));
        generator.setContentView(text);
        Bitmap icon = generator.makeIcon();
        marker.setIcon(BitmapDescriptorFactory.fromBitmap(icon));
        return true;
    }


    @Override
    public void onLocationChanged(Location location) {
        mMap.clear();
        yourLocation = location;

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
         markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.draggable(true);
        markerOptions.title(String.valueOf(R.string.salutation));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fbMyLocation) {
            Toast.makeText(getContext(), "Should zoom to current Location", Toast.LENGTH_SHORT).show();
        }
    }
}
