package ph.stacktrek.novare.ecommercenovare.par.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context):
    SQLiteOpenHelper(context, DATABASENAME,null, DATABASEVERSION) {

    companion object {
        private val DATABASEVERSION = 1
        private val DATABASENAME = "ProductsDatabase"

        const val TABLE_PRODUCT = "product_table"
        const val TABLE_PRODUCT_ID = "id"
        const val TABLE_PRODUCT_NAME = "name"
        const val TABLE_PRODUCT_PRICE = "price"
        const val TABLE_PRODUCT_BRAND = "brand"
        const val TABLE_PRODUCT_MEASUREMENT = "measurement"
        const val TABLE_PRODUCT_DESCRIPTION = "description"
        const val TABLE_PRODUCT_MEASUREMENT_UNIT = "measurement_unit"
        const val TABLE_PRODUCT_QUANTITY = "quantity"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_PRODUCTS_TABLE =
            "CREATE TABLE $TABLE_PRODUCT " +
                    "($TABLE_PRODUCT_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$TABLE_PRODUCT_NAME TEXT, " +
                    "$TABLE_PRODUCT_PRICE REAL, " +
                    "$TABLE_PRODUCT_BRAND TEXT, " +
                    "$TABLE_PRODUCT_MEASUREMENT REAL, " +
                    "$TABLE_PRODUCT_DESCRIPTION TEXT, " +
                    "$TABLE_PRODUCT_MEASUREMENT_UNIT TEXT, " +
                    "$TABLE_PRODUCT_QUANTITY REAL)"

        db?.execSQL(CREATE_PRODUCTS_TABLE)

        db?.execSQL("Insert into $TABLE_PRODUCT ($TABLE_PRODUCT_NAME, $TABLE_PRODUCT_PRICE, " +
                "$TABLE_PRODUCT_DESCRIPTION) values ('D''Cream Golden Sun ', " +
                "'75'," +
                " 'D''Cream is an underrated milk tea place but extremely popular with students " +
                "in the University Belt where most of its branches are located."+
                "It''s also an affordable option not just for students but also " +
                "adult milk tea fans with a limited budget. For less than ₱100, " +
                "you can already order the biggest size of milk tea from D''Cream." +
                "Despite its cheap prices, D''Cream doesn''t scrimp on quality and " +
                "milk tea flavor. In fact, many customers say that its best-selling Golden Sun" +
                " (made of black tea, creamy milk, and chewy pearls) " +
                "is the best milk tea they''ve tried.')")

        db?.execSQL("Insert into $TABLE_PRODUCT ($TABLE_PRODUCT_NAME, $TABLE_PRODUCT_PRICE, " +
                "$TABLE_PRODUCT_DESCRIPTION) values ('Chatime Pearl Milk Tea '," +
                " '100', " +
                "'Chatime serves freshly brewed tea mixed with a variety of " +
                "natural flavors. The classic Chatime Milk Tea is a stand out with its " +
                "simple blend of milk and black tea. In addition to milk tea," +
                " Chatime has a large selection of drinks that include smoothies, fruit tea, " +
                "coffee, tea-based latte, and chocolate drinks.')")

        db?.execSQL("Insert into $TABLE_PRODUCT ($TABLE_PRODUCT_NAME, $TABLE_PRODUCT_PRICE, "+
                "$TABLE_PRODUCT_DESCRIPTION) values ('Macao Imperial Cream Cheese Milk Tea '" +
                ", '135'" +
                ", 'Macao Imperial Tea uses jasmine tea that adds a floral scent and sublime taste"+
                " to its drinks. Generous swirls of cream cheese provide the richness that " +
                "perfectly complements the tea. If creamy drinks are your cup of tea, " +
                "the Cream Cheese Milk Tea is a must-try.')")

        db?.execSQL("Insert into $TABLE_PRODUCT ($TABLE_PRODUCT_NAME, $TABLE_PRODUCT_PRICE, " +
                "$TABLE_PRODUCT_DESCRIPTION) values ('Serenitea Okinawa Milk Tea '," +
                " '115', " +
                "'Serenitea’s best-selling milk tea variants include Okinawa" +
                " (brown sugar-flavored milk), Wintermelon (winter melon with caramel " +
                "flavor and milk), and Hokkaido (butter toffee caramel-flavored milk).')")

        db?.execSQL("Insert into $TABLE_PRODUCT ($TABLE_PRODUCT_NAME, $TABLE_PRODUCT_PRICE, " +
                "$TABLE_PRODUCT_DESCRIPTION) values ('Gong Cha Wintermelon Milk Tea', " +
                "'90'," +
                " 'The fastest-growing milk tea brand in Asia, Gong Cha enjoys huge popularity " +
                "in the Philippines, with numerous branches around the country. " +
                "It''s the best milk tea shop for anyone who loves wintermelon. " +
                "Customers rave about the Milk Wintermelon, Gong Cha''s" +
                " signature blend with sweet, cheesy, and salty cream on top.')")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCT")
        onCreate(db)
    }


}