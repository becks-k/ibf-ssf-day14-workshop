package ibf.ssf.day14.configuration;

import ibf.ssf.day14.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    
    // @Value injects a default value for field
    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private Integer redisPort;

    @Value("${spring.data.redis.username}")
    private String redisUsername;

    @Value("${spring.data.redis.password}")
    private String redisPassword;

    @Value("${spring.data.redis.database}")
    private String redisDb;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        
        // Sets up a single node connection to Redis via RedisConnection with RedisConnectionFactory 
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(redisHost, redisPort);

        // No need to set username and password

        // Build redis client configuration for Jedis
        JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        JedisConnectionFactory jedisConn = new JedisConnectionFactory(redisConfig, jedisClient);
        jedisConn.afterPropertiesSet();

        return jedisConn;
    }

    // Data will be stored as a list of strings
    // employees:["firstName1, lastName1, salary1", "firstName2, lastName2, salary2"]
    @Bean(Util.T_ONE)
    public RedisTemplate<String, String> redisListTemplate() {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());

        return template;
    }
}
