package com.daniilvdovin.wowraider.Token;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daniilvdovin.wowraider.R;
import com.daniilvdovin.wowraider.model.MythicPluseProgressItem;

public class AdapterToken extends RecyclerView.Adapter<AdapterToken.ViewHolder>{
    private TokenByRegion[] Item = new TokenByRegion[0];

    public Context context;
    public com.daniilvdovin.wowraider.MainActivity MainActivity;

    public AdapterToken(Context context, TokenByRegion[] Item)
    {
        this.Item = Item;
        this.MainActivity = MainActivity;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title,score,type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.t_region);
            type = itemView.findViewById(R.id.t_last_change);
            score = itemView.findViewById(R.id.t_price);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tokenregionitem,
                        parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final TokenByRegion data = Item[position];
        if(data == null) return;
        holder.title.setText(data.getRegion());
        holder.score.setText(data.getCurrent_price());
        holder.type.setText(data.getLast_change());
    }

    @Override
    public int getItemCount() {
        return Item.length;
    }
}

