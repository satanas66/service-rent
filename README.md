# Desafío JAVA"

## Poyecto service-rent"

### Alquiler de coches

El siguiente proyecto ha sido realizado siguiendo los requerimientos del documento Java developer challenge.pdf que se encuentra localizado en el directorio de recursos.

### Framework

Las tecnologías más relevantes para el desarrollo del proyecto service-rent son:

* IDE Intellij Ultimate 2020.3
* Spring boot 2.7.5
* JDK 11
* JPA 2.2.3
* H2 
* Spring WEB 5.3.23
* Lombok 1.18.24
* JUnit 5.8.2
* Mockito 4.5.1

### Architecture 

El patrón de diseño de software es el de "Arquitecturas limpias" en el cual se modulariza y separa claramente el negocio/dominio de la insfraestructura/tecnologia.

En este tipo de arquitectura hexagonal se cumplen las reglas de dependencia de fuera hacia dentro:

* INFRAESTRUCTURE => APPLICATION => DOMAIN 

## Analysis, development an tests 

### Business entities

Después de un breve análisis de los requerimientos he concluido que necesito tres entes de negocio.

* Car: Representa a los coches para alquilar.
* CustomerLoyalty: Representa las bonificaciones en puntos que tiene un cliente.
* Rent: Representa tanto alquiler como a la devolución de un coche.

Estos entes de negocio los he modelado en Java y los he creado mediante un script sql lanzado en tiempo de ejecución en base de datos H2

### Use cases

* Listar todos los coches
* Listar las bonificaciones para los clientes
* Alquilar uno o varios coches
* Devolución de coche o coches

### Modules

Siguiendo la arquitectura hexagonal el proyecto está modularizado en tres niveles que únicamente se comunican de fuera hacia dentro.

En cada múdulo se definen, programan y desarrollan todas aquellas funcionalidades para cumplir con los requisitos del documento Java developer challenge.pdf

Para facilitar la cohesión entre los entes de negocio por cada uno se genera una estructura de carpetas que contendra infraestructure, application y domain.

#### Domain 

En esta carpeta se detallan las entidades de negocio e interfaces
 
En cuanto a las Interfaces:

* Interfaz de repositorio: Se refiere a la interfaz JPARepository
* Interfaz de repositorio extendida: La uso para implementar métodos mediantes API-Criteria que no me proporciona la interfaz JpaRepository
* Interfaz de servicio: Se refiere a la interfaz de casos de uso

#### Application

En esta carpeta se implementan los casos de uso definidos en las interfaces

#### Infraestructure

En esta carpeta se implementan las clases que publicaran los servicios que serán consumidos a posteriori

### Services 

Previa cosumisión de los servicios se debe arrancar el proyecto cuyo núcleo es la clase "ServiceRentApplication"

Una vez arrancado el proyecto y mediante la herramienta "POSTMAN" se podrán consumir los servicios.

#### Customer loyalty (GET)

Este servicio se publicará en la siguiente url: http://localhost:8091/customerloyalties

#### Car Inventory (GET)

Este servicio se publicará en la siguiente url: http://localhost:8091/cars

#### Rents (GET)

Para consultar los alquileres actuales se deberá consultar mediante la siguiente url: http://localhost:8091/rents/

#### Car Rent (POST)

Este servicio se publicará en la siguiente url: http://localhost:8091/rents/create

Para el alquiler de un coche es necesario proveer al servicio de la siguiente información, por ejemplo:

* fecha_inicio con formato dd/MM/yyyy
* fecha_fin con formato dd/MM/yyyy
* tipo_coche puede ser premium, suv o small
* nombre del que alquila
* dni del que alquila
* cantidad debe ser un valor entero

Cada vez que se realiza un alquiler se actualiza el stock y las bonificaciones.

#### Car Devolution (GET)

Este servicio se publicará en la siguiente url: http://localhost:8091/rents/devolution/

Para realizar una devolución es necesario indicar el identificador del alquiler realizado (Consultar los alquileres actuales).

Cada vez que se realiza una devolución se actualiza el stock

### Test

Se han realizado un total de 9 test entre unitarios y de integración para la mayoría de funcionalidades realizadas. 

### Notes

Al tratarse de una prueba de habilidades de análisis, diseño, estructura y desarrollo/pruebas donde el único input es un documento de requisitos, este proyecto tiene muchas carencias que espero lo entiendan.

* No se han realizado test para todos los casos posibles
* No se han realizado control ni lanzamiento de excepciones
* No se han realizado validaciones en el caso POST
* Hay más servicios disponibles que pueden ser consultados directamente en el código.

## Author Computer Enginner
* Edwin Patricio Arévalo Angulo
* edwinarevaloangulo@gmail.com


