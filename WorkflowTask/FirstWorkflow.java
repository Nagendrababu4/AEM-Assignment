package com.aem.geeks.core.workflows;


import javax.jcr.Session;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.replication.AgentIdFilter;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.ReplicationOptions;
import com.day.cq.replication.Replicator;

@Component(
        service = WorkflowProcess.class,
        property = 
                "process.label = MyReplicationStep"
)
public class FirstWorkflow implements WorkflowProcess{

    @Reference
    private Replicator replicator;

    private final static String agentId = "publish";

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
        AgentIdFilter agentIdFilter = new AgentIdFilter(agentId);
        ReplicationOptions replicationOptions = new ReplicationOptions();
        replicationOptions.setFilter(agentIdFilter);
        String path = workItem.getWorkflowData().getPayload().toString();
        try {
             replicator.replicate(workflowSession.adaptTo(Session.class), ReplicationActionType.ACTIVATE, path, replicationOptions);
        } catch (ReplicationException e) {
            e.printStackTrace();
        }
    }
}
