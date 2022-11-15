package TrabajoPractico.TP.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import TrabajoPractico.TP.Models.Empleado;
import TrabajoPractico.TP.Models.Recibo;
import TrabajoPractico.TP.dtos.EmpleadoDTO;
import TrabajoPractico.TP.dtos.ReciboDTO;
import TrabajoPractico.TP.repositories.EmpleadoRepository;

import exception.exceptionRep;

@RestController
public class EmpleadoController {
    @Autowired
    private EmpleadoRepository repository;
    
    @PostMapping("api/recibo/nuevo")
    public ResponseEntity<?> registrarRecibo(@RequestBody ReciboDTO reciboDto) throws exceptionRep{
        Empleado aux = new Empleado();
        aux.setLegajo(reciboDto.getLegajo());
        Recibo recibo = new Recibo(0,
        reciboDto.getMes(),
        reciboDto.getAnio(),
        reciboDto.getMontoAntiguedad(),
        reciboDto.getJubilacion(),
        reciboDto.getObraSocial(),
        reciboDto.getFondoComplejidad(),
        aux);
        repository.crearRecibo(recibo);
        
        return ResponseEntity.ok("Recibo Creado Correctamente");

    
    }

    @GetMapping("/api/empleados")
    public ResponseEntity<?> traerEmpleados() {
        try {
            return ResponseEntity.ok((repository.traerEmpleados()));
        } catch (exceptionRep e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/empleados/area/{area}")
    public ResponseEntity<?> getById(@PathVariable String area) {
        if (area == "") {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Debe Ingresar un area");
        }
        try {
            return ResponseEntity.ok(repository.traerEmpleadosXArea(area));
        } catch (exceptionRep e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error de datos al traer empleados por area");
        }
    }

    @PostMapping("api/empleado/nuevo/")
    public ResponseEntity<?> create(@RequestBody EmpleadoDTO emp) throws exceptionRep {
        Empleado empleado = new Empleado
        (emp.getLegajo(),
        emp.getNombre(),
        emp.getApellido(),
        emp.getFechaIngreso(),
        emp.getFechaNacimiento(),
        emp.getArea(),
        emp.getSueldoBruto());
        repository.crearEmpleado(empleado);
        
        return ResponseEntity.ok("Empleado Creado Correctamente");
    }
    @DeleteMapping("api/empleado/eliminar/{Legajo}")
    public ResponseEntity<?> delete(@PathVariable int Legajo) {
        if (Legajo == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El Legajo no puede ser 0");
        }
        try {
            return ResponseEntity.ok(repository.borrarEmpleado(Legajo));
        } catch (exceptionRep e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error de datos al eliminar el empleado!");
        }
    }
    @GetMapping("/empleados/recibos/{Legajo}")
    public ResponseEntity<?> getById(@PathVariable int Legajo) {
        if (Legajo == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Debe poner un Legajo");
        }
        try {
            return ResponseEntity.ok(repository.traerRecibos(Legajo));
        } catch (exceptionRep e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error de datos!");
        }
    }

    @GetMapping("api/empleados/recibos/MesYAnio/{Anio}/{Mes}")
    public ResponseEntity<?> getById(@PathVariable int Anio, @PathVariable int Mes) {
        if (Anio == 0 && Mes == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Debe poner un año y mes");
        }
        try {
            return ResponseEntity.ok(repository.traerRecibosMesAnio(Anio,Mes));
        } catch (exceptionRep e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error de datos!");
        }
    }


    
}
