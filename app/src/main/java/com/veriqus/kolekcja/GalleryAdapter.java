package com.veriqus.kolekcja;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by krzysztofmarczewski on 21.10.2017.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private List<GalleryItem> mGallery;
    private Context mContext;

    public GalleryAdapter(Context context, List<GalleryItem> galleryItems) {
        mGallery = galleryItems;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageViewHolder;
        private Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewHolder = (ImageView) itemView.findViewById(R.id.image_view);
        }

        public ViewHolder(Context context, View itemView) {

            super(itemView);
            imageViewHolder = (ImageView) itemView.findViewById(R.id.image_view);
            this.context = context;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            Toast.makeText(getContext(), position+"", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, PhotoZoomActivity.class);
            intent.putExtra("position", position);
            context.startActivity(intent);
        }
    }


    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View galleryView = inflater.inflate(R.layout.image_view, parent, false);

        ViewHolder viewHolder = new ViewHolder(context, galleryView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder (GalleryAdapter.ViewHolder viewHolder,int position){
        GalleryItem galleryItem = mGallery.get(position);

        ImageView imageView = viewHolder.imageViewHolder;
        imageView.setImageResource(galleryItem.getPhotoID());

    }

    @Override
    public int getItemCount () {
        return mGallery.size();
    }
}