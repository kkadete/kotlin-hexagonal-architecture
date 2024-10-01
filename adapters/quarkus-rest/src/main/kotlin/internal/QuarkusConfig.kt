package hexagonal.example.internal

import hexagonal.example.adapters.inmemory.InMemoryAccountStore
import hexagonal.example.application.account.CreateAccountUseCase
import hexagonal.example.application.account.DepositBalanceUseCase
import hexagonal.example.application.account.GetAccountByIdUseCase
import hexagonal.example.application.port.out.AccountStore
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.context.Dependent
import jakarta.enterprise.inject.Produces
import port.output.ClockService
import java.time.Clock

@Dependent
class HexagonalPortProducer {

    @ApplicationScoped
    @Produces
    fun producesGetAccountById(accountStore: AccountStore): GetAccountByIdUseCase {
        return GetAccountByIdUseCase(accountStore)
    }

    @ApplicationScoped
    @Produces
    fun producesCreateAccountUseCase(clockService: ClockService, accountStore: AccountStore): CreateAccountUseCase {
        return CreateAccountUseCase(clockService, accountStore)
    }

    @ApplicationScoped
    @Produces
    fun producesDepositBalanceUseCase(clockService: ClockService, accountStore: AccountStore): DepositBalanceUseCase {
        return DepositBalanceUseCase(clockService, accountStore)
    }

    @ApplicationScoped
    @Produces
    fun producesAccountStore(): AccountStore {
        return InMemoryAccountStore()
    }

    @ApplicationScoped
    @Produces
    fun producesClockService(): ClockService {
        return ClockService { Clock.systemUTC() }
    }
}
