package com.android.jetnotes.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.jetnotes.components.NoteButton
import com.android.jetnotes.components.NoteInputText
import com.android.jetnotes.components.NoteScreenHeader
import com.android.jetnotes.model.Note
import com.android.jetnotes.util.formateDate





@Composable
fun NotesApp(noteViewModel: NoteViewModel) {

    val context = LocalContext.current
    val notesList = noteViewModel.noteList.collectAsState().value


    NoteScreen(
        noteList = notesList,
        addNote = {
            noteViewModel.addNote(it)
        },
        removeNote = {
            noteViewModel.removeNote(it)
            Toast.makeText(context,"Note Deleted",Toast.LENGTH_SHORT).show()
        }
    )

}


@Composable
fun NoteScreen(
    noteList: List<Note> ,
    addNote: (Note) -> Unit,
    removeNote: (Note) -> Unit
) {
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {

            }) {
                Icon(Icons.Filled.Add,"add")
            }
        }

    ) {
        Column(modifier = Modifier) {

            var title by remember {
                mutableStateOf("")
            }
            var description by remember {
                mutableStateOf("")
            }
            val context = LocalContext.current

            val state = rememberLazyListState()

            NoteScreenHeader()
            //content
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                NoteInputText(
                    modifier = Modifier.padding(
                        top = 9.dp,
                        bottom = 8.dp
                    ),
                    text = title,
                    label = "Title",
                    onTextChange = {
                        if (it.all { char->
                                char.isLetter() || char.isWhitespace()
                            }) title = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                )
                NoteInputText(
                    modifier = Modifier.padding(
                        top = 9.dp,
                        bottom = 8.dp
                    ),
                    text = description,
                    label = "Description",
                    onTextChange = {
                        if (it.all { char->
                                char.isLetter() || char.isWhitespace()
                            }) description = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                )
                NoteButton(
                    modifier = Modifier.padding(
                        top = 9.dp,
                        bottom = 8.dp
                    ),
                    text = "Add",
                    onClick = {
                        if (title.isNotEmpty() || description.isNotEmpty()){
                            addNote(Note(
                                title = title,
                                description = description
                            ))
                            title = ""
                            description = ""

                            Toast.makeText(context,"Note Added",Toast.LENGTH_SHORT).show()
                        }
                    },
                )

                Divider(modifier = Modifier.padding(top = 10.dp, end = 10.dp, start = 10.dp))

                LazyColumn(state = state){
                    items(items = noteList){ note->
                        NoteRow(
                            note = note,
                            onNoteClicked = {
                                removeNote(it)
                            }
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked : (Note) -> Unit 
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clip(shape = RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
//            .pointerInput(Unit) {
//                detectTapGestures(
//                    onLongPress = {
//                        onNoteClicked(note)
//                    }
//                )
//            },
            .clickable {
                onNoteClicked(note)
            },

        color = Color.LightGray,
        elevation = 4.dp
    ){
        Column(
            modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = formateDate(note.entryDate.time),
                style = MaterialTheme.typography.caption
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    val notes = remember{
        mutableStateListOf<Note>()
    }
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        NoteScreen(
            noteList = notes,
            addNote = {
                notes.remove(it)
            },
            removeNote = {
                notes.add(it)
            }
        )
    }
}