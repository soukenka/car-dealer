package com.soukenka.cardealer.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utilities manipulating with String data type.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 10.05.2025
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {
    /**
     * Removes the specified suffix from the given string if it is present.
     *
     * @param str    The original string from which the suffix is to be removed.
     * @param suffix The suffix to be removed from the original string.
     * @return The string after removing the suffix, or the original string if the suffix is not present.
     */
    public static String removeSuffix(String str, String suffix) {
        if (str == null) {
            return null;
        }
        if (suffix != null && str.endsWith(suffix)) {
            return str.substring(0, str.length() - suffix.length());
        }
        return str;
    }

    /**
     * Converts a CamelCase string to a snake_case string.
     *
     * @param camelCase The CamelCase string to be converted.
     * @return The converted snake_case string.
     */
    public static String convertCamelToSnake(String camelCase) {
        if (camelCase == null) {
            return null;
        }
        return camelCase
                // Insert an underscore between a lowercase letter followed by an uppercase letter
                .replaceAll("([a-z])([A-Z0-9])", "$1_$2")
                // Insert an underscore between an uppercase letter and an uppercase letter followed by a lowercase letter
                .replaceAll("([A-Z0-9])([A-Z0-9][a-z])", "$1_$2")
                .toLowerCase();
    }
}
