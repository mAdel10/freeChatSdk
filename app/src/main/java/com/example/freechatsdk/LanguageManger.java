package com.example.freechatsdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import java.util.Locale;

public class LanguageManger {
    private Context ct;
    SharedPreferences pref;


    public LanguageManger(Context ctx) {
        this.ct = ctx;
        pref = ct.getSharedPreferences("LANG",Context.MODE_PRIVATE);
    }

    public void updateResource(String langCode){
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);
        Resources resources = ct.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
        setLang(langCode);
    }

    public String getLang(){
        return pref.getString("lang","en");
    }

    public void setLang(String code){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("lang",code);
        editor.commit();
    }

}
