package Model.TableModel;

import Model.SQL.Movie;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class MovieTableModel implements TableModel {
    private List<Movie> movies;
    private String[] columnNames = {"Title", "Year", "Rating", "Plot", "Length"};
    private List<TableModelListener> listeners = new ArrayList<>();

    public MovieTableModel(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // Define the data type for each column
        switch (columnIndex) {
            case 0: return String.class;  // Title
            case 1: return Integer.class; // Year
            case 2: return Integer.class;  // Rating
            case 3: return String.class;  // Plot
            case 4: return Integer.class; // Length
            default: return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Allow editing for specific columns, e.g., Title, Year, and Rating
        return columnIndex == 0 || columnIndex == 1
                || columnIndex == 2 || columnIndex == 3
                || columnIndex == 4 || columnIndex == 5;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movie movie = movies.get(rowIndex);
        switch (columnIndex) {
            case 0: return movie.getTitle();
            case 1: return movie.getRelease_year();
            case 2: return movie.getRating();
            case 3: return movie.getPlot();
            case 4: return movie.getDuration();
            default: return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Movie movie = movies.get(rowIndex); // Get the movie at the specified row
        switch (columnIndex) {
            case 0:
                movie.setTitle((String) aValue);
                break;
            case 1:
                movie.setRelease_year((Integer) aValue);
                break;
            case 2:
                movie.setRating((Integer) aValue);
                break;
            case 3:
                movie.setPlot((String) aValue);
                break;
            case 4:
                movie.setDuration((Integer) aValue);
                break;
            default:
                throw new IllegalArgumentException("Invalid column index");
        }
                // Notify listeners that the data has changed
                for (TableModelListener listener : listeners) {
                    listener.tableChanged(new javax.swing.event.TableModelEvent(this, rowIndex, rowIndex, columnIndex));
                }
        }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }
}
