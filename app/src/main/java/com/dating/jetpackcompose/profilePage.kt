package com.dating.jetpackcompose

import android.print.PrintAttributes.Margins
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun profifepage(){
    Card(elevation = 2.dp, modifier = Modifier
        .fillMaxSize()
        .padding(top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
        .border(width = 3.dp, color = Color.Cyan, shape = RoundedCornerShape(3.dp))) {
        
        BoxWithConstraints() {
            val constraints=if(minWidth<600.dp){
                potraitConstraints(margin = 16.dp)
            }else{
                landscapeConstraints(margin = 16.dp)

            }
        ConstraintLayout(constraints,Modifier.verticalScroll(rememberScrollState())) {
            Image(painter = painterResource(id = R.drawable.datte),
                contentDescription ="datte",
                modifier= Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(width = 2.dp, color = Color.Blue, shape = CircleShape)
                    .layoutId("image"),
                contentScale = ContentScale.Crop)
            Text(text = "Date me", modifier = Modifier.layoutId("nameText"))
            Text(text = "MyName",modifier = Modifier.layoutId("myName"))
            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .layoutId("clRow"))
            {
                profileStats(count = "150", title ="Followers" )
                profileStats(count = "100", title ="Following" )
                profileStats(count = "50", title = "Posts")
            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier.layoutId("btnFollow")) {
                Text(text = "Follow")

            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier.layoutId("btnMessage")) {
                Text(text = "Direct Message")

            }
            }
        }
        }
}
fun potraitConstraints(margin:Dp):ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val myName = createRefFor("myName")
        val clRow = createRefFor("clRow")
        val btnFollow = createRefFor("btnFollow")
        val btnMessage = createRefFor("btnMessage")

        constrain(image) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)

        }
        constrain(nameText) {
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)

        }
        constrain(myName) {
            top.linkTo(nameText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(clRow) {
            top.linkTo(myName.bottom)

        }
        constrain(btnFollow) {
            top.linkTo(clRow.bottom)
            start.linkTo(parent.start)
            end.linkTo(btnMessage.start)
            width = Dimension.wrapContent
        }
        constrain(btnMessage) {
            top.linkTo(clRow.bottom)
            start.linkTo(btnFollow.end)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
        }
    }
}
fun landscapeConstraints(margin:Dp):ConstraintSet{
        return ConstraintSet {
            val image = createRefFor("image")
            val nameText = createRefFor("nameText")
            val myName = createRefFor("myName")
            val clRow = createRefFor("clRow")
            val btnFollow = createRefFor("btnFollow")
            val btnMessage = createRefFor("btnMessage")

            constrain(image){
                top.linkTo(parent.top,margin=margin)
                start.linkTo(parent.start,margin=margin)
            }
            constrain(nameText) {
                top.linkTo(image.bottom)
                start.linkTo(image.start)

            }
            constrain(myName) {
                top.linkTo(nameText.bottom)
                start.linkTo(nameText.start)
                end.linkTo(nameText.end)
            }
            constrain(clRow) {
                start.linkTo(image.end)
                top.linkTo(image.top)
                end.linkTo(parent.end)

            }
            constrain(btnFollow) {
                top.linkTo(clRow.bottom)
                start.linkTo(image.end)
                end.linkTo(btnMessage.start)
                width = Dimension.wrapContent
            }
            constrain(btnMessage) {
                top.linkTo(clRow.bottom)
                start.linkTo(btnFollow.end)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
            }
        }

    }


@Composable
fun profileStats(count:String,title:String){
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title)
    }

}
@Preview(showBackground = true)
@Composable
fun profilePreview(){
    profifepage()
}
