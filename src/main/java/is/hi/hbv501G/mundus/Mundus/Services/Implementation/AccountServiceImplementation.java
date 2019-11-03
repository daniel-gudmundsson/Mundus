package is.hi.hbv501G.mundus.Mundus.Services.Implementation;

import is.hi.hbv501G.mundus.Mundus.Entities.Account;
import is.hi.hbv501G.mundus.Mundus.Entities.Parent;
import is.hi.hbv501G.mundus.Mundus.Repositories.AccountRepository;
import is.hi.hbv501G.mundus.Mundus.Repositories.PersonRepository;
import is.hi.hbv501G.mundus.Mundus.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import java.util.List;

@Service
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

    @Override
    public Account findAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }

    /**
     * A method for creating an account (Sign up)
     *
     * @param account A new account
     * @param parent  The prent that the account must contain
     * @throws Exception
     */
    @Override
    public void createAccount(Account account, Parent parent) throws Exception {
        Account exists = accountRepository.findAccountByEmail(account.getEmail());
        if (exists != null) {
            throw new AccountException("This email is in use");
        }
        account.setParent(parent);
        parent.setAccount(account);

        accountRepository.save(account);
    }

    /**
     * A method for logging into an account
     *
     * @param email
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public long login(String email, String password) throws Exception {

        Account account = accountRepository.findAccountByEmail(email);

        if (account == null) {
            throw new LoginException("Account not found"); // The acccount doesn't exist
        }

        if (account.getPassword().equals(password)) { // The account exists and the passoword is correct
            return account.getId();
        } else {
            throw new FailedLoginException("Wrong password"); // Incorrect password
        }


    }


}
