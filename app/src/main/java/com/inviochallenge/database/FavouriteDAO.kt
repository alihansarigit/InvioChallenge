package com.inviochallenge.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.inviochallenge.model.Product.Meal

@Dao
interface FavouriteDAO {
    @Query("Select * from Meal")
    fun getFavouriteFood(): LiveData<List<Meal>>

    @Query("select * from Meal where id = :mealID")
    fun getFavouriteFoodById(mealID: String): Meal?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg meal: Meal)

    @Delete
    suspend fun delete(meal: Meal)

}