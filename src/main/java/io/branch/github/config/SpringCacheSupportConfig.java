package io.branch.github.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.QueryTimeoutException;

import io.lettuce.core.RedisCommandTimeoutException;

@Configuration
public class SpringCacheSupportConfig extends CachingConfigurerSupport {
	@Override
	public CacheErrorHandler errorHandler() {
		return new SpringCacheErrorHandler();
	}

	private class SpringCacheErrorHandler implements CacheErrorHandler {
		private final Logger LOG = LoggerFactory.getLogger(SpringCacheErrorHandler.class);

		@Override
		public void handleCacheGetError(final RuntimeException exception, final Cache cache, final Object key) {
			defaultExceptionHandler(exception);
		}

		@Override
		public void handleCachePutError(final RuntimeException exception, final Cache cache, final Object key, final Object value) {
			defaultExceptionHandler(exception);
		}

		@Override
		public void handleCacheEvictError(final RuntimeException exception, final Cache cache, final Object key) {
			defaultExceptionHandler(exception);
		}

		@Override
		public void handleCacheClearError(final RuntimeException exception, final Cache cache) {
			defaultExceptionHandler(exception);
		}

		private void defaultExceptionHandler(final RuntimeException e) {
			if (e instanceof RedisCommandTimeoutException || e instanceof QueryTimeoutException) {
				LOG.warn("Redis cache timed out: ", e);
				return;
			}

			LOG.error("Redis cache exception occurred: ", e);
		}
	}
}
