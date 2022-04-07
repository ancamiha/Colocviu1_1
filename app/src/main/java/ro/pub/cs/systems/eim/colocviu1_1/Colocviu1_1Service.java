package ro.pub.cs.systems.eim.colocviu1_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Colocviu1_1Service extends Service {

    ProcessingThread processingThread;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int instruction = intent.getIntExtra("instruction", -1);
        Log.d("[instruction]", String.valueOf(instruction));
        processingThread = new ProcessingThread(this, instruction);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}