package querry;

import jakarta.persistence.*;
import model.*;

import java.util.List;

/**
 * <h1>QueryRetyre</h1>
 * Used to make queries to the DB
 */
public class QueryRetyre {
    private EntityManagerFactory emf;

    public QueryRetyre(){
        this.emf = Persistence.createEntityManagerFactory("RetyrePU");
    }

    public boolean addVehicleType (VehicleType v){
        if (v==null){return false;}
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            em.merge(v);;
            et.commit();
        }catch (Exception e){
            System.out.println("Rollback");
            et.rollback();
            return false;
        }finally {
            em.close();
        }
        return true;
    }

    public List<Piece> getPiece(){
        EntityManager em = emf.createEntityManager();
        String req = "SELECT p FROM Piece p JOIN FETCH p.installedOn"; //To avoid lazy error => meaning not all the data have been load
        Query query = em.createQuery(req);
        List<Piece> pieces = query.getResultList();
        em.close();
        return pieces;
    }

    public List<VehicleType> getVehicleType (){
        EntityManager em = emf.createEntityManager();
        String req = "SELECT v FROM VehicleType v";
        Query query = em.createQuery(req);
        List<VehicleType> types = query.getResultList();
        em.close();
        return  types;
    }

    public boolean addVehicle (Vehicle v, Owner o,VehicleType vt){
        if (v==null || o==null || vt == null){return false;}
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            VehicleType vehicleType = em.merge(vt);
            Owner managedOwner = em.merge(o);

            vehicleType.addVehicle(v);
            managedOwner.addVehicle(v);

            em.persist(v);
            et.commit();
        }catch (Exception e){
            System.out.println("Rollback");
            e.printStackTrace();
            et.rollback();
            return false;
        }finally {
            em.close();
        }
        return true;
    }

    public List<Owner> getOwners (){
        EntityManager em = emf.createEntityManager();
        String req = "SELECT o FROM Owner o";
        Query query = em.createQuery(req);
        List<Owner> owners = query.getResultList();
        em.close();
        return  owners;
    }

    public List<Intervention> getInterventionsVehicle(Vehicle vehicle){
        EntityManager em = emf.createEntityManager();
        String req = "SELECT i FROM Intervention i JOIN i.vehicle v WHERE v.licencePlate = :plate";
        Query query = em.createQuery(req);
        query.setParameter("plate",vehicle.getLicencePlate());
        List<Intervention> interventions = query.getResultList();
        em.close();
        return  interventions;
    }

    // QUERY MADE BY KERMIT
    public List<Employee> getEmployees(){
        EntityManager em = emf.createEntityManager();
        String req = "SELECT e FROM Employee e";
        Query query = em.createQuery(req);
        List<Employee> employees = query.getResultList();
        em.close();
        return employees;
    }

    public boolean addIntervention(Intervention i){
        return true;
    }

    public List<Vehicle> getVehicles(){
        EntityManager em = emf.createEntityManager();
        String req = "SELECT v FROM Vehicle v";
        Query query = em.createQuery(req);
        List<Vehicle> vehicles = query.getResultList();
        em.close();
        return vehicles;
    }

    public List<InterventionType> getInterventionTypes(){
        EntityManager em = emf.createEntityManager();
        String req = "SELECT t FROM InterventionType t";
        Query query = em.createQuery(req);
        List<InterventionType> interventionType = query.getResultList();
        em.close();
        return interventionType;
    }

    public List<Intervention> getInterventions(){        EntityManager em = emf.createEntityManager();
        String req = "SELECT i FROM Intervention i";
        Query query = em.createQuery(req);
        List<Intervention> intervention = query.getResultList();
        em.close();
        return intervention;
    }
}
