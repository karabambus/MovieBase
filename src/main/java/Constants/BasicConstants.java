package Constants;

import java.awt.*;

public class BasicConstants {

    public static final Color PRIMARY_COLOR = Color.decode("#4E28A7");
    public static final Color SECONDARY_COLOR = Color.decode("#5080BF");
    public static final Color TEXT_COLOR = Color.decode("#C3E6C0");
    public static final Font PARAGRAPH_FONT = new Font("Arial", Font.PLAIN, 12);
    public static final Font HEADER_1_FONT = new Font("Arial", Font.BOLD, 12);
    public static final Font HEADER_2_FONT = new Font("Arial", Font.BOLD, 12);

    //JTablee columns
    public static final String[] ALL_MOVIE_COLUMNS = new String[]{"id_movie", "title", "year", "rating", "plot", "duration"};
}
