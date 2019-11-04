package com.example.paging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button buttonGet, buttonClear;

    StudentDao studentDao;
    StudentsDatabase studentsDatabase;
    private MyPageAdapter myPageAdapter;
    LiveData<PagedList<Student>> allStudentsLivePaged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rercycle_view);
        buttonGet = findViewById(R.id.buttonGet);
        buttonClear = findViewById(R.id.buttonClear);
        myPageAdapter = new MyPageAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myPageAdapter);
        studentsDatabase = StudentsDatabase.getInstance(this);
        studentDao = studentsDatabase.getStudentDao();
        allStudentsLivePaged = new LivePagedListBuilder<>(studentDao.getAllStudents(), 20).build();
        allStudentsLivePaged.observe(this, new Observer<PagedList<Student>>() {
            @Override
            public void onChanged(final PagedList<Student> students) {
                myPageAdapter.submitList(students);
                students.addWeakCallback(null, new PagedList.Callback() {
                    @Override
                    public void onChanged(int position, int count) {
                        Log.d("mytag", "onChanged: "+ students);
                    }

                    @Override
                    public void onInserted(int position, int count) {

                    }

                    @Override
                    public void onRemoved(int position, int count) {

                    }
                });
            }
        });
        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student[] students = new Student[1000];
                for (int i=0;i<1000;i++){
                    Student student = new Student();
                    student.setStudentNumber(i);
                    students[i] = student;
                }
                new InsertAsyncTask(studentDao).execute(students);
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ClearAsyncTask(studentDao).execute();
            }
        });
    }


    static class InsertAsyncTask extends AsyncTask<Student, Void, Void> {
        StudentDao studentDao;

        public InsertAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insertStudents(students);
            return null;
        }
    }

    static class ClearAsyncTask extends AsyncTask<Void, Void, Void> {
        StudentDao studentDao;

        public ClearAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentDao.deleteAllStudents();
            return null;
        }
    }
}
