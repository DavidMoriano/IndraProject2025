# Portal de eventos de desarrollo sostenible
Encriptar contraseñas
Niveles (organizador, usuario)

## CASOS DE USO
    - Usuario
    EL usuario tendrá que registrarse en el portal.
    El usuario podra apuntarse a los eventos y cancerlar si quiere.
    El usuario podrá ver todos los eventos en los que está apuntado.

    - Organizador 
    Hacer una lista con los nombres de los usuarios para cada evento.
    Un organizador podrá crear un nuevo evento (¿Categoría?).
    Un organizador tendrá una lista de todos los eventos creados.
    Un organizador podrá cancelar un evento si no se realiza o modificar uno ya existente.


    - Ambos
    Tanto el usuario como el organizador podrá buscar eventos por categorias.
    Tanto el usuario como el organizador tendrán un perfil con sus datos personales.

## MODELO 
### EVENTOS
#### Propiedades
    Categorias categorias (Conferencias, Talleres o Actividades relacionadas con la sostenibilidad).
    String name
    String fecha
    Ubicacion ubicación

### USUARIOS
#### Propiedades
    String nombres 
    String email
    String contraseña

### ORGANIZADORES
#### Propiedades
    String tipo
    String nombre 
    String informacion_contacto

### CATEGORÍAS
#### Propiedades
    String nombre

### INSCRIPCIÓN
#### Propiedades
    int identidadUsuario
    int identidadEvento
    String fechaInscripcion
    
### UBICACION
#### Propiedades
    String Tipo: Online / Presencial
    String Direccion: (Si es presencial, obligatoria)

