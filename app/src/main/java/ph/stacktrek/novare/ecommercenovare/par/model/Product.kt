package ph.stacktrek.novare.ecommercenovare.par.model

import com.google.gson.Gson
import java.io.Serializable
import java.math.BigDecimal

class Product(var name: String,  var price: String, var description : String)
{
    lateinit var id: String
    fun toJsonString(): String {
        return Gson().toJson(this)
    }
}

enum class MeasurementUnit{
    KILOGRAMS,
    GRAMS,
    METER,
    TONS,
    NOT_SET
}



//class ElectronicsProduct(
//    name: String,
//    price: Double,
//    description: String,
//    category: String,
//    brand: String,
//    dateAdded: LocalDate,
//    val warranty: String,
//    val manufacturer: String,
//    val modelNumber: String
//) : Product(name, price, description, category, brand, dateAdded)