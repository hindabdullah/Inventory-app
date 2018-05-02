package com.example.android.inventory.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hind on 05/02/18.
 */

public class InventoryDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "Product.db";

    private static final int DATABASE_VERSION = 1;

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE  " + InventoryContract.InventoryEntry.TABLE_NAME + " ("
                + InventoryContract.InventoryEntry._ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME + "  TEXT NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_PRODUCT_PRICE + "  INTEGER NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_PRODUCT_QUANTITY + "  INTEGER NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_PRODUCT_SUPPLIER + "  TEXT NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_PRODUCT_Email + "  TEXT NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_PRODUCT_PHONE + "  INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}