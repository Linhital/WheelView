package com.sinelead.syld_phone.view.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

public class TimerTextView {
    private CountDownTimer timer;
    private TextView button;
    private CompleteListener listener;

    public TimerTextView(TextView buttons, final String defaultString, int max) {
        this.button = buttons;
        timer = new CountDownTimer(max * 1000, 1000) {
            @Override
            public void onTick(long time) {
                button.setText(time / 1000 + "s后重新获取");
            }

            @Override
            public void onFinish() {
                button.setEnabled(true);
                button.setText(defaultString);
                if (listener != null)
                    listener.onComplete();
            }
        };
    }

    public void start() {
        button.setEnabled(false);
        if (timer != null)
            timer.start();
    }

    public void stop() {
        if (timer != null)
            timer.cancel();
    }

    public void setListener(CompleteListener listener) {
        this.listener = listener;
    }

    public interface CompleteListener {
        void onComplete();
    }
}
