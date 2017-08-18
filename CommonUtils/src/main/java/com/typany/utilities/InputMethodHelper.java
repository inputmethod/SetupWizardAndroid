package com.typany.utilities;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;

/**
 * Created by yangfeng on 2017/8/18.
 */

public final class InputMethodHelper {
    public static void openSettings(Activity context) {
        Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS |
                Intent.FLAG_ACTIVITY_NO_HISTORY);
        IntentUtil.tryIntent(context, intent);
        context.startActivity(intent);
    }
}
