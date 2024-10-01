package hexagonal.example.domain.model

import java.time.OffsetDateTime

class Account(
    val accountId: AccountId,
    var amount: Amount,
    var accountStatus: AccountStatus,
    var createdAt: OffsetDateTime,
    var updatedAt: OffsetDateTime,
)
