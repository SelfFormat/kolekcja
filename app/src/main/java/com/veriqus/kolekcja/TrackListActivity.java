package com.veriqus.kolekcja;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class TrackListActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer = new MediaPlayer();
    ArrayList<TrackItem> trackList;
    ImageButton play;
    ImageButton stop;
    ImageButton next;
    ImageButton previous;

    static Boolean stateOfPlayButton = false;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_list);

        trackList = new ArrayList<>();
        trackList.add(new TrackItem("Intensity", Uri.parse("android.resource://"+getPackageName()+"/raw/intensity")));
        trackList.add(new TrackItem("Hourglass", Uri.parse("android.resource://"+getPackageName()+"/raw/hourglass")));

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view_player);
        TrackAdapter adapter = new TrackAdapter(this, trackList, mediaPlayer);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        play = (ImageButton) findViewById(R.id.playButton);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer==null) {
                    Toast.makeText(getApplicationContext(), "pick some track", Toast.LENGTH_SHORT).show();
                    changeButton(true);
                }
                else if (mediaPlayer.isPlaying()) {
                    try {
                        pause();
                        changeButton(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if ((!mediaPlayer.isPlaying()) && mediaPlayer!=null) {
                    try {
                        mediaPlayer.start();
                        changeButton(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "pick some track", Toast.LENGTH_SHORT).show();
                }
            }
        });

        stop = (ImageButton) findViewById(R.id.stopButton);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer==null) {
                    Toast.makeText(getApplicationContext(), "pick some track", Toast.LENGTH_SHORT).show();
                    changeButton(true);
                }
                else if (mediaPlayer.isPlaying()) {
                    try {
                        stop();
                        Toast.makeText(getApplicationContext(), "stop", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "pick some track", Toast.LENGTH_SHORT).show();
                }
            }
        });


        next = (ImageButton) findViewById(R.id.nextTrackButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer==null) {
                    Toast.makeText(getApplicationContext(), "pick some track", Toast.LENGTH_SHORT).show();
                    changeButton(true);
                }
                else if (mediaPlayer.isPlaying() || mediaPlayer.getCurrentPosition() > 0) {
                    Log.i("pos", mediaPlayer.getCurrentPosition()+"");
                    try {
                        stop();
                        Toast.makeText(getApplicationContext(), "stop", Toast.LENGTH_SHORT).show();
                        if(position<trackList.size()-1) {
                            player(trackList.get(position+1).getTrack());
                            changeButton(true);
                            position++;

                        } else {
                            Toast.makeText(getApplicationContext(), "no next song", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "pick some track", Toast.LENGTH_SHORT).show();
                }
            }
        });

        previous = (ImageButton) findViewById(R.id.prevTrackButton);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer==null) {
                    Toast.makeText(getApplicationContext(), "pick some track", Toast.LENGTH_SHORT).show();
                    changeButton(true);
                }
                else if (mediaPlayer.isPlaying() || mediaPlayer.getCurrentPosition() > 0) {
                    try {
                        stop();
                        Toast.makeText(getApplicationContext(), "stop", Toast.LENGTH_SHORT).show();
                        if(position>0) {
                            player(trackList.get(position-1).getTrack());
                            changeButton(true);
                            position--;
                        } else {
                            Toast.makeText(getApplicationContext(), "no previous song", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "pick some track", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public void changeButton(boolean isPlaying){
        if(isPlaying){
            play.setImageResource(R.drawable.pause);
            stateOfPlayButton = true;
        } else {
            play.setImageResource(R.drawable.play);
            stateOfPlayButton = false;
        }
    }

    public void player(Uri uri){
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(getApplicationContext(), uri);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        try {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            mediaPlayer = new MediaPlayer();
            changeButton(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void pause(){
        mediaPlayer.pause();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("state", stateOfPlayButton);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onRestoreInstanceState(savedInstanceState);
        stateOfPlayButton = savedInstanceState.getBoolean("state");
        changeButton(stateOfPlayButton);
    }
}