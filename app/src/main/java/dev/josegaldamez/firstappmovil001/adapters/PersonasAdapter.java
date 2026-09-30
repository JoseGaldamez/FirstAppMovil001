package dev.josegaldamez.firstappmovil001.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.josegaldamez.firstappmovil001.R;
import dev.josegaldamez.firstappmovil001.models.Personas;

public class PersonasAdapter extends RecyclerView.Adapter<PersonasAdapter.PersonaViewHolder> {

    private List<Personas> personasList;

    public PersonasAdapter(List<Personas> personasList) {
        this.personasList = personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona, parent, false);
        return new PersonaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Personas persona = personasList.get(position);
        Context context = holder.itemView.getContext();

        String nombreCompleto = persona.getNombre() + " " + persona.getApellido();
        holder.tvNombreCompleto.setText(nombreCompleto);

        if (persona.getCorreo() != null && !persona.getCorreo().isEmpty()) {
            holder.tvCorreo.setText(context.getString(R.string.label_correo, persona.getCorreo()));
            holder.tvCorreo.setVisibility(View.VISIBLE);
        } else {
            holder.tvCorreo.setVisibility(View.GONE);
        }

        if (persona.getTelefono() != null && !persona.getTelefono().isEmpty()) {
            holder.tvTelefono.setText(context.getString(R.string.label_telefono, persona.getTelefono()));
            holder.tvTelefono.setVisibility(View.VISIBLE);
        } else {
            holder.tvTelefono.setVisibility(View.GONE);
        }

        StringBuilder extraInfo = new StringBuilder();
        if (persona.getFechaNac() != null && !persona.getFechaNac().isEmpty()) {
            extraInfo.append(context.getString(R.string.label_fecha_nac, persona.getFechaNac()));
        }
        if (persona.getDireccion() != null && !persona.getDireccion().isEmpty()) {
            if (extraInfo.length() > 0) extraInfo.append(" | ");
            extraInfo.append(context.getString(R.string.label_direccion, persona.getDireccion()));
        }

        if (extraInfo.length() > 0) {
            holder.tvDireccionYFecha.setText(extraInfo.toString());
            holder.tvDireccionYFecha.setVisibility(View.VISIBLE);
        } else {
            holder.tvDireccionYFecha.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return personasList != null ? personasList.size() : 0;
    }

    public static class PersonaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreCompleto, tvCorreo, tvTelefono, tvDireccionYFecha;

        public PersonaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreCompleto = itemView.findViewById(R.id.tvNombreCompleto);
            tvCorreo = itemView.findViewById(R.id.tvCorreo);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvDireccionYFecha = itemView.findViewById(R.id.tvDireccionYFecha);
        }
    }
}
