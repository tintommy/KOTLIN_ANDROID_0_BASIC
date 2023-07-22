package com.tommy.studentregister

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tommy.studentregister.databinding.ListItemBinding
import com.tommy.studentregister.db.Student

class StudentRecyclerViewAdapter(private val clickListener: (Student) -> Unit) : RecyclerView.Adapter<StudentViewHolder>() {

    private val studentList = ArrayList<Student>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding=ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StudentViewHolder(binding)
//        LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(studentList[position], clickListener )
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun setList(students: List<Student>) {
        studentList.clear()
        studentList.addAll(students)
    }

}

class StudentViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(student: Student, clickListener: (Student) -> Unit) {
        binding.apply {
            binding.tvName.text = student.name.toString()
            binding.tvEmail.text = student.email.toString()
            root.setOnClickListener {
                clickListener(student)
            } }

    }

}