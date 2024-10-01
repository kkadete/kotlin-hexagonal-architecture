package hexagonal.example.application.account

import hexagonal.example.application.port.out.AccountStore
import hexagonal.example.domain.model.Amount
import port.output.ClockService
import java.time.OffsetDateTime

class DepositBalanceUseCase(
    private val clockService: ClockService,
    private val accountStore: AccountStore,
) {
    fun execute(command: DepositBalanceCommand): DepositBalanceCommandResult {
        if (command.amount < 0) {
            return DepositBalanceCommandResult.InvalidAmount
        }

        val account = accountStore.getAccountById(command.accountId)
        if (account == null) {
            return DepositBalanceCommandResult.AccountNotFound
        }

        if (account.amount.value < command.amount) {
            return DepositBalanceCommandResult.InsufficientBalance
        }

        account.amount += Amount(command.amount)
        account.updatedAt = OffsetDateTime.now(clockService.clock())

        accountStore.saveAccount(account)

        return DepositBalanceCommandResult.Success(account)
    }
}
