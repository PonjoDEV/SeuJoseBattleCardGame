
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


    for ( i in 1..5){
        PlayerControl().drawCard(field.player1,deck)
        PlayerControl().drawCard(field.player2,deck)
    }

    //First round, the player is not supposed to attack, so we dont use battlephase yet
    FieldView().roundStart(field)
    FieldView().placePhase(field.player1)
    FieldView().changeMode(field)
    FieldView().endPhase(field)

    do {
        FieldView().roundStart(field)
        FieldView().placePhase(field.player1)
        FieldView().changeMode(field)
        FieldView().battlePhase(field)
        if(FieldControl().victory(field.player1,field.player2)) break
        FieldView().placePhase(field.player1)
        FieldView().changeMode(field)
        FieldView().endPhase(field)

        println("${deck.size} Cartas restantes\n\n")

    }while (!FieldControl().noMoreCards(deck.size)&&!FieldControl().zeroLifePoints(field.player1)&&!FieldControl().zeroLifePoints(field.player2))

    FieldControl().victory(field.player1,field.player2)

}

