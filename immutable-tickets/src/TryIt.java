import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

public class TryIt {

    public static void main(String[] args) {

        TicketService service = new TicketService();

        IncidentTicket t1 = service.createTicket(
                "TCK-1001",
                "reporter@example.com",
                "Payment failing on checkout"
        );

        System.out.println("Created: " + t1);

        // "Update" by creating NEW instances
        IncidentTicket t2 = service.assign(t1, "agent@example.com");
        IncidentTicket t3 = service.escalateToCritical(t2);

        System.out.println("\nAfter updates (new object): " + t3);

        // Old object unchanged
        System.out.println("\nOriginal still unchanged: " + t1);

        // Attempt external mutation
        List<String> tags = t3.getTags();

        try {
            tags.add("HACKED_FROM_OUTSIDE");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nTags are immutable from outside!");
        }

        System.out.println("\nFinal state: " + t3);
    }
}