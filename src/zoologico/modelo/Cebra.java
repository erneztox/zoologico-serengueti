package zoologico.modelo;

/**
 * Representa a un ejemplar de Cebra (Equus quagga), herbívoro perisodáctilo
 * de las planicies y sabanas africanas.
 * 
 * Modela el patrón exclusivo de franjas y su sistema digestivo de fermentación en el ciego.
 * 
 * @author Ernesto
 * @version 1.0
 */
public class Cebra extends Herbivoro {

    private String patronRayas;
    private int cantidadRayasAprox;

    /**
     * Constructor completo para Cebra.
     * 
     * @param identificador Código único del animal.
     * @param nombre Nombre del ejemplar.
     * @param edad Edad en años.
     * @param peso Peso en kilogramos.
     * @param sexo Sexo del animal.
     * @param estadoSalud Estado clínico.
     * @param alimentacion Dieta general asignada.
     * @param tipoForrajePreferido Forraje favorito (ej. Pastos duros de sabana y heno de festuca).
     * @param habilitadoParaContacto Si tolera cercanía supervisada en zona de contacto.
     * @param patronRayas Descripción del patrón morfológico de rayas.
     * @param cantidadRayasAprox Estimación del número de franjas transversales (> 0).
     */
    public Cebra(String identificador, String nombre, int edad, double peso,
                 Sexo sexo, EstadoSalud estadoSalud, String alimentacion,
                 String tipoForrajePreferido, boolean habilitadoParaContacto,
                 String patronRayas, int cantidadRayasAprox) {
        super(identificador, nombre, "Cebra de Llanura (Equus quagga)", edad, peso,
              sexo, estadoSalud, alimentacion, tipoForrajePreferido, habilitadoParaContacto);
        setPatronRayas(patronRayas);
        setCantidadRayasAprox(cantidadRayasAprox);
    }

    @Override
    public String describirAlimentacion() {
        return String.format(
            "Dieta de Cebra: Pastador pionero con fermentación en el intestino posterior (ciego). " +
            "Consume entre 12 y 16 kg de pastos fibrosos y duros de sabana, heno seleccionado de alfalfa " +
            "y suplementación con bloques minerales. Forraje preferente: %s.",
            getTipoForrajePreferido()
        );
    }

    @Override
    public String obtenerInformacionEspecie() {
        return String.format(
            "Especie: Cebra de Llanura (Equus quagga). Equino gregario africano. " +
            "Patrón de pelaje: %s con un aproximado de %d franjas únicas irrepetibles. " +
            "Su diseño rayado genera efecto estroboscópico ante depredadores y actúa como termorregulador natural.",
            patronRayas, cantidadRayasAprox
        );
    }

    public String getPatronRayas() {
        return patronRayas;
    }

    public void setPatronRayas(String patronRayas) {
        if (patronRayas == null || patronRayas.trim().isEmpty()) {
            throw new IllegalArgumentException("El patrón de rayas no puede ser nulo ni estar vacío.");
        }
        this.patronRayas = patronRayas.trim();
    }

    public int getCantidadRayasAprox() {
        return cantidadRayasAprox;
    }

    public void setCantidadRayasAprox(int cantidadRayasAprox) {
        if (cantidadRayasAprox <= 0) {
            throw new IllegalArgumentException("La cantidad estimada de rayas debe ser mayor que cero (recibido: " + cantidadRayasAprox + ").");
        }
        this.cantidadRayasAprox = cantidadRayasAprox;
    }
}
