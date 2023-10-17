package com.aem.geeks.core.servlets;

import java.io.IOException;
import java.util.Iterator;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = Servlet.class)
@SlingServletPaths(
    value = {"/libs/babu"})

@SlingServletResourceTypes(
        resourceTypes = "aemgeeks/components/helloworld",
        selectors = {"geeks","test"},
        extensions = {"txt","xml"}
)
public class MyfirstServelt extends SlingAllMethodsServlet{

    @Override
    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res) throws IOException{
        ResourceResolver resourceResolver = req.getResourceResolver();
        String parameter = req.getParameter("parameter");

        Page page = resourceResolver.adaptTo(PageManager.class).getPage(parameter);
        JSONArray pagearray = new JSONArray();
        
        Iterator<Page> listChildren = page.listChildren();

        while (listChildren.hasNext()) {
            Page listChild = listChildren.next();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put(listChild.getTitle(), listChild.getPath().toString());
                pagearray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            } 
        }
        res.setContentType("application/json");
        res.getWriter().write(pagearray.toString());
    }
}