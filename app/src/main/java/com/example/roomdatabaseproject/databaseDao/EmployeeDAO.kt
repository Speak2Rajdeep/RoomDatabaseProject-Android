package com.example.roomdatabaseproject.databaseDao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdatabaseproject.dataClass.Employee

@Dao
interface EmployeeDAO {

    @Insert
    suspend fun insertEmployee(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Query("SELECT * FROM employee")
    fun getEmployees(): LiveData<List<Employee>>

    @Query("SELECT * FROM employee where emp_id =:id")
    suspend fun getEmployee(id: Int): Employee

    @Query("SELECT * FROM employee where emp_lname =:fname")
    suspend fun getEmployeebyfName(fname: String): Employee
}