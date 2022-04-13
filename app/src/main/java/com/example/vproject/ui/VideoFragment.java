package com.example.vproject.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.vproject.R;
import com.example.vproject.storageinteraction.StorageHandler;

import java.io.File;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public VideoFeedActivity activity;

    public int createViewsForFiles(File[] files) {
        Random random = new Random();
        LinearLayoutCompat videoLayout1 = activity.findViewById(R.id.layoutVideoFragment1);
        LinearLayoutCompat videoLayout2 = activity.findViewById(R.id.layoutVideoFragment2);
        boolean addToFirstVideoLayout = true;
        for (File file : files) {
            if (!StorageHandler.isVideoFile(file.getPath())) {
                continue;
            }
            ImageView imageView = new ImageView(activity);
            imageView.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, 300));
            Glide.with(activity).load(file).thumbnail().centerCrop().into(imageView);
            imageView.setBackgroundColor(getResources().getColor(R.color.mainVariant));
            imageView.setPadding(10, 10, 10, 10);
            //TODO: Optimise on click listener
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*
                    PlaybackFragment playbackFragment = new PlaybackFragment(activity, file);
                    FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.videoConstraintLayout, playbackFragment);
                    fragmentTransaction.commitNow();
                    playbackFragment.setupVideo();
                    */
                    Intent intent = new Intent(activity, PlaybackActivity.class);
                    intent.putExtra("videoPath", file.getPath());
                    startActivity(intent);
                }
            });
            if (addToFirstVideoLayout) {
                videoLayout1.addView(imageView);
            } else {
                videoLayout2.addView(imageView);
            }
            addToFirstVideoLayout = !addToFirstVideoLayout;
        }
        return 0;
    }

    public VideoFragment(VideoFeedActivity activity) {
        this.activity = activity;
    }

    public VideoFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false);
    }
}