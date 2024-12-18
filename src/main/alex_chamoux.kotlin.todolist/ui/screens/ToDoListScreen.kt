package org.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.votreprojet.todolist.ui.components.TodoItem
import com.votreprojet.todolist.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    viewModel: TodoViewModel = hiltViewModel()
) {
    var newTaskTitle by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ma TodoList") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (newTaskTitle.isNotBlank()) {
                        viewModel.addTodo(newTaskTitle)
                        newTaskTitle = ""
                    }
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Ajouter une tâche")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            TextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                label = { Text("Nouvelle tâche") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            val todos by viewModel.todos.collectAsState()

            LazyColumn {
                items(todos) { todo ->
                    TodoItem(
                        todo = todo,
                        onToggleComplete = { viewModel.toggleTodoCompletion(it) },
                        onDelete = { viewModel.deleteTodo(it) }
                    )
                }
            }
        }
    }
}