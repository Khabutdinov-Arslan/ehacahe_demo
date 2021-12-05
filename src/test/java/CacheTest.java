import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CacheTest {
    SquareCache testCache;

    @Before
    public void init(){
        testCache = new SquareCache();
    }

    @Test
    public void preemptionTest(){
        testCache.CalculateSquare(1);
        assertEquals(testCache.CalculateSquare(1), Integer.valueOf(1));
        assertEquals(testCache.GetSquareCached(1), Integer.valueOf(1));
        testCache.CalculateSquare(2);
        assertEquals(testCache.GetSquareCached(2), Integer.valueOf(4));
        testCache.CalculateSquare(3);
        assertNull(testCache.GetSquareCached(4));
        testCache.CalculateSquare(4);
        assertNull(testCache.GetSquareCached(1));
    }
}
