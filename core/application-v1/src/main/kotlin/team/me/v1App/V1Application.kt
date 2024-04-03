package team.me.v1App

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["team.me.domain", "team.me.jpa"])
class V1Application

fun main(args: Array<String>) {
    runApplication<V1Application>(*args)
}
