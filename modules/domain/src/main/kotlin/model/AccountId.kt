package hexagonal.example.domain.model

@JvmInline
value class AccountId(val value: String){
    init {
        require(value.isNotBlank()) { "AccountId cannot be blank" }
    }
}
