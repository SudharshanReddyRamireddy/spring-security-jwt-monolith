package SpringSecutity.Practice.SchedulersHandling;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import SpringSecutity.Practice.service.TokenBlockListService;

@Component
public class BlocklistCleanupScheduler {
	
	private static final Logger log = LoggerFactory.getLogger(BlocklistCleanupScheduler.class);
	
	
	private final TokenBlockListService blockListService;
	
	public BlocklistCleanupScheduler(TokenBlockListService blockListService) {
		this.blockListService = blockListService;
	}
	
	
	
	// Run every 24 hours
    @Scheduled(fixedRate = 24 * 60 * 1000)
    public void cleanupBlocklist() {
    	log.info("BlocklistCleanupScheduler executed");
        Set<String> blacklist = blockListService.getBlacklistSet();
        blacklist.clear(); // remove all tokens which are in Block List set
        log.info("********* BLOCK LIST CLEANED ********");
    }

}
