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

public class ChooseFoodActivity extends AppCompatActivity {
    ListView listView;
    ListView listView1;   ListView listView2;   ListView listView3;
    List<Food> list;
    List<Food> list1; List<Food> list2; List<Food> list3;
    ProgressDialog progressDialog;
    final ArrayList<String> keyList = new ArrayList<>();
    private DatabaseReference databaseReference;
    public static final String DATABASE_PATH = "Food";
    MyAdapterChooseFood myAdapter;
    MyAdapterChooseFood myAdapter1;
    MyAdapterChooseFood myAdapter2;
    MyAdapterChooseFood myAdapter3;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_choose_food);
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        listView=(ListView) findViewById( R.id.list1);
        listView1=(ListView) findViewById( R.id.list2);
        listView2=(ListView) findViewById( R.id.list3);
        listView3=(ListView) findViewById( R.id.list4);


        Intent intent = getIntent();
        final  Intent intent1;

        final String act = intent.getExtras().getString( "Activity" );

        final String type = intent.getExtras().getString( "type" );
        final String date = intent.getExtras().getString( "date" );
        final String guests = intent.getExtras().getString( "guests" );
        final String location = intent.getExtras().getString( "location" );
        final String hallS=intent.getStringExtra("hallS");
        final String decorS=intent.getStringExtra("decorS");
        final Food[] foodChosen = new Food[1];

        if(act.equals( "party" ))
        {
            intent1 =new Intent(ChooseFoodActivity.this, PartyActivity.class);


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
            intent1.putExtra( "decorS", decorS );
           // intent1.putExtra( "appetizerS", appetizerS );
            //intent1.putExtra( "mainS", mainS );
            //intent1.putExtra( "dessertS", dessertS );
            //intent1.putExtra( "cakeS", cakeS );
            intent1.putExtra("photoS",photographerS);
            intent1.putExtra( "singerS", singerS );
            intent1.putExtra( "bandS", bandS );
            intent1.putExtra( "makeupS", makeupS );
            intent1.putExtra( "hairS", hairS );
            intent1.putExtra( "clownS", clownS );

        }

        else
        {
            intent1 =new Intent(ChooseFoodActivity.this, ChoosePhotoActivity.class);

            intent1.putExtra( "Activity", "hi" );

        }

        /*  final Hall hallO=(Hall)intent.getSerializableExtra("hallO");
        final Decor decorO=(Decor)intent.getSerializableExtra("decorO");*/
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference(DATABASE_PATH);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                list.clear();
                list1.clear(); list2.clear(); list3.clear();

                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    keyList.add(snap.getKey());

                    Food food = snap.getValue(Food.class);
                    if(food.type.equals( "Appetizer" )){
                        list.add(food);
                    }
                    if(food.type.equals( "Main course" )){
                        list1.add(food);
                    }
                    if(food.type.equals( "Dessert" )){
                        list2.add(food);
                    }
                    if(food.type.equals( "Cake" )){
                        list3.add(food);
                    }

                }
                myAdapter = new MyAdapterChooseFood(ChooseFoodActivity.this,R.layout.data_items_choose_food,list);
                listView.setAdapter(myAdapter);

                myAdapter1 = new MyAdapterChooseFood(ChooseFoodActivity.this,R.layout.data_items_choose_food,list1);
                listView1.setAdapter(myAdapter);
                myAdapter2 = new MyAdapterChooseFood(ChooseFoodActivity.this,R.layout.data_items_choose_food,list2);
                listView2.setAdapter(myAdapter2);
                myAdapter3 = new MyAdapterChooseFood(ChooseFoodActivity.this,R.layout.data_items_choose_food,list3);
                listView3.setAdapter(myAdapter3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseFoodActivity.this, UserHomeActivity.class));

            }
        } );
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                foodChosen[0] =list.get(position);

                AlertDialog.Builder alert = new AlertDialog.Builder(
                        ChooseFoodActivity.this );
                alert.setTitle( "Confirm" );
                alert.setMessage( "Are you sure you want this? " );
                alert.setPositiveButton( "YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intent1.putExtra( "appetizerS", Double.toString(foodChosen[0].getPrice()) );
                    //    intent1.putExtra( "appetizerO", databaseReference.getRoot().child( DATABASE_PATH ).child( keyList.get( position ) ).getClass().toString() );

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
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                foodChosen[0] =list.get(position);

                AlertDialog.Builder alert = new AlertDialog.Builder(
                        ChooseFoodActivity.this );
                alert.setTitle( "Confirm" );
                alert.setMessage( "Are you sure you want this? " );
                alert.setPositiveButton( "YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intent1.putExtra( "mainS", Double.toString(foodChosen[0].getPrice()) );
                   //     intent1.putExtra( "mainO", databaseReference.getRoot().child( DATABASE_PATH ).child( keyList.get( position ) ).getClass() );

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
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                foodChosen[0] =list.get(position);

                AlertDialog.Builder alert = new AlertDialog.Builder(
                        ChooseFoodActivity.this );
                alert.setTitle( "Confirm" );
                alert.setMessage( "Are you sure you want this? " );
                alert.setPositiveButton( "YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intent1.putExtra( "dessertS", Double.toString(foodChosen[0].getPrice()) );
                     //   intent1.putExtra( "dessertO", databaseReference.getRoot().child( DATABASE_PATH ).child( keyList.get( position ) ).getClass() );

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
        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                foodChosen[0] =list.get(position);

                AlertDialog.Builder alert = new AlertDialog.Builder(
                        ChooseFoodActivity.this );

                alert.setTitle( "Confirm" );
                alert.setMessage( "Are you sure you want this? " );
                alert.setPositiveButton( "YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intent1.putExtra( "cakeS",Double.toString(foodChosen[0].getPrice()) );
                    //    intent1.putExtra( "cakeO", databaseReference.getRoot().child( DATABASE_PATH ).child( keyList.get( position ) ).getClass() );

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
              /*  intent1.putExtra("hallO",hallO);
                intent1.putExtra("decorO",decorO);*/



                startActivity(intent1);




            }
        });

    }
}
