package com.example.aguazero.Foro;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aguazero.Login.UserManager;
import com.example.aguazero.Login.Usuario;
import com.example.aguazero.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ResponderForo extends AppCompatActivity implements MensajeAdapter.OnMensajeListener {

    private static final String PREFS_NAME = "ForoPrefs";
    private static final String MESSAGES_KEY = "mensajes";

    private EditText etMensaje;
    private RecyclerView recyclerView;
    private LinearLayout messageBar;
    private MensajeAdapter mensajeAdapter;

    private SharedPreferences sharedPreferences;
    private UserManager userManager;
    private Usuario usuarioActual;
    private Mensaje mensajeEnRespuesta;
    private Mensaje mensajeEnEdicion; // Nuevo campo para almacenar el mensaje en edición

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder_foro);

        String tituloForo = getIntent().getStringExtra("titulo_foro");
        String descripcionForo = getIntent().getStringExtra("descripcion_foro");

        TextView textViewTituloForo = findViewById(R.id.textViewtituloforo);
        TextView textViewDescripcionForo = findViewById(R.id.textViewponerdescripciondeforo);

        if (tituloForo != null) {
            textViewTituloForo.setText(tituloForo);
        }
        if (descripcionForo != null) {
            textViewDescripcionForo.setText(descripcionForo);
        }

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        userManager = new UserManager(this);
        usuarioActual = userManager.cargarUsuario();

        setupUI();
        cargarMensajesGuardados();
    }

    private void setupUI() {
        recyclerView = findViewById(R.id.recyclerViewMensajes);
        mensajeAdapter = new MensajeAdapter(this);
        recyclerView.setAdapter(mensajeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

        messageBar = findViewById(R.id.message_bar);
        etMensaje = findViewById(R.id.etMensaje);
        Button submitButton = findViewById(R.id.submit_button);
        Button comentarButton = findViewById(R.id.submit_button2);

        comentarButton.setOnClickListener(v -> {
            recyclerView.setVisibility(View.VISIBLE);
            messageBar.setVisibility(View.VISIBLE);
        });

        submitButton.setOnClickListener(v -> {
            String mensaje = etMensaje.getText().toString();
            if (!mensaje.isEmpty()) {
                agregarMensaje(mensaje);
                etMensaje.setText("");
                recyclerView.scrollToPosition(mensajeAdapter.getItemCount() - 1);
            }
        });
    }

    private void agregarMensaje(String mensaje) {
        String nombreUsuario = (usuarioActual != null) ? usuarioActual.getNombre() : "Anónimo";

        if (mensajeEnEdicion != null) {
            // Si estamos en modo edición, actualizamos el mensaje existente
            mensajeEnEdicion.setContenido(mensaje);
            mensajeAdapter.notifyDataSetChanged();
            mensajeEnEdicion = null; // Salimos del modo de edición
        } else {
            Mensaje nuevoMensaje = new Mensaje(mensaje, nombreUsuario);

            if (mensajeEnRespuesta != null) {
                mensajeAdapter.addRespuesta(mensajeEnRespuesta, nuevoMensaje);
                mensajeEnRespuesta = null;
            } else {
                mensajeAdapter.addMensaje(nuevoMensaje);
            }

            guardarMensaje(nuevoMensaje);
        }
    }

    private void cargarMensajesGuardados() {
        List<Mensaje> mensajes = obtenerMensajes();
        for (Mensaje mensaje : mensajes) {
            mensajeAdapter.addMensaje(mensaje);
        }
    }

    private List<Mensaje> obtenerMensajes() {
        List<Mensaje> mensajes = new ArrayList<>();
        String mensajesJson = sharedPreferences.getString(MESSAGES_KEY, null);

        if (mensajesJson != null) {
            try {
                JSONArray jsonArray = new JSONArray(mensajesJson);
                for (int i = 0; i < jsonArray.length(); i++) {
                    String contenido = jsonArray.getString(i);
                    Mensaje mensaje = new Mensaje(contenido, "Autor");
                    mensajes.add(mensaje);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mensajes;
    }

    private void guardarMensaje(Mensaje mensaje) {
        // Código para guardar mensajes
    }

    @Override
    public void onResponderMensaje(Mensaje mensaje) {
        etMensaje.setText("");
        mensajeEnRespuesta = mensaje;
        etMensaje.requestFocus();
    }

    @Override
    public void onEliminarMensaje(Mensaje mensaje) {
        mensajeAdapter.removeMensaje(mensaje);
    }

    @Override
    public void onEditarMensaje(Mensaje mensaje) {
        mensajeEnEdicion = mensaje; // Guardamos el mensaje en edición
        etMensaje.setText(mensaje.getContenido()); // Cargamos el contenido en el EditText
        etMensaje.requestFocus();
    }
}
