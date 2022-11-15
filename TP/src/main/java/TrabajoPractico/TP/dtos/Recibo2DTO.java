package TrabajoPractico.TP.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Recibo2DTO {
    private int numeroRecibo;
    private int mes;
    private int anio;
    private double montoAntiguedad;
    private double jubilacion;
    private double obraSocial;
    private double fondoComplejidad;
    private int legajo;   
    private double sueldoNeto;
}
