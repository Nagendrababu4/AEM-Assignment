package com.aem.geeks.core.models.impl;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.SlingHttpServletRequest;
import com.aem.geeks.core.models.College;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = College.class,        
        defaultInjectionStrategy =DefaultInjectionStrategy.OPTIONAL)
public class CollegeSlingmodelImpl implements College{

    @ValueMapValue
    private String fname;

    @ValueMapValue
    private String lname;

    @ValueMapValue
    private String text;

     @ValueMapValue
    private String pathbrowser;

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getText() {
        return text;
    }

    public String getPathbrowser() {
        return pathbrowser;
    }

    
}
