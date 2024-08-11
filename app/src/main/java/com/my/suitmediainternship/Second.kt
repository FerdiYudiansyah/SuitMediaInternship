package com.my.suitmediainternship

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.my.suitmediainternship.ui.theme.SuitMediaInternshipTheme

class Second : ComponentActivity() {
    companion object{
        var selectedname:String = "Selected User Name"
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
                   secondwindow()
                }
            }
        }
    }
}

@Composable
fun secondwindow(){
    var context = LocalContext.current
    val intent1 = Intent(context,Second::class.java)
    Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        Row(horizontalArrangement = Arrangement.Absolute.Left, modifier = Modifier
            .border(border = BorderStroke(width = 1.dp, Color.LightGray))
            .fillMaxWidth()) {
            Button(onClick = {val intent = Intent(context,MainActivity::class.java)
                context.startActivity(intent)},
                elevation = null,
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White.copy(0f)),
                ) {
                Text(text = "<", fontSize = 30.sp)
            }
            Column(modifier = Modifier.fillMaxWidth(), ) {
                Text(text = "Second Screen", textAlign = TextAlign.Left)
            }
        }
        Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(30.dp)) {
            Box(contentAlignment = Alignment.Center){
                Text(text = "Welcome", color =Color(4, 2, 29))
            }
            Box(contentAlignment = Alignment.Center){
                Text(text = MainActivity.enteredname, color =Color(4, 2, 29))
            }
            Box(contentAlignment = Alignment.Center){
                Text(text = Second.selectedname, color =Color(4, 2, 29))
            }
            Row(horizontalArrangement = Arrangement.Absolute.Center, modifier = Modifier.fillMaxWidth(),verticalAlignment =Alignment.CenterVertically) {

            }
            Row(horizontalArrangement = Arrangement.Absolute.Center, modifier = Modifier.fillMaxWidth().fillMaxHeight(), verticalAlignment =Alignment.Bottom) {
                Button(onClick = {val intent = Intent(context,Third::class.java)
                    context.startActivity(intent)},
                    shape = RoundedCornerShape(30),
                    modifier = Modifier.fillMaxWidth().size(width = 100.dp,height = 50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(43, 99, 123), contentColor = Color.White)) {
                    Text(text = "Choose a User")
                }
            }
        }
        Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxWidth().fillMaxHeight()){

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    SuitMediaInternshipTheme {
        secondwindow()
    }
}