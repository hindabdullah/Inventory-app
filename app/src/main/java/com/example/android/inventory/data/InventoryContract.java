package com.example.android.inventory.data;

import android.provider.BaseColumns;

/**
 * Created by hind on 11/02/18.
 */

public class InventoryContract {

    private InventoryContract() {
    }


    public static final class InventoryEntry implements BaseColumns {

        public final static String TABLE_NAME = "Product";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_PRODUCT_NAME = "name";
        public final static String COLUMN_PRODUCT_PRICE = "price";
        public final static String COLUMN_PRODUCT_QUANTITY = "quantity";
        public final static String COLUMN_PRODUCT_SUPPLIER = "supplier";
        public final static String COLUMN_PRODUCT_Email = "email";
        public final static String COLUMN_PRODUCT_PHONE = "phone";

    }
}