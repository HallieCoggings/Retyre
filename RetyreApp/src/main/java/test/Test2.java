package test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.*;
import model.enums.*;

import java.time.LocalDate;


/**
 * <h1>Test 2</h1>
 * Test the behavior of the following classes :
 * <ul>
 *     <li>Employee</li>
 *     <li>Intervention</li>
 *     <li>Intervention Type</li>
 *     <li>Piece</li>
 * </ul>
 */
public class Test2 {
    static void main() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("RetyrePU");
        final EntityManager em = emf.createEntityManager();
        try{
            final EntityTransaction et = em.getTransaction();
            try{
                et.begin();

                VehicleType vType1 = new VehicleType("Ferrari","901 Turbo", EnergyType.FUEL,
                        TransmissionType.MANUAL,3,2,1500);

                Owner owner1 = new Owner("Silverhand","Johny","Samurai");
                Vehicle v1 = new Vehicle("NY2077",vType1,owner1,2077,
                        LocalDate.of(2077,4,17));
                Employee e1 = new Employee("Alvarez","Judy",Speciality.MACHINERY);


                Piece p1 = new Piece("153132","Motor",300,"Core piece of the vehicle","Motor");
                Piece p2 = new Piece("15913213","Oil",300,"Core piece of the vehicle","Motor");

                InterventionType it1 = new InterventionType("Change Motor",InterventionCategory.REPAIR,0,0);
                InterventionType it2 = new InterventionType("Cleaning",InterventionCategory.MAINTENANCE,50,60);

                Intervention i1 = new Intervention(v1,it1,LocalDate.of(2077,8,31));
                Intervention i2 = new Intervention(v1,it2,LocalDate.of(2077,7,8));


                vType1.addPiece(p1);

                i1.setEmployee(e1);
                i2.setEmployee(e1);

                it1.addPieceUsed(p1);
                it2.addPieceUsed(p2);

                i2.beginIntervention();
                Intervention i3 = i2.endIntervention();

                em.persist(vType1);
                em.persist(owner1);
                em.persist(e1);
                em.persist(v1);
                em.persist(p1);
                em.persist(p2);
                em.persist(it1);
                em.persist(it2);
                em.persist(i1);
                em.persist(i2);
                em.persist(i3);

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

            if (emf!=null && emf.isOpen()){
                emf.close();
            }
        }
    }
}
