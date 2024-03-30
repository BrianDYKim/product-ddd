package team.me.jpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExternalJpaApplication

fun main(args: Array<String>) {
    runApplication<ExternalJpaApplication>(*args)
}
