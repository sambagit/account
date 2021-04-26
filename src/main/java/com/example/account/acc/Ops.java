package com.example.account.acc;

import com.example.account.acc.model.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ops")
public class Ops extends BaseEntity {

    private Double amount;
    private Double balance;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Ops{" +
                " amount=" + amount +
                ", balance=" + balance +
                ", date=" + date +
                ", account_id=" + account +
                '}';
    }
}
