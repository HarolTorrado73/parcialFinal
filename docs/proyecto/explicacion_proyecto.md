# Exposición del Proyecto: Sistema de Supermercado Virtual

## Equipo de Presentación

### Presentadores y Roles

1. **Arquitecto del Sistema** - Daniel Leandro Ascanio (192468)
   - Responsable de la introducción y visión general
   - Explicará el problema y la solución general
   - Como Diseñador y Analista, presentará la arquitectura del sistema

2. **Modelador de Datos** - Shary Torcoroma Jimenez (192467)
   - Presentará la clase `Producto.java`
   - Explicará el modelado de datos y la gestión del stock
   - Como Analista y Documentadora, detallará la estructura de datos

3. **Gestor de Compras** - Angie Daniela Ramirez (192491)
   - Presentará la clase `Carrito.java`
   - Explicará la lógica de compras y gestión del carrito
   - Como Desarrolladora, mostrará la implementación del carrito

4. **Coordinador del Supermercado** - Harol Camilo Melo (192471)
   - Presentará la clase `Supermercado.java`
   - Explicará la interacción entre componentes
   - Como Desarrollador y tester, demostrará el funcionamiento del sistema

### Diagrama UML
Para una mejor comprensión de la arquitectura del sistema, hemos incluido un diagrama UML detallado en el archivo `diagrama_uml.md`. Este diagrama muestra las relaciones entre las clases y sus principales atributos y métodos.

---

## Introducción y Visión General (El Arquitecto del Sistema)

¡Hola a todos! Hoy les presentaremos nuestro proyecto de un **Sistema de Supermercado Virtual** implementado en Java. Nuestro objetivo fue simular las operaciones esenciales de un supermercado en un entorno de consola, resolviendo desafíos clave de gestión de inventario y compras.

### El Problema que Abordamos:

Imaginemos un pequeño negocio que necesita un sistema para:
*   Manejar su inventario de productos.
*   Permitir a los clientes agregar artículos a un carrito de compras.
*   Calcular el total a pagar de forma automática.
*   Ofrecer una experiencia de compra sencilla y funcional.

### Nuestra Solución: Un Diseño Orientado a Objetos

Hemos abordado este problema con un diseño modular y estructurado en **Programación Orientada a Objetos (POO)**. Creamos tres pilares fundamentales que trabajan en conjunto, cada uno con una responsabilidad clara:

*   **`Producto.java`**: Modela los artículos que vendemos.
*   **`Carrito.java`**: Gestiona las compras del cliente.
*   **`Supermercado.java`**: Orquesta toda la experiencia de usuario y la lógica principal.

---

## Rol 1: El Modelador de Datos (`Producto.java`)

Este rol es fundamental, ya que define qué es un producto en nuestro sistema. Es como el **catálogo** del supermercado.

### Responsabilidades Clave:
*   Definir las características de cada producto: nombre, precio, stock y categoría.
*   Permitir acceder y modificar el stock de los productos.

### Fragmento de Código Clave:

```java
// src/Producto.java
public class Producto {
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;

    public Producto(String nombre, double precio, int stock, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public String getCategoria() { return categoria; }

    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return String.format("%s - $%.2f (Stock: %d) - %s", nombre, precio, stock, categoria);
    }
}
```

---

## Rol 2: El Gestor de Compras (`Carrito.java`)

Este componente actúa como el **asistente personal de compras** del cliente. Se encarga de todo lo relacionado con el carrito, desde agregar productos hasta calcular el total.

### Responsabilidades Clave:
*   Añadir productos al carrito, verificando el stock disponible.
*   Mostrar el contenido actual del carrito y el total.
*   Vaciar el carrito y restaurar el stock de los productos si es necesario.

### Fragmento de Código Clave:

```java
// src/Carrito.java
// ... existing code ...
    public void agregarProducto(Producto producto) {
        if (producto == null) {
            System.out.println("❌ Error: Producto no válido");
            return;
        }
        
        if (producto.getStock() > 0) {
            productos.add(producto);
            total += producto.getPrecio();
            producto.setStock(producto.getStock() - 1); // Decrementa el stock del producto
            System.out.println("✨ ¡" + producto.getNombre() + " agregado al carrito!");
        } else {
            System.out.println("❌ Lo siento, no hay stock disponible de " + producto.getNombre());
        }
    }

    public void mostrarCarrito() {
        if (productos.isEmpty()) {
            System.out.println("🛒 Tu carrito está vacío");
            return;
        }

        System.out.println("\n🛒 TU CARRITO DE COMPRAS 🛒");
        System.out.println("------------------------");
        for (Producto p : productos) {
            System.out.println(p.getNombre() + " - $" + p.getPrecio());
        }
        System.out.println("------------------------");
        System.out.println("Total: $" + String.format("%.2f", total));
    }

    public void vaciarCarrito() {
        // Restaurar el stock de los productos
        for (Producto p : productos) {
            p.setStock(p.getStock() + 1); // Incrementa el stock al vaciar el carrito
        }
        productos.clear();
        total = 0.0;
        System.out.println("🗑️ Carrito vaciado");
    }

    public double getTotal() {
        return total;
    }
// ... existing code ...
```

---

## Rol 3: El Coordinador del Supermercado (`Supermercado.java`)

Esta es la clase principal que **dirige la orquesta** de nuestro sistema. Es la interfaz que el usuario final ve y con la que interactúa.

### Responsabilidades Clave:
*   Inicializar el inventario con productos de ejemplo.
*   Mostrar el menú principal y gestionar las opciones del usuario.
*   Coordinar la interacción entre el inventario y el carrito.
*   Manejar el flujo de la compra.

### Fragmento de Código Clave:

```java
// src/Supermercado.java
// ... existing code ...
    public void iniciar() {
        boolean salir = false;
        
        try {
            while (!salir) {
                mostrarMenu();
                int opcion = obtenerOpcion();
                
                switch (opcion) {
                    case 1:
                        mostrarInventario();
                        break;
                    case 2:
                        agregarAlCarrito(); // Llama al Carrito para añadir producto
                        break;
                    case 3:
                        carrito.mostrarCarrito(); // Llama al Carrito para mostrar
                        break;
                    case 4:
                        realizarCompra(); // Procesa la compra
                        break;
                    case 5:
                        carrito.vaciarCarrito(); // Llama al Carrito para vaciar
                        break;
                    case 6:
                        salir = true;
                        System.out.println("👋 ¡Gracias por visitar nuestro supermercado!");
                        break;
                    default:
                        System.out.println("❌ Opción no válida");
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
// ... existing code ...
    private void agregarAlCarrito() {
        if (inventario.isEmpty()) {
            System.out.println("❌ No hay productos disponibles");
            return;
        }

        mostrarInventario();
        System.out.print("Seleccione el número del producto: ");
        int seleccion = obtenerOpcion();
        
        if (seleccion > 0 && seleccion <= inventario.size()) {
            carrito.agregarProducto(inventario.get(seleccion - 1)); // Pasa el Producto al Carrito
        } else {
            System.out.println("❌ Selección no válida");
        }
    }

    private void realizarCompra() {
        if (carrito.getTotal() > 0) {
            System.out.println("\n💰 TOTAL A PAGAR: $" + String.format("%.2f", carrito.getTotal()));
            System.out.println("¡Gracias por tu compra! 🎉");
            carrito.vaciarCarrito(); // Vacía el carrito después de la compra
        } else {
            System.out.println("❌ No hay productos en el carrito");
        }
    }
// ... existing code ...
```

---

## Conclusión: La Sinergia del Diseño Orientado a Objetos

Nuestro Sistema de Supermercado Virtual es un claro ejemplo de cómo la **Programación Orientada a Objetos** nos permite resolver problemas complejos de manera organizada y eficiente. Al dividir las responsabilidades en clases bien definidas, logramos un código:

*   **Robusto:** Cada componente maneja su propia lógica.
*   **Fácil de Entender:** La funcionalidad está encapsulada en roles lógicos.
*   **Extensible:** Podemos añadir nuevas características o tipos de productos sin romper el sistema existente.
*   **Mantenible:** Los errores son más fáciles de localizar y corregir.

Esperamos que esta presentación les haya brindado una comprensión clara de nuestro proyecto y cómo aplicamos los principios de la POO para crear una solución práctica y funcional.

¡Muchas gracias!