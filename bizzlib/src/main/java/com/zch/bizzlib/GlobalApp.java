/*
 * GlobalApp      2017-11-22
 * Copyright © gnt All Rights Reserved.
 *
 */
package com.zch.bizzlib;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zch.baselib.base.BaseApp;

/**
 * MobileManager 应用环境对象
 *
 * @author zch
 * @since 2017-11-22
 */
public class GlobalApp extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}