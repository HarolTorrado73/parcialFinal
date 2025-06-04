# Diagramas UML - Supermercado Virtual

## Diagrama de Clases

```mermaid
classDiagram
    class Producto {
        -String nombre
        -double precio
        -int stock
        -String categoria
        +Producto(nombre, precio, stock, categoria)
        +getNombre() String
        +getPrecio() double
        +getStock() int
        +getCategoria() String
        +setStock(int) void
        +toString() String
    }

    class Carrito {
        -List~Producto~ productos
        -double total
        +Carrito()
        +agregarProducto(Producto) void
        +mostrarCarrito() void
        +vaciarCarrito() void
        +getTotal() double
    }

    class Supermercado {
        -List~Producto~ inventario
        -Carrito carrito
        -Scanner scanner
        +Supermercado()
        +iniciar() void
        -mostrarMenu() void
        -obtenerOpcion() int
        -mostrarInventario() void
        -agregarAlCarrito() void
        -realizarCompra() void
    }

    Supermercado "1" --> "1" Carrito
    Supermercado "1" --> "*" Producto
    Carrito "1" --> "*" Producto
```

## Diagrama de Casos de Uso

```mermaid
graph TD
    A[Usuario] --> B[Ver Inventario]
    A --> C[Agregar al Carrito]
    A --> D[Ver Carrito]
    A --> E[Realizar Compra]
    A --> F[Vaciar Carrito]
    A --> G[Salir del Sistema]

    B --> H[Mostrar Productos Disponibles]
    C --> I[Seleccionar Producto]
    I --> J[Verificar Stock]
    J --> K[Actualizar Carrito]
    D --> L[Mostrar Productos en Carrito]
    D --> M[Mostrar Total]
    E --> N[Confirmar Compra]
    N --> O[Actualizar Inventario]
    N --> P[Vaciar Carrito]
    F --> Q[Eliminar Productos]
```

## Diagrama de Secuencia - Proceso de Compra

```mermaid
sequenceDiagram
    actor Usuario
    participant S as Supermercado
    participant C as Carrito
    participant P as Producto

    Usuario->>S: Iniciar Sesión
    S->>S: Mostrar Menú
    Usuario->>S: Seleccionar "Agregar al Carrito"
    S->>S: Mostrar Inventario
    Usuario->>S: Seleccionar Producto
    S->>P: Verificar Stock
    P-->>S: Stock Disponible
    S->>C: Agregar Producto
    C->>P: Actualizar Stock
    C-->>S: Confirmar Agregado
    S-->>Usuario: Mostrar Mensaje de Éxito
```

## Diagrama de Estados - Producto

```mermaid
stateDiagram-v2
    [*] --> Disponible
    Disponible --> EnCarrito: Agregar al Carrito
    EnCarrito --> Disponible: Remover del Carrito
    EnCarrito --> Vendido: Realizar Compra
    Vendido --> [*]
    Disponible --> Agotado: Stock = 0
    Agotado --> Disponible: Reponer Stock
```

## Diagrama de Componentes

```mermaid
graph TD
    A[Interfaz de Usuario] --> B[Supermercado]
    B --> C[Carrito]
    B --> D[Inventario]
    D --> E[Producto]
    C --> E
```

## Diagrama de Actividad - Proceso de Compra

```mermaid
graph TD
    A[Inicio] --> B[Mostrar Menú]
    B --> C{Selección Usuario}
    C -->|Ver Inventario| D[Mostrar Productos]
    C -->|Agregar al Carrito| E[Seleccionar Producto]
    E --> F{Verificar Stock}
    F -->|Disponible| G[Agregar al Carrito]
    F -->|No Disponible| H[Mostrar Error]
    C -->|Ver Carrito| I[Mostrar Contenido]
    C -->|Realizar Compra| J[Confirmar Compra]
    J --> K[Actualizar Inventario]
    K --> L[Vaciar Carrito]
    C -->|Salir| M[Fin]
    D --> B
    G --> B
    H --> B
    I --> B
    L --> B
``` 