package net.littlelite.smartbatch

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SmartbatchApplication

const val VERSION = "0.1.0"
var logger: Logger = LoggerFactory.getLogger("main")

fun hello()
{
	logger.info("*****************************************************************")
	logger.info("  SmartREST v.$VERSION")
	logger.info("  Running on http://localhost:8080 (JVM " + System.getProperty("java.version") + ")")
	logger.info("*****************************************************************")
}

fun main(args: Array<String>)
{
	runApplication<SmartbatchApplication>(*args)
	hello()
}
