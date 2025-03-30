package com.example.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recyclerview.R;
import com.example.recyclerview.model.IconModel;

import java.time.Instant;
import java.util.List;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconHolder> {
    private Context context;
    private List<IconModel> arrayList;

    public IconAdapter(Context context, List<IconModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public IconHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_icon_promotion,parent,false);
        return new IconHolder(view);
    }

    @Override
    public void onBindViewHolder(IconHolder holder, int position) {
        IconModel iconModel = arrayList.get(position);
        Glide.with(context).load(iconModel.getIdImg()).into(holder.imageView);
        holder.tvIcon.setText(iconModel.getDesc());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public void setListenerList(List<IconModel> list){
        this.arrayList = list;
        notifyDataSetChanged();
    }

    public class IconHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tvIcon;

        public IconHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivImgIcon);
            tvIcon = itemView.findViewById(R.id.tvIcon);
        }
    }
}
