package com.veriqus.kolekcja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    ArrayList<GalleryItem> gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);
        gallery = GalleryItem.makeGallery();

        GalleryAdapter adapter = new GalleryAdapter(this, gallery);
        rv.setAdapter(adapter);

        rv.setLayoutManager(new GridLayoutManager(this,2));
    }

    public void goToActivity(Class c){
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}
