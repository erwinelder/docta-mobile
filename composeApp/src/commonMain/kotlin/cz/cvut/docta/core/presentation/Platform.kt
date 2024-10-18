package cz.cvut.docta.core.presentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform