package com.example.roomdatabaseproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdatabaseproject.dataClass.Employee
import com.example.roomdatabaseproject.databaseDao.EmployeeDAO

@Database(entities = [Employee::class], version = 1)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDAO(): EmployeeDAO
}