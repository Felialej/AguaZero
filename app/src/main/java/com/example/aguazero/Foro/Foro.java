package com.example.aguazero.Foro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Foro {
    private String titulo;
    private String descripcion;
    private List<Mensaje> mensajes; // Lista de mensajes asociados al foro

    // Constructor
    public Foro(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.mensajes = new ArrayList<>();
    }

    // Getters y Setters para título y descripción
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getter y Setter para la lista de mensajes
    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    // Método para agregar un solo mensaje
    public void addMensaje(Mensaje mensaje) {
        mensajes.add(mensaje);
    }

    // Convertir el foro a JSON para guardarlo en SharedPreferences
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("titulo", titulo);
            jsonObject.put("descripcion", descripcion);

            JSONArray mensajesArray = new JSONArray();
            for (Mensaje mensaje : mensajes) {
                mensajesArray.put(mensaje.toJson());
            }
            jsonObject.put("mensajes", mensajesArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    // Crear un objeto Foro desde un JSON
    public static Foro fromJson(JSONObject jsonObject) {
        try {
            String titulo = jsonObject.getString("titulo");
            String descripcion = jsonObject.getString("descripcion");
            Foro foro = new Foro(titulo, descripcion);

            JSONArray mensajesArray = jsonObject.getJSONArray("mensajes");
            for (int i = 0; i < mensajesArray.length(); i++) {
                Mensaje mensaje = Mensaje.fromJson(mensajesArray.getJSONObject(i));
                foro.addMensaje(mensaje);
            }

            return foro;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
