package ru.example.util;

import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component(service = DateUtils.class)
public class DateUtils {


    public static final ThreadLocal< SimpleDateFormat > SIMPLE_MONTH_PATTERN =
            ThreadLocal.withInitial( () -> new SimpleDateFormat( "dd-MMMM-yyyy", new Locale( "ru" ) ) );

    public static String formatDate(  Date date ) {
        String result = null;
        if ( date != null ) {
            try {
                result = SIMPLE_MONTH_PATTERN.get().format(date);
            } catch ( Exception ex ) {
                ex.printStackTrace();
            }
        }
        return result;
    }

}
