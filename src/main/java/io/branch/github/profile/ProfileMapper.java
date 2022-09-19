package io.branch.github.profile;

import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
class ProfileMapper extends ConfigurableMapper {
	@Override
	protected void configure(final MapperFactory mapperFactory) {
		mapperFactory.classMap(ProfileBO.class, ProfileDTO.class)
				.field("user.userName", "userName")
				.field("user.displayName", "displayName")
				.field("user.avatar", "avatar")
				.field("user.geoLocation", "geoLocation")
				.field("user.email", "email")
				.field("user.url", "url")
				.field("user.createdAt", "createdAt")
				.byDefault()
				.register();
	}
}
