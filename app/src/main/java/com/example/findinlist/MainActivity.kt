package com.example.findinlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchBar: EditText
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBar = findViewById(R.id.searchBar)
        recyclerView = findViewById(R.id.recyclerView)

        // Tạo danh sách sinh viên mẫu
        studentList = listOf(
            Student("Pham Huu Phuc", "20215119"),
            Student("Lieu Nhat Minh", "20215089"),
            Student("Ronaldo", "20215000"),
            Student("Messi", "20211037")
        )

        studentAdapter = StudentAdapter(studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        // Thêm TextWatcher để lọc danh sách khi người dùng nhập từ khóa
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    // Hàm filter để lọc danh sách
    private fun filter(text: String) {
        val filteredList = if (text.length > 2) {
            studentList.filter {
                it.name.contains(text, ignoreCase = true) || it.id.contains(text, ignoreCase = true)
            }
        } else {
            studentList
        }
        studentAdapter.filterList(filteredList)
    }
}
