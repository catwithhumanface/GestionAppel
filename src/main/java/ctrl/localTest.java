package ctrl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class localTest {
    private static final SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();

        System.out.println(DF.format(c.getTime()));
    }
}
