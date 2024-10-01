package hexagonal.architecture.example.quarkus

class GetAccountResponse(
    val accountId: String,
    val amount: Double,
    val accountStatus: String,
    val createdAt: String,
)
