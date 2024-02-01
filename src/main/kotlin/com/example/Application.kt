package com.example

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.statuspages.*

fun Application.module() {

    intercept(ApplicationCallPipeline.Fallback) {
        if (call.isHandled) return@intercept
        val status = call.response.status() ?: HttpStatusCode.NotFound
        call.respond(status)
    }

    install(StatusPages) {
        status(HttpStatusCode.NotFound) { call, status ->
            call.respondText(text = "404: Page Not Found", status = status)
        }
    }

    routing {
        get("/") {
            call.respondRedirect("main")
        }

        route("main") {
            get {
                call.respondText("Hello, world!")
            }
        }
    }
}
