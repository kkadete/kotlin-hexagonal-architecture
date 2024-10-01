package hexagonal.example.adapters.inmemory

import hexagonal.example.application.port.out.AccountStore
import hexagonal.example.domain.model.Account
import hexagonal.example.domain.model.AccountId

class InMemoryAccountStore : AccountStore {
    private val accounts = mutableMapOf<AccountId, Account>()

    override fun getAccountById(accountId: AccountId): Account? {
        return accounts[accountId]
    }

    override fun saveAccount(account: Account): AccountId {
        accounts[account.accountId] = account
        return account.accountId
    }
}
