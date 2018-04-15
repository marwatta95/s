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

public class NotificationAdapter extends ArrayAdapter<UserNotification> {

    Activity activity;
    int resource;
    List<UserNotification> list;

    public NotificationAdapter (Activity activity, int resource, List<UserNotification> list) {
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
        String dateLeft=list.get(position).partyDate;

        date.setText(dateLeft);

        TextView name = (TextView) view.findViewById(R.id.name);
        String headName=list.get(position).headName;

         name.setText(headName);

        TextView phone = (TextView) view.findViewById(R.id.phone);
        String phoneNum=list.get(position).headPhone;

      phone.setText(phoneNum);


        return view;
    }
}