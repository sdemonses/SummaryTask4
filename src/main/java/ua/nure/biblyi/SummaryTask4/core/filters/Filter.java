package ua.nure.biblyi.SummaryTask4.core.filters;

import java.util.List;

/**
 * Created by Dimasyk on 20.01.2017.
 */
public interface Filter<T> {

    List<T> filter(List<T> list);

}
