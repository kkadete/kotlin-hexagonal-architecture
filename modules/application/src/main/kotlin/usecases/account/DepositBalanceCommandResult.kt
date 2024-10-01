package hexagonal.example.application.account

import hexagonal.example.domain.model.Account

sealed class DepositBalanceCommandResult {
    data class Success(val account: Account) : DepositBalanceCommandResult()
    data object AccountNotFound : DepositBalanceCommandResult()
    data object InsufficientBalance : DepositBalanceCommandResult()
    data object InvalidAmount : DepositBalanceCommandResult()
}
