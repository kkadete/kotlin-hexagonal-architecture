package hexagonal.example.application.account

import hexagonal.example.domain.model.Account

sealed class GetAccountQueryResult {
    data class Success(val account: Account) : GetAccountQueryResult()
    data object AccountNotFound : GetAccountQueryResult()
}
