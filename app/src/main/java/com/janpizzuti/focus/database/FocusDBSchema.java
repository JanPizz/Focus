package com.janpizzuti.focus.database;

public class FocusDBSchema {
    public static final class UserPreferencesTable {
        public static final String NAME = "user_preferences";

        public static final class Cols {
            public static final String MINUTES = "minutes";
            public static final String STATUS = "status (on | off)";
        }
    }
}
