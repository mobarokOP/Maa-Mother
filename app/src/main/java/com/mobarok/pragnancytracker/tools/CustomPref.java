package com.mobarok.pragnancytracker.tools;

import android.content.Context;
import android.content.SharedPreferences;

public class CustomPref {
    Context context;
    /*public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            for (NetworkInfo networkInfo : info) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void showError(Context context,String msg, Retry retry){
       new AlertDialog.Builder(context)
               .setTitle("Something went wrong..")
               .setMessage(msg)
               .setCancelable(false)
               .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       if (isNetworkAvailable(context)){
                           retry.OnRetry();
                           dialogInterface.dismiss();
                       }else {
                           Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                           context.startActivity(intent);
                           retry.OnRetry();
                       }
                   }
               }).show();
    }*/

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public CustomPref(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }



    public void setDate(String setDate) {
        editor.putString("selectDate", setDate);
        editor.apply();
    }
    public String getDate() {
        return sharedPreferences.getString("selectDate","null");
    }

    public void setPossibleDate(String setPossibleDate) {
        editor.putString("possibleDate", setPossibleDate);
        editor.apply();
    }
    public String getPossibleDate() {
        return sharedPreferences.getString("possibleDate","null");
    }

    public void setSession(boolean session) {
        editor.putBoolean("session", session);
        editor.apply();
    }

    public boolean getSession(){
        return sharedPreferences.getBoolean("session", true);
    }





}
