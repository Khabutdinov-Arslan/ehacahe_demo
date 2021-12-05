import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.net.InetAddress;

public class SquareCache {
    private final Cache<Integer, Integer> squareCache;

    public SquareCache(){
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        squareCache = cacheManager.createCache("squareCache",
                CacheConfigurationBuilder.
                        newCacheConfigurationBuilder(Integer.class, Integer.class, ResourcePoolsBuilder.heap(3)));
    }

    public Integer GetSquareCached(Integer x){
        return squareCache.get(x);
    }

    public Integer CalculateSquare(Integer x){
        Integer cachedValue = squareCache.get(x);
        if (cachedValue == null){
            Integer calculatedValue = x * x;
            squareCache.put(x, calculatedValue);
            return calculatedValue;
        } else {
            return cachedValue;
        }
    }

}
