
package com.example.demo;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class EmployeeLifeCycleService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void demoLifecycle() {

        System.out.println("========== 1) TRANSIENT ==========");
        Employee e = new Employee("Arjun", 50000);
        System.out.println("ID before persist: " + e.getId());
        System.out.println("Is managed? " + em.contains(e)); // false

        System.out.println("\n========== 2) PERSIST (MANAGED) ==========");
        em.persist(e); 
        System.out.println("ID after persist: " + e.getId()); 
        System.out.println("Is managed? " + em.contains(e)); 

       
        e.setSalary(55000);

        
        em.flush();
        System.out.println("Flushed changes (INSERT + UPDATE may happen depending on provider)");

        System.out.println("\n========== 3) DETACH ==========");
        em.detach(e); 
        System.out.println("Is managed? " + em.contains(e)); 

        
        e.setSalary(99999);
        em.flush(); 
        System.out.println("Changed salary after detach (DB should NOT change)");

        System.out.println("\n========== 4) MERGE (DETACHED -> MANAGED COPY) ==========");
        Employee managedCopy = em.merge(e);
        System.out.println("Is original managed? " + em.contains(e)); 
        System.out.println("Is merged copy managed? " + em.contains(managedCopy)); 

        managedCopy.setSalary(70000);
        em.flush(); 
        System.out.println("Merged copy updated salary to 70000 (DB WILL change)");

        System.out.println("\n========== 5) REMOVE ==========");
        em.remove(managedCopy); 
        em.flush();
        System.out.println("Deleted employee from DB");
    }
}
