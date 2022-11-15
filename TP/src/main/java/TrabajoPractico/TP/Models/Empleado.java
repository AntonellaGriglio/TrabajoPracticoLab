package TrabajoPractico.TP.Models;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Legajo;
    private String Nombre; 
    private String Apellido;
    @Column(name = "Fecha_Nac")
    private Date fechaNacimiento;
    @Column(name = "Fecha_Ingreso")
    private Date fechaIngreso;
    private String Area;
    private double SueldoBruto;
    
    @OneToMany(targetEntity = Recibo.class, mappedBy = "empleado")
    @JsonManagedReference
    private List<Recibo> recibos;
    
    public void addRecibo(Recibo recibo){
        recibo.setEmpleado(this);
        recibos.add(recibo);
    }

    public Empleado(int Legajo, String Nombre, String Apellido,java.util.Date fechaNacimiento2,
            java.util.Date fechaIngreso2, String area, double sueldoBruto) {
                this.Legajo = Legajo;
                this.Nombre = Nombre;
                this.Apellido = Apellido;
                this.fechaIngreso = (Date) fechaIngreso2;
                this.fechaNacimiento = (Date) fechaNacimiento2;
                this.Area=area;
                this.SueldoBruto= sueldoBruto;
    }
    

   
    
}
