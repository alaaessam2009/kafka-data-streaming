package com.demo.service;

import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CsvStrategy<T> extends HeaderColumnNameTranslateMappingStrategy<T> {

    private final boolean writeHeader;

    public CsvStrategy(Class<T> type, boolean writeHeader) {
        this.writeHeader = writeHeader;
        Map<String, String> map = new HashMap<>();
        for (Field field : type.getDeclaredFields()) {
            map.put(field.getName(), field.getName());
        }
        setType(type);
        setColumnMapping(map);
    }

    @Override
    public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {
        String[] result = super.generateHeader(bean);
        for (int i = 0; i < result.length; i++) {
            result[i] = getColumnName(i);
        }
        if (writeHeader) {
            return result;
        } else {
            return new String[0];
        }

    }
}