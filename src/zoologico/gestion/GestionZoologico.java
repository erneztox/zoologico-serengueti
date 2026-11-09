package zoologico.gestion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import zoologico.modelo.Animal;
import zoologico.modelo.Cuidador;
import zoologico.modelo.EstadoSalud;
import zoologico.modelo.Herbivoro;

/**
 * Clase controladora central que coordina las operaciones del Zoológico Serengueti.
 * 
 * Administra el inventario zoológico, el personal de cuidadores,
 * las bitácoras históricas de nutrición y salud veterinaria, y el acceso
 * a la Zona de Contacto con visitantes.
 * 
 * @author Ernesto
 * @version 1.0
 */
public class GestionZoologico {

    private final String nombreZoologico;
    private final Map<String, Animal> animales;
    private final Map<String, Cuidador> cuidadores;
    private final List<RegistroAlimentacion> historialAlimentacion;
    private final List<RegistroSalud> historialSalud;
    private final ZonaContacto zonaContacto;

    private int contadorAlimentacion = 1;
    private int contadorSalud = 1;

    /**
     * Constructor por defecto del sistema gestor.
     */
    public GestionZoologico() {
        this("Zoológico Serengueti");
    }

    /**
     * Constructor que asigna un nombre específico a la entidad zoológica.
     * 
     * @param nombreZoologico Nombre del zoológico.
     */
    public GestionZoologico(String nombreZoologico) {
        if (nombreZoologico == null || nombreZoologico.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del zoológico no puede ser nulo ni vacío.");
        }
        this.nombreZoologico = nombreZoologico.trim();
        this.animales = new LinkedHashMap<>();
        this.cuidadores = new LinkedHashMap<>();
        this.historialAlimentacion = new ArrayList<>();
        this.historialSalud = new ArrayList<>();
        this.zonaContacto = new ZonaContacto();
    }

    // =========================================================================
    // GESTIÓN DE CUIDADORES
    // =========================================================================

    /**
     * Registra un nuevo cuidador en la plantilla profesional del zoológico.
     * 
     * @param cuidador Cuidador a incorporar.
     * @throws IllegalArgumentException Si el cuidador es nulo o ya existe su ID.
     */
    public void registrarCuidador(Cuidador cuidador) {
        if (cuidador == null) {
            throw new IllegalArgumentException("El cuidador a registrar no puede ser nulo.");
        }
        if (cuidadores.containsKey(cuidador.getIdentificador())) {
            throw new IllegalArgumentException("Ya existe un cuidador registrado con el ID: " + cuidador.getIdentificador());
        }
        cuidadores.put(cuidador.getIdentificador(), cuidador);
    }

    /**
     * Busca un cuidador por su código identificador.
     * 
     * @param idCuidador Código del cuidador.
     * @return El cuidador encontrado, o null si no existe.
     */
    public Cuidador buscarCuidador(String idCuidador) {
        if (idCuidador == null) return null;
        return cuidadores.get(idCuidador.trim());
    }

    /**
     * Retorna la lista inmodificable de cuidadores registrados.
     * 
     * @return Lista inmodificable de cuidadores.
     */
    public List<Cuidador> listarCuidadores() {
        return Collections.unmodifiableList(new ArrayList<>(cuidadores.values()));
    }

    // =========================================================================
    // GESTIÓN DE ANIMALES
    // =========================================================================

    /**
     * Registra un nuevo animal en el censo del zoológico.
     * 
     * @param animal Ejemplar a registrar.
     * @throws IllegalArgumentException Si el animal es nulo o su ID ya está ocupado.
     */
    public void registrarAnimal(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("El animal a registrar no puede ser nulo.");
        }
        if (animales.containsKey(animal.getIdentificador())) {
            throw new IllegalArgumentException("Ya existe un animal registrado con el ID: " + animal.getIdentificador());
        }
        animales.put(animal.getIdentificador(), animal);
    }

    /**
     * Busca un animal por su código identificador.
     * 
     * @param idAnimal Código único del ejemplar.
     * @return El animal localizado, o null si no se encuentra en el censo.
     */
    public Animal buscarAnimal(String idAnimal) {
        if (idAnimal == null) return null;
        return animales.get(idAnimal.trim());
    }

    /**
     * Retorna la lista inmodificable de todos los animales registrados.
     * 
     * @return Lista de animales censados.
     */
    public List<Animal> listarAnimales() {
        return Collections.unmodifiableList(new ArrayList<>(animales.values()));
    }

    /**
     * Asigna formalmente un cuidador registrado a un animal específico.
     * 
     * @param idAnimal Identificador del animal.
     * @param idCuidador Identificador del cuidador.
     * @throws IllegalArgumentException Si alguno de los IDs no existe o es inválido.
     */
    public void asignarCuidadorAAnimal(String idAnimal, String idCuidador) {
        Animal animal = buscarAnimal(idAnimal);
        if (animal == null) {
            throw new IllegalArgumentException("No se encontró el animal con ID: " + idAnimal);
        }
        Cuidador cuidador = buscarCuidador(idCuidador);
        if (cuidador == null) {
            throw new IllegalArgumentException("No se encontró el cuidador con ID: " + idCuidador);
        }
        animal.asignarCuidador(cuidador);
    }

    // =========================================================================
    // GESTIÓN DE ALIMENTACIÓN
    // =========================================================================

    /**
     * Registra un evento de alimentación para un animal, generando un código
     * único y almacenándolo en la bitácora nutricional histórica.
     * 
     * @param idAnimal Identificador del animal que recibe el alimento.
     * @param tipoAlimento Tipo de alimento suministrado.
     * @param cantidadKg Cantidad en kilogramos.
     * @param observaciones Notas u observaciones.
     * @return El objeto RegistroAlimentacion creado.
     */
    public RegistroAlimentacion registrarAlimentacion(String idAnimal, String tipoAlimento, 
                                                      double cantidadKg, String observaciones) {
        Animal animal = buscarAnimal(idAnimal);
        if (animal == null) {
            throw new IllegalArgumentException("No es posible registrar alimentación: animal no encontrado con ID " + idAnimal);
        }
        String idRegistro = String.format("ALIM-%04d", contadorAlimentacion++);
        RegistroAlimentacion registro = new RegistroAlimentacion(
            idRegistro, LocalDateTime.now(), animal, tipoAlimento, cantidadKg, observaciones
        );
        historialAlimentacion.add(registro);
        return registro;
    }

    /**
     * Obtiene el historial de raciones y alimentación de un animal particular.
     * 
     * @param idAnimal Identificador del animal.
     * @return Lista de registros de alimentación asociados al animal.
     */
    public List<RegistroAlimentacion> consultarHistorialAlimentacion(String idAnimal) {
        return historialAlimentacion.stream()
                .filter(reg -> reg.getAnimal().getIdentificador().equalsIgnoreCase(idAnimal.trim()))
                .collect(Collectors.toList());
    }

    /**
     * Retorna la totalidad del historial de alimentación del zoológico.
     * 
     * @return Lista inmodificable con todos los registros nutricionales.
     */
    public List<RegistroAlimentacion> listarHistorialAlimentacionCompleto() {
        return Collections.unmodifiableList(historialAlimentacion);
    }

    // =========================================================================
    // GESTIÓN DE SALUD VETERINARIA
    // =========================================================================

    /**
     * Registra una evaluación clínica veterinaria, actualizando el estado de salud
     * del animal e incorporando el diagnóstico y tratamiento a su historial médico.
     * 
     * @param idAnimal Identificador del paciente animal.
     * @param veterinario Nombre del médico veterinario.
     * @param nuevoEstado Estado clínico asignado.
     * @param diagnostico Cuadro o hallazgos clínicos.
     * @param tratamiento Tratamiento o indicaciones médicas.
     * @return El objeto RegistroSalud generado.
     */
    public RegistroSalud registrarControlSalud(String idAnimal, String veterinario,
                                               EstadoSalud nuevoEstado, String diagnostico, 
                                               String tratamiento) {
        Animal animal = buscarAnimal(idAnimal);
        if (animal == null) {
            throw new IllegalArgumentException("No es posible registrar control de salud: animal no encontrado con ID " + idAnimal);
        }

        EstadoSalud estadoPrevio = animal.getEstadoSalud();
        // Se actualiza el estado clínico en la ficha del animal
        animal.actualizarEstadoSalud(nuevoEstado);

        // Si el animal estaba en la zona de contacto y su nuevo estado ya no es saludable,
        // se retira automáticamente por motivos de bioseguridad
        if (zonaContacto.contieneAnimal(idAnimal) && !nuevoEstado.isCompatibleConContacto()) {
            zonaContacto.retirarAnimal(idAnimal);
        }

        String idRegistro = String.format("SALUD-%04d", contadorSalud++);
        RegistroSalud registro = new RegistroSalud(
            idRegistro, LocalDateTime.now(), animal, veterinario, 
            estadoPrevio, nuevoEstado, diagnostico, tratamiento
        );
        historialSalud.add(registro);
        return registro;
    }

    /**
     * Consulta el expediente médico histórico de un animal específico.
     * 
     * @param idAnimal Identificador del animal.
     * @return Lista de atenciones veterinarias registradas para el animal.
     */
    public List<RegistroSalud> consultarHistorialSalud(String idAnimal) {
        return historialSalud.stream()
                .filter(reg -> reg.getAnimal().getIdentificador().equalsIgnoreCase(idAnimal.trim()))
                .collect(Collectors.toList());
    }

    /**
     * Retorna el expediente sanitario completo de todas las consultas médicas del parque.
     * 
     * @return Lista inmodificable de registros de salud.
     */
    public List<RegistroSalud> listarHistorialSaludCompleto() {
        return Collections.unmodifiableList(historialSalud);
    }

    // =========================================================================
    // GESTIÓN DE ZONA DE CONTACTO
    // =========================================================================

    /**
     * Incorpora un animal censado a la Zona de Contacto, validando previamente
     * todas las restricciones biológicas, médicas y de capacidad.
     * 
     * @param idAnimal Identificador del animal.
     * @return true si la admisión fue exitosa.
     * @throws ZonaContactoException Si se violan las reglas de la zona.
     * @throws IllegalArgumentException Si el animal no existe en el sistema.
     */
    public boolean incorporarAZonaContacto(String idAnimal) throws ZonaContactoException {
        Animal animal = buscarAnimal(idAnimal);
        if (animal == null) {
            throw new IllegalArgumentException("No se encontró el animal con ID: " + idAnimal);
        }
        return zonaContacto.admitirAnimal(animal);
    }

    /**
     * Retira un animal de la Zona de Contacto.
     * 
     * @param idAnimal Identificador del animal.
     * @return El animal retirado, o null si no estaba en la zona.
     */
    public Herbivoro retirarDeZonaContacto(String idAnimal) {
        return zonaContacto.retirarAnimal(idAnimal);
    }

    /**
     * Obtiene la instancia activa de la Zona de Contacto.
     * 
     * @return Instancia de ZonaContacto.
     */
    public ZonaContacto getZonaContacto() {
        return zonaContacto;
    }

    // =========================================================================
    // INFORMES Y ESTADÍSTICAS GLOBALES
    // =========================================================================

    /**
     * Genera un balance general del zoológico con métricas globales.
     * 
     * @return Resumen consolidado del estado de la institución.
     */
    public String generarResumenGeneral() {
        long totalHerbi = animales.values().stream().filter(a -> a instanceof Herbivoro).count();
        long totalCarni = animales.size() - totalHerbi;
        long animalesSaludables = animales.values().stream().filter(a -> a.getEstadoSalud() == EstadoSalud.SALUDABLE).count();

        StringBuilder sb = new StringBuilder();
        sb.append("====================================================================\n");
        sb.append(String.format("          BALANCE GENERAL DEL %s          \n", nombreZoologico.toUpperCase()));
        sb.append("====================================================================\n");
        sb.append(String.format(" Total de Animales Registrados: %d\n", animales.size()));
        sb.append(String.format("   - Herbívoros               : %d\n", totalHerbi));
        sb.append(String.format("   - Carnívoros               : %d\n", totalCarni));
        sb.append(String.format("   - Estado Clínico Saludable : %d\n", animalesSaludables));
        sb.append(String.format(" Total de Cuidadores en Planta: %d\n", cuidadores.size()));
        sb.append(String.format(" Registros de Alimentación    : %d\n", historialAlimentacion.size()));
        sb.append(String.format(" Registros de Salud Médica    : %d\n", historialSalud.size()));
        sb.append(String.format(" Animales en Zona de Contacto : %d / %d (Capacidad Máx.)\n", 
                zonaContacto.getCantidadAnimales(), zonaContacto.getCapacidadMaxima()));
        sb.append("====================================================================");
        return sb.toString();
    }

    public String getNombreZoologico() {
        return nombreZoologico;
    }
}
