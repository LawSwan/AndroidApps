package com.example.door2doordonations;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DonationStore {

    public static final String PREFS = "door2door_prefs";
    private static final String KEY_DONATIONS = "donations_json";
    public static final String KEY_LOCATION = "pref_location";
    public static final String KEY_NOTIFICATIONS = "pref_notifications";

    public static final double FLAT_FEE = 20.00;

    public static class Donation {
        public final String recipient;
        public final double amount;
        public final String date;
        public final String status;

        public Donation(String recipient, double amount, String date, String status) {
            this.recipient = recipient;
            this.amount = amount;
            this.date = date;
            this.status = status;
        }
    }

    private final SharedPreferences prefs;

    public DonationStore(Context context) {
        this.prefs = context.getApplicationContext()
                .getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public void addDonation(String recipient) {
        String today = new SimpleDateFormat("MMM d, yyyy", Locale.US).format(new Date());
        Donation d = new Donation(recipient, FLAT_FEE, today, "Pending pickup");
        List<Donation> list = getAll();
        list.add(0, d);
        save(list);
    }

    public List<Donation> getAll() {
        List<Donation> list = new ArrayList<>();
        String json = prefs.getString(KEY_DONATIONS, "[]");
        try {
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                list.add(new Donation(
                        o.optString("recipient"),
                        o.optDouble("amount", FLAT_FEE),
                        o.optString("date"),
                        o.optString("status")));
            }
        } catch (JSONException ignored) {
        }
        return list;
    }

    public int getCount() {
        return getAll().size();
    }

    public void clear() {
        prefs.edit().remove(KEY_DONATIONS).apply();
    }

    private void save(List<Donation> list) {
        JSONArray arr = new JSONArray();
        try {
            for (Donation d : list) {
                JSONObject o = new JSONObject();
                o.put("recipient", d.recipient);
                o.put("amount", d.amount);
                o.put("date", d.date);
                o.put("status", d.status);
                arr.put(o);
            }
        } catch (JSONException ignored) {
        }
        prefs.edit().putString(KEY_DONATIONS, arr.toString()).apply();
    }
}
