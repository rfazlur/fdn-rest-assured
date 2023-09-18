package network

class RegionPath {
    fun setEndpoint(caseName: String): String {
        val endpoint: String = caseName.replace(" ", "/")
        return "/$endpoint"
    }
}
