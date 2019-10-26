package is.hi.hbv501G.mundus.Mundus.Services.Implementation;

import is.hi.hbv501G.mundus.Mundus.Entities.Account;
import is.hi.hbv501G.mundus.Mundus.Repositories.AccountRepository;
import is.hi.hbv501G.mundus.Mundus.Repositories.PersonRepository;
import is.hi.hbv501G.mundus.Mundus.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountServiceImplementation implements AccountService {

    AccountRepository accountRepository;
    PersonRepository personRepository;


    @Autowired
    public AccountServiceImplementation(AccountRepository accountRepository, PersonRepository personRepository) {
        this.accountRepository = accountRepository;
        this.personRepository = personRepository;
    }


    @Override
    public Account save(Account account) {
        return this.accountRepository.save(account);
    }

    @Override
    public void delete(Account account) {
        this.accountRepository.delete(account);
    }

    @Override
    public List<Account> findAll() {
        return this.accountRepository.findAll();
    }

    @Override
    public Account findAccountById(long id) {
        return this.accountRepository.findAccountById(id);
    }
}
