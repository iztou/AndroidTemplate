package gml.template.androidtemplate.tools.util;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gml on 2016/1/15.
 */
public class FileUtils {
    public static File getAppFolder() {
        if (!isSDCardAvailable()) {
            return null;
        }
        String folderName = "/app";
        File folder = new File(Environment.getExternalStorageDirectory(), folderName);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }

    public static void deleteFile(File file) {
        if (file == null) {
            return;
        }
        if (file.isFile() && file.exists()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                deleteFile(f);
            }
            file.delete();
        }
    }

    /**
     * 检测SD卡是否可用
     *
     * @return True if SD card is can be written, false otherwise.
     */
    public static boolean isSDCardAvailable() {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
//            MYUtils.showToastMessage("SD卡不可用");
            return false;
        }
        return true;
    }

    public static File createPhotoSavedFile() {
        File folder = getAppFolder();
        if (folder == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        String fname = sdf.format(new Date()).toString() + ".jpg";
        return new File(folder, fname);
    }

    public static String createMp3SavedFile() {
        File appFolder = getAppFolder();
        if (appFolder == null) {
            return null;
        }

        File voiceFolder = new File(appFolder, "voice");
        if (!voiceFolder.exists()) {
            voiceFolder.mkdirs();
        }

        String fname = "record.mp3";
        File voice = new File(voiceFolder, fname);

        return voice.getAbsolutePath();
    }

    public static File createProductShareImageFile(String productId) {
        File appFolder = getAppFolder();
        if (appFolder == null) {
            return null;
        }
        return new File(appFolder, "product-share-" + productId + ".jpg");
    }

    public static String readStringFromAsset(String filename) {
        try {
            //TODO editing
            InputStream inputStream = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringWriter writer = new StringWriter();

            String line = null;

            while ((line = reader.readLine()) != null) {
                writer.append(line);
            }

            reader.close();
            writer.close();

            return writer.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static File createRequestTimeSavedFile() {
        File appFolder = getAppFolder();
        if (appFolder == null) {
            return null;
        }

//        File voiceFolder = new File(appFolder,StaticConfig.USEHTTPS?"volley-https":"volley-http");
//        if (!voiceFolder.exists()) {
//            voiceFolder.mkdirs();
//        }
//
//        File[] files = voiceFolder.listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File pathname) {
//                if (pathname.exists() && pathname.isFile()) {
//                    if (pathname.length() < ByteConstants.MB) {
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });
//        File voice=null;
//        if (files==null||files.length==0){
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
//            String fname = sdf.format(new Date()).toString() + ".txt";
//            voice = new File(voiceFolder, "volley-request-"+ fname);
//        }else{
//            voice=files[0];
//        }
        return null;
    }

    public static File createPhotoFilterSavedFile(String fileName) {
        File folder = getAppFolder();
        if (folder == null) {
            return null;
        }

        File filterfolder = new File(folder,"filter");
        if (!filterfolder.exists()) {
            filterfolder.mkdirs();
        }

        String fname = fileName+ ".jpg";
        return new File(filterfolder, fname);
    }
}
