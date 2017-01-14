package ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO;

/**
 * Created by dmitry on 14.01.17.
 */

import ua.nure.biblyi.SummaryTask4.db.entity.Entity;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;

import java.io.Serializable;
import java.util.List;

/**
 * Унифицированный интерфейс управления персистентным состоянием объектов
 *
 * @param <T>  тип объекта персистенции
 * @param <PK> тип первичного ключа
 */
public interface GenericDao<T extends Entity, PK extends Serializable> {

    /**
     * Создает новую запись и соответствующий ей объект
     */
    T create() throws DAOException;

    /**
     * Создает новую запись, соответствующую объекту object
     */
    T insert(T object) throws DAOException;

    /**
     * Возвращает объект соответствующий записи с первичным ключом key или null
     */
    T getByPK(PK key) throws DAOException;

    /**
     * Сохраняет состояние объекта group в базе данных
     */
    void update(T object) throws DAOException;

    /**
     * Удаляет запись об объекте из базы данных
     */
    void delete(T object) throws DAOException;

    /**
     * Возвращает список объектов соответствующих всем записям в базе данных
     */
    List<T> getAll() throws DAOException;
}
