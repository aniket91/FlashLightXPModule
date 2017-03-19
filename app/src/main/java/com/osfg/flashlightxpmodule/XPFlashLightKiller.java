package com.osfg.flashlightxpmodule;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by athakur on 3/20/17.
 */

public class XPFlashLightKiller implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        if (!loadPackageParam.packageName.equals("com.osfg.flashlight"))
            return;

        XposedBridge.log("Loaded app: " + loadPackageParam.packageName);

        XposedHelpers.findAndHookMethod("com.osfg.flashlight.FlashLightActivity", loadPackageParam.classLoader, "isFlashSupported", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                // this will be called before the clock was updated by the original method
            }
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                // this will be called after the clock was updated by the original method
                XposedBridge.log("Hooked method isFlashSupported of class com.osfg.flashlight.FlashLightActivity");
                param.setResult(false);
            }
        });
    }
}
