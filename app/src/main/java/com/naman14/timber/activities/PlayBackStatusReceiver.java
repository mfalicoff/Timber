package com.naman14.timber.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.naman14.timber.MusicService;
import com.naman14.timber.R;

public class PlaybackStatusReceiver extends BroadcastReceiver {

    private final Context context;

    public PlaybackStatusReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) return;

        switch (action) {
            case MusicService.META_CHANGED:
                // Handle meta changed action
                break;
            case MusicService.PLAYSTATE_CHANGED:
                // Handle play state changed
                break;
            case MusicService.PLAYLIST_CHANGED:
                // Handle playlist changed
                break;
            case MusicService.TRACK_ERROR:
                String errorMsg = context.getString(R.string.error_playing_track,
                        intent.getStringExtra(MusicService.TrackErrorExtra.TRACK_NAME));
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
