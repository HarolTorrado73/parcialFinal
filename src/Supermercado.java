import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Supermercado {
    private List<Producto> inventario;
    private Carrito carrito;
    private Scanner scanner;

    public Supermercado() {
        this.inventario = new ArrayList<>(); //lista vacia
        this.carrito = new Carrito();
        this.scanner = new Scanner(System.in);
        inicializarInventario();
    }

    private void inicializarInventario() {
        // Agregar algunos productos de ejemplo
        agregarProductoAlInventario(new Producto("Leche", 2.50, 10, "Lácteos"));
        agregarProductoAlInventario(new Producto("Pan", 1.50, 15, "Panadería"));
        agregarProductoAlInventario(new Producto("Huevos", 3.00, 20, "Lácteos"));
        agregarProductoAlInventario(new Producto("Manzanas", 1.00, 30, "Frutas"));
        agregarProductoAlInventario(new Producto("Arroz", 2.00, 25, "Granos"));
        agregarProductoAlInventario(new Producto("Pollo", 5.00, 8, "Carnes"));
        agregarProductoAlInventario(new Producto("Coca-Cola", 1.50, 20, "Bebidas"));
        agregarProductoAlInventario(new Producto("Galletas", 2.00, 15, "Snacks"));
    }

    private void agregarProductoAlInventario(Producto producto) {
        if (producto != null && !existeProducto(producto.getNombre())) {
            inventario.add(producto);
        }
    }

    private boolean existeProducto(String nombre) {
        return inventario.stream().anyMatch(p -> p.getNombre().equalsIgnoreCase(nombre));
    }

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
                        agregarAlCarrito();
                        break;
                    case 3:
                        carrito.mostrarCarrito();
                        break;
                    case 4:
                        realizarCompra();
                        break;
                    case 5:
                        carrito.vaciarCarrito();
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

    private void mostrarMenu() {
        System.out.println("\n🏪 SUPERMERCADO VIRTUAL 🏪");
        System.out.println("1. Ver inventario");
        System.out.println("2. Agregar producto al carrito");
        System.out.println("3. Ver carrito");
        System.out.println("4. Realizar compra");
        System.out.println("5. Vaciar carrito");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private int obtenerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim()); //elimina espacios innecesarios con .trim().

        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void mostrarInventario() {
        if (inventario.isEmpty()) { //es más limpio y más fácil de leer que hacer algo como size() == 0
            System.out.println("❌ No hay productos en el inventario");
            return;
        }

        System.out.println("\n📋 INVENTARIO DISPONIBLE 📋");
        System.out.println("------------------------");
        for (int i = 0; i < inventario.size(); i++) {
            System.out.println((i + 1) + ". " + inventario.get(i));
        }
        System.out.println("------------------------");
    }

    private void agregarAlCarrito() {
        if (inventario.isEmpty()) {
            System.out.println("❌ No hay productos disponibles");
            return;
        }

        mostrarInventario();
        System.out.print("Seleccione el número del producto: ");
        int seleccion = obtenerOpcion();
        
        if (seleccion > 0 && seleccion <= inventario.size()) {
            carrito.agregarProducto(inventario.get(seleccion - 1));
        } else {
            System.out.println("❌ Selección no válida");
        }
    }

    private void realizarCompra() {
        if (carrito.getTotal() > 0) {
            System.out.println("\n💰 TOTAL A PAGAR: $" + String.format("%.2f", carrito.getTotal()));
            System.out.println("¡Gracias por tu compra! 🎉");
            carrito.vaciarCarrito();
        } else {
            System.out.println("❌ No hay productos en el carrito");
        }
    }

    public static void main(String[] args) { //Punto de entrada de la aplicación
        Supermercado supermercado = new Supermercado();
        supermercado.iniciar();
    }
} 