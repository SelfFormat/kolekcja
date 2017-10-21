package com.veriqus.kolekcja;

import java.util.ArrayList;

/**
 * Created by krzysztofmarczewski on 21.10.2017.
 */

class GalleryItem {

    int photoID;

    public GalleryItem(int photoID) {
        this.photoID = photoID;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public static ArrayList<GalleryItem> makeGallery(){
        ArrayList<GalleryItem> gallery = new ArrayList<GalleryItem>();
        gallery.add(new GalleryItem(R.drawable.a));
        gallery.add(new GalleryItem(R.drawable.b));
        gallery.add(new GalleryItem(R.drawable.c));
        gallery.add(new GalleryItem(R.drawable.d));
        gallery.add(new GalleryItem(R.drawable.e));
        gallery.add(new GalleryItem(R.drawable.a));
        gallery.add(new GalleryItem(R.drawable.b));
        gallery.add(new GalleryItem(R.drawable.c));
        gallery.add(new GalleryItem(R.drawable.d));
        gallery.add(new GalleryItem(R.drawable.e));
        return gallery;
    }
}
