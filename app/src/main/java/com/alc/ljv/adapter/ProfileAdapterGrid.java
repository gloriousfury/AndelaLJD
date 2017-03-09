package com.alc.ljv.adapter;

/**
 * Created by OLORIAKE KEHINDE on 11/16/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alc.ljv.R;
import com.alc.ljv.model.ProfileModel;
import com.alc.ljv.ui.activity.SingleUserActivity;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProfileAdapterGrid extends RecyclerView.Adapter<ProfileAdapterGrid.ViewHolder> {
    Context context;
    private List<ProfileModel> profile_list;
    String DEV_NAME = "dev_name";
    String HTML_URL = "html_url";
    String IMAGE_URL = "image_url";

    public ProfileAdapterGrid(Context context, List<ProfileModel> ProfileList) {
        this.context = context;
        this.profile_list = ProfileList;


    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView profile_name;
        ImageView profile_image;

        public ViewHolder(View view) {
            super(view);
            view.setClickable(true);
            view.setOnClickListener(this);
            profile_name = (TextView) view.findViewById(R.id.profile_name);
            profile_image =(ImageView) view.findViewById(R.id.profile_image);


        }

        @Override
        public void onClick(View v) {

            Intent singleUser = new Intent(context, SingleUserActivity.class);
            singleUser.putExtra(DEV_NAME,profile_list.get(getAdapterPosition()).getLogin());
            singleUser.putExtra(HTML_URL,profile_list.get(getAdapterPosition()).getHtmlUrl());
            singleUser.putExtra(IMAGE_URL,profile_list.get(getAdapterPosition()).getAvatarUrl());
            context.startActivity(singleUser);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_card2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.profile_name.setText(profile_list.get(position).getLogin());
        Picasso.with(context).load(profile_list.get(position).getAvatarUrl()).into(holder.profile_image);


    }

    @Override
    public int getItemCount() {
        return profile_list.size();
    }
}

