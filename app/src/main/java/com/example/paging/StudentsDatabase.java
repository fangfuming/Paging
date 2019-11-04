package com.example.paging;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class},version = 1,exportSchema = false)
/**
 * 这里做了两件事
 * 1.使用单例模式初始化StudentsDatabase的实例
 * 2.定义抽象方法返回dao对象(StudentDao)
 */
public abstract class StudentsDatabase extends RoomDatabase {
    private static StudentsDatabase instance ;
    static  synchronized StudentsDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,StudentsDatabase.class,"students_database").build();
        }
        return  instance;
    };

    abstract  StudentDao getStudentDao();

}
