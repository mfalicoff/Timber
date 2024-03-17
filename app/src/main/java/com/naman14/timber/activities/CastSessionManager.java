package com.naman14.timber.cast;

import android.content.Context;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.naman14.timber.activities.BaseActivity;

public class CastSessionManager {
    private final Context context;
    private SessionManager sessionManager;
    private CastSession castSession;
    private final SessionManagerListener<Session> sessionManagerListener = new SessionManagerListenerImpl();

    public CastSessionManager(Context context) {
        this.context = context;
        initialize();
    }

    private void initialize() {
        if (context instanceof BaseActivity) {
            this.sessionManager = CastContext.getSharedInstance(context).getSessionManager();
        }
    }

    public void registerSessionManagerListener() {
        if (sessionManager != null) {
            sessionManager.addSessionManagerListener(sessionManagerListener, Session.class);
        }
    }

    public void unregisterSessionManagerListener() {
        if (sessionManager != null) {
            sessionManager.removeSessionManagerListener(sessionManagerListener, Session.class);
        }
    }

    private class SessionManagerListenerImpl implements SessionManagerListener<Session> {
        @Override
        public void onSessionStarting(Session session) {
        }

        @Override
        public void onSessionStarted(Session session, String sessionId) {
            castSession = (CastSession) session;
            // Perform additional actions on session start
        }

        @Override
        public void onSessionEnded(Session session, int error) {
            if (session == castSession) {
                castSession = null;
                // Perform additional actions on session end
            }
        }

        // Implement other methods as necessary...
    }
}
