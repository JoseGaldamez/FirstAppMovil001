package dev.josegaldamez.firstappmovil001.database;

import dev.josegaldamez.firstappmovil001.models.Personas;

public class DatabaseConfiguration {
    public static final String DATABASE_NAME = "personas.db";
    public static final int VERSION = 1;
    public static final String TABLA_PERSONAS = "personas";

    // Campos de la tabla
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_APELLIDO = "apellido";
    public static final String COLUMN_DIRECCION = "direccion";
    public static final String COLUMN_FECHANAC = "fechanac";
    public static final String COLUMN_TELEFONO = "telefono";
    public static final String COLUMN_CORREO = "correo";

    // create tabla
    public static final String CREATE_TABLE_PERSONAS = "CREATE TABLE IF NOT EXISTS "
            + TABLA_PERSONAS + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOMBRE + " TEXT NOT NULL, "
                + COLUMN_APELLIDO + " TEXT NOT NULL, "
                + COLUMN_FECHANAC + " TEXT NOT NULL, "
                + COLUMN_DIRECCION + " TEXT, "
                + COLUMN_TELEFONO + " TEXT, "
                + COLUMN_CORREO + " TEXT);";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLA_PERSONAS + ";";
    public static final String SELECT_ALL = "SELECT * FROM " + TABLA_PERSONAS + ";";

    public static final String INSERT_SINGLE_PERSON = "INSERT INTO " + TABLA_PERSONAS + " ("
                + COLUMN_NOMBRE + ", "
                + COLUMN_APELLIDO + ", "
                + COLUMN_FECHANAC + ", "
                + COLUMN_DIRECCION + ", "
                + COLUMN_TELEFONO + ", "
                + COLUMN_CORREO + ") VALUES (?, ?, ?, ?, ?, ?)";

}
