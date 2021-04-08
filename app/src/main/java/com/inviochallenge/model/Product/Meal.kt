package com.inviochallenge.model.Product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Meal")
data class Meal(
    @PrimaryKey @ColumnInfo(name="id") val idMeal: String,
    @ColumnInfo(name="strMeal")  val strMeal: String,
    @ColumnInfo(name="strMealThumb")  val strMealThumb: String,
    val strInstructions: String? = null,
    val strArea: String ? = null,
    val strCategory: String ? = null,
)