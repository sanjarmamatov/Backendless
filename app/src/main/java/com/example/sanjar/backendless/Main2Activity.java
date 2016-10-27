package com.example.sanjar.backendless;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.example.sanjar.backendless.Announcement;

public class SecondActivity extends AppCompatActivity {
    BackendlessUser user = new BackendlessUser();
    EditText street, house, roomCount, floor, homePrice;
    Button button, buttonNext, searchButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = (TextView) findViewById(R.id.textView3);
        street = (EditText) findViewById(R.id.streetText);
        house = (EditText) findViewById(R.id.houseText);
        roomCount = (EditText) findViewById(R.id.roomCountText);
        floor = (EditText) findViewById(R.id.floorText);
        homePrice = (EditText) findViewById(R.id.homePriceText);
        button = (Button) findViewById(R.id.button2);
        buttonNext = (Button) findViewById(R.id.button3);
        searchButton = (Button) findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                lat;
                lng;

                String whereClause = "latitude = " + lat + " AND longtitude = "+lng;


                String whereClause ="street '"+ String.valueOf(street.getText()) + "%' OR floor = " + String.valueOf(floor.getText()) +
                        " OR house '" + String.valueOf(house.getText()) + "%' OR homePrice <=" + String.valueOf(homePrice.getText()) +
                        " OR roomCount = " + String.valueOf(roomCount.getText());
                textView.setText(whereClause);



                BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                dataQuery.setWhereClause( whereClause );
                Backendless.Persistence.of( Announcement.class ).find( dataQuery,

                        new AsyncCallback<BackendlessCollection<Announcement>>(){
                            @Override
                            public void handleResponse( BackendlessCollection<Announcement> foundAnnouncement )
                            {
                                Backendless.Persistence.of( Announcement.class ).remove( savedContact,
                                        new AsyncCallback<Long>()
                                        {
                                            public void handleResponse( Long response )
                                            {
                                                // Contact has been deleted. The response is the
                                                // time in milliseconds when the object was deleted
                                            }
                                            public void handleFault( BackendlessFault fault )
                                            {
                                                // an error has occurred, the error code can be
                                                // retrieved with fault.getCode()
                                            }
                                        } );
                                 }  // the "foundContact" collection now contains instances of the Contact class.
                                // each instance represents an object stored on the server.
                            }
                            @Override
                            public void handleFault( BackendlessFault fault )
                            {
                                textView.setText("Error!1!! " + fault.getCode());
                                // an error has occurred, the error code can be retrieved with fault.getCode()
                            }
                        });
            }
        });

        buttonNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(j);
            }
        });

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Announcement announcement = new Announcement();
                announcement.setFloor(Integer.valueOf(floor.getText().toString()));
                announcement.setHouse(String.valueOf(house.getText()));
                announcement.setHomePrice(Integer.valueOf(homePrice.getText().toString()));
                announcement.setRoomCount(Integer.valueOf(roomCount.getText().toString()));
                announcement.setStreet(String.valueOf(street.getText()));

                // save object synchronously
                //  Announcement savedAnnouncement = Backendless.Persistence.save(announcement);

                // save object asynchronously
                Backendless.Persistence.save(announcement, new AsyncCallback<Announcement>() {
                    public void handleResponse(Announcement response) {
                        Toast.makeText(getApplication(), "Saved!1!!", Toast.LENGTH_LONG).show();
                    }

                    public void handleFault(BackendlessFault fault) {
                        // an error has occurred, the error code can be retrieved with fault.getCode()
                    }
                });
            }
        });
    }
}
























/*
package com.example.sanjar.backendless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.BackendlessCallback;

public class Main2Activity extends AppCompatActivity {

    EditText street, flatNum, aparType, floorCnt, roomCnt;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initVars();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                final String emailIntent = intent.getStringExtra("email");

                int floor = Integer.parseInt(floorCnt.getText().toString());
                int rmCnt = Integer.parseInt(roomCnt.getText().toString());

                Backendless.Persistence.save(new Sorting(street.getText().toString(),
                        aparType.getText().toString(), flatNum.getText().toString(),
                        emailIntent, floor, rmCnt), new BackendlessCallback<Sorting>() {
                    @Override
                    public void handleResponse(Sorting sorting) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Ваши данные сохранены.", Toast.LENGTH_LONG);
                        toast.show();

                        Intent intent1 = new Intent(Main2Activity.this, Main3Activity.class);
                        startActivity(intent1);
                    }
                });
            }
        });


    }

    private void initVars() {
        street = (EditText) findViewById(R.id.strt);
        flatNum = (EditText) findViewById(R.id.number);
        aparType = (EditText) findViewById(R.id.type);
        floorCnt = (EditText) findViewById(R.id.floor);
        roomCnt = (EditText) findViewById(R.id.count);
        save = (Button) findViewById(R.id.save);
    }
}*/
