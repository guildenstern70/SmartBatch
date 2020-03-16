package net.littlelite.smartbatch.service

import net.littlelite.smartbatch.dao.ItemDAO
import net.littlelite.smartbatch.model.Item
import org.springframework.stereotype.Service

@Service
class ItemService(
        val itemDAO: ItemDAO)
{

    fun getAllItems(): List<Item>
    {
        return this.itemDAO.findAll().toList()
    }

    fun getTotalItemsValue(): Int
    {
        val items = this.itemDAO.findAll()
        return items
                .map { it.value }
                .reduce { acc, i -> acc + i }
    }
}