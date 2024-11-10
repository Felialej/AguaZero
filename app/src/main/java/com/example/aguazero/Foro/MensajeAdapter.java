package com.example.aguazero.Foro;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aguazero.R;

import java.util.ArrayList;
import java.util.List;

public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.MensajeViewHolder> {

    private List<Mensaje> mensajes;
    private OnMensajeListener listener;

    public MensajeAdapter(OnMensajeListener listener) {
        this.mensajes = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public MensajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mensaje, parent, false);
        return new MensajeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MensajeViewHolder holder, int position) {
        Mensaje mensaje = mensajes.get(position);

        // Configurar el mensaje principal
        holder.mensajeTextView.setText(mensaje.getAutor() + ": " + mensaje.getContenido());

        // Configurar el menú contextual
        holder.itemView.setOnClickListener(v -> showPopupMenu(v, mensaje));

        // Mostrar respuestas con indentación
        if (mensaje.tieneRespuestas()) {
            StringBuilder respuestasTexto = new StringBuilder();
            for (Mensaje respuesta : mensaje.getRespuestas()) {
                // Indentación para cada respuesta
                respuestasTexto.append("    ↳ ").append(respuesta.getAutor()).append(": ").append(respuesta.getContenido()).append("\n");
            }
            holder.respuestasTextView.setText(respuestasTexto.toString());
            holder.respuestasTextView.setVisibility(View.VISIBLE);
        } else {
            holder.respuestasTextView.setVisibility(View.GONE);
        }

        // Aplicar padding solo si es una respuesta, de lo contrario, sin padding
        holder.messageContainer.setPadding(0, 0, 0, 0); // Sin padding para mensajes principales
    }

    @Override
    public int getItemCount() {
        return mensajes.size();
    }
    public void updateMensajes(List<Mensaje> nuevosMensajes) {
        mensajes.clear();
        mensajes.addAll(nuevosMensajes);
        notifyDataSetChanged();
    }


    private void showPopupMenu(View view, Mensaje mensaje) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_mensaje, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_responder) {
                listener.onResponderMensaje(mensaje);
                return true;
            } else if (item.getItemId() == R.id.action_eliminar) {
                listener.onEliminarMensaje(mensaje);
                return true;
            } else if (item.getItemId() == R.id.action_editar) {
                listener.onEditarMensaje(mensaje);
                return true;
            }
            return false;
        });

        popupMenu.show();
    }

    public void addMensaje(Mensaje mensaje) {
        mensajes.add(mensaje);
        notifyItemInserted(mensajes.size() - 1);
    }

    public void addRespuesta(Mensaje mensajePadre, Mensaje respuesta) {
        mensajePadre.addRespuesta(respuesta);
        notifyDataSetChanged(); // Refresca para mostrar la respuesta anidada
    }

    public void removeMensaje(Mensaje mensaje) {
        mensajes.remove(mensaje);
        notifyDataSetChanged();
    }

    public interface OnMensajeListener {
        void onResponderMensaje(Mensaje mensaje);
        void onEliminarMensaje(Mensaje mensaje);
        void onEditarMensaje(Mensaje mensaje);
    }

    static class MensajeViewHolder extends RecyclerView.ViewHolder {

        private TextView mensajeTextView;
        private TextView respuestasTextView;
        private View messageContainer;

        public MensajeViewHolder(@NonNull View itemView) {
            super(itemView);
            mensajeTextView = itemView.findViewById(R.id.mensajeTextView);
            respuestasTextView = itemView.findViewById(R.id.respuestasTextView);
            messageContainer = itemView.findViewById(R.id.message_container); // Contenedor principal para aplicar padding
        }
    }
}
