package com.typany.ime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.typany.floatwindow.TipViewController;
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
        createFloatView();
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
    protected void onDestroy() {
        super.onDestroy();
        if (mTipViewController != null) {
            mTipViewController.dismiss();
//            mTipViewController.setViewDismissHandler(null);
            mTipViewController = null;
        }
    }
}
