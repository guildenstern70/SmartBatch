package net.littlelite.smartbatch.service

import net.littlelite.smartbatch.dao.ItemDAO
import net.littlelite.smartbatch.model.Item
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class DbInitializer(
        val itemDAO: ItemDAO) : ApplicationRunner
{
    protected val logger = LoggerFactory.getLogger(DbInitializer::class.java)

    @Throws(Exception::class)
    override fun run(args: ApplicationArguments)
    {
        val randomValues = List(100) { Random.nextInt(0, 10000) }
        logger.info("Populating Database...")
        randomValues.forEach {
            val item = Item("Item #${it}", it)
            this.itemDAO.save(item)
        }
        logger.info("Done.")
    }

}