package com.example.erase;

import com.google.android.gms.maps.model.LatLng;

import java.util.Comparator;

public class SortPlaces implements Comparator<Place> {

    LatLng current;
    public SortPlaces(LatLng myLocation) {
        current = myLocation;
    }
    @Override
    public int compare(final Place place1, final Place place2) {
        double lat1 = place1.latLng.latitude;
        double lon1 = place1.latLng.longitude;
        double lat2 = place2.latLng.latitude;
        double lon2 = place2.latLng.longitude;

        double distanceToPlace1 = distance(current.latitude, current.longitude, lat1, lon1);
        double distanceToPlace2 = distance(current.latitude, current.longitude, lat2, lon2);
        return (int) (distanceToPlace1 - distanceToPlace2);
    }

    public double distance(double fromLat, double fromLon, double toLat, double toLon) {
        double radius = 6378137;   // approximate Earth radius, *in meters*
        double deltaLat = toLat - fromLat;
        double deltaLon = toLon - fromLon;
        double angle = 2 * Math.asin( Math.sqrt(
                Math.pow(Math.sin(deltaLat/2), 2) +
                        Math.cos(fromLat) * Math.cos(toLat) *
                                Math.pow(Math.sin(deltaLon/2), 2) ) );
        return radius * angle;
    }
}
