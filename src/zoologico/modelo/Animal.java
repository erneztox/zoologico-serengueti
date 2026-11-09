package zoologico.modelo;

import java.util.Objects;

/**
 * Clase abstracta que define la estructura y comportamiento base común
 * para todos los animales del Zoológico Serengueti.
 * 
 * Implementa el encapsulamiento protegiendo el estado de los atributos
 * y delegando comportamientos específicos a través de métodos abstractos (polimorfismo).
 * 
 * @author Ernesto
 * @version 1.0
 */
public abstract class Animal {

    private String identificador;
    private String nombre;
    private String especie;
    private int edad;
    private double peso;
    private Sexo sexo;
    private EstadoSalud estadoSalud;
    private String alimentacion;
    private Cuidador cuidadorAsignado;

    /**
     * Constructor para inicializar los atributos esenciales de cualquier animal.
     * 
     * @param identificador Código único del animal (ej. ELE-001).
     * @param nombre Nombre del ejemplar (ej. Dumbo).
     * @param especie Nombre común o científico de la especie.
     * @param edad Edad en años (>= 0).
     * @param peso Peso en kilogramos (> 0).
     * @param sexo Sexo biológico (MACHO o HEMBRA).
     * @param estadoSalud Estado de salud inicial.
     * @param alimentacion Descripción general de su régimen alimenticio.
     * @throws IllegalArgumentException Si algún dato no cumple las validaciones de integridad.
     */
    public Animal(String identificador, String nombre, String especie, int edad, 
                  double peso, Sexo sexo, EstadoSalud estadoSalud, String alimentacion) {
        setIdentificador(identificador);
        setNombre(nombre);
        setEspecie(especie);
        setEdad(edad);
        setPeso(peso);
        setSexo(sexo);
        setEstadoSalud(estadoSalud);
        setAlimentacion(alimentacion);
        this.cuidadorAsignado = null; // Inicialmente sin cuidador asignado
    }

    // --- Métodos Abstractos (Polimorfismo forzoso en subclases) ---

    /**
     * Describe en detalle el régimen alimenticio específico de la especie,
     * las raciones típicas y las particularidades de ingestión.
     * 
     * @return Cadena explicativa de la alimentación de la especie.
     */
    public abstract String describirAlimentacion();

    /**
     * Retorna la información taxonómica, biológica y características
     * destacadas de la especie animal.
     * 
     * @return Cadena con la información zoológica de la especie.
     */
    public abstract String obtenerInformacionEspecie();

    /**
     * Determina si el ejemplar cumple con todas las condiciones biológicas,
     * de temperamento y médicas para interactuar en la zona de contacto.
     * 
     * @return true si es apto; false en caso contrario.
     */
    public abstract boolean esAptoParaContacto();

    // --- Métodos de Operación y Negocio ---

    /**
     * Asigna un cuidador profesional al animal.
     * 
     * @param cuidador Cuidador calificado que se hará cargo del animal.
     * @throws IllegalArgumentException Si el cuidador proporcionado es nulo.
     */
    public void asignarCuidador(Cuidador cuidador) {
        if (cuidador == null) {
            throw new IllegalArgumentException("No se puede asignar un cuidador nulo o inexistente al animal " + nombre + ".");
        }
        this.cuidadorAsignado = cuidador;
    }

    /**
     * Retira la asignación del cuidador actual del animal.
     */
    public void desasignarCuidador() {
        this.cuidadorAsignado = null;
    }

    /**
     * Actualiza el estado de salud del animal.
     * 
     * @param nuevoEstado Nuevo estado clínico diagnosticado.
     * @throws IllegalArgumentException Si el estado es nulo.
     */
    public void actualizarEstadoSalud(EstadoSalud nuevoEstado) {
        setEstadoSalud(nuevoEstado);
    }

    /**
     * Genera una ficha técnica legible y completa del animal.
     * 
     * @return Cadena formateada con todos los datos del ejemplar.
     */
    public String mostrarFichaTecnica() {
        StringBuilder sb = new StringBuilder();
        sb.append("====================================================\n");
        sb.append(String.format(" FICHA TÉCNICA: %s [%s]\n", nombre.toUpperCase(), identificador));
        sb.append("====================================================\n");
        sb.append(String.format(" Especie          : %s\n", especie));
        sb.append(String.format(" Edad             : %d años\n", edad));
        sb.append(String.format(" Peso             : %.2f kg\n", peso));
        sb.append(String.format(" Sexo             : %s\n", sexo));
        sb.append(String.format(" Estado de Salud  : %s\n", estadoSalud));
        sb.append(String.format(" Apto p/ Contacto : %s\n", esAptoParaContacto() ? "SÍ" : "NO"));
        sb.append(String.format(" Cuidador a cargo : %s\n", (cuidadorAsignado != null ? cuidadorAsignado.getNombreCompleto() : "Sin asignar")));
        sb.append(String.format(" Dieta Base       : %s\n", alimentacion));
        sb.append(String.format(" Info Especie     : %s\n", obtenerInformacionEspecie()));
        sb.append("====================================================");
        return sb.toString();
    }

    // --- Getters y Setters con validaciones (Encapsulamiento) ---

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("El identificador del animal no puede ser nulo ni estar vacío.");
        }
        this.identificador = identificador.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del animal no puede ser nulo ni estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        if (especie == null || especie.trim().isEmpty()) {
            throw new IllegalArgumentException("La especie no puede ser nula ni estar vacía.");
        }
        this.especie = especie.trim();
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad del animal no puede ser negativa (valor recibido: " + edad + ").");
        }
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso del animal debe ser estrictamente mayor que cero (valor recibido: " + peso + ").");
        }
        this.peso = peso;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        if (sexo == null) {
            throw new IllegalArgumentException("El sexo del animal no puede ser nulo.");
        }
        this.sexo = sexo;
    }

    public EstadoSalud getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(EstadoSalud estadoSalud) {
        if (estadoSalud == null) {
            throw new IllegalArgumentException("El estado de salud no puede ser nulo.");
        }
        this.estadoSalud = estadoSalud;
    }

    public String getAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(String alimentacion) {
        if (alimentacion == null || alimentacion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la alimentación no puede ser nula ni vacía.");
        }
        this.alimentacion = alimentacion.trim();
    }

    public Cuidador getCuidadorAsignado() {
        return cuidadorAsignado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(identificador, animal.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s, %d años, %.1f kg, Salud: %s, Cuidador: %s)",
                identificador, nombre, especie, edad, peso, estadoSalud,
                (cuidadorAsignado != null ? cuidadorAsignado.getNombreCompleto() : "Sin asignar"));
    }
}
