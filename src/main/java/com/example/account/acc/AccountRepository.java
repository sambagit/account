package com.example.account.acc;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface AccountRepository extends Repository<Account, Integer> {

    @Query("SELECT DISTINCT account FROM Account account left join fetch account.ops WHERE account.name LIKE :name%")
    @Transactional(readOnly = true)
    Collection<Account> findByName(@Param("name") String name);

    @Query("SELECT account FROM Account account left join fetch account.ops WHERE account.id =:id")
    @Transactional(readOnly = true)
    Account findById(@Param("id") Integer id);

    void save(Account owner);

}
