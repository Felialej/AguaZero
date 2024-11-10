package com.example.aguazero.Foro;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aguazero.R;
import java.util.ArrayList;
import java.util.List;

public class ForoDetailActivity extends AppCompatActivity implements MensajeAdapter.OnMensajeListener {

    private TextView textViewTituloForo, textViewDescripcionForo;
    private EditText etMensaje;
    private Button btnEnviar;
    private RecyclerView recyclerView;
    private MensajeAdapter mensajeAdapter;
    private List<Mensaje> mensajesList;
    private ForoManager foroManager;
    private int foroIndex;
    private Mensaje mensajeEnRespuesta; // Para responder a un mensaje específico
    private Mensaje mensajeEnEdicion; // Para almacenar el mensaje en modo edición

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro_detail);

        // Inicialización de las vistas
        textViewTituloForo = findViewById(R.id.textViewTituloForo);
        textViewDescripcionForo = findViewById(R.id.textViewDescripcionForo);
        etMensaje = findViewById(R.id.etMensaje);
        btnEnviar = findViewById(R.id.btnEnviar);
        recyclerView = findViewById(R.id.recyclerViewMensajes);

        foroManager = new ForoManager(this);

        // Obtener el índice del foro desde el intent
        foroIndex = getIntent().getIntExtra("foro_index", -1);

        // Cargar los detalles del foro
        Foro foro = null;
        if (foroIndex != -1) {
            foro = foroManager.getForos().get(foroIndex);
            textViewTituloForo.setText(foro.getTitulo());
            textViewDescripcionForo.setText(foro.getDescripcion());

            // Cargar mensajes desde el foro y mostrarlos en el adaptador
            mensajesList = foro.getMensajes();
            mensajeAdapter = new MensajeAdapter(this);
            mensajeAdapter.updateMensajes(mensajesList);
        } else {
            mensajesList = new ArrayList<>();
        }

        recyclerView.setAdapter(mensajeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Configurar el botón para enviar el mensaje o guardar cambios
        btnEnviar.setOnClickListener(v -> {
            String contenidoMensaje = etMensaje.getText().toString();
            if (!contenidoMensaje.isEmpty()) {
                if (mensajeEnEdicion != null) {
                    // Si estamos en modo edición, actualizamos el mensaje existente
                    mensajeEnEdicion.setContenido(contenidoMensaje); // Actualizar el contenido
                    mensajeAdapter.notifyDataSetChanged(); // Refrescar el adaptador para ver el cambio
                    mensajeEnEdicion = null; // Salir del modo edición
                } else if (mensajeEnRespuesta != null) {
                    // Si estamos respondiendo a un mensaje específico
                    Mensaje respuesta = new Mensaje(contenidoMensaje, "Usuario actual");
                    mensajeEnRespuesta.addRespuesta(respuesta); // Añadir respuesta al mensaje padre
                    mensajeAdapter.notifyDataSetChanged();
                    mensajeEnRespuesta = null;

                    // Guardar el foro con la respuesta nueva en SharedPreferences
                    if (foroIndex != -1) {
                        Foro foroActualizado = foroManager.getForos().get(foroIndex);
                        foroActualizado.setMensajes(mensajesList); // Actualizar la lista completa de mensajes
                        foroManager.updateForo(foroIndex, foroActualizado); // Guardar cambios en ForoManager
                    }
                } else {
                    // Si es un nuevo mensaje
                    Mensaje nuevoMensaje = new Mensaje(contenidoMensaje, "Usuario actual");
                    mensajesList.add(nuevoMensaje);
                    mensajeAdapter.addMensaje(nuevoMensaje);

                    // Guardar el nuevo mensaje en SharedPreferences
                    if (foroIndex != -1) {
                        foroManager.addMensajeToForo(foroIndex, nuevoMensaje);
                    }
                }
                etMensaje.setText(""); // Limpiar el campo de texto
                etMensaje.setHint("Escribe un mensaje"); // Restaurar el hint
            }
        });
    }

    @Override
    public void onResponderMensaje(Mensaje mensaje) {
        mensajeEnRespuesta = mensaje;
        mensajeEnEdicion = null; // Asegurarse de que no estamos en modo edición
        etMensaje.setHint("Respondiendo a " + mensaje.getAutor());
        etMensaje.requestFocus();
    }

    @Override
    public void onEditarMensaje(Mensaje mensaje) {
        mensajeEnEdicion = mensaje; // Guardamos el mensaje en edición
        mensajeEnRespuesta = null; // Asegurarse de que no estamos en modo respuesta
        etMensaje.setText(mensaje.getContenido()); // Cargar el contenido en el campo de texto
        etMensaje.setHint("Editando mensaje"); // Cambiar el hint para indicar edición
        etMensaje.requestFocus();
    }

    @Override
    public void onEliminarMensaje(Mensaje mensaje) {
        mensajeAdapter.removeMensaje(mensaje);
        mensajesList.remove(mensaje);
        // Guardar los cambios en ForoManager después de eliminar el mensaje
        if (foroIndex != -1) {
            Foro foro = foroManager.getForos().get(foroIndex);
            foro.setMensajes(mensajesList); // Actualizar la lista de mensajes en el foro
            foroManager.updateForo(foroIndex, foro); // Guardar la lista actualizada
        }
    }
}
