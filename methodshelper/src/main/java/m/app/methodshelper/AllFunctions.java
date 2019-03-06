package m.app.methodshelper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.format.DateFormat;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
public class AllFunctions {

    static Context context;

    public static final String timeFormat = "hh:mm a";
    public static final String DATE_FORMAT_ddMMyy = "ddMMyy";
    public static final String DATE_FORMAT_yyyy_dash_MM_dash_dd = "yyyy-MM-dd";
    public static final String DATE_FORMAT_dd_dash_MMM_dash_yyyy = "dd-MMM-yyyy";
    public static final String DATE_FORMAT_dd_dash_MM_dash_yyyy = "dd-MM-yyyy";
    public static final String DATE_FORMAT_dd_slash_mm_slash_yyyy = "dd/MM/yyyy";
    public static final String DATE_FORMAT_EEE_space_comma_d_space_LLL = "EEE, d LLL";
    public static final String DATE_FORMAT_d_space_LLL = "d LLL";
    public static final String DATE_FORMAT_d_space_LLL_space_yyyy = "d LLL yyyy";
    public static final String DATE_FORMAT_dd = "dd";
    public static final String DATE_FORMAT_MMMM_yyyy = "MMMM yyyy";
    public static final String DATE_FORMAT_MM_YYYY = "MM";
    public static final String DATE_FORMAT_EEEE = "EEEE";
    public static final String DATE_FORMAT_dd_space_MM_space_yyyy = "dd MM yyyy";
    public static final String DATE_FORMAT_dd_space_MMM_space_yyyy = "dd MMM yyyy";
    public static final String DATE_FORMAT_dd_dot_MMM_dot_yyyy = "dd.MMM.yyyy";
    public static final String DATE_FORMAT_yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_dd_dash_MMM_dash_yyyy_comma_HH_colon_mm_colon_am_pm = "dd-MMM-yyyy, HH:mm a";

    public static void initialize(Context context){
        AllFunctions.context = context;
    }

    public static void changeStatusBarColor(Activity activity, String color) {  // Color must be in hexadecimal format
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }

    public static void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showMessage(Context context, String msg, int length) {
        Toast.makeText(context, msg, length).show();
    }

    static Context getContext(){
        return AllFunctions.context;
    }

    public static String getFormattedTime(String unixTime) {
        Calendar smsTime = Calendar.getInstance();
        smsTime.setTimeInMillis(Long.parseLong(unixTime));

        Calendar now = Calendar.getInstance();

        if (now.get(Calendar.DATE) == smsTime.get(Calendar.DATE)) {
            return /*getContext().getString(R.string.text_today_at) + " " + */DateFormat.format(timeFormat, smsTime).toString();
        } else if (now.get(Calendar.DATE) - smsTime.get(Calendar.DATE) == 1) {
            return "Yesterday"/*+ " " + DateFormat.format(timeFormat, smsTime)*/;
        } else if (now.get(Calendar.DATE) - smsTime.get(Calendar.DATE) < 7) {
            return DateFormat.format(DATE_FORMAT_EEEE, smsTime).toString() /*+ " at " + DateFormat.format(timeFormat, smsTime).toString()*/;
        } else {
            return DateFormat.format(DATE_FORMAT_dd_slash_mm_slash_yyyy, smsTime).toString()/* + " at " + DateFormat.format(timeFormat, smsTime).toString()*/;
        }
    }

    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
    static public String firstLetterCaps(String data) {
        if (data.length() == 0) {
            return data;
        } else {
            data = Character.toUpperCase(data.charAt(0)) + data.substring(1);
        }
        return data;
    }

    public static Date getDateFromFormat(String dateString, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return date;
        }
    }

    public static int calculateAge(String date_of_birth) {
        try {
            Date date = new Date();
            int year = date.getYear();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_dd_space_MMM_space_yyyy);
            date = simpleDateFormat.parse(date_of_birth);
            return year - date.getYear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 27;
    }
}
