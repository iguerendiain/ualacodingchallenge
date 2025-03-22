package ignacio.guerendiain.uala.core.storage

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ignacio.guerendiain.uala.core.storage.dao.CityDao
import ignacio.guerendiain.uala.core.storage.model.CityDB

@Database(entities = [CityDB::class], version = 1)
abstract class MainDB : RoomDatabase() {
    abstract fun cityDao(): CityDao
}

fun buildRoomDB(app: Application): MainDB {
    return Room.databaseBuilder(
        app,
        MainDB::class.java, "maindb"
    ).build()
}