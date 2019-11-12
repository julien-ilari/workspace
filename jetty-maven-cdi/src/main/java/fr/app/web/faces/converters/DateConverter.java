package fr.app.web.faces.converters;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;


@FacesConverter(forClass=Calendar.class)
public class DateConverter extends DateTimeConverter {

    private static final String FORMAT_DATE = "dd/MM/yyyy HH:mm:ss";

    public DateConverter() {
        setPattern(FORMAT_DATE);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.length() != getPattern().length()) {
            throw new ConverterException("Format date invalide.");
        }
        return super.getAsObject(context, component, value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        if (value == null) {
            return null;
        }

        Date date = (Date) value;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);

        try {
            return sdf.format(date);

        } catch (Exception e) {
            return null;
        }
    }

}
