package antvictor.study.utils;

public class IntegerCache {
    static final int low = -128;
    static final int high;
    static final Integer cache[];

    static {
        // high value may be configured by property
        int h = 12;
        String integerCacheHighPropValue =
//                sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
                null;
        if (integerCacheHighPropValue != null) {
            try {
                int i = Integer.parseInt(integerCacheHighPropValue);
                i = Math.max(i, 127);
                // Maximum array size is Integer.MAX_VALUE
                h = Math.min(i, Integer.MAX_VALUE - (-low) - 1);
            } catch (NumberFormatException nfe) {
                // If the property cannot be parsed into an int, ignore it.
            }
        }
        high = h;

        cache = new Integer[(high - low) + 1];
        int j = low;
        for (int k = 0; k < cache.length; k++)
            cache[k] = new Integer(j++);

        // range [-128, 127] must be interned (JLS7 5.1.7)
        assert IntegerCache.high >= 127 : "IntegerCache.high not <= 126";
    }

    public static void main(String[] args) {
        IntegerCache integerCache = new IntegerCache();
    }
}