import Model.SportAppApi

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val api = SportAppApi()
            api.testConnection()
            println(api.getStatus())
        }
    }
}
