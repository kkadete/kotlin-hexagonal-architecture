package hexagonal.example.application.account

import hexagonal.example.domain.model.AccountId

class GetAccountQuery(
    val accountId: AccountId
)