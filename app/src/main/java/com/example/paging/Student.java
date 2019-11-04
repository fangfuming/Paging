package com.example.paging;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private  int id;

    @ColumnInfo(name = "student_number")
    private int StudentNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentNumber() {
        return StudentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        StudentNumber = studentNumber;
    }
}
