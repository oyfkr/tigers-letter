package chat.tigersnews

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TigersNewsApplication

fun main(args: Array<String>) {
	runApplication<TigersNewsApplication>(*args)
}
