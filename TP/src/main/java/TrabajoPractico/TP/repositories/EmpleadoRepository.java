package TrabajoPractico.TP.repositories;

import java.util.List;



import TrabajoPractico.TP.Models.Empleado;
import TrabajoPractico.TP.Models.Recibo;
import TrabajoPractico.TP.dtos.EmpleadoDTO;
import TrabajoPractico.TP.dtos.Recibo2DTO;
import TrabajoPractico.TP.dtos.ReciboReporteDTO;
import exception.exceptionRep;

public interface EmpleadoRepository{
    void crearRecibo(Recibo recibo)throws exceptionRep ;
    List<EmpleadoDTO> traerEmpleados() throws exceptionRep;
    List<EmpleadoDTO> traerEmpleadosXArea(String Area) throws exceptionRep ;
    void crearEmpleado(Empleado empleado) throws exceptionRep; 
    boolean borrarEmpleado(int id)  throws exceptionRep;
    Empleado getbyId(int id) throws exceptionRep;
    List<Recibo2DTO> traerRecibos(int Legajo) throws exceptionRep;
    List<ReciboReporteDTO> traerRecibosMesAnio(int Anio, int Mes) throws exceptionRep;
    

}