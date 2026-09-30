package dev.josegaldamez.firstappmovil001.controllers;

import android.content.Context;

import java.util.List;

import dev.josegaldamez.firstappmovil001.database.DatabaseHelper;
import dev.josegaldamez.firstappmovil001.models.Personas;

public class PersonasController {

    private final DatabaseHelper databaseHelper;

    public PersonasController(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public long guardarPersona(Personas persona) {
        return databaseHelper.insertPerson(persona);
    }

    public List<Personas> obtenerPersonas() {
        return databaseHelper.getAllPersonas();
    }
}
