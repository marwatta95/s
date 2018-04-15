package net.marwa.applicationy;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.provider.SyncStateContract;
import android.support.v4.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.internal.zzh;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.google.firebase.database.FirebaseDatabase.*;

public class PartyActivity extends AppCompatActivity {

    NotificationCompat.Builder notification;
    private static final int uniqueID=3454;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private  Button confirm;
    private Button cancel;
    private TextView hall;
    private TextView decor;
    private TextView photographer;
    private TextView music;
    private TextView clown;
    private TextView custom;
    private TextView food1;
    private TextView food2;
    private TextView food3;
    private TextView food4;
    private TextView makeup;
    private TextView hair;
    private DatabaseReference dr;

    private  Party party;
    private UserNotification uN;
    private Head yourHead;
    List<Head> list;
    List<Hall> list1;
    private StorageReference storageReference;
   Hall h;
Hall hallO;
    public   static final String uu="Notification";

 public   static final String aa="Party";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);

        notification=new NotificationCompat.Builder( this,"default" );
        notification.setAutoCancel( true );
        firebaseAuth = FirebaseAuth.getInstance();
// showing the party information

        Intent intent = getIntent();
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
        final String clownS=intent.getStringExtra("clownS");
        final String customS=intent.getStringExtra("customS");

        list = new ArrayList<>();
        list1 = new ArrayList<>();

        hall = (TextView)findViewById(R.id.textViewHall);
        hall.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {

              Intent in=new Intent(getApplicationContext(), ChooseHallActivity.class);
              in.putExtra( "Activity","party" );

                in.putExtra( "type", type );
                in.putExtra( "date", date );
                in.putExtra( "guests", guests );
                in.putExtra( "location", location );
              //  in.putExtra( "hallS", hallS );
                in.putExtra( "decorS", decorS );
                in.putExtra( "appetizerS", appetizerS );
                in.putExtra( "mainS", mainS );
                in.putExtra( "dessertS", dessertS );
                in.putExtra( "cakeS", cakeS );
                in.putExtra("photoS",photographerS);
                in.putExtra( "singerS", singerS );
                in.putExtra( "djS", djS );
                in.putExtra( "bandS", bandS );
                in.putExtra( "makeupS", makeupS );
                in.putExtra( "hairS", hairS );
                in.putExtra( "clownS", clownS );
                in.putExtra( "customS", customS );


                startActivity(in);
            }
        });
        decor = (TextView)findViewById(R.id.textViewDecor);
        decor.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {

                Intent in=new Intent(getApplicationContext(), ChooseDecorationActivity.class);
                in.putExtra( "Activity","party" );
                in.putExtra( "type", type );
                in.putExtra( "date", date );
                in.putExtra( "guests", guests );
                in.putExtra( "location", location );
                in.putExtra( "hallS", hallS );
               // in.putExtra( "decorS", decorS );
                in.putExtra( "appetizerS", appetizerS );
                in.putExtra( "mainS", mainS );
                in.putExtra( "dessertS", dessertS );
                in.putExtra( "cakeS", cakeS );
                in.putExtra("photoS",photographerS);
                in.putExtra( "singerS", singerS );
                in.putExtra( "djS", djS );
                in.putExtra( "bandS", bandS );
                in.putExtra( "makeupS", makeupS );
                in.putExtra( "hairS", hairS );
                in.putExtra( "clownS", clownS );
                in.putExtra( "customS", customS );

                startActivity(in);
            }
        });
        food1 = (TextView)findViewById(R.id.textViewFood1);
        food1.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {

                Intent in=new Intent(getApplicationContext(), ChooseFoodActivity.class);
                in.putExtra( "Activity","party" );
                in.putExtra( "type", type );
                in.putExtra( "date", date );
                in.putExtra( "guests", guests );
                in.putExtra( "location", location );
                in.putExtra( "hallS", hallS );
                in.putExtra( "decorS", decorS );
               // in.putExtra( "appetizerS", appetizerS );
                in.putExtra( "mainS", mainS );
                in.putExtra( "dessertS", dessertS );
                in.putExtra( "cakeS", cakeS );
                in.putExtra("photoS",photographerS);
                in.putExtra( "singerS", singerS );
                in.putExtra( "djS", djS );
                in.putExtra( "bandS", bandS );
                in.putExtra( "makeupS", makeupS );
                in.putExtra( "hairS", hairS );
                in.putExtra( "clownS", clownS );
                in.putExtra( "customS", customS );

                startActivity(in);
            }
        });
        food2 = (TextView)findViewById(R.id.textViewFood2);
        food2.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {

                Intent in=new Intent(getApplicationContext(), ChooseFoodActivity.class);
                in.putExtra( "Activity","party" );
                in.putExtra( "type", type );
                in.putExtra( "date", date );
                in.putExtra( "guests", guests );
                in.putExtra( "location", location );
                in.putExtra( "hallS", hallS );
                in.putExtra( "decorS", decorS );
                in.putExtra( "appetizerS", appetizerS );
                in.putExtra( "mainS", mainS );
              //  in.putExtra( "dessertS", dessertS );
                in.putExtra( "cakeS", cakeS );
                in.putExtra("photoS",photographerS);
                in.putExtra( "singerS", singerS );
                in.putExtra( "djS", djS );
                in.putExtra( "bandS", bandS );
                in.putExtra( "makeupS", makeupS );
                in.putExtra( "hairS", hairS );
                in.putExtra( "clownS", clownS );
                in.putExtra( "customS", customS );

                startActivity(in);
            }
        });
        food3 = (TextView)findViewById(R.id.textViewFood3);
        food3.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {

                Intent in=new Intent(getApplicationContext(), ChooseFoodActivity.class);
                in.putExtra( "Activity","party" );

                in.putExtra( "type", type );
                in.putExtra( "date", date );
                in.putExtra( "guests", guests );
                in.putExtra( "location", location );
                in.putExtra( "hallS", hallS );
                in.putExtra( "decorS", decorS );
                in.putExtra( "appetizerS", appetizerS );
               // in.putExtra( "mainS", mainS );
                in.putExtra( "dessertS", dessertS );
                in.putExtra( "cakeS", cakeS );
                in.putExtra("photoS",photographerS);
                in.putExtra( "singerS", singerS );
                in.putExtra( "djS", djS );
                in.putExtra( "bandS", bandS );
                in.putExtra( "makeupS", makeupS );
                in.putExtra( "hairS", hairS );
                in.putExtra( "clownS", clownS );
                in.putExtra( "customS", customS );

                startActivity(in);
            }
        });
        food4 = (TextView)findViewById(R.id.textViewFood4);
        food4.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {

                Intent in=new Intent(getApplicationContext(), ChooseFoodActivity.class);
                in.putExtra( "Activity","party" );
                in.putExtra( "type", type );
                in.putExtra( "date", date );
                in.putExtra( "guests", guests );
                in.putExtra( "location", location );
                in.putExtra( "hallS", hallS );
                in.putExtra( "decorS", decorS );
                in.putExtra( "appetizerS", appetizerS );
                in.putExtra( "mainS", mainS );
                in.putExtra( "dessertS", dessertS );
              //  in.putExtra( "cakeS", cakeS );
                in.putExtra("photoS",photographerS);
                in.putExtra( "singerS", singerS );
                in.putExtra( "djS", djS );
                in.putExtra( "bandS", bandS );
                in.putExtra( "makeupS", makeupS );
                in.putExtra( "hairS", hairS );
                in.putExtra( "clownS", clownS );
                in.putExtra( "customS", customS );

                startActivity(in);
            }
        });

        photographer = (TextView)findViewById(R.id.textViewPhoto);
        photographer.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {

                Intent in=new Intent(getApplicationContext(), ChoosePhotoActivity.class);
                in.putExtra( "Activity","party" );
                in.putExtra( "type", type );
                in.putExtra( "date", date );
                in.putExtra( "guests", guests );
                in.putExtra( "location", location );
                in.putExtra( "hallS", hallS );
                in.putExtra( "decorS", decorS );
                in.putExtra( "appetizerS", appetizerS );
                in.putExtra( "mainS", mainS );
                in.putExtra( "dessertS", dessertS );
                in.putExtra( "cakeS", cakeS );
               // in.putExtra("photoS",photographerS);
                in.putExtra( "singerS", singerS );
                in.putExtra( "djS", djS );
                in.putExtra( "bandS", bandS );
                in.putExtra( "makeupS", makeupS );
                in.putExtra( "hairS", hairS );
                in.putExtra( "clownS", clownS );
                in.putExtra( "customS", customS );

                startActivity(in);
            }
        });
        clown = (TextView) findViewById(R.id.textViewClown);
        clown.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {

                Intent in=new Intent(getApplicationContext(), ChooseClownActivity.class);
                in.putExtra( "Activity","party" );
                in.putExtra( "type", type );
                in.putExtra( "date", date );
                in.putExtra( "guests", guests );
                in.putExtra( "location", location );
                in.putExtra( "hallS", hallS );
                in.putExtra( "decorS", decorS );
                in.putExtra( "appetizerS", appetizerS );
                in.putExtra( "mainS", mainS );
                in.putExtra( "dessertS", dessertS );
                in.putExtra( "cakeS", cakeS );
                in.putExtra("photoS",photographerS);
                in.putExtra( "singerS", singerS );
                in.putExtra( "djS", djS );
                in.putExtra( "bandS", bandS );
                in.putExtra( "makeupS", makeupS );
                in.putExtra( "hairS", hairS );
                //in.putExtra( "clownS", clownS );
                in.putExtra( "customS", customS );

                startActivity(in);
            }
        });
        music = (TextView)findViewById(R.id.textViewMusic);
        music.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {

                Intent in=new Intent(getApplicationContext(), ChooseMusicActivity.class);
                in.putExtra( "Activity","party" );

                in.putExtra( "type", type );
                in.putExtra( "date", date );
                in.putExtra( "guests", guests );
                in.putExtra( "location", location );
                in.putExtra( "hallS", hallS );
                in.putExtra( "decorS", decorS );
                in.putExtra( "appetizerS", appetizerS );
                in.putExtra( "mainS", mainS );
                in.putExtra( "dessertS", dessertS );
                in.putExtra( "cakeS", cakeS );
                in.putExtra("photoS",photographerS);
              //  in.putExtra( "singerS", singerS );
               // in.putExtra( "djS", djS );
              //  in.putExtra( "bandS", bandS );
                in.putExtra( "makeupS", makeupS );
                in.putExtra( "hairS", hairS );
                in.putExtra( "clownS", clownS );
                in.putExtra( "customS", customS );

                startActivity(in);
            }
        });
        makeup=(TextView)findViewById(R.id.textViewMakeup);
        makeup.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {

                Intent in=new Intent(getApplicationContext(), ChooseMakeupActivity.class);
                in.putExtra( "Activity","party" );

                in.putExtra( "type", type );
                in.putExtra( "date", date );
                in.putExtra( "guests", guests );
                in.putExtra( "location", location );
                in.putExtra( "decorS", decorS );
                in.putExtra( "appetizerS", appetizerS );
                in.putExtra( "mainS", mainS );
                in.putExtra( "dessertS", dessertS );
                in.putExtra( "cakeS", cakeS );
                in.putExtra("photoS",photographerS);
                in.putExtra( "singerS", singerS );
                in.putExtra( "djS", djS );
                in.putExtra( "bandS", bandS );
             //   in.putExtra( "makeupS", makeupS );
                in.putExtra( "hairS", hairS );
                in.putExtra( "clownS", clownS );

                startActivity(in);
            }
        });
        hair=(TextView)findViewById(R.id.textViewHairDresser);
        hair.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {

                Intent in=new Intent(getApplicationContext(), ChooseHairActivity.class);
                in.putExtra( "Activity","party" );
                in.putExtra( "type", type );
                in.putExtra( "date", date );
                in.putExtra( "guests", guests );
                in.putExtra( "location", location );
                in.putExtra( "hallS", hallS );
                in.putExtra( "decorS", decorS );
                in.putExtra( "appetizerS", appetizerS );
                in.putExtra( "mainS", mainS );
                in.putExtra( "dessertS", dessertS );
                in.putExtra( "cakeS", cakeS );
                in.putExtra("photoS",photographerS);
                in.putExtra( "singerS", singerS );
                in.putExtra( "djS", djS );
                in.putExtra( "bandS", bandS );
                in.putExtra( "makeupS", makeupS );
               // in.putExtra( "hairS", hairS );
                in.putExtra( "clownS", clownS );
                in.putExtra( "customS", customS );

                startActivity(in);
            }
        });
        custom = (TextView)findViewById(R.id.textViewCustom);
        custom.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {

                Intent in=new Intent(getApplicationContext(), ChooseCustomActivity.class);
                in.putExtra( "Activity","party" );
                in.putExtra( "type", type );
                in.putExtra( "date", date );
                in.putExtra( "guests", guests );
                in.putExtra( "location", location );
                in.putExtra( "hallS", hallS );
                in.putExtra( "decorS", decorS );
                in.putExtra( "appetizerS", appetizerS );
                in.putExtra( "mainS", mainS );
                in.putExtra( "dessertS", dessertS );
                in.putExtra( "cakeS", cakeS );
                in.putExtra("photoS",photographerS);
                in.putExtra( "singerS", singerS );
                in.putExtra( "djS", djS );
                in.putExtra( "bandS", bandS );
                in.putExtra( "makeupS", makeupS );
                in.putExtra( "hairS", hairS );
                in.putExtra( "clownS", clownS );
              //  in.putExtra( "customS", customS );

                startActivity(in);
            }
        });
if(hallS!=null)
   hall.setText("Your hall is: "+hallS);
else    hall.setText("Your Hall: "+"None");

if(decorS!=null)
   decor.setText("Your decoration price is: "+decorS);
else    decor.setText("Your decoration price is: "+"None");



     if(appetizerS!=null)
         food1.setText("Your appetizer price is: "+appetizerS);
     else          food1.setText("Your appetizer price is: "+"None");

        if(dessertS!=null)
         food2.setText( "Your dessert price is: "+dessertS);
        else          food2.setText("Your dessert price is: "+"None");

        if(mainS!=null)
            food3.setText("Your main course price is: "+mainS);
        else          food3.setText("Your main course price is: "+"None");

        if(cakeS!=null)
         food4.setText("Your cake price is: "+cakeS);
     else          food4.setText("Your cake price is: "+"None");


        if(photographerS!=null)
     photographer.setText("Your photographer is: "+photographerS);
        else      photographer.setText("Your photographer is: "+"None");


        if(makeupS!=null)
        makeup.setText("Your makeup artist is: "+makeupS);
        else        makeup.setText("Your makeup artist is: "+"None");

        if(hairS!=null)
        hair.setText("Your hair dresser is: "+hairS);
        else         hair.setText("Your hair dresser is: "+"None");


        if(customS!=null)
        custom.setText("Your custom price is: "+customS);
        else         custom.setText("Your custom price is: "+"None");




        // set text for music
        if(singerS!=null)
            music.setText("Your singer is: "+singerS);
else{
        if(djS!=null)
            music.setText("Your DJ is: "+djS);
        else {
         if(bandS!=null)
             music.setText("Your Band is: "+bandS);
         else             music.setText("Your music is: "+"None");}}

        // set text for clown

            if(clownS!=null)
            clown.setText("Your Clown is: "+clownS);
           else             clown.setText("Your Clown is: "+"None");



        // if the user want to cancel the party
        cancel=(Button) findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {

                AlertDialog.Builder alert = new AlertDialog.Builder(
                        PartyActivity.this );
                alert.setTitle( "Confirm" );
                alert.setMessage( "Are you sure you want to cancel? " );
                alert.setPositiveButton( "YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //*********************************************************
                        startActivity(new Intent(getApplicationContext(), UserHomeActivity.class));
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

        //***************************************************************************************

        dr = getInstance().getReference(HeadActivity.DATABASE_PATH);
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list.clear();

                for (DataSnapshot snap : dataSnapshot.getChildren()) {

                    Head head = snap.getValue( Head.class );

                    list.add( head );

                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // if the user want to confirm the party
        confirm=(Button) findViewById(R.id.confirm);
        confirm.setOnClickListener( new View.OnClickListener()   {
            public void onClick(View v){
                //
                FirebaseUser user = firebaseAuth.getCurrentUser();


                final ProgressDialog progressDialog = new ProgressDialog(PartyActivity.this);
                progressDialog.setTitle("Saving Party Info..!!");
                progressDialog.show();
               // if(appetizerS!=null)
             party=new Party(date,hallS,decorS,bandS,clownS,customS,djS,appetizerS,mainS,dessertS,cakeS ,hairS,photographerS,makeupS,singerS);
           /* else    if(mainS!=null)
                    party=new Party(user,date,hallS,decorS,bandS,clownS,customS,djS,mainS ,hairS,photographerS,makeupS,singerS);
            else    if(cakeS!=null)
                    party=new Party(user,date,hallS,decorS,bandS,clownS,customS,djS,cakeS ,hairS,photographerS,makeupS,singerS);
           else     if(dessertS!=null)
                    party=new Party(user,date,hallS,decorS,bandS,clownS,customS,djS,dessertS ,hairS,photographerS,makeupS,singerS);
           else
                    party=new Party(user,date,hallS,decorS,bandS,clownS,customS,djS,"No food" ,hairS,photographerS,makeupS,singerS);*/



                // to the database
                dr= getInstance().getReference();
                String id = dr.push().getKey();
                FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user1.getUid();
                DatabaseReference restaurantRef = getInstance().getReference(aa).child(uid);
               /// DatabaseReference pushRef = restaurantRef.push();
                String pushId = restaurantRef.getKey();
               party.setPushID(pushId);
           //     restaurantRef.setValue(party);
                dr.child("Party").child(id).setValue(party);

                //    final User user2 = (User) (user.getClass()).addParty(party);

                progressDialog.dismiss();
                Toast.makeText( getApplicationContext(), "your party has been reserved ", Toast.LENGTH_LONG ).show();

                DatabaseReference notRef = getInstance().getReference(uu).child(uid);
                String pushId1 = notRef.getKey();


                int index = new Random().nextInt(list.size());
                yourHead = list.get(index);



                uN=new UserNotification( date,yourHead.getName(),yourHead.getPhone());
                uN.setPushID(pushId1);

                dr.child("Notification").child(id).setValue(uN);

                notification.setSmallIcon( R.mipmap.ic_launcher );
                notification.setTicker( "YOUR HEAD " );
                notification.setWhen( System.currentTimeMillis() );

                notification.setContentTitle( "Now one Last step!!" );

                notification.setContentText( "This head is been assigned to help you in your party and makes sure you are fully satisfied \n "+yourHead );

                Intent intent=new Intent(getApplicationContext(),NotificationActivity.class);
                intent.putExtra( "notification","This head is been assigned to help you in your party and makes sure you are fully satisfied \nName: "+yourHead.getName()+"\nPhone Number: "+yourHead.getPhone() );
                PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent( pendingIntent );
                NotificationManager nm = (NotificationManager) getSystemService( NOTIFICATION_SERVICE );
                nm.notify(uniqueID, notification.build());




                ///////// set dates for chosen







            }});




        /*    @Override
            public void onDataChange(DataSnapshot dataSnapshot) {








               for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    String name = (String) messageSnapshot.child("name").getValue();
                    String message = (String) messageSnapshot.child("message").getValue();
                }
            }

        */



           /*
        hall.setText(hallS);
   //  final  Hall[] h;
     //   h = (Hall[])intent.getSerializableExtra("hallO");
     /*   final String type1 = intent.getExtras().getString( "type" );
        final String date1 = intent.getExtras().getString( "date" );
        final String guests1 = intent.getExtras().getString( "guests" );
        final String location1 = intent.getExtras().getString( "location" );
        final String hall1 = intent.getExtras().getString( "hall" );
        final String decor1 = intent.getExtras().getString( "decor" );
        final String appetizer1 = intent.getExtras().getString( "appetizer" );
        final String main1 = intent.getExtras().getString( "main" );
        final String dessert1 = intent.getExtras().getString( "dessert" );
        final String cake1 = intent.getExtras().getString( "cake" );
        final String photographer1 = intent.getExtras().getString( "photographer" );
        final String singer1 = intent.getExtras().getString( "singer" );
        final String dj1 = intent.getExtras().getString( "dj" );
        final String band1 = intent.getExtras().getString( "band" );
        final String makeup1 = intent.getExtras().getString( "makeup" );
        final String hair1 = intent.getExtras().getString( "hair" );
        final String clown1=intent.getExtras().getString("clown");
        final String custom1=intent.getExtras().getString("custom");
        final Hall hallO=(Hall)intent.getSerializableExtra("hallO");
        final Decor decorO=(Decor)intent.getSerializableExtra("decorO");
        final Food appetizerO=(Food)intent.getSerializableExtra("appetizerO");
        final Food mainO=(Food)intent.getSerializableExtra("mainO");
        final Food dessertO=(Food)intent.getSerializableExtra("dessertO");
        final Food cakeO=(Food)intent.getSerializableExtra("cakeO");
        final Photographer photographerO=(Photographer)intent.getSerializableExtra("photographerO");
        final Dj  djO=(Dj)intent.getSerializableExtra("djO");
        final Band  bandO=(Band) intent.getSerializableExtra("bandO");
        final Singer  singerO=(Singer) intent.getSerializableExtra("singerO");
        final MakeUp makeupO=(MakeUp)intent.getSerializableExtra("makeupO");
        final Hair hairO=(Hair)intent.getSerializableExtra("hairO");
        final Clown clownO=(Clown)intent.getSerializableExtra("clownO");
        final Custom customO=(Custom)intent.getSerializableExtra("customO");
*/







/*
pp p;
                    //    if((singer1!=null)&&(appetizer1!=null))
                     p= new pp(hallS,decorS);
    /*                    if((singer1!=null)&&(dessert1!=null))
                party= new Party((Hall)getIntent().getSerializableExtra("hall"),(Decor) getIntent().getSerializableExtra("decorO"),(Band) getIntent().getSerializableExtra("BandO"),(Clown) getIntent().getSerializableExtra("clownO"),(Custom) getIntent().getSerializableExtra("customO"),(Dj)getIntent().getSerializableExtra("singerO"),(Food) getIntent().getSerializableExtra("dessertO"),(Hair) getIntent().getSerializableExtra("hairO"),(Photographer)getIntent().getSerializableExtra("photographer"),(MakeUp)getIntent().getSerializableExtra("makeup"),(Singer) getIntent().getSerializableExtra("singer"));
                if((singer1!=null)&&(cake1!=null))
                    party= new Party((Hall)getIntent().getSerializableExtra("hall"),(Decor) getIntent().getSerializableExtra("decorO"),(Band) getIntent().getSerializableExtra("BandO"),(Clown) getIntent().getSerializableExtra("clownO"),(Custom) getIntent().getSerializableExtra("customO"),(Dj)getIntent().getSerializableExtra("singerO"),(Food) getIntent().getSerializableExtra("cakeO"),(Hair) getIntent().getSerializableExtra("hairO"),(Photographer)getIntent().getSerializableExtra("photographer"),(MakeUp)getIntent().getSerializableExtra("makeup"),(Singer) getIntent().getSerializableExtra("singer"));
                if((singer1!=null)&&(main1!=null))
                    party= new Party((Hall)getIntent().getSerializableExtra("hall"),(Decor) getIntent().getSerializableExtra("decorO"),(Band) getIntent().getSerializableExtra("BandO"),(Clown) getIntent().getSerializableExtra("clownO"),(Custom) getIntent().getSerializableExtra("customO"),(Dj)getIntent().getSerializableExtra("singerO"),(Food) getIntent().getSerializableExtra("mainO"),(Hair) getIntent().getSerializableExtra("hairO"),(Photographer)getIntent().getSerializableExtra("photographer"),(MakeUp)getIntent().getSerializableExtra("makeup"),(Singer) getIntent().getSerializableExtra("singer"));
*/
/*
                if((band1!=null)&&(appetizer1!=null))
                    party= new Party((Hall)getIntent().getSerializableExtra("hall"),(Decor) getIntent().getSerializableExtra("decor"),(Band) getIntent().getSerializableExtra("Band"),(Clown) getIntent().getSerializableExtra("clown"),(Custom) getIntent().getSerializableExtra("custom"),(Dj)getIntent().getSerializableExtra("singer"),(Food) getIntent().getSerializableExtra("appetizer"),(Hair) getIntent().getSerializableExtra("hair"),(Photographer)getIntent().getSerializableExtra("photographer"),(MakeUp)getIntent().getSerializableExtra("makeup"),(Singer) getIntent().getSerializableExtra("singer"));
                if((band1!=null)&&(dessert1!=null))
                    party= new Party((Hall)getIntent().getSerializableExtra("hall"),(Decor) getIntent().getSerializableExtra("decor"),(Band) getIntent().getSerializableExtra("Band"),(Clown) getIntent().getSerializableExtra("clown"),(Custom) getIntent().getSerializableExtra("custom"),(Dj)getIntent().getSerializableExtra("singer"),(Food) getIntent().getSerializableExtra("dessert"),(Hair) getIntent().getSerializableExtra("hair"),(Photographer)getIntent().getSerializableExtra("photographer"),(MakeUp)getIntent().getSerializableExtra("makeup"),(Singer) getIntent().getSerializableExtra("singer"));
                if((band1!=null)&&(cake1!=null))
                    party= new Party((Hall)getIntent().getSerializableExtra("hall"),(Decor) getIntent().getSerializableExtra("decor"),(Band) getIntent().getSerializableExtra("Band"),(Clown) getIntent().getSerializableExtra("clown"),(Custom) getIntent().getSerializableExtra("custom"),(Dj)getIntent().getSerializableExtra("singer"),(Food) getIntent().getSerializableExtra("cake"),(Hair) getIntent().getSerializableExtra("hair"),(Photographer)getIntent().getSerializableExtra("photographer"),(MakeUp)getIntent().getSerializableExtra("makeup"),(Singer) getIntent().getSerializableExtra("singer"));
                if((band1!=null)&&(main1!=null))
                    party= new Party((Hall)getIntent().getSerializableExtra("hall"),(Decor) getIntent().getSerializableExtra("decor"),(Band) getIntent().getSerializableExtra("Band"),(Clown) getIntent().getSerializableExtra("clown"),(Custom) getIntent().getSerializableExtra("custom"),(Dj)getIntent().getSerializableExtra("singer"),(Food) getIntent().getSerializableExtra("main"),(Hair) getIntent().getSerializableExtra("hair"),(Photographer)getIntent().getSerializableExtra("photographer"),(MakeUp)getIntent().getSerializableExtra("makeup"),(Singer) getIntent().getSerializableExtra("singer"));


                if((dj1!=null)&&(appetizer1!=null))
                    party= new Party((Hall)getIntent().getSerializableExtra("hall"),(Decor) getIntent().getSerializableExtra("decor"),(Band) getIntent().getSerializableExtra("Band"),(Clown) getIntent().getSerializableExtra("clown"),(Custom) getIntent().getSerializableExtra("custom"),(Dj)getIntent().getSerializableExtra("singer"),(Food) getIntent().getSerializableExtra("appetizer"),(Hair) getIntent().getSerializableExtra("hair"),(Photographer)getIntent().getSerializableExtra("photographer"),(MakeUp)getIntent().getSerializableExtra("makeup"),(Singer) getIntent().getSerializableExtra("singer"));
                if((dj1!=null)&&(dessert1!=null))
                    party= new Party((Hall)getIntent().getSerializableExtra("hall"),(Decor) getIntent().getSerializableExtra("decor"),(Band) getIntent().getSerializableExtra("Band"),(Clown) getIntent().getSerializableExtra("clown"),(Custom) getIntent().getSerializableExtra("custom"),(Dj)getIntent().getSerializableExtra("singer"),(Food) getIntent().getSerializableExtra("dessert"),(Hair) getIntent().getSerializableExtra("hair"),(Photographer)getIntent().getSerializableExtra("photographer"),(MakeUp)getIntent().getSerializableExtra("makeup"),(Singer) getIntent().getSerializableExtra("singer"));
                if((dj1!=null)&&(cake1!=null))
                    party= new Party((Hall)getIntent().getSerializableExtra("hall"),(Decor) getIntent().getSerializableExtra("decor"),(Band) getIntent().getSerializableExtra("Band"),(Clown) getIntent().getSerializableExtra("clown"),(Custom) getIntent().getSerializableExtra("custom"),(Dj)getIntent().getSerializableExtra("singer"),(Food) getIntent().getSerializableExtra("cake"),(Hair) getIntent().getSerializableExtra("hair"),(Photographer)getIntent().getSerializableExtra("photographer"),(MakeUp)getIntent().getSerializableExtra("makeup"),(Singer) getIntent().getSerializableExtra("singer"));
                if((dj1!=null)&&(main1!=null))
                    party= new Party((Hall)getIntent().getSerializableExtra("hall"),(Decor) getIntent().getSerializableExtra("decor"),(Band) getIntent().getSerializableExtra("Band"),(Clown) getIntent().getSerializableExtra("clown"),(Custom) getIntent().getSerializableExtra("custom"),(Dj)getIntent().getSerializableExtra("singer"),(Food) getIntent().getSerializableExtra("main"),(Hair) getIntent().getSerializableExtra("hair"),(Photographer)getIntent().getSerializableExtra("photographer"),(MakeUp)getIntent().getSerializableExtra("makeup"),(Singer) getIntent().getSerializableExtra("singer"));

*/





    }
}

