package model

class Player (val name:String, var lifePoints:Int=10000, var hand:Array<Card?> = arrayOfNulls(10), var field:Array<Card?> = arrayOfNulls(5))