package com.example.aguazero.Foro;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

public class ForoManager {

    private static final String PREFS_NAME = "ForoPrefs";
    private static final String FOROS_KEY = "foros";
    private SharedPreferences sharedPreferences;

    public ForoManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public List<Foro> getForos() {
        List<Foro> foros = new ArrayList<>();
        String forosJson = sharedPreferences.getString(FOROS_KEY, "[]");

        try {
            JSONArray jsonArray = new JSONArray(forosJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                Foro foro = Foro.fromJson(jsonArray.getJSONObject(i));
                if (foro != null) {
                    foros.add(foro);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return foros;
    }

    private void saveForos(List<Foro> foros) {
        JSONArray jsonArray = new JSONArray();
        for (Foro foro : foros) {
            jsonArray.put(foro.toJson());
        }
        sharedPreferences.edit().putString(FOROS_KEY, jsonArray.toString()).apply();
    }

    public void addForo(Foro foro) {
        List<Foro> foros = getForos();
        foros.add(foro);
        saveForos(foros);
    }

    public void updateForo(int index, Foro foro) {
        List<Foro> foros = getForos();
        if (index >= 0 && index < foros.size()) {
            foros.set(index, foro);
            saveForos(foros);
        }
    }

    public void deleteForo(int index) {
        List<Foro> foros = getForos();
        if (index >= 0 && index < foros.size()) {
            foros.remove(index);
            saveForos(foros);
        }
    }

    // Método para agregar un mensaje a un foro específico y guardar los cambios
    public void addMensajeToForo(int foroIndex, Mensaje mensaje) {
        List<Foro> foros = getForos();
        if (foroIndex >= 0 && foroIndex < foros.size()) {
            Foro foro = foros.get(foroIndex);
            foro.addMensaje(mensaje); // Agregar el mensaje al foro
            saveForos(foros); // Guardar la lista actualizada de foros con sus mensajes
        }
    }
}
