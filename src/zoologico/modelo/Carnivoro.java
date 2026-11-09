package zoologico.modelo;

/**
 * Clase abstracta intermedia que representa a los animales carnívoros del zoológico.
 * Modela los requerimientos de manejo, dieta cárnica y protocolos de seguridad.
 * 
 * Por definición de seguridad biológica y física de los visitantes, los animales
 * carnívoros nunca están habilitados para la zona de contacto.
 * 
 * @author Ernesto
 * @version 1.0
 */
public abstract class Carnivoro extends Animal {

    private String tipoCarne;
    private String nivelPeligrosidad;

    /**
     * Constructor para animales carnívoros.
     * 
     * @param identificador Código único del animal.
     * @param nombre Nombre del ejemplar.
     * @param especie Especie a la que pertenece.
     * @param edad Edad en años.
     * @param peso Peso en kilogramos.
     * @param sexo Sexo del animal.
     * @param estadoSalud Estado de salud inicial.
     * @param alimentacion Descripción de su alimentación.
     * @param tipoCarne Tipo de corte cárnico suministrado (ej. Carne vacuna fresca con hueso).
     * @param nivelPeligrosidad Nivel de riesgo en su manejo (ej. Alto, Extremo).
     */
    public Carnivoro(String identificador, String nombre, String especie, int edad, 
                     double peso, Sexo sexo, EstadoSalud estadoSalud, String alimentacion,
                     String tipoCarne, String nivelPeligrosidad) {
        super(identificador, nombre, especie, edad, peso, sexo, estadoSalud, alimentacion);
        setTipoCarne(tipoCarne);
        setNivelPeligrosidad(nivelPeligrosidad);
    }

    /**
     * Por estrictas normas de seguridad y preservación de visitantes,
     * ningún carnívoro es apto para la zona de contacto.
     * 
     * @return Siempre retorna false.
     */
    @Override
    public boolean esAptoParaContacto() {
        return false;
    }

    public String getTipoCarne() {
        return tipoCarne;
    }

    public void setTipoCarne(String tipoCarne) {
        if (tipoCarne == null || tipoCarne.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de carne no puede ser nulo ni estar vacío.");
        }
        this.tipoCarne = tipoCarne.trim();
    }

    public String getNivelPeligrosidad() {
        return nivelPeligrosidad;
    }

    public void setNivelPeligrosidad(String nivelPeligrosidad) {
        if (nivelPeligrosidad == null || nivelPeligrosidad.trim().isEmpty()) {
            throw new IllegalArgumentException("El nivel de peligrosidad no puede ser nulo ni estar vacío.");
        }
        this.nivelPeligrosidad = nivelPeligrosidad.trim();
    }
}
