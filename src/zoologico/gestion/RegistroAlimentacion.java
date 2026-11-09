package zoologico.gestion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import zoologico.modelo.Animal;

/**
 * Representa el registro individual de una sesión de alimentación
 * suministrada a un animal específico del zoológico.
 * 
 * Permite mantener trazabilidad histórica de raciones, fechas y observaciones.
 * 
 * @author Ernesto
 * @version 1.0
 */
public class RegistroAlimentacion {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private String identificadorRegistro;
    private LocalDateTime fechaHora;
    private Animal animal;
    private String tipoAlimento;
    private double cantidadKg;
    private String observaciones;

    /**
     * Constructor para registrar un suministro de alimento.
     * 
     * @param identificadorRegistro Código único de control (ej. ALIM-001).
     * @param fechaHora Fecha y hora exacta de suministro (si es nulo, asigna la actual).
     * @param animal Animal destinatario de la alimentación.
     * @param tipoAlimento Descripción del alimento suministrado (ej. Heno de alfalfa, Carne vacuna).
     * @param cantidadKg Cantidad en kilogramos (> 0).
     * @param observaciones Comentarios adicionales o novedades durante la ingesta.
     * @throws IllegalArgumentException Si algún parámetro obligatorio es inválido o cantidad <= 0.
     */
    public RegistroAlimentacion(String identificadorRegistro, LocalDateTime fechaHora,
                                Animal animal, String tipoAlimento, double cantidadKg,
                                String observaciones) {
        setIdentificadorRegistro(identificadorRegistro);
        this.fechaHora = (fechaHora != null) ? fechaHora : LocalDateTime.now();
        setAnimal(animal);
        setTipoAlimento(tipoAlimento);
        setCantidadKg(cantidadKg);
        setObservaciones(observaciones);
    }

    public String mostrarDetalle() {
        return String.format(
            "[%s] %s | Animal: %s (%s) | Alimento: %s | Cantidad: %.2f kg | Obs: %s",
            identificadorRegistro,
            fechaHora.format(FORMATO_FECHA),
            animal.getNombre(),
            animal.getIdentificador(),
            tipoAlimento,
            cantidadKg,
            (observaciones != null && !observaciones.isBlank() ? observaciones : "Sin observaciones")
        );
    }

    public String getIdentificadorRegistro() {
        return identificadorRegistro;
    }

    public void setIdentificadorRegistro(String identificadorRegistro) {
        if (identificadorRegistro == null || identificadorRegistro.trim().isEmpty()) {
            throw new IllegalArgumentException("El identificador del registro de alimentación no puede ser nulo ni vacío.");
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
            throw new IllegalArgumentException("El animal asociado al registro de alimentación no puede ser nulo.");
        }
        this.animal = animal;
    }

    public String getTipoAlimento() {
        return tipoAlimento;
    }

    public void setTipoAlimento(String tipoAlimento) {
        if (tipoAlimento == null || tipoAlimento.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de alimento no puede ser nulo ni estar vacío.");
        }
        this.tipoAlimento = tipoAlimento.trim();
    }

    public double getCantidadKg() {
        return cantidadKg;
    }

    public void setCantidadKg(double cantidadKg) {
        if (cantidadKg <= 0) {
            throw new IllegalArgumentException("La cantidad de alimento debe ser estrictamente mayor que cero (recibido: " + cantidadKg + ").");
        }
        this.cantidadKg = cantidadKg;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = (observaciones != null) ? observaciones.trim() : "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistroAlimentacion that = (RegistroAlimentacion) o;
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
