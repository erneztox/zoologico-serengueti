# Sistema de Gestión del Zoológico Serengueti

Proyecto académico integral desarrollado en **Java 21** para la administración, control biológico, trazabilidad clínica-nutricional y gestión de la zona de contacto con visitantes en una reserva zoológica.

El proyecto está diseñado bajo una arquitectura limpia y modular, plenamente compatible con **Eclipse IDE for Java Developers** y ejecutable de forma autónoma desde consola mediante herramientas estándar del JDK 21 (sin dependencias externas ni frameworks pesados).

## Índice

- [Objetivo del Proyecto](#1-objetivo-del-proyecto)
- [Requisitos del Entorno](#2-requisitos-del-entorno)
- [Estructura de Paquetes y Responsabilidades](#3-estructura-de-paquetes-y-responsabilidades)
- [Conceptos de Programación Orientada a Objetos](#4-conceptos-de-programación-orientada-a-objetos-aplicados)
- [Reglas de Negocio de la Zona de Contacto](#5-reglas-de-negocio-de-la-zona-de-contacto)
- [Instrucciones de Ejecución](#6-instrucciones-de-ejecución)
- [Demostración y Pruebas Realizadas](#7-demostración-y-pruebas-realizadas)

---

## 1. Objetivo del Proyecto

Modelar computacionalmente la gestión integral del **Zoológico Serengueti**, demostrando la aplicación práctica y rigurosa de los principios fundamentales del paradigma de **Programación Orientada a Objetos (POO)** en Java:
- Abstracción y modelado de dominio.
- Encapsulamiento y validación defensiva de estado.
- Herencia jerárquica para especialización biológica.
- Polimorfismo dinámico en tiempo de ejecución.
- Modularización en paquetes funcionales.
- Manejo estructurado de excepciones de negocio.

---

## 2. Requisitos del Entorno

- **Java Development Kit (JDK)**: Versión 21 o superior (`openjdk 21.0.x`).
- **IDE Recomendado**: Eclipse IDE for Java Developers (2023-12, 2024-03 o posterior).
- **Consola / Terminal**: Bash / Zsh / PowerShell / CMD con `javac` y `java` configurados en el `PATH`.
- **Dependencias**: Ninguna (100% Java Standard Edition).

---

## 3. Estructura de Paquetes y Responsabilidades

El proyecto se distribuye en tres paquetes lógicos bien delimitados dentro de la carpeta `src/`:

```
ZoologicoSerengueti/
├── .classpath                   # Configuración del build path de Eclipse (JavaSE-21)
├── .project                     # Descriptor de proyecto de Eclipse JDT
├── .settings/                   # Preferencias de compilación Java 21
├── bin/                         # Archivos bytecode compilados (.class)
├── src/                         # Código fuente Java
│   └── zoologico/
│       ├── modelo/              # Dominio y entidades del zoológico
│       │   ├── Animal.java              (Clase abstracta base)
│       │   ├── Herbivoro.java           (Clase intermedia abstracta)
│       │   ├── Carnivoro.java           (Clase intermedia abstracta)
│       │   ├── Elefante.java            (Clase concreta herbívora)
│       │   ├── Jirafa.java              (Clase concreta herbívora)
│       │   ├── Cebra.java               (Clase concreta herbívora)
│       │   ├── Leon.java                (Clase concreta carnívora)
│       │   ├── Cuidador.java            (Entidad de personal profesional)
│       │   ├── Sexo.java                (Enumeración biológica)
│       │   └── EstadoSalud.java         (Enumeración de estados clínicos)
│       │
│       ├── gestion/             # Servicios y lógica de negocio
│       │   ├── RegistroAlimentacion.java (Trazabilidad nutricional)
│       │   ├── RegistroSalud.java        (Historial médico veterinario)
│       │   ├── ZonaContacto.java         (Control de acceso y capacidad de 60 herbívoros)
│       │   ├── ZonaContactoException.java(Excepción para reglas de negocio zoológicas)
│       │   └── GestionZoologico.java     (Controlador central del zoológico)
│       │
│       └── principal/           # Punto de entrada
│           └── Main.java                (Demostración paso a paso)
│
└── README.md                    # Documentación técnica y guía de ejecución
```

### Responsabilidad de cada clase

| Clase / Enumeración | Paquete | Responsabilidad Principal |
| :--- | :--- | :--- |
| `Animal` | `zoologico.modelo` | Clase abstracta raíz. Encapsula identificador, nombre, especie, edad, peso, sexo, estado de salud, dieta base y cuidador asignado. Declara métodos abstractos polimórficos. |
| `Herbivoro` | `zoologico.modelo` | Especialización para animales fitófagos. Incorpora el tipo de forraje preferido y la habilitación de contacto. Determina la aptitud para la zona de contacto. |
| `Carnivoro` | `zoologico.modelo` | Especialización para animales carnívoros. Modela el tipo de corte cárnico y nivel de peligrosidad. Bloquea el acceso a la zona de contacto por seguridad. |
| `Elefante` | `zoologico.modelo` | Especie concreta (*Loxodonta africana*). Implementa su dieta específica, trompeteo y medidas morfológicas (trompa y colmillos). |
| `Jirafa` | `zoologico.modelo` | Especie concreta (*Giraffa camelopardalis*). Modela su dieta ramoneadora de copas altas, altura total y longitud de cuello. |
| `Cebra` | `zoologico.modelo` | Especie concreta (*Equus quagga*). Modela su dieta de pastos duros con fermentación en ciego, conteo aproximado de rayas y patrón de camuflaje. |
| `Leon` | `zoologico.modelo` | Especie concreta carnívora (*Panthera leo*). Modela tamaño de melena, liderazgo de manada, rugido y raciones cárnicas con ayuno emulado. |
| `Cuidador` | `zoologico.modelo` | Registra el perfil profesional: identificación, nombre, apellidos, especialidad, años de experiencia y teléfono de urgencia. |
| `Sexo` | `zoologico.modelo` | Enum seguro con valores `MACHO` y `HEMBRA`. |
| `EstadoSalud` | `zoologico.modelo` | Enum con valores `SALUDABLE`, `EN_OBSERVACION`, `EN_TRATAMIENTO`, `ENFERMO`, `CUARENTENA`, indicando compatibilidad médica con la zona de contacto. |
| `RegistroAlimentacion` | `zoologico.gestion` | Ficha inmutable de suministro nutricional: código, fecha/hora, animal, alimento, cantidad en kg y observaciones. |
| `RegistroSalud` | `zoologico.gestion` | Expediente clínico: código, fecha/hora, paciente, veterinario actuante, transición de estado de salud, diagnóstico y tratamiento. |
| `ZonaContacto` | `zoologico.gestion` | Administra la capacidad estricta de 60 animales herbívoros, validando ausencia de carnívoros, conducta, salud saludable, duplicados y aforo. |
| `ZonaContactoException`| `zoologico.gestion` | Excepción verificada que notifica con detalle las transgresiones a las políticas de seguridad de la zona de contacto. |
| `GestionZoologico` | `zoologico.gestion` | Fachada o controlador central que administra las colecciones en memoria, asignaciones, bitácoras y estadísticas globales. |
| `Main` | `zoologico.principal`| Orquestador de la prueba y demostración completa en consola en 9 etapas sucesivas. |

---

## 4. Conceptos de Programación Orientada a Objetos Aplicados

### 4.1. Abstracción y Clases
Se modelaron entidades del mundo real en abstracciones de software con atributos cohesivos y métodos representativos de operaciones zoológicas (atención veterinaria, suministro de alimentos, admisión en recintos).

### 4.2. Encapsulamiento
- Todos los atributos de las clases son estrictamente **privados** (`private`).
- El acceso y modificación se realiza exclusivamente mediante **getters y setters**.
- Los constructores y setters incorporan **validaciones defensivas**:
  - Edad no negativa (`edad >= 0`).
  - Peso estrictamente positivo (`peso > 0`).
  - Cadenas de texto no nulas ni en blanco (`!identificador.isBlank()`).
  - Referencias a cuidadores y animales no nulas.
  - Protección de colecciones internas mediante `Collections.unmodifiableList(...)` para evitar modificaciones externas directas.

### 4.3. Herencia
- Estructura jerárquica de dos niveles:
  - Nivel 1: `Animal` (Superclase abstracta)
  - Nivel 2: `Herbivoro` y `Carnivoro` (Clases intermedias abstractas)
  - Nivel 3: `Elefante`, `Jirafa`, `Cebra` (Heredan de `Herbivoro`) y `Leon` (Hereda de `Carnivoro`).
- Reutilización de código eliminando duplicaciones: atributos comunes (`identificador`, `nombre`, `peso`, `cuidadorAsignado`, etc.) y métodos comunes (`asignarCuidador`, `actualizarEstadoSalud`) se definen una sola vez en `Animal`.

### 4.4. Polimorfismo
- Métodos abstractos en `Animal` forzados a sobreescritura (`@Override`):
  - `describirAlimentacion()`
  - `obtenerInformacionEspecie()`
  - `esAptoParaContacto()`
- Durante la demostración en `Main`, una sola colección `List<Animal>` se recorre de manera uniforme. Cada instancia ejecuta su propia implementación especializada según su tipo dinámico (enlace dinámico o *late binding*).
- Polimorfismo en la admisión a la Zona de Contacto: la condición `esAptoParaContacto()` evalúa en tiempo de ejecución las reglas específicas de cada subclase.

---

## 5. Reglas de Negocio de la Zona de Contacto

La clase `ZonaContacto` gestiona un aforo estricto de **60 animales herbívoros**, garantizando la integridad de los visitantes:

1. **Filtro de Herbívoros**: Solo se aceptan instancias de `Herbivoro`. Cualquier intento de ingresar un carnívoro (como un león) es rechazado inmediatamente.
2. **Habilitación Expresa**: El herbívoro debe tener `habilitadoParaContacto == true` (evaluado por su comportamiento y docilidad).
3. **Condición Sanitaria**: Únicamente pueden ingresar ejemplares con `EstadoSalud.SALUDABLE`. Animales en tratamiento, enfermos o en cuarentena son denegados.
4. **No Duplicidad**: Un animal no puede registrarse dos veces simultáneamente en el recinto.
5. **Capacidad Máxima (60 ejemplares)**: Alcanzado el cupo de 60 animales, el sistema rechaza cualquier solicitud de ingreso adicional por sobrecupo.

---

## 6. Instrucciones de Ejecución

### 6.1. Ejecución desde Eclipse IDE

1. Abra **Eclipse IDE for Java Developers**.
2. Vaya a `File` > `Open Projects from File System...` (o `Import...` > `Existing Projects into Workspace`).
3. Seleccione el directorio raíz del proyecto (la carpeta `ZoologicoSerengueti`).
4. Pulse **Finish**. Eclipse detectará automáticamente el archivo `.project` y `.classpath`.
5. En el *Package Explorer*, despliegue `src` > `zoologico.principal`.
6. Haga clic derecho sobre `Main.java` y seleccione `Run As` > `Java Application`.
7. Observe la salida completa formateada en la vista *Console*.

### 6.2. Ejecución desde Consola / Terminal

Abra una terminal en el directorio del proyecto y ejecute los siguientes comandos:

```bash
# 1. Posicionarse en el directorio del proyecto
cd ZoologicoSerengueti

# 2. Compilar todos los paquetes hacia la carpeta bin/
javac -d bin -sourcepath src src/zoologico/modelo/*.java src/zoologico/gestion/*.java src/zoologico/principal/*.java

# 3. Ejecutar la clase principal
java -cp bin zoologico.principal.Main
```

---

## 7. Demostración y Pruebas Realizadas

La clase `Main.java` ejecuta una secuencia automatizada de verificación en 9 fases:

1. **Registro de Cuidadores**: Se crean 4 cuidadores profesionales con especialidades en grandes herbívoros, rumiantes, équidos y grandes felinos.
2. **Registro de Animales**: Se registran ejemplares iniciales de `Elefante`, `Jirafa`, `Cebra` y `Leon`.
3. **Asignación de Cuidadores**: Se vinculan cuidadores a animales verificando la relación bidireccional.
4. **Registro de Alimentación**: Se asientan suministros nutricionales con cantidades en kg, fecha/hora y notas de ingesta.
5. **Controles Veterinarios**: Se registran revisiones médicas y se simula la transición de estado clínico de una cebra (`SALUDABLE` -> `EN_TRATAMIENTO` -> `SALUDABLE`), comprobando la bioseguridad en cada estado.
6. **Demostración de Polimorfismo**: Se itera sobre una lista heterogénea de `Animal`, invocando `describirAlimentacion()`, `obtenerInformacionEspecie()` y `esAptoParaContacto()`.
7. **Admisión de 60 Herbívoros en Zona de Contacto**: Se genera e incorpora un lote controlado de 20 elefantes, 20 jirafas y 20 cebras, alcanzando el 100% del aforo (60/60).
8. **Pruebas de Rechazo Controlado (Excepciones)**:
   - *Intento A*: Incorporar al León Simba (Carnívoro) -> **Rechazado con `ZonaContactoException`**.
   - *Intento B*: Incorporar un herbívoro duplicado -> **Rechazado con `ZonaContactoException`**.
   - *Intento C*: Incorporar un 61º herbívoro con cupo lleno -> **Rechazado con `ZonaContactoException`**.
   - *Intento D*: Incorporar un animal en cuarentena -> **Rechazado con `ZonaContactoException`**.
9. **Resumen Consolidado**: Emisión de balances generales con métricas institucionales y desglose porcentual de la zona de contacto.
