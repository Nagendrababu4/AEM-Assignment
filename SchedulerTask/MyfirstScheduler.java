package com.aem.geeks.core.schedulers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.geeks.core.config.Myconfig;
import com.aem.geeks.core.services.ExampleService;
import com.day.cq.wcm.api.Page;

@Component(service = MyfirstScheduler.class, immediate=true)
@Designate(ocd = Myconfig.class)
public class MyfirstScheduler implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference
    private Scheduler scheduler;

    @Reference
    ExampleService service;
    

    @Activate
    protected void activate(Myconfig config) {
        logger.info("Myconfig activate method called");
        // Execute this method to add scheduler
        addScheduler(config);
    }
    // add all configurations to schedule a scheduler depending on name and expression
    private void addScheduler(Myconfig config) {
        logger.info("Scheduler added successfully.........");
        if (config.enable_scheduler()) {
            ScheduleOptions options = scheduler.EXPR(config.cron_expression());
            options.name(config.scheduler_name());
            options.canRunConcurrently(config.concurrent_Scheduler());
            // add scheduler to call depending on option passed.
            scheduler.schedule(this, options);
            logger.info("Scheduler added successfully '{}'",config.scheduler_name());
        } else {
            logger.info("My config disabled===================");
        }
    }
    // on deactivate component it will unschdule scheduler
    @Deactivate
    protected void deactivate(Myconfig config) {
        removeScheduler(config);
    }
    // custom method to deactivate or unschedule scheduler
    private void removeScheduler(Myconfig config) {
        scheduler.unschedule(config.scheduler_name());
    }
    
    // run() method will get call every two minutes
    @Override
    public void run() {

        logger.info("Myconfig run method started..........");

        // below code is getting title and path using child pages
        List<String> listpages = new ArrayList<String>();
        
        try {
            Iterator<Page> pages = service.getPages();
            while (pages.hasNext()) {
                listpages.add(pages.next().getTitle());
                listpages.add(pages.next().getPath());
        }
           
        } catch (Exception e) {
        }
         logger.info("Myconfig run method completed..........'{}'", listpages.toString());
        
    }
}
