package hexagonal.architecture.example.quarkus

import hexagonal.example.domain.model.AccountId
import hexagonal.example.domain.model.AccountStatus
import hexagonal.example.domain.model.Amount
import java.time.OffsetDateTime

class DepositBalanceResponse (
    val accountId: AccountId,
    val amount: Amount,
    val accountStatus: AccountStatus,
    val createdAt: OffsetDateTime,
)
