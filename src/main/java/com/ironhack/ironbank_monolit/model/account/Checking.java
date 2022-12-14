package com.ironhack.ironbank_monolit.model.account;
import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import com.ironhack.ironbank_monolit.validation.Operations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Checking extends Account{


    //@DecimalMin(value = "250")
   // private Money MINIMAL_BALANCE;
    @Transient
    private Money MINIMAL_BALANCE = new Money(new BigDecimal("250"));
    @Transient
    private final Money MONTHLY_MAINTENANCE_FEE = new Money(new BigDecimal("12"));

    public Checking(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status);
    }

    public static Checking byDTO(CheckingDTO checkingDTO, User primaryOwner, User Secondary) {

        return new Checking(checkingDTO.getBalance(), checkingDTO.getSecretKey(), primaryOwner, Secondary, checkingDTO.getStatus()/*, checkingDTO.getSend(), checkingDTO.getReceive()*/);
    }


    @Override
    public void setBalance(Money balance){
        //call to set the super attributte AND THE SETTING THE BALANCE WITH THE PENALTYFEE CHECKING FOR EVERY CLASS

        monthlyDeduction();
        super.setBalance(balance);
        super.penaltyFeeChecker(MINIMAL_BALANCE);

    }

   public void monthlyDeduction(){

        Date deduction = (Date) getCreationDate().clone();
        deduction.setMonth(deduction.getMonth() + 1);

        if(deduction == new Date()){
            super.setBalance(new Money(getBalance().decreaseAmount(MONTHLY_MAINTENANCE_FEE)));
        }
    }

}
