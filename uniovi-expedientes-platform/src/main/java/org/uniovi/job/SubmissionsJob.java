package org.uniovi.job;

import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.schedule.AbstractScheduledLockedJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SubmissionsJob extends AbstractScheduledLockedJob {
    @Override
    public void executeJob(JobExecutionContext jobContext) throws JobExecutionException {
        JobDataMap jobData = jobContext.getJobDetail().getJobDataMap();

        // Extract the Job executer to use
        Object executerObj = jobData.get("jobExecuter");
        if (executerObj == null || !(executerObj instanceof SubmissionsJobExecuter)) {
            throw new AlfrescoRuntimeException("ScheduledJob data must contain valid 'Executer' reference");
        }

        final SubmissionsJobExecuter jobExecuter = (SubmissionsJobExecuter) executerObj;

        AuthenticationUtil.runAsSystem(() -> {

            jobExecuter.execute();

            return null;
        });
    }
}
