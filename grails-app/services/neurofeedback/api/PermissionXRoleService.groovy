package neurofeedback.api

import grails.gorm.services.Service

@Service(PermissionXRole)
interface PermissionXRoleService {

    PermissionXRole get(Serializable id)

    List<PermissionXRole> list(Map args)

    Long count()

    void delete(Serializable id)

    PermissionXRole save(PermissionXRole permissionXRole)

}