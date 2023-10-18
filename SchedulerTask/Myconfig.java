package com.aem.geeks.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="Scheduler Task",
            description = "OCD description")
public @interface Myconfig {

    @AttributeDefinition(
                name = "Scheduler name",
                description = "name of scheduler.",
                type = AttributeType.STRING)
        public String scheduler_name() default "Nagendra Babu";

        // cron expression for every two minutes
        @AttributeDefinition(
                name = "Cron Job expression",
                description = "used to configure instances of CronTrigger.",
                type = AttributeType.STRING)
        public String cron_expression() default "0/20 * * * * ?";

        @AttributeDefinition(
                name = "Enable Scheduler",
                description = "enable Scheduler.",
                type = AttributeType.BOOLEAN)
        public boolean enable_scheduler() default true;

        @AttributeDefinition(
                name = "Concurrent Scheduler",
                description = "concurrent Scheduler.",
                type = AttributeType.BOOLEAN)
        public boolean concurrent_Scheduler() default false;

    
}
