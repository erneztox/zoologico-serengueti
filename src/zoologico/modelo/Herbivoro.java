package zoologico.modelo;

/**
 * Clase abstracta intermedia que representa a los animales herbívoros del zoológico.
 * Especializa la jerarquía de herencia para especies que se alimentan de materia vegetal.
 * 
 * Gestiona el atributo de habilitación expresa para interacción humana y
 * la regla de compatibilidad médica para la zona de contacto.
 * 
 * @author Ernesto
 * @version 1.0
 */
public abstract class Herbivoro extends Animal {

    private String tipoForrajePreferido;
    private boolean habilitadoParaContacto;

    /**
     * Constructor para animales herbívoros.
     * 
     * @param identificador Código único del animal.
     * @param nombre Nombre del ejemplar.
     * @param especie Especie a la que pertenece.
     * @param edad Edad en años.
     * @param peso Peso en kilogramos.
     * @param sexo Sexo del animal.
     * @param estadoSalud Estado de salud inicial.
     * @param alimentacion Descripción de su alimentación.
     * @param tipoForrajePreferido Tipo de forraje o vegetal predilecto (ej. Alfalfa, ramas de acacia).
     * @param habilitadoParaContacto Indicador de si el ejemplar cuenta con temperamento apto y habilitación expresa.
     */
    public Herbivoro(String identificador, String nombre, String especie, int edad, 
                     double peso, Sexo sexo, EstadoSalud estadoSalud, String alimentacion,
                     String tipoForrajePreferido, boolean habilitadoParaContacto) {
        super(identificador, nombre, especie, edad, peso, sexo, estadoSalud, alimentacion);
        setTipoForrajePreferido(tipoForrajePreferido);
        this.habilitadoParaContacto = habilitadoParaContacto;
    }

    /**
     * Evalúa si el herbívoro es apto para participar en la zona de contacto.
     * Requiere habilitación expresa de conducta Y condición médica compatible (Saludable).
     * 
     * @return true si cumple ambas condiciones; false si está deshabilitado o enfermo/en observación.
     */
    @Override
    public boolean esAptoParaContacto() {
        return this.habilitadoParaContacto && getEstadoSalud().isCompatibleConContacto();
    }

    public String getTipoForrajePreferido() {
        return tipoForrajePreferido;
    }

    public void setTipoForrajePreferido(String tipoForrajePreferido) {
        if (tipoForrajePreferido == null || tipoForrajePreferido.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de forraje preferido no puede ser nulo ni estar vacío.");
        }
        this.tipoForrajePreferido = tipoForrajePreferido.trim();
    }

    public boolean isHabilitadoParaContacto() {
        return habilitadoParaContacto;
    }

    public void setHabilitadoParaContacto(boolean habilitadoParaContacto) {
        this.habilitadoParaContacto = habilitadoParaContacto;
    }
}
