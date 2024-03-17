package com.naman14.timber.adapters;

import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public abstract class AbstractSongAdapter extends RecyclerView.Adapter<AbstractSongAdapter.ItemHolder> {
    protected List<Song> arraylist;
    protected Activity mContext;
    protected String ateKey;

    public AbstractSongAdapter(Activity context, List<Song> arraylist) {
        this.arraylist = arraylist;
        this.mContext = context;
        this.ateKey = Helpers.getATEKey(context);
    }

    @Override
    public abstract ItemHolder onCreateViewHolder(ViewGroup viewGroup, int i);

    @Override
    public void onBindViewHolder(ItemHolder itemHolder, int i) {
        Song localItem = arraylist.get(i);

        itemHolder.title.setText(localItem.title);
        itemHolder.artist.setText(localItem.artistName);

        ImageLoader.getInstance().displayImage(TimberUtils.getAlbumArtUri(localItem.albumId).toString(),
                itemHolder.albumArt, new DisplayImageOptions.Builder().cacheInMemory(true)
                        .showImageOnLoading(R.drawable.ic_empty_music2).resetViewBeforeLoading(true).build());
    }

    @Override
    public int getItemCount() {
        return (null != arraylist ? arraylist.size() : 0);
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        protected TextView title, artist;
        protected ImageView albumArt, popupMenu;

        public ItemHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.song_title);
            this.artist = (TextView) view.findViewById(R.id.song_artist);
            this.albumArt = (ImageView) view.findViewById(R.id.albumArt);
        }
    }

    protected abstract void setOnPopupMenuListener(ItemHolder itemHolder, final int position);
}

