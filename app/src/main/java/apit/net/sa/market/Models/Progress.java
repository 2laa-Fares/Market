package apit.net.sa.market.Models;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatDialog;

import java.util.Objects;

import apit.net.sa.market.R;

/**
 * Custom App compat dialog.
 */
@SuppressLint("Registered")
public class Progress extends Application {

    /**
     * Activity where to show.
     */
    @SuppressLint("StaticFieldLeak")
    private static Activity activity;
    /**
     * App compat dialog which will custom.
     */
    AppCompatDialog progressDialog;

    /**
     * Init progress.
     * @param activity Activity where to show.
     */
    public Progress(Activity activity){
        Progress.activity = activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * Use to open ans set custom layout.
     */
    public void progressON() {

        if (activity == null || activity.isFinishing()) {
            return;
        }


        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new AppCompatDialog(activity);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.progress_loading);
            progressDialog.show();

        }


        final ImageView img_loading_frame = progressDialog.findViewById(R.id.iv_frame_loading);
        assert img_loading_frame != null;
        final AnimationDrawable frameAnimation = (AnimationDrawable) img_loading_frame.getBackground();
        img_loading_frame.post(frameAnimation::start);
    }

    /**
     * Use to close.
     */
    public void progressOFF() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}