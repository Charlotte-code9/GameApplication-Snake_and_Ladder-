package ph.stacktrek.novare.snakeandladder.medalla.sabellano

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import ph.stacktrek.novare.snakeandladder.medalla.sabellano.databinding.ActivityAddPlayerBinding
import ph.stacktrek.novare.snakeandladder.medalla.sabellano.databinding.ActivityPlayerSelectionBinding

class PlayerSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerSelectionBinding
    private lateinit var playerNames: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.twoPlayerButton.setOnClickListener {
            showPlayersDialogue(2).show()
        }
        binding.threePlayerButton.setOnClickListener {
            showPlayersDialogue(3).show()
        }
        binding.fourPlayerButton.setOnClickListener {
            showPlayersDialogue(4).show()
        }
    }

    fun showPlayersDialogue(players: Int): Dialog {
        return this.let {
            val builder = AlertDialog.Builder(it)
            val activityDialoguePlayersBinding: ActivityAddPlayerBinding =
                ActivityAddPlayerBinding.inflate(it.layoutInflater)

            val playerNames = mutableListOf<String>()

            if (players == 2) {
                activityDialoguePlayersBinding.playerThreeLabel.visibility = View.GONE
                activityDialoguePlayersBinding.playerThreeInput.visibility = View.GONE
                activityDialoguePlayersBinding.playerFourLabel.visibility = View.GONE
                activityDialoguePlayersBinding.playerFourInput.visibility = View.GONE
            }
            if (players == 3) {
                activityDialoguePlayersBinding.playerFourLabel.visibility = View.GONE
                activityDialoguePlayersBinding.playerFourInput.visibility = View.GONE
            }

            with(builder) {
                setPositiveButton("START", DialogInterface.OnClickListener { dialog, id ->
                    val playerOneName = activityDialoguePlayersBinding.playerOneInput.text.toString()
                    val playerTwoName = activityDialoguePlayersBinding.playerTwoInput.text.toString()
                    val playerThreeName = activityDialoguePlayersBinding.playerThreeInput.text.toString()
                    val playerFourName = activityDialoguePlayersBinding.playerFourInput.text.toString()

                    if (playerOneName.isBlank() || playerTwoName.isBlank() ||
                        (players > 2 && playerThreeName.isBlank()) || (players > 3 && playerFourName.isBlank())) {
                        Toast.makeText(this@PlayerSelectionActivity, "Please enter all player names.", Toast.LENGTH_SHORT).show()
                    } else {
                        val goToGame = Intent(applicationContext, GameBoardActivity::class.java)
                        playerNames.add(playerOneName)
                        playerNames.add(playerTwoName)

                        if (players > 2) {
                            playerNames.add(playerThreeName)
                        }
                        if (players > 3) {
                            playerNames.add(playerFourName)
                        }

                        goToGame.putExtra("playerCount", playerNames.size)
                        goToGame.putStringArrayListExtra("playerNames", ArrayList(playerNames))
                        startActivity(goToGame)
                        finish()
                    }
                })
                setNegativeButton("CANCEL",DialogInterface.OnClickListener { dialog, id ->

                })
                setView(activityDialoguePlayersBinding.root)

                create()
            }
        }
    }
}