package querry;

import jakarta.persistence.*;
import model.VehicleType;

/**
 * <h1>QueryRetyre</h1>
 * Used to make queries to the DB
 */
public class QueryRetyre {
    private EntityManagerFactory emf;

    public QueryRetyre(){
        this.emf = Persistence.createEntityManagerFactory("RetyrePU");
    }

    public boolean addVehicle (VehicleType v){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            em.persist(v);;
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
}
