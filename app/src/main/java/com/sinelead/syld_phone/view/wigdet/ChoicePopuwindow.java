package com.sinelead.syld_phone.view.wigdet;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sinelead.syld_phone.R;

public class ChoicePopuwindow extends PopupWindow implements View.OnClickListener {

    protected TextView password;
    protected TextView cancle;
    protected TextView messageLogin;
    private Activity activity;
    private View view;
    private OnChoiceListener listener;

    public ChoicePopuwindow(Activity activity) {
        this.activity = activity;
        initView();
    }

    public void setListener(OnChoiceListener listener) {
        this.listener = listener;
    }

    private void initView() {
        view = LayoutInflater.from(activity).inflate(R.layout.popu_layout_choice, null);
        password = view.findViewById(R.id.password_for_back);
        cancle = view.findViewById(R.id.cancel);
        messageLogin = view.findViewById(R.id.message_login);
        cancle.setOnClickListener(this);
        password.setOnClickListener(this);
        messageLogin.setOnClickListener(this);
        setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.showPopupAnimation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);


        this.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                activity.getWindow().setAttributes(lp);
            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = view.findViewById(R.id.password_for_back).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    public void show() {
        // 产生背景变暗效果
        WindowManager.LayoutParams lp = activity.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        activity.getWindow().setAttributes(lp);
        showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void onClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.password_for_back:
                if (listener != null)
                    listener.onRetrieve(v);
                break;
            case R.id.message_login:
                if (listener != null)
                    listener.onMessage(v);
                break;
        }
    }

    public interface OnChoiceListener {
        void onRetrieve(View V);

        void onMessage(View v);
    }
}
