package com.daniilvdovin.wowraider;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daniilvdovin.wowraider.model.Rank;

public class AdapterRanks extends RecyclerView.Adapter<AdapterRanks.ViewHolder>{
    private Rank[] Item = new Rank[]{};

    public View.OnClickListener OnClickListener;
    public View.OnLongClickListener OnLongClickListener ;
    public Context context;
    public MainActivity MainActivity;

    public AdapterRanks(Context context, Rank[] Item)
    {
        this.Item = Item;
        this.context = context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title,word,realm,region;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.t_price);
            word = itemView.findViewById(R.id.t_name2);
            realm = itemView.findViewById(R.id.t_name3);
            region = itemView.findViewById(R.id.t_name4);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ranksrowitem,
                        parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Rank data = Item[position];
        holder.title.setText(""+data.getName());
        holder.word.setText(""+data.getWorld());
        holder.realm.setText(""+data.getRealm());
        holder.region.setText(""+data.getRegion());
    }

    @Override
    public int getItemCount() {
        return Item.length;
    }
}

