package com.savadev.unrest.service.disk;

import com.savadev.unrest.domain.disk.Disk;

import java.util.function.Predicate;

public class Disks {

    public static Predicate<Disk> isFlash() {
        return Disk::isFlash;
    }

    public static Predicate<Disk> isNotFlash() {
        return disk -> !disk.isFlash();
    }

    public static Predicate<Disk> isData() {
        return Disk::isData;
    }

    public static Predicate<Disk> isCache() {
        return Disk::isCache;
    }

    public static Predicate<Disk> isParity() {
        return Disk::isParity;
    }

    public static Predicate<Disk> byIdx(int idx) {
        return disk -> idx == disk.getIdx();
    }

}
