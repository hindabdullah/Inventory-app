package com.example.android.inventory;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.inventory.data.InventoryContract;
import com.example.android.inventory.data.InventoryDbHelper;

public class MainActivity extends AppCompatActivity {

    private InventoryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new InventoryDbHelper(this);
    }

    public Cursor readDb() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                InventoryContract.InventoryEntry._ID,
                InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME,
                InventoryContract.InventoryEntry.COLUMN_PRODUCT_PRICE,
                InventoryContract.InventoryEntry.COLUMN_PRODUCT_QUANTITY,
                InventoryContract.InventoryEntry.COLUMN_PRODUCT_SUPPLIER,
                InventoryContract.InventoryEntry.COLUMN_PRODUCT_Email,
                InventoryContract.InventoryEntry.COLUMN_PRODUCT_PHONE};

        Cursor cursor = db.query(
                InventoryContract.InventoryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }

    private void displayDatabaseInfo(Cursor cursor) {
        TextView displayView = (TextView) findViewById(R.id.inventory_text_view);

        try {
            displayView.setText("The table contains " + cursor.getCount() + " Product.\n\n");
            displayView.append(InventoryContract.InventoryEntry._ID + " - " +
                    InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME + " - " +
                    InventoryContract.InventoryEntry.COLUMN_PRODUCT_PRICE + " - " +
                    InventoryContract.InventoryEntry.COLUMN_PRODUCT_QUANTITY + " - " +
                    InventoryContract.InventoryEntry.COLUMN_PRODUCT_SUPPLIER + " - " +
                    InventoryContract.InventoryEntry.COLUMN_PRODUCT_Email + " - " +
                    InventoryContract.InventoryEntry.COLUMN_PRODUCT_PHONE + "\n");

            int idColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRODUCT_QUANTITY);
            int supplierColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRODUCT_SUPPLIER);
            int emailColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRODUCT_Email);
            int phoneColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRODUCT_PHONE);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplier = cursor.getString(supplierColumnIndex);
                String currentEmail = cursor.getString(emailColumnIndex);
                int currentPhone = cursor.getInt(phoneColumnIndex);

                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentPrice + " - " +
                        currentQuantity + " - " +
                        currentSupplier + " - " +
                        currentEmail + " - " +
                        currentPhone));
            }
        } finally {
            cursor.close();
        }
    }

    private void insertInventory() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME, "Milk");
        values.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_PRICE, 6);
        values.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_QUANTITY, 3);
        values.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_SUPPLIER, "AB");
        values.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_Email, "supplier@gmail.com");
        values.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_PHONE, 5544);


        long newRowId = db.insert(InventoryContract.InventoryEntry.TABLE_NAME, null, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.insert_dummy_data:
                insertInventory();
                Cursor c = readDb();
                displayDatabaseInfo(c);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}