package com.aem.geeks.core.services;

import java.util.Iterator;

import org.apache.sling.api.SlingHttpServletRequest;

import com.day.cq.wcm.api.Page;

public interface ExampleService {
    Iterator<Page> getlistChildren(SlingHttpServletRequest req);

    // this method is used for getting paths and title using scheduler
    Iterator<Page> getPages();
}
