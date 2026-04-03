# Sistema Administrador de Usuarios

Un sistema de escritorio completo (CRUD) desarrollado en **Java**, con interfaz gráfica en **Swing** y conexión a base de datos **MySQL** utilizando **JPA (Hibernate)**. 

Este proyecto implementa una arquitectura multicapas estricta (GUI, Lógica y Persistencia) y cuenta con un sistema de autenticación basado en roles (Admin/User).



## Aclaracion
**Aclaración importante:** Este es un proyecto desarrollado con fines academicos y de aprendizaje, enfocado en dominar la arquitectura en capas, el manejo de interfaces con Java Swing y el mapeo objeto-relacional con JPA. 
Por este motivo, las contraseñas se almacenan en texto plano directamente en la base de datos para facilitar las pruebas de desarrollo. 

* **Autenticación (Login):** Validación de credenciales contra la base de datos y redirección dinámica según el rol del usuario.
* **Control de Acceso Basado en Roles:**
  * **Administrador:** Acceso total al sistema (Crear, Leer, Editar y Eliminar usuarios).
  * **Usuario Estándar:** Acceso de solo lectura para visualizar el panel principal.
* **Gestión de Usuarios (CRUD Completo):**
  * **Alta:** Creación de nuevos usuarios asignando roles dinámicos extraídos de la BD.
  * **Baja:** Eliminación de registros con ventanas modales de confirmación (JOptionPane) para prevenir borrados accidentales.
  * **Modificación:** Edición de datos existentes recuperando la información del usuario y aplicando em.merge().
  * **Lectura:** Visualización de registros en tiempo real mediante JTable y DefaultTableModel.
* **Manejo de Transacciones:** Uso de EntityManager para garantizar la integridad de los datos.

  

## Tecnologías y herramientas usadas

* **Lenguaje:** Java
* **Interfaz Gráfica:** Java Swing
* **ORM (Mapeo Objeto-Relacional):** JPA 2.2 / Hibernate
* **Base de Datos:** MySQL 8
* **Control de Versiones:** Git / GitHub

  

## Arquitectura del Proyecto

El código está ordenado para que sea fácil de leer, mantener y modificar. Cada parte del sistema se ocupa de una sola tarea. Para esto, el proyecto está dividido en tres áreas principales:

* Gui: Contiene todas las vistas (JFrame, JPanel) y la captura de eventos (ActionListeners). No tiene acceso directo a la base de datos.
* Logica: Actúa como el "Cerebro" del sistema. Contiene las entidades (Clases User y Rol) y la clase Controladora que procesa las reglas de negocio.
* Persistencia: Se encarga exclusivamente de la comunicación con MySQL. Utiliza EntityManagerFactory para realizar las consultas nativas y transacciones.

  

## Estructura de la Base de Datos

El sistema genera automáticamente el esquema de la base de datos gracias a la propiedad hibernate.hbm2ddl.auto = update en el archivo persistence.xml. Cuenta con relaciones bidireccionales (@OneToMany y @ManyToOne):

1. **Tabla User**: id (PK, Auto-Incremental), nameUser, password, fk_rol (FK).
2. **Tabla Rol**: id (PK, Auto-Incremental), nombreRol, descripcion.

   

## Configuración e Instalación

Para clonar y ejecutar este proyecto en tu entorno local:

1. **Clonar el repositorio:**

   git clone [https://github.com/WillyDev/administrador-usuarios-java.git](https://github.com/WillyDev/administrador-usuarios-java.git)
