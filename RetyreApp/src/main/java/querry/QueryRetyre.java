package querry;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * <h1>QueryRetyre</h1>
 * Used to make queries to the DB
 */
public class QueryRetyre {
    private EntityManagerFactory emf;

    public QueryRetyre(){
        this.emf = Persistence.createEntityManagerFactory("RetyrePU");
    }
}
