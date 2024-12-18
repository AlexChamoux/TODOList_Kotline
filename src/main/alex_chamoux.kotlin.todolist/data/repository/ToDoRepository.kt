package org.example.data.repository

import org.example.data.local.TodoDao
import org.example.data.model.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoDao: TodoDao
) {
    fun getAllTodos(): Flow<List<Todo>> = todoDao.getAllTodos()

    suspend fun addTodo(todo: Todo) = todoDao.insertTodo(todo)

    suspend fun updateTodo(todo: Todo) = todoDao.updateTodo(todo)

    suspend fun deleteTodo(todo: Todo) = todoDao.deleteTodo(todo)

    fun getTodosByCompletion(completed: Boolean): Flow<List<Todo>> =
        todoDao.getTodosByCompletion(completed)
}