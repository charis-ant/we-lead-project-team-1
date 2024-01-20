package gr.athtech.spring.app.mapper;

import gr.athtech.spring.app.model.Account;
import gr.athtech.spring.app.transfer.resource.AccountResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface AccountMapper extends BaseMapper<Account, AccountResource> {
}
