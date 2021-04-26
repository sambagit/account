package com.example.account.service;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assertions;
import com.example.account.acc.Account;
import com.example.account.acc.AccountRepository;
import com.example.account.acc.OpsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AccountServiceTests {

    @Autowired
    protected AccountRepository accountRepository;

    @Autowired
    protected OpsRepository opsRepository;

    @BeforeEach
    void setup() {
        accountRepository.deleteAll();
    }

    @Test
    @Transactional
    void shouldInsertAccount() {
        Collection<Account> accounts = this.accountRepository.findByName("rockfeller");
        int found = accounts.size();

        Account account = new Account();
        account.setName("rockfeller");
        this.accountRepository.save(account);
        Assertions.assertThat(account.getId().longValue()).isNotEqualTo(0);

        accounts = this.accountRepository.findByName("rockfeller");
        Assertions.assertThat(accounts.size()).isEqualTo(found + 1);
    }
}
