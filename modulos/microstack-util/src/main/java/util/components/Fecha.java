package util.components;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.tapestry5.annotations.Parameter;

public class Fecha {
    @Parameter
    Object valor;

    public String getFechaFormateada() {
        if (valor instanceof LocalDate) {
            return ((LocalDate) valor)
                .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        } else if (valor instanceof Timestamp) {
            return FastDateFormat
                .getInstance("d 'de' MMMM 'de' yyyy")
                .format((Timestamp) valor);
        } else {
            return "No soportado!!";
        }
    }

}
