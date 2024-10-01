package usecases.account

import hexagonal.example.application.account.CreateAccountCommand
import hexagonal.example.application.account.CreateAccountCommandResult
import hexagonal.example.application.account.CreateAccountUseCase
import hexagonal.example.application.port.out.AccountStore
import hexagonal.example.domain.model.AccountId
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import port.output.ClockService
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

class CreateAccountUseCaseTest {

    @Test
    fun `should create account`() {
        // given
        val clockService = mock(ClockService::class.java)
        doReturn(Clock.fixed(Instant.parse("2024-01-01T00:00:00.00Z"), ZoneId.of("UTC"))).`when`(clockService).clock()

        val accountId = AccountId("33")
        val accountStore = mock(AccountStore::class.java)
        doReturn(accountId).`when`(accountStore).saveAccount(any())

        val useCase = CreateAccountUseCase(clockService, accountStore)

        val command = CreateAccountCommand(100.0)

        // when
        val result = useCase.execute(command)

        // verify
        assertTrue(result is CreateAccountCommandResult.Success)
    }
}
