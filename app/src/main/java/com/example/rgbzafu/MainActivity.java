package com.example.rgbzafu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.internal.ViewUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.DriverPropertyInfo;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        mContainer = findViewById(R.id.cl1);
//        LinearLayout simpleItem = new LinearLayout(this);
        TextView sexTextView = new TextView(this);
//        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams();
//        params.leftMargin = 10;
//        sexTextView.setLayoutParams(params);
        sexTextView.setBackgroundColor(Color.rgb(255, 0, 0));
        sexTextView.setText("color");
        sexTextView.setTextSize(30);
        sexTextView.setTextColor(Color.rgb(255, 255, 255));
        mContainer.addView(sexTextView, 1);
    }
}