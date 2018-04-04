package net.marwa.applicationy;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyAdapterMyParties extends ArrayAdapter<Party> {

    Activity activity;
    int resource;
    List<Party> list;

    public MyAdapterMyParties (Activity activity, int resource, List<Party> list) {
        super(activity, resource,list);
        this.activity = activity;
        this.resource = resource;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = activity.getLayoutInflater();

        View view = layoutInflater.inflate(resource,null);

        TextView date = (TextView) view.findViewById(R.id.dateLeft);
        String dateLeft=list.get(position).date;
        String dateCurrent = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault()).format(new Date());

        date.setText( "Days Left : "+(Integer.parseInt( dateLeft.substring( 0,1 ))-Integer.parseInt( dateCurrent.substring( 0,1 ) )));

        TextView first = (TextView) view.findViewById(R.id.hall);
        String firstn=list.get(position).hall;
        first.setText( firstn );
        TextView last = (TextView) view.findViewById(R.id.decor);
        String lastn=list.get(position).decor;
        last.setText( lastn );
        TextView phone = (TextView) view.findViewById(R.id.band);
        String phonen=list.get(position).band;
        phone.setText( phonen );

        TextView price = (TextView) view.findViewById(R.id.clown);
        String price1=list.get(position).clown;
        price.setText(price1);

        TextView gen = (TextView) view.findViewById(R.id.costume);
        String gender=list.get(position).custom;
        gen.setText( gender );


        TextView djt = (TextView) view.findViewById(R.id.dj);
        String dj=list.get(position).dj;
        gen.setText( dj );

        TextView hairt = (TextView) view.findViewById(R.id.hair);
        String hair=list.get(position).hair;
        hairt.setText( hair );
        TextView singert = (TextView) view.findViewById(R.id.singer);
        String singer=list.get(position).singer;
        singert.setText( singer );
        TextView makeupt = (TextView) view.findViewById(R.id.makeup);
        String makeup=list.get(position).makeup;
        makeupt.setText( makeup );
        TextView appetizert = (TextView) view.findViewById(R.id.appetizer);
        String appetizer=list.get(position).appetizer;
        appetizert.setText( appetizer );
        TextView mainCourset = (TextView) view.findViewById(R.id.maincource);
        String mainCourse=list.get(position).mainCourse;
        mainCourset.setText( mainCourse );
        TextView dessertt = (TextView) view.findViewById(R.id.dessert);
        String dessert=list.get(position).dessert;
        dessertt.setText( dessert );
        TextView caket = (TextView) view.findViewById(R.id.cake);
        String cake=list.get(position).cake;
        caket.setText( cake );
        TextView photot = (TextView) view.findViewById(R.id.photographer);
        String photo=list.get(position).photo;
        photot.setText( photo );


        return view;
    }
}