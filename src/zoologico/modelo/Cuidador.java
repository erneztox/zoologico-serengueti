package zoologico.modelo;

import java.util.Objects;

/**
 * Representa a un profesional cuidador del zoológico, encargado del
 * bienestar, manejo y supervisión de los animales asignados.
 * 
 * Aplica principios de encapsulamiento mediante validaciones rigurosas
 * en sus constructores y métodos modificadores (setters).
 * 
 * @author Ernesto
 * @version 1.0
 */
public class Cuidador {

    private String identificador;
    private String nombre;
    private String apellidos;
    private String especialidad;
    private int aniosExperiencia;
    private String telefono;

    /**
     * Constructor completo para inicializar un cuidador con todos sus atributos.
     * 
     * @param identificador Código único del cuidador (ej. CUID-01).
     * @param nombre Nombre de pila.
     * @param apellidos Apellidos del cuidador.
     * @param especialidad Área de especialización (ej. Grandes Mamíferos, Herbívoros).
     * @param aniosExperiencia Años acumulados de experiencia profesional (>= 0).
     * @param telefono Teléfono de contacto de emergencia.
     * @throws IllegalArgumentException Si algún parámetro de texto es nulo/vacío o los años son negativos.
     */
    public Cuidador(String identificador, String nombre, String apellidos, 
                     String especialidad, int aniosExperiencia, String telefono) {
        setIdentificador(identificador);
        setNombre(nombre);
        setApellidos(apellidos);
        setEspecialidad(especialidad);
        setAniosExperiencia(aniosExperiencia);
        setTelefono(telefono);
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("El identificador del cuidador no puede ser nulo ni estar vacío.");
        }
        this.identificador = identificador.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cuidador no puede ser nulo ni estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        if (apellidos == null || apellidos.trim().isEmpty()) {
            throw new IllegalArgumentException("Los apellidos del cuidador no pueden ser nulos ni estar vacíos.");
        }
        this.apellidos = apellidos.trim();
    }

    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad del cuidador no puede ser nula ni estar vacía.");
        }
        this.especialidad = especialidad.trim();
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        if (aniosExperiencia < 0) {
            throw new IllegalArgumentException("Los años de experiencia no pueden ser negativos (recibido: " + aniosExperiencia + ").");
        }
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede ser nulo ni estar vacío.");
        }
        this.telefono = telefono.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuidador cuidador = (Cuidador) o;
        return Objects.equals(identificador, cuidador.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s, %d años exp., Tel: %s)",
                identificador, getNombreCompleto(), especialidad, aniosExperiencia, telefono);
    }
}
