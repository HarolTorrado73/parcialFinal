# Informe Técnico: Análisis de Diagramas UML del Sistema Supermercado Virtual

**Fecha:** 13 de Junio de 2025
**Autor(es):** Daniel Leandro Ascanio, Shary Torcoroma Jimenez, Angie Daniela Ramirez, Harol Camilo Melo

---

## 1. Introducción

El presente informe técnico tiene como finalidad analizar y describir la arquitectura y el comportamiento del sistema "Supermercado Virtual" a través de sus diagramas UML. Estos diagramas son herramientas fundamentales para comprender la estructura estática (Diagrama de Clases) y el comportamiento dinámico (Diagrama de Actividad) de la aplicación, facilitando así su desarrollo, mantenimiento y futura expansión.

El Sistema Supermercado Virtual es una aplicación de consola desarrollada en Java que simula las operaciones básicas de compra y gestión de inventario en un entorno de supermercado.

## 2. Objetivo

El objetivo principal de este informe es proporcionar una comprensión detallada y clara de los siguientes aspectos del Sistema Supermercado Virtual:

*   **Flujo de Usuario:** Cómo interactúa el usuario con la aplicación, desde la selección de opciones hasta la realización de una compra.
*   **Estructura del Sistema:** La organización de las clases principales, sus atributos, métodos y las relaciones que existen entre ellas, reflejando el diseño orientado a objetos.

## 3. Análisis de Diagramas

### 3.1. Diagrama de Actividad: Flujo de Usuario

El Diagrama de Actividad ilustra el flujo de control y las decisiones que un usuario puede tomar al interactuar con el Sistema Supermercado Virtual. Representa las actividades principales y cómo se enlazan para formar un proceso completo de compra. Este diagrama comienza con el nodo inicial (círculo negro) y finaliza con el nodo final (círculo negro con borde).

**Descripción de las Actividades y Transiciones:**

*   **Inicio del Flujo:** El sistema se inicia y la primera actividad es "Mostrar Menú", que presenta al usuario las opciones disponibles.

*   **Opciones del Menú (Decisiones del Usuario):**
    *   **Ver Inventario:** Al seleccionar esta opción, el sistema realiza la actividad "Mostrar Productos" y, una vez completada, el flujo retorna a la actividad "Volver al Menú".
    *   **Agregar al Carrito:**
        1.  El usuario selecciona un producto mediante la actividad "Seleccionar Producto".
        2.  El sistema realiza una actividad de decisión: "Verificar Stock".
            *   Si el producto está **Disponible**, se procede a la actividad "Agregar al Carrito" (añadiendo el producto al carrito de compras).
            *   Si el producto **No Disponible**, se ejecuta la actividad "Mostrar Error" (informando al usuario sobre la falta de stock).
        3.  Ambos caminos (Disponible y No Disponible) se unen y el flujo regresa a la actividad "Volver al Menú".
    *   **Ver Carrito:** Al seleccionar esta opción, el sistema realiza la actividad "Mostrar Contenido" del carrito y luego regresa a "Volver al Menú".
    *   **Realizar Compra:** Este es un proceso secuencial:
        1.  "Confirmar Compra": El usuario valida su intención de comprar.
        2.  "Actualizar Inventario": El stock de los productos comprados se reduce en el inventario general.
        3.  "Vaciar Carrito": El carrito del usuario se vacía para una nueva sesión de compra.
        4.  Una vez completado, el flujo retorna a "Volver al Menú".
    *   **Salir:** Esta opción finaliza el flujo del programa, llevando directamente al nodo final del diagrama.

**Punto de Unión y Retorno al Menú:** Un nodo de unión (un diamante) indica el punto donde convergen los flujos después de la verificación de stock. La actividad "Volver al Menú" es un punto clave que garantiza que el usuario pueda seguir operando el sistema hasta que decida salir explícitamente.

### 3.2. Diagrama de Clases: Estructura del Sistema

El Diagrama de Clases representa la estructura estática del Sistema Supermercado Virtual, mostrando las clases (`Supermercado`, `Carrito`, `Producto`), sus atributos (propiedades), métodos (comportamientos) y las relaciones (asociaciones, composiciones) entre ellas. Este diagrama es fundamental para entender la modularidad y la organización del código.

**Clases Principales y sus Componentes:**

*   **`Supermercado` (Clase Principal)**
    *   **Atributos:**
        *   `- inventario: List<Producto>`: Colección de productos disponibles para la venta (privado).
        *   `- carrito: Carrito`: Instancia del carrito de compras del usuario (privado).
        *   `- scanner: Scanner`: Objeto para la entrada de datos del usuario (privado).
    *   **Métodos:**
        *   `+ Supermercado()`: Constructor que inicializa el inventario, el carrito y el scanner.
        *   `+ iniciar(): void`: Método público para iniciar la aplicación y el bucle del menú.
        *   `- mostrarMenu(): void`: Muestra las opciones principales del menú.
        *   `- obtenerOpcion(): int`: Lee y valida la opción del usuario.
        *   `- mostrarInventario(): void`: Muestra los productos disponibles.
        *   `- agregarAlCarrito(): void`: Maneja la lógica para añadir productos al carrito.
        *   `- realizarCompra(): void`: Procesa la transacción de compra.

*   **`Carrito` (Clase de Gestión de Compras)**
    *   **Atributos:**
        *   `- productos: List<Producto>`: Colección de productos que el usuario ha añadido al carrito (privado).
        *   `- total: double`: Suma total de los precios de los productos en el carrito (privado).
    *   **Métodos:**
        *   `+ Carrito()`: Constructor de la clase.
        *   `+ agregarProducto(producto: Producto): void`: Añade un producto al carrito, actualizando el total y el stock del producto.
        *   `+ mostrarCarrito(): void`: Muestra el contenido actual del carrito.
        *   `+ vaciarCarrito(): void`: Limpia el carrito y restaura el stock de los productos.
        *   `+ getTotal(): double`: Retorna el valor total de la compra.

*   **`Producto` (Clase de Modelado de Datos)**
    *   **Atributos:**
        *   `- nombre: String`: Nombre del producto (privado).
        *   `- precio: double`: Precio unitario del producto (privado).
        *   `- stock: int`: Cantidad disponible en el inventario (privado).
        *   `- categoria: String`: Categoría a la que pertenece el producto (privado).
    *   **Métodos:**
        *   `+ Producto(nombre, precio, stock, categoria)`: Constructor para crear instancias de productos.
        *   `+ getNombre(): String`, `+ getPrecio(): double`, `+ getStock(): int`, `+ getCategoria(): String`: Métodos públicos para acceder a los atributos.
        *   `+ setStock(stock: int): void`: Método público para modificar el stock del producto.
        *   `+ toString(): String`: Representación en cadena del objeto Producto para una visualización amigable.

**Relaciones entre Clases (Composiciones):**

*   **`Supermercado` y `Carrito` (Composición 1 a 1):**
    *   Representada por una línea con un diamante relleno en el lado de `Supermercado` y una flecha al lado de `Carrito`. Esto indica que una instancia de `Supermercado` "contiene" una única instancia de `Carrito` y que el `Carrito` no puede existir sin el `Supermercado`.

*   **`Supermercado` y `Producto` (Composición 1 a muchos):**
    *   Similar a la anterior, con un diamante relleno en `Supermercado` y una flecha hacia `Producto` con una cardinalidad de `*` (muchos). Esto significa que `Supermercado` gestiona múltiples instancias de `Producto` (su inventario), y estos productos son parte integral del supermercado.

*   **`Carrito` y `Producto` (Composición 1 a muchos):**
    *   Con un diamante relleno en `Carrito` y una flecha hacia `Producto` con `*`. Esto denota que una instancia de `Carrito` "contiene" múltiples instancias de `Producto` (los artículos que el cliente ha seleccionado para comprar).

## 4. Conclusiones

Los diagramas UML analizados revelan un diseño de software coherente y bien estructurado para el Sistema Supermercado Virtual. El uso de la Programación Orientada a Objetos se manifiesta en la clara separación de responsabilidades entre las clases `Supermercado`, `Carrito` y `Producto`, lo que contribuye a la modularidad y facilita el mantenimiento del código.

El Diagrama de Actividad proporciona una visión clara de la interacción del usuario, lo que es crucial para entender la usabilidad. Por otro lado, el Diagrama de Clases establece una base robusta para la implementación, con relaciones bien definidas que aseguran la integridad y el flujo de datos dentro del sistema.

En resumen, el diseño presentado es robusto, escalable y fácilmente comprensible, demostrando una aplicación sólida de los principios de diseño de software. 