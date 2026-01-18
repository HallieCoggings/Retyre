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
            Owner managedOwner;
            try {
                managedOwner = em.createQuery(
                                "SELECT o FROM Owner o WHERE o.name = :n AND o.firstName = :fn", Owner.class)
                        .setParameter("n", o.getName())
                        .setParameter("fn", o.getFirstName())
                        .getSingleResult();

               //Avoid recreate Owner
                managedOwner.setPersonalDetails(o.getPersonalDetails());
            } catch (NoResultException e) {
                // Create a new Owner
                managedOwner = o;
                em.persist(managedOwner);
            }

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

    // TODO : Fix the following methods
    public List<Employee> getEmployees(){
        return  null;
    }

    public boolean addIntervention(Intervention i){
        return true;
    }

    public List<Vehicle> getVehicles(){
        return null;
    }

    public List<InterventionType> getInterventionTypes(){
        return null;
    }

    public List<Intervention> getInterventions(){
        return null;
    }
    // END TODO
}
