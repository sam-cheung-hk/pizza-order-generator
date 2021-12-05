package com.challenge.order.generator.repository;

import com.challenge.order.generator.bean.db.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, String> {

    /**
     * Find {@link Pizza} by name
     *
     * @param name pizza name
     * @return target {@link Pizza}, NULL if no match was found
     */
    Pizza findByName(String name);

}
