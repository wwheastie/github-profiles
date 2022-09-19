package io.branch.github.user;

import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
class UserMapper extends ConfigurableMapper {
	@Override
	protected void configure(final MapperFactory mapperFactory) {
		mapperFactory.classMap(GithubUserDTO.class, UserBO.class)
				.byDefault()
				.register();
	}
}
