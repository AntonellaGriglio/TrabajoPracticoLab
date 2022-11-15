/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TrabajoPractico.TP.repositories;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import javax.sql.DataSource;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import TrabajoPractico.TP.Models.Empleado;
import TrabajoPractico.TP.Models.Recibo;
import TrabajoPractico.TP.dtos.EmpleadoDTO;
import TrabajoPractico.TP.dtos.Recibo2DTO;
import TrabajoPractico.TP.dtos.ReciboReporteDTO;
import exception.exceptionRep;

/**
 *
 * @author Martin
 */
@Repository
@Setter
@Primary
public class EmpleadoRepositoryMySql implements EmpleadoRepository {
    @Autowired
    private DataSource dataSource;


    @Override
    public void crearRecibo(Recibo recibo) throws exceptionRep {
        Empleado aux = getbyId(recibo.getEmpleado().getLegajo());
        String query;


        if (aux == null) {}
        else{
            query = "INSERT INTO `tp_lab`.`recibo` (Legajo, Anio,Mes, MontoAntigüedad, Jubilacion, ObraSocial, FondoComplejidad) VALUES ( ?, ?, ?, ?, ?, ?, ?) ;";

            try {
                Connection cnn = dataSource.getConnection();
                PreparedStatement ps = cnn.prepareStatement(query);

                ps.setInt(1, recibo.getEmpleado().getLegajo());
                ps.setInt(2, recibo.getAnio());
                ps.setInt(3, recibo.getMes());
                ps.setDouble(4, recibo.getMontoAntigüedad());
                ps.setDouble(5, recibo.getJubilacion());
                ps.setDouble(6, recibo.getObraSocial());
                ps.setDouble(7, recibo.getFondoComplejidad());


                ps.execute();


            } catch (Exception e) {
                System.out.println(e);
                throw new exceptionRep("Error de datos al crear Recibos", e);
            }
        }
    }


  
    @Override
    public List<EmpleadoDTO> traerEmpleados() throws exceptionRep {
        List<EmpleadoDTO> lst = new ArrayList<>();
        final String query = "SELECT *,DATEDIFF(NOW(),Fecha_Ingreso) DiasAntiguedad, year(now())-year(Fecha_Ingreso) Antiguedad FROM tp_lab.empleado ";

        try ( Connection cnn = dataSource.getConnection();  Statement smt = cnn.createStatement();  ResultSet rs = smt.executeQuery(query)) {
            while (rs.next()) {
                lst.add(mapEmpleadoDTO(rs));
            }

        } catch (SQLException e) {
            throw new exceptionRep("Error de datos al Obtener Empleados", e);
        }

        return lst;
    }

    private EmpleadoDTO mapEmpleadoDTO(ResultSet rs) throws SQLException {
        int Legajo = rs.getInt("Legajo");
        String Nombre = rs.getString("Nombre"); 
        String Apellido = rs.getString("Apellido");
        java.util.Date fechaNacimiento = rs.getDate("Fecha_Nac");
        java.util.Date fechaIngreso = rs.getDate("Fecha_Ingreso");
        String Area = rs.getString("Area");
        int antiguedad = rs.getInt("Antiguedad");
        int diasAntiguedad= rs.getInt("DiasAntiguedad");
        double SueldoBruto = rs.getDouble("SueldoBruto");
        

        return new EmpleadoDTO(Legajo,Nombre,Apellido,fechaNacimiento,fechaIngreso,Area,diasAntiguedad,antiguedad,SueldoBruto);
    }
    private Empleado mapEmpleado(ResultSet rs) throws SQLException {
        int Legajo = rs.getInt("Legajo");
        String Nombre = rs.getString("Nombre"); 
        String Apellido = rs.getString("Apellido");
        java.util.Date fechaNacimiento = rs.getDate("Fecha_Nac");
        java.util.Date fechaIngreso = rs.getDate("Fecha_Ingreso");
        String Area = rs.getString("Area");
        double SueldoBruto = rs.getDouble("SueldoBruto");
        

        return new Empleado(Legajo,Nombre,Apellido,fechaNacimiento,fechaIngreso,Area,SueldoBruto);
    }


    @Override
    public List<EmpleadoDTO> traerEmpleadosXArea(String Area) throws exceptionRep {
       List<EmpleadoDTO> lst = new ArrayList<>();
        final String query = "SELECT *,DATEDIFF(NOW(),Fecha_Ingreso) DiasAntiguedad, year(now())-year(Fecha_Ingreso) Antiguedad FROM tp_lab.empleado WHERE Area = ?";

        try ( Connection cnn = dataSource.getConnection();  PreparedStatement ps = cnn.prepareStatement(query);) {
            ps.setString(1, Area);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lst.add(mapEmpleadoDTO(rs));
            }
           

        } catch (SQLException e) {
            throw new exceptionRep("Error de datos traer empleasoa por area", e);
        }

        return lst;
    }

    @Override
    public void crearEmpleado(Empleado empleado) throws exceptionRep {
        Empleado aux = getbyId(empleado.getLegajo());
        System.out.println(empleado.toString());
        String query;


        if (aux == null) {
            query = "INSERT INTO empleado(Legajo,Nombre,Apellido,Fecha_Nac,Fecha_Ingreso,Area,SueldoBruto) VALUES (?, ?, ?, ?, ?, ?, ?);";

            try {
                Connection cnn = dataSource.getConnection();
                PreparedStatement ps = cnn.prepareStatement(query);

                ps.setInt(1, empleado.getLegajo());
                ps.setString(2, empleado.getNombre());
                ps.setString(3, empleado.getApellido());
                java.sql.Date sqlFecNac = new java.sql.Date(empleado.getFechaNacimiento().getTime());
                ps.setDate(4, sqlFecNac);
                java.sql.Date sqlFecIng = new java.sql.Date(empleado.getFechaIngreso().getTime());
                ps.setDate(5, sqlFecIng);
                ps.setString(6, empleado.getArea());
                ps.setDouble(7, empleado.getSueldoBruto());


                ps.execute();

            } catch (Exception e) {
                System.out.println(e);
                throw new exceptionRep("Error de datos al crear empleado", e);
            }
        }


    }

    @Override
    public boolean borrarEmpleado(int Legajo) throws exceptionRep {
        final String query = "DELETE  FROM  tp_lab.empleado Where Legajo = ? ";
        int rows = 0;
        try ( Connection cnn = dataSource.getConnection();  PreparedStatement ps = cnn.prepareStatement(query);) {
            ps.setInt(1, Legajo);
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            throw new exceptionRep("Error de datos al borrar empleado", e);
        }

        return rows == 1;
    }


    @Override
    public Empleado getbyId(int id) throws exceptionRep {
        Empleado found = null;
        final String query = "SELECT * FROM tp_lab.empleado WHERE Legajo = ?";

        try ( Connection cnn = dataSource.getConnection();  PreparedStatement ps = cnn.prepareStatement(query);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                found = mapEmpleado(rs);
            }
            rs.close();

        } catch (SQLException e) {
            throw new exceptionRep("Error de datos", e);
        }

        return found;
    }


    @Override
    public List<Recibo2DTO> traerRecibos(int Legajo) throws exceptionRep {
        List<Recibo2DTO> lst = new ArrayList<>();
        final String query = "SELECT r.*, sum(e.SueldoBruto + r.MontoAntigüedad - r.Jubilacion - r.ObraSocial- r.FondoComplejidad ) SueldoNeto FROM tp_lab.recibo r join tp_lab.empleado e on e.Legajo = r.Legajo  where e.Legajo = ? group by r.NumeroRecibo;";

        try ( Connection cnn = dataSource.getConnection();  PreparedStatement ps = cnn.prepareStatement(query);) {
            ps.setInt(1, Legajo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lst.add(mapRecibo(rs));
            }
           

        } catch (SQLException e) {
            throw new exceptionRep("Error de datos al traer recibos por empleado", e);
        }

        return lst;
        
    }


    private Recibo2DTO mapRecibo(ResultSet rs) throws SQLException {
        int NumeroRecibo = rs.getInt("NumeroRecibo");
        int Legajo = rs.getInt("Legajo");
        int Mes = rs.getInt("Mes"); 
        int Anio = rs.getInt("Anio"); 
        double MontoAntiguedad = rs.getDouble("MontoAntigüedad");
        double Jubilacion = rs.getDouble("Jubilacion");
        double ObraSocial = rs.getDouble("ObraSocial");
        double FondoComplejidad = rs.getDouble("FondoComplejidad");
        double SueldoNeto = rs.getDouble("SueldoNeto");
        
        

        return new Recibo2DTO(NumeroRecibo,Mes,Anio,MontoAntiguedad,Jubilacion,ObraSocial,FondoComplejidad,Legajo,SueldoNeto);
    }


    @Override
    public List<ReciboReporteDTO> traerRecibosMesAnio(int Anio, int Mes) throws exceptionRep {
        List<ReciboReporteDTO> lst = new ArrayList<>();
        final String query = "SELECT  sum(e.SueldoBruto + r.MontoAntigüedad - r.Jubilacion - r.ObraSocial- r.FondoComplejidad ) SueldoNeto,e.Area,r.Anio,r.Mes  FROM tp_lab.recibo r join tp_lab.empleado e on e.Legajo = r.Legajo  where r.Mes = ? && r.Anio= ? group by e.Area order by  SueldoNeto asc";

        try ( Connection cnn = dataSource.getConnection();  PreparedStatement ps = cnn.prepareStatement(query);) {
            ps.setInt(1,Mes);
            ps.setInt(2,Anio);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lst.add(mapReciboReporte(rs));
            }
           

        } catch (SQLException e) {
            throw new exceptionRep("Error de datos", e);
        }

        return lst;
    }


    private ReciboReporteDTO mapReciboReporte(ResultSet rs) throws SQLException {
        String Area = rs.getString("Area");
        double SueldoNeto = rs.getDouble("SueldoNeto");
        int Anio=rs.getInt("Anio");
        int Mes = rs.getInt("Mes");

        return new ReciboReporteDTO(Area,SueldoNeto,Anio,Mes);
    }

}
