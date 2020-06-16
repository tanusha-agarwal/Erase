package com.example.erase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Place extends AppCompatActivity {
    public String name;
    public LatLng latLng;

    public Place(String s, LatLng latLng) {
        this.name = s;
        this.latLng = latLng;
    }
    public Place(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Intent i = getIntent();
        final Double lat, lon;
        lat = Double.valueOf(i.getStringExtra("lat"));
        lon = Double.valueOf(i.getStringExtra("long"));


        LatLng myLocation = new LatLng(lat, lon);
        final ArrayList<Place> place = new ArrayList<>();
        place.add(new Place("Attero Recycling Pvt. Ltd", new LatLng(26.785430,80.915830)));
        place.add(new Place("Green Star e-Waste Recycling India", new LatLng(16.505340, 80.661210)));
        place.add(new Place("E- Parisaraa Private Limited", new LatLng(13.011250, 77.509230)));
        place.add(new Place("Virogreen India Pvt Ltd", new LatLng(36.305408, -78.407404)));
        place.add(new Place("E Waste Recyclers India", new LatLng(28.553619, 77.268646)));
        place.add(new Place("E Waste Recycling India", new LatLng(19.101090, 72.875449)));
        place.add(new Place("Namo eWaste Management Ltd.", new LatLng(28.452880, 77.308090)));
        place.add(new Place("Green India E-Waste & Recycling Opc Pvt. Ltd.", new LatLng(19.059310, 73.001510)));
        place.add(new Place("Pruthvi e-recycle Pvt. Ltd.", new LatLng(23.010800, 72.518110)));
        place.add(new Place("Ecoreco", new LatLng(18.969050, 72.821180)));

        Collections.sort(place, new SortPlaces(myLocation));


        ArrayList<String> place_name = new ArrayList<>();
        for (Place x : place) {
            place_name.add(x.name);
        }
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.activity_place, R.id.textView, place_name);
        ListView listView = findViewById(R.id.place_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                LatLng latLng = place.get(i).latLng;
                double latitude = latLng.latitude;
                double longitude = latLng.longitude;
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=loc:%f,%f", latitude,longitude);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });
    }
}

