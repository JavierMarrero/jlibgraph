# JLibGraph: Una biblioteca integral de grafos y algoritmos

![build-status](https://img.shields.io/badge/build-passing-success)
![version](https://img.shields.io/badge/version-v0.7-orange)

## ¿Qué es JLibGraph?

:small_blue_diamond: JLibGraph es una biblioteca desarrollada en el lenguaje de programación *Java*. Posee como objetivo fundamental el manejo de grafos y la realización de operaciones sobre estos. Su enfoque se basa en el tratamiento de varios problemas comunes en la *teoría de grafos*: rama de las matemáticas dedicada a los grafos y sus problemáticas asociadas.

:small_blue_diamond: Un grafo es un conjunto no vacío de vértices y aristas. Las aristas son pares ordenados de la forma (u, v), representando las líneas que conectan dichos vértices, y pueden ser clasificadas en adyacentes, paralelas, cíclicas o cruzadas; mientras que los vértices son los elementos que conforman el grafo y tienen un grado dependiente del número de aristas que inciden sobre dicho vértice. Un camino es el conjunto de vértices interconectados por aristas. 

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

- Árboles de expansión
  - [X] Prim
  - [ ] Kruskal
- Caminos más cortos
  - [X] Bellman-Ford
  - [X] Dial
  - [X] Dijkstra
  - [X] Floyd-Warshall
- Centralidad:
  - [X] Katz  
- Ciclos
  - [X] Detección de ciclos
  - [X] Ciclo de Euler
  - [X] Ciclo de Hamilton
- Componentes fuertemente conexos (SCC):
  - [X] Kosaraju
- Comunidades:
  - [X] Girvan-Newman
- NP de m-números cromáticos:
  - [X] Colorable
  - [X] Greedy
- Redes de flujo
  - [X] Ford-Fulkerson
  
## Primeros pasos

La biblioteca está programada en *Java*, y contiene un amplio espectro de algoritmos y utilidades generales para trabajar con grafos y árboles. El paquete principal es `cu.edu.cujae.graphy.core`. Ahí se encuentran la mayoría de interfaces. La interfaz genérica `Graph<T>` representa cualquier tipo de grafo, mientras que la interfaz `WeightedGraph<T>` extiende a la interfaz `Graph<T>` añadiendo operaciones para el trabajo con pesos. La instanciación de los grafos puede realizarse a través de los *builders*, pero la clase de utilidad `GraphBuilders` proporciona métodos estáticos para construir grafos.
El paquete `cu.edu.cujae.graphy.algorithms` incluye los algoritmos de la biblioteca. Muchos de ellos toman como parámetro un `GraphIterator<T>`. Los iteradores pueden ser secuenciales o aleatorios y constituyen la vía de manipulación de los datos en la biblioteca. 

> No se exponen los nodos de los grafos (excepto en la biblioteca de árboles).

Actualmente se encuentran soportados los siguientes IDEs:

- NetBeans (el desarrollo principal se efectúa aquí)
- Visual Studio Code
- Eclipse (soporte parcial)

### Pequeño ejemplo

#### Grafos simples

```java

// La interfaz Graph es genérica y puede contener cualquier objeto
// El boolean de makeSimpleGraph determina si el grafo construído es dirigido o no

Graph<Object> graph = GraphBuilders.makeSimpleGraph(false);

```
  
#### Grafos con pesos

```java

// La interfaz WeightedGraph<?> extiende a Graph<?>
// El boolean cumple la misma función en todos los métodos de GraphBuilders

WeightedGraph<Object> weightedGraph = GraphBuilders.makeSimpleWeightedGraph(false);

```

### Iteradores

> Los iteradores pueden ser aleatorios, a lo ancho o en profundidad

#### Iterador aleatorio

```java

Graph<Object> graph = GraphBuilders.makeSimpleGraph(false);
GraphIterator<Object> randomIterator = graph.randomIterator();

// o también un parámetro entero que representa el identificador del nodo en el grafo

randomIterator = graph.iterator(0);

```

#### Iterador en profundidad

```java

GraphIterator<Object> depthFirstSearchIterator = graph.depthFirstSearchIterator();

```

#### Iterador a lo ancho

```java

GraphIterator<Object> breadthFirstSearchIterator = graph.breadthFirstSearchIterator();

```
  
## Contribuyendo al proyecto

Para contribuir al proyecto, debe clonar primero el repositorio. En la rama `main` se encuentra el código estable, mientras que la rama activa de desarrollo es `development`. Todos los pull requests con `main` o `development` como destino tendrán que ser aprobados por los revisores. No olvide mirar los *issues* y las discusiones con frecuencia :wink:. Cualquier cambio al código o sugerencia debe ser puesto como *issue*, y las discusiones generales en su respectiva pestaña.

## Contribuidores

La siguiente lista se encuentra ordenada alfabéticamente. Siéntase libre de incluirse en ella si ha hecho alguna contribución al proyecto, sólo manténgala ordenada, por favor:

- J. García:mango:(@JoseCarlosGarcia)
- J. Marrero:robot:(@JavierMarrero)
- A. Méndez:watermelon:(@Amy-Mendez)
- A. Morales:snowflake:(@SnowBlackQueen)
- C. Robaina:evergreen_tree:(@cdrobaina01)

## Agradecimientos

Por esta vía se realiza un agradecimiento especial al colectivo de la asignatura *Estructura de Datos* de la Facultad de Ingeniería Informática, de la Universidad Tecnológica de La Habana "José Antonio Echeverría" (CUJAE), específicamente a la profesora *Dr. C. Lisandra Bravo Ilisástigui* (@Lisibravo), por la idea original :yellow_heart:.

## Licencia

El proyecto *JLibGraph* es software libre y se encuentra licenciado bajo los términos de la GNU Lesser General Public License en su versión 2.1 u opcionalmente versiones posteriores.
