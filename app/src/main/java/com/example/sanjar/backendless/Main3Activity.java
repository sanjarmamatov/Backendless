

package apps.thirtyseven.newfloat;

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
                                textVi
                                int size = foundAnnouncement.getData().size();
                                for(int i = 0; i<size; i++){
                                    Announcement content = foundAnnouncement.getData().get(i);


                                    // при o
                                    textView.append("улица: " + content.getStreet()+"\n" + "дом: " + content.getHouse() + "\n" + "этаж: "+content.getFloor() + "\n" + "кол-во комнат: "+ content.getRoomCount() + "\n" + "цена: " +content.getHomePrice()+"$" + "\n" +"--------------------" + "\n");
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




*/
/*

package com.example.sanjar.backendless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

public class Main3Activity extends AppCompatActivity {

    EditText street, flatNum, aparType, floorCnt, roomCnt;
    Button find;
    TextView txtView;
    private ListView listView;
    private ArrayAdapter arrayAdapter;
    String listViewArr[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        initVars();



        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userStreet = street.getText().toString();
                String userFlatNum = flatNum.getText().toString();
                String userAparType = aparType.getText().toString();
                String userFloor = floorCnt.getText().toString();
                String userRoomCount = roomCnt.getText().toString();
                String whereClause = "street LIKE '%" + userStreet + "%' OR flatNumber = '" + userFlatNum + "'" +
                        "OR apartmentType LIKE '%" + userAparType + "%' OR floorCount = " + userFloor +
                        "OR roomsCount = " + userRoomCount;
                BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                dataQuery.setWhereClause(whereClause);


                Backendless.Persistence.of(Sorting.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Sorting>>() {
                    @Override
                    public void handleResponse(BackendlessCollection<Sorting> sorting) {
                        int size = sorting.getData().size();
                        listViewArr = new String[size];
                        for (int i = 0; i < size; i++) {
                            */
/*txtSorting.append(sorting.getData().get(i).getStreet() + "\n" + sorting.getData().get(i).getAppartamentType() + "\n" + sorting.getData().get(i).getFlatStyle() + "\n" + sorting.getData().get(i).getPrice().toString() + "\n" + sorting.getData().get(i).getRoomsCount().toString() + "\n");*//*

                            listViewArr[i] = sorting.getData().get(i).getStreet();
                        }
                        arrayAdapter = new ArrayAdapter(Main3Activity.this, android.R.layout.simple_list_item_1, listViewArr);
                        listView.setAdapter(arrayAdapter);
                    }

                    @Override
                    public void handleFault(BackendlessFault backendlessFault) {

                    }
                });













*/
/*

                Backendless.Persistence.of(Sorting.class).find(dataQuery,
                        new AsyncCallback<BackendlessCollection<Sorting>>() {
                            @Override
                            public void handleResponse(BackendlessCollection<Sorting> foundContacts) {
                                int size = foundContacts.getData().size();

                                for (int i = 0; i < size; i++) {
                                    Sorting content = foundContacts.getData().get(i);
                                    txtView.append("\n" + content.getStreet() + "\n" + content.getApartmentType() + "\n"
                                            + content.getFlatNumber() + "\n" + content.getFloorCount() + "\n" + content.getRoomsCount());



                                    arrayAdapter = new ArrayAdapter(Main3Activity.this, android.R.layout.simple_list_item_1, listViewArr);
                                    listView.setAdapter(arrayAdapter);

                                }
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                // an error has occurred, the error code can be retrieved with fault.getCode()
                            }
                        });*//*

            }
        });

    }

    private void initVars() {
        street = (EditText) findViewById(R.id.streetAct3);
        flatNum = (EditText) findViewById(R.id.numberFlatAct3);
        aparType = (EditText) findViewById(R.id.typeAct3);
        floorCnt = (EditText) findViewById(R.id.floorCountAct3);
        roomCnt = (EditText) findViewById(R.id.roomCountAct3);
        txtView = (TextView) findViewById(R.id.textView);
        find = (Button) findViewById(R.id.result);
        listView = (ListView) findViewById(R.id.listView2);
    }
}


*/
