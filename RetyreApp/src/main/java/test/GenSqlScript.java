package test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.*;
import model.enums.*;

import java.time.LocalDate;

/**
 * <h1>GenSqlScript</h1>
 * Use to generate the SQL creation script
 * And use as final
 */
public class GenSqlScript {
    static void main() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("RetyrePU");
        final EntityManager em = emf.createEntityManager();
        try{
            final EntityTransaction et = em.getTransaction();
            try{
                et.begin();

                // Vehicle Type Creation
                VehicleType vType1 = new VehicleType("Ferrari","901", EnergyType.FUEL, TransmissionType.MANUAL,3,2,180);
                VehicleType vType2 = new VehicleType("Renault","Zoe",EnergyType.ELECTRIC,TransmissionType.AUTOMATIC,5,5,100);
                VehicleType vType3 = new VehicleType("Villefort","Alvarado",EnergyType.HYBRID,TransmissionType.MANUAL,5,5,500);
                VehicleType vType4 = new VehicleType("DoLorean","DMC-12",EnergyType.DIESEL,TransmissionType.MANUAL,3,2,121);


                //Piece creation
                Piece p1 = new Piece("AZE123654","Oil",28,"Oil used for warious task","Liquid");
                Piece p2 = new Piece("YTB202563","Crankshaft",140,"A crankshaft","Mechanical");
                Piece p3 = new Piece("CP2077","Sparkle",15,"Used to light the motor","Electrical");
                Piece p4 = new Piece("CAR2006","Piston",200,"Piston used to make the explosion","Mechanical");
                Piece p5 = new Piece("BTF1985","Flux Conductor",1500,"Makes Time travel Possible","TimeTravel");

                //Add pieces to vehicle Type
                vType1.addPiece(p1);
                vType1.addPiece(p3);

                vType2.addPiece(p2);
                vType2.addPiece(p4);

                vType3.addPiece(p3);

                vType4.addPiece(p5);

                //Create Persons
                // Owner
                Owner owner1 = new Owner("Doe","John","8989-456-132");
                Owner owner2 = new Owner("Brown","Emet","1985-989-991");
                Owner owner3 = new Owner("Kim","Jennie","9874-123-456");

                //Employee
                Employee emp1 = new Employee("Kim","Rumi",Speciality.MACHINERY);
                Employee emp2 = new Employee("Yoon","Mira",Speciality.ELECTRONIC);
                Employee emp3 = new Employee("Park","Zoey",Speciality.COACHBUILDER);

                //Create Vehicle
                Vehicle v1 = new Vehicle("AA-159-BB",vType1,owner1,25000, LocalDate.of(2000,12,25));
                Vehicle v2 = new Vehicle("OUTATIME",vType4,owner2,400,LocalDate.of(1985,10,30));
                Vehicle v3 = new Vehicle("CC-456-TV",vType3,owner1,25,LocalDate.of(199,4,5));
                Vehicle v4 = new Vehicle("DD-789-DD",vType2,owner3,2016,LocalDate.of(2016,8,6));

                //Create Intervention Type
                InterventionType iType1 = new InterventionType("Oil Filtering",InterventionCategory.MAINTENANCE,60,90);
                InterventionType iType2 = new InterventionType("Motor Reparation",InterventionCategory.REPAIR,0,0);
                InterventionType iType3 = new InterventionType("Change Tyres",InterventionCategory.MAINTENANCE,1000,365);

                //Create Intervention
                Intervention i1 = new Intervention(v1,iType1,LocalDate.of(2025,6,6));
                Intervention i2 = new Intervention(v2,iType2,LocalDate.of(2015,11,14));
                Intervention i3 = new Intervention(v3,iType3,LocalDate.now());

                //Set Pice used for intervention
                iType1.addPieceUsed(p1);

                //Set employee for intervention
                i1.setEmployee(emp1);
                i2.setEmployee(emp3);
                i3.setEmployee(emp2);

                //Make intervention
                i2.beginIntervention();
                i3.beginIntervention();
                Intervention i4 = i3.endIntervention();


                //Persis every element
                em.persist(vType1);em.persist(vType2);em.persist(vType3);em.persist(vType4);
                em.persist(owner1); em.persist(owner2); em.persist(owner3);
                em.persist(p1);em.persist(p2);em.persist(p3);em.persist(p4);em.persist(p5);
                em.persist(emp1);em.persist(emp2);em.persist(emp3);
                em.persist(v1);em.persist(v2);em.persist(v3);em.persist(v4);
                em.persist(iType1);em.persist(iType2);em.persist(iType3);
                em.persist(i1);em.persist(i2);em.persist(i3);em.persist(i4);

                et.commit();
            }catch (Exception e){
                System.out.println("Exception "+e);
                System.out.println("Rollback");
                et.rollback();
            }
        }finally {
            if (emf!=null && em.isOpen()){
                em.close();
            }

            if (emf!=null && emf.isOpen()){
                em.close();
            }
        }


    }
}