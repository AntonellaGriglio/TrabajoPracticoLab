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
    private int NumeroRecibo;
    private int Mes;
    private int Anio;
    private double MontoAntiguedad;
    private double Jubilacion;
    private double ObraSocial;
    private double FondoComplejidad;
    private int Legajo;   
    private double SueldoNeto;
}
