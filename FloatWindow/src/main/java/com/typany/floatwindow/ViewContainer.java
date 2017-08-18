package com.typany.floatwindow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.FrameLayout;

public class ViewContainer extends FrameLayout {

    public KeyEventHandler mKeyEventHandler;

    public ViewContainer(Context context) {
        super(context);
    }

    public ViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setKeyEventHandler(KeyEventHandler handler) {
        mKeyEventHandler = handler;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (mKeyEventHandler != null) {
            if (mKeyEventHandler.onKeyEvent(event)) {
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    public interface KeyEventHandler {
        boolean onKeyEvent(KeyEvent event);
    }
}
