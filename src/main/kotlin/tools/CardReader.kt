package tools

import model.Card
import java.io.File
import java.io.InputStream

class CardReader (){

    companion object{
        private lateinit var cards:MutableList<Card>

        fun getCards():MutableList<Card>{
            if(!::cards.isInitialized){
                val fileLine = readCardsCSV()
                cards = fileLine.map { line -> stringToCards(line) }.toMutableList()
                //cartas = lerCartasCSV()
                println(readCardsCSV())
            }
            return cards.map { it }.toMutableList()  //retorna uma replica das cartas
        }

        //Receives the String with all arameters but card position, initializes the card in defense position
        fun stringToCards(line:String):Card{
            val parts = line.split(";")
            if (parts.size == 5){
                return Card(parts[0], parts[1], parts[2].toInt(), parts[3].toInt(), parts[4])
            }else{
                return Card("","",0,0,"", false)
            }
        }

        private fun readCardsCSV(): List<String> {
            val streamDados:InputStream = File("D:\\Coding\\Programação mobile\\SeuJoseBattleCardGame\\cartas.csv").inputStream()
            val leitorStream = streamDados.bufferedReader()
            return leitorStream.lineSequence()
                .filter { it.isNotBlank() }.toList()
        }
    }



}