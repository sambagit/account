package com.example.account.acc;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface OpsRepository extends Repository<Ops, Integer> {

    @Transactional(readOnly = true)
    Ops findById(Integer id);

    void save(Ops ops);
}
