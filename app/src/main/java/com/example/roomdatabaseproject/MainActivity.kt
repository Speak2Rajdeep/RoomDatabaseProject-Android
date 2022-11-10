package com.example.roomdatabaseproject

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.roomdatabaseproject.dataClass.Employee
import com.example.roomdatabaseproject.database.EmployeeDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var phNo: EditText? = null
    private var dob: EditText? = null
    private var gender: EditText? = null
    private var address: EditText? = null
    private var email: EditText? = null
    private var department: EditText? = null
    private var lastName: EditText? = null
    private var firstName: EditText? = null
    lateinit var employeeDatabase: EmployeeDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        employeeDatabase = Room.databaseBuilder(
            applicationContext,
            EmployeeDatabase::class.java, "employeeDB"
        ).build()

        firstName = findViewById<EditText>(R.id.fname)
        lastName = findViewById<EditText>(R.id.lname)
        email = findViewById<EditText>(R.id.email)
        dob = findViewById<EditText>(R.id.dob)
        gender = findViewById<EditText>(R.id.gender)
        phNo = findViewById<EditText>(R.id.ph)
        address = findViewById<EditText>(R.id.address)
        department = findViewById<EditText>(R.id.dept)
    }

    fun insertEmployee(view: View) {
        CoroutineScope(Dispatchers.IO).launch {
            employeeDatabase.employeeDAO().insertEmployee(
                Employee(
                    0,
                    firstName?.text.toString(),
                    lastName?.text.toString(),
                    dob?.text.toString(),
                    email?.text.toString(),
                    gender?.text.toString(),
                    phNo?.text.toString(),
                    address?.text.toString(),
                    department?.text.toString()
                )
            )
        }
        Toast.makeText(this, "New Record Inserted Successfully!!!", Toast.LENGTH_SHORT).show()
    }

    fun getEmployeeData(view: View?) {
        employeeDatabase.employeeDAO().getEmployees().observe(this@MainActivity) {
            Log.d("databaselog", it.toString())
        }
        Toast.makeText(this, "Employee Record Fetched Successfully!!!", Toast.LENGTH_SHORT).show()
    }

    fun deleteEmployeeData(view: View) {
        firstName?.text?.toString()?.takeIf { it.isNotEmpty() }?.let { enteredId ->
            CoroutineScope(Dispatchers.IO).launch {
                employeeDatabase.employeeDAO().getEmployeebyfName(enteredId).let { employee ->
                    employeeDatabase.employeeDAO().deleteEmployee(employee)
                }
            }
        }
        Toast.makeText(this, "Employee Record Deleted Successfully!!!", Toast.LENGTH_SHORT).show()
    }

    fun updateEmployeeData(view: View) {
        firstName?.text?.toString()?.takeIf { it.isNotEmpty() }?.let { enteredId ->
            CoroutineScope(Dispatchers.IO).launch {
                employeeDatabase.employeeDAO().getEmployeebyfName(enteredId).let { employee ->
                    employee.emp_fname = "new updated name"
                    employeeDatabase.employeeDAO().updateEmployee(employee)
                }
            }
        }
        Toast.makeText(this, "Employee Record Updated Successfully!!!", Toast.LENGTH_SHORT).show()
    }
}