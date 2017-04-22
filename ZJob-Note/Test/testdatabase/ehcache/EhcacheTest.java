package testdatabase.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EhcacheTest {
	
	private static Logger LOG = LoggerFactory.getLogger(EhcacheTest.class);

	@Test
	public void test1(){
		CacheManager cacheManager = new CacheManager("ehcache-conf.xml");
		Cache cache = cacheManager.getCache("cache-maxelement-test");

		Element element1 = new Element("no1","v1");
	    cache.put(element1);
		
	    Element element2 = new Element("no2","v2");
	    cache.put(element2);
	    
		Element c1 = cache.get("no1");
		LOG.debug(c1.toString());

		Element c2 = cache.get("no2");
		LOG.debug(c2.toString());
		
	}
	
}
