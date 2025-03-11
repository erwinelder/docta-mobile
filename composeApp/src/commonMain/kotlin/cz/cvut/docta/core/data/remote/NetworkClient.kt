package cz.cvut.docta.core.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

expect val httpClient: HttpClient

const val doctaBackendUrl = "https://docta-backend-adh0f0hsebb5epg7.northeurope-01.azurewebsites.net"