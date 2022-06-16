package com.tsign.aienterprise.camera.hk;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.tsign.aop.util.TsLog;
import com.tsign.module.lib.app.AppLib;
import com.tsign.module.lib.core.service.ServiceFactory;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class UnistallUtil {
    static String xmlPath = null;
    static String suPath = "/system/bin/su";
    private static final String[] rootRelatedDirs = new String[]{
            "/su", "/su/bin/su", "/sbin/su",
            "/data/local/xbin/su", "/data/local/bin/su", "/data/local/su",
            "/system/xbin/su",
            "/system/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su",
            "/system/bin/cufsdosck", "/system/xbin/cufsdosck", "/system/bin/cufsmgr",
            "/system/xbin/cufsmgr", "/system/bin/cufaevdd", "/system/xbin/cufaevdd",
            "/system/bin/conbb", "/system/xbin/conbb"};

    public static boolean hasRootPrivilege() {
        boolean hasRootDir = false;
        String[] rootDirs;
        int dirCount = (rootDirs = rootRelatedDirs).length;
        for (int i = 0; i < dirCount; ++i) {
            String dir = rootDirs[i];
            TsLog.e("suPath：" + dir);
            if ((new File(dir)).exists()) {
                hasRootDir = true;
                suPath = dir;
                break;
            }
        }
        return Build.TAGS != null && Build.TAGS.contains("test-keys") || hasRootDir;
    }

    public static String execCommand(String... command) {

        Process process = null;
        InputStream errIs = null;
        InputStream inIs = null;
        String result = "";
        try {
            process = new ProcessBuilder().command(command).start();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int read = -1;
            errIs = process.getErrorStream();
            while ((read = errIs.read()) != -1) {
                baos.write(read);
            }
            inIs = process.getInputStream();
            while ((read = inIs.read()) != -1) {
                baos.write(read);
            }
            result = new String(baos.toByteArray());
            if (inIs != null) inIs.close();
            if (errIs != null) errIs.close();
            process.destroy();
        } catch (IOException e) {
            result = e.getMessage();
        }
        return result;
    }

    /**
     * 卸载目标app
     */
    public static void uninstallApp(Context context, String pkg) {
        Log.e("UnistallAiGate:", "卸载云考勤。。。");
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + pkg));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static boolean checkAppInstalled(Context context, String pkgName) {
        if (TextUtils.isEmpty(pkgName) || null == context) {
            return false;
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    /**
     * 静默卸载app
     *
     * @param context
     * @param packageName app的包名
     * @throws IOException
     * @throws InterruptedException
     */

    public static void uninstallAppBackground(Context context, String packageName) throws IOException, InterruptedException {
        List<PackageInfo> packageInfos = context.getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);
        for (PackageInfo packageInfo1 : packageInfos) {

            if (packageName.equals(packageInfo1.packageName)) {
                Process process = Runtime.getRuntime().exec(suPath);
                String cmd = "pm uninstall -k \"" + packageName + "\"\n";
                TsLog.e("卸载：" + cmd);
                //TsLog.e("suPath："+suPath);

                process.getOutputStream().write(cmd.getBytes());
                process.waitFor();
                break;
            }
        }
    }


    /**
     * 57      * 应用安装、卸载处理
     * 58      *
     * 59      * @param args
     * 60      *            安装、卸载参数
     * 61      * @return Apk安装、卸载结果
     * 62
     */
    public static void unistallWithoutRoot(String apkName) {
        PackageInstaller packageInstaller = AppLib.getContext().getPackageManager().getPackageInstaller();
        packageInstaller.uninstall(apkName, null);
    }

    public static boolean scanAndFileExist(String dir) {
        String name = null, path = null;
        File sdRoot = new File(Environment.getExternalStorageDirectory() + "/" + dir);
        Collection<File> collection = FileUtils.listFiles(sdRoot, new String[]{"xml"}, true);
        for (File item : collection) {
            if ("configure.xml".equals(item.getName())) {
                name = item.getName();
                path = item.getPath();
            }
            Log.e("Name: ", item.getName() + "");
            Log.e("Path: ", item.getAbsolutePath() + "");
        }
        if (path != null && path.contains("configure.xml")) {
            xmlPath = path;
        }
        return null != name && null != path;

    }

    public static void copyXmlFileToEnterpriseDir() {
        String sourcePath = null;
        if (xmlPath != null) {
            sourcePath = xmlPath;
            String descPath = "/storage/emulated/0/aiEnterprise/configure.xml";
            try {
                if (fileCopy(sourcePath, descPath)) {
                    deleteSomethingWithConfigureXml(descPath);
                    TsLog.e("复制成功");
                    ServiceFactory.getInstance().getMqttPushService().restartAppBiz();
                } else {
                    TsLog.e("复制失败");
                }


            } catch (IOException e) {
                TsLog.e("复制失败");
                e.printStackTrace();
            }
        }
    }

    public static boolean fileCopy(String oldFilePath, String newFilePath) throws IOException {
//获得原文件流
        FileInputStream inputStream = new FileInputStream(new File(oldFilePath));
        byte[] data = new byte[1024];
//输出流
        FileOutputStream outputStream = new FileOutputStream(new File(newFilePath));
//开始处理流
        while (inputStream.read(data) != -1) {
            outputStream.write(data);
        }
        inputStream.close();
        outputStream.close();
        return true;
    }
    @SuppressLint("CheckResult")
    public static void deleteSomethingWithConfigureXml(String xmlFilePath) {
        //加载文件
        XMLUtil.loadWithDomRx(xmlFilePath)
                .subscribe(document -> {
                    //判断是否加载到文件
                    if (document != null && document.getDocumentElement() != null) {
                        //得到最外层节点（configure）
                        Element element=document.getDocumentElement();
                        NodeList nodeList = document.getDocumentElement().getChildNodes();
                        if (nodeList != null) {
                            //遍历node list
                            for (int i = 0; i < nodeList.getLength(); i++) {
                                Node node = nodeList.item(i);
                                //判断是否是idc和camera 然后删掉他
                                if (node.getNodeName() != null && node.getNodeName().equals("idc")) {
                                    element.removeChild(node);
                                }
                                if (node.getNodeName() != null && node.getNodeName().equals("camera")) {
                                    element.removeChild(node);
                                }
                            }
                            XMLUtil.saveXmlWithDom(document,"/storage/emulated/0/aiEnterprise/configure.xml");
                        }
                    }
                });
    }
    /**

     * 判断应用是否已经启动

     *

     * @param context 上下文对象

     * @param packageName 要判断应用的包名

     * @return boolean

     */

    public static boolean isAppAlive(Context context, String packageName) {
        ActivityManager activityManager =

                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();

        for (int i = 0; i < processInfos.size(); i++) {
            if (processInfos.get(i).processName.equals(packageName)) {
                return true;

            }

        }

        return false;

    }
}
