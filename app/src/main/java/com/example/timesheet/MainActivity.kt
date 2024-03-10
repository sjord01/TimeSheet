package com.example.timesheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timesheet.ui.theme.TimeSheetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimeSheetTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TimeSheet()
                }
            }
        }
    }
}

@Composable
fun TimeSheet() {
    var mondayHours by remember { mutableIntStateOf(0) }
    var tuesdayHours by remember { mutableIntStateOf(0) }
    var wednesdayHours by remember { mutableIntStateOf(0) }
    var thursdayHours by remember { mutableIntStateOf(0) }
    var fridayHours by remember { mutableIntStateOf(0) }

    val totalHours by remember {
        derivedStateOf {
            mondayHours + tuesdayHours + wednesdayHours + thursdayHours + fridayHours
        }
    }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DayInput("Monday", mondayHours) { hours -> mondayHours = hours }
        DayInput("Tuesday", tuesdayHours) { hours -> tuesdayHours = hours }
        DayInput("Wednesday", wednesdayHours) { hours -> wednesdayHours = hours }
        DayInput("Thursday", thursdayHours) { hours -> thursdayHours = hours }
        DayInput("Friday", fridayHours) { hours -> fridayHours = hours }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Total Hours: $totalHours")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Send button click handler */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Send Time Sheet")
        }
    }
}


@Composable
fun DayInput(day: String, hours: Int, onHoursChange: (Int) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "$day: ", modifier = Modifier.widthIn(120.dp))
        OutlinedTextField(
            value = hours.toString(),
            onValueChange = { onHoursChange(it.toIntOrNull() ?: 0) },
            modifier = Modifier.width(100.dp)
        )
    }
}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun DefaultPreview() {
    TimeSheetTheme {
        TimeSheet()
    }
}
