package com.example.vproject.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;

public class ThumbImageView extends androidx.appcompat.widget.AppCompatImageView {
    public File videoFile;

    public ThumbImageView(@NonNull Context context) {
        super(context);
    }

    public ThumbImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ThumbImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ThumbImageView(@NonNull Context context, File videoFile) {
        super(context);
        this.videoFile = videoFile;
    }
}
