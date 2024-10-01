package hexagonal.example.domain.model

class Amount(
    val value: Double,
) {
    operator fun plus(amount: Amount): Amount {
        return Amount(this.value + amount.value)
    }
}
