package com.vish.cucumber;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by vish on 13/7/16.
 */
public class app {

    /**
     * commands to be executed
     */
    private static enum command { CREATE, PARK, LEAVE, STATUS, QUERY_REG_CARS_BY_COLOR, QUERY_SLOT_CARS_BY_COLOR, QUERY_SLOT_CAR_BY_REG};

    /** an object representing a car.*/
    public class Car {
        private String licenseNumber;
        private String color;
        public Car(String l, String c) {
            this.licenseNumber = l;
            this.color = c;
        }
        private String getLicenseNumber() { return licenseNumber; }
        private String getColor() { return color; }
    }

    /**
     * represents a parking lot. call the constructor to initialize a new parking lot.
     *
     */
    public class ParkingLot {
        /** the number of slots available. */
        private int totalSlots;
        private Car[] parkingAllocations;
        public ParkingLot(int s) {
            this.totalSlots = s;
            parkingAllocations = new Car[totalSlots];
        }
        public int getTotalSlots() {
            return totalSlots;
        }

        public void getStatus() {
            boolean allFree = true;
            for (int i = 0; i< parkingAllocations.length; i++) {
                if (parkingAllocations[i] != null) allFree = false;
            }
            if (allFree) {
                log("all slots are free");
            } else {
                log("Slot No.\tRegistration No.\tColour");
            }
            for (int i = 0; i< parkingAllocations.length; i++) {
                Car c = parkingAllocations[i];
                if (c != null)
                    log((i + 1) + "\t" + c.getLicenseNumber() + "\t" + c.getColor());
            }
        }

        /**
         * allocate a parking slot if not already allocated.
         * @param licenseNumber
         * @param color
         */
        public void allocateParking(String licenseNumber, String color) {
            Car c = new Car(licenseNumber,color);
            boolean allocated = false;
            //add it to nearest available slot
            for (int i=0; i< parkingAllocations.length; i++) {
                if (parkingAllocations[i] == null) {
                    parkingAllocations[i] = c;
                    log("Allocated slot number: " + (i + 1));
                    allocated = true;
                    break;
                }
            }
            if (!allocated){
                log("Sorry, parking lot is full");
            }
        }

        /**
         * Free an already allocated parking slot.
         * @param slotToFree 1-based integer representing the parking slot to free.
         */
        public void freeParking(int slotToFree)  {
            guard();
            if (parkingAllocations.length < slotToFree) {
                System.err.println("slot to free should be between 1 and " + parkingAllocations.length);
                System.exit(1);
            }
            if (parkingAllocations[slotToFree-1] == null) {
                log("slot is already free");
            } else {
                parkingAllocations[slotToFree-1] = null;
                log("Slot number " + slotToFree + " is free");
            }
        }

        /**
         * Query registration numbers or slots by color or license number.
         * @param query
         * @param queryType "color", "reg"
         * @param returnType "reg", "slot"
         */
        public void doQuery(String query, String queryType, String returnType) {

            List<String> retVal = new ArrayList<String>();

            guard();
            for (int i=0; i<parkingAllocations.length; i++) {
                if (parkingAllocations[i] != null) {
                    if (queryType.equals("color")) {
                        if (parkingAllocations[i].getColor().equals(query)) {
                            if (returnType.equals("reg")) {
                                retVal.add(parkingAllocations[i].getLicenseNumber());
                            }
                            else if (returnType.equals("slot")) {
                                retVal.add(String.valueOf(i + 1));
                            }
                        }
                    } else if (queryType.equals("reg")) {
                        if (parkingAllocations[i].getLicenseNumber().equals(query)) {
                            if (returnType.equals("slot")) {
                                retVal.add(String.valueOf(i + 1));
                            }
                        }
                    }
                }
            }

            //print not found
            if (retVal.size() == 0)
                System.out.println("Not found");

            //print
            for (int i = 0; i<retVal.size(); i++) {
                System.out.print(retVal.get(i));
                //trying to get the commas right
                if (i == retVal.size() - 1)
                    System.out.println();
                else
                    System.out.print(", ");
            }
        }

        private void guard() {
            if (parkingAllocations.length == 0 ||
                    totalSlots == 0) {
                System.err.println("parking lot not initialized yet!");
                System.exit(1);
            }

        }
    }

    public ParkingLot parkingLot;

    /**
     * empty constructor
     */
    public app() {
    }

    /**
     * TODO
     */
    private static void doInteractiveMode() {
        Scanner in = new Scanner(System.in);
        System.out.println("How many parking slots do you want?");
        if  (!in.hasNextInt()) {
            System.err.println("Invalid Value");
            System.exit(1);
        }
        int v = in.nextInt();
        if (v <= 0) {
            System.err.println("Invalid Value");
            System.exit(1);
        }
    }

    private void log(String m) {
        System.out.println(m);
    }


    /**
     * Parse a line (either from console or file) and return a {@link command} object
     * representing that command.
     * @param line
     * @return
     */
    private Map<command, String> getCommandFromLine(String line) {
        command c = null;
        String s = null;
        Map<command, String> retVal = new HashMap<command,String>();
        if (line.startsWith("create_parking_lot")) {
            c = command.CREATE;
            s = line.split("create_parking_lot")[1].trim();
        } else if (line.startsWith("park")) {
            c = command.PARK;
            s = line.split("park")[1].trim();
        } else if (line.startsWith("leave")) {
            c = command.LEAVE;
            s = line.split("leave")[1].trim();
        } else if (line.startsWith("status")) {
            c = command.STATUS;
        } else if (line.startsWith("registration_numbers_for_cars_with_colour")) {
            c = command.QUERY_REG_CARS_BY_COLOR;
            s = line.split("registration_numbers_for_cars_with_colour")[1].trim();
        } else if (line.startsWith("slot_numbers_for_cars_with_colour")) {
            c = command.QUERY_SLOT_CARS_BY_COLOR;
            s = line.split("slot_numbers_for_cars_with_colour")[1].trim();
        } else if (line.startsWith("slot_number_for_registration_number")) {
            c = command.QUERY_SLOT_CAR_BY_REG;
            s = line.split("slot_number_for_registration_number")[1].trim();
        } else {
            System.err.println("unknown command " + line);
            System.exit(1);
        }
        retVal.put(c,s);
        return retVal;
    }

    private void executeCommand(Map<command, String> c) {
        if (c.containsKey(command.CREATE)) {
            parkingLot = new ParkingLot(Integer.parseInt(c.get(command.CREATE)));
            log("Created a parking lot with " + parkingLot.getTotalSlots() + " slots");
        } else if (c.containsKey(command.STATUS)) {
            parkingLot.getStatus();
        } else if (c.containsKey(command.PARK)) {
            String[] splits = c.get(command.PARK).split("\\s");
            parkingLot.allocateParking(splits[0].trim(),splits[1].trim());
        } else if (c.containsKey(command.LEAVE)) {
            int slotToLeave = Integer.parseInt(c.get(command.LEAVE).trim());
            parkingLot.freeParking(slotToLeave);
        } else if (c.containsKey(command.QUERY_REG_CARS_BY_COLOR)) {
            String query = c.get(command.QUERY_REG_CARS_BY_COLOR).trim();
            parkingLot.doQuery(query,"color","reg");
        } else if (c.containsKey(command.QUERY_SLOT_CAR_BY_REG)) {
            String query = c.get(command.QUERY_SLOT_CAR_BY_REG).trim();
            parkingLot.doQuery(query,"reg","slot");
        } else if (c.containsKey(command.QUERY_SLOT_CARS_BY_COLOR)) {
            String query = c.get(command.QUERY_SLOT_CARS_BY_COLOR).trim();
            parkingLot.doQuery(query,"color","slot");
        } else {
            System.err.println("unknown command " + c);
            System.exit(1);
        }
    }



    /**
     * parse text file for commands
     */
    private void executeCommandsFromFile(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                executeCommand(getCommandFromLine(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String... args) {
        if (args.length == 0) {
            //interactive mode
            System.out.println("in interactive mode");
        } else if (args.length > 1) {
            System.err.println("invalid argument count!");
            System.exit(1);
        }
        //file mode
        String fileName = System.getProperty("user.dir") + File.separator +
                "app" + File.separator + "inputs" + File.separator + args[0];
        if (!new File(fileName).exists()) {
            System.err.println(fileName + " not found");
            System.exit(1);
        }

        System.out.println("in file mode");
        app a = new app();
        a.executeCommandsFromFile(fileName);

    }
}
