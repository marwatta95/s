package net.marwa.applicationy;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChooseHallActivity extends AppCompatActivity {
    ListView listView;
    List<Hall> extraList;

    List<Hall> list;
    String hallP;
    ProgressDialog progressDialog;
    final ArrayList<String> keyList = new ArrayList<>();
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference1;

    public static final String DATABASE_PATH = "Halls";
    MyAdapterChooseHall myAdapter;
    private Button next;
TextView fits;
    TextView noResult ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_choose_hall2 );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        listView=(ListView) findViewById( R.id.list1);
noResult=findViewById( R.id.noResult );
noResult.setVisibility( View.GONE );

        Intent intent = getIntent();
   final  Intent intent1;

        //**************************************
        final Hall[] hallChosen = new Hall[1];

        final String act = intent.getExtras().getString( "Activity" );

        final String type = intent.getExtras().getString( "type" );
        final String date = intent.getExtras().getString( "date" );
        final String guests = intent.getExtras().getString( "guests" );
        final String location = intent.getExtras().getString( "location" );

       if(act.equals( "party" ))
       {
           intent1 =new Intent(ChooseHallActivity.this, PartyActivity.class);

           final String decorS=intent.getStringExtra("decorS");
           final String appetizerS = intent.getStringExtra( "appetizerS" );
           final String mainS = intent.getStringExtra( "mainS" );
           final String dessertS = intent.getStringExtra( "dessertS" );
           final String cakeS = intent.getStringExtra( "cakeS" );
           final String photographerS = intent.getStringExtra("photoS" );
           final String singerS = intent.getStringExtra( "singerS" );
           final String djS = intent.getStringExtra( "djS" );
           final String bandS =  intent.getStringExtra( "bandS" );
           final String makeupS = intent.getStringExtra( "makeupS" );
           final String hairS = intent.getStringExtra( "hairS" );
           final String clownS=intent.getStringExtra("clownS");
           final String customS=intent.getStringExtra("customS");


           intent1.putExtra( "djS", djS );
           intent1.putExtra( "customS", customS );
           intent1.putExtra( "type", type );
           intent1.putExtra( "date", date );
           intent1.putExtra( "guests", guests );
          intent1.putExtra( "location", location );
          // intent1.putExtra( "hallS", hallS );
           intent1.putExtra( "decorS", decorS );
           intent1.putExtra( "appetizerS", appetizerS );
           intent1.putExtra( "mainS", mainS );
           intent1.putExtra( "dessertS", dessertS );
           intent1.putExtra( "cakeS", cakeS );
           intent1.putExtra("photoS",photographerS);
           intent1.putExtra( "singerS", singerS );
           intent1.putExtra( "bandS", bandS );
           intent1.putExtra( "makeupS", makeupS );
           intent1.putExtra( "hairS", hairS );
           intent1.putExtra( "clownS", clownS );
       }

      else
       {
           intent1 =new Intent(ChooseHallActivity.this, ChooseDecorationActivity.class);

           intent1.putExtra( "Activity", "hi" );

       }

        list = new ArrayList<>();
        extraList = new ArrayList<>();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference("Party");
        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();

                for(DataSnapshot snap : dataSnapshot.getChildren()){

                    Party party = snap.getValue(Party.class);
                    if (party.date.equals( date ))
                    hallP=party.hall;

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference(HallActivity.DATABASE_PATH);
        databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                list.clear();
                    extraList.clear();
                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    keyList.add(snap.getKey());

                    Hall hall = snap.getValue(Hall.class);
                    if (!hall.getName().equals( hallP ))
                    extraList.add(hall);
               //     Toast.makeText( getApplicationContext(), , Toast.LENGTH_SHORT).show();
              // if(location.equals( hall.location )) {
                    int guest = Integer.parseInt( guests );
                    if ((hall.capacity >= guest) && (hall.capacity <= guest + 50)) {

                        if (!hall.getName().equals( hallP ))
                            list.add( hall );
                }

                }

                if(list.size()==0)
                {
                    fits=(TextView) findViewById( R.id .textViewUserEmail);
                    fits.setText( "No Results!! Those are available if you would like.." );


                    myAdapter = new MyAdapterChooseHall(ChooseHallActivity.this,R.layout.data_items_choose_hall,extraList);
                    listView.setAdapter(myAdapter);

                }
                else
                {
                myAdapter = new MyAdapterChooseHall(ChooseHallActivity.this,R.layout.data_items_choose_hall,list);
                listView.setAdapter(myAdapter);}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseHallActivity.this, UserHomeActivity.class));
            }
        } );

        if(list.size()!=0) {
            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    //**********************************************************************
                    hallChosen[0] = list.get( position );
                    AlertDialog.Builder alert = new AlertDialog.Builder(
                            ChooseHallActivity.this );
                    alert.setTitle( "Confirm" );
                    alert.setMessage( "Are you sure you want this hall? " );
                    alert.setPositiveButton( "YES", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //*********************************************************
                            intent1.putExtra( "hallS", (String) hallChosen[0].getName() );
                            //            intent1.putExtra("bundle",bundle);
                            //   intent1.putExtra("hallO", hallChosen);
                            Toast.makeText( getApplicationContext(), "Chosen Successfully!!!", Toast.LENGTH_LONG ).show();
                            dialog.dismiss();

                        }
                    } );
                    alert.setNegativeButton( "NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    } );

                    alert.show();

                }
            } );
        }
        else {
            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    //**********************************************************************
                    hallChosen[0] = extraList.get( position );
                    AlertDialog.Builder alert = new AlertDialog.Builder(
                            ChooseHallActivity.this );
                    alert.setTitle( "Confirm" );
                    alert.setMessage( "Are you sure you want this hall? " );
                    alert.setPositiveButton( "YES", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //*********************************************************
                            intent1.putExtra( "hallS", (String) hallChosen[0].getName() );
                            //            intent1.putExtra("bundle",bundle);
                            //   intent1.putExtra("hallO", hallChosen);
                            Toast.makeText( getApplicationContext(), "Chosen Successfully!!!", Toast.LENGTH_LONG ).show();
                            dialog.dismiss();

                        }
                    } );
                    alert.setNegativeButton( "NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    } );

                    alert.show();

                }
            } );

        }
        next=(Button) findViewById(R.id.next);
       if(!act.equals( "hi" )){next.setText( "Back To Confirmation" );}
       next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                intent1.putExtra( "type", type );
                intent1.putExtra( "date", date );
                intent1.putExtra( "guests", guests );
                intent1.putExtra( "location", location );

                startActivity(intent1);
            }
        });


    }
}
