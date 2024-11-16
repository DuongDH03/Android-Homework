package vn.edu.hust.studentman

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

  private lateinit var studentAdapter: StudentAdapter
  private val students = mutableListOf(
    StudentModel("Nguyễn Văn An", "SV001"),
    StudentModel("Trần Thị Bảo", "SV002"),
    StudentModel("Lê Hoàng Cường", "SV003"),
    StudentModel("Phạm Thị Dung", "SV004"),
    StudentModel("Đỗ Minh Đức", "SV005"),
    StudentModel("Vũ Thị Hoa", "SV006"),
    StudentModel("Hoàng Văn Hải", "SV007"),
    StudentModel("Bùi Thị Hạnh", "SV008"),
    StudentModel("Đinh Văn Hùng", "SV009"),
    StudentModel("Nguyễn Thị Linh", "SV010"),
    StudentModel("Phạm Văn Long", "SV011"),
    StudentModel("Trần Thị Mai", "SV012"),
    StudentModel("Lê Thị Ngọc", "SV013"),
    StudentModel("Vũ Văn Nam", "SV014"),
    StudentModel("Hoàng Thị Phương", "SV015"),
    StudentModel("Đỗ Văn Quân", "SV016"),
    StudentModel("Nguyễn Thị Thu", "SV017"),
    StudentModel("Trần Văn Tài", "SV018"),
    StudentModel("Phạm Thị Tuyết", "SV019"),
    StudentModel("Lê Văn Vũ", "SV020")
  )

  private var removedStudent: StudentModel? = null
  private var removedPosition: Int? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    studentAdapter = StudentAdapter(students, ::editStudent, ::deleteStudent)
    findViewById<RecyclerView>(R.id.recycler_view_students).run {
      adapter = studentAdapter
      layoutManager = LinearLayoutManager(this@MainActivity)
    }

    findViewById<Button>(R.id.btn_add_new).setOnClickListener {
      showAddStudentDialog()
    }
  }

  private fun showAddStudentDialog() {
    val view = LayoutInflater.from(this).inflate(R.layout.dialog_student, null, false)
    val editName = view.findViewById<EditText>(R.id.edit_student_name)
    val editId = view.findViewById<EditText>(R.id.edit_student_id)

    AlertDialog.Builder(this)
      .setTitle("Add New Student")
      .setView(view)
      .setPositiveButton("Add") { _, _ ->
        val name = editName.text.toString()
        val id = editId.text.toString()
        if (name.isNotBlank() && id.isNotBlank()) {
          students.add(StudentModel(name, id))
          studentAdapter.notifyItemInserted(students.size - 1)
        } else {
          Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
      }
      .setNegativeButton("Cancel", null)
      .show()
  }

  private fun editStudent(position: Int) {
    val student = students[position]
    val view = LayoutInflater.from(this).inflate(R.layout.dialog_student, null)
    val editName = view.findViewById<EditText>(R.id.edit_student_name)
    val editId = view.findViewById<EditText>(R.id.edit_student_id)

    editName.setText(student.studentName)
    editId.setText(student.studentId)

    AlertDialog.Builder(this)
      .setTitle("Edit Student")
      .setView(view)
      .setPositiveButton("Update") { _, _ ->
        student.studentName = editName.text.toString()
        student.studentId = editId.text.toString()
        studentAdapter.notifyItemChanged(position)
      }
      .setNegativeButton("Cancel", null)
      .show()
  }

  private fun deleteStudent(position: Int) {
    val student = students[position]
    removedStudent = student
    removedPosition = position

    AlertDialog.Builder(this)
      .setTitle("Delete Student")
      .setMessage("Are you sure you want to delete ${student.studentName}?")
      .setPositiveButton("Delete") { _, _ ->
        removedStudent = student
        removedPosition = position

        students.removeAt(position)
        studentAdapter.notifyItemRemoved(position)

        Snackbar.make(
          findViewById(R.id.recycler_view_students),
          "${student.studentName} deleted",
          Snackbar.LENGTH_LONG
        )
          .setAction("Undo") {
            removedStudent?.let {
              students.add(removedPosition!!, it)
              studentAdapter.notifyItemInserted(removedPosition!!)
            }
          }
          .show()
      }
    .setNegativeButton("Cancel", null)
    .show()
  }
}