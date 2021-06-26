package id.ac.ubaya.informatika.finaltermproject.view.model

import androidx.room.*

    @Dao
    interface ReportDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAll(vararg report: id.ac.ubaya.informatika.finaltermproject.view.model.Report)

        @Query("SELECT * FROM report")
        suspend fun selectAllReport(): List<id.ac.ubaya.informatika.finaltermproject.view.model.Report>

        @Query("UPDATE report SET calories=:calories WHERE date=:date")
        suspend fun update(date:String, calories:Int)

        @Delete
        suspend fun deleteReport(report: id.ac.ubaya.informatika.finaltermproject.view.model.Report)
    }
