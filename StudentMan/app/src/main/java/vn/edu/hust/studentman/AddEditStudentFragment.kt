package vn.edu.hust.studentman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class AddEditStudentFragment : Fragment(){
    private val args: AddEditStudentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_edit_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editName = view.findViewById<EditText>(R.id.edit_student_name)
        val editId = view.findViewById<EditText>(R.id.edit_student_id)
        val btnSave = view.findViewById<Button>(R.id.btn_save)

        editName.setText(args.studentName)
        editId.setText(args.studentId)

        btnSave.setOnClickListener {
            // Logic to save or update the student
            findNavController().navigateUp()
        }
    }
}