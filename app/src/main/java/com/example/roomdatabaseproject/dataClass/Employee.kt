package com.example.roomdatabaseproject.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val emp_id: Int,
    var emp_fname: String,
    val emp_lname: String,
    val emp_dob: String,
    val emp_email: String,
    val emp_gender: String,
    var emp_phoneNo: String,
    val emp_address: String,
    val emp_department: String
)
