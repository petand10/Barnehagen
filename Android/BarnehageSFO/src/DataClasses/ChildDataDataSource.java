package DataClasses;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ChildDataDataSource {

	private static final String AUTO_ID_COLUMN = "_id";
	private static final String NAME_COLUMN = "name";
	private static final String GUARDIAN_ONE = "guardian_one";
	private static final String GUARDIAN_ONE_PHONE = "guardian_one_phone";
	private static final String GUARDIAN_ONE_ADRESS = "guardian_one_adress";
	private static final String GUARDIAN_TWO = "guardian_two";
	private static final String GUARDIAN_TWO_PHONE = "guardian_two_phone";
	private static final String GUARDIAN_TWO_ADRESS = "guardian_two_adress";
	private static final String OTHER_CONTACT = "other_contact";
	private static final String OTHER_CONTACT_PHONE = "other_contact_phone";

	private static final String TABLE = "persons";

	private static final String CREATE_TABLE = "create table " + TABLE + " " +
	   "(" + AUTO_ID_COLUMN + " integer primary key autoincrement, " + 
	   NAME_COLUMN + " text not null, " +
	   GUARDIAN_ONE + " text not null, " +
	   GUARDIAN_ONE_PHONE + " text not null, " +
	   GUARDIAN_ONE_ADRESS + " text null, " +
	   GUARDIAN_TWO + " text null, " +
	   GUARDIAN_TWO_PHONE + " text null, " +
	   GUARDIAN_TWO_ADRESS + " text null, " +
	   OTHER_CONTACT + " text null, " +
	   OTHER_CONTACT_PHONE + " text null);";
	
	private DbHelper dbHelper;
	private SQLiteDatabase db;
	
	public ChildDataDataSource(Context context) {
		if(dbHelper == null) {
			dbHelper = new DbHelper(context);
		}		
	}
	
	private void open(boolean readOnly) throws SQLException {
		if(db == null || !db.isOpen()) {
			if(readOnly) {
				db = dbHelper.getReadableDatabase();
			} else {
				db = dbHelper.getWritableDatabase();
			}
		}
	}
	
	private void close() {
		db.close();
	}
	
	public void insertRow(ChildData childData) {
		
		ContentValues values = new ContentValues();

		values.put(NAME_COLUMN, childData.getName());
		values.put(GUARDIAN_ONE, childData.getGuardian(0).getName());
		values.put(GUARDIAN_ONE_PHONE, childData.getGuardian(0).getNumber());
		values.put(GUARDIAN_ONE_ADRESS, childData.getGuardian(0).getAdress());
		values.put(GUARDIAN_TWO, childData.getGuardian(1).getName());
		values.put(GUARDIAN_TWO_PHONE, childData.getGuardian(1).getNumber());
		values.put(GUARDIAN_TWO_ADRESS, childData.getGuardian(1).getAdress());
		values.put(OTHER_CONTACT, childData.getGuardian(2).getName());
		values.put(OTHER_CONTACT_PHONE, childData.getGuardian(2).getNumber());
		
		open(false);
		
		db.insert(TABLE, null, values);
		
		close();
		
	}
	
	public boolean updateRow(ChildData childData) {
		ContentValues values = new ContentValues();

		values.put(NAME_COLUMN, childData.getName());
		values.put(GUARDIAN_ONE, childData.getGuardian(0).getName());
		values.put(GUARDIAN_ONE_PHONE, childData.getGuardian(0).getNumber());
		values.put(GUARDIAN_ONE_ADRESS, childData.getGuardian(0).getAdress());
		values.put(GUARDIAN_TWO, childData.getGuardian(1).getName());
		values.put(GUARDIAN_TWO_PHONE, childData.getGuardian(1).getNumber());
		values.put(GUARDIAN_TWO_ADRESS, childData.getGuardian(1).getAdress());
		values.put(OTHER_CONTACT, childData.getGuardian(2).getName());
		values.put(OTHER_CONTACT_PHONE, childData.getGuardian(2).getNumber());
		
		open(false);

		//String[] args = {person.getPnr()+""};		
		boolean updated = db.update(TABLE, values, AUTO_ID_COLUMN + "=?", null) > 0;
		
		close();
		
		return updated;
	}
	
	public boolean deleteRow(ChildData childData) {
		open(false);
		//String[] args = {person.getPnr()+""};
		//boolean deleted = db.delete(TABLE, AUTO_ID_COLUMN + "=?", args) > 0;
		close();
		boolean deleted = false;
		return deleted;
	}
	
	public List<ChildData> getAllChildren() {		
		List<ChildData> persons = new ArrayList<ChildData>();
		
		open(false);

		Cursor cursor = db.query(TABLE, 
				new String[] {NAME_COLUMN, GUARDIAN_ONE, GUARDIAN_TWO, OTHER_CONTACT, 
				OTHER_CONTACT_PHONE, GUARDIAN_ONE_ADRESS, GUARDIAN_TWO_ADRESS, GUARDIAN_ONE_PHONE, GUARDIAN_TWO_PHONE}, 
				null, null, null, null, null);
		
		if(cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
			
			do {
				String name = cursor.getString(cursor.getColumnIndex(NAME_COLUMN));
				String guardianOne = cursor.getString(cursor.getColumnIndex(GUARDIAN_ONE));
				String guardianOnePhone = cursor.getString(cursor.getColumnIndex(GUARDIAN_ONE_PHONE));
				String guardianOneAdress = cursor.getString(cursor.getColumnIndex(GUARDIAN_ONE_ADRESS));
				String guardianTwo = cursor.getString(cursor.getColumnIndex(GUARDIAN_TWO));
				String guardianTwoPhone = cursor.getString(cursor.getColumnIndex(GUARDIAN_TWO_PHONE));
				String guardianTwoAdress = cursor.getString(cursor.getColumnIndex(GUARDIAN_TWO_ADRESS));
				String otherContact = cursor.getString(cursor.getColumnIndex(OTHER_CONTACT));
				String otherContactPhone = cursor.getString(cursor.getColumnIndex(OTHER_CONTACT_PHONE));
				
				
				GuardianData g = new GuardianData(guardianOne, guardianOnePhone, guardianOneAdress);
				GuardianData g2 = new GuardianData(guardianTwo, guardianTwoPhone, guardianTwoAdress);
				GuardianData g3 = new GuardianData(otherContact, otherContactPhone, "");

		        List<GuardianData> guardians = new ArrayList<GuardianData>();
				guardians.add(g);
				guardians.add(g2);
				guardians.add(g3);
				
				persons.add(new ChildData(name, guardians));
				
			} while(cursor.moveToNext());
		}
		
		close();
		
		return persons;
	}
	
	
	
	private static class DbHelper extends SQLiteOpenHelper {
		private static final String DATABASE_NAME = "person_db";

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, 1);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE);
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO
		}
		
	}
}
