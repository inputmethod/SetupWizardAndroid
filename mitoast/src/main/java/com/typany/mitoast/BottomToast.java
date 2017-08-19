package com.typany.mitoast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by yangfeng on 2017/8/19.
 */

public class BottomToast extends Toast {

//	private static TextView toastTextView;

    public BottomToast(Context context) {
        super(context);
    }

    public static Toast makeText(Context context, CharSequence text, int duration){
        Toast result = new Toast(context);

        //获取LayoutInflater对象
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //获得屏幕的宽度
        int width = wm.getDefaultDisplay().getWidth();

        //由layout文件创建一个View对象
        View view = inflater.inflate(R.layout.layout_bottom_toast, null);


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = 16;

        TextView toastTextView = (TextView) view.findViewById(R.id.tv_toast);
        //设置TextView的宽度为 屏幕宽度
        toastTextView.setLayoutParams(layoutParams);
        toastTextView.setText(text);

        result.setView(view);
        result.setGravity(Gravity.BOTTOM, 90, 0);
        result.setDuration(duration);

        return result;
    }

}
