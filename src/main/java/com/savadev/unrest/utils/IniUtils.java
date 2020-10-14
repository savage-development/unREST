package com.savadev.unrest.utils;

import org.apache.commons.lang3.StringUtils;
import org.ini4j.Ini;
import org.ini4j.Profile;

import java.util.Optional;

public class IniUtils {

    public static Profile.Section getSection(Ini ini, String key, boolean quoted) {
        if (quoted) {
            if (!key.startsWith("\"")) {
                key = "\"" + key;
            }

            if (!key.endsWith("\"")) {
                key = key + "\"";
            }
        } else {
            if (key.startsWith("\"")) {
                key = StringUtils.stripStart(key, "\"");
            }

            if (key.endsWith("\"")) {
                key = StringUtils.stripEnd(key, "\"");
            }
        }
        return ini.get(key);
    }

    public static Optional<String> getValue(Profile.Section section, String key, boolean quoted) {
        return Optional.ofNullable(section.get(quoted ? "\"" + key + "\"" : key))
                .map(s -> s.replaceAll("\"", ""))
                .filter(s -> !StringUtils.isEmpty(s));
    }

    public static Optional<String> getValue(Profile.Section section, String key) {
        return getValue(section, key, false);
    }

    public static <V> Optional<V> getValue(Profile.Section section, String key, Class<V> clazz, boolean quoted) {
        return Optional.ofNullable(section.get(quoted ? "\"" + key + "\"" : key, clazz));
    }

    public static <V> Optional<V> getValue(Profile.Section section, String key, Class<V> clazz) {
        return getValue(section, key, clazz, false);
    }

//    public static void main(String[] args) {
//        var report = """
//                smartctl 7.1 2019-12-30 r5022 [x86_64-linux-4.19.107-Unraid] (local build)
//                Copyright (C) 2002-19, Bruce Allen, Christian Franke, www.smartmontools.org
//
//                === START OF READ SMART DATA SECTION ===
//                SMART Attributes Data Structure revision number: 16
//                Vendor Specific SMART Attributes with Thresholds:
//                ID# ATTRIBUTE_NAME          FLAG     VALUE WORST THRESH TYPE      UPDATED  WHEN_FAILED RAW_VALUE
//                  1 Raw_Read_Error_Rate     0x002f   200   200   051    Pre-fail  Always       -       0
//                  3 Spin_Up_Time            0x0027   169   169   021    Pre-fail  Always       -       6525
//                  4 Start_Stop_Count        0x0032   100   100   000    Old_age   Always       -       50
//                  5 Reallocated_Sector_Ct   0x0033   200   200   140    Pre-fail  Always       -       0
//                  7 Seek_Error_Rate         0x002e   100   253   000    Old_age   Always       -       0
//                  9 Power_On_Hours          0x0032   075   075   000    Old_age   Always       -       18291
//                 10 Spin_Retry_Count        0x0032   100   253   000    Old_age   Always       -       0
//                 11 Calibration_Retry_Count 0x0032   100   253   000    Old_age   Always       -       0
//                 12 Power_Cycle_Count       0x0032   100   100   000    Old_age   Always       -       41
//                192 Power-Off_Retract_Count 0x0032   200   200   000    Old_age   Always       -       31
//                193 Load_Cycle_Count        0x0032   199   199   000    Old_age   Always       -       3314
//                194 Temperature_Celsius     0x0022   107   091   000    Old_age   Always       -       43
//                196 Reallocated_Event_Count 0x0032   200   200   000    Old_age   Always       -       0
//                197 Current_Pending_Sector  0x0032   200   200   000    Old_age   Always       -       0
//                198 Offline_Uncorrectable   0x0030   100   253   000    Old_age   Offline      -       0
//                199 UDMA_CRC_Error_Count    0x0032   200   200   000    Old_age   Always       -       0
//                200 Multi_Zone_Error_Rate   0x0008   200   200   000    Old_age   Offline      -       0""";
//
//        Pattern pattern = Pattern.compile("^\\s{0,2}(\\d{1,3}\\s+.*)$",
//                Pattern.MULTILINE);
//        Matcher matcher = pattern.matcher(report);
//        while (matcher.find()) {
//            String[] tokens = matcher.group(1).split("\\s+");
//            System.out.println(Arrays.toString(tokens));
//        }
//    }

}
