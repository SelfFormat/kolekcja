package com.veriqus.kolekcja;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by krzysztofmarczewski on 22.10.2017.
 */

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {

        private List<TrackItem> mTracks;
        private Context mContext;
        MediaPlayer mediaPlayer;


    public TrackAdapter (Context context, List<TrackItem> trackItems, MediaPlayer player) {
            mTracks = trackItems;
            mContext = context;
            mediaPlayer = player;
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

            if (mediaPlayer==null) {
                ((TrackListActivity)getContext()).player(mTracks.get(position).getTrack());
                ((TrackListActivity)getContext()).changeButton(true);
                ((TrackListActivity)getContext()).setPosition(position);

            } else {
                ((TrackListActivity)getContext()).changeButton(true);
                ((TrackListActivity)getContext()).player(mTracks.get(position).getTrack());
                ((TrackListActivity)getContext()).setPosition(position);
            }
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
