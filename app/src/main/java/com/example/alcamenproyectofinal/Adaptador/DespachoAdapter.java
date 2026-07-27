package com.example.alcamenproyectofinal.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alcamenproyectofinal.Modelo.Despacho_Sede;
import com.example.alcamenproyectofinal.R;

import java.util.List;

public class DespachoAdapter extends RecyclerView.Adapter<DespachoAdapter.ViewHolder> {

    private List<Despacho_Sede> listaDespachos;

    public DespachoAdapter(List<Despacho_Sede> listaDespachos) {
        this.listaDespachos = listaDespachos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_despacho, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Despacho_Sede d = listaDespachos.get(position);

        // Usamos los getters correspondientes a tu clase Despacho_Sede
        holder.tvCodigo.setText("Código Despacho: " + (d.getCodigo_despacho() != null ? d.getCodigo_despacho() : ""));
        holder.tvOperario.setText("Operario: " + (d.getCodigo_operario() != null ? d.getCodigo_operario() : "null"));
        holder.tvProducto.setText("Producto: " + (d.getCodigo_producto() != null ? d.getCodigo_producto() : ""));
        holder.tvSede.setText("Sede: " + (d.getCodigo_sede() != null ? d.getCodigo_sede() : ""));
        holder.tvCantidad.setText("Cantidad: " + (d.getCantidad() != null ? d.getCantidad() : 0));
    }

    @Override
    public int getItemCount() {
        return listaDespachos != null ? listaDespachos.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigo, tvOperario, tvProducto, tvSede, tvCantidad;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigoDespacho);
            tvOperario = itemView.findViewById(R.id.tvOperarioDespacho);
            tvProducto = itemView.findViewById(R.id.tvProductoDespacho);
            tvSede = itemView.findViewById(R.id.tvSedeDespacho);
            tvCantidad = itemView.findViewById(R.id.tvCantidadDespacho);
        }
    }
}