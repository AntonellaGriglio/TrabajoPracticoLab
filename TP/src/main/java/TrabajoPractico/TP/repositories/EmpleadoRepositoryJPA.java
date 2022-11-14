package TrabajoPractico.TP.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import TrabajoPractico.TP.Models.Empleado;
import TrabajoPractico.TP.Models.Recibo;
import TrabajoPractico.TP.dtos.Recibo2DTO;
import TrabajoPractico.TP.dtos.ReciboReporteDTO;
import exception.exceptionRep;


@Repository
public class EmpleadoRepositoryJPA implements EmpleadoRepository{
    
    @PersistenceContext
    private EntityManager em;


    @Transactional
    public void crearRecibo(Recibo recibo) {
        Empleado emp = em.find(Empleado.class,recibo.getEmpleado().getLegajo());
        emp.addRecibo(recibo);
        em.merge(recibo);
    }

    @Override
    public List<Empleado> traerEmpleados() throws exceptionRep {
        return em.createQuery("SELECT * FROM tp_lab.empleado;", Empleado.class).getResultList();
    }


    @Override
    public void crearEmpleado(Empleado empleado) throws exceptionRep {
       em.merge(empleado);
    }

    @Override
    public boolean borrarEmpleado(int id) throws exceptionRep {
        try {
            Empleado entity = this.getbyId(id);
            em.remove(entity);
        } catch (Exception e) {
            throw new exceptionRep("Error de datos!", e);
        }
        return true;
    }

    @Override
    public List<Empleado> traerEmpleadosXArea(String Area) throws exceptionRep {

      return em.createQuery("SELECT * FROM tp_lab.empleado WHERE Area = ;" + Area, Empleado.class).getResultList();

    }

    @Override
    public Empleado getbyId(int id) throws exceptionRep {
        return em.find(Empleado.class, id);
    }

  

    @Override
    public List<Recibo2DTO> traerRecibos(int Legajo) throws exceptionRep {
        return em.createQuery("SELECT R.*, sum(e.SueldoBruto + r.MontoAntigüedad - r.Jubilacion - r.ObraSocial- r.FondoComplejidad ) SueldoNeto FROM tp_lab.recibo r join tp_lab.empleado e on e.Legajo = r.Legajo  where e.Legajo = " + Legajo, Recibo2DTO.class).getResultList();

    }

    @Override
    public List<ReciboReporteDTO> traerRecibosMesAnio(int Anio, int Mes) throws exceptionRep {
        return em.createQuery("SELECT  sum(e.SueldoBruto + r.MontoAntigüedad - r.Jubilacion - r.ObraSocial- r.FondoComplejidad ) SueldoNeto,e.Area,r.Anio,r.Mes  FROM tp_lab.recibo r join tp_lab.empleado e on e.Legajo = r.Legajo  where r.Mes = "+ Mes +" && r.Anio= "+ Anio +"group by e.Area order by  SueldoNeto asc", ReciboReporteDTO.class).getResultList();

    }
    
}
