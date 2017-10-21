package com.veriqus.kolekcja;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.drawable.ic_media_pause;
import static android.R.drawable.ic_media_play;

public class TrackListActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer = new MediaPlayer();
    ArrayList<TrackItem> trackList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_list);

//        final Uri uri = Uri.parse("android.resource://"+getPackageName()+"/raw/intensity");

        //populate array
        trackList = new ArrayList<>();
        trackList.add(new TrackItem("Tap Me", Uri.parse("android.resource://"+getPackageName()+"/raw/tapme")));
        trackList.add(new TrackItem("Intensity", Uri.parse("android.resource://"+getPackageName()+"/raw/intensity")));
        trackList.add(new TrackItem("Hourglass", Uri.parse("android.resource://"+getPackageName()+"/raw/hourglass")));

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view_player);
        TrackAdapter adapter = new TrackAdapter(this, trackList);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        final ImageButton play = (ImageButton) findViewById(R.id.playButton);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer==null) {
                    Toast.makeText(getApplicationContext(), "pick some track", Toast.LENGTH_SHORT).show();
                    play.setImageResource(ic_media_pause);
                }
                else if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                    play.setImageResource(ic_media_play);

                }
                else {
                    Toast.makeText(getApplicationContext(), "pick some track", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

//    public void player(Uri uri){
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        try {
//            mediaPlayer.setDataSource(getApplicationContext(), uri);
//            mediaPlayer.prepare();
//            mediaPlayer.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}