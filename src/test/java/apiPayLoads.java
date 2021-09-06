public class apiPayLoads {

    static String modifySearch() {
        return "{\n" +
                "    \"template\": \"top-airports-v2.json\",\n" +
                "    \"country\": \"general\",\n" +
                "    \"media\": \"mobile\",\n" +
                "    \"locale\": \"en_us\"\n" +
                "}";
    }


    static String modifySearchHotels(String fromDate,String toDate,String hotelId) {
        return "{\n" +
                "    \"checkIn\": \""+fromDate+"\",\n" +
                "    \"checkOut\": \""+toDate+"\",\n" +
                "    \"roomsInfo\": [\n" +
                "        {\n" +
                "            \"adultsCount\": 2,\n" +
                "            \"kidsAges\": []\n" +
                "        }\n" +
                "    ],\n" +
                "    \"hotelId\": \""+hotelId+"\"\n" +
                "}";
    }
}
