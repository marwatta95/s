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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChooseDecorationActivity extends AppCompatActivity {
    ListView listView;

    List<Decor> list;
    ProgressDialog progressDialog;
    final ArrayList<String> keyList = new ArrayList<>();
    private DatabaseReference databaseReference;
    public static final String DATABASE_PATH = "Decoration";
    MyAdapterChooseDecor myAdapter;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_choose_decoration );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        listView=(ListView) findViewById( R.id.list1);


        final Intent intent = getIntent();
        final  Intent intent1;
      //  final Bundle bundle1=new Bundle();
  //      final Bundle bundle= getIntent().getExtras();

        final Decor[] decorChosen = new Decor[1];
        //get the info from last activity

        final String act = intent.getExtras().getString( "Activity" );

        final String type = intent.getExtras().getString( "type" );
        final String date = intent.getExtras().getString( "date" );
        final String guests = intent.getExtras().getString( "guests" );
        final String location = intent.getExtras().getString( "location" );
        final String hallS=intent.getStringExtra("hallS");

        if(act.equals( "party" ))
        {
            intent1 =new Intent(ChooseDecorationActivity.this, PartyActivity.class);

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
            intent1.putExtra( "hallS", hallS );
           // intent1.putExtra( "decorS", decorS );
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
            intent1 =new Intent(ChooseDecorationActivity.this, ChooseFoodActivity.class);

            intent1.putExtra( "Activity", "hi" );

        }
        // there is an exception
 //       final Hall hallO=(Hall)(intent.getSerializableExtra("hallO"));
        // show the decorations available to the user
        list = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference(DecorActivity.DATABASE_PATH);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                list.clear();

                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    keyList.add(snap.getKey());

                    Decor decor = snap.getValue(Decor.class);
                    if(type.equals( decor.type )){
                        list.add(decor);
                    }
                }
                myAdapter = new MyAdapterChooseDecor(ChooseDecorationActivity.this,R.layout.data_items_choose_decor,list);
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
                startActivity(new Intent(ChooseDecorationActivity.this, UserHomeActivity.class));

            }
        } );
        // get what the user choose and put it in intent
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {

                decorChosen[0] =list.get(position);
                AlertDialog.Builder alert = new AlertDialog.Builder(
                        ChooseDecorationActivity.this );
                alert.setTitle( "Confirm" );
                alert.setMessage( "Are you sure you want this Decoration? " );
                alert.setPositiveButton( "YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    intent1.putExtra( "decorS", Double.toString(decorChosen[0].getPrice()) );
                //        intent1.putExtra("decorO", decorChosen);
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
               intent1.putExtra( "hallS",hallS );
            //    intent1.putExtra("bundle",bundle1);

                startActivity(intent1);




            }
        });


    }
}
