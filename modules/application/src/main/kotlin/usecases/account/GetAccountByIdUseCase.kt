package hexagonal.example.application.account

import hexagonal.example.application.port.out.AccountStore
import hexagonal.example.framework.UseCase

@UseCase
class GetAccountByIdUseCase(
    private val accountStore: AccountStore,
) {
    fun execute(command: GetAccountQuery): GetAccountQueryResult {
        val accountId = command.accountId

        val account = accountStore.getAccountById(accountId)

        return when (account) {
            null -> GetAccountQueryResult.AccountNotFound
            else -> GetAccountQueryResult.Success(account)
        }
    }
}
