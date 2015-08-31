package Protocols;

/**
 * Created by ahmad on 31/08/15.
 */
public class TeacherProtocol {
    /*
     * The Student sends this message to request for a Quotation
     *
    */
    public static final class QuoteRequest {
    }

    /*
     * The TeacherActor responds back to the Student with this message object
     * The actual quote string is wrapped inside the response.
     *
     */
    public static final class QuoteResponse {
        public final String quoteResponse;

        public QuoteResponse(String quoteResponse) {
            this.quoteResponse = quoteResponse;
        }
    }
}
