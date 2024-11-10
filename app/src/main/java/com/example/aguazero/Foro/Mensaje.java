package com.example.aguazero.Foro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Mensaje {
    private String contenido;
    private String autor;
    private List<Mensaje> respuestas; // Lista para almacenar respuestas anidadas

    // Constructor
    public Mensaje(String contenido, String autor) {
        this.contenido = contenido;
        this.autor = autor;
        this.respuestas = new ArrayList<>();
    }

    // Getters y Setters
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getAutor() {
        return autor;
    }

    public List<Mensaje> getRespuestas() {
        return respuestas;
    }

    // Método para agregar una respuesta
    public void addRespuesta(Mensaje respuesta) {
        respuestas.add(respuesta);
    }

    // Método para verificar si el mensaje tiene respuestas
    public boolean tieneRespuestas() {
        return !respuestas.isEmpty();
    }

    // Convertir un mensaje (y sus respuestas) a JSON
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("contenido", contenido);
            jsonObject.put("autor", autor);

            // Convertir respuestas a un JSONArray
            JSONArray respuestasArray = new JSONArray();
            for (Mensaje respuesta : respuestas) {
                respuestasArray.put(respuesta.toJson()); // Guardar cada respuesta como JSON
            }
            jsonObject.put("respuestas", respuestasArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    // Crear un mensaje desde un JSON, incluyendo respuestas
    public static Mensaje fromJson(JSONObject jsonObject) {
        try {
            String contenido = jsonObject.getString("contenido");
            String autor = jsonObject.getString("autor");
            Mensaje mensaje = new Mensaje(contenido, autor);

            // Cargar respuestas desde JSON
            if (jsonObject.has("respuestas")) {
                JSONArray respuestasArray = jsonObject.getJSONArray("respuestas");
                for (int i = 0; i < respuestasArray.length(); i++) {
                    Mensaje respuesta = Mensaje.fromJson(respuestasArray.getJSONObject(i));
                    mensaje.addRespuesta(respuesta);
                }
            }

            return mensaje;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
