package com.ironhack.iroronbank_monolit.model.account;

import com.ironhack.iroronbank_monolit.model.enums.Status;
import com.ironhack.iroronbank_monolit.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class StudentChecking extends Account {

    public StudentChecking(BigDecimal balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, BigDecimal penaltyFee, Date creationDate, Date interestDate, Date transactionDate, User accounts) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status, penaltyFee, creationDate, interestDate, transactionDate, accounts);
    }
}
