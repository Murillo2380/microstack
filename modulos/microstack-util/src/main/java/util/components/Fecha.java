package util.components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.apache.tapestry5.annotations.Parameter;

public class Fecha {
    @Parameter
    LocalDate valor;

    public String getFechaFormateada() {
        return valor.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }
}
