/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.idm.engine.impl.persistence.entity.data.impl;

import java.util.List;
import java.util.Map;

import org.flowable.engine.common.impl.Page;
import org.flowable.idm.api.User;
import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.idm.engine.impl.UserQueryImpl;
import org.flowable.idm.engine.impl.persistence.entity.UserEntity;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.flowable.idm.engine.impl.persistence.entity.data.AbstractDataManager;
import org.flowable.idm.engine.impl.persistence.entity.data.UserDataManager;

/**
 * @author Joram Barrez
 */
public class MybatisUserDataManager extends AbstractDataManager<UserEntity> implements UserDataManager {

    public MybatisUserDataManager(IdmEngineConfiguration idmEngineConfiguration) {
        super(idmEngineConfiguration);
    }

    @Override
    public Class<? extends UserEntity> getManagedEntityClass() {
        return UserEntityImpl.class;
    }

    @Override
    public UserEntity create() {
        return new UserEntityImpl();
    }

    @SuppressWarnings("unchecked")
    public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
        return getDbSqlSession().selectList("selectUserByQueryCriteria", query, page);
    }

    public long findUserCountByQueryCriteria(UserQueryImpl query) {
        return (Long) getDbSqlSession().selectOne("selectUserCountByQueryCriteria", query);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findUsersByPrivilegeId(String privilegeId) {
        return getDbSqlSession().selectList("selectUsersWithPrivilegeId", privilegeId);
    }

    @SuppressWarnings("unchecked")
    public List<User> findUsersByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        return getDbSqlSession().selectListWithRawParameter("selectUserByNativeQuery", parameterMap, firstResult, maxResults);
    }

    public long findUserCountByNativeQuery(Map<String, Object> parameterMap) {
        return (Long) getDbSqlSession().selectOne("selectUserCountByNativeQuery", parameterMap);
    }

}
