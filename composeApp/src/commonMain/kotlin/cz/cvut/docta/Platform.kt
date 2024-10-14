package cz.cvut.docta

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform