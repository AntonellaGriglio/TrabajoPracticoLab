package TrabajoPractico.TP.dtos;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReciboDTO {
    private int Mes;
    private int Anio;
    private double MontoAntiguedad;
    private double Jubilacion;
    private double ObraSocial;
    private double FondoComplejidad;
    private int Legajo;   
}
