package permadb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Steve Rich
 *
 * Expected Structures of list/checkbox
 *
 * Lists: 0: light, 1: zone, 2: moisture, 3: habitat, 4: results
 *
 * Checkbox[y,n]: n2 fixer[0,1], mineral[2,3], invert shelter[4,5]
 * groundCover[6,7], poison: [8,9]
 */
public class Core {

    private boolean first = true;
    Connection SQL;

    public Core(Connection SQL) {
        this.SQL = SQL;
    }

    //  Returns distinct values for list. Works when column name is the table name
    String[] getListValues(String table) throws SQLException {
        String query = "select distinct " + table + " from " + table + " order by " + table + " asc";
        return getResultArr(query, table);
    }

    // Main event updater, calls and executes new query on each button/checkbox event
    void updateResults(ArrayList<javax.swing.JList> lists, ArrayList<javax.swing.JCheckBox> boxList) {
        try {
            ArrayList<String> output = new ArrayList<>();
            String query = getParams(lists, boxList);
            PreparedStatement SQLquery = SQL.prepareStatement(query);
            ResultSet results = SQLquery.executeQuery();
            while (results.next()) {
                output.add(results.getString("genus") + " " + results.getString("species"));
            }
            String[] listData = new String[]{};
            lists.get(4).setListData(output.toArray(listData));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Polls each list and all check boxes to build the search query
    String getParams(ArrayList<javax.swing.JList> lists, ArrayList<javax.swing.JCheckBox> boxList) {
        first = true; // reset global, determines where/or in query
        String nameType = "genus, species ";
        StringBuilder query = new StringBuilder("Select " + nameType + " from plants ");

        StringBuilder[] queryArr = new StringBuilder[2];
        queryArr[0] = query;
        queryArr[1] = new StringBuilder();

        queryArr = appendParamsArr(queryArr, getListParams(lists.get(0), "light"), "light");
        queryArr = appendParamsArr(queryArr, getListParams(lists.get(1), "zone"), "zone");
        queryArr = appendParamsArr(queryArr, getListParams(lists.get(2), "moisture"), "moisture");
        queryArr = appendParamsArr(queryArr, getListParams(lists.get(3), "habitat"), "habitat");

        query.append(getCheckBoxParams(boxList));
        //System.out.println(queryArr[0].toString() + " " + queryArr[1].toString());  // Prints full select string
        return queryArr[0].toString() + " " + queryArr[1].toString();
    }

    // Handles each value, assigns "Where" if it's the first in the list, else "And"
    StringBuilder appendParams(StringBuilder input, String[] inputArray) {
        for (String value : inputArray) {
            input.append(getConditional()).append(value);
        }
        return input;
    }

    // Struct: input[0] contains all tables and joins, input[1] contains all conditionals
    StringBuilder[] appendParamsArr(StringBuilder input[], String[] inputArray, String table) {
        int counter = 0;
        for (String join : inputArray) {
            input[0].append(" INNER JOIN " + table + " " + table + counter + " on plants.plantid = " + table + counter + ".plantid");
            if (first) {
                input[1].append(" WHERE " + table + counter).append(join);
                first = false;
            } else {
                input[1].append(" AND " + table + counter).append(join);
            }
            counter++;
        }
        return input;
    }

    // Handles all of the checkboxes for plant tables, only conditionals so returns String
    String getCheckBoxParams(ArrayList<javax.swing.JCheckBox> boxList) {
        StringBuilder output = new StringBuilder();

        if (boxList.get(0).isSelected()) {
            output.append(getConditional()).append("plants.n2Fixer = 1 ");
        } else if (boxList.get(1).isSelected()) {
            output.append(getConditional()).append("plants.n2Fixer = 0 ");
        }

        if (boxList.get(2).isSelected()) {
            output.append(getConditional()).append("plants.mineralAccum = 1 ");
        } else if (boxList.get(3).isSelected()) {
            output.append(getConditional()).append("plants.mineralAccum = 0 ");
        }

        if (boxList.get(4).isSelected()) {
            output.append(getConditional()).append("plants.invertShelter = 1 ");
        } else if (boxList.get(5).isSelected()) {
            output.append(getConditional()).append("plants.invertShelter = 0 ");
        }

        if (boxList.get(6).isSelected()) {
            output.append(getConditional()).append("plants.groundCover = 1 ");
        } else if (boxList.get(7).isSelected()) {
            output.append(getConditional()).append("plants.groundCover = 0 ");
        }

        if (boxList.get(8).isSelected()) {
            output.append(getConditional()).append("plants.poison = 1 ");
        } else if (boxList.get(9).isSelected()) {
            output.append(getConditional()).append("plants.poison = 0 ");
        }

        return output.toString();
    }

    // Returns and/where depending on status of global boolean 'first'
    String getConditional() {
        String output = " AND ";
        if (first) {
            output = " WHERE ";
            first = false;
        }
        return output;
    }

    // Retrieves list selections, assists in select query build
    String[] getListParams(javax.swing.JList<String> inputList, String table) {
        int listSize = inputList.getModel().getSize();
        String[] listContents = new String[listSize];
        for (int i = 0; i < listSize; i++) {
            listContents[i] = (inputList.getModel().getElementAt(i));
        }

        int[] listIndex = inputList.getSelectedIndices();
        String[] output = new String[listIndex.length];
        for (int i = 0; i < listIndex.length; i++) {
            output[i] = "." + table + "= '" + listContents[listIndex[i]] + "'";
        }
        return output;
    }

    // Returns array of simple query with single column result
    String[] getResultArr(String query, String column) {
        ArrayList<String> outputArr = new ArrayList<>();
        try {
            PreparedStatement SQLquery = SQL.prepareStatement(query);
            ResultSet results = SQLquery.executeQuery();
            while (results.next()) {
                outputArr.add(results.getString(column));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        String[] output = new String[outputArr.size()];
        return outputArr.toArray(output);
    }
}