package com.naman14.timber.activities;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.naman14.timber.ITimberService;
import com.naman14.timber.MusicPlayer;

import com.naman14.timber.cast.CastSessionManager;
import com.naman14.timber.listeners.MusicStateListener;
import com.naman14.timber.utils.Helpers;


import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity implements ServiceConnection, MusicStateListener {

    private MusicPlayer.ServiceToken mToken;
    private final ArrayList<MusicStateListener> mMusicStateListeners = new ArrayList<>();
    private CastSessionManager castSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mToken = MusicPlayer.bindToService(this, this);
        castSessionManager = new CastSessionManager(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        castSessionManager.registerSessionManagerListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        castSessionManager.unregisterSessionManagerListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mService == null) {
            mToken = MusicPlayer.bindToService(this, this);
        }
        onMetaChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mService = ITimberService.Stub.asInterface(service);
        onMetaChanged();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mService = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mToken != null) {
            MusicPlayer.unbindFromService(mToken);
            mToken = null;
        }
        mMusicStateListeners.clear();
    }

    @Override
    public void onMetaChanged() {
        for (MusicStateListener listener : mMusicStateListeners) {
            if (listener != null) {
                listener.onMetaChanged();
            }
        }
    }

    public void addMusicStateListener(MusicStateListener listener) {
        if (listener != null && !mMusicStateListeners.contains(listener)) {
            mMusicStateListeners.add(listener);
        }
    }

    public void removeMusicStateListener(MusicStateListener listener) {
        mMusicStateListeners.remove(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Menu inflation and setup...
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here...
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public String getATEKey() {
        return Helpers.getATEKey(this);
    }
}
