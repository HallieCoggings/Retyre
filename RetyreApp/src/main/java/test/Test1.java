package test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Owner;
import model.Vehicle;
import model.VehicleType;
import model.enums.*;

import java.time.LocalDate;

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
                VehicleType vType2 = new VehicleType("Renault","Twingo",EnergyType.FUEL,TransmissionType.MANUAL,5,5,130);
                Owner owner1 = new Owner("Doe","John","bibib");
                Owner owner2 = new Owner("Doe","Jane","MAMA");
                Owner owner3 = new Owner("Mercuro","Freddo","Bohemian");
                Vehicle v1 = new Vehicle("AA-192-BB",vType1,owner1,1230, LocalDate.of(2025,5,12));
                Vehicle v2 = new Vehicle("AA-185-BB",vType1,owner2,1230, LocalDate.of(2025,6,6));
                Vehicle v3 = new Vehicle("BA-185-BB",vType2,owner1,1230, LocalDate.of(2023,7,6));
                Vehicle v4 = new Vehicle("AA-169-BB",vType1,owner3,15000, LocalDate.of(1991,11,24));

                em.persist(vType1);
                em.persist(vType2);
                em.persist(owner1);
                em.persist(owner2);
                em.persist(v1);
                em.persist(v2);
                em.persist(v3);


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
