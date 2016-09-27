package com.redhat.demo.demoandroid.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.redhat.demo.demoandroid.R;
import com.redhat.demo.demoandroid.model.Picture;
import com.redhat.demo.demoandroid.view.PictureDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by David on 25/09/16.
 */
public class PictureAdapter extends  RecyclerView.Adapter<PictureAdapter.PictureViewHoler>{

    private ArrayList<Picture> pictures;
    private int resource;
    private Activity activity;

    public PictureAdapter(ArrayList<Picture> pictures, int resource, Activity activity) {
        this.pictures = pictures;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public PictureViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        return new PictureViewHoler(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHoler holder, int position) {
        Picture picture = pictures.get(position);
        holder.usernameCard.setText(picture.getUserName());
        holder.timeCard.setText(picture.getTime());
        holder.likeNumberCard.setText(picture.getLikeNumber());
        Picasso.with(activity).load(picture.getPicture()).into(holder.pictureCard);

        holder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PictureDetailActivity.class);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Explode explode = new Explode();
                    explode.setDuration(100);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view,activity.getString(R.string.transitionname_picture)).toBundle());
                }else{
                    activity.startActivity(intent);
                }

                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }


    public class PictureViewHoler extends RecyclerView.ViewHolder{

        private ImageView pictureCard;
        private TextView usernameCard;
        private TextView timeCard;
        private  TextView likeNumberCard;

        public PictureViewHoler(View itemView) {
            super(itemView);

            pictureCard = (ImageView) itemView.findViewById(R.id.pictureCard);
            usernameCard = (TextView) itemView.findViewById(R.id.userNameCard);
            timeCard = (TextView) itemView.findViewById(R.id.timeCard);
            likeNumberCard = (TextView) itemView.findViewById(R.id.likeNumberCard);
        }
    }
}
