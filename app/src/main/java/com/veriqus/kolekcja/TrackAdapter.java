package com.veriqus.kolekcja;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.R.drawable.ic_media_pause;
import static android.R.drawable.ic_media_play;

/**
 * Created by krzysztofmarczewski on 22.10.2017.
 */

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {

        private List<TrackItem> mTracks;
        private Context mContext;
        MediaPlayer mediaPlayer = new MediaPlayer();


    public TrackAdapter (Context context, List<TrackItem> trackItems) {
            mTracks = trackItems;
            mContext = context;
        }

    private Context getContext() {
        return mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView trackNameView;
        private Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            trackNameView = (TextView) itemView.findViewById(R.id.titleText);
        }

        public ViewHolder(Context context, View itemView) {

            super(itemView);
            trackNameView = (TextView) itemView.findViewById(R.id.titleText);
            this.context = context;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            Toast.makeText(getContext(), position+"", Toast.LENGTH_SHORT).show();
            ImageButton play = (ImageButton) view.findViewById(R.id.playButton);

            if (mediaPlayer==null) {
                mediaPlayer = new MediaPlayer();
                player(mTracks.get(position).getTrack());
                play.setImageResource(ic_media_pause);
            }
            else if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                play.setImageResource(ic_media_play);

            }
            else {
                player(mTracks.get(position).getTrack());
                play.setImageResource(ic_media_pause);
            }
        }
    }


    public void player(Uri uri){
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(getContext(), uri);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public TrackAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View trackView = inflater.inflate(R.layout.track_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(context, trackView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder (TrackAdapter.ViewHolder viewHolder,int position){
        TrackItem trackItem = mTracks.get(position);

        TextView textView = viewHolder.trackNameView;
        textView.setText(trackItem.getTitle());

    }

    @Override
    public int getItemCount () {
        return mTracks.size();
    }
}
