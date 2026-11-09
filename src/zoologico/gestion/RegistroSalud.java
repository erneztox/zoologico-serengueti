package zoologico.gestion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import zoologico.modelo.Animal;
import zoologico.modelo.EstadoSalud;

/**
 * Representa el informe clínico de una intervención o control médico veterinario
 * aplicado a un animal del zoológico.
 * 
 * Registra el diagnóstico, tratamiento prescrito, facultativo responsable y
 * la transición del estado de salud del ejemplar.
 * 
 * @author Ernesto
 * @version 1.0
 */
public class RegistroSalud {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private String identificadorRegistro;
    private LocalDateTime fechaHora;
    private Animal animal;
    private String veterinario;
    private EstadoSalud estadoPrevio;
    private EstadoSalud nuevoEstado;
    private String diagnostico;
    private String tratamiento;

    /**
     * Constructor completo para registrar una atención médica veterinaria.
     * 
     * @param identificadorRegistro Código único de la consulta médica (ej. MED-001).
     * @param fechaHora Fecha y hora de la intervención. Si es nula, se usa la actual.
     * @param animal Animal examinado.
     * @param veterinario Nombre y credencial del profesional veterinario a cargo.
     * @param estadoPrevio Estado clínico previo a la revisión.
     * @param nuevoEstado Estado clínico asignado post-evaluación.
     * @param diagnostico Cuadro clínico u observaciones diagnósticas.
     * @param tratamiento Terapia, medicamentos o indicaciones recomendadas.
     * @throws IllegalArgumentException Si algún dato obligatorio es nulo o vacío.
     */
    public RegistroSalud(String identificadorRegistro, LocalDateTime fechaHora,
                         Animal animal, String veterinario, EstadoSalud estadoPrevio,
                         EstadoSalud nuevoEstado, String diagnostico, String tratamiento) {
        setIdentificadorRegistro(identificadorRegistro);
        this.fechaHora = (fechaHora != null) ? fechaHora : LocalDateTime.now();
        setAnimal(animal);
        setVeterinario(veterinario);
        setEstadoPrevio(estadoPrevio);
        setNuevoEstado(nuevoEstado);
        setDiagnostico(diagnostico);
        setTratamiento(tratamiento);
    }

    public String mostrarDetalle() {
        return String.format(
            "[%s] %s | Paciente: %s (%s)\n" +
            "       Veterinario : %s\n" +
            "       Transición  : %s -> %s\n" +
            "       Diagnóstico : %s\n" +
            "       Tratamiento : %s",
            identificadorRegistro,
            fechaHora.format(FORMATO_FECHA),
            animal.getNombre(),
            animal.getIdentificador(),
            veterinario,
            estadoPrevio,
            nuevoEstado,
            diagnostico,
            (tratamiento != null && !tratamiento.isBlank() ? tratamiento : "Ninguno requerido")
        );
    }

    public String getIdentificadorRegistro() {
        return identificadorRegistro;
    }

    public void setIdentificadorRegistro(String identificadorRegistro) {
        if (identificadorRegistro == null || identificadorRegistro.trim().isEmpty()) {
            throw new IllegalArgumentException("El identificador del registro médico no puede ser nulo ni vacío.");
        }
        this.identificadorRegistro = identificadorRegistro.trim();
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("El animal asociado al registro de salud no puede ser nulo.");
        }
        this.animal = animal;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        if (veterinario == null || veterinario.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del veterinario no puede ser nulo ni estar vacío.");
        }
        this.veterinario = veterinario.trim();
    }

    public EstadoSalud getEstadoPrevio() {
        return estadoPrevio;
    }

    public void setEstadoPrevio(EstadoSalud estadoPrevio) {
        if (estadoPrevio == null) {
            throw new IllegalArgumentException("El estado previo no puede ser nulo.");
        }
        this.estadoPrevio = estadoPrevio;
    }

    public EstadoSalud getNuevoEstado() {
        return nuevoEstado;
    }

    public void setNuevoEstado(EstadoSalud nuevoEstado) {
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo.");
        }
        this.nuevoEstado = nuevoEstado;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        if (diagnostico == null || diagnostico.trim().isEmpty()) {
            throw new IllegalArgumentException("El diagnóstico médico no puede ser nulo ni estar vacío.");
        }
        this.diagnostico = diagnostico.trim();
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = (tratamiento != null) ? tratamiento.trim() : "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistroSalud that = (RegistroSalud) o;
        return Objects.equals(identificadorRegistro, that.identificadorRegistro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificadorRegistro);
    }

    @Override
    public String toString() {
        return mostrarDetalle();
    }
}
