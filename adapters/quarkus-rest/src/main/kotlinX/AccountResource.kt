package hexagonal.example.quarkus

import hexagonal.architecture.example.quarkus.DepositBalanceRequest
import hexagonal.architecture.example.quarkus.DepositBalanceResponse
import hexagonal.architecture.example.quarkus.GetAccountResponse
import hexagonal.example.application.account.CreateAccountCommand
import hexagonal.example.application.account.DepositBalanceCommand
import hexagonal.example.domain.model.Account
import hexagonal.example.domain.model.AccountId

fun Account.toGetAccountResponse(): GetAccountResponse {
    return GetAccountResponse(
        accountId = this.accountId.value,
        amount = this.amount.value,
        accountStatus = this.accountStatus.name,
        createdAt = this.createdAt.toString()
    )
}

fun AccountId.toCreateAccountResponse(): CreateAccountResponse {
    return CreateAccountResponse(
        accountId = this
    )
}

fun CreateAccountRequest.toCreateAccountCommand(): CreateAccountCommand {
    return CreateAccountCommand(
        amount = amount
    )
}

fun DepositBalanceRequest.toDepositBalanceCommand(accountId: String): DepositBalanceCommand {
    return DepositBalanceCommand(
        accountId = AccountId(accountId),
        amount = this.amount
    )
}

fun Account.toDepositBalanceResponse(): DepositBalanceResponse {
    return DepositBalanceResponse(
        accountId = this.accountId,
        amount = this.amount,
        accountStatus = this.accountStatus,
        createdAt = this.createdAt,
    )
}
