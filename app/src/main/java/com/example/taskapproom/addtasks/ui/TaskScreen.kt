package com.example.taskapproom.addtasks.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.taskapproom.addtasks.ui.model.TaskModel

@Composable
fun TaskScreen(modifier: Modifier = Modifier,taskViewModel: TaskViewModel){

    val showDialog:Boolean by taskViewModel.showDialog.observeAsState(false)

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 60.dp)){
       AddTaskDialog(
           show = showDialog,
           onDismiss = {taskViewModel.onDialogClose()},
           onTaskAdded = {taskViewModel.onTaskCreated(it)})
       FabDialog(
           Modifier
               .align(Alignment.BottomEnd)
               .padding(16.dp),taskViewModel)
        TaskList(taskViewModel)
    }
}

@Composable
fun TaskList(taskViewModel: TaskViewModel){

    val myTasks:List<TaskModel> = taskViewModel.tasks
    LazyColumn {
        items(myTasks, key = {it.id}){ task ->
            ItemTask(task, taskViewModel = taskViewModel)
        }
    }
}

@Composable
fun ItemTask(taskModel: TaskModel, taskViewModel: TaskViewModel){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .pointerInput(Unit) {
                detectTapGestures (onLongPress = {
                    taskViewModel.onItemRemove(taskModel)
                })
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        border = BorderStroke(2.dp, Color.Magenta),

        ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = taskModel.task, modifier = Modifier
                .padding(horizontal = 4.dp)
                .weight(1f))
            Checkbox(
                checked = taskModel.selected,
                onCheckedChange = {taskViewModel.onCheckBoxSelected(taskModel)},
                colors = CheckboxDefaults.colors(
                    uncheckedColor = Color.Magenta
                )
            )
        }
    }
}

        @Composable
fun FabDialog(modifier: Modifier, taskViewModel: TaskViewModel) {
    FloatingActionButton(onClick = {
        taskViewModel.onShowdialogClick()
    }, modifier = modifier) {
        Icon(Icons.Filled.Add, contentDescription = "" )
    }
}
@Composable
fun AddTaskDialog(show:Boolean,onDismiss:()->Unit,onTaskAdded:(String)->Unit){
    var myTask by remember { mutableStateOf("") }
    if(show){
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)) {
                Text(
                    text = "Añade tu tarea",
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(16.dp))
                TextField(value = myTask, onValueChange = {myTask = it}, singleLine = true, maxLines = 1)
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = {
                    onTaskAdded(myTask)
                    myTask = ""
                },modifier=Modifier.fillMaxWidth()) {
                    Text(text = "Añadir tarea")
                }
            }
        }
    }
}


