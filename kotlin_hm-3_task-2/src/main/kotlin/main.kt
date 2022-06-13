const val PAY_1 = "Maestro"
const val PAY_2 = "MasterCard"
const val PAY_3 = "VISA"
const val PAY_4 = "Мир"
const val PAY_5 = "VK Pay"
const val MAXAMOUNT_PAY_1_2 = 75000_00
const val MINAMOUNT_PAY_1_2 = 300_00
const val FIXCOMMISSION_PAY_1_2 = 20_00
const val COMMISSIONPERCENT_PAY_1_2 = 0.06
const val FIXCOMMISSION_PAY_3_4 = 35_00
const val COMMISSIONPERCENT_PAY_3_4 = 0.0075
const val COMMISSIONPERCENT_PAY_5 = 0

fun main() {
    printing(PAY_1,0,1200000_00)
    printing(PAY_3,0,200_00)
    printing(PAY_5,0,150000_00)
    printing(PAY_2,15000_00,75000_00)
}

fun comissionCount(cartType:String =PAY_5, lastAmount:Int = 0, amount:Int): Int {
    val totalAmount = amount + lastAmount
    val comission = when (cartType) {
        PAY_1, PAY_2 -> {
            when (totalAmount) {
                in MINAMOUNT_PAY_1_2..MAXAMOUNT_PAY_1_2 -> 0
                else -> (COMMISSIONPERCENT_PAY_1_2 * amount + FIXCOMMISSION_PAY_1_2).toInt()
            }
        }
        PAY_3, PAY_4 -> {
            if (COMMISSIONPERCENT_PAY_3_4 * amount <= FIXCOMMISSION_PAY_3_4) {
                return FIXCOMMISSION_PAY_3_4.toInt()
            } else {
                return (COMMISSIONPERCENT_PAY_3_4 * amount).toInt()
            }
        }

        PAY_5 -> COMMISSIONPERCENT_PAY_5 * amount
        else -> 0
    }
    return comission
}
fun printing(cartType:String =PAY_5,lastAmount: Int=0, amount:Int) {
    println("Тип карты: $cartType")
    println("Сумма перевода: $amount")
    println("Комиссия за перевод составит:"+comissionCount(cartType,lastAmount, amount))
    println("********")
}

