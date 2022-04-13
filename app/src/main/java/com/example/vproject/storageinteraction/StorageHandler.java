package com.example.vproject.storageinteraction;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.bumptech.glide.Glide;
import com.example.vproject.R;
import com.example.vproject.custom.ThumbImageView;
import com.example.vproject.ui.VideoFeedActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.net.URLConnection;
import java.util.Random;

public class StorageHandler {
    private static String videoFolder;

    public static File[] getVideoFiles(
            final String folderPath) {

        File directory = new File(Environment.getExternalStorageDirectory().toString());
        File videoDirectory = new File(folderPath);
        File[] files = videoDirectory.listFiles();

        try {
            Log.i("text", "Was able to create a file:" + directory.createNewFile());
            Log.i("text", "Can read directory: " + directory.canRead());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return files;
    }

    public static Void updateVideoFolderPath(String folderPath) {
        videoFolder = folderPath;
        return null;
    }

    public static boolean isVideoFile(String path) {
        String mimeType = URLConnection.guessContentTypeFromName(path);
        return mimeType != null && mimeType.startsWith("video");
    }

    public static String getVideoFolder() { return videoFolder; }
}
