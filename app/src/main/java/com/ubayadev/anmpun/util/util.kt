import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubayadev.anmpun.model.HabitDatabase

val DB_NAME = "habitdb"
fun buildDb(context: Context): HabitDatabase {
    val db = HabitDatabase(context)
    return db
}

