package com.example.reports;

public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;

    private RealReport realReport; // cached instance
    private final AccessControl accessControl = new AccessControl();

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {

        // 1️⃣ Access check
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED for " + user.getName()
                    + " to report " + reportId);
            return;
        }

        // 2️⃣ Lazy loading
        if (realReport == null) {
            System.out.println("[proxy] creating RealReport for " + reportId);
            realReport = new RealReport(reportId, title, classification);
        }

        // 3️⃣ Use cached instance
        realReport.display(user);
    }
}