package tw.edu.pu.o365.s1132234

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    var screenWidthPx by mutableStateOf(0f)
        private set

    var screenHeightPx by mutableStateOf(0f)
        private set

    fun SetGameSize(w: Float, h: Float) {
        screenWidthPx = w
        screenHeightPx = h
    }


}