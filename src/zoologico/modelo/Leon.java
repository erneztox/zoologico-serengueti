package zoologico.modelo;

/**
 * Representa a un ejemplar de León africano (Panthera leo), superdepredador carnívoro
 * representativo del ecosistema del Serengueti.
 * 
 * Implementa las particularidades de alimentación carnívora y características
 * de comportamiento gregario de la especie.
 * 
 * @author Ernesto
 * @version 1.0
 */
public class Leon extends Carnivoro {

    private String tamanoMelena;
    private boolean esLiderManada;

    /**
     * Constructor completo para León.
     * 
     * @param identificador Código único del animal.
     * @param nombre Nombre del león.
     * @param edad Edad en años.
     * @param peso Peso en kilogramos.
     * @param sexo Sexo del león (MACHO o HEMBRA).
     * @param estadoSalud Estado clínico.
     * @param alimentacion Dieta general asignada.
     * @param tipoCarne Corte de carne suministrado (ej. Carne vacuna fresca con hueso y vísceras).
     * @param nivelPeligrosidad Nivel de riesgo en su manejo (ej. Extremo).
     * @param tamanoMelena Descripción de la melena (ej. Densa, oscura y tupida, o 'N/A' en hembras).
     * @param esLiderManada Indica si ocupa la posición dominante en su manada.
     */
    public Leon(String identificador, String nombre, int edad, double peso,
                Sexo sexo, EstadoSalud estadoSalud, String alimentacion,
                String tipoCarne, String nivelPeligrosidad,
                String tamanoMelena, boolean esLiderManada) {
        super(identificador, nombre, "León Africano (Panthera leo)", edad, peso,
              sexo, estadoSalud, alimentacion, tipoCarne, nivelPeligrosidad);
        setTamanoMelena(tamanoMelena);
        this.esLiderManada = esLiderManada;
    }

    @Override
    public String describirAlimentacion() {
        return String.format(
            "Dieta de León: Carnívoro estricto con raciones de 7 a 10 kg diarios de carne fresca. " +
            "Se suministra %s complementada con cartílago, huesos recreativos para salud dental y taurina esencial. " +
            "Se programa un día semanal de ayuno controlado para emular los ciclos digestivos de caza en vida silvestre.",
            getTipoCarne()
        );
    }

    @Override
    public String obtenerInformacionEspecie() {
        return String.format(
            "Especie: León Africano (Panthera leo). Único felino social que vive en manadas familiares complejas. " +
            "Características: Melena: '%s' | Líder de manada: %s | Nivel de riesgo: %s. " +
            "Rugido audible a más de 8 km a la redonda como señal territorial.",
            tamanoMelena, (esLiderManada ? "SÍ" : "NO"), getNivelPeligrosidad()
        );
    }

    public String rugir() {
        return getNombre() + " se yergue majestuoso y lanza un rugido potente y gutural que hace retumbar el recinto.";
    }

    public String getTamanoMelena() {
        return tamanoMelena;
    }

    public void setTamanoMelena(String tamanoMelena) {
        if (tamanoMelena == null || tamanoMelena.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la melena no puede ser nula ni estar vacía.");
        }
        this.tamanoMelena = tamanoMelena.trim();
    }

    public boolean isEsLiderManada() {
        return esLiderManada;
    }

    public void setEsLiderManada(boolean esLiderManada) {
        this.esLiderManada = esLiderManada;
    }
}
