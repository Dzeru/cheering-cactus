package com.dzeru.cheeringcactus.repos;

import com.dzeru.cheeringcactus.entities.Cactus;
import org.springframework.data.repository.CrudRepository;

public interface CactusRepo extends CrudRepository<Cactus, Long>
{
    Cactus findByUuid(String uuid);
}
