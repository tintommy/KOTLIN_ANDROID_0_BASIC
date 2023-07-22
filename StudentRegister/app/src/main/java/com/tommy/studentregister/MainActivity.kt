package com.tommy.studentregister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tommy.studentregister.databinding.ActivityMainBinding
import com.tommy.studentregister.db.Student
import com.tommy.studentregister.db.StudentDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

//    private lateinit var nameTxt: EditText
//    private lateinit var emailTxt: EditText
//    private lateinit var saveBtn: Button
//    private lateinit var deleteBtn: Button

    private lateinit var viewModel: StudentViewModel
    private lateinit var studenRecyclerView: RecyclerView
    private lateinit var adapter: StudentRecyclerViewAdapter

    private lateinit var selectedStudent: Student
    private var isListItemClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




      /*  nameTxt = findViewById(R.id.etName)
        emailTxt = findViewById(R.id.etEmail)
        saveBtn = findViewById(R.id.btnSave)
        deleteBtn = findViewById(R.id.btnDelete)
        studenRecyclerView = findViewById(R.id.rvStudent)*/

        val dao = StudentDatabase.getInstance(application).studentDao()
        val factory = StudentViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(StudentViewModel::class.java)

        binding.apply {


            btnSave.setOnClickListener {
                if(isListItemClicked){
                    updateStudentData()

                }else{
                    saveStudent()
                }
                clearInput()

            }

            btnDelete.setOnClickListener{
                if(isListItemClicked){
                    deleteStudentData()

                }
                clearInput()
            }

            initRecyclerView()
        }


    }

    private fun saveStudent() {
        /*   val name=nameTxt.text.toString()
           val email=emailTxt.text.toString()
           val student=Student(0,name,email)
           viewModel.insertStudent(student)*/

        viewModel.insertStudent(
            Student(0, binding.etName.text.toString(), binding.etEmail.text.toString())
        )
    }
    private fun updateStudentData(){
        viewModel.updateStudent(
            Student(
                selectedStudent.id, binding.etName.text.toString(), binding.etEmail.text.toString()
            )
        )

        binding.btnSave.text = "Lưu"
        binding.btnDelete.text = "Xoá"
        isListItemClicked = false
    }

    private fun deleteStudentData(){
        viewModel.deleteStudent(
            Student(
                selectedStudent.id, binding.etName.text.toString(), binding.etEmail.text.toString()
            )
        )

        binding.btnSave.text = "Lưu"
        binding.btnDelete.text = "Xoá"
        isListItemClicked = false
    }

    private fun clearInput() {
        binding.etName.setText("")
        binding.etEmail.setText("")
    }




    private fun initRecyclerView() {
       binding.rvStudent.layoutManager = LinearLayoutManager(this)
        adapter = StudentRecyclerViewAdapter { selectedItem: Student ->
            listItemClicked(selectedItem)
        }
        binding.rvStudent.adapter = adapter
        displayStudentList()

    }

    private fun displayStudentList() {
        viewModel.students.observe(this, {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(student: Student) {
        /*  Toast.makeText(this,"${student.name}",Toast.LENGTH_SHORT).show()*/
        selectedStudent = student
        binding.btnSave.text = "Cập Nhật"
        binding.btnDelete.text = "Xoá"
        isListItemClicked = true
        binding.etName.setText(selectedStudent.name)
        binding.etEmail.setText(selectedStudent.email)
    }
}