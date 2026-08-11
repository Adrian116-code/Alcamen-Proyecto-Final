package com.example.alcamenproyectofinal.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alcamenproyectofinal.Modelo.Usuario;
import com.example.alcamenproyectofinal.R;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onEditarClick(Usuario usuario, int position);
        void onEliminarClick(Usuario usuario, int position);
    }

    private List<Usuario> listaUsuarios;
    private OnItemClickListener listener;

    public UsuarioAdapter(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public UsuarioAdapter(List<Usuario> listaUsuarios, OnItemClickListener listener) {
        this.listaUsuarios = listaUsuarios;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_usuario, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Usuario u = listaUsuarios.get(position);

        String nombreCompleto = u.getNombres() + " " + u.getApellidos();
        holder.tvNombreCompleto.setText(nombreCompleto);
        holder.tvRol.setText(u.getRol());

        String detalles = "DNI: " + u.getDni() + " | User: " + u.getUsername() + " | Edad: " + u.getEdad();
        holder.tvDetalles.setText(detalles);

        if (holder.btnEditar != null) {
            holder.btnEditar.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onEditarClick(u, holder.getAdapterPosition());
                }
            });
        }

        if (holder.btnEliminar != null) {
            holder.btnEliminar.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onEliminarClick(u, holder.getAdapterPosition());
                }
            });
        }
    }

        @Override
        public int getItemCount() {
            return listaUsuarios.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvNombreCompleto, tvRol, tvDetalles;
            View btnEditar, btnEliminar; // Usa ImageButton o MaterialButton según corresponda

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvNombreCompleto = itemView.findViewById(R.id.tvNombreCompleto);
                tvRol = itemView.findViewById(R.id.tvRol);
                tvDetalles = itemView.findViewById(R.id.tvDetalles);

                btnEditar = itemView.findViewById(R.id.btnEditarUsuario);
                btnEliminar = itemView.findViewById(R.id.btnEliminarUsuario);
            }
        }

}