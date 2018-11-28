package com.magarex.memelord.utils;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.CountDownLatch;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

/**
 * Created by sahil on 14/11/18.
 **/
public class FirebaseQueryLiveData extends LiveData<DataSnapshot> {

    private static final String LOG_TAG = "FirebaseQueryLiveData";

    private final Query query;
    private final InternalValueEventListener listener = new InternalValueEventListener();

    public FirebaseQueryLiveData(Query query) {
        this.query = query;
    }

    @Override
    protected void onActive() {
        // super.onActive();
        Log.d(LOG_TAG, "onActive");
        query.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        // super.onInactive();
        Log.d(LOG_TAG, "onActive");
        query.removeEventListener(listener);

    }

    public DataSnapshot singleFetch(int limit) throws InterruptedException {
        final DataSnapshot[] snapshot = new DataSnapshot[1];
        CountDownLatch latch = new CountDownLatch(1);
        query.limitToFirst(limit).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                snapshot[0] = dataSnapshot;
                latch.countDown();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                latch.countDown();
            }
        });

        latch.await();

        return snapshot[0];
    }

    private class InternalValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(dataSnapshot);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(LOG_TAG, "Can't listen to query " + query, databaseError.toException());
        }
    }
}
