package com.example.viewfipper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.viewfipper.R;
import com.example.viewfipper.model.ImageSlider;
import com.example.viewfipper.model.Images;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ImagesViewHolder> {
    private Context context;
    private List<ImageSlider> imagesList;

    public SliderAdapter(Context context, List<ImageSlider> imagesList) {
        this.context = context;
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_images, parent, false);
        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {
        ImageSlider images = imagesList.get(position);
        if (images == null) return;

        // Load ảnh bằng Glide (hoặc dùng `setImageResource` nếu ảnh từ drawable)
        Glide.with(context).load(images.getImageResId()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imagesList != null ? imagesList.size() : 0;
    }

    public static class ImagesViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImagesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgView);
        }
    }
}
