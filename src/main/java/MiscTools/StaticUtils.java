package MiscTools;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.function.Function;

public class StaticUtils {
    public static <E,F> E tryParse(Function<F,E> parsefun, F object, E other){
        try {
            return parsefun.apply(object);
        } catch (Exception e) {
            return other;
        }
    }
    
    public static <E,F> E tryParse(Function<F,E> parsefun, F object){
        return tryParse(parsefun, object,null);
    }
    
    public static Instant parseDateStringToInstant(String dateString){
        return LocalDate.parse(dateString).atStartOfDay().toInstant(ZoneOffset.UTC);
    }
    
}
