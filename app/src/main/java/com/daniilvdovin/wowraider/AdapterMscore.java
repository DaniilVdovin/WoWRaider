package com.daniilvdovin.wowraider;


import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.daniilvdovin.wowraider.model.GearItem;
import com.daniilvdovin.wowraider.model.MythicPluseProgressItem;

public class AdapterMscore extends RecyclerView.Adapter<AdapterMscore.ViewHolder>{
    private MythicPluseProgressItem[] Item = new MythicPluseProgressItem[0];

    public Context context;
    public MainActivity MainActivity;

    public AdapterMscore(Context context, MythicPluseProgressItem[] Item)
    {
        this.Item = Item;
        this.MainActivity = MainActivity;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title,score,type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            type = itemView.findViewById(R.id.tv_type);
            score = itemView.findViewById(R.id.tv_score);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mplusescoreitem,
                        parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final MythicPluseProgressItem data = Item[position];
        if(data == null) return;
        holder.title.setText(""+data.getTite());
        holder.score.setText(""+data.getScore());
        holder.type.setText(""+data.getType());
    }

    @Override
    public int getItemCount() {
        return Item.length;
    }
}

