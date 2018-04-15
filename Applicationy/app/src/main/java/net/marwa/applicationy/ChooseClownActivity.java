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
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChooseClownActivity extends AppCompatActivity {
    ListView listView;
    List<Clown> list;
String clownP;
ProgressDialog progressDialog;
    final ArrayList<String> keyList = new ArrayList<>();
    private DatabaseReference databaseReference;
    MyAdapterChooseClown myAdapter;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_choose_clown);
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        listView=(ListView) findViewById( R.id.list1);


        Intent intent = getIntent();
        final  Intent intent1;
        final String act = intent.getExtras().getString( "Activity" );

        final String type = intent.getExtras().getString( "type" );
        final String date = intent.getExtras().getString( "date" );
        final String guests = intent.getExtras().getString( "guests" );
        final String location = intent.getExtras().getString( "location" );
        final String hallS=intent.getStringExtra("hallS");
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

        final Clown[] clownChoosen=new Clown[1];


        if(act.equals( "party" ))
        {
            intent1 =new Intent(ChooseClownActivity.this, PartyActivity.class);


            final String customS=intent.getStringExtra("customS");


            intent1.putExtra( "djS", djS );
            intent1.putExtra( "customS", customS );
            intent1.putExtra( "type", type );
            intent1.putExtra( "date", date );
            intent1.putExtra( "guests", guests );
            intent1.putExtra( "location", location );
            intent1.putExtra( "hallS", hallS );
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
          //  intent1.putExtra( "clownS", clownS );
        }

        else
        {


                intent1 =new Intent(ChooseClownActivity.this, ChooseCustomActivity.class);


            intent1.putExtra( "Activity", "hi" );}

      /*  final Hall hallO=(Hall)intent.getSerializableExtra("hallO");
        final Decor decorO=(Decor)intent.getSerializableExtra("decorO");
        final Food appetizerO=(Food)intent.getSerializableExtra("appetizerO");
        final Food mainO=(Food)intent.getSerializableExtra("mainO");
        final Food dessertO=(Food)intent.getSerializableExtra("dessertO");
        final Food cakeO=(Food)intent.getSerializableExtra("cakeO");
        final Photographer photographerO=(Photographer)intent.getSerializableExtra("photographerO");
        final Dj  djO=(Dj)intent.getSerializableExtra("djO");
        final Band  bandO=(Band) intent.getSerializableExtra("bandO");
        final Singer  singerO=(Singer) intent.getSerializableExtra("singerO");
        final MakeUp makeupo=(MakeUp)intent.getSerializableExtra("makeupO");
        final Hair hairO=(Hair)intent.getSerializableExtra("hairO");*/


        list = new ArrayList<>();

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
                     clownP=party.clown;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference(ClownActivity.DATABASE_PATH);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                list.clear();

                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    keyList.add(snap.getKey());

                    Clown clown = snap.getValue(Clown.class);
                    if (!clown.getName().equals( clownP ))
                        list.add( clown );


                }
                myAdapter = new MyAdapterChooseClown(ChooseClownActivity.this,R.layout.data_items_choose_clown,list);
                listView.setAdapter(myAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseClownActivity.this, UserHomeActivity.class));

            }
        } );
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                clownChoosen[0]=list.get(position);
                AlertDialog.Builder alert = new AlertDialog.Builder(
                        ChooseClownActivity.this );
                alert.setTitle( "Confirm" );
                alert.setMessage( "Are you sure you want this? " );
                alert.setPositiveButton( "YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intent1.putExtra( "clownS",(String) clownChoosen[0].getName() );
                   //     intent1.putExtra( "clownO", databaseReference.getRoot().child( ClownActivity.DATABASE_PATH ).child( keyList.get( position ) ).getClass() );

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
        });
        next=(Button) findViewById(R.id.next);
        if(!act.equals( "hi" )){next.setText( "Back To Confirmation" );}

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                intent1.putExtra( "type", type );
                intent1.putExtra( "date", date );
                intent1.putExtra( "guests", guests );
                intent1.putExtra( "location", location );
                intent1.putExtra( "hallS", hallS );
                intent1.putExtra( "decorS", decorS );
                intent1.putExtra( "appetizerS", appetizerS );
                intent1.putExtra( "mainS", mainS );
                intent1.putExtra( "dessertS", dessertS );
                intent1.putExtra( "cakeS", cakeS );
                intent1.putExtra("photoS",photographerS);
                intent1.putExtra( "singerS", singerS );
                intent1.putExtra( "djS", djS );
                intent1.putExtra( "bandS", bandS );
                intent1.putExtra( "makeupS", makeupS );
                intent1.putExtra( "hairS", hairS );

           /*     intent1.putExtra("hallO",hallO);
                intent1.putExtra("decorO",decorO);
                intent1.putExtra( "appetizerO", appetizerO );
                intent1.putExtra( "mainO", mainO );
                intent1.putExtra( "dessertO", dessertO );
                intent1.putExtra( "cakeO", cakeO );
                intent1.putExtra("photographerO",photographerO);
                intent1.putExtra("djO",djO);
                intent1.putExtra("singerO",singerO);
                intent1.putExtra("bandO",bandO);
                intent1.putExtra("makeupO",makeupo);
                intent1.putExtra("hairO",hairO);*/








                startActivity(intent1);




            }
        });


    }

}
