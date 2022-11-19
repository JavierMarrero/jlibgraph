# JLibGraph: Una biblioteca integral de grafos y algoritmos

![build-status](https://img.shields.io/badge/build-passing-success)
![version](https://img.shields.io/badge/version-v0.7-orange)

## ¿Qué es JLibGraph?

:small_blue_diamond: JLibGraph es una biblioteca realizada en el lenguaje de programación Java para manejar grafos y realizar operaciones sobre estos. La biblioteca está enfocada a tratar varios problemas comunes en la *teoría de grafos*: la rama de las matemáticas que trata sobre los grafos y sus problemas asociados.

:small_blue_diamond: Un grafo es un conjunto no vacío de vértices y aristas. Las aristas son pares ordenados de la forma (u, v), representando las líneas que conectan dichos vértices, y pueden ser clasificadas en adyacentes, paralelas, cíclicas o cruzadas; mientras que los vértices son los elementos que conforman el grafo y tienen un grado dependiente del número de aristas que inciden sobre el vértice. Un camino es el conjunto de vértices interconectados por aristas. 

### Grafos

La biblioteca aspira a implementar los siguientes tipos de grafos:

- [X] Simple
- [X] Multígrafo 
- [X] Dirigido 
- [X] Etiquetado 
- [ ] Aleatorio 
- [X] Regular 
- [X] Dual 

### Algoritmos

Para dar tratamiento a algunos problemas comunes en teoría de grafos se han implementado los siguientes algoritmos:

- Caminos más cortos
  - [X] Dijkstra
  - [X] Bellman-Ford
  - [X] Floyd-Warshall
  - [X] Dial
- Redes de flujo
  - [X] Ford-Fulkerson
- Ciclos
  - [X] Detección de ciclos
  - [X] Ciclo hamiltoniano
- Árboles de expansión
  - [X] Algoritmo de Prim
  
## Primeros pasos

La biblioteca está programada en Java, y tiene un amplio espectro de algoritmos y utilidades generales para trabajar tanto con grafos como con árboles. El paquete principal es `cu.edu.cujae.graphy.core`. Ahí se encuentran la mayoría de interfaces. La interfaz genérica `Graph<T>` representa cualquier tipo de grafo, mientras que la interfaz `WeightedGraph<T>` extiende a la interfaz `Graph<T>` añadiendo operaciones para trabajar con pesos. La instanciación de los grafos puede realizarse a través de los builders, pero la clase de utilidad `GraphBuilders` proporciona métodos estáticos para construir grafos.
El paquete `cu.edu.cujae.graphy.algorithms` incluye los algoritmos de la biblioteca. Muchos de ellos toman como parámetro un `GraphIterator<T>`. Los iteradores pueden ser secuenciales o aleatorios y son la forma de manipular los datos en la biblioteca. 

> No se exponen los nodos de los grafos (excepto en la biblioteca de árboles)

Actualmente se encuentran soportados los siguientes IDEs:

- NetBeans (el desarrollo principal se efectúa aquí)
- Visual Studio Code
- Eclipse (soporte parcial)
  
## Contribuyendo al proyecto

Para contribuir al proyecto, clone primero el repositorio. En la rama `main` se encuentra el código estable, mientras que la rama activa de desarrollo es `development`. Todos los pull requests con `main` o `development` como destino tendrán que ser aprobados por los revisores. No olvide mirar los issues y las discusiones con frecuencia :wink:. Cualquier cambio al código o sugenrencia debe ser puesto como issue, y las discusiones generales deben ser hechas en... bueno... propiamente... las discusiones.

## Contribuidores

La siguiente lista se encuentra ordenada por orden alfabético. Siéntase libre de incluirse en ella si ha hecho alguna contribución al proyecto, sólo manténgala ordenada, por favor:

- J. García:mango:(@JoseCarlosGarcia)
- J. Marrero:robot:(@JavierMarrero)
- A. Méndez:watermelon:(@Amy-Mendez)
- A. Morales:snowflake:(@SnowBlackQueen)
- C. Robaina:evergreen_tree:(@cdrobaina01)

## Agradecimientos

Queremos agradecer al colectivo de asignatura de Estructura de Datos de la Facultad de Ingeniería Informática de la UTH Jose Antonio Echeverría de La Habana, en especial a la profesora Lisandra Bravo, por la idea original :wink:

## Licencia

El proyecto JLibGraph es software libre y se encuentra licenciado bajo los términos de la GNU Lesser General Public License en su versión 2.1 u opcionalmente versiones posteriores.
