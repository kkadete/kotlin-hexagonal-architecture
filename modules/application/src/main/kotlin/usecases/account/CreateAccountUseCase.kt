package hexagonal.example.application.account

import hexagonal.example.application.port.out.AccountStore
import hexagonal.example.domain.model.Account
import hexagonal.example.domain.model.AccountId
import hexagonal.example.domain.model.AccountStatus
import hexagonal.example.domain.model.Amount
import port.output.ClockService
import java.time.OffsetDateTime

class CreateAccountUseCase(
    private val clockService: ClockService,
    private val accountStore: AccountStore,
) {
    fun execute(command: CreateAccountCommand): CreateAccountCommandResult {
        val amount = command.amount
        if (amount < 0) {
            return CreateAccountCommandResult.InvalidAmount
        }

        val accountId = generateAccountId()

        val account = Account(
            accountId = accountId,
            amount = Amount(amount),
            accountStatus = AccountStatus.ACTIVE,
            createdAt = OffsetDateTime.now(clockService.clock()),
            updatedAt = OffsetDateTime.now(clockService.clock()),
        )

        val result = accountStore.saveAccount(account)

        return CreateAccountCommandResult.Success(result)
    }

    private fun generateAccountId(): AccountId {
        return AccountId(nextSequence().toString())
    }

    companion object {
        var sequence: Int = 0

        fun nextSequence(): Int {
            return ++sequence
        }
    }
}
