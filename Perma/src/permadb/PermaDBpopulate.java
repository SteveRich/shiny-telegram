package permadb;

/**
 * Steve Rich - Permaculture DB populator, implements SQLite db
 *
 * CSV Spec 0: Genus 1: Species 2: Common Name 3: Family 4: Hardiness Zone 5:
 * Light 6: Moisture 7: pH: Strongly Acid 8: pH: Acid 9: pH: Garden 10: pH:
 * Alkaline 11: Form 12: Habit 13: Root Pattern 14: Height 15: Width 16: Growth
 * Rate 17: Native Region 18-28: Habitat 29-35: Edible 36: Medicinal 37: N2
 * fixer 38: Mineral Accum 39: Wildlife 40: Invertebrate Shelter 41: Nectary 42:
 * Ground Cover 43: Other Uses 44: Nuisances 45: Poisin
 *
 *
 */
import permadb.utils.generalUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PermaDBpopulate extends generalUtils {

    String[] tables = {"plantZones", "plantNames", "light",
        "moisture", "soilph", "habit", "form", "rootpattern",
        "growthrate", "nativeregion", "plants", "habitat", "edibility",
        "medicinal", "height", "width"};
    String database;

    Connection SQL;

    public PermaDBpopulate(String database) {
        this.database = database;
        try {
            SQL = openConnection(database);
            System.out.println(database + " opened successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Primary CSV parses, caontains all distinct insert methods
    public void populateList(String inputFile) throws SQLException {
        HashMap<String, String> habitStorage = buildHabit();
        HashMap<String, String> rootStorage = buildRoots();
        HashMap<String, String> regionStorage = buildRegion();

        ArrayList<String> input = ArrayListLoader(inputFile);

        for (String line : input) {

            String[] lineSplit = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            String plantId = generateKey(lineSplit[0], lineSplit[1]);
            System.out.println("\n\nNew Entry");

            String[] plantInfo = insertPlantFormatter(plantId, line);
            insertPlant(plantInfo); // Main Plant entity

            insertZoneFormatter(plantId, lineSplit[4]); // Handles zone split and insertion
            insertLight(plantId, lineSplit[5]); // Handles light type split and insertion
            insertMoisture(plantId, lineSplit[6]);
            insertGrowthRate(plantId, lineSplit[16]);

            insertHabit(plantId, lineSplit[12], habitStorage);
            insertRoot(plantId, lineSplit[13], rootStorage);
            insertRegion(plantId, lineSplit[17], regionStorage);
            insertMedicinal(plantId, lineSplit[36]);

            insertMeasurement(plantId, lineSplit[14], "height");
            insertMeasurement(plantId, lineSplit[15], "width");

            insertHabitatFormatter(plantId, Arrays.copyOfRange(lineSplit, 18, 28));
            insertEdibilityFormatter(plantId, Arrays.copyOfRange(lineSplit, 29, 35));

            if (!lineSplit[2].equals("")) { // Common Names
                insertPlantNames(plantId, lineSplit[2]);
            }

        }

    }

    void insertMeasurement(String plantId, String length, String table) throws SQLException {
        String query = "insert into " + table + " values (?,?,?)";
        int[] range = new int[2];
        length = length.replace("+", "");
        boolean inches = false;
        if (length.contains("\"")) {
            inches = true;
            length = length.replace("\"", "");
        } else {
            length = length.replace("'", "");
        }

        if (!length.equalsIgnoreCase("indef")) {
            if (length.contains("-")) {
                String[] rangeTemp = length.split("-");
                range[0] = Integer.valueOf(rangeTemp[0]);
                range[1] = Integer.valueOf(rangeTemp[1]);
            } else {
                range[0] = Integer.valueOf(length);
                range[1] = Integer.valueOf(length);
            }
            if (!inches) {
                range[0] = range[0] * 12;
                range[1] = range[1] * 12;
            }
            PreparedStatement SQLquery = SQL.prepareStatement(query);
            SQLquery.setString(1, plantId);
            SQLquery.setInt(2, range[0]);
            SQLquery.setInt(3, range[1]);
            SQLquery.execute();
            SQLquery.close();
            System.out.println("Added " + table + " Low: " + range[0] + ", High: " + range[1]);

        }
    }

    void insertHabitatFormatter(String plantId, String[] habitatArr) throws SQLException {
        System.out.println("Habitat arr length: " + habitatArr.length);
        String[] habitatDefinition = {"Disturbed", "Meadows", "Prairies", "Old Fields",
            "Thickets", "Edges", "Gaps/Clearings", "Open Woods", "Forest",
            "Conifer Forest", "Other Habitats"};

        for (int i = 0; i < habitatArr.length; i++) {
            if (habitatArr[i].trim().equalsIgnoreCase("x")) {
                String query = "insert into habitat values (?,?)";
                PreparedStatement SQLquery = SQL.prepareStatement(query);
                SQLquery.setString(1, plantId);
                SQLquery.setString(2, habitatDefinition[i]);
                SQLquery.execute();
                SQLquery.close();
                System.out.println("Added Habitat: " + plantId + ", " + habitatDefinition[i]);
            }
        }
    }

    HashMap<String, String> buildHabit() {
        HashMap<String, String> habitStorage = new HashMap<>();
        habitStorage.put("ms", "Multistemmed");
        habitStorage.put("std", "Standard");
        habitStorage.put("run", "Runner");
        habitStorage.put("w vine", "Woody Vine");
        habitStorage.put("clmp", "Clumper");
        habitStorage.put("e clmp", "Ephemeral Clumper");
        habitStorage.put("Rtkt", "Running Thicket former");
        habitStorage.put("a r vine", "Annual Herbaceous Vine");
        habitStorage.put("E clmp", "Evergreen Clumper");
        habitStorage.put("r v/skr", "Herbaceous Suckering Vine");
        habitStorage.put("r vine", "Herbaceous Vine");
        habitStorage.put("Ctkt", "Clumping Thicket former");
        habitStorage.put("E Std", "Evergreen Standard");
        habitStorage.put("E Rmat", "Evergreen Running Mat former");
        habitStorage.put("E run", "Evergreen Runner");
        habitStorage.put("skr", "Suckering");
        habitStorage.put("a clmp", "Annual Clumper");
        habitStorage.put("e run", "Ephemeral Runner");
        habitStorage.put("E Cmat", "Evergreen Clumping Mat former");
        habitStorage.put("E ms", "Evergreen Multistemmed");
        habitStorage.put("Cmat", "Clumping Mat former");
        habitStorage.put("e Rmat", "Ephemeral Running Mat former");
        habitStorage.put("spr", "Sprouting");
        habitStorage.put("E Rtkt", "Evergreen Running Thicket former");
        habitStorage.put("E Ctkt", "Evergreen Clumping Thicket former");
        habitStorage.put("Rmat", "Running Mat former");
        habitStorage.put("Cthk", "Clumping Thicket former");
        habitStorage.put("E std", "Evergreen Standard");
        habitStorage.put("a Rmat", "Annual Running Mat former");
        habitStorage.put("mat", "Mat former");

        return habitStorage;
    }

    HashMap<String, String> buildRoots() {
        HashMap<String, String> rootStorage = new HashMap<>();
        rootStorage.put("F", "Flat");
        rootStorage.put("H", "Heart");
        rootStorage.put("Fb", "Fibrous");
        rootStorage.put("R", "Rhizomatous");
        rootStorage.put("Sk", "Suckering");
        rootStorage.put("T", "Tap");
        rootStorage.put("St", "Stoloniferous");
        rootStorage.put("C", "Corm");
        rootStorage.put("Fb", "Fibrous");
        rootStorage.put("Tu", "Tuberous");
        rootStorage.put("Fl", "Fleshy");
        rootStorage.put("B", "Bulb");
        return rootStorage;
    }

    HashMap<String, String> buildRegion() {
        HashMap<String, String> regionStorage = new HashMap<>();
        regionStorage.put("ENA", "Eastern North America");
        regionStorage.put("EURA", "Eurasia");
        regionStorage.put("ASIA", "Asia");
        regionStorage.put("WNA", "Western North America");
        regionStorage.put("EUR", "Europe");
        regionStorage.put("PRAI", "North American Prairies");
        regionStorage.put("SAM", "South America");
        regionStorage.put("CULT", "Of Cultivated or Hybrid Origin");
        regionStorage.put("AUST", "Australia");
        return regionStorage;
    }

    // Generates a unique key based on genus/species
    String generateKey(String genus, String species) throws SQLException {
        int counter = 0;
        String keyRoot = genus.substring(0, 3) + species.substring(0, 3);
        String findExisting = "Select * from plants where plantid like ?";
        PreparedStatement SQLquery = SQL.prepareStatement(findExisting);
        SQLquery.setString(1, keyRoot + "%");

        // Checks for existing entries, increments counter to append to id
        ResultSet plants = SQLquery.executeQuery();
        while (plants.next()) {
            counter++;
        }
        plants.close();

        return keyRoot + counter;

    }

    void selectPlants() throws SQLException {
        System.out.println("\nRetrieving Plant List");
        String query = "Select * from plants;";
        Statement SQLquery = SQL.createStatement();
        ResultSet results = SQLquery.executeQuery(query);

        while (results.next()) {
            String plantId = results.getString("plantId");
            System.out.println("PlantID: " + plantId);
            System.out.println("Species: " + results.getString("genus")
                    + " " + results.getString("species"));
            System.out.print("Zones: ");

            PreparedStatement plantDetailStatement = SQL.prepareStatement("Select zone from plantzones where plantid = ?");
            plantDetailStatement.setString(1, plantId);
            ResultSet plantDetailResults = plantDetailStatement.executeQuery();
            while (plantDetailResults.next()) {
                System.out.print(plantDetailResults.getInt("zone") + ", ");
            }

            System.out.print("\nCommon Names: ");
            plantDetailStatement = SQL.prepareStatement("Select name from plantNames where plantid = ?");
            plantDetailStatement.setString(1, plantId);
            plantDetailResults = plantDetailStatement.executeQuery();
            while (plantDetailResults.next()) {
                System.out.print(plantDetailResults.getString("name") + ", ");
            }
            System.out.println("\n\n");

            plantDetailResults.close();
            plantDetailStatement.close();
        }
        System.out.println("Plant list complete");

        results.close();
        SQLquery.close();
    }

    void insertEdibilityFormatter(String plantId, String[] edibleArr) throws SQLException {
        String query = "insert into edibility values (?,?,?,?,?,?,?,?)";
        String[] edibleTitle = {"Edible Fruit", "Edible Nuts", "Edible Greens", // Just for the output display
            "Edible Roots", "Culinary", "Tea", "Edible Other"};

        PreparedStatement SQLquery = SQL.prepareStatement(query);
        SQLquery.setString(1, plantId);
        for (int i = 2; i < 9; i++) { // default nulls
            SQLquery.setString(i, null);
        }
        for (int i = 0; i < edibleArr.length; i++) {
            if (edibleArr[i].equals("")) {
                SQLquery.setString(i + 2, "a");
            } else {
                String edibleDef = "";
                if (edibleArr[i].equalsIgnoreCase("F")) {
                    edibleDef = "Fair";
                } else if (edibleArr[i].equalsIgnoreCase("E")) {
                    edibleDef = "Excellent";
                } else if (edibleArr[i].equalsIgnoreCase("Y")) {
                    edibleDef = "Yes, unrated";
                } else if (edibleArr[i].equalsIgnoreCase("S")) {
                    edibleDef = "Superfood";
                } else if (edibleArr[i].equalsIgnoreCase("G")) {
                    edibleDef = "Good";
                }
                SQLquery.setString(i + 2, edibleDef);
                System.out.println("Adding Edibility, " + edibleTitle[i] + ": " + plantId + ", " + edibleDef);
            }
        }
        SQLquery.execute();
        SQLquery.close();

    }

    // Expects zone in either single numeric, or range
    void insertZoneFormatter(String plantId, String zones) throws SQLException {
        if (!zones.equals("") && zones != null && !zones.equals("U")) {
            zones = zones.replace("b", "");

            if (zones.length() == 1) {
                insertZones(plantId, Integer.parseInt(zones));
            } else {
                try {
                    String[] zoneSplit = zones.split("-");
                    int lowZone = Integer.parseInt(zoneSplit[0]);
                    int highZone = Integer.parseInt(zoneSplit[1]);
                    for (int i = lowZone; i <= highZone; i++) {
                        insertZones(plantId, i);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Format error: " + zones);
                }
            }
        }

    }

    // Plant String[] return: plantid,family,genus,species,n2fixer,mineralaccum,invertshelter,ground cover, poison
    String[] insertPlantFormatter(String plantId, String input) {
        String[] output = new String[9];
        String[] inputSplit = input.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

        output[0] = plantId;
        System.out.println("Key: " + output[0]);
        output[1] = inputSplit[3]; // Family
        System.out.println("Family: " + output[1]);
        output[2] = inputSplit[0]; // Genus
        System.out.println("Genus: " + output[2]);
        output[3] = inputSplit[1]; // Species
        System.out.println("Species: " + output[3]);
        // Defaults all to 0, change to 1 if Y
        output[4] = "0";
        output[5] = "0";
        output[6] = "0";
        output[7] = "0";
        output[8] = "0";

        if (inputSplit[37].equalsIgnoreCase("y")) { // N2 Fixer
            output[4] = "1";
        }
        System.out.println("N2 Fixer: " + output[4]);
        if (inputSplit[38].equalsIgnoreCase("y")) { // Mineral Accum
            output[5] = "1";
        }
        System.out.println("Mineral Accum: " + output[5]);
        if (inputSplit[40].equalsIgnoreCase("y")) { // Invert Shelter
            output[6] = "1";
        }
        System.out.println("Invert Shelter: " + output[6]);
        if (inputSplit[42].equalsIgnoreCase("y")) { // Ground Cover
            output[7] = "1";
        }
        System.out.println("Ground Cover: " + output[7]);
        if (inputSplit[45].equalsIgnoreCase("p")) { // Poison
            output[8] = "1";
        }
        System.out.println("Poison: " + output[8]);
        return output;

    }

    void insertHabit(String plantId, String habit, HashMap<String, String> habitStorage) throws SQLException {
        if (!habit.equals("")) {

            if (habitStorage.containsKey(habit)) {
                String habitValue = habitStorage.get(habit);
                String query = "Insert into habit values (?,?)";
                PreparedStatement SQLquery = SQL.prepareStatement(query);
                SQLquery.setString(1, plantId);
                SQLquery.setString(2, habitValue);
                SQLquery.execute();
                System.out.println("Added habit: " + plantId + ", " + habitValue);
            } else {
                System.out.println("No habit match for code: " + habit);
            }
        }
    }

    void insertRegion(String plantId, String region, HashMap<String, String> regionStorage) throws SQLException {
        if (!region.equals("")) {
            if (regionStorage.containsKey(region)) {
                String regionResult = regionStorage.get(region);
                String query = "insert into nativeregion values (?,?)";
                PreparedStatement SQLquery = SQL.prepareStatement(query);
                SQLquery.setString(1, plantId);
                SQLquery.setString(2, regionResult);
                SQLquery.execute();
                SQLquery.close();
                System.out.println("Added region: " + plantId + ", " + regionResult);
            }
        }
    }

    // Doesn't parse range, s-f must be s-m-f (only 1 instance of s-f)
    void insertGrowthRate(String plantId, String rate) throws SQLException {
        if (!rate.equals("")) {
            rate = rate.trim();
            String[] rateSplit;
            if (rate.contains("-")) {
                rateSplit = rate.split("-");
            } else {
                rateSplit = new String[]{rate};
            }

            for (String rateSt : rateSplit) {
                if (rateSt.equalsIgnoreCase("S")) {
                    rateSt = "Slow";
                } else if (rateSt.equalsIgnoreCase("M")) {
                    rateSt = "Medium";
                } else if (rateSt.equalsIgnoreCase("F")) {
                    rateSt = "Fast";
                }
                String query = "insert into growthrate values (?,?)";
                PreparedStatement SQLquery = SQL.prepareStatement(query);
                SQLquery.setString(1, plantId);
                SQLquery.setString(2, rateSt);
                SQLquery.close();
                System.out.println("Added growth rate: " + plantId + ", " + rateSt);

            }
        }
    }

    void insertMedicinal(String plantId, String medicinalField) throws SQLException {
        if (!medicinalField.equals("")) {
            String query = "insert into medicinal values (?,?)";
            String medicinalDef = null;
            if (medicinalField.equalsIgnoreCase("F")) {
                medicinalDef = "Fair";
            } else if (medicinalField.equalsIgnoreCase("E")) {
                medicinalDef = "Excellent";
            } else if (medicinalField.equalsIgnoreCase("G")) {
                medicinalDef = "Good";
            } else if (medicinalField.equalsIgnoreCase("S")) {
                medicinalDef = "Superfood";
            } else if (medicinalField.equalsIgnoreCase("Y")) {
                medicinalDef = "Yes, unrated";
            }
            PreparedStatement SQLquery = SQL.prepareStatement(query);
            SQLquery.setString(1, plantId);
            SQLquery.setString(2, medicinalDef);
            SQLquery.execute();
            SQLquery.close();
            System.out.println("Added medicinal: " + plantId + ", " + medicinalDef);

        }
    }

    void insertRoot(String plantId, String root, HashMap<String, String> rootStorage) throws SQLException {
        root = root.replace("\"", "");
        if (!root.equals("")) {
            String[] roots;
            if (root.contains(",")) {
                roots = root.split(",");
            } else {
                roots = new String[]{root};
            }
            for (String rootEntry : roots) {
                rootEntry = rootEntry.trim();
                if (rootStorage.containsKey(rootEntry)) {
                    String rootResult = rootStorage.get(rootEntry);
                    String query = "Insert into rootPattern values (?,?)";
                    PreparedStatement SQLquery = SQL.prepareStatement(query);
                    SQLquery.setString(1, plantId);
                    SQLquery.setString(2, rootResult);
                    SQLquery.execute();
                    SQLquery.close();
                    System.out.println("Added root pattern: " + plantId + ", " + rootResult);
                }
            }
        }
    }

    // Splits and inserts light. F: full sun, P: part shade, S: Shade
    void insertLight(String plantId, String shade) throws SQLException {
        if (!shade.equals("") && shade != null) {
            char[] shadeSplit = shade.toCharArray();
            for (char shadeChar : shadeSplit) {
                String shadeSt = String.valueOf(shadeChar);
                String query = "Insert into light values (?,?)";
                PreparedStatement SQLquery = SQL.prepareStatement(query);
                SQLquery.setString(1, plantId);
                SQLquery.setString(2, shadeSt);
                SQLquery.execute();
                SQLquery.close();
                System.out.println("Added light entry: " + plantId + ", " + shadeSt);
            }
        }
    }

    // Splits and inserts moisture. 
    void insertMoisture(String plantId, String moisture) throws SQLException {
        char[] moistureSplit = moisture.replace("-", "").toCharArray();
        for (char moistureChar : moistureSplit) {
            String moistStr = String.valueOf(moistureChar);
            if (moistStr.equalsIgnoreCase("X")) {
                moistStr = "Xeric";
            } else if (moistStr.equalsIgnoreCase("M")) {
                moistStr = "Mesic";
            } else if (moistStr.equalsIgnoreCase("H")) {
                moistStr = "Hydric";
            }
            String query = "Insert into moisture values(?,?)";
            PreparedStatement SQLstatement = SQL.prepareStatement(query);
            SQLstatement.setString(1, plantId);
            SQLstatement.setString(2, moistStr);
            SQLstatement.execute();
            SQLstatement.close();
            System.out.println("Added moisture entry: " + plantId + ", " + moistStr);
        }
    }

    // Single query to plant table, no associative tables
    void insertPlant(String[] plantInfo) throws SQLException {
        String plantID = plantInfo[0];
        String family = plantInfo[1];
        String genus = plantInfo[2];
        String species = plantInfo[3];
        int n2Fixer = Integer.parseInt(plantInfo[4]);
        int mineralAccum = Integer.parseInt(plantInfo[5]);
        int invertShelter = Integer.parseInt(plantInfo[6]);
        int groundCover = Integer.parseInt(plantInfo[7]);
        int poison = Integer.parseInt(plantInfo[8]);

        int counter = 0;

        String query = "INSERT into plants values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement SQLquery = SQL.prepareStatement(query);
        SQLquery.setString(1, plantID);
        SQLquery.setString(2, family);
        SQLquery.setString(3, genus);
        SQLquery.setString(4, species);
        SQLquery.setInt(5, n2Fixer);
        SQLquery.setInt(6, mineralAccum);
        SQLquery.setInt(7, invertShelter);
        SQLquery.setInt(8, groundCover);
        SQLquery.setInt(9, poison);

        SQLquery.executeUpdate();
        SQLquery.close();
        System.out.println("Added entry: " + plantID);
    }

    void insertPlantNames(String plantId, String names) throws SQLException {

        String[] plantNames = names.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        for (String plantName : plantNames) {
            String query = "Insert into plantNames values (?,?)";

            PreparedStatement SQLquery = SQL.prepareStatement(query);
            SQLquery.setString(1, plantId);
            SQLquery.setString(2, plantName);
            SQLquery.executeUpdate();
            SQLquery.close();
            System.out.println("Added common name: " + plantId + ", " + plantName);
        }
    }

    void insertZones(String plantId, int zone) throws SQLException {

        String query = "Insert into plantZones values (?,?)";
        PreparedStatement SQLquery = SQL.prepareStatement(query);
        SQLquery.setString(1, plantId);
        SQLquery.setInt(2, zone);
        SQLquery.executeUpdate();
        SQLquery.close();
        System.out.println("Added zone entry: " + plantId + ", Zone: " + zone);
    }

    void deletePlants(String plantId) throws SQLException {

        String[] tableList = {"plantzones", "plantNames", "plants"};  // Make sure these are in order if I use FK's

        for (String table : tableList) {
            String query = "Delete from " + table + " where plantid = ?";
            PreparedStatement SQLquery = SQL.prepareStatement(query);
            SQLquery.setString(1, plantId);
            SQLquery.executeUpdate();
            System.out.println(plantId + " removed from: " + table);
            SQLquery.close();
        }
    }

    void createTables() throws SQLException {
        // done
        String plants = "Create table plants "
                + "(plantid varchar primary key,"
                + "family varchar,"
                + "genus varchar,"
                + "species varchar,"
                + "n2Fixer int,"
                + "mineralAccum int,"
                + "invertShelter int,"
                + "groundCover int,"
                + "poison int)";

        // done
        String plantZones = "Create table plantzones "
                + "(plantid varchar,"
                + "zone int,"
                + "FOREIGN KEY(plantid) REFERENCES plants(plantid))";

        // done
        String plantNames = "Create table plantNames"
                + "(plantid varchar,"
                + "name varchar,"
                + "FOREIGN KEY(plantid) REFERENCES plants(plantid))";

        // done
        String light = "Create table light"
                + "(plantid varchar,"
                + "lightType varchar,"
                + "FOREIGN KEY(plantid) REFERENCES plants(plantid))";

        // done
        String moisture = "Create table moisture "
                + "(plantid varchar,"
                + "moistureType varchar,"
                + "FOREIGN KEY(plantid) REFERENCES plants(plantid))";

        String soilph = "Create table soilph"
                + "(plantid varchar,"
                + "phtype varchar,"
                + "FOREIGN KEY(plantid) REFERENCES plants(plantid))";

        // done
        String habit = "Create table habit"
                + "(plantid varchar,"
                + "habit varchar,"
                + "FOREIGN KEY(plantid) REFERENCES plants(plantid))";

        // done
        String habitat = "Create table habitat"
                + "(plantid varchar,"
                + "habitat varchar,"
                + "FOREIGN KEY(plantid) REFERENCES plants(plantid))";

        // done
        String edibility = "Create table edibility"
                + "(plantid varchar,"
                + "fruit varchar,"
                + "nuts varchar,"
                + "greens varchar,"
                + "roots varchar,"
                + "culinary varchar,"
                + "tea varchar,"
                + "other varchar,"
                + "FOREIGN KEY(plantid) REFERENCES plants(plantid))";

        // done
        String medicinal = "Create table medicinal (plantid varchar, medicinal varchar,"
                + "FOREIGN KEY(plantid) REFERENCES plants(plantid))";

        // done
        String height = "Create table height"
                + " (plantid varchar,"
                + " low int,"
                + " high int,"
                + "FOREIGN KEY(plantid) REFERENCES plants(plantid))";

        // done
        String width = "Create table width "
                + "(plantId varchar, "
                + " low int, "
                + "high int,"
                + "FOREIGN KEY(plantid) REFERENCES plants(plantid))";

        String form = "Create table form"
                + "(plantid varchar,"
                + "formtype varchar,"
                + "FOREIGN KEY(plantid) REFERENCES plants(plantid))";

        // done
        String rootPattern = "Create table rootPattern"
                + "(plantid varchar,"
                + "pattern varchar,"
                + "FOREIGN KEY(plantid) REFERENCES plants(plantid))";

        // done
        String growthRate = "Create table growthRate"
                + "(plantid varchar,"
                + "rate varchar,"
                + "FOREIGN KEY(plantid) REFERENCES plants(plantid))";

        // done
        String nativeRegion = "Create table nativeRegion"
                + "(plantid varchar,"
                + "region varchar,"
                + "FOREIGN KEY(plantid) REFERENCES plants(plantid))";

        Statement SQLquery = SQL.createStatement();

        SQLquery.execute(plants);
        System.out.println("Table created: plants");
        SQLquery.execute(plantZones);
        System.out.println("Table created: plantzones");
        SQLquery.execute(plantNames);
        System.out.println("Table created: plantNames");
        SQLquery.execute(light);
        System.out.println("Table created: light");
        SQLquery.execute(moisture);
        System.out.println("Table created: moisture");
        SQLquery.execute(soilph);
        System.out.println("Table created: soilph");
        SQLquery.execute(habit);
        System.out.println("Table created: habit");
        SQLquery.execute(habitat);
        System.out.println("Table created: habitat");
        SQLquery.execute(height);
        System.out.println("Table created: height");
        SQLquery.execute(width);
        System.out.println("Table created: width");
        SQLquery.execute(form);
        System.out.println("Table created: form");
        SQLquery.execute(rootPattern);
        System.out.println("Table created: rootPattern");
        SQLquery.execute(growthRate);
        System.out.println("Table created: growthrate");
        SQLquery.execute(nativeRegion);
        System.out.println("Table created: nativeregion");
        SQLquery.execute(edibility);
        System.out.println("Table created: edibility");
        SQLquery.execute(medicinal);
        System.out.println("Table Created: medicinal");

        SQLquery.close();
    }

    void dropTables() throws SQLException {
        Statement SQLquery = SQL.createStatement();
        for (String table : tables) {
            SQLquery.execute("DROP TABLE if exists " + table);
            System.out.println("Dropped: " + table);
        }
        SQLquery.close();
    }

    Connection openConnection(String database) throws SQLException {
        String url = "jdbc:sqlite:" + database;
        return DriverManager.getConnection(url);
    }

    void closeConnection() {
        try {
            SQL.close();
            System.out.println("Closing DB");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
