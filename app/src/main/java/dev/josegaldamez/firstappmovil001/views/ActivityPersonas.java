package dev.josegaldamez.firstappmovil001.views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import dev.josegaldamez.firstappmovil001.R;
import dev.josegaldamez.firstappmovil001.controllers.PersonasController;
import dev.josegaldamez.firstappmovil001.models.Personas;

public class ActivityPersonas extends AppCompatActivity {

    EditText txtNombre, txtApellido, txtDireccion, txtFechaNac, txtCorreo, txtTelefono;
    Button btnSavePersonas;

    PersonasController personasController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personas);

        personasController = new PersonasController(this);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        txtNombre = findViewById(R.id.nombre);
        txtApellido = findViewById(R.id.apellido);
        txtDireccion = findViewById(R.id.direccion);
        txtFechaNac = findViewById(R.id.fechaNac);
        txtTelefono = findViewById(R.id.phone);
        txtCorreo = findViewById(R.id.email);

        btnSavePersonas = findViewById(R.id.savePersona);

        btnSavePersonas.setOnClickListener(v -> guardarPersona());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void guardarPersona() {
        String nombre = txtNombre.getText().toString().trim();
        String apellido = txtApellido.getText().toString().trim();
        String direccion = txtDireccion.getText().toString().trim();
        String fechaNac = txtFechaNac.getText().toString().trim();
        String telefono = txtTelefono.getText().toString().trim();
        String correo = txtCorreo.getText().toString().trim();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            Toast.makeText(this, R.string.validation_required_fields, Toast.LENGTH_SHORT).show();
            return;
        }

        Personas persona = new Personas(nombre, apellido, fechaNac, direccion, telefono, correo);

        long idResult = personasController.guardarPersona(persona);

        if (idResult > 0) {
            Toast.makeText(this, R.string.person_saved_success, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, R.string.person_saved_error, Toast.LENGTH_SHORT).show();
        }
    }
}
