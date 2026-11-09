package zoologico.modelo;

/**
 * Enumeración que define el sexo de los animales en el zoológico.
 * 
 * @author Ernesto
 * @version 1.0
 */
public enum Sexo {
    MACHO("Macho"),
    HEMBRA("Hembra");

    private final String descripcion;

    Sexo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
