package com.veriqus.kolekcja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @OnClick(R.id.galleryButton)
    public void goToGalleryActivity() {
        goToActivity(GalleryActivity.class);
    }
    @OnClick(R.id.trackListButton)
    public void goToTrackActivity() {
        goToActivity(TrackListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    private void goToActivity(Class c){
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

}
