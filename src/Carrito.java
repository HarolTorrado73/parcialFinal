import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Producto> productos;
    private double total;

    public Carrito() {
        this.productos = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarProducto(Producto producto) {
        if (producto == null) {
            System.out.println("❌ Error: Producto no válido");
            return;
        }
        
        if (producto.getStock() > 0) {
            productos.add(producto);
            total += producto.getPrecio();
            producto.setStock(producto.getStock() - 1);
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
            p.setStock(p.getStock() + 1);
        }
        productos.clear();
        total = 0.0;
        System.out.println("🗑️ Carrito vaciado");
    }

    public double getTotal() {
        return total;
    }
} 