package TrabajoPractico.TP.dtos;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReciboDTO {
    private int mes;
    private int anio;
    private double montoAntiguedad;
    private double jubilacion;
    private double obraSocial;
    private double fondoComplejidad;
    private int legajo;   
}
