package Simulacros.ADA;

import java.util.List;

public class Producto {
    private int productoId;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean disponible;
    private Especificaciones especificaciones;
    private List<Usuario> comentarios;
    private String fechaLanzamiento;
    private List<Promos> promociones;
}

class Especificaciones {
    private double peso;
    private Dimensiones dimensiones;
    private String bateria;
    private String[] coloresDisponibles;
}

class Dimensiones {
    private double alto;
    private double ancho;
    private double profundidad;
}

class Usuario{
    private String usuario;
    private String comentario;
    private String fecha;
    private int calificacion;
}
class Promos {
    private String tipo;
    private Double valor;
}
