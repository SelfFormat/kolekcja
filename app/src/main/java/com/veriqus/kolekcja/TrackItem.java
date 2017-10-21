package com.veriqus.kolekcja;

import android.net.Uri;

/**
 * Created by krzysztofmarczewski on 17.10.2017.
 */

public class TrackItem {

    String title;
    Uri track;

    public TrackItem(String title, Uri track) {
        this.title = title;
        this.track = track;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Uri getTrack() {
        return track;
    }

    public void setTrack(Uri track) {
        this.track = track;
    }
}
