# API

## 1. Recurso CURSOS

### 1.1. Modelo de datos (ya definido)

```json
🧱 Tabla: cursos
	•	id        BIGSERIAL      (PK)
	•	titulo    VARCHAR(255)   NOT NULL
	•	categoria VARCHAR(100)   NOT NULL
	•	duracion  INTEGER        NULL      
	•	precio    NUMERIC(10, 2) NULL
```

### 1.2. Listar cursos

**Endpoint:**

```
GET /cursos
```

**Requisitos:**

- Devuelve un listado de cursos usando el DTO CursoResumenResponse.
- La lista debe venir de base de datos (no datos “a mano”).
- Debe permitir **ordenar** por los campos:
    - id, titulo, categoria, duracionEnMinutos, precio
- La dirección de ordenación será:
    - asc (ascendente) o desc (descendente).

**Parámetros de consulta (query params):**

| **Parámetro** | **Obligatorio** | **Valores permitidos** | **Valor por defecto** |
| --- | --- | --- | --- |
| ordenarPor | No | id, titulo, categoria, duracionEnMinutos, precio | titulo |
| direccion | No | asc, desc | asc |
- Si llega un valor **no permitido** en ordenarPor o direccion, puedes:
    - o bien usar los valores por defecto,
    - o manejarlo de forma controlada (pero nunca dejar que la app “reviente”).

**Ejemplos de URL:**

- GET /cursos
- GET /cursos?ordenarPor=categoria&direccion=desc

**Ejemplo de respuesta (200 OK):**

```json
[
  {
    "id": 1,
    "titulo": "Introducción a la Programación con Java",
    "categoria": "Programación"
  },
  {
    "id": 2,
    "titulo": "Desarrollo Web con HTML y CSS",
    "categoria": "Desarrollo Web"
  }
]
```

---

### 1.3. Detalle de un curso

**Endpoint:**

```
GET /cursos/{id}
```

**Requisitos:**

- Devuelve el detalle de un curso usando `CursoResponse`.
- El id del curso se recibe en la URL.
- Si el curso no se encuentra:
    - Debes lanzar `CursoNoEncontradoException`.
    - El @RestControllerAdvice debe devolver **HTTP 404 NOT_FOUND** con un cuerpo de error razonable.

**Transformaciones en la respuesta:**

- duracion_en_minutos:
    - En el JSON se debe llamar así (duracion_en_minutos), aunque en Java se llame de otra forma.
- precio:
    - Se devuelve como **String**, añadiendo el símbolo "€" al valor numérico.
    - Ejemplo: si en BBDD el precio es 35.0, en la respuesta debe aparecer:

```
"precio": "35.0 €"
```

**Ejemplo de respuesta (200 OK):**

```json
{
  "id": 1,
  "titulo": "Desarrollo Web Full Stack con React y Node.js",
  "categoria": "Desarrollo Web",
  "duracion_en_minutos": 5400,
	  "alumnos_matriculados": 42,
  "precio": "299.00 €"
}
```

<aside>
🚨

El campo **alumnos_matriculados** se podrá calcular más adelante cuando tengas el recurso de Alumnos implementado. Si no llegas, puedes devolver temporalmente null o 0, pero es recomendable dejarlo preparado.

</aside>

---

### 1.4. Crear un curso

**Endpoint:**

```json
POST /cursos
```

**Body (JSON de entrada):**

```json
{
  "titulo": "Introducción a la Programación con Java",
  "categoria": "Programación",
  "duracion_en_minutos": 2400,
  "precio": 199.0
}
```

**Validaciones de CursoRequest**

Estas validaciones deben estar implementadas con **Jakarta Bean Validation** en el DTO de entrada:

| **Campo** | **Obligatorio** | **Restricciones** |
| --- | --- | --- |
| titulo | Sí | No vacío. Longitud máxima 255 caracteres. |
| categoria | Sí | No vacía. Longitud máxima 100 caracteres. |
| duracion_en_minutos | No | Si viene informado: como mínimo 30, como máximo 6000 |
| precio | No | Si viene informado: no puede ser negativo. |

<aside>
👀

Ojo al nombre JSON: el atributo del body se llama `duracion_en_minutos` y en Java usarás probablemente `duracionEnMinutos`. Tendrás que usar las anotaciones de Jackson para mapearlo correctamente.

</aside>

**Restricción extra de negocio**

- No puede haber dos cursos con el **mismo título**.
    - Si ya existe un curso con ese título al crear:
        - Debes lanzar `CursoYaExisteException`.
        - En el @RestControllerAdvice devolver **HTTP 409 CONFLICT**.

**Respuesta**

- Debe tener el mismo formato que “Detalle de un curso” (`CursoResponse`).
- Código HTTP: **201 CREATED**.

---

### 1.5. Actualizar un curso

**Endpoint:**

```
PUT /cursos/{id}
```

- Usa el mismo JSON de entrada y las mismas validaciones que en POST /cursos.
- Si el curso no existe:
    - `CursoNoEncontradoException` → 404.
- Si ya existe **otro** curso con el mismo título:
    - `CursoYaExisteException` → 409 (igual que en POST).
- Respuesta: formato `CursoResponse`.

### **1.6. Eliminar un curso**

**Endpoint:**

```
DELETE /cursos/{id}
```

- El id se recibe en la URL.
- Si el curso no existe:
    - `CursoNoEncontradoException` → 404.
- Si se elimina correctamente:
    - Código HTTP **204 NO_CONTENT**, sin cuerpo.

---

## 2. Recurso ALUMNOS

En esta parte debes **diseñar e implementar todas las capas** para el recurso Alumnos:

- Entidad JPA.
- Repositorio.
- DTOs (entrada y salida).
- Servicio.
- Controlador.

### 2.1. Modelo de datos (ya definido)

En base de datos tienes la tabla:

```
Tabla: alumnos

- id         BIGSERIAL   (PK)
- nombre     VARCHAR(100)   NOT NULL
- apellidos  VARCHAR(150)   NOT NULL
- pais       VARCHAR(50)    NOT NULL
- edad       INTEGER        NULL
- curso_id   BIGINT         NOT NULL (FK → cursos.id)
```

<aside>
🚨

**Ayuda** En Java deberás crear algo como AlumnoEntity, con una relación hacia CursoEntity (@ManyToOne o similar).

</aside>

---

### 2.2. Listado de alumnos de un curso

**Endpoint recomendado:**

```
GET /cursos/{cursoId}/alumnos
```

**Requisitos:**

- Devuelve el listado de alumnos matriculados en el curso cursoId.
- Si el curso no existe:
    - `CursoNoEncontradoException` → 404.

**Transformaciones en la respuesta:**

- nombre_completo:
    - Debe contener nombre + " " + apellidos.

**Ejemplo de respuesta (200 OK):**

```json
[
  {
    "id": 1,
    "nombre_completo": "Manuel García López",
    "pais": "España",
    "edad": 25
  },
  {
    "id": 2,
    "nombre_completo": "Mary O'Donell",
    "pais": "Irlanda",
    "edad": 38
  }
]
```

---

### 2.3. Matricular (Crear) un alumno en un curso

**Endpoint recomendado:**

```
POST /cursos/{cursoId}/alumnos
```

**Requisitos:**

- El cursoId se recibe en la URL.
- El cuerpo de la petición contiene solo datos del alumno:
    - **No** debe incluir el curso_id, ya que el curso se toma de la URL.

**Ejemplo de petición:**

```json
{
  "nombre": "Tomás",
  "apellidos": "Pérez Gil",
  "pais": "España",
  "edad": 39
}
```

**Validaciones de alumnos**

Estas validaciones deben estar en el DTO de entrada y usar Bean Validation:

| **Campo** | **Obligatorio** | **Restricciones** |
| --- | --- | --- |
| nombre | Sí | No vacío. Máximo 100 caracteres. |
| apellidos | Sí | No vacío. Máximo 150 caracteres. |
| pais | Sí | No vacío. Máximo 50 caracteres. |
| edad | No | Si viene, debe estar entre 1 y 99 (inclusive). |
- Si el curso no existe:
    - `CursoNoEncontradoException` → 404.

**Ejemplo de respuesta (201 CREATED):**

```json
{
  "id": 17,
  "nombre_completo": "Vicente Sánchez Maroto",
  "pais": "Ecuador",
  "edad": 43
}
```

Puedes reutilizar el mismo DTO de respuesta que en “Listado alumnos de un curso”.
