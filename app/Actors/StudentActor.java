package Actors;

import Protocols.StudentProtocol;
import Protocols.TeacherProtocol;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by ahmad on 03/09/15.
 */
public class StudentActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    ActorRef teacherActorRef;

    public StudentActor(ActorRef teacherActorRef) {
        this.teacherActorRef = teacherActorRef;
    }


    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof StudentProtocol.InitSignal) {
            TeacherProtocol.QuoteRequest quoteRequest = new TeacherProtocol.QuoteRequest();
            teacherActorRef.tell(quoteRequest, self());
        } else if (message instanceof TeacherProtocol.QuoteResponse) {
            log.info("Received QuoteResponse from Teacher");
            log.info("Printing from Student Actor " + ((TeacherProtocol.QuoteResponse) message).quoteResponse);
        }
    }
}
