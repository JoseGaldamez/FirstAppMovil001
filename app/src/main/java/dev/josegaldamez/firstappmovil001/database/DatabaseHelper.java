package dev.josegaldamez.firstappmovil001.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import dev.josegaldamez.firstappmovil001.models.Personas;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, DatabaseConfiguration.DATABASE_NAME, null, DatabaseConfiguration.VERSION);
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseConfiguration.CREATE_TABLE_PERSONAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            sqLiteDatabase.execSQL(DatabaseConfiguration.DROP_TABLE);
            onCreate(sqLiteDatabase);
        }
    }

    public long insertPerson(Personas persona) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String sql = DatabaseConfiguration.INSERT_SINGLE_PERSON;

            db.execSQL(sql, new Object[]{
                    persona.getNombre(),
                    persona.getApellido(),
                    persona.getFechaNac(),
                    persona.getDireccion(),
                    persona.getTelefono(),
                    persona.getCorreo()
            });
            db.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Personas> getAllPersonas() {
        List<Personas> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(DatabaseConfiguration.SELECT_ALL, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(DatabaseConfiguration.COLUMN_ID);
                int nombreIndex = cursor.getColumnIndex(DatabaseConfiguration.COLUMN_NOMBRE);
                int apellidoIndex = cursor.getColumnIndex(DatabaseConfiguration.COLUMN_APELLIDO);
                int fechaNacIndex = cursor.getColumnIndex(DatabaseConfiguration.COLUMN_FECHANAC);
                int direccionIndex = cursor.getColumnIndex(DatabaseConfiguration.COLUMN_DIRECCION);
                int telefonoIndex = cursor.getColumnIndex(DatabaseConfiguration.COLUMN_TELEFONO);
                int correoIndex = cursor.getColumnIndex(DatabaseConfiguration.COLUMN_CORREO);

                Personas persona = new Personas();
                if (idIndex != -1) persona.setId(cursor.getInt(idIndex));
                if (nombreIndex != -1) persona.setNombre(cursor.getString(nombreIndex));
                if (apellidoIndex != -1) persona.setApellido(cursor.getString(apellidoIndex));
                if (fechaNacIndex != -1) persona.setFechaNac(cursor.getString(fechaNacIndex));
                if (direccionIndex != -1) persona.setDireccion(cursor.getString(direccionIndex));
                if (telefonoIndex != -1) persona.setTelefono(cursor.getString(telefonoIndex));
                if (correoIndex != -1) persona.setCorreo(cursor.getString(correoIndex));

                lista.add(persona);
            } while (cursor.moveToNext());
            cursor.close();
        }

        db.close();
        return lista;
    }
}
