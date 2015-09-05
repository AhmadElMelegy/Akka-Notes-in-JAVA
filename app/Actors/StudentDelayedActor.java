package Actors;

import Protocols.StudentProtocol;
import Protocols.TeacherProtocol;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by ahmad on 04/09/15.
 */
public class StudentDelayedActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    ActorRef teacherActorRef;

    public StudentDelayedActor(ActorRef teacherActorRef) {
        this.teacherActorRef = teacherActorRef;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof StudentProtocol.InitSignal) {
            TeacherProtocol.QuoteRequest quoteRequest = new TeacherProtocol.QuoteRequest();
            //     teacherActorRef.tell(quoteRequest, self());
            getContext().system().scheduler().schedule(
                    Duration.create(0, TimeUnit.MILLISECONDS),
                    Duration.create(1000, TimeUnit.MILLISECONDS),
                    teacherActorRef,
                    quoteRequest,
                    getContext().system().dispatcher(),
                    self()
            );
        } else if (message instanceof TeacherProtocol.QuoteResponse) {
            log.info("Received QuoteResponse from Teacher");
            log.info("Printing from Student Actor " + ((TeacherProtocol.QuoteResponse) message).quoteResponse);
        }

    }
}
