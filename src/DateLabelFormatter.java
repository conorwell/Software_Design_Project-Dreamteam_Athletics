import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//this is external code used for the date chooser
//I have altered it to fit into the project
//https://stackoverflow.com/questions/26794698/how-do-i-implement-jdatepicker
public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    //No tests made for this class because no methods are used.
    //Date pattern instance variable is needed
    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}