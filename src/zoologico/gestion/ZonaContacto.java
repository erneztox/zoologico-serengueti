package zoologico.gestion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import zoologico.modelo.Animal;
import zoologico.modelo.Cebra;
import zoologico.modelo.Elefante;
import zoologico.modelo.EstadoSalud;
import zoologico.modelo.Herbivoro;
import zoologico.modelo.Jirafa;

/**
 * Gestiona de forma controlada la Zona de Contacto del zoológico,
 * permitiendo la interacción directa y segura entre los visitantes y hasta
 * un límite estricto de 60 animales herbívoros calificados.
 * 
 * Aplica rigurosamente las reglas de negocio de seguridad zoológica:
 * 1. Solo admite animales de tipo Herbivoro.
 * 2. El ejemplar debe contar con habilitación de conducta expresa.
 * 3. El ejemplar debe gozar de un estado de salud estrictamente compatible (SALUDABLE).
 * 4. Controla la capacidad máxima fija (60 ejemplares).
 * 5. Previene la duplicación de animales en la zona.
 * 
 * @author Ernesto
 * @version 1.0
 */
public class ZonaContacto {

    /**
     * Capacidad máxima permitida de animales en la zona de contacto.
     */
    public static final int CAPACIDAD_MAXIMA = 60;

    private final Map<String, Herbivoro> animalesHabilitados;

    /**
     * Constructor que inicializa la estructura de almacenamiento.
     */
    public ZonaContacto() {
        this.animalesHabilitados = new LinkedHashMap<>();
    }

    /**
     * Evalúa y admite un animal en la zona de contacto si cumple la totalidad
     * de las reglas de admisión zoológicas.
     * 
     * @param animal Ejemplar que se intenta ingresar a la zona.
     * @return true si la admisión se efectuó con éxito.
     * @throws ZonaContactoException Si se incumple cualquiera de las 5 reglas de negocio.
     */
    public boolean admitirAnimal(Animal animal) throws ZonaContactoException {
        if (animal == null) {
            throw new ZonaContactoException("No se puede admitir un animal nulo o no especificado.");
        }

        // Regla 1: Solo herbívoros (Rechazo polimórfico de carnívoros u otros tipos)
        if (!(animal instanceof Herbivoro)) {
            throw new ZonaContactoException(String.format(
                "RECHAZO POR ESPECIE INCOMPATIBLE: El animal '%s' [%s] pertenece a una especie carnívora (%s). " +
                "Por estrictas normas de seguridad para los visitantes, únicamente se admiten animales herbívoros.",
                animal.getNombre(), animal.getIdentificador(), animal.getEspecie()
            ));
        }

        Herbivoro herbivoro = (Herbivoro) animal;

        // Regla 2: Habilitación expresa de conducta y temperamento
        if (!herbivoro.isHabilitadoParaContacto()) {
            throw new ZonaContactoException(String.format(
                "RECHAZO POR FALTA DE HABILITACIÓN: El herbívoro '%s' [%s] no cuenta con habilitación expresa de conducta " +
                "para interacción directa con el público.",
                herbivoro.getNombre(), herbivoro.getIdentificador()
            ));
        }

        // Regla 3: Condición de salud estrictamente compatible (SALUDABLE)
        if (!herbivoro.getEstadoSalud().isCompatibleConContacto()) {
            throw new ZonaContactoException(String.format(
                "RECHAZO POR ESTADO SANITARIO: El animal '%s' [%s] presenta estado de salud '%s', " +
                "incompatible con la zona de contacto. Solo ejemplares con diagnóstico SALUDABLE pueden ingresar.",
                herbivoro.getNombre(), herbivoro.getIdentificador(), herbivoro.getEstadoSalud()
            ));
        }

        // Regla 4: Control de duplicados
        if (animalesHabilitados.containsKey(herbivoro.getIdentificador())) {
            throw new ZonaContactoException(String.format(
                "RECHAZO POR DUPLICIDAD: El animal '%s' [%s] ya se encuentra registrado activamente en la Zona de Contacto.",
                herbivoro.getNombre(), herbivoro.getIdentificador()
            ));
        }

        // Regla 5: Capacidad máxima fija (60 animales)
        if (animalesHabilitados.size() >= CAPACIDAD_MAXIMA) {
            throw new ZonaContactoException(String.format(
                "RECHAZO POR SOBRECUPÓ: La Zona de Contacto ha alcanzado su capacidad máxima permitida (%d/%d animales). " +
                "No es posible ingresar a '%s' [%s].",
                animalesHabilitados.size(), CAPACIDAD_MAXIMA, herbivoro.getNombre(), herbivoro.getIdentificador()
            ));
        }

        // Si supera todas las validaciones, se registra exitosamente
        animalesHabilitados.put(herbivoro.getIdentificador(), herbivoro);
        return true;
    }

    /**
     * Retira a un animal de la zona de contacto según su identificador.
     * 
     * @param identificador Código del animal a retirar.
     * @return El animal retirado, o null si no se encontraba en la zona.
     */
    public Herbivoro retirarAnimal(String identificador) {
        if (identificador == null) return null;
        return animalesHabilitados.remove(identificador.trim());
    }

    /**
     * Consulta si un animal específico se encuentra actualmente en la zona.
     * 
     * @param identificador Código del animal.
     * @return true si está presente; false en caso contrario.
     */
    public boolean contieneAnimal(String identificador) {
        if (identificador == null) return false;
        return animalesHabilitados.containsKey(identificador.trim());
    }

    /**
     * Retorna la cantidad actual de animales presentes en la zona de contacto.
     * 
     * @return Número de ejemplares (0 a 60).
     */
    public int getCantidadAnimales() {
        return animalesHabilitados.size();
    }

    /**
     * Retorna el cupo máximo de la zona.
     * 
     * @return Constante 60.
     */
    public int getCapacidadMaxima() {
        return CAPACIDAD_MAXIMA;
    }

    /**
     * Retorna las plazas vacantes disponibles.
     * 
     * @return Número de vacantes restantes.
     */
    public int getPlazasDisponibles() {
        return CAPACIDAD_MAXIMA - animalesHabilitados.size();
    }

    /**
     * Retorna una vista inmodificable de los animales presentes en la zona.
     * Protege el encapsulamiento interno de la colección.
     * 
     * @return Lista inmodificable de herbívoros habilitados.
     */
    public List<Herbivoro> getAnimales() {
        return Collections.unmodifiableList(new ArrayList<>(animalesHabilitados.values()));
    }

    /**
     * Genera un informe detallado con estadísticas de ocupación y desglose por especie.
     * 
     * @return Reporte formateado en cadena de texto.
     */
    public String generarReporte() {
        long elefantes = animalesHabilitados.values().stream().filter(a -> a instanceof Elefante).count();
        long jirafas = animalesHabilitados.values().stream().filter(a -> a instanceof Jirafa).count();
        long cebras = animalesHabilitados.values().stream().filter(a -> a instanceof Cebra).count();
        double porcentajeOcupacion = (animalesHabilitados.size() * 100.0) / CAPACIDAD_MAXIMA;

        StringBuilder sb = new StringBuilder();
        sb.append("====================================================================\n");
        sb.append("                   ESTADO DE LA ZONA DE CONTACTO                   \n");
        sb.append("====================================================================\n");
        sb.append(String.format(" Capacidad Máxima     : %d animales\n", CAPACIDAD_MAXIMA));
        sb.append(String.format(" Ocupación Actual     : %d animales (%.1f%%)\n", animalesHabilitados.size(), porcentajeOcupacion));
        sb.append(String.format(" Plazas Disponibles   : %d\n", getPlazasDisponibles()));
        sb.append("--------------------------------------------------------------------\n");
        sb.append(" Distribución Biológica por Especie:\n");
        sb.append(String.format("   - Elefantes Africanos : %d ejemplares\n", elefantes));
        sb.append(String.format("   - Jirafas             : %d ejemplares\n", jirafas));
        sb.append(String.format("   - Cebras de Llanura   : %d ejemplares\n", cebras));
        sb.append("====================================================================");
        return sb.toString();
    }
}
