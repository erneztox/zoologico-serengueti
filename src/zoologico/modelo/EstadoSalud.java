package zoologico.modelo;

/**
 * Enumeración que representa el estado de salud de un animal en el zoológico.
 * Define además si el estado clínico es compatible con la interacción en la zona de contacto.
 * 
 * @author Ernesto
 * @version 1.0
 */
public enum EstadoSalud {
    SALUDABLE("Saludable", true),
    EN_OBSERVACION("En Observación Médica", false),
    EN_TRATAMIENTO("En Tratamiento Clínico", false),
    ENFERMO("Enfermo", false),
    CUARENTENA("En Aislamiento / Cuarentena", false);

    private final String descripcion;
    private final boolean compatibleConContacto;

    EstadoSalud(String descripcion, boolean compatibleConContacto) {
        this.descripcion = descripcion;
        this.compatibleConContacto = compatibleConContacto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCompatibleConContacto() {
        return compatibleConContacto;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
