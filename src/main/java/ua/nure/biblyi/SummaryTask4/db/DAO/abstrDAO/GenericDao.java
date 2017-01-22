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
     * Create new record in database  corresponding to the object
     * @param object The object to be inserted
     */
    T insert(T object) throws DAOException;

    /**
     * Return object from database  corresponding personal key
     * @param key Personal key object
     */
    T getByPK(PK key) throws DAOException;

    /**
     * Update information record in database  corresponding to the object
     * @param object the object to be updated
     */
    void update(T object) throws DAOException;

    /**
     * Delete record from database
     * @param id Personal key object
     */
    void delete(PK id) throws DAOException;

    /**
     * Return all record from database
     */
    List<T> getAll() throws DAOException;
}
