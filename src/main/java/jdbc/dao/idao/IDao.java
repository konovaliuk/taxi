package jdbc.dao.idao;

import jdbc.dto.Dto;
import jdbc.exception.DaoException;

public interface IDao<T extends Dto,K extends DaoException> {

    T insert(T dto) throws K;

    int update(Integer pk, T dto) throws K;

    int delete(Integer pk) throws K;

    T findByPrimaryKey(Integer pk) throws K;

    T[] findAll() throws K;

    T[] findByWhere(String where, Object[] sqlParams) throws K;

    int countAll() throws K;

    int countByWhere(String where, Object[] sqlParams) throws K;
}
