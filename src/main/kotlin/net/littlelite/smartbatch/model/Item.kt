package net.littlelite.smartbatch.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Item(
        var name: String,
        var value: Int
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = -1
}