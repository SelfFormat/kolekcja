package com.veriqus.kolekcja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhotoZoomActivity extends AppCompatActivity {

    ArrayList<GalleryItem> gallery;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_zoom);
        position = getIntent().getIntExtra("position", 1);

        gallery = GalleryItem.makeGallery();

        final ImageView fullImage = (ImageView) findViewById(R.id.full_image);
        fullImage.setImageResource(gallery.get(position).getPhotoID());


        Button next = (Button) findViewById(R.id.nextButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position<gallery.size()-1) {
                    position++;
                    fullImage.setImageResource(gallery.get(position).getPhotoID());
                } else Toast.makeText(getApplicationContext(), "No more Photo", Toast.LENGTH_SHORT).show();
            }
        });

        Button previous = (Button) findViewById(R.id.previousButton);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position>0) {
                    position--;
                    fullImage.setImageResource(gallery.get(position).getPhotoID());
                } else Toast.makeText(getApplicationContext(), "No more Photo", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
