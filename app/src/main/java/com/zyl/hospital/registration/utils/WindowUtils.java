package com.zyl.hospital.registration.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by zhouyou on 2016/6/24.
 * Class desc:
 *
 * 与屏幕相关的工具类，
 * 可以方便的设置全屏模式，
 * 可以得到屏幕的宽度高度
 */
public class WindowUtils {

    private WindowUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 设置当前界面为全屏模式
     */
    public static void setFullScreen(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    /**
     * 如果当前为全屏，那么取消全屏模式，回到正常的模式
     */
    public static void cancelFullScreen(Activity activity) {
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 判断当前手机是否是全屏
     *
     * @return 如果是true，那么当前就是全屏
     */
    public static boolean isFullScreen(Activity activity) {
        int flag = activity.getWindow().getAttributes().flags;
        if ((flag & WindowManager.LayoutParams.FLAG_FULLSCREEN)
                == WindowManager.LayoutParams.FLAG_FULLSCREEN) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断当前屏幕是否是横屏
     *
     * @param activity 当前的activity
     * @return 如果true就是竖屏
     */
    public static boolean isVerticalScreen(Activity activity) {
        int flag = activity.getResources().getConfiguration().orientation;
        if (flag == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取顶部状态栏高度
     *
     * @return 顶部状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = -1;
        // 获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            // 根据资源ID获取响应的尺寸值
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 推荐的获取屏幕长宽的方式,但需要API13
     *
     * @return 装载了屏幕长宽的数组，int[0] = width,int[1] = height
     */
    @SuppressLint("NewApi")
    public static int[] getWindow_WH(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return new int[]{size.x, size.y};
    }

    /**
     * 获取屏幕长宽的方式(仅在低版本中使用)
     *
     * @return 装载了屏幕长宽的数组，int[0] = width,int[1] = height
     */
    @Deprecated
    public static int[] getWindow_wh(Activity activity) {
        int w = activity.getWindowManager().getDefaultDisplay().getWidth();//获得手机屏幕的宽度
        int h = activity.getWindowManager().getDefaultDisplay().getHeight();//获得手机屏幕的高度
        return new int[]{w, h};
    }

    /**
     * 获得屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕宽高度
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

}
