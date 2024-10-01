package hexagonal.example.application.account

import hexagonal.example.domain.model.AccountId

sealed class CreateAccountCommandResult {
    data class Success(val accountId: AccountId) : CreateAccountCommandResult()
    data object InvalidAmount : CreateAccountCommandResult()
}
