package com.example.rgbzafu;

import static android.content.Intent.getIntentOld;
import static android.content.Intent.parseUri;

import android.content.Intent;
import android.content.res.XModuleResources;
import android.content.res.XResources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class HookTest implements IXposedHookZygoteInit,IXposedHookInitPackageResources {
    private static String MODULE_PATH = null;
    private TextView mContainer;
    int c = Color.rgb(255, 183, 197);

    public void initZygote(IXposedHookZygoteInit.StartupParam startupParam) throws Throwable {
        MODULE_PATH = startupParam.modulePath;
    }

    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {

        XModuleResources modRes = XModuleResources.createInstance(MODULE_PATH, resparam.res);
//        if (!resparam.packageName.equals("edu.zafu.uniteapp"))
        resparam.res.setReplacement("edu.zafu.uniteapp", "color", "primary", c);
        resparam.res.setReplacement("edu.zafu.uniteapp", "mipmap", "menu1_active", modRes.fwd(R.mipmap.menu1_p));
        resparam.res.setReplacement("edu.zafu.uniteapp", "mipmap", "menu2_active", modRes.fwd(R.mipmap.menu2_p));
        resparam.res.setReplacement("edu.zafu.uniteapp", "mipmap", "menu4_active", modRes.fwd(R.mipmap.menu4_p));
        resparam.res.setReplacement("edu.zafu.uniteapp", "mipmap", "menu5_active", modRes.fwd(R.mipmap.menu5_p));
        resparam.res.setReplacement("edu.zafu.uniteapp", "mipmap", "menu6_active", modRes.fwd(R.mipmap.menu6_p));
        resparam.res.setReplacement("edu.zafu.uniteapp", "mipmap", "logo_index", modRes.fwd(R.mipmap.dog));
        XposedBridge.log("layout  resNames.fullname:");
        resparam.res.hookLayout("edu.zafu.uniteapp", "layout", "fragment_contact", new XC_LayoutInflated() {
            @Override
            public void handleLayoutInflated(XC_LayoutInflated.LayoutInflatedParam liparam) throws Throwable {
                XposedBridge.log("layout  resNames.fullname:" + liparam.resNames.fullName);
                XposedBridge.log("layout  resNames.id:" + liparam.resNames.id);
                XposedBridge.log("layout  resNames.name:" + liparam.resNames.name);
                XposedBridge.log("layout  resNames.pkg:" + liparam.resNames.pkg);
                XposedBridge.log("layout  resNames.type:" + liparam.resNames.type);
                XposedBridge.log("layout  resNames.variant:" + liparam.variant);
                XposedBridge.log("layout  resNames.view:" + liparam.view);

                ConstraintLayout simpleItem = (ConstraintLayout) liparam.view;
                TextView sexTextView = new TextView(simpleItem.getContext());
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                sexTextView.setBackgroundColor(Color.rgb(0,0,0));
                sexTextView.setLayoutParams(params);
                sexTextView.setText("color");
                simpleItem.addView(sexTextView, 1, params);
            }
        });
    }
}