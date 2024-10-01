package hexagonal.example.application.port.out

import hexagonal.example.domain.model.Account
import hexagonal.example.domain.model.AccountId

interface AccountStore {

    fun getAccountById(accountId: AccountId): Account?

    fun saveAccount(account: Account): AccountId

}
