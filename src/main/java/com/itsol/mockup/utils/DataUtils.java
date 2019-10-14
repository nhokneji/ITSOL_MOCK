package com.itsol.mockup.utils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author anhvd_itsol
 */

public class DataUtils {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DataUtils.class);

    public static boolean isNullOrZero(Long value) {
        return (value == null || value.equals(0L));
    }

    public static boolean isNullOrEmpty(String value) {
        return (value == null || value.isEmpty());
    }

    public static boolean isNullOrZero(BigDecimal value) {
        return (value == null || value.compareTo(BigDecimal.ZERO) == 0);
    }

    public static Long safeToLong(Object obj1, Long defaultValue) {
        Long result = defaultValue;
        if (obj1 != null) {
            try {
                result = Long.parseLong(obj1.toString());
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }

        return result;
    }

    public static Long safeToLong(Object obj1) {
        Long result = null;
        if (obj1 != null) {
            try {
                result = Long.parseLong(obj1.toString());
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }

        return result;
    }

    public static BigDecimal safeToBigDecimal(Object obj1) {
        BigDecimal result = new BigDecimal(0);
        if (obj1 != null) {
            try {
                result = BigDecimal.valueOf(Long.parseLong(obj1.toString()));
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }

        return result;
    }

    public static Double safeToDouble(Object obj1) {
        Double result = null;
        if (obj1 != null) {
            try {
                result = Double.parseDouble(obj1.toString());
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }

        return result;
    }

    public static Short safeToShort(Object obj1) {
        Short result = 0;
        if (obj1 != null) {
            try {
                result = Short.parseShort(obj1.toString());
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }

        return result;
    }

    public static int safeToInt(Object obj1) {
        int result = 0;
        if (obj1 == null) {
            return 0;
        }
        try {
            result = Integer.parseInt(obj1.toString());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        return result;
    }

    public static String safeToString(Object obj1) {
        if (obj1 == null) {
            return "";
        }

        return obj1.toString();
    }

    public static Date safeToDate(Object obj1) {
        Date result = null;
        if (obj1 == null) {
            return null;
        }
        try {
            result = (Date) obj1;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    public static boolean isNumber(String strId) {
        return strId.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isLong(String strId) {
        return strId.matches("\\d*");
    }

    public static boolean isNullOrZero(Short value) {
        return (value == null || value.equals(Short.parseShort("0")));
    }

    public static boolean isNullOrZero(Integer value) {
        return (value == null || value.equals(Integer.parseInt("0")));
    }
}
