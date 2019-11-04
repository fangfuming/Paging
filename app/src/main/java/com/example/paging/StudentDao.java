package com.example.paging;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


@Dao
public interface StudentDao {
    @Insert
    void insertStudents(Student ...students);

    @Query("delete from student_table")
    void  deleteAllStudents();

//    以前返回LiveData的写法
//    @Query("SELECT * FROM student_table ORDER BY id")
//    LiveData<List<Student>> getAllStudentsLive();

    @Query("SELECT * FROM student_table ORDER BY id")
    DataSource.Factory<Integer,Student> getAllStudents();
}
