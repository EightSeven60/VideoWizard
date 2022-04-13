package com.example.vproject.user;

public class CurrentUser {
    public static final String separator = "~";
    public static String Email;
    public static String Name;
    public static String PasswordHash;
    public static boolean Payment;

    //do not call constructor, use static methods
    private CurrentUser() { }

    public static void setCurrentUser(String Email, String Name, String PasswordHash, boolean Payment) {
        CurrentUser.Email = Email;
        CurrentUser.Name = Name;
        CurrentUser.PasswordHash = PasswordHash;
        CurrentUser.Payment = Payment;
    }

    public static void reset() {
        Email = "";
        Name = "";
        PasswordHash = "";
        Payment = false;
    }

    public static String toText() {
        return Email + separator + Name + separator + PasswordHash + separator + Payment;
    }

    public static Void fromText(String userString) {
        String[] parameterUser = userString.split(separator);
        if (parameterUser.length != 4) return null;
        Email = parameterUser[0];
        Name = parameterUser[1];
        PasswordHash = parameterUser[2];
        Payment = parameterUser[3].equals("1");
        return null;
    }
}
