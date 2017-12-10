package fr.utt.if26.if26_sncfprojet;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Permet de sauvegarder et de récupérer la clé d'API sauvegardée.
 * Created by Jeanba on 08/12/2017.
 */

class APIKeyClasse {
    static String getKey(Context context) {
        SharedPreferences pref = context.getSharedPreferences(
                context.getResources().getString(R.string.apikey),
                Context.MODE_PRIVATE);
        return pref.getString("apikey", null);
    }
    static void setKey(Context context, String newKey) {
        SharedPreferences pref = context.getSharedPreferences(
                context.getResources().getString(R.string.apikey),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("apikey", newKey);
        editor.apply();
    }
}
