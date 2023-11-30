package com.example.tictactoe_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoe_kotlin.ui.theme.TicTacToe_KotlinTheme
import kotlin.collections.*
//import kotlin.collections.EmptyList.isEmpty
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToe_KotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TicTacToeGame()
                }
            }
        }
    }
}

@Composable
fun TicTacToeGame() {
    //METHOD 1: Explained
    //Using mutableStateOf you are creating an observable object. So, the recomposition will
    // happen just if you assign a new instance to this state. Let's say that you want that
    // recomposition happens after add a new item to the list. In this case, you need to create a
    // copy of this list, add the elements to this copied list, and then assign the copied list to
    // the state.

    //METHOD 2: Explained
    //The mutableStateListOf creates an observable list. All operations you've done in this list
    // (add, remove, update) will cause a recomposition.


    // State to track the state of each square in the Tic Tac Toe grid
    //METHOD 1: using mutableStateof()
    //var squares by remember { mutableStateOf(List(9) { "" }) }

    //using a direct mutable list
    //fill with empty strings in the start as list is empty
    //val squatres by remember { mutableStateListOf<String>()}
    //METHOD 2: using mutableStateListOf<String>()
    val squares = remember { mutableStateListOf<String>()}.apply {
        if(isEmpty()){
            repeat(9){add("")}
        }
    }

    // State to track whose turn it is (X or O)
    var isPlayerXTurn by remember { mutableStateOf(true) }

    // State to update the title text
    var titleText by remember { mutableStateOf("") }

    // Function to reset the game
    fun resetGame() {
        //squares = List(9) { "" }
        isPlayerXTurn = true
        titleText = ""
    }

    // Function to check if a player has won based on the array, squares
    fun checkWin(): Boolean {
        // Check rows, if the rows character all matches
        for (i in 0 until 3) {
            if (squares[i * 3] == squares[i * 3 + 1] && squares[i * 3 + 1] == squares[i * 3 + 2] && squares[i * 3].isNotEmpty()) {
                return true
            }
        }

        // Check columns, if the columns character all matches
        for (i in 0 until 3) {
            if (squares[i] == squares[i + 3] && squares[i + 3] == squares[i + 6] && squares[i].isNotEmpty()) {
                return true
            }
        }

        // Check diagonals, if the left diagonal character all matches
        if (squares[0] == squares[4] && squares[4] == squares[8] && squares[0].isNotEmpty()) {
            return true
        }
        // if the right diagonal character all matches
        if (squares[2] == squares[4] && squares[4] == squares[6] && squares[2].isNotEmpty()) {
            return true
        }

        return false
    }

    // Column composable that fills the entire vertical space with padding
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Text indicating whose turn it is, centered horizontally
        Text(
            text = if (titleText.isNotEmpty()) titleText else if (isPlayerXTurn) "Player X's Turn" else "Player O's Turn",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        // LazyVerticalGrid to display the Tic Tac Toe grid with 3 columns
        LazyVerticalGrid(columns = GridCells.Fixed(3),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            // Iterate through the indices of the Tic Tac Toe grid
            items((0..8).toList()) { index ->
                // Button for each square in the grid
                Button(
                    onClick = {
                        // Check if the square is empty before making a move
                        //To update an item in the list, first, check if the selected square is empty.
                        //If it is, create a mutable copy of the current list (since lists in Kotlin are immutable by default),
                        //modify the required index, and then update squares.value with this new list.
                        //squares is a mutable state holder, where squares.value gives you access to the current list.
                        if (squares[index].isEmpty()) {
                            // Update the square based on the current player's turn
                            //toMutableList() is used to safely modify a list within a local scope
                            // without affecting the original immutable list, aligning with functional
                            // programming principles that emphasize immutability and side-effect-free functions
                            //METHOD 1: using mutableStateOf
                            /*squares = squares.toMutableList().also {
                                it[index] = if (isPlayerXTurn) "X" else "O"
                            }*/
                            //METHOD 2: using mutableStateListOf
                            //triggers a recomposition
                            //do direct updates since list is mutable
                            if (squares[index].isEmpty()) {
                                squares[index] = if (isPlayerXTurn) "X" else "O"
                                // ... rest of your game logic
                            }

                            // Check if a player has won
                            if (checkWin()) {
                                // Handle the game-over state here
                                val winnerText = if (isPlayerXTurn) "Player X wins!" else "Player O wins!"
                                // Update the title text indicating the winner
                                titleText = winnerText
                            } else {
                                // Switch to the other player's turn
                                isPlayerXTurn = !isPlayerXTurn
                            }
                        }
                    },
                    // Button styling and appearance
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Gray)
                        .padding(4.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White
                    )
                ) {
                    // Display the content of the square (X, O, or empty)
                    Text(
                        text = squares[index],
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }

        // Reset button to reset the game
        Button(
            onClick = { resetGame() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(text = "Reset Game")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicTacToePreview() {
    TicTacToeGame()
}





