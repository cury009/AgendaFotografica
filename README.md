# AgendaFotografica
TFG Raul Salva Jimeno -- Aplicacion Android cuya funcion es reservar  sesiones fotograficas en un calendario


Para poder reservar un evento/sesion. Es necesario primeramente registrar/iniciar sesion.

La primera pantalla de la aplicacion será un animacion de carga de varios segundos, seguido se mostrará el logín (Correo y contraseña). Y dos botones
  - El primer botón es de acceso. En ese botón hay un método que comprueba los datos con firebase Authenticacion.
  - El segundo botón es de registrar. Este llevará a una pantalla, donde hay un formulario de usuario. 
    - Correo
    - Contraseña
    - Nombre
    - Apellidos
    - Rol Administrador/Cliente
    - Telefono
  En el backEnd disponemos de un método que recoge correo y contraseña para insertar en firebase Autenticacion. Los demás datos se inserta en mySQL.
  Habrá una validacion de campos vacios. Para obligar al usuario a rellenar los campos.
 
Asi qué hay dos bases de datos. Una en google firebase y la otra de momento servidor local, futuramente en el servidor del instituto IES LAS SALINAS, SESEÑA.

Base de datos local: tabla usuario (correo,nombre,apellidos,rol,telefono) y tabla evento(fecha,descripcion,hora,correo)
  
  
Una vez hecho las validaciones nos dirigimos a la pantalla MainActivity. Aqui sera el home/vestibulo de la aplicacion. 
Aqui podremos ir:
  - apartado de reservar fotos:
    - Aqui es la funcion principal. Donde está ubicado un calendario para seleccionar dia. Y las funciones de cada evento
      - Selecciona el dia -- Es un botón previo antes de clickar en los otros botones. (sirve para pasar el calendario a formato String para poderlo llevar a otros activitys)
      - Mostrar Evento --(filtrará por dias. Mostrará en una lista de eventos de cada dia) 
        -  Borrar Evento --(se mostrará datos del evento y un botón de borrar)
      - Insertar Evento --(un formulario evento. Un boton para  insertar en la tabla evento(fecha,descripcion, hora, correo) y un botón para volver al activity anterior)
      - Volver al Home -- (redirige al home/ vestibulo.)
   - apartado info:
    - Aqui podras ver informacion de la aplicacion. (correo de contacto, un pdf de manual de usuario)
      - Volver al Home -- (redirige al home/ vestibulo.)
   - apartado facebook:
    - redirige a un link de internet. para poder visualizar las fotos del fotografo
   - cerrar sesion:
    - vuelve a la pantalla de inicio. Para hacer el login/registrar.
