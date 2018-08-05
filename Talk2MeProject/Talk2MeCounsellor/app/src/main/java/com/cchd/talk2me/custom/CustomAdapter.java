package com.cchd.talk2me.custom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cchd.talk2me.LinkActivity;
import com.cchd.talk2me.MainChatActivity;
import com.cchd.talk2me.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<String> display_name;
    ArrayList<String> location;
    ArrayList<String> online_status;
    ArrayList<String> user_id;
    ArrayList<String> my_own_display_name;

    Context context;
    private static String TAG = "CUSTOM_ADAPTOR";

    public CustomAdapter(Context context, ArrayList<String> display_name,
                         ArrayList<String> location,
                         ArrayList<String> online_status, ArrayList<String> user_id, ArrayList<String> my_own_display_name) {
        this.context = context;
        this.display_name = display_name;
        this.location = location;
        this.online_status = online_status;
        this.user_id = user_id;
        this.my_own_display_name = my_own_display_name;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        // set the data in items
        holder.tvRequester_name.setText(display_name.get(position));
        holder.tvLocation.setText(location.get(position));
        holder.tvOnline_status.setText(online_status.get(position));
        holder.tvUser_id.setText(user_id.get(position));
        holder.tvMy_display.setText(my_own_display_name.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person tvRequester_name on item click
                Bundle bundle = new Bundle();
                bundle.putString("DISPLAY_NAME", display_name.get(position));
                bundle.putString("USER_ID", user_id.get(position));
                //bundle.putString("MY_OWN_DISPLAY_NAME", my_own_display_name.get(position));
                Log.d(TAG, "display_name.get(position) is :" + display_name.get(position));
                Log.d(TAG, "my_own_USER_ID.toString is :" + user_id.get(position));

                Intent intent = new Intent(context.getApplicationContext(), LinkActivity.class);
                intent.putExtra("DISPLAY_NAME", display_name.get(position));
                intent.putExtra("USER_ID", user_id.get(position));
               // intent.putExtra("MY_OWN_DISPLAY_NAME", my_own_display_name.toString());

                context.startActivity(intent);
                Toast.makeText(context, display_name.get(position), Toast.LENGTH_SHORT).show();
                //Toast.makeText(context, my_own_display_name.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return display_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvRequester_name, tvLocation, tvOnline_status,
                tvUser_id, tvMy_display;
        // init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            tvRequester_name = itemView.findViewById(R.id.display_name);
            tvLocation = itemView.findViewById(R.id.location);
            tvOnline_status = itemView.findViewById(R.id.online_starus);
            tvUser_id = itemView.findViewById(R.id.user_id);
            tvMy_display = itemView.findViewById(R.id.my_display);
        }
    }
}