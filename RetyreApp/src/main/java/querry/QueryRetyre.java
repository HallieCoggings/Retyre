package querry;

import jakarta.persistence.*;
import model.Piece;
import model.VehicleType;

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

    public boolean addVehicle (VehicleType v){
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
}
