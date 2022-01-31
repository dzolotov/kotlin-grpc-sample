import io.grpc.ManagedChannelBuilder

suspend fun main() {
    val port = 50051

    val channel = ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build()
    val stub = RegistrationServiceGrpcKt.RegistrationServiceCoroutineStub(channel)
    val data = user {
        lastname = "Ivanov"
        firstname = "Petr"
        middlename = "Sidorovich"
        age = 23
        gender = Register.User.Gender.MALE
    }
    val result = stub.register(data)
    print("Success is ${result.succeeded}")
}