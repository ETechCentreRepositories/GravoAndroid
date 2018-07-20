package com.greenravolution.gravo.objects;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

@Database(entities = {Notification.class}, version = 1,  exportSchema = false)
public abstract class NotificationRoomDatabase extends RoomDatabase {

//    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE notification_table "
//                    + " ADD COLUMN time LONG");
//        }
//    };

    public abstract NotificationDao notificationDao();

    private static NotificationRoomDatabase INSTANCE;

    static NotificationRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotificationRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotificationRoomDatabase.class, "notification_database")
                            //.addMigrations(MIGRATION_1_2)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}
