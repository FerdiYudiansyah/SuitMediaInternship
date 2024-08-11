package com.my.suitmediainternship

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.my.suitmediainternship.ui.theme.SuitMediaInternshipTheme
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.util.concurrent.Executors


class Third : ComponentActivity() {
    companion object{
        var name:String=""
        var datafull=0
        var appusers = fetchData()
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
                    thirdwindow()
                }
            }
        }
    }
}

@Composable
fun thirdwindow(){
    var name=""
    var context = LocalContext.current
    Column(verticalArrangement = Arrangement.Top) {
        Row(horizontalArrangement = Arrangement.Absolute.Left, modifier = Modifier
            .border(border = BorderStroke(width = 1.dp, Color.LightGray))
            .fillMaxWidth()) {
            Button(
                onClick = {
                    val intent = Intent(context, Second::class.java)
                    context.startActivity(intent)
                },
                elevation = null,
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White.copy(0f)),
            ) {
                Text(text = "<", fontSize = 30.sp)
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Third Screen", textAlign = TextAlign.Left)
            }
        }
        Row(horizontalArrangement = Arrangement.Absolute.Right, modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()) {
            //Image(painter =  painterResource(id = R.drawable.avatar), contentDescription = "@Null")
            //for (i in 0 until Third.appusers.size){
                //
            //}
            viewItems(Third.appusers)
        }
    }
}

@Composable
fun viewItems(appusers : MutableList<user>){
    var context = LocalContext.current
    LazyColumn(modifier = Modifier.fillMaxWidth()){
        items(appusers){user->
            Button(
                onClick = {
                    Second.selectedname=user.firstname + " " + user.lastname
                    val intent = Intent(context, Second::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White.copy(0f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(border = BorderStroke(width = 1.dp, Color.LightGray)),
                elevation = null,
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()){
                    Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier.fillMaxHeight()){
                        AsyncImage(
                            model = user.imgpath,
                            contentDescription = null,
                        )
                        Text(user.firstname+" "+user.lastname)
                    }
                    Text(user.gmail)
                }

            }

        }

    }
}

fun fetchData(): MutableList<user> {
    var listofusers= mutableListOf<user>()
    Executors.newSingleThreadExecutor().execute {
        var jsonstr = URL("https://reqres.in/api/users?page=1&per_page=10").readText()
        var jsonobj = JSONObject(jsonstr)
        var jsonArray = jsonobj.getJSONArray("data")
        for (i in 0 until jsonArray.length()) {
            val jsonArrObj = jsonArray.getJSONObject(i)
            var firstname= jsonArrObj.getString("first_name")
            var lastname= jsonArrObj.getString("last_name")
            var email=jsonArrObj.getString("email")
            var imgpath=jsonArrObj.getString("avatar")
            var singleuser=user(firstname,lastname,email,imgpath)
            listofusers.add(singleuser)
        }
    }
    return listofusers
}

class user(firstName:String,lastName:String,Gmail:String,imgPath:String){
    var firstname:String=firstName
    var lastname:String=lastName
    var imgpath:String=imgPath
    var gmail:String=Gmail
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    SuitMediaInternshipTheme {
        thirdwindow()
    }
}