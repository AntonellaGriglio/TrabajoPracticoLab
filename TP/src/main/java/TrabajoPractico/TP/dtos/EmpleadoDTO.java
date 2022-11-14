package TrabajoPractico.TP.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmpleadoDTO {
    private int legajo;
    private String nombre; 
    private String apellido;
    private Date fechaNacimiento;
    private Date fechaIngreso;
    private String area;
    private double sueldoBruto;
}