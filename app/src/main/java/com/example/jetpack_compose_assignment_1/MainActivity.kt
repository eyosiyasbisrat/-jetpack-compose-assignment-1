package com.example.jetpack_compose_assignment_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_assignment_1.ui.theme.JetpackComposeAssignment1Theme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.animation.animateContentSize

data class Course(
    val title: String,
    val code: String,
    val creditHours: Int,
    val description: String,
    val prerequisites: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAssignment1Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CourseListScreen(sampleCourses)
                }
            }
        }
    }
}

@Composable
fun CourseCard(course: Course) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { isExpanded = !isExpanded }
            .animateContentSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = course.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = course.code,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "${course.creditHours} Credits",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            if (isExpanded) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = course.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Prerequisites",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = course.prerequisites,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun CourseListScreen(courses: List<Course>) {
    LazyColumn {
        items(courses) { course ->
            CourseCard(course)
        }
    }
}

val sampleCourses = listOf(
    Course(
        "Computer Programming",
        "ECEg-1052",
        3,
        "A comprehensive introduction to programming concepts and problem-solving techniques using a modern programming language. Topics include variables, control structures, functions, and basic data structures.",
        "None"
    ),
    Course(
        "Data Structures and Algorithms",
        "SECT-3091",
        5,
        "Study of fundamental data structures and algorithms. Covers arrays, linked lists, stacks, queues, trees, graphs, and their applications. Includes analysis of algorithm complexity.",
        "ECEg 1052"
    ),
    Course(
        "Operating Systems",
        "SECT-3082",
        5,
        "Study of operating system concepts including processes, threads, memory management, file systems, and I/O. Practical experience with system programming.",
        "SECT-3091"
    ),
    Course(
        "Database Systems",
        "CS401",
        3,
        "Introduction to database design and management. Covers relational databases, SQL, normalization, transactions, and database applications.",
        "ECEg-1052"
    ),
    Course(
        "Fundamentals of Software Engineering",
        "SECT-2111",
        3,
        "Principles and practices of software development. Topics include requirements analysis, design patterns, testing, version control, and project management.",
        "SECT-2101"
    ),
    Course(
        "Fundamentals of Networking",
        "SECT-2171",
        3,
        "Study of computer network architecture, protocols, and applications. Covers TCP/IP, routing, switching, and network security.",
        "None"
    ),
    Course(
        "Fundamentals of AI",
        "SECT-3151",
        3,
        "Introduction to AI concepts and techniques. Topics include search algorithms, machine learning, neural networks, and natural language processing.",
        "SECT-1082, SECT-3092"
    )
)

@Preview(showBackground = true)
@Composable
fun PreviewCardLight() {
    JetpackComposeAssignment1Theme {
        CourseCard(sampleCourses[0])
    }
}

@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewCardDark() {
    JetpackComposeAssignment1Theme {
        CourseCard(sampleCourses[1])
    }
}