package com.example.aguazero.Login;

import android.content.Context;
import android.content.SharedPreferences;

public class UserManager {

    private static final String PREF_NAME = "usuarios";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public UserManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    // Guardar un usuario en SharedPreferences
    public void guardarUsuario(Usuario usuario) {
        editor.putString(KEY_NOMBRE, usuario.getNombre());
        editor.putString(KEY_EMAIL, usuario.getEmail());
        editor.putString(KEY_PASSWORD, usuario.getPassword());
        editor.apply(); // Guarda los cambios
    }

    // Cargar un usuario desde SharedPreferences
    public Usuario cargarUsuario() {
        String nombre = sharedPreferences.getString(KEY_NOMBRE, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        String password = sharedPreferences.getString(KEY_PASSWORD, null);

        if (nombre != null && email != null && password != null) {
            return new Usuario(nombre, email, password);
        }
        return null; // Retorna null si no hay usuario guardado
    }

    // Validar si un correo ya está registrado
    public boolean correoRegistrado(String email) {
        String savedEmail = sharedPreferences.getString(KEY_EMAIL, null);
        return email.equals(savedEmail);
    }

    // Validar las credenciales del usuario
    public boolean validarUsuario(String email, String password) {
        String savedEmail = sharedPreferences.getString(KEY_EMAIL, null);
        String savedPassword = sharedPreferences.getString(KEY_PASSWORD, null);
        return email.equals(savedEmail) && password.equals(savedPassword);
    }

    // Borrar los datos del usuario (logout o eliminar cuenta)
    public void borrarUsuario() {
        editor.clear();
        editor.apply();
    }
}

