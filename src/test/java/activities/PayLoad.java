package activities;

public class PayLoad {
        public static String addPlace() {
            return "{\n" +
                    "  \"location\": {\n" +
                    "    \"lat\": -38.383494,\n" +
                    "    \"lng\": 33.427362\n" +
                    "  },\n" +
                    "  \"accuracy\": 50,\n" +
                    "  \"name\": \"Frontline house\",\n" +
                    "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                    "  \"address\": \"29, side layout, cohen 09\",\n" +
                    "  \"types\": [\n" +
                    "    \"shoe park\",\n" +
                    "    \"shop\"\n" +
                    "  ],\n" +
                    "  \"website\": \"http://google.com\",\n" +
                    "  \"language\": \"French-IN\"\n" +
                    "}";
        }

        public static String updatePlace(String key, String placeId) {
            return "{\n" +
                    "\"place_id\":\"" + placeId + "\",\n" +
                    "\"address\":\"70 winter walk, USA\",\n" +
                    "\"key\":\"" + key + "\"\n" +
                    "}";
        }

        public static String dataNestedJson() {
            return "{\n" +
                    "\t\"dashboard\": {\n" +
                    "\t\t\"purchaseAmount\": 910,\n" +
                    "\t\t\"website\": \"rahulshettyacademy.com\"\n" +
                    "\t},\n" +
                    "\t\"courses\": [\n" +
                    "\t\t{\n" +
                    "\t\t\t\"title\": \"Selenium Python\",\n" +
                    "\t\t\t\"price\": 50,\n" +
                    "\t\t\t\"copies\": 6\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"title\": \"Cypress\",\n" +
                    "\t\t\t\"price\": 40,\n" +
                    "\t\t\t\"copies\": 4\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"title\": \"RPA\",\n" +
                    "\t\t\t\"price\": 45,\n" +
                    "\t\t\t\"copies\": 10\n" +
                    "\t\t}\n" +
                    "\t]\n" +
                    "}";
        }
    }

