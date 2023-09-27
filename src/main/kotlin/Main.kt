
import control.FieldControl
import control.PlayerControl
import model.Card
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
    //Dont know why it need the toMutableList function
    val deck:MutableList<Card> = CardReader.getCards().shuffled().toMutableList()

    for (card in deck ){
        println(card.name+"  ATK "+card.attack+" DEF "+card.defense+ " TYP "+card.cardClass)
    }

    println(deck.size)

    var field: Field = FieldControl().createField("Yugi","Kaiba",deck)

    var player = field.player1
    var enemy = field.player2

    var i =20

    //Need to put this into a view package
    do {
        PlayerControl().drawCard(player,deck)
        for ( carta in player.hand){
            println(carta?.name)
        }
        //FieldControl().invertField(field)
        player = field.player1
        enemy = field.player2

        i--

    }while (i>0&&!FieldControl().noMoreCards(deck.size)&&!FieldControl().zeroLifePoints(field.player1)&&!FieldControl().zeroLifePoints(field.player2))

}

