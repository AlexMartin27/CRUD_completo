package modelo;

public class Persona {

    int id;
    String nombre;
    String dui;

    public Persona() {
    }

    public Persona(int id, String nombre, String dui) {
        this.id = id;
        this.nombre = nombre;
        this.dui = dui;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

}
