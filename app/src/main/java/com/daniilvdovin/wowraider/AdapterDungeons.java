package com.daniilvdovin.wowraider;


import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daniilvdovin.wowraider.model.DungeonRun;
import com.daniilvdovin.wowraider.model.MythicPluseProgressItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class AdapterDungeons extends RecyclerView.Adapter<AdapterDungeons.ViewHolder>{
    private DungeonRun[] Item = new DungeonRun[0];

    public Context context;

    public AdapterDungeons(Context context, DungeonRun[] Item)
    {
        this.Item = Item;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title,score,lvl,time;
        public WebView url;
        public ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.tv_dungen_icon);
            title = itemView.findViewById(R.id.tv_dungen_name);
            lvl = itemView.findViewById(R.id.tv_dungen_lvl);
            time = itemView.findViewById(R.id.tv_dungeon_time);
            score = itemView.findViewById(R.id.tv_dungeon_score);
            url = itemView.findViewById(R.id.web);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dungeonrunrecentitem,
                        parent, false));
    }

    private int getScale(){
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width)/new Double(10f);
        return val.intValue();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final DungeonRun data = Item[position];
        if(data == null) return;

        holder.title.setText(data.dungeon);
        holder.score.setText(""+data.score);
        holder.lvl.setText(data.mythic_level.toString());
        Date date = new Date(data.clear_time_ms);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        holder.time.setText(formatter.format(date));
        new DonwloadImageTask(holder.icon).execute("https://cdnassets.raider.io/images/keystone-icons/"+data.zone_id+".jpg");
        holder.url.setBackgroundColor(R.color.bar_background);
        holder.url.setPadding(0, 0, 0, 0);
        holder.url.setInitialScale(getScale());
        holder.url.loadData("<iframe width=\"900\" height=\"485\" src=\""+data.url+"?embed=1\" frameborder=\"0\"></iframe>", "text/html", "UTF-8");
    }

    @Override
    public int getItemCount() {
        return Item.length;
    }
}

