package tw.edu.pu.o365.s1132234

class Horse(n: Int) {
    var HorseX = 0
    var HorseY = 100 + 320 * n
    var HorseNo = 0

    fun Run(){
        //賽馬圖片處理
        HorseNo ++
        if (HorseNo > 3){
            HorseNo = 0
        }

        HorseX += (10..30).random()

    }
}