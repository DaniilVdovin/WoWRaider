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

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private GearItem[] Item = new GearItem[]{};

    public View.OnClickListener OnClickListener;
    public View.OnLongClickListener OnLongClickListener ;
    public Context context;
    public MainActivity MainActivity;

    public Adapter(Context context,GearItem[] Item)
    {
        this.Item = Item;
        this.MainActivity = MainActivity;
        this.context = context;

        this.OnClickListener = OnClickListener;
        this.OnLongClickListener = OnLongClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public boolean phaze;
        public ImageView icon;
        public TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon =  itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.textView);
            phaze = false;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gearitem,
                        parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final GearItem data = Item[position];
        if(data == null) return;
        holder.title.setText(""+data.item_level);

        new DonwloadImageTask(holder.icon).execute(String.format(
                "https://cdnassets.raider.io/images/wow/icons/large/%s.jpg",data.icon));

        holder.icon.setOnClickListener(v ->{
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse( String.format("https://www.wowhead.com/item=%s",data.item_id))));
        });
    }

    @Override
    public int getItemCount() {
        return Item.length;
    }
}

