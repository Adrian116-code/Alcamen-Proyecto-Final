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

    private List<Usuario> listaUsuarios;

    public UsuarioAdapter(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
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
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreCompleto, tvRol, tvDetalles;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreCompleto = itemView.findViewById(R.id.tvNombreCompleto);
            tvRol = itemView.findViewById(R.id.tvRol);
            tvDetalles = itemView.findViewById(R.id.tvDetalles);
        }
    }
}