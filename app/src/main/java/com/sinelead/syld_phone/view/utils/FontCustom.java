package com.sinelead.syld_phone.view.utils;

import android.content.Context;
import android.graphics.Typeface;

public class FontCustom {
    private static final String FZSTK = "FZSTK.TTF";
    private static Typeface tf;

    public static Typeface setFont(Context context, String path) {
        if (tf == null) {
            tf = Typeface.createFromAsset(context.getAssets(), FZSTK);
        }
        return tf;
    }
}
