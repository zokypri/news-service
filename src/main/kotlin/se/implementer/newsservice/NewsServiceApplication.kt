package se.implementer.newsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NewsServiceApplication

fun main(args: Array<String>) {
	runApplication<NewsServiceApplication>(*args)
	var swaggerUrl = "/news-service/swagger-ui/index.html#/";
	println("Swagger URL: http://localhost:8087$swaggerUrl")
}
