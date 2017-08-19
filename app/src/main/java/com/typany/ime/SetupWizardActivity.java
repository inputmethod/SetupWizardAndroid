package com.typany.ime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.typany.floatwindow.TipViewController;
import com.typany.floatwindow.ViewContainer;
import com.typany.mitoast.BottomToast;
import com.typany.mitoast.ToastMiui;
import com.typany.utilities.InputMethodHelper;
import com.typany.utilities.IntentUtil;

/**
 * Created by yangfeng on 2017/8/18.
 */

public class SetupWizardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_wizard_layout);

        setupButtons();
    }

    private void setupButtons() {
        findViewById(R.id.wizard_button_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstAction();
            }
        });
    }

    private void firstAction() {
        IntentUtil.navigateActivity(this, MainActivity.class);

        InputMethodHelper.openSettings(this);
//        createFloatView();
        findViewById(R.id.wizard_button_1).postDelayed(new Runnable() {
            @Override
            public void run() {
                showCustomizeToast();
            }
        }, 1000);
    }

    private Toast toast;
    private void showCustomizeToast() {
        if (null == toast) {
            toast = BottomToast.makeText(this, "This is a TOAST.", Toast.LENGTH_LONG);
//            toast = new Toast(this);
//            toast.setDuration(Toast.LENGTH_LONG);
//            View view = getLayoutInflater().inflate(R.layout.pop_view, null);
//            view.setOnKeyListener(new View.OnKeyListener() {
//                @Override
//                public boolean onKey(View v, int keyCode, KeyEvent event) {
//                    return false;
//                }
//            });
//            toast.setView(view);
//            toast.setGravity(Gravity.BOTTOM, 0, 0);
        }
        toast.show();
//        ViewContainer view = (ViewContainer) View.inflate(this, R.layout.pop_view, null);
//        ToastMiui toastMiui = ToastMiui.MakeText(this,"仿MIUI的带有动画的Toast",ToastMiui.LENGTH_LONG);
//        toastMiui.show();
    }


    private TipViewController mTipViewController;

    private void createFloatView() {
        if (mTipViewController != null) {
            mTipViewController.updateContent("find typany keyboard and enable it");
        } else {
            mTipViewController = new TipViewController(getApplication(), "check and select typany keyboard");
//            mTipViewController.setViewDismissHandler(this);
            mTipViewController.show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent. KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (null != toast) {
                toast.cancel();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTipViewController != null) {
            mTipViewController.dismiss();
//            mTipViewController.setViewDismissHandler(null);
            mTipViewController = null;
        }
    }

}
