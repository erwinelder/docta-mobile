package cz.cvut.docta.presentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform