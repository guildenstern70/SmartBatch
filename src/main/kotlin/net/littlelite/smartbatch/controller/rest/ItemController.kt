package net.littlelite.smartbatch.controller.rest

import net.littlelite.smartbatch.model.Item
import net.littlelite.smartbatch.service.ItemService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/v1/items"])
class ItemController(
        val itemService: ItemService
)
{
    private val logger: Logger = LoggerFactory.getLogger(ItemController::class.java)

    @GetMapping
    fun getAllItems(): ResponseEntity<List<Item>>
    {
        logger.info("GET all items")
        return ResponseEntity<List<Item>>(this.itemService.getAllItems(), HttpStatus.OK)
    }

    @GetMapping("/sum")
    fun getItemSum(): ResponseEntity<Int>
    {
        logger.info("GET items' sum")
        return ResponseEntity<Int>(this.itemService.getTotalItemsValue(), HttpStatus.OK)
    }
}