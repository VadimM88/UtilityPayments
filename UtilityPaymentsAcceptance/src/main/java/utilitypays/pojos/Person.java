package utilitypays.pojos;

import utilitypays.entity.Account;

public interface Person {
    String getName();

    long getBalance();

    void setBalance(long balance);

    static Person getPerson(Account acc){
        return acc.getLegalPerson() == null ? acc.getPhysicalPerson() : acc.getLegalPerson();
    }

}