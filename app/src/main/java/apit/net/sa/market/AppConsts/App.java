package apit.net.sa.market.AppConsts;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * Here is app context and we can use it at any where in app.
 */
@SuppressLint("Registered")
public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    /**
     * Gets context.
     *
     * @return the context
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * Sets context.
     *
     * @param context the context
     */
    public static void setContext(Context context) {
        mContext = context;
    }



}
