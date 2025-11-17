package tw.edu.pu.o365.s1132234

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel: ViewModel() {

    var screenWidthPx by mutableStateOf(0f)
        private set

    var screenHeightPx by mutableStateOf(0f)
        private set

    var gameRunning by mutableStateOf(false)

    var circleX by mutableStateOf(0f)
    var circleY by mutableStateOf(0f)

    //val horse = Horse()
    val horses = mutableListOf<Horse>()

    var score by mutableStateOf(0)
        private set

    var winnerMessage by mutableStateOf("")
        private set

    fun SetGameSize(w: Float, h: Float) {
        screenWidthPx = w
        screenHeightPx = h
        for (i in 0..2){
            horses.add(Horse(i))
        }
    }

    fun StartGame() {

        if (gameRunning) return

        gameRunning = true
        circleX = 100f
        circleY = screenHeightPx - 100f

        winnerMessage = ""

        viewModelScope.launch {
            while (gameRunning) {
                delay(100)
                circleX += 10

                // 當圓形碰到右邊邊界時
                if (circleX >= screenWidthPx - 100){
                    circleX = 100f // 圓形重置回左邊
                    score++        // 【邏輯新增】分數 + 1
                }
                var isWinner = false
                for (i in 0..2){
                    horses[i].Run()

                    if (!isWinner && horses[i].HorseX >= screenWidthPx - 300) {
                        isWinner = true
                        gameRunning = false // 停止遊戲循環
                        winnerMessage = "第${i + 1}馬獲勝"
                    }

                    if (horses[i].HorseX >= screenWidthPx - 300){
                        horses[i].HorseX = 0
                    }

                }

                if (isWinner) {
                    for (i in 0..2) {
                        horses[i].HorseX = 0
                    }
                }

            }
        }
    }

    fun MoveCircle(x: Float, y: Float) {
        circleX += x
        circleY += y
    }
}