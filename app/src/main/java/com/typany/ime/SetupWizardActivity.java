package com.typany.ime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
    }
}
