package com.example.vproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.vproject.R;

import java.io.File;

public class PlaybackActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playback);

        VideoView videoView = findViewById(R.id.videoViewA);
        videoView.setVideoPath(getIntent().getExtras().getString("videoPath"));

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);
        videoView.setVisibility(VideoView.VISIBLE);
        videoView.start();
    }
}