package hexagonal.example.application.account

import hexagonal.example.domain.model.AccountId

class DepositBalanceCommand(
    val accountId: AccountId,
    val amount: Double,
)