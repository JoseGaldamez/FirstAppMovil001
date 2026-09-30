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

    private RecyclerView recyclerListViewPersonas;
    private TextView tvEmpty;

    private PersonasController personasController;
    private PersonasAdapter adapter;

    // T001 - Aquí comienza al aplicación
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        personasController = new PersonasController(this);

        recyclerListViewPersonas = findViewById(R.id.recyclerViewPersonas);
        tvEmpty = findViewById(R.id.tvEmpty);
        FloatingActionButton fabAddPerson = findViewById(R.id.fabAddPerson);

        recyclerListViewPersonas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PersonasAdapter(new ArrayList<>());
        recyclerListViewPersonas.setAdapter(adapter);

        // T008 - Este botón abre la vista de nueva persona
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

    // T002 - Esto se ejecuta cuando la pantalla pasa a primer plano
    @Override
    protected void onResume() {
        super.onResume();
        cargarPersonas(); // T003 - Esto busca las personas ya guardadas
    }

    private void cargarPersonas() {
        // T009 - El controlador es quien le envía las instrucciones a la base de datos.
        List<Personas> lista = personasController.obtenerPersonas();
        adapter.setPersonasList(lista);

        if (lista.isEmpty()) { // T004 - Si no hay personas, muestra un mensaje diciendo que está vacío
            tvEmpty.setVisibility(View.VISIBLE);
            recyclerListViewPersonas.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            recyclerListViewPersonas.setVisibility(View.VISIBLE); // T005 - Si hay personas, entonces las muestra en una lista
        }
    }
}
