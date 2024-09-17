package com.doctor.payloads;

public class InputValidator {

        public static boolean isNumeric(String input) {
            if (input == null || input.trim().isEmpty()) {
                return false;
            }

            try {
                Double.parseDouble(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }


    public static boolean isInteger(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        if (!input.matches("\\d")) {
            return false;
        }

        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
