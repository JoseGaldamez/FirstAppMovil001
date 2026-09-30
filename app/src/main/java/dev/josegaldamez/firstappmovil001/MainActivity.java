package dev.josegaldamez.firstappmovil001;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import dev.josegaldamez.firstappmovil001.adapters.PersonasAdapter;
import dev.josegaldamez.firstappmovil001.controllers.PersonasController;
import dev.josegaldamez.firstappmovil001.models.Personas;
import dev.josegaldamez.firstappmovil001.views.ActivityPersonas;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPersonas;
    private TextView tvEmpty;

    private PersonasController personasController;
    private PersonasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        personasController = new PersonasController(this);

        recyclerViewPersonas = findViewById(R.id.recyclerViewPersonas);
        tvEmpty = findViewById(R.id.tvEmpty);
        FloatingActionButton fabAddPerson = findViewById(R.id.fabAddPerson);

        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PersonasAdapter(new ArrayList<>());
        recyclerViewPersonas.setAdapter(adapter);

        fabAddPerson.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityPersonas.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarPersonas();
    }

    private void cargarPersonas() {
        List<Personas> lista = personasController.obtenerPersonas();
        adapter.setPersonasList(lista);

        if (lista.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            recyclerViewPersonas.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            recyclerViewPersonas.setVisibility(View.VISIBLE);
        }
    }
}
