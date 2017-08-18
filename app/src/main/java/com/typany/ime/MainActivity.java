package com.typany.ime;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.typany.utilities.IntentUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupGif();

        findViewById(R.id.tvHello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browseWebsite();
            }
        });
    }

    private void setupGif() {

        ImageView emptyGif = (ImageView) findViewById(R.id.gif_empty);

        LayoutParams empty;

        int width = getResources().getDisplayMetrics().widthPixels;
        if (!getScreenPortMode()) {
            width *= 3;
            width /= 4;
            empty = new LayoutParams(226 * width / 1080, 146 * width / 1080);
        } else {
            empty = new LayoutParams(336 * width / 1080, 236 * width / 1080);
        }

        empty.addRule(RelativeLayout.CENTER_HORIZONTAL);
        if (!getScreenPortMode()) {
            empty.setMargins(0, 0, 0, 0);
        } else {
            empty.setMargins(0, 76 * width / 1080, 0, 0);
        }

        emptyGif.setLayoutParams(empty);
//        Glide.with(this).onDestroy();
//        Glide.get(this).with(this)
////                .load(R.mipmap.emojimaker)
//                .load(R.mipmap.downloaded)
////                .load(Uri.parse("http://i.kinja-img.com/gawker-media/image/upload/s--B7tUiM5l--/gf2r69yorbdesguga10i.gif"))
//                .asGif()
//                .priority(Priority.HIGH)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .into(emptyGif);
//        emptyGif.setBackground(null);

        Glide.with(this)
                .load("http://i.kinja-img.com/gawker-media/image/upload/s--B7tUiM5l--/gf2r69yorbdesguga10i.gif")
//                .load(R.mipmap.downloaded)
//                .thumbnail(Glide.with(this).load(ImageConfig.URL_JPEG))
                .thumbnail(Glide.with(this).load(R.drawable.setup_keyboard))
                .listener(new RequestListener<Drawable>() {

                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target,
                                                boolean isFirstResource) {
                        Log.e(TAG, "onLoadFailed --->" + e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        Log.e(TAG, "onResourceReady---> " + resource);
                        return false;
                    }
                })
                .into(emptyGif);
    }

    private boolean getScreenPortMode() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    private void browseWebsite() {
        IntentUtil.openBrowser(this, "http://www.typany.com/");
    }
}
