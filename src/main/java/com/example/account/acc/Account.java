package com.example.account.acc;

import com.example.account.acc.model.NamedEntity;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "account")
public class Account extends NamedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double balance;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<Ops> ops;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    protected Set<Ops> getOpsInternal() {
        if(this.ops == null) {
            this.ops = new HashSet<>();
        }
        return this.ops;
    }

    protected void setOpsInternal(Set<Ops> ops) {
        this.ops = ops;
    }

    public List<Ops> getOps() {
        List<Ops> sortedOps = new ArrayList<>(getOpsInternal());
        PropertyComparator.sort(sortedOps, new MutableSortDefinition("date", true, true));
        return Collections.unmodifiableList(sortedOps);
    }

    public void addOps(Ops ops) {
        if (ops.isNew()) {
            getOpsInternal().add(ops);
        }
        ops.setAccount(this);
    }

    public synchronized void save(Double money) {
        Ops ops = new Ops();
        ops.setAmount(money);
        ops.setDate(LocalDateTime.now());
        ops.setBalance(this.balance);
        this.balance += money;
        this.addOps(ops);
    }

    public synchronized void retrieve(Double money) {
        Ops ops = new Ops();
        ops.setAmount(money);
        ops.setDate(LocalDateTime.now());
        ops.setBalance(this.balance);
        this.balance -= money;
        this.addOps(ops);
    }
}
