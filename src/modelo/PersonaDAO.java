package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
 Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

   public List listar() {
        List<Persona> datos = new ArrayList<>();
        String sql = "select * from persona";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Persona p = new Persona();
                p.setId(rs.getInt(1));
                p.setDui(rs.getString(2));
                p.setNombre(rs.getString(3));
                datos.add(p);
            }
        } catch (Exception e) {
        }
        return datos;
    }  
   
    public int agregar(Persona p) {
        String sql = "insert into persona(dui,nombre) values(?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getDui());
            ps.setString(2, p.getNombre());
            ps.executeUpdate();
        } catch (Exception e) {
        }

        return 1;
    }

      public int Actualizar(Persona p) {
          System.out.println("entro");
        int r = 0;
        String sql = "update persona set dui=?, nombre=? where id=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getDui());
            ps.setString(2, p.getNombre());
            ps.setInt(3, p.getId());
            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
        }
        return 1;
    }
      
      public void delete(int id) {
        String sql = "delete from persona where id=" + id;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
