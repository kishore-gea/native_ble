/*
 * Copyright GE Appliances, a Haier Company (Confidential). All rights reserved.
 */

package com.ge.cafe.applianceUI.IceMaker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.ge.cafe.a_UnderConstruction.common.erd.advantium.Erd0x0035;

import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

public class Erd0x0035Test {

    private static final String zeroPrefix = "000000";
    private static final String[] prefixes = new String[] {
            zeroPrefix,
            "010101",
            "012301",
            "7fffff"
    };

    private static final String isNotDispenser = "00";
    private static final String isDispenser = "01";

    private static final String personalityUnknown = "00";
    private static final String personality120vCafe = "01";
    private static final String personality120vMonogram = "02";
    private static final String personality240vMonogram = "09";
    private static final String personality240vCafe = "0A";

    private static final Map<String, Erd0x0035.UnitType> testPairs = new TreeMap<>();
    static {
        testPairs.put(personalityUnknown, Erd0x0035.UnitType.UNKNOWN);
        testPairs.put(personality120vCafe, Erd0x0035.UnitType.TYPE_120V_CAFE);
        testPairs.put(personality120vMonogram, Erd0x0035.UnitType.TYPE_120V_MONOGRAM);
        testPairs.put(personality240vMonogram, Erd0x0035.UnitType.TYPE_240V_MONOGRAM);
        testPairs.put(personality240vCafe, Erd0x0035.UnitType.TYPE_240V_CAFE);
    }

    private static final String personalityWasher959 = "01";
    private static final String personalityWasher979 = "02";
    private static final String personalityDryer959 = "03";
    private static final String personalityDryer979 = "04";

    private static final Map<String, Erd0x0035.HaierLaundryTypes> laundryTestPairs = new TreeMap<>();
    static {
        laundryTestPairs.put(personalityUnknown, Erd0x0035.HaierLaundryTypes.UNKNOWN);
        laundryTestPairs.put(personalityWasher959, Erd0x0035.HaierLaundryTypes.WASHER_959_UI);
        laundryTestPairs.put(personalityWasher979, Erd0x0035.HaierLaundryTypes.WASHER_979_UI);
        laundryTestPairs.put(personalityDryer959, Erd0x0035.HaierLaundryTypes.DRYER_959_UI);
        laundryTestPairs.put(personalityDryer979, Erd0x0035.HaierLaundryTypes.DRYER_979_UI);
    }

    @Test
    public void isDispenser() {
        String emptyDispenserData = "";
        Erd0x0035 defaultDispenserPersonality = new Erd0x0035(emptyDispenserData);
        assertFalse("Erd0x0035 with empty string defaults to not a dispenser.",
                defaultDispenserPersonality.isDispenser);

        String isNotDispenserData = zeroPrefix + isNotDispenser;
        Erd0x0035 notDispenserAppliance = new Erd0x0035(isNotDispenserData);
        assertFalse("Erd0x0035 with raw value " + isNotDispenserData + " indicates appliance is not a dispenser.",
                notDispenserAppliance.isDispenser);

        String isDispenserData = zeroPrefix + isDispenser;
        Erd0x0035 dispenserAppliance = new Erd0x0035(isDispenserData);
        assertTrue("Erd0x0035 with data " + isDispenserData + " indicates appliance is a dispenser.",
                dispenserAppliance.isDispenser);

        // behavior is undefined for all other prefixes/values
    }

    @Test
    public void unitType() {
        for (Map.Entry<String, Erd0x0035.UnitType> pair : testPairs.entrySet()) {
            String data = pair.getKey();
            Erd0x0035.UnitType type = pair.getValue();

            for (String prefix : prefixes) {
                String dataString = prefix + data;
                Erd0x0035 appliancePersonality = new Erd0x0035(dataString);

                assertEquals("Advantium personality for data " + dataString + " is " + type,
                        type, appliancePersonality.getUnitType());
            }
        }
    }

    @Test
    public void haierLaundryTypes() {
        for (Map.Entry<String, Erd0x0035.HaierLaundryTypes> pair: laundryTestPairs.entrySet()) {
            String data = pair.getKey();
            Erd0x0035.HaierLaundryTypes type = pair.getValue();

            for (String prefix: prefixes) {
                String dataString = prefix + data;
                Erd0x0035 appliancePersonality = new Erd0x0035(dataString);

                assertEquals("Haier laundry personality for data " + dataString + " is " + type,
                        type, appliancePersonality.getHaierLaundryTypes());
            }
        }
    }

    @Test
    public void is120Advantium() {
        for (Map.Entry<String, Erd0x0035.UnitType> pair : testPairs.entrySet()) {
            String data = pair.getKey();
            Erd0x0035.UnitType type = pair.getValue();

            for (String prefix : prefixes) {
                String dataString = prefix + data;
                Erd0x0035 appliancePersonality = new Erd0x0035(dataString);

                if (type == Erd0x0035.UnitType.TYPE_120V_CAFE || type == Erd0x0035.UnitType.TYPE_120V_MONOGRAM) {
                    assertTrue("Advantium personality for data " + dataString + " is 120v",
                            appliancePersonality.is120VAdvantium());
                } else {
                    assertFalse("Advantium personality for data " + dataString + " is not 120v",
                            appliancePersonality.is120VAdvantium());
                }
            }
        }
    }

}
