package com.tsign.aienterprise.camera.hk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.tsign.aop.util.TsLog;
import com.tsign.module.lib.app.AppLib;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class UninstallService extends Service {
    public UninstallService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        TsLog.e("uninstallService: onBind...");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        boolean root = UnistallUtil.hasRootPrivilege();
        if (root) {
            TsLog.e("有root权限");

            TsLog.e("uninstallService: onCreate...");
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (UnistallUtil.scanAndFileExist("/aiGate") && !UnistallUtil.scanAndFileExist("/aiEnterprise")) {
                        UnistallUtil.copyXmlFileToEnterpriseDir();
                    }
//                //如果存在云考勤则删除云考勤版本
//                if (UnistallUtil.checkAppInstalled(this,"com.tsign.smartdoor")){
//                    UnistallUtil.uninstallApp(this,"com.tsign.smartdoor");
//                }
//                //如果存在正式版则删除中间版本
//                if (UnistallUtil.checkAppInstalled(this,"com.tsign.aienterprise.tspad.xmys101")){
//                    UnistallUtil.uninstallApp(this,"com.tsign.aienterprise.tspad.xmys1018");
//                }

                    //如果存在云考勤则删除云考勤版本
                    //   if (UnistallUtil.checkAppInstalled(AppLib.getContext(),"com.tsign.ipcamera.hik")){
                    if (UnistallUtil.checkAppInstalled(AppLib.getContext(), "com.tsign.ipcamera")) {
                        if (UnistallUtil.isAppAlive(AppLib.getContext(),"com.tsign.ipcamera")){
                            UnistallUtil.unistallWithoutRoot( "com.tsign.ipcamera");
                        }
                    }
                    //如果存在正式版则删除中间版本
                    if (UnistallUtil.checkAppInstalled(AppLib.getContext(), "com.tsign.aienterprise.camera.hk")) {
                        if (UnistallUtil.isAppAlive(AppLib.getContext(),"com.tsign.aienterprise.camera.hk")){
                            UnistallUtil.unistallWithoutRoot( "com.tsign.aienterprise.camera.hk8");
                        }
                    }
                }
            }, 0, 3000);
        } else {
            TsLog.e("没有root权限");
        }


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        TsLog.e("uninstallService: onStartCommand...");
        return super.onStartCommand(intent, flags, startId);
    }
}