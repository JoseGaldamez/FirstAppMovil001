# FirstAppMovil001 - Aplicación de Gestión de Personas

Aplicación móvil desarrollada en **Android con Java y XML** que permite gestionar un registro de personas almacenando sus datos de forma persistente mediante una base de datos local **SQLite**.

El proyecto está diseñado siguiendo el patrón de arquitectura **MVC (Modelo-Vista-Controlador)**.

---

## 📱 Capturas y Funcionalidades

- **Pantalla Principal (`MainActivity`)**: Muestra la lista de personas registradas usando `RecyclerView`. Si la lista está vacía, se muestra un mensaje informativo. Incluye un botón flotante (`FloatingActionButton`) para navegar al formulario.
- **Pantalla de Registro (`ActivityPersonas`)**: Formulario para ingresar nombre, apellido, fecha de nacimiento, dirección, teléfono y correo electrónico. Valida campos obligatorios y guarda los registros en SQLite.
- **Actualización Automática**: Al guardar una nueva persona y regresar a la pantalla principal, la lista se actualiza automáticamente a través del ciclo de vida (`onResume`).

---

## 🏗️ Arquitectura del Proyecto (MVC)

El código está organizado en paquetes claramente definidos:

```
dev.josegaldamez.firstappmovil001
 ┣ 📂 models          # Clases POJO de datos (Personas.java)
 ┣ 📂 database        # Configuración y Helper de SQLite (DatabaseConfiguration.java, DatabaseHelper.java)
 ┣ 📂 controllers     # Controlador puente entre la Vista y la Base de Datos (PersonasController.java)
 ┣ 📂 adapters        # Adaptador para el RecyclerView (PersonasAdapter.java)
 ┣ 📂 views           # Vistas secundarias de la UI (ActivityPersonas.java)
 ┗ 📜 MainActivity.java  # Vista principal de la aplicación
```

---

## 🛠️ Tecnologías y Componentes Usados

- **Lenguaje:** Java 11
- **UI & Layouts:** XML con `ConstraintLayout`, `MaterialCardView`, `MaterialToolbar` y `FloatingActionButton`
- **Lista:** `RecyclerView` con `PersonasAdapter` y ViewHolder
- **Persistencia de Datos:** SQLite (`SQLiteOpenHelper`, `db.execSQL` con consultas parametrizadas `?`)
- **Compilación:** Gradle (Kotlin DSL) con soporte para Android SDK 37 (minSdk 24)

---

## ⚙️ Requisitos de Instalación / Ejecución

1. Clonar o abrir el proyecto en **Android Studio** (2024.1 o superior recomendado).
2. Sincronizar el proyecto con los archivos Gradle (`Sync Project with Gradle Files`).
3. Ejecutar la aplicación en un emulador Android (API 24+) o un dispositivo físico mediante depuración USB.

---

## 📝 Estructura de la Base de Datos

Tabla: **`personas`**

| Campo | Tipo | Notas |
| :--- | :--- | :--- |
| `id` | `INTEGER` | Clave primaria, autoincrementable |
| `nombre` | `TEXT` | Campo obligatorio (`NOT NULL`) |
| `apellido` | `TEXT` | Campo obligatorio (`NOT NULL`) |
| `fechanac` | `TEXT` | Campo obligatorio (`NOT NULL`) |
| `direccion` | `TEXT` | Opcional |
| `telefono` | `TEXT` | Opcional |
| `correo` | `TEXT` | Opcional |
