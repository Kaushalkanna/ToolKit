package com.kaushal.toolkit.scanwishlist;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ItemsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private ItemsDbHelper dbHelper;
    private String[] allColumns = {
            ItemsDbHelper.COLUMN_UPC,
            ItemsDbHelper.COLUMN_NAME,
            ItemsDbHelper.COLUMN_PRICE,
            ItemsDbHelper.COLUMN_IMAGEURL,
            ItemsDbHelper.COLUMN_PRODUCTURL,
            ItemsDbHelper.COLUMN_STORENAME,
    };

    public ItemsDataSource(Context context) {
        dbHelper = new ItemsDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Item createItem(String upc, String name, String price, String imageurl, String producturl, String storename) {
        ContentValues values = new ContentValues();
        values.put(ItemsDbHelper.COLUMN_UPC, upc);
        values.put(ItemsDbHelper.COLUMN_NAME, name);
        values.put(ItemsDbHelper.COLUMN_PRICE, price);
        values.put(ItemsDbHelper.COLUMN_IMAGEURL, imageurl);
        values.put(ItemsDbHelper.COLUMN_PRODUCTURL, producturl);
        values.put(ItemsDbHelper.COLUMN_STORENAME, storename);
        long insertId = database.insert(ItemsDbHelper.TABLE_ITEMS, null,
                values);
        Cursor cursor = database.query(ItemsDbHelper.TABLE_ITEMS,
                allColumns, "" + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Item newItem = cursorToItem(cursor);
        cursor.close();
        return newItem;
    }

    public void deleteItem(Item item) {
        String UPC = item.getUPC();
        database.delete(ItemsDbHelper.TABLE_ITEMS, ItemsDbHelper.COLUMN_UPC
                + " = " + UPC, null);
    }

    public void deleteAll(){
        database.delete(ItemsDbHelper.TABLE_ITEMS, null, null);
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<Item>();

        Cursor cursor = database.query(ItemsDbHelper.TABLE_ITEMS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Item item = cursorToItem(cursor);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }

    private Item cursorToItem(Cursor cursor) {
        Item item = new Item();
        item.setUPC(cursor.getString(0));
        item.setName(cursor.getString(1));
        item.setPrice(cursor.getString(2));
        item.setImageUrl(cursor.getString(3));
        item.setProductUrl(cursor.getString(4));
        item.setStoreName(cursor.getString(5));
        return item;
    }
}
