package com.cchd.talk2me.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.pkmmte.view.CircularImageView;

import java.util.List;

import com.cchd.talk2me.ProfileActivity;
import com.cchd.talk2me.R;
import com.cchd.talk2me.app.App;
import com.cchd.talk2me.constants.Constants;
import com.cchd.talk2me.model.Notify;

public class NotifyListAdapter extends BaseAdapter implements Constants {

	private Activity activity;
	private LayoutInflater inflater;
	private List<Notify> notifyList;

    ImageLoader imageLoader = App.getInstance().getImageLoader();

	public NotifyListAdapter(Activity activity, List<Notify> notifyList) {

		this.activity = activity;
		this.notifyList = notifyList;
	}

	@Override
	public int getCount() {

		return notifyList.size();
	}

	@Override
	public Object getItem(int location) {

		return notifyList.get(location);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}
	
	static class ViewHolder {

        public TextView notifyTitle;
        public TextView notifyTimeAgo;
		public CircularImageView notifyAuthor;
        public CircularImageView notifyType;
	        
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder = null;

		if (inflater == null) {

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

		if (convertView == null) {
			
			convertView = inflater.inflate(R.layout.notify_list_row, null);
			
			viewHolder = new ViewHolder();

            viewHolder.notifyAuthor = (CircularImageView) convertView.findViewById(R.id.notifyAuthor);
            viewHolder.notifyType = (CircularImageView) convertView.findViewById(R.id.notifyType);
            viewHolder.notifyTitle = (TextView) convertView.findViewById(R.id.notifyTitle);
			viewHolder.notifyTimeAgo = (TextView) convertView.findViewById(R.id.notifyTimeAgo);

            convertView.setTag(viewHolder);

            viewHolder.notifyAuthor.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    int getPosition = (Integer) v.getTag();

                    Notify notify = notifyList.get(getPosition);

                    Intent intent = new Intent(activity, ProfileActivity.class);
                    intent.putExtra("profileId", notify.getFromUserId());
                    activity.startActivity(intent);
                }
            });

		} else {
			
			viewHolder = (ViewHolder) convertView.getTag();
		}

        if (imageLoader == null) {

            imageLoader = App.getInstance().getImageLoader();
        }

        viewHolder.notifyTitle.setTag(position);
        viewHolder.notifyTimeAgo.setTag(position);
        viewHolder.notifyAuthor.setTag(position);
        viewHolder.notifyType.setTag(position);
        viewHolder.notifyAuthor.setTag(R.id.notifyAuthor, viewHolder);
		
		final Notify notify = notifyList.get(position);

        viewHolder.notifyTitle.setText(notify.getFromUserFullname() + " " + activity.getText(R.string.label_friend_request_added));
        viewHolder.notifyType.setImageResource(R.drawable.notify_follower);

        if (notify.getFromUserPhotoUrl().length() > 0) {

            imageLoader.get(notify.getFromUserPhotoUrl(), ImageLoader.getImageListener(viewHolder.notifyAuthor, R.drawable.profile_default_photo, R.drawable.profile_default_photo));

        } else {

            viewHolder.notifyAuthor.setImageResource(R.drawable.profile_default_photo);
        }

        viewHolder.notifyTimeAgo.setText(notify.getTimeAgo());

		return convertView;
	}
}