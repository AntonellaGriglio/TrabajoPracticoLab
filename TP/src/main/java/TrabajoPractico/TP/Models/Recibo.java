package TrabajoPractico.TP.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recibo")

public class Recibo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int NumeroRecibo;
    private int Mes;
    private int Anio;
    private double MontoAntigüedad;
    private double Jubilacion;
    private double ObraSocial;
    private double FondoComplejidad;

    @ManyToOne(targetEntity = Empleado.class)
    @JoinColumn(name = "Legajo")
    @JsonBackReference
    private Empleado empleado;
}
