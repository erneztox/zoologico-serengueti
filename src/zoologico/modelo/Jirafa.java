package zoologico.modelo;

/**
 * Representa a un ejemplar de Jirafa (Giraffa camelopardalis), herbívoro
 * rumiante caracterizado por su notable altura y cuello alargado.
 * 
 * Implementa polimórficamente la alimentación por ramoneo de copas altas y
 * la información biológica correspondiente a su morfología.
 * 
 * @author Ernesto
 * @version 1.0
 */
public class Jirafa extends Herbivoro {

    private double alturaMetros;
    private double longitudCuelloMetros;

    /**
     * Constructor completo para Jirafa.
     * 
     * @param identificador Código único del animal.
     * @param nombre Nombre de la jirafa.
     * @param edad Edad en años.
     * @param peso Peso en kilogramos.
     * @param sexo Sexo del animal.
     * @param estadoSalud Estado clínico del animal.
     * @param alimentacion Descripción de la dieta asignada.
     * @param tipoForrajePreferido Tipo de forraje favorito (ej. Brotes tiernos de acacia).
     * @param habilitadoParaContacto Si está apta para interacción y alimentación asistida.
     * @param alturaMetros Altura total en metros (> 0).
     * @param longitudCuelloMetros Longitud estimada del cuello en metros (> 0).
     */
    public Jirafa(String identificador, String nombre, int edad, double peso,
                  Sexo sexo, EstadoSalud estadoSalud, String alimentacion,
                  String tipoForrajePreferido, boolean habilitadoParaContacto,
                  double alturaMetros, double longitudCuelloMetros) {
        super(identificador, nombre, "Jirafa (Giraffa camelopardalis)", edad, peso,
              sexo, estadoSalud, alimentacion, tipoForrajePreferido, habilitadoParaContacto);
        setAlturaMetros(alturaMetros);
        setLongitudCuelloMetros(longitudCuelloMetros);
    }

    @Override
    public String describirAlimentacion() {
        return String.format(
            "Dieta de Jirafa: Rumiante ramoneador de dosel superior. Ingiere unos 35 a 45 kg diarios de brotes " +
            "frescos, hojas de acacia y mimosa, utilizando su lengua prensil oscura de hasta 45 cm para sortear espinas. " +
            "Complementa con sales minerales y pellets concentrados. Preferencia: %s.",
            getTipoForrajePreferido()
        );
    }

    @Override
    public String obtenerInformacionEspecie() {
        return String.format(
            "Especie: Jirafa (Giraffa camelopardalis). El mamífero más alto del planeta. " +
            "Altura registrada: %.2f m, longitud de cuello: %.2f m. " +
            "Sistema cardiovascular hipertenso adaptado con válvulas yugulares para regular la irrigación cerebral.",
            alturaMetros, longitudCuelloMetros
        );
    }

    public double getAlturaMetros() {
        return alturaMetros;
    }

    public void setAlturaMetros(double alturaMetros) {
        if (alturaMetros <= 0) {
            throw new IllegalArgumentException("La altura debe ser estrictamente mayor que cero (recibido: " + alturaMetros + ").");
        }
        this.alturaMetros = alturaMetros;
    }

    public double getLongitudCuelloMetros() {
        return longitudCuelloMetros;
    }

    public void setLongitudCuelloMetros(double longitudCuelloMetros) {
        if (longitudCuelloMetros <= 0) {
            throw new IllegalArgumentException("La longitud del cuello debe ser mayor que cero (recibido: " + longitudCuelloMetros + ").");
        }
        this.longitudCuelloMetros = longitudCuelloMetros;
    }
}
