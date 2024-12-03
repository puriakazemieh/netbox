package ir.net_box.test.data.local.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.net_box.test.data.local.db.entity.VideosItemEntity


@ProvidedTypeConverter
class VideoItemConverter {
   private val gson = Gson()

    @TypeConverter
    fun storedStringToMyObjects(data: String?): List<VideosItemEntity>? {
        if (data == null) {
            return null
        }
        val listType = object : TypeToken<List<VideosItemEntity?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun myObjectsToStoredString(myObjects: List<VideosItemEntity>?): String {
        return gson.toJson(myObjects)
    }
}
