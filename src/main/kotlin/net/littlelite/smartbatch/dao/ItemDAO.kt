package net.littlelite.smartbatch.dao

import net.littlelite.smartbatch.model.Item
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemDAO : CrudRepository<Item, Int>
