package com.example.aguazero.Localidades;

public class DatosLocalidades {

    // Array de localidades
    public static final String[] LOCALIDADES = {
            "Antonio Nariño", "Barrios Unidos", "Bosa", "La Candelaría", "Chapinero",
            "Ciudad Bolívar", "Engativá", "Fontibón", "Kennedy", "Los Martires",
            "Puente Aranda", "Rafael Uribe", "San Cristobal", "Santa Fe", "Suba",
            "Sumapaz", "Teusaquillo", "Tunjuelito", "Usaquén", "Usme"
    };

    // Array de fechas de corte
    public static final String[] FECHAS_CORTE = {
            "29/09/2024", "29/09/2024", "--/--/----", "--/--/----", "29/09/2024",
            "--/--/----", "--/--/----", "--/--/----", "--/--/----", "29/09/2024",
            "29/09/2024", "29/09/2024", "--/--/----", "--/--/----", "--/--/----",
            "--/--/----", "29/09/2024", "29/09/2024", "29/09/2024", "--/--/----"
    };

    // Método que devuelve la fecha de corte para una localidad específica
    public static String obtenerFechaCorte(String localidad) {
        for (int i = 0; i < LOCALIDADES.length; i++) {
            if (LOCALIDADES[i].equals(localidad)) {
                return FECHAS_CORTE[i];
            }
        }
        return "--/--/----"; // En caso de no encontrar la localidad
    }
}

