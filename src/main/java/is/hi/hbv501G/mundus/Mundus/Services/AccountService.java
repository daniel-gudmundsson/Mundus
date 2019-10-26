package is.hi.hbv501G.mundus.Mundus.Services;

import is.hi.hbv501G.mundus.Mundus.Entities.Account;

import java.util.List;

public interface AccountService {

    Account save(Account account);
    void delete(Account account);
    List<Account> findAll();
    Account findAccountById(long id);

}
