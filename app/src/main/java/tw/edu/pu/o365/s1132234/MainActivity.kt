package tw.edu.pu.o365.s1132234

import android.os.Bundle
import android.content.pm.ActivityInfo
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.window.layout.WindowMetricsCalculator
import tw.edu.pu.o365.s1132234.ui.theme.RaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 設定為橫向螢幕
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        // 隱藏狀態列和導航列，實現全螢幕
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
        WindowCompat.setDecorFitsSystemWindows(
            window, false)

        // 計算螢幕尺寸
        val windowMetricsCalculator =
            WindowMetricsCalculator.getOrCreate()
        val currentWindowMetrics=
            windowMetricsCalculator.computeCurrentWindowMetrics(this)
        val bounds = currentWindowMetrics.bounds
        val screenWidthPx = bounds.width().toFloat()
        val screenHeightPx = bounds.height().toFloat()

        val gameViewModel: GameViewModel by viewModels()
        gameViewModel.SetGameSize(screenWidthPx , screenHeightPx)


        setContent {
            RaceTheme {
                GameScreen(message="賽馬遊戲(作者：黃士豪) ", gameViewModel)
            }
        }
    }
}