package com.typany.ime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.typany.utilities.IntentUtil;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tvHello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browseWebsite();
            }
        });
    }

    private void browseWebsite() {
        IntentUtil.openBrowser(this, "http://www.typany.com/");
    }
}
