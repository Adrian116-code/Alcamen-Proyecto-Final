package com.example.alcamenproyectofinal.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alcamenproyectofinal.Modelo.Producto;
import com.example.alcamenproyectofinal.R;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    private List<Producto> listaProductos;

    public ProductoAdapter(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Producto p = listaProductos.get(position);

        // Asignar los valores a las vistas de la tarjeta
        holder.tvNombreProducto.setText(p.getNombre());
        holder.tvDescripcion.setText(p.getDescripcion());

        String detalles = "Ubicación: " + p.getUbicacion()
                + " | Stock: " + p.getStock()
                + " | Prov: " + p.getCodigo_proveedor()
                + " | Cód: " + p.getCodigo_producto();

        holder.tvDetallesProducto.setText(detalles);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreProducto, tvDescripcion, tvDetallesProducto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreProducto = itemView.findViewById(R.id.tvNombreProducto);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvDetallesProducto = itemView.findViewById(R.id.tvDetallesProducto);
        }
    }
}