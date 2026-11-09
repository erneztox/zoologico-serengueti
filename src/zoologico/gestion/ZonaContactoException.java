package zoologico.gestion;

/**
 * Excepción de regla de negocio que se lanza cuando se intenta realizar una
 * operación inválida en la Zona de Contacto del zoológico (ej. incorporar un
 * carnívoro, un animal no habilitado, un animal enfermo o exceder la capacidad máxima).
 * 
 * @author Ernesto
 * @version 1.0
 */
public class ZonaContactoException extends Exception {

    private static final long serialVersionUID = 1L;

    public ZonaContactoException(String mensaje) {
        super(mensaje);
    }

    public ZonaContactoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
