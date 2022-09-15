package com.ironhack.ironbank_monolit.dto.accountDTO;

import com.ironhack.ironbank_monolit.model.account.Credit;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreditDTO {

    private Money balance;

    private String secretKey;

    private long primaryOwner;

    private long secondaryOwner;

    private Status status;

    //private long accounts;

    //own by class
    private Money creditLimit;

    private BigDecimal interestRate;


    public static CreditDTO  byObject(Credit credit){

        return new CreditDTO(credit.getBalance(), credit.getSecretKey(), credit.getPrimaryOwner().getId(), credit.getSecondaryOwner().getId(), credit.getStatus(),  credit.getCreditLimit(), credit.getInterestRate());
    }
}
