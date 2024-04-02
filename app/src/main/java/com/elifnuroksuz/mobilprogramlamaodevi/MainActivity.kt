package com.elifnuroksuz.mobilprogramlamaodevi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


open class Item(var name: String, var price: Double)

class Food(name: String, price: Double, var weight: Double) : Item(name, price)

class Cloth(name: String, price: Double, var type: String) : Item(name, price)

class ShoppingList {
    private val items = mutableListOf<Item>()

    fun addItem(item: Item) {
        items.add(item)
        println("\"${item.name}\" was successfully added.")
    }

    fun displayItems() {
        println("Your shopping list:")
        items.forEachIndexed { index, item ->
            when (item) {
                is Food -> println("${index + 1}. ${item.name}, ${item.price}₺, ${item.weight}kg")
                is Cloth -> println("${index + 1}. ${item.name}, ${item.price}₺, ${item.type}")
            }
        }
    }

    fun deleteItem(index: Int) {
        if (index in 1..items.size) {
            val item = items.removeAt(index - 1)
            println("\"${item.name}\" was removed from the list.")
        } else {
            println("Invalid index!")
        }
    }
}

fun readDouble(): Double {
    while (true) {
        val input = readLine()
        if (input != null) {
            val doubleValue = input.toDoubleOrNull()
            if (doubleValue != null) {
                return doubleValue
            }
        }
        println("Invalid entry! Please enter a number:")
    }
}

fun main() {
    val shoppingList = ShoppingList()
    while (true) {
        println("1) Add Item\n2) Display Item\n3) Delete Item\n4) Exit")
        when (readLine()?.toIntOrNull()) {
            1 -> {
                println("Choose the product type (1: Food, 2: Cloth)")
                when (readLine()?.toIntOrNull()) {
                    1 -> {
                        println("Enter the name of the Food:")
                        val name = readLine() ?: ""
                        println("Enter the price of the Food:")
                        val price = readDouble()
                        println("Enter the weight of the Food:")
                        val weight = readDouble()
                        shoppingList.addItem(Food(name, price, weight))
                    }
                    2 -> {
                        println("Enter the name of the Cloth:")
                        val name = readLine() ?: ""
                        println("Enter the price of the Cloth:")
                        val price = readDouble()
                        println("Enter the type of the Cloth:")
                        val type = readLine() ?: ""
                        shoppingList.addItem(Cloth(name, price, type))
                    }
                    else -> println("Invalid selection!")
                }
            }
            2 -> shoppingList.displayItems()
            3 -> {
                println("Enter the number of the product you want to delete:")
                val index = readLine()?.toIntOrNull()
                if (index != null) {
                    shoppingList.deleteItem(index)
                } else {
                    println("Invalid index!")
                }
            }
            4 -> return
            else -> println("Invalid selection!")
        }
    }
}
