package com.fire.todo.screens

//import org.koin.androidx.compose.koinViewModel


import android.util.Log.v
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import com.fire.todo.database.TodoEntity
import com.fire.todo.screens.HomeViewmodel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fire.todo.database.addDate

//import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen (viewModel: HomeViewmodel = viewModel()) {

    val todos by viewModel.todos.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getTodos()
    }

    val (dialogOpen, setDialogOpen) = remember {
        mutableStateOf(false)
    }
    if (dialogOpen) {
        val (title, setTitle) = remember {
            mutableStateOf("")

        }
        val (subTitle, setSubTitle) = remember {
            mutableStateOf("")
        }
        val (description, setDescription) = remember {
            mutableStateOf("")
        }
        Dialog(onDismissRequest = { setDialogOpen(false) }) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { setTitle(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Title") },
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        focusedBorderColor = Color.White,
//                        unfocusedBorderColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        cursorColor = Color.White
//
//                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = subTitle,
                    onValueChange = { setSubTitle(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Sub Title") },
//                    colors = TextFieldDefaults.(
//                        focusedTextColor = Color.White,
//                        focusedBorderColor = Color.White,
//                        unfocusedBorderColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        cursorColor = Color.White
                )
                // )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        if (title.isNotEmpty() && subTitle.isNotEmpty()) {
                            viewModel.addTodo(
                                TodoEntity(
                                    title = title,
                                    subTitle = subTitle,
                                    description = description
                                )

                            )
                            setDialogOpen(false)
                        }
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        "Add",
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = FontFamily.Monospace
                    )
                }

            }
        }
    }


    Scaffold(
        containerColor = MaterialTheme.colorScheme.secondary,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    setDialogOpen(true)
                },
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }


        }

    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        )
        {

            AnimatedVisibility(
                visible = todos.isEmpty(),
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {

                Text(
                    text = "No Todos",
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = FontFamily.Monospace,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )

            }
            AnimatedVisibility(
                visible = todos.isNotEmpty(),
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(
                        bottom = paddingValues.calculateBottomPadding() + 8.dp,
                        top = 8.dp,
                        end = 8.dp,
                        start = 8.dp

                    ), verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = todos.sortedBy { it.done },
                        key = { it.id }
                    ) { todo ->
                        TodoItem(
                            todo = todo,
                            onClick = {
                                viewModel.updateTodo(todo.copy(done = !todo.done))
                            },
                            onDelete = {
                                viewModel.deleteTodo(todo)

                            }
                        )

                    }
                }


            }
        }


    }
}

    @Composable
    fun TodoItem(todo: TodoEntity, onClick: () -> Unit, onDelete: () -> Unit) {
        val color by animateColorAsState(
            targetValue = if (todo.done) Color(0xFF00FF00) else Color.White,
            animationSpec = tween(500), label = ""
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color)
                .padding(8.dp)
        )
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {

            Row(
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(color)
                    .clickable {
                        onClick()
                    }
                    .padding(
                        horizontal = 8.dp,
                        vertical = 4.dp

                    ),
                Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)

                ) {


                }

            }

            Box(
                modifier = Modifier.size(25.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(4.dp), contentAlignment = Alignment.Center
            ) {
                Row() {
                    AnimatedVisibility(
                        visible = !todo.done,
                        enter = scaleIn() + fadeIn(),
                        exit = scaleOut() + fadeOut()

                    ) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = color)
                    }
                }
            }
            Column(

            ) {
                Text(
                    text = todo.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
                Text(text = todo.subTitle, fontSize = 14.sp, color = Color.White)

            }

        }
        Box(
            modifier = Modifier.size(25.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Delete, contentDescription = null, tint = Color.White,
                modifier = Modifier.clickable {
                    onDelete()
                })
        }
        Text(text = todo.addDate, color = Color.White, modifier =   Modifier.padding(end = 8.dp), fontSize = 10.sp)
    }




//18:00