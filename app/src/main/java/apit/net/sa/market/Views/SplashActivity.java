package apit.net.sa.market.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import apit.net.sa.market.AppConsts.App;
import apit.net.sa.market.R;

/**
 * Splash activity.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        App.setContext(this);

        new Handler().postDelayed(() -> {
            /* Create an Intent that will start the MainActivity. */
            Intent mainIntent = new Intent(SplashActivity.this, SlidingActivity.class);
            startActivity(mainIntent);
            finish();
        }, 3000);
    }
}
