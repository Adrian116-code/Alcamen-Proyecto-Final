package com.example.alcamenproyectofinal.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alcamenproyectofinal.Modelo.Producto;
import com.example.alcamenproyectofinal.R;

import java.util.ArrayList;
import java.util.List;

public class ProductoOperarioAdapter extends RecyclerView.Adapter<ProductoOperarioAdapter.ViewHolder> {

    private List<Producto> listaProductos;

    public ProductoOperarioAdapter(List<Producto> listaProductos) {
        this.listaProductos = listaProductos != null ? listaProductos : new ArrayList<>();
    }

    public void setProductos(List<Producto> productos) {
        this.listaProductos = productos != null ? productos : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto_operario, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Producto p = listaProductos.get(position);

        if (holder.tvNombre != null) {
            holder.tvNombre.setText(p.getNombre());
        }

        if (holder.tvCantidad != null) {
            holder.tvCantidad.setText(String.valueOf(p.getStock()));
        }
    }

    @Override
    public int getItemCount() {
        return listaProductos != null ? listaProductos.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvCantidad;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreProductoOperario);
            tvCantidad = itemView.findViewById(R.id.tvCantidadProductoOperario);
        }
    }
}