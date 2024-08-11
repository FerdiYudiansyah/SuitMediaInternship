package com.my.suitmediainternship

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import android.content.Intent
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.my.suitmediainternship.ui.theme.SuitMediaInternshipTheme

class MainActivity : ComponentActivity() {
    companion object{
        var enteredname:String=""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuitMediaInternshipTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    firstwindow()
                }
            }
        }
    }
}

@Composable
fun firstwindow() {
    var name by remember {mutableStateOf("")}
    var sentence by remember { mutableStateOf("") }
    var ispalindrome by remember { mutableStateOf(false) }
    var isSubmitted by remember { mutableStateOf(false) }
    var context = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.padding(30.dp)) {
        TextField(
            value = name,
            shape = RoundedCornerShape(30),
            label = { Text("Name") },
            onValueChange = {name=it},
            modifier = Modifier
                .fillMaxWidth(),
            colors=TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent),
            textStyle = LocalTextStyle.current.copy(fontSize = 20.sp)
        )
        TextField(
            value = sentence,
            shape = RoundedCornerShape(30),
            label = { Text("Sentence") },
            onValueChange = {sentence=it},
            modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 0.dp)
                .fillMaxWidth(),
            colors=TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent),
            textStyle = LocalTextStyle.current.copy(fontSize = 20.sp)
        )
        Button(onClick ={ispalindrome=isPalindrome(sentence);isSubmitted=true},
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(43, 99, 123),contentColor = Color.White),
            shape = RoundedCornerShape(30),
            modifier = Modifier
                .padding(0.dp, 20.dp, 0.dp, 0.dp)
                .fillMaxWidth()
                .size(width = 100.dp, height = 50.dp)) {
            Text(text = "CHECK")
        }
        Button(onClick = {
            val intent = Intent(context,Second::class.java)
            context.startActivity(intent)
            MainActivity.enteredname=name
        },colors = ButtonDefaults.buttonColors(backgroundColor = Color(43, 99, 123), contentColor = Color.White),
            modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 0.dp)
                .fillMaxWidth()
                .size(width = 100.dp, height = 50.dp),
            shape = RoundedCornerShape(30),) {
            Text(text = "NEXT")
        }
        if (isSubmitted){
            if(ispalindrome){
                checkDialog("Is Palindrome")
            }
            else{
                checkDialog("Not Palindrome")
            }
        }
    }
}


fun isPalindrome(sentence:String): Boolean {
    var sentence=sentence.replace("\\s".toRegex(), "").lowercase()
    var tempString=""
    for (i in sentence.length downTo 1){
        tempString += sentence[i-1]
    }
    if (tempString==sentence){
        return true
    }
    return false
}

@Composable
fun checkDialog(text:String){
    Dialog(onDismissRequest = {}) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SuitMediaInternshipTheme {
        secondwindow()
    }
}
