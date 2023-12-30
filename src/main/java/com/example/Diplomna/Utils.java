package com.example.Diplomna;

//клас бере назву відео і додає розширення крапку
public final class Utils {
    public static String removeFileExt(String fileName) {
        int extIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, extIndex);
    }
}
