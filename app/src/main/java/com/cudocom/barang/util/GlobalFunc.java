package com.cudocom.barang.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TimeZone;
import java.util.TreeMap;

public class GlobalFunc {

    @SuppressLint("HardwareIds")
    public static String GET_DEVICE_ID(Context ctx) {
        return Settings.Secure.getString(ctx.getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static String DECODE_NUMERIC_PRICE(String val) {
        String input = val;
        input = input.replace(",", "");
        return input;
    }

    public static String GET_DEVICE_NAME() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }

//    @SuppressLint("HardwareIds")
//    public static String GET_IP_ADDRESS(Context ctx) {
//        WifiManager wm = (WifiManager) ctx.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        return Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
//    }

//    public static boolean IS_NETWORK_AVAILABLE(Context mContext) {
//        if (mContext != null) {
//            ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//
//            return networkInfo != null && networkInfo.isConnected();
//        }
//        return false;
//    }

    public static int COUNT_VAL_CHARTACTER(final String str, final char ch) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (ch == str.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    public static String GET_MONTH_NAME_FROM_DATE(String input) {
        String val = input;
        if (input != null) {
            String month = val.substring(5, 7);
            switch (month) {
                case "01":
                    val = "Januari";
                    break;
                case "02":
                    val = "Februari";
                    break;
                case "03":
                    val = "Maret";
                    break;
                case "04":
                    val = "April";
                    break;
                case "05":
                    val = "Mei";
                    break;
                case "06":
                    val = "Juni";
                    break;
                case "07":
                    val = "Juli";
                    break;
                case "08":
                    val = "Agustus";
                    break;
                case "09":
                    val = "September";
                    break;
                case "10":
                    val = "Oktober";
                    break;
                case "11":
                    val = "November";
                    break;
                case "12":
                    val = "Desember";
                    break;
            }
        }
        return val;
    }

    public static void SHOW_MESSAGE_ERROR_CONNECTION_TOAST(Activity mContext) {
        if (mContext != null) {
            Toast.makeText(mContext, "Ops, no Internet Connection, Please check your connection.",
                    Toast.LENGTH_SHORT).show();
        }
    }


    public static String REPLACE_FORMATER_FLOAT(String input) {
        input = input.replace(",", "");
        return input;
    }

    public static String GET_FORMAT_THOUSAND_SEPARATOR_WITHOUT_COMMA(Double value) {
        if (value == null || value.isNaN())
            return "0.0";
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);

        DecimalFormat formatter = new DecimalFormat("#,###,###", symbols);
        return formatter.format(value);
    }

    public static String GET_FORMAT_THOUSAND_SEPARATOR_WITHOUT_COMMA(Integer value) {
        if (value == null)
            return "0.0";
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);

        DecimalFormat formatter = new DecimalFormat("#,###,###", symbols);
        return formatter.format(value);
    }

    public static String GET_FORMAT_THOUSAND_SEPARATOR_2DIGIT(Double value) {
        if (value == null || value.isNaN())
            return "0.00";
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat formatter = new DecimalFormat("#,###,###.##", symbols);
        formatter.setRoundingMode(RoundingMode.FLOOR); //Rounding 4 digit
        return formatter.format(value);
    }

    public static String GET_FORMAT_THOUSAND_SEPARATOR_2DIGIT(String value) {
        if (value == null)
            return "0.0";
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat formatter = new DecimalFormat("#,###,###.##", symbols);
        formatter.setRoundingMode(RoundingMode.FLOOR); //Rounding 4 digit
        return formatter.format(value);
    }

    public static String GET_FORMAT_THOUSAND_SEPARATOR_2DIGIT(Integer value) {
        if (value == null)
            return "0";
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat formatter = new DecimalFormat("#,###,###.##", symbols);
        formatter.setRoundingMode(RoundingMode.FLOOR); //Rounding 4 digit
        return formatter.format(value);
    }

    public static String GET_FORMAT_THOUSAND_SEPARATOR_2DIGIT(Float value) {
        if (value == null)
            return "0";
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat formatter = new DecimalFormat("#,###,###.##", symbols);
        formatter.setRoundingMode(RoundingMode.FLOOR); //Rounding 4 digit
        return formatter.format(value);
    }

    public static String GET_FORMAT_THOUSAND_SEPARATOR_2DIGIT(Long value) {
        if (value == null)
            return "0";
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat formatter = new DecimalFormat("#,###,###.##", symbols);
        formatter.setRoundingMode(RoundingMode.FLOOR); //Rounding 4 digit
        return formatter.format(value);
    }


    public static String GET_FORMAT_THOUSAND_SEPARATOR_4DIGIT(Double value) {
        if (value == null || value.isNaN())
            return "0.0000";
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);

        DecimalFormat formatter = new DecimalFormat("#,###,###.####", symbols);
        formatter.setRoundingMode(RoundingMode.FLOOR); //Rounding 4 digit
        return formatter.format(value);
    }

    public static String GET_FORMAT_THOUSAND_SEPARATOR_4DIGIT(String value) {
        if (value == null)
            return "0";

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat formatter = new DecimalFormat("#,###,###.####", symbols);
        return formatter.format(value);
    }

    public static String GET_FORMAT_THOUSAND_SEPARATOR_4DIGIT(Integer value) {
        if (value == null)
            return "0";

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat formatter = new DecimalFormat("#,###,###.####", symbols);
        return formatter.format(value);
    }

    public static String GET_FORMAT_THOUSAND_SEPARATOR_4DIGIT(Float value) {
        if (value == null)
            return "0";

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat formatter = new DecimalFormat("#,###,###.####", symbols);
        return formatter.format(value);
    }

    public static String GET_FORMAT_THOUSAND_SEPARATOR_4DIGIT(Long value) {
        if (value == null)
            return "0";

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat formatter = new DecimalFormat("#,###,###.####", symbols);
        return formatter.format(value);
    }


    public static String GET_UNFORMAT_THOUSAND_SEPARATOR(String value) {
        String val = value;
        if (value != null) {
            if (value.equals("") || value.equals("-")) {
                val = "0";
            } else {
//                DecimalFormat formatter = new DecimalFormat("#,###,###.##");
//                formatter.to
                val = val.replaceAll(",", "");
            }
        }
        return val;
    }

    public static String SYSTEM_DATE_FORMAT_CURENT() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return formatter.format(date);
    }

    public static String SYSTEM_DATE_TIME_MILIS_CURRENT() {
        String output = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Instant instant = Instant.now(); // Current moment in UTC.
            output = instant.toString();
        }
        return output;
    }

    public static String TIME_MILIS_STRNG_TO_DATE(String date) {
        Date formatteddate = null;
        String date1 = date.substring(0, 10);
        String time1 = date.substring(11, 19);
//        if (date != null) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            formatteddate = formatter.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String date2 = formatter.format(formatteddate);
        return date2;
    }

    public static String SIMPLE_DATE_FORMAT(Date date) {
        if (date != null) {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy");
            return formatter.format(date);
        }
        return "";
    }

    public static String SIMPLE_DATE_FORMAT_WITH_TIME(Date date) {
        if (date != null) {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
            return formatter.format(date);
        }
        return "";
    }

    public static String SIMPLE_DATE_FORMAT(Long dateValue) {
        if (dateValue != null) {
            Date date = new Date(dateValue);
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy");
            return formatter.format(date);
        }
        return "";
    }

    public static String SIMPLE_DATE_FORMAT_WITHOUT_DAYS(Date date) {
        if (date != null) {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
            return formatter.format(date);
        }
        return "";
    }

    public static String MILISTIMECONVERT(String date) {
        if (date != null) {
            // treat "Z" as literal
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            // use UTC as timezone
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date dt = null;
            try {
                dt = sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat convertdate = new SimpleDateFormat("dd-MMM-yyyy");
            return convertdate.format(dt);
        }
        return "";
    }

//    public static Date FORMAT_STRING_TO_DATE_ZONE(String date) {
//        @SuppressLint("SimpleDateFormat")
//        SimpleDateFormat formatOne = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        SimpleDateFormat formatTwo = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        //
//        SimpleDateFormat formatThree = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
//        SimpleDateFormat formatFour = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        Date dates = null;
//        if (date.substring(date.length() - 1, date.length()).matches("Z")) {
//            try {
//                dates = formatOne.parse(date);
//            } catch (ParseException e) {
//                try {
//                    dates = formatTwo.parse(date);
//                } catch (ParseException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        } else {
//            try {
//                dates = formatThree.parse(date);
//            } catch (ParseException ex) {
//                try {
//                    dates = formatFour.parse(date);
//                } catch (ParseException e1) {
//                    e1.printStackTrace();
//                }
//            }
//
//        }
//        return dates;
//    }

    public static Date FORMAT_STRING_TO_DATE(String date) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        //
        Date dates = null;
        try {
            dates = formatOne.parse(date);
        } catch (ParseException ex) {
            try {
                dates = formatTwo.parse(date);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }

        return dates;
    }

    public static String SIMPLE_DATE_FORMAT_CONVERT_ENCODE(String date) {
        if (date != null) {

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat defaultdate = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = null;
            try {
                dt = defaultdate.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat convertdate = new SimpleDateFormat("dd-MMM-yyyy");
            return convertdate.format(dt);
        }
        return "";
    }

    public static String SIMPLE_DATE_FORMAT_CONVERT_DECODE(String date) {
        if (date != null) {

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat defaultdate = new SimpleDateFormat("dd-MMM-yyyy");
            Date dt = null;
            try {
                dt = defaultdate.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat convertdate = new SimpleDateFormat("yyyy-MM-dd");
            return convertdate.format(dt);
        }
        return "";
    }


    public static String SIMPLE_DATE_FORMAT_SYSTEM(Date date) {
        if (date != null) {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            return formatter.format(date);
        }
        return "";
    }

    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();

    static {
        suffixes.put(1_000L, "ribu");
        suffixes.put(1_000_000L, " Juta");
        suffixes.put(1_000_000_000L, " Miliar");
        suffixes.put(1_000_000_000_000L, " Triliun");
    }

    public static String GET_FORMAT_CURRENCY_SHORT(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return GET_FORMAT_CURRENCY_SHORT(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + GET_FORMAT_CURRENCY_SHORT(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = null;
        if (e != null) {
            divideBy = e.getKey();
        }
        String suffix = null;
        if (e != null) {
            suffix = e.getValue();
        }

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

    private static final NavigableMap<Long, String> suffixes3 = new TreeMap<>();

    static {
        suffixes3.put(1_000L, "");
        suffixes3.put(1_000_000L, "");
        suffixes3.put(1_000_000_000L, "");
        suffixes3.put(1_000_000_000_000L, "");
    }

    public static String GET_FORMAT_CURRENCY_SHORT_NUMBER_ONLY(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE)
            return GET_FORMAT_CURRENCY_SHORT_NUMBER_ONLY(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + GET_FORMAT_CURRENCY_SHORT_NUMBER_ONLY(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes3.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return String.valueOf(hasDecimal ? (truncated / 10d) : (truncated / 10));
    }


    private static final NavigableMap<Long, String> suffixess2 = new TreeMap<>();

    static {
        suffixess2.put(1_000L, "ribu");
        suffixess2.put(1_000_000L, " juta");
        suffixess2.put(1_000_000_000L, " miliar");
        suffixess2.put(1_000_000_000_000L, " triliun");
    }

    public static String GET_FORMAT_CURRENCY_SHORT_COMPRESS(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return GET_FORMAT_CURRENCY_SHORT(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + GET_FORMAT_CURRENCY_SHORT(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixess2.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

    public static String GET_FORMAT_CURRENCY_SHORT_TYPE_CURRENCY_ONLY(long value) {
        if (value < 1000) return ""; //deal with easy case
        Map.Entry<Long, String> e = suffixess2.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();
        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? suffix : suffix;
    }


    @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
    public static void REMOVE_PREFERENCE(Context context, String preffName, String preffKey) {
        if (context != null && preffName != null && preffKey != null) {
            SharedPreferences settings = context.getSharedPreferences(preffName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();

            editor.remove(preffKey);
            editor.commit();
        }
    }

    @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
    public static void ADD_PREFERENCE(Context context, String preffName, String preffKey, String value) {
        if (context != null && preffName != null && preffKey != null && value != null) {
            SharedPreferences settings = context.getSharedPreferences(preffName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();

            editor.putString(preffKey, value);
            editor.commit();
        }
    }

    @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
    public static void ADD_PREFERENCE(Context context, String preffName, String preffKey, int value) {
        if (context != null && preffName != null && preffKey != null && value != 0) {
            SharedPreferences settings = context.getSharedPreferences(preffName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();

            editor.putInt(preffKey, value);
            editor.commit();
        }
    }

    @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
    public static void ADD_PREFERENCE(Context context, String preffName, String preffKey, boolean value) {
        if (context != null && preffName != null && preffKey != null) {
            SharedPreferences settings = context.getSharedPreferences(preffName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();

            editor.putBoolean(preffKey, value);
            editor.commit();
        }
    }

    public static String GET_PREFFERENCE(Context context, String preffName, String preffKey) {
        String value = "";
        if (context != null && preffName != null && preffKey != null) {
            SharedPreferences settings = context.getSharedPreferences(preffName, Context.MODE_PRIVATE);
            value = settings.getString(preffKey, "");
        }
        return value;
    }

    public static int GET_PREFFERENCE_INT(Context context, String preffName, String preffKey) {
        int value = 0;
        if (context != null && preffName != null && preffKey != null) {
            SharedPreferences settings = context.getSharedPreferences(preffName, Context.MODE_PRIVATE);
            value = settings.getInt(preffKey, 0);
        }
        return value;
    }

    public static boolean GET_PREFFERENCE_BOOLEAN(Context context, String preffName, String preffKey) {
        boolean value = false;
        if (context != null && preffName != null && preffKey != null) {
            SharedPreferences settings = context.getSharedPreferences(preffName, Context.MODE_PRIVATE);
            value = settings.getBoolean(preffKey, false);
        }
        return value;
    }

    public static boolean VALID_FORMAT_PASSWORD(String password) {
        boolean validatee;
        //belum bisa masuk    -[]\
        String passwordPattern = "(?=.*[A-Z])" + "(?=.*[`~!@#$%^&*()_+={}|/?,.<>:;])" + "(?=\\S+$)" + ".{6,}" + "$";
        if (password.matches(passwordPattern)) {
            validatee = true;
        } else {
            validatee = false;
        }
        return validatee;
    }


    public static boolean VALID_FORMAT_EMAIL(String email) {
        boolean validate;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";
        String emailPattern3 = "[A-Z0-9a-z._%+-]+@[a-z-*[@#$%^_.*&+=]]+\\.+[a-z]+\\.+[a-z]+";

        if (email.matches(emailPattern)) {
            validate = true;
        } else if (email.matches(emailPattern2)) {
            validate = true;
        } else validate = email.matches(emailPattern3);
        return validate;
    }

    public static String ENCODE_BASE64(Bitmap bitmap) {
        if (bitmap != null) {
            Bitmap immagex = bitmap;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
            return imageEncoded;
        }
        return "";
    }


    public static Bitmap DECODE_BASE64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public static ArrayList<Integer> CONVERT_SPLIT_COMMA_TO_ARRAY_INT(String string) {
        ArrayList<Integer> result = new ArrayList<>();
        if (string != null) {
            if (!string.equals("")) {
                if (string.startsWith(",")) {
                    string.substring(1);
                }
                String[] splitted = string.split(",");
                for (String data : splitted) {
                    result.add(Integer.parseInt(data));
                }
            }
        }
        return result;
    }

    public static Integer NOTIF_TYPE_CODE(String code) {
        int codeNotif = 0;
        switch (code) {
            case "IVDRI":
                codeNotif = 5;
                break;
            case "IVMIT":
                codeNotif = 1;
                break;
            case "VCHR":
                codeNotif = 3;
                break;
            case "NOTIF":
                codeNotif = 2;
                break;
            case "VHCLE":
                codeNotif = 4;
                break;
        }
        return codeNotif;
    }
}
