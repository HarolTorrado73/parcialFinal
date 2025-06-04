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