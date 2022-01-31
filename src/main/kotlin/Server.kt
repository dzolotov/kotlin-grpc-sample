import io.grpc.ServerBuilder

private class RegistrationService : RegistrationServiceGrpcKt.RegistrationServiceCoroutineImplBase() {
    override suspend fun register(request: Register.User): Register.RegistrationResult {
        print("Registering user ${request.lastname} ${request.firstname} ${request.middlename}, age: ${request.age}, gender: ${request.gender.name}")
        return registrationResult { succeeded=true }
    }
}

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 50051
    val server = ServerBuilder
        .forPort(port)
        .addService(RegistrationService())
        .build()
    server.start()
    Runtime.getRuntime().addShutdownHook(Thread {
        println("Shutdown server")
        server.shutdown()
    })
    //wait for connection until shutdown
    server.awaitTermination()
}