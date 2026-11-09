package zoologico.modelo;

/**
 * Representa a un ejemplar de Elefante (Loxodonta africana), especie herbívora
 * de gran tamaño originaria de la sabana africana.
 * 
 * Especializa el comportamiento de alimentación y ficha zoológica de la especie.
 * 
 * @author Ernesto
 * @version 1.0
 */
public class Elefante extends Herbivoro {

    private double longitudTrompaCm;
    private double longitudColmillosCm;

    /**
     * Constructor completo para Elefante.
     * 
     * @param identificador Código único del animal.
     * @param nombre Nombre del elefante.
     * @param edad Edad en años.
     * @param peso Peso en kilogramos.
     * @param sexo Sexo del animal.
     * @param estadoSalud Estado clínico.
     * @param alimentacion Dieta general asignada.
     * @param tipoForrajePreferido Forraje favorito (ej. Hojas de acacia y bambú).
     * @param habilitadoParaContacto Si está habituado al contacto supervisado con humanos.
     * @param longitudTrompaCm Longitud de la trompa en centímetros (> 0).
     * @param longitudColmillosCm Longitud promedio de colmillos en centímetros (>= 0).
     */
    public Elefante(String identificador, String nombre, int edad, double peso,
                    Sexo sexo, EstadoSalud estadoSalud, String alimentacion,
                    String tipoForrajePreferido, boolean habilitadoParaContacto,
                    double longitudTrompaCm, double longitudColmillosCm) {
        super(identificador, nombre, "Elefante Africano (Loxodonta africana)", edad, peso,
              sexo, estadoSalud, alimentacion, tipoForrajePreferido, habilitadoParaContacto);
        setLongitudTrompaCm(longitudTrompaCm);
        setLongitudColmillosCm(longitudColmillosCm);
    }

    @Override
    public String describirAlimentacion() {
        return String.format(
            "Dieta de Elefante: Consume aproximadamente entre 150 y 180 kg diarios de materia vegetal. " +
            "Su régimen incluye forraje seco, heno de avena, ramas frescas de acacia, cortezas ricas en fibra " +
            "y suplementos de fruta fresca (manzanas y sandías). Bebe hasta 160 litros de agua al día. " +
            "Preferencia particular: %s.", getTipoForrajePreferido()
        );
    }

    @Override
    public String obtenerInformacionEspecie() {
        return String.format(
            "Especie: Elefante Africano (Loxodonta africana). Mayor mamífero terrestre vivo. " +
            "Posee trompa multifuncional con longitud registrada de %.1f cm y colmillos de marfil de %.1f cm. " +
            "Estructura social matriarcal con alta empatía y memoria espacial.",
            longitudTrompaCm, longitudColmillosCm
        );
    }

    public String trompetear() {
        return getNombre() + " levanta su trompa de " + longitudTrompaCm + " cm y emite un sonoro barrito (trompeteo).";
    }

    public double getLongitudTrompaCm() {
        return longitudTrompaCm;
    }

    public void setLongitudTrompaCm(double longitudTrompaCm) {
        if (longitudTrompaCm <= 0) {
            throw new IllegalArgumentException("La longitud de la trompa debe ser mayor que cero (recibido: " + longitudTrompaCm + ").");
        }
        this.longitudTrompaCm = longitudTrompaCm;
    }

    public double getLongitudColmillosCm() {
        return longitudColmillosCm;
    }

    public void setLongitudColmillosCm(double longitudColmillosCm) {
        if (longitudColmillosCm < 0) {
            throw new IllegalArgumentException("La longitud de los colmillos no puede ser negativa (recibido: " + longitudColmillosCm + ").");
        }
        this.longitudColmillosCm = longitudColmillosCm;
    }
}
