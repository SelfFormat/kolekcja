package com.veriqus.kolekcja;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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

        Button effect = (Button) findViewById(R.id.effect) ;
        effect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "No effect", Toast.LENGTH_SHORT).show();
                Bitmap bmp;
                BitmapDrawable abmp = (BitmapDrawable)fullImage.getDrawable();
                bmp = abmp.getBitmap();
                bmp = doGreyscale(bmp);
                fullImage.setImageBitmap(bmp);
            }
        });
    }

    //Bitmap to grayscale conversion from: https://xjaphx.wordpress.com/2011/06/21/image-processing-grayscale-image-on-the-fly/

    public static Bitmap doGreyscale(Bitmap src) {
        // constant factors
        final double GS_RED = 0.299;
        final double GS_GREEN = 0.587;
        final double GS_BLUE = 0.114;

        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // pixel information
        int A, R, G, B;
        int pixel;

        // get image size
        int width = src.getWidth();
        int height = src.getHeight();

        // scan through every single pixel
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get one pixel color
                pixel = src.getPixel(x, y);
                // retrieve color of all channels
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                // take conversion up to one single value
                R = G = B = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);
                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        // return final image
        return bmOut;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
