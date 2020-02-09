package apit.net.sa.market.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import apit.net.sa.market.AppConsts.App;
import apit.net.sa.market.Models.Category;
import apit.net.sa.market.R;

public class Utils {
    /**
     * checking if there is internet or not
     *
     * @return boolean
     */
    public static Boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) App.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        assert conMgr != null;
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();
        if (networkInfo == null)
            return false;
        if (!networkInfo.isConnected())
            return false;
        return networkInfo.isAvailable();
    }

    /**
     * Alart view to user to notify him about any changes.
     *
     * @param massage massage need to make user see.
     */
    @SuppressLint("RtlHardcoded")
    public static void alart(String massage) {
        if (!((Activity) App.getContext()).isFinishing() && (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(App.getContext());
            builder1.setMessage(Html.fromHtml("<font color='#FF7F27'>" + massage + "</font>"));
            builder1.setCancelable(true);

            builder1.setPositiveButton("Close",
                    (dialog, id) -> dialog.cancel());

            AlertDialog alert11 = builder1.create();
            alert11.show();

            Objects.requireNonNull(alert11.getWindow()).setBackgroundDrawableResource(R.drawable.card_background);
            alert11.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            Button pbutton = alert11.getButton(DialogInterface.BUTTON_POSITIVE);
            //Set positive button background
            //pbutton.setBackgroundColor(App.getContext().getResources().getColor(R.color.colorPrimaryDark));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                pbutton.setBackground(App.getContext().getResources().getDrawable(R.drawable.radious_button));
            }
            //Set positive button text color
            pbutton.setTextColor(Color.WHITE);
            //pbutton.setGravity(View.FOCUS_LEFT);

            final LinearLayout lParent = (LinearLayout) pbutton.getParent();
            // Ensure the Parent of the Buttons aligns it's contents to the right.
            lParent.setGravity(Gravity.RIGHT);
            // Hide the LeftSpacer. (Strict dependence on the order of the layout!)
            lParent.getChildAt(1).setVisibility(View.GONE);
        } else Toast.makeText(App.getContext(), massage, Toast.LENGTH_LONG).show();
    }

    /**
     * Get new shared preferences object.
     *
     * @param context current context.
     * @return shared preferences object.
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("APP_PREF", Context.MODE_PRIVATE);
    }

    /**
     * Save list of categories in shared preferences.
     *
     * @param context current context.
     * @param key     key of saved value.
     * @param value   value will be saved (list of categories).
     */
    public static void storeSerializableList(Context context, String key, List<Category> value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        if (value != null) {
            List<String> valueString = new ArrayList<>();
            Gson gson = new Gson();
            for (Category c : value) {
                String json = gson.toJson(c);
                valueString.add(json);
            }
            Set<String> items = new HashSet<>(valueString);
            editor.putStringSet(key, items);
        } else
            editor.putStringSet(key, null);
        editor.apply();
    }

    /**
     * Get list of categories by key.
     *
     * @param context current context.
     * @param key     key of saved value.
     * @return list of categories..
     */
    public static List<Category> getList(Context context, String key) {
        //HashSet<String> items = (HashSet<String>) getSharedPreferences(context).getStringSet(key, null);
        List<String> result;
        HashSet<String> namesSet = (HashSet<String>) getSharedPreferences(context).getStringSet(key, null);
        if (namesSet != null && namesSet.size() > 0) {
            List<Category> categories = new ArrayList<>();
            result = new ArrayList<>(namesSet);
            Gson gson = new Gson();
            for (String s : result) {
                categories.add(gson.fromJson(s, Category.class));
            }
            return categories;
        } else return null;
    }
}
