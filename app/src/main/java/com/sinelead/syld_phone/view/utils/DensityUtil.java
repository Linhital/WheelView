package com.sinelead.syld_phone.view.utils;

import android.content.Context;

public class DensityUtil {

    public static int dp2px(Context context, int dp) {
        int px = DecimalUtil.multiply(context.getResources().getDisplayMetrics().density, dp);
        return px;
    }

    public static int px2dp(Context context, int px) {
        int dp = DecimalUtil.divide(px, context.getResources().getDisplayMetrics().density);
        return dp;
    }
}
