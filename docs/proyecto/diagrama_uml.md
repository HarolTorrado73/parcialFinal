```mermaid
classDiagram
    class Supermercado {
        -List~Producto~ inventario
        -Carrito carrito
        -Scanner scanner
        +Supermercado()
        -inicializarInventario()
        -agregarProductoAlInventario(Producto)
        -existeProducto(String)
        +iniciar()
        -mostrarMenu()
        -obtenerOpcion()
        -mostrarInventario()
        -agregarAlCarrito()
        -realizarCompra()
    }

    class Producto {
        -String nombre
        -double precio
        -int stock
        -String categoria
        +Producto(String, double, int, String)
        +getNombre()
        +getPrecio()
        +getStock()
        +getCategoria()
        +setStock(int)
        +toString()
    }

    class Carrito {
        -List~Producto~ productos
        -double total
        +Carrito()
        +agregarProducto(Producto)
        +mostrarCarrito()
        +vaciarCarrito()
        +getTotal()
    }

    Supermercado "1" *-- "1" Carrito : contiene
    Supermercado "1" *-- "*" Producto : gestiona
    Carrito "1" *-- "*" Producto : contiene
}

## Relaciones entre Clases

1. **Supermercado - Carrito**: Relación de composición (1 a 1)
   - El Supermercado contiene un único Carrito
   - El Carrito no puede existir sin un Supermercado

2. **Supermercado - Producto**: Relación de composición (1 a muchos)
   - El Supermercado gestiona múltiples Productos
   - Los Productos son parte del inventario del Supermercado

3. **Carrito - Producto**: Relación de composición (1 a muchos)
   - El Carrito puede contener múltiples Productos
   - Los Productos pueden estar en el Carrito

## Atributos y Métodos Principales

### Supermercado
- **Atributos**:
  - `inventario`: Lista de productos disponibles
  - `carrito`: Instancia del carrito de compras
  - `scanner`: Para entrada de usuario
- **Métodos**:
  - `iniciar()`: Inicia la aplicación
  - `mostrarMenu()`: Muestra el menú principal
  - `agregarAlCarrito()`: Añade productos al carrito

### Producto
- **Atributos**:
  - `nombre`: Nombre del producto
  - `precio`: Precio unitario
  - `stock`: Cantidad disponible
  - `categoria`: Categoría del producto
- **Métodos**:
  - Getters y setters para todos los atributos
  - `toString()`: Representación en texto del producto

### Carrito
- **Atributos**:
  - `productos`: Lista de productos en el carrito
  - `total`: Suma total de los productos
- **Métodos**:
  - `agregarProducto()`: Añade un producto al carrito
  - `mostrarCarrito()`: Muestra el contenido del carrito
  - `vaciarCarrito()`: Limpia el carrito 