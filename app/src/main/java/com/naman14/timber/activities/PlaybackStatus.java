package com.naman14.timber.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.naman14.timber.activities.BaseActivity;

import java.lang.ref.WeakReference;

public class PlaybackStatus extends BroadcastReceiver {
    private final WeakReference<BaseActivity> activityWeakReference;

    public PlaybackStatus(BaseActivity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        BaseActivity activity = activityWeakReference.get();
        if (activity != null) {
            // Handle the received intent action (e.g., update UI based on the music playback status)
            String action = intent.getAction();
            if (action != null) {
                switch (action) {
                    case MusicService.META_CHANGED:
                        activity.onMetaChanged();
                        break;
                    case MusicService.PLAYSTATE_CHANGED:
                        // Update play/pause button state if applicable
                        break;
                    // Handle other actions as necessary
                }
            }
        }
    }
}
