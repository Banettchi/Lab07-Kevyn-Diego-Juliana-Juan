# TechCup-Lab07-Kevyn-Diego-Juliana-Juan


## Descripción
API REST para la gestión del torneo TechCup, desarrollada con Spring Boot.
Equipo: Kevyn Daniel Forero, Diego Alejandro Montes, Maria Juliana Rodríguez, Juan Angel Salas.

## Laboratorio 6 - TDD y Diseño

### Objetivo
Aplicar TDD como fundamento de estructuración técnica del proyecto de software.

### Diagrama de Clases
El proyecto cuenta con las siguientes clases principales:
- **User**: Representa a un usuario del sistema con rol, correo y contraseña.
- **Tournament**: Representa un torneo con estados: DRAFT, ACTIVE, IN_PROGRESS, FINISHED.
- **Team**: Equipo de jugadores dentro de un torneo.
- **Match**: Partido entre dos equipos dentro de un torneo.
- **Payment**: Pago asociado a un equipo para participar en un torneo.

### Pruebas TDD implementadas
Se implementaron pruebas unitarias para los siguientes casos:

**Usuarios:**
- Registro de usuario con rol por defecto PLAYER.
- Autenticación con credenciales válidas e inválidas.

**Torneos:**
- Creación de torneo en estado DRAFT por defecto.
- No modificar torneo en estado FINISHED.
- Eliminar torneo solo en estado DRAFT.