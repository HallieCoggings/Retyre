package test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Owner;
import model.Vehicle;
import model.VehicleType;
import model.enums.*;

import java.util.Date;

/**
 * <h1>Test 1</h1>
 * Test the behavior of the following classes :
 *<ul>
 *     <li>VehicleType</li>
 *     <li>Vehicle</li>
 *     <li>Owner</li>
 *</ul>
 */
public class Test1 {
    static void main() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("RetyrePU");
        final EntityManager em =emf.createEntityManager();
        try{
            final EntityTransaction et = em.getTransaction();
            try{
                et.begin();
                VehicleType vType1 = new VehicleType("Peugeot","308",EnergyType.FUEL,TransmissionType.MANUAL,5,5,130);
                Owner owner1 = new Owner("Doe","John","bibib");
                Vehicle v1 = new Vehicle("AA-192-BB",vType1,owner1,1230,new Date(2025,12,5));

                owner1.addVehicle(v1);

                em.persist(vType1);
                em.persist(owner1);
                em.persist(v1);

                et.commit();
            }catch (Exception e){
                System.out.println("Exception "+e);
                System.out.println("Rollback");
                et.rollback();
            }
        }finally {
            if(em!=null && em.isOpen()){
                em.close();
            }

            if(emf!=null && emf.isOpen()){
                emf.close();
            }
        }
    }
}
