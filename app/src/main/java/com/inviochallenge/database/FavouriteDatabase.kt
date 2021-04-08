package com.inviochallenge.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.inviochallenge.model.Product.Meal

@Database(entities = [Meal::class], version = 2)
abstract class FavouriteDatabase : RoomDatabase() {

    abstract fun favouriteDao(): FavouriteDAO


//    companion object{
//        @Volatile private var instance:FavouriteDatabase?=null
//
//        private val lock = Any()
//
//        operator fun invoke(context:Context) = instance ?: synchronized(lock){
//            instance?: makeDatabase(context).also{
//                instance = it
//            }
//        }
//
//        private fun makeDatabase(context: Context) = Room.databaseBuilder(
//            context.applicationContext, FavouriteDatabase::class.java,"FavouriteDatabase")
//            .build()
//    }
}