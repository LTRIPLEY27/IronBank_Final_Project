package com.ironhack.ironbank_monolit.model.user;

import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.account.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ThirdParty extends User {

    @Size(min = 24, max = 24, message = "the hashed key must be with a 24 length extension")
    private String hashedKey;

    @NotBlank // --> difference between @NotBlank and @NotEmpty??
    @NotNull
    private Money amount;

    @NotBlank
    private long idAccount;

    @NotNull
    private String secretKey;

    public ThirdParty(String name, List<Account> owner, List<Account> secondaryOwner, String secret, String userName, String hashedKey, Money amount, long idAccount, String secretKey) {
        super(name, owner, secondaryOwner, secret, userName);
        this.hashedKey = hashedKey;
        this.amount = amount;
        this.idAccount = idAccount;
        this.secretKey = secretKey;
    }
}
