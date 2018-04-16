package net.marwa.applicationy;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    ListView listView;
    final ArrayList<String> keyList = new ArrayList<>();
    NotificationAdapter myAdapter;
    ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    List<UserNotification> list;

    private FirebaseAuth firebaseAuth;
    TextView textView;
    private Button buttonSave,buttonOk;
    public static final String DATABASE_PATH = "Notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_notification );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        buttonSave=(Button) findViewById(R.id.buttonSave);

        String type1="No new notifications";
        textView=(TextView) findViewById( R.id.textNotification ) ;
        Intent intent = getIntent();
        if(intent.getExtras().getString( "notification" )!=null)
          type1 = intent.getStringExtra( "notification" );
if (type1.equals( "No new notification" ))
    buttonSave.setVisibility( View.GONE );
        textView.setText( type1 );
        buttonSave=(Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), PaymentActivity.class));



            }
        });

        buttonOk=(Button) findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), UserHomeActivity.class));



            }
        });



        listView = (ListView) findViewById(R.id.list1);
        databaseReference = FirebaseDatabase.getInstance().getReference(DATABASE_PATH);


        list = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference(DATABASE_PATH);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                list.clear();

                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    keyList.add(snap.getKey());

                    UserNotification userNotification = snap.getValue(UserNotification.class);

                    FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = user1.getUid();

                    if(userNotification.pushID.equals(uid)){

                        list.add(userNotification);}
                }
                for(int i=0;i<list.size();i++)
                //    Toast.makeText( getApplicationContext(), list.get( i ).partyDate, Toast.LENGTH_LONG ).show();
                myAdapter = new NotificationAdapter (NotificationActivity.this,R.layout.notification_dataitems,list);
                listView.setAdapter(myAdapter);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
