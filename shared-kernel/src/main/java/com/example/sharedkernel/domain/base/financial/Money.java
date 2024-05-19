package com.example.sharedkernel.domain.base.financial;

import com.example.sharedkernel.domain.base.ValueObject;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Objects;

@Embeddable
@Getter
public class Money implements ValueObject {

    @Enumerated(value = EnumType.STRING)
    private final Currency currency;
    private final double amount;
    protected Money(){
        this.currency=null;
        this.amount=0.0;
    }
    public  Money(@NotNull Currency currency,@NotNull double amount){
        this.amount=amount;
        this.currency=currency;
    }
    public static Money valueOf(Currency currency,double amount){
        return new Money(currency,amount);
    }
    public Money add(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(currency,amount + money.amount);
    }

    public Money subtract(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot subtract two Money objects with different currencies");
        }
        return new Money(currency,amount - money.amount);
    }

    public Money multiply(int m)  {
        return new Money(currency,amount*m);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount && currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }



}
