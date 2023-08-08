package ph.stacktrek.novare.ecommercenovare.par.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.util.Log
import ph.stacktrek.novare.ecommercenovare.par.model.Product


interface ProductDAO {
    fun addProduct(product: Product)
    fun getProducts(): ArrayList<Product>
    fun updateProduct(product: Product)
    fun deleteProduct(product: Product)
}


class ProductDAOSQLLiteImplementation(var context: Context): ProductDAO{
    override fun addProduct(product: Product) {
        val databaseHandler = DatabaseHandler(context)
        val db = databaseHandler.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(DatabaseHandler.TABLE_PRODUCT_NAME, product.name)
        contentValues.put(DatabaseHandler.TABLE_PRODUCT_PRICE, product.price)
        contentValues.put(DatabaseHandler.TABLE_PRODUCT_DESCRIPTION, product.description)

        var status = db.insert(DatabaseHandler.TABLE_PRODUCT,
            null,
            contentValues)
        db.close()
    }

    override fun getProducts(): ArrayList<Product> {
        val databaseHandler = DatabaseHandler(context)
        val db = databaseHandler.readableDatabase
        var result = ArrayList<Product>()
        var cursor: Cursor? = null

        val columns =  arrayOf(DatabaseHandler.TABLE_PRODUCT_ID,
            DatabaseHandler.TABLE_PRODUCT_NAME,DatabaseHandler.TABLE_PRODUCT_PRICE,
            DatabaseHandler.TABLE_PRODUCT_DESCRIPTION)

        try {
            cursor = db.query(DatabaseHandler.TABLE_PRODUCT,
                columns,
                null,
                null,
                null,
                null,
                null)

        }catch (sqlException: SQLException){
            db.close()
            return result
        }

        var product: Product
        if(cursor.moveToFirst()){
            do{
                product = Product("", "", "")
                product.name = cursor.getString(1)
                product.id = cursor.getInt(0).toString()
                product.price = cursor.getString(2)
                product.description = cursor.getString(3)
                result.add(product)
            }while(cursor.moveToNext())
        }
        return result
    }

    override fun updateProduct(product: Product) {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(product: Product) {
       val databaseHandler = DatabaseHandler(context)
        val db = databaseHandler.writableDatabase



        val values = arrayOf("${product.id}")
        db.delete(DatabaseHandler.TABLE_PRODUCT, "${DatabaseHandler.TABLE_PRODUCT_ID}=?", values)



        db.close()
    }

}