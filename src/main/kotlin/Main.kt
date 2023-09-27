
import control.FieldControl
import control.PlayerControl
import model.Field
import tools.CardReader

fun main(args: Array<String>) {

    /*
    *leia atentamente o enunciado
    * você pode criar quantas classes precisar
    * recomendo que você separe minimamente sua solução em MVC
    * tente criar uma classe que seja capaz de expressar as regras que serão aplicadas no jogo
    * tente criar um classe que seja capaz de representar o status atual do jogo
     */

    //Initializing the deck and shuffling the cards
    val deck = CardReader.getCards().shuffled()

    for (card in deck ){
        println(card.name+"  ATK "+card.attack+" DEF "+card.defense+ " TYP "+card.cardClass)
    }
    println(deck.size)

    var field: Field = FieldControl().createField("Yugi","Kaiba",deck)

    var player = field.player1
    var enemy = field.player2
    for (i in 1..5){
        PlayerControl().drawCard(player,deck)
        PlayerControl().drawCard(enemy,deck)
    }

    var deckSize:Int = deck.size-1

    do {

        FieldControl().invertField(field)
        player = field.player1
        enemy = field.player2


    }while (!FieldControl().noMoreCards(deck.size)&&!FieldControl().zeroLifePoints(field.player1)&&!FieldControl().zeroLifePoints(field.player2))


}

