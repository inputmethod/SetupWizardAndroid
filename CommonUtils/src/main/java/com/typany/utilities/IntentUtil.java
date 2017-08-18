package com.typany.utilities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * Created by yangfeng on 2017/8/18.
 */

public final class IntentUtil {
    private static final String TAG = IntentUtil.class.getSimpleName();

    private IntentUtil() {
        // helper class, should not instance
    }

    public static void openBrowser(Activity context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "");
        }
    }
}
