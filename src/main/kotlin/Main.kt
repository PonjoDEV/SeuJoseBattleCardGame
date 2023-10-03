
import control.FieldControl
import control.PlayerControl
import model.Card
import model.Field
import tools.CardReader
import view.FieldView

fun main(args: Array<String>) {

    /*
    *leia atentamente o enunciado
    * você pode criar quantas classes precisar
    * recomendo que você separe minimamente sua solução em MVC
    * tente criar uma classe que seja capaz de expressar as regras que serão aplicadas no jogo
    * tente criar um classe que seja capaz de representar o status atual do jogo
     */

    //Initializing the deck and shuffling the cards
    //Don't know why it needs the toMutableList function
    val deck:MutableList<Card> = CardReader.getCards().shuffled().toMutableList()

    for (card in deck ){
        println(card.name+"  ATK "+card.attack+" DEF "+card.defense+ " TYP "+card.cardClass)
    }

    println(deck.size)

    var field: Field = FieldControl().createField("Yugi","Kaiba",deck)

    /*
    var player = field.player1
    var enemy = field.player2
    */
    var i =30

    for ( i in 1..5){
        PlayerControl().drawCard(field.player1,deck)
        PlayerControl().drawCard(field.player2,deck)
    }

    //Need to put this into a view package
    do {
        FieldView().roundStart(field)
        //println("Penislandia 3000")
        //FieldView().placePhase(field)

        FieldControl().placeMonster(field.player1)
        FieldControl().printPlayerField(field.player1)
        FieldControl().placeEquipment(field.player1)

        FieldControl().invertField(field)

        /*
        player = field.player1
        enemy = field.player2
         */

        println("${deck.size} Cartas restantes\n\n")

        i--

    }while (i>2&&!FieldControl().noMoreCards(deck.size)&&!FieldControl().zeroLifePoints(field.player1)&&!FieldControl().zeroLifePoints(field.player2))

}

