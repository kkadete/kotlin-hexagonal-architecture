package hexagonal.example.quarkus

import hexagonal.architecture.example.quarkus.DepositBalanceRequest
import hexagonal.example.application.account.*
import hexagonal.example.domain.model.AccountId
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import jakarta.ws.rs.core.Response

@Path("/account")
class AccountResource(
    private val createAccount: CreateAccountUseCase,
    private val depositBalance: DepositBalanceUseCase,
    private val getAccountById: GetAccountByIdUseCase,
) {
    @GET
    @Path("/{accountId}")
    @Produces(APPLICATION_JSON)
    suspend fun getAccount(@PathParam("accountId") accountId: String): Response {
        // validate the input

        // create a command
        val command = GetAccountQuery(AccountId(accountId))

        // handle the command
        val result = getAccountById.execute(command)

        // handle the result
        return when (result) {
            is GetAccountQueryResult.Success -> {
                // mao to response object
                val response = result.account.toGetAccountResponse()

                Response.ok(response).build()
            }

            GetAccountQueryResult.AccountNotFound -> {
                Response.status(Response.Status.NOT_FOUND).build()

                // or throw Exception and use the default Quarkus exception handling for NotFound exception
            }
        }
    }

    @POST
    @Produces(APPLICATION_JSON)
    suspend fun createAccount(request: CreateAccountRequest): Response {
        val command = request.toCreateAccountCommand()

        val result = createAccount.execute(command)

        return when (result) {
            is CreateAccountCommandResult.Success -> {
                val response = result.accountId.toCreateAccountResponse()

                Response.status(Response.Status.CREATED).entity(response).build()
            }

            CreateAccountCommandResult.InvalidAmount -> {
                Response.status(Response.Status.NOT_FOUND).build()
            }
        }
    }

    @PUT
    @Path("/{accountId}/deposit")
    @Produces(APPLICATION_JSON)
    suspend fun depositBalance(@PathParam("accountId") accountId: String, request: DepositBalanceRequest): Response {
        val command = request.toDepositBalanceCommand(accountId)

        val result = depositBalance.execute(command)

        return when (result) {
            is DepositBalanceCommandResult.Success -> {
                val response = result.account.toDepositBalanceResponse()
                Response.ok(response).build()
            }

            DepositBalanceCommandResult.InsufficientBalance -> {
                Response.status(Response.Status.BAD_REQUEST).build()
            }

            DepositBalanceCommandResult.InvalidAmount -> {
                Response.status(Response.Status.BAD_REQUEST).build()
            }

            DepositBalanceCommandResult.AccountNotFound -> {
                Response.status(Response.Status.NOT_FOUND).build()
            }
        }
    }
}
