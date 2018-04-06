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

        if(dateLeft !=null) date.setText(dateLeft);

        TextView first = (TextView) view.findViewById(R.id.hall);
        String firstn=list.get(position).hall;
        if(firstn!=null) first.setText( firstn );
        TextView last = (TextView) view.findViewById(R.id.decor);
        String lastn=list.get(position).decor;
        if(lastn!=null)last.setText( lastn );
        TextView phone = (TextView) view.findViewById(R.id.band);
        String phonen=list.get(position).band;
        if(phonen!=null)  phone.setText( phonen );

        TextView price = (TextView) view.findViewById(R.id.clown);
        String price1=list.get(position).clown;
        if(price1!=null) price.setText(price1);

        TextView gen = (TextView) view.findViewById(R.id.costume);
        String gender=list.get(position).custom;
        if(gender!=null)  gen.setText( gender );


        TextView djt = (TextView) view.findViewById(R.id.dj);
        String dj=list.get(position).dj;
        if(dj!=null)   djt.setText( dj );

        TextView hairt = (TextView) view.findViewById(R.id.hair);
        String hair=list.get(position).hair;
        if(hair!=null)  hairt.setText( hair );
        TextView singert = (TextView) view.findViewById(R.id.singer);
        String singer=list.get(position).singer;
        if(singer!=null) singert.setText( singer );
        TextView makeupt = (TextView) view.findViewById(R.id.makeup);
        String makeup=list.get(position).makeup;
        if(makeup!=null)  makeupt.setText( makeup );
        TextView appetizert = (TextView) view.findViewById(R.id.appetizer);
        String appetizer=list.get(position).appetizer;
        if(appetizer!=null) appetizert.setText( appetizer );
        TextView mainCourset = (TextView) view.findViewById(R.id.maincource);
        String mainCourse=list.get(position).mainCourse;
        if(mainCourse!=null) mainCourset.setText( mainCourse );
        TextView dessertt = (TextView) view.findViewById(R.id.dessert);
        String dessert=list.get(position).dessert;
        if(dessert!=null) dessertt.setText( dessert );
        TextView caket = (TextView) view.findViewById(R.id.cake);
        String cake=list.get(position).cake;
        if(cake!=null) caket.setText( cake );
        TextView photot = (TextView) view.findViewById(R.id.photographer);
        String photo=list.get(position).photo;
       if(photo!=null) photot.setText( photo );


        return view;
    }
}