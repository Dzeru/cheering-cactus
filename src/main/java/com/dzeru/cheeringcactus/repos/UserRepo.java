package com.dzeru.cheeringcactus.repos;

import com.dzeru.cheeringcactus.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;

@Service
@Repository
public interface UserRepo extends CrudRepository<User, Long>
{
    User findByUsername(String uuid);
}
