package TrabajoPractico.TP.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReciboReporteDTO {
    private String Area;
    private double SueldoNeto;
    private int Anio;
    private int Mes;
}
