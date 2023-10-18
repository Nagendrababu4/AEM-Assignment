package com.aem.geeks.core.services.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import com.aem.geeks.core.services.ExampleService;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = ExampleService.class,immediate = true)
public class FirstServiceImpl implements ExampleService{

    @Reference
    ResourceResolverFactory resourceResolver;

    @Activate
    public void activate(ComponentContext componentContext){
    }

    @Deactivate
    public void deactivate(ComponentContext componentContext){
    }

    @Modified
    public void modified(ComponentContext componentContext){
    }

    @Override
    public Iterator<Page> getlistChildren(SlingHttpServletRequest req) {
          ResourceResolver resourceResolver = req.getResourceResolver();
          String parameter = req.getParameter("parameter");
          try {
            PageManager pageManager=resourceResolver.adaptTo(PageManager.class);
            Page page=pageManager.getPage(parameter);
            Iterator<Page> pages=page.listChildren();
            return pages;}
            catch(Exception e){
            }
        return null;
    }

    // this code is used for get pages using scheduler
    @Override
    public Iterator<Page> getPages() {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put(ResourceResolverFactory.SUBSERVICE, "myuser");
        try {
            ResourceResolver serviceResourceResolver = resourceResolver.getServiceResourceResolver(hashMap);
            PageManager pagemanager = serviceResourceResolver.adaptTo(PageManager.class);
            Page page = pagemanager.getPage("/content/aemgeeks/us/en");
            Iterator<Page> listChildren = page.listChildren();
            return listChildren;
        } catch (LoginException e) {
        }
        return null; 
    }
}
