package zoologico.principal;

import java.util.ArrayList;
import java.util.List;
import zoologico.gestion.GestionZoologico;
import zoologico.gestion.RegistroAlimentacion;
import zoologico.gestion.RegistroSalud;
import zoologico.gestion.ZonaContacto;
import zoologico.gestion.ZonaContactoException;
import zoologico.modelo.Animal;
import zoologico.modelo.Carnivoro;
import zoologico.modelo.Cebra;
import zoologico.modelo.Cuidador;
import zoologico.modelo.Elefante;
import zoologico.modelo.EstadoSalud;
import zoologico.modelo.Herbivoro;
import zoologico.modelo.Jirafa;
import zoologico.modelo.Leon;
import zoologico.modelo.Sexo;

/**
 * Punto de entrada principal y ejecutor de la demostración integral
 * del Sistema de Gestión del Zoológico Serengueti.
 * 
 * Desarrollado con Java 21 conforme a los estándares de POO y requerimientos académicos.
 * 
 * @author Ernesto
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        imprimirEncabezadoPrincipal();

        GestionZoologico gestion = new GestionZoologico("Zoológico Serengueti - Reserva de Conservación");

        // =====================================================================
        // PASO 1: REGISTRO DE CUIDADORES PROFESIONALES
        // =====================================================================
        imprimirSeccion("1. REGISTRO DE CUIDADORES PROFESIONALES");

        Cuidador c1 = new Cuidador("CUID-01", "Carlos", "Mendoza Rojas", "Grandes Herbívoros", 12, "+34-600-111-222");
        Cuidador c2 = new Cuidador("CUID-02", "Laura", "Morales Gil", "Rumiantes y Jiráfidos", 8, "+34-600-333-444");
        Cuidador c3 = new Cuidador("CUID-03", "David", "Ramos Santos", "Équidos y Sabana", 6, "+34-600-555-666");
        Cuidador c4 = new Cuidador("CUID-04", "Sofía", "Benítez Vega", "Grandes Carnívoros Felinos", 10, "+34-600-777-888");

        gestion.registrarCuidador(c1);
        gestion.registrarCuidador(c2);
        gestion.registrarCuidador(c3);
        gestion.registrarCuidador(c4);

        System.out.println("Se registraron 4 cuidadores profesionales en el sistema:");
        for (Cuidador cuidador : gestion.listarCuidadores()) {
            System.out.println("  ✓ " + cuidador);
        }

        // =====================================================================
        // PASO 2: REGISTRO DE ANIMALES DE DIVERSAS ESPECIES
        // =====================================================================
        imprimirSeccion("2. REGISTRO DE ANIMALES DE DISTINTAS ESPECIES");

        // Herbívoros representativos
        Elefante elefante1 = new Elefante(
            "ELE-0001", "Tambo", 15, 4850.0, Sexo.MACHO, EstadoSalud.SALUDABLE,
            "Forraje fibroso, ramas de acacia y frutas de estación",
            "Ramas frescas de acacia y bambú tierno", true, 165.0, 92.0
        );

        Jirafa jirafa1 = new Jirafa(
            "JIR-0001", "Kenia", 7, 980.0, Sexo.HEMBRA, EstadoSalud.SALUDABLE,
            "Brotes de dosel alto, follaje de acacia y concentrado de vitaminas",
            "Brotes tiernos de acacia espinosa", true, 4.85, 2.10
        );

        Cebra cebra1 = new Cebra(
            "CEB-0001", "Rayas", 5, 340.0, Sexo.HEMBRA, EstadoSalud.SALUDABLE,
            "Pastos duros de sabana, heno de festuca y sales en bloque",
            "Heno de festuca y alfalfa seleccionada", true,
            "Rayas anchas de llanura transversales", 82
        );

        // Carnívoros representativos
        Leon leon1 = new Leon(
            "LEO-0001", "Simba", 8, 205.0, Sexo.MACHO, EstadoSalud.SALUDABLE,
            "Carne vacuna magra fresca con hueso",
            "Corte vacuno con costillar y cartílago", "Extremo",
            "Voluminosa, densa y oscura", true
        );

        Leon leon2 = new Leon(
            "LEO-0002", "Nala", 6, 145.0, Sexo.HEMBRA, EstadoSalud.SALUDABLE,
            "Carne vacuna y de aves con suplementación de calcio",
            "Muslo de res fresca y cuartos traseros", "Alto",
            "Sin melena (hembra)", false
        );

        gestion.registrarAnimal(elefante1);
        gestion.registrarAnimal(jirafa1);
        gestion.registrarAnimal(cebra1);
        gestion.registrarAnimal(leon1);
        gestion.registrarAnimal(leon2);

        System.out.println("Animales iniciales censados exitosamente:");
        System.out.println("  ✓ " + elefante1);
        System.out.println("  ✓ " + jirafa1);
        System.out.println("  ✓ " + cebra1);
        System.out.println("  ✓ " + leon1);
        System.out.println("  ✓ " + leon2);

        // =====================================================================
        // PASO 3: ASIGNACIÓN DE CUIDADORES A LOS ANIMALES
        // =====================================================================
        imprimirSeccion("3. ASIGNACIÓN DE CUIDADORES A LOS ANIMALES");

        gestion.asignarCuidadorAAnimal("ELE-0001", "CUID-01");
        gestion.asignarCuidadorAAnimal("JIR-0001", "CUID-02");
        gestion.asignarCuidadorAAnimal("CEB-0001", "CUID-03");
        gestion.asignarCuidadorAAnimal("LEO-0001", "CUID-04");
        gestion.asignarCuidadorAAnimal("LEO-0002", "CUID-04");

        System.out.println("Asignaciones de cuidadores completadas:");
        System.out.printf("  ✓ %s asignado a %s (%s)\n", elefante1.getNombre(), c1.getNombreCompleto(), c1.getEspecialidad());
        System.out.printf("  ✓ %s asignada a %s (%s)\n", jirafa1.getNombre(), c2.getNombreCompleto(), c2.getEspecialidad());
        System.out.printf("  ✓ %s asignada a %s (%s)\n", cebra1.getNombre(), c3.getNombreCompleto(), c3.getEspecialidad());
        System.out.printf("  ✓ %s y %s asignados a %s (%s)\n", leon1.getNombre(), leon2.getNombre(), c4.getNombreCompleto(), c4.getEspecialidad());

        // =====================================================================
        // PASO 4: REGISTRO Y CONSULTA DE ALIMENTACIÓN
        // =====================================================================
        imprimirSeccion("4. REGISTRO Y SEGUIMIENTO DE ALIMENTACIÓN");

        RegistroAlimentacion regAlim1 = gestion.registrarAlimentacion(
            "ELE-0001", "Heno de avena y ramas de acacia", 160.0, "Ingesta completa sin anomalías."
        );
        RegistroAlimentacion regAlim2 = gestion.registrarAlimentacion(
            "JIR-0001", "Brotes de acacia y follaje fresco", 38.5, "Consumo vigoroso desde canasto aéreo."
        );
        RegistroAlimentacion regAlim3 = gestion.registrarAlimentacion(
            "CEB-0001", "Heno de festuca y heno de alfalfa", 14.0, "Pastoreo en corral exterior."
        );
        RegistroAlimentacion regAlim4 = gestion.registrarAlimentacion(
            "LEO-0001", "Carne vacuna fresca con hueso", 8.5, "Ración carnívora devorada en 20 minutos."
        );

        System.out.println("Registros de alimentación asentados en bitácora:");
        System.out.println("  " + regAlim1.mostrarDetalle());
        System.out.println("  " + regAlim2.mostrarDetalle());
        System.out.println("  " + regAlim3.mostrarDetalle());
        System.out.println("  " + regAlim4.mostrarDetalle());

        // =====================================================================
        // PASO 5: REGISTRO DE CONTROLES DE SALUD Y SEGUIMIENTO VETERINARIO
        // =====================================================================
        imprimirSeccion("5. CONTROLES VETERINARIOS Y TRANSICIÓN DE ESTADO DE SALUD");

        System.out.println("Caso A: Control rutinario de Elefante Tambo:");
        RegistroSalud saludElefante = gestion.registrarControlSalud(
            "ELE-0001", "Dra. Patricia Varela (M.V. 4412)", EstadoSalud.SALUDABLE,
            "Revisión dental y podológica trimestral. Almohadillas plantares en excelente estado.",
            "Limpieza profiláctica de almohadillas plantares."
        );
        System.out.println(saludElefante.mostrarDetalle());

        System.out.println("\nCaso B: Detección y tratamiento de herida menor en Cebra Rayas:");
        RegistroSalud saludCebra = gestion.registrarControlSalud(
            "CEB-0001", "Dr. Andrés Blanco (M.V. 3890)", EstadoSalud.EN_TRATAMIENTO,
            "Escoriación superficial en casco posterior izquierdo tras juego en corral.",
            "Limpieza con yodopovidona y aplicación de pomada antibiótica por 48 horas."
        );
        System.out.println(saludCebra.mostrarDetalle());
        System.out.printf("  -> Estado actual de %s: %s (¿Apto p/ contacto?: %s)\n",
                cebra1.getNombre(), cebra1.getEstadoSalud(), cebra1.esAptoParaContacto() ? "SÍ" : "NO");

        // Posteriormente se le da el alta médica
        System.out.println("\nCaso C: Evolución y alta médica para Cebra Rayas tras 48 horas:");
        RegistroSalud altaCebra = gestion.registrarControlSalud(
            "CEB-0001", "Dr. Andrés Blanco (M.V. 3890)", EstadoSalud.SALUDABLE,
            "Cicatrización completa y movilidad perfecta del casco.",
            "Alta médica concedida. Se autoriza retorno a actividades habituales."
        );
        System.out.println(altaCebra.mostrarDetalle());
        System.out.printf("  -> Estado actual de %s post-alta: %s (¿Apto p/ contacto?: %s)\n",
                cebra1.getNombre(), cebra1.getEstadoSalud(), cebra1.esAptoParaContacto() ? "SÍ" : "NO");

        // =====================================================================
        // PASO 6: DEMOSTRACIÓN DE POLIMORFISMO
        // =====================================================================
        imprimirSeccion("6. DEMOSTRACIÓN DE POLIMORFISMO ENTRE DISTINTAS ESPECIES");

        System.out.println("Recorriendo una colección genérica de Animal (List<Animal>) e invocando");
        System.out.println("los mismos métodos abstractos 'describirAlimentacion()' y 'obtenerInformacionEspecie()':\n");

        List<Animal> muestraPolimorfica = List.of(elefante1, jirafa1, cebra1, leon1);
        for (Animal animal : muestraPolimorfica) {
            System.out.println("====================================================================");
            System.out.printf(" EJEMPLAR: %s | ESPECIE: %s | TIPO: %s\n", 
                    animal.getNombre(), animal.getEspecie(), 
                    (animal instanceof Herbivoro ? "HERBÍVORO" : "CARNÍVORO"));
            System.out.println("--------------------------------------------------------------------");
            System.out.println(" [Alimentación Polimórfica]: " + animal.describirAlimentacion());
            System.out.println(" [Ficha Zoológica]:          " + animal.obtenerInformacionEspecie());
            System.out.println(" [¿Apto Zona de Contacto?]: " + (animal.esAptoParaContacto() ? "AUTORIZADO" : "DENEGADO"));
            
            // Invocación polimórfica específica si corresponde
            if (animal instanceof Elefante e) {
                System.out.println(" [Acción Especial]:          " + e.trompetear());
            } else if (animal instanceof Leon l) {
                System.out.println(" [Acción Especial]:          " + l.rugir());
            }
        }
        System.out.println("====================================================================");

        // =====================================================================
        // PASO 7: REGISTRO DE LOS 60 ANIMALES HERBÍVOROS EN LA ZONA DE CONTACTO
        // =====================================================================
        imprimirSeccion("7. REGISTRO DE 60 HERBÍVOROS EN LA ZONA DE CONTACTO");

        System.out.println("Generando lote controlado de 60 herbívoros (20 elefantes, 20 jirafas y 20 cebras)...");

        // Los 3 primeros ya existen en el sistema (ELE-0001, JIR-0001, CEB-0001)
        // Generamos los restantes hasta completar exactamente 20 de cada especie (60 en total)
        List<Herbivoro> lote60Herbivoros = new ArrayList<>();
        lote60Herbivoros.add(elefante1);
        lote60Herbivoros.add(jirafa1);
        lote60Herbivoros.add(cebra1);

        // 19 elefantes adicionales
        for (int i = 2; i <= 20; i++) {
            String id = String.format("ELE-%04d", i);
            Elefante e = new Elefante(
                id, "Elefante-" + i, 8 + (i % 15), 3500.0 + (i * 50),
                (i % 2 == 0) ? Sexo.HEMBRA : Sexo.MACHO, EstadoSalud.SALUDABLE,
                "Forraje seco, hojas de acacia y frutas", "Acacia africana",
                true, 140.0 + (i % 30), 60.0 + (i % 40)
            );
            gestion.registrarAnimal(e);
            lote60Herbivoros.add(e);
        }

        // 19 jirafas adicionales
        for (int i = 2; i <= 20; i++) {
            String id = String.format("JIR-%04d", i);
            Jirafa j = new Jirafa(
                id, "Jirafa-" + i, 4 + (i % 10), 800.0 + (i * 15),
                (i % 2 == 0) ? Sexo.HEMBRA : Sexo.MACHO, EstadoSalud.SALUDABLE,
                "Ramas tiernas y pellets de forraje", "Brotes de acacia",
                true, 4.20 + ((i % 10) * 0.1), 1.80 + ((i % 5) * 0.1)
            );
            gestion.registrarAnimal(j);
            lote60Herbivoros.add(j);
        }

        // 19 cebras adicionales
        for (int i = 2; i <= 20; i++) {
            String id = String.format("CEB-%04d", i);
            Cebra c = new Cebra(
                id, "Cebra-" + i, 3 + (i % 8), 280.0 + (i * 6),
                (i % 2 == 0) ? Sexo.HEMBRA : Sexo.MACHO, EstadoSalud.SALUDABLE,
                "Pastos de sabana y heno seleccionado", "Pasto de sabana",
                true, "Rayas de llanura patrón-" + (i % 4), 70 + (i % 20)
            );
            gestion.registrarAnimal(c);
            lote60Herbivoros.add(c);
        }

        System.out.printf("Total de herbívoros preparados para admisión: %d ejemplares.\n", lote60Herbivoros.size());
        System.out.println("Ingresando los 60 herbívoros a la Zona de Contacto...");

        int admitidosConExito = 0;
        for (Herbivoro h : lote60Herbivoros) {
            try {
                gestion.incorporarAZonaContacto(h.getIdentificador());
                admitidosConExito++;
            } catch (ZonaContactoException ex) {
                System.err.println("Error inesperado de admisión: " + ex.getMessage());
            }
        }

        System.out.printf("¡Operación completada con éxito! Herbívoros admitidos: %d / %d (Capacidad 100%%).\n",
                admitidosConExito, ZonaContacto.CAPACIDAD_MAXIMA);

        // =====================================================================
        // PASO 8: PRUEBAS DE VALIDACIÓN Y RECHAZO CONTROLADO DE REGLAS DE NEGOCIO
        // =====================================================================
        imprimirSeccion("8. PRUEBAS DE VALIDACIÓN Y COMPROBACIÓN DE REGLAS DE NEGOCIO");

        System.out.println("A) Prueba de Seguridad: Intento de incorporar un CARNÍVORO (León Simba [LEO-0001]):");
        try {
            gestion.incorporarAZonaContacto("LEO-0001");
            System.err.println("FALLO: El sistema admitió indebidamente al carnívoro.");
        } catch (ZonaContactoException e) {
            System.out.println("  [CAPTURA EXITOSA]: " + e.getMessage());
        }

        System.out.println("\nB) Prueba de Integridad: Intento de ingresar animal DUPLICADO (Elefante Tambo [ELE-0001]):");
        try {
            gestion.incorporarAZonaContacto("ELE-0001");
            System.err.println("FALLO: El sistema admitió un animal duplicado.");
        } catch (ZonaContactoException e) {
            System.out.println("  [CAPTURA EXITOSA]: " + e.getMessage());
        }

        System.out.println("\nC) Prueba de Aforo: Intento de incorporar un 61º herbívoro con aforo completo:");
        Elefante elefanteExtra = new Elefante(
            "ELE-0021", "Elefante-Extra", 9, 4100.0, Sexo.MACHO, EstadoSalud.SALUDABLE,
            "Forraje", "Acacia", true, 150.0, 70.0
        );
        gestion.registrarAnimal(elefanteExtra);
        try {
            gestion.incorporarAZonaContacto("ELE-0021");
            System.err.println("FALLO: El sistema superó la capacidad máxima de 60.");
        } catch (ZonaContactoException e) {
            System.out.println("  [CAPTURA EXITOSA]: " + e.getMessage());
        }

        System.out.println("\nD) Prueba Sanitaria: Intento de incorporar un herbívoro NO SALUDABLE:");
        // Liberamos una plaza retirando temporalmente a la Cebra-20
        gestion.retirarDeZonaContacto("CEB-0020");
        System.out.println("  -> Se retiró provisionalmente 'CEB-0020' (Plazas disponibles: " + 
                gestion.getZonaContacto().getPlazasDisponibles() + ")");

        Cebra cebraEnferma = new Cebra(
            "CEB-0099", "Cebra-Aislada", 4, 310.0, Sexo.HEMBRA, EstadoSalud.CUARENTENA,
            "Pastos y heno", "Festuca", true, "Rayas montaña", 80
        );
        gestion.registrarAnimal(cebraEnferma);
        try {
            gestion.incorporarAZonaContacto("CEB-0099");
            System.err.println("FALLO: El sistema admitió un animal en cuarentena.");
        } catch (ZonaContactoException e) {
            System.out.println("  [CAPTURA EXITOSA]: " + e.getMessage());
        }

        // Reincorporamos a la Cebra-20 para dejar la zona de contacto al 100% de su capacidad
        try {
            gestion.incorporarAZonaContacto("CEB-0020");
            System.out.println("  -> Reincorporada 'CEB-0020'. Capacidad restablecida a " + 
                    gestion.getZonaContacto().getCantidadAnimales() + "/60.");
        } catch (ZonaContactoException e) {
            System.err.println("Error al reincorporar: " + e.getMessage());
        }

        // =====================================================================
        // PASO 9: RESUMEN FINAL CONSOLIDADO DEL SISTEMA
        // =====================================================================
        imprimirSeccion("9. RESUMEN FINAL CONSOLIDADO DEL SISTEMA");

        System.out.println(gestion.generarResumenGeneral());
        System.out.println();
        System.out.println(gestion.getZonaContacto().generarReporte());

        System.out.println("\nPrimeros 5 herbívoros activos en la Zona de Contacto (de los 60 registrados):");
        List<Herbivoro> activos = gestion.getZonaContacto().getAnimales();
        for (int i = 0; i < Math.min(5, activos.size()); i++) {
            Herbivoro h = activos.get(i);
            System.out.printf("  [%02d] %-10s | %-38s | Peso: %7.1f kg | Salud: %s\n",
                    (i + 1), h.getIdentificador(), h.getEspecie(), h.getPeso(), h.getEstadoSalud());
        }
        System.out.printf("  ... y %d herbívoros más debidamente certificados y supervisados.\n", (activos.size() - 5));

        imprimirPieFinal();
    }

    private static void imprimirEncabezadoPrincipal() {
        System.out.println("####################################################################");
        System.out.println("#                                                                  #");
        System.out.println("#           SISTEMA DE GESTIÓN DEL ZOOLÓGICO SERENGUETI           #");
        System.out.println("#                    Demostración Integral POO                     #");
        System.out.println("#                            Java 21                               #");
        System.out.println("#                                                                  #");
        System.out.println("####################################################################\n");
    }

    private static void imprimirSeccion(String titulo) {
        System.out.println("\n" + "=".repeat(68));
        System.out.println(" " + titulo);
        System.out.println("=".repeat(68));
    }

    private static void imprimirPieFinal() {
        System.out.println("\n####################################################################");
        System.out.println("#       DEMOSTRACIÓN FINALIZADA SATISFACTORIAMENTE (CÓDIGO 0)      #");
        System.out.println("####################################################################");
    }
}
