package com.ironhack.ironbank_monolit.model.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.ironbank_monolit.dto.accountDTO.SavingDTO;
import com.ironhack.ironbank_monolit.model.enums.InterestType;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import com.ironhack.ironbank_monolit.validation.Operations;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Saving extends Account {

    @DecimalMin(value = "0.0", message = "minimun interest rate")
    @DecimalMax(value = "0.5", message = "max interest rate is 0.5")
    private BigDecimal interestRate = new BigDecimal("0.0025");

    @Transient
    private Money MINIMUM_BALANCE = new Money(new BigDecimal("100"));

    @Transient
    private Money DEFAULT_BALANCE = new Money(new BigDecimal("1000"));

    private BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal("0.0025");

    private BigDecimal MAXIMUM_INTEREST_RATE = new BigDecimal("0.5");

    // admin constructor


    public Saving(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status);

        //setInterestRate(interestRate);

        if (interestRate.compareTo(MAXIMUM_INTEREST_RATE) > 0) {
            this.interestRate = MAXIMUM_INTEREST_RATE;
        } else if (interestRate.compareTo(DEFAULT_INTEREST_RATE) < 0) {
            this.interestRate = DEFAULT_INTEREST_RATE;
        } else {
            this.interestRate = interestRate;
        }


    }

    //user constructor, every values are by default
    /*public Saving(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, User accounts) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status, accounts);
        super.setBalance(MINIMUM_BALANCE);
        this.interestRate = getInterestRate();
    }*/

    public static Saving byDTO(SavingDTO savingDTO, User primaryOwner, User secondaryOwner) {

        return new Saving(savingDTO.getBalance(), savingDTO.getSecretKey(), primaryOwner, secondaryOwner, savingDTO.getStatus(),/*savingDTO.getSend(), savingDTO.getReceive(), */savingDTO.getInterestRate());
    }


    @Override
    public void setBalance(Money balance) {

        if (balance.getAmount().compareTo(DEFAULT_BALANCE.getAmount()) > 0) {
            this.balance = DEFAULT_BALANCE;
        } else {
            this.balance = balance;
        }

        super.setBalance(balance);
        super.addInterestRate(interestRate, InterestType.ANNUALLY);
        penaltyFeeChecker(MINIMUM_BALANCE);

    }

}