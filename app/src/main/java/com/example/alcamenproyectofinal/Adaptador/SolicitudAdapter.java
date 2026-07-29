package com.example.alcamenproyectofinal.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alcamenproyectofinal.Modelo.Solicitud;
import com.example.alcamenproyectofinal.R;

import java.util.List;

public class SolicitudAdapter extends RecyclerView.Adapter<SolicitudAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Solicitud solicitud);
        void onEditarClick(Solicitud solicitud, int position);
        void onEliminarClick(Solicitud solicitud, int position);
    }

    private List<Solicitud> listaSolicitudes;
    private OnItemClickListener listener;

    public SolicitudAdapter(List<Solicitud> listaSolicitudes, OnItemClickListener listener) {
        this.listaSolicitudes = listaSolicitudes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_solicitud, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Solicitud s = listaSolicitudes.get(position);

        holder.tvCodigoSolicitud.setText("Solicitud: " + s.getCodigo_solicitud());

        String estadoText = (s.getEstado() != null && !s.getEstado().isEmpty()) ? s.getEstado() : "PENDIENTE";
        holder.tvEstado.setText(estadoText.toUpperCase());

        String prodOp = "Producto: " + s.getCodigo_producto() + " | Operario: " + s.getCodigo_operario();
        holder.tvProductoOperario.setText(prodOp);

        holder.tvFecha.setText("Fecha: " + s.getFecha());

        // Clic en la tarjeta entera
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(s);
            }
        });

        // Clic en el botón Editar (Lápiz)
        holder.btnEditar.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditarClick(s, holder.getAdapterPosition());
            }
        });

        // Clic en el botón Eliminar (X)
        holder.btnEliminar.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEliminarClick(s, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaSolicitudes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigoSolicitud, tvEstado, tvProductoOperario, tvFecha;
        ImageButton btnEditar, btnEliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigoSolicitud = itemView.findViewById(R.id.tvCodigoSolicitud);
            tvEstado = itemView.findViewById(R.id.tvEstado);
            tvProductoOperario = itemView.findViewById(R.id.tvProductoOperario);
            tvFecha = itemView.findViewById(R.id.tvFecha);

            // Importante: Revisa que estos IDs coincidan con tu item_solicitud.xml
            btnEditar = itemView.findViewById(R.id.btnEditarSolicitud);
            btnEliminar = itemView.findViewById(R.id.btnEliminarSolicitud);
        }
    }
}