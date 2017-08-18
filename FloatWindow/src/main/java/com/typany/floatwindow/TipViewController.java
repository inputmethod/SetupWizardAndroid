package com.typany.floatwindow;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public final class TipViewController implements ViewContainer.KeyEventHandler {

    private WindowManager mWindowManager;
    private Context mContext;
    private ViewContainer mWholeView;
    private View mContentView;
    private ViewDismissHandler mViewDismissHandler;
    private CharSequence mContent;
    private TextView mTextView;

    public TipViewController(Context application, CharSequence content) {
        mContext = application;
        mContent = content;
        mWindowManager = (WindowManager) application.getSystemService(Context.WINDOW_SERVICE);
    }

    public void setViewDismissHandler(ViewDismissHandler viewDismissHandler) {
        mViewDismissHandler = viewDismissHandler;
    }

    public void updateContent(CharSequence content) {
        mContent = content;
        mTextView.setText(mContent);
    }

    public void show() {

        ViewContainer view = (ViewContainer) View.inflate(mContext, R.layout.pop_view, null);

        // display content
        mTextView = (TextView) view.findViewById(R.id.pop_view_text);
        mTextView.setText(mContent);

        mWholeView = view;
        mContentView = view.findViewById(R.id.pop_view_content_view);

        // event listeners
//        mContentView.setOnClickListener(this);
//        mWholeView.setOnTouchListener(this);
        mWholeView.setKeyEventHandler(this);

        int w = WindowManager.LayoutParams.MATCH_PARENT;
        int h = WindowManager.LayoutParams.WRAP_CONTENT;

        int flags = 0;
        int type = 0;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //解决Android 7.1.1起不能再用Toast的问题（先解决crash）
            if (Build.VERSION.SDK_INT > 24) {
                type = WindowManager.LayoutParams.TYPE_PHONE;
            } else {
                type = WindowManager.LayoutParams.TYPE_TOAST;
            }
        } else {
            type = WindowManager.LayoutParams.TYPE_PHONE;
        }

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(w, h, type, flags, PixelFormat.TRANSLUCENT);
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                /*| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE*/;

        mWindowManager.addView(mWholeView, layoutParams);
    }

//    @Override
//    public void onClick(View v) {
//        removePoppedViewAndClear();
//        MainActivity.startForContent(mContext, mContent.toString());
//    }

    public void dismiss() {
        removePoppedViewAndClear();
    }

    private void removePoppedViewAndClear() {
        // remove view
        if (mWindowManager != null && mWholeView != null) {
            mWindowManager.removeView(mWholeView);
        }

        if (mViewDismissHandler != null) {
            mViewDismissHandler.onViewDismiss();
        }

        // remove listeners
        mContentView.setOnClickListener(null);
        mWholeView.setOnTouchListener(null);
        mWholeView.setKeyEventHandler(null);
    }

    /**
     * touch the outside of the content view, remove the popped view
     */
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        int x = (int) event.getX();
//        int y = (int) event.getY();
//        Rect rect = new Rect();
//        mContentView.getGlobalVisibleRect(rect);
//        if (!rect.contains(x, y)) {
//            removePoppedViewAndClear();
//        }
//        return false;
//    }
    @Override
    public boolean onKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            removePoppedViewAndClear();
            return true;
        }
        return false;
    }

    public interface ViewDismissHandler {
        void onViewDismiss();
    }
}
